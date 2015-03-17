package org.fbi.gwk.processor;

import org.apache.commons.lang.StringUtils;
import org.fbi.gwk.helper.ProjectConfigManager;
import org.fbi.gwk.helper.TpsSocketClient;
import org.fbi.gwk.internal.AppActivator;
import org.fbi.gwk.tpsserver.hdserver.MsgCommHeader;
import org.fbi.linking.codec.dataformat.SeperatedTextDataFormat;
import org.fbi.linking.processor.ProcessorException;
import org.fbi.linking.processor.standprotocol10.Stdp10Processor;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User: zhanrui
 * Date: 2014-10-19
 */
public abstract class AbstractTxnProcessor extends Stdp10Processor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void service(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        String txnCode = request.getHeader("txnCode");
        String tellerId = request.getHeader("tellerId");
        String hostTxnsn = request.getHeader("serialNo");

        if (StringUtils.isEmpty(tellerId)) {
            tellerId = "TELLERID";
        }

        try {
            MDC.put("txnCode", txnCode);
            MDC.put("tellerId", tellerId);
            logger.info("CBS Request:" + "[sn=" + hostTxnsn + "]\n" + request.toString());
            doRequest(request, response);
            logger.info("CBS Response:" + "[sn=" + hostTxnsn + "]\n" + response.toString());
        } finally {
            MDC.remove("txnCode");
            MDC.remove("tellerId");
        }
    }

    abstract protected void doRequest(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException;


    //生成特色平台响应报文
    protected <T> String marshalCbsResponseMsg(T cbsToa) {
        String cbsRespMsg = "";
        Map<String, Object> modelObjectsMap = new HashMap<String, Object>();
        modelObjectsMap.put(cbsToa.getClass().getName(), cbsToa);
        SeperatedTextDataFormat cbsDataFormat = new SeperatedTextDataFormat(cbsToa.getClass().getPackage().getName());
        try {
            cbsRespMsg = (String) cbsDataFormat.toMessage(modelObjectsMap);
        } catch (Exception e) {
            throw new RuntimeException("特色平台报文转换失败.", e);
        }
        return cbsRespMsg;
    }

    //第三方服务处理：可根据交易号设置不同的超时时间
    protected void processThirdPartyServer(TpsContext context)  {
        String servIp = ProjectConfigManager.getInstance().getProperty("tps.server.ip");
        int servPort = Integer.parseInt(ProjectConfigManager.getInstance().getProperty("tps.server.port"));
        TpsSocketClient client = new TpsSocketClient(servIp, servPort);

        String timeoutCfg = ProjectConfigManager.getInstance().getProperty("tps.server.timeout.txn." + context.getTpsTiaTxnCode());
        if (timeoutCfg != null) {
            int timeout = Integer.parseInt(timeoutCfg);
            client.setTimeout(timeout);
        } else {
            timeoutCfg = ProjectConfigManager.getInstance().getProperty("tps.server.timeout");
            if (timeoutCfg != null) {
                int timeout = Integer.parseInt(timeoutCfg);
                client.setTimeout(timeout);
            }
        }

        //通讯
        try {
            byte[] bizMsgBuf  = context.getTpsTiaXml().getBytes("GBK");
            String commHeader = assembleTpsCommmsgHeader(context, bizMsgBuf.length);
            byte[] commMsgBuf = commHeader.getBytes();

            byte[] sendTpsBuf = new byte[commMsgBuf.length + bizMsgBuf.length];
            System.arraycopy(commMsgBuf, 0, sendTpsBuf, 0, commMsgBuf.length);
            System.arraycopy(bizMsgBuf, 0, sendTpsBuf, commMsgBuf.length, bizMsgBuf.length);

            logger.info("TPS Request:[HEAD=]" + context.getTpsTiaHeader() + "\n\t[XML=]" + context.getTpsTiaXml());

            byte[] rcvTpsBuf = client.call(sendTpsBuf);
            String respMsg = new String(rcvTpsBuf, "GBK");

            MsgCommHeader respCommHead = new MsgCommHeader(respMsg);
            context.setTpsToaHeader(respCommHead);
            context.setTpsToaTxnCode(respCommHead.getDataType());
            String respXml = respMsg.substring(respCommHead.getMsgHeaderLength());
            context.setTpsToaXml(respXml);
            logger.info("TPS Response:[HEAD=]" + context.getTpsToaHeader() + "\n\t[XML=]" + context.getTpsToaXml());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码错误", e);
        }
    }

    //组第三方服务器 通讯报文头
    private String assembleTpsCommmsgHeader(TpsContext context, int bizMsgLen) {
        String authoCode = "authorization";

        MsgCommHeader msgCommHeader = new MsgCommHeader();
        msgCommHeader.setSenderCode("111");
        msgCommHeader.setRecverCode("222");
        msgCommHeader.setDataType(context.getTpsTiaTxnCode());
        msgCommHeader.setSendTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        msgCommHeader.setSignFlag("0");

        msgCommHeader.setAuthoCode(authoCode);
        int authoCodeLen = 0;
        try {
            authoCodeLen = authoCode.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            //
        }
        msgCommHeader.setAuthoCodeLen("" + authoCodeLen);

        int msgLen = bizMsgLen + msgCommHeader.getMsgHeaderLength();
        msgCommHeader.setMsgLen("" + msgLen);

        //保存TIA MSG HEAD
        context.setTpsTiaHeader(msgCommHeader);
        return msgCommHeader.marshal();
    }

    protected String getTpsRtnErrorMsg(String rtnCode) {
        BundleContext bundleContext = AppActivator.getBundleContext();
        URL url = bundleContext.getBundle().getEntry("rtncode.properties");

        Properties props = new Properties();
        try {
            props.load(url.openConnection().getInputStream());
        } catch (Exception e) {
            throw new RuntimeException("错误码配置文件解析错误", e);
        }
        String property = props.getProperty(rtnCode);
        if (property == null) {
            property = "未定义对应的错误信息(错误码:" + rtnCode + ")";
        }
        return property;
    }
}
