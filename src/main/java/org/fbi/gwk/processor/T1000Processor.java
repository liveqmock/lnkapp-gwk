package org.fbi.gwk.processor;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fbi.gwk.domain.cbs.T1000Request.CbsTia1000;
import org.fbi.gwk.domain.cbs.T1000Response.CbsToa1000;
import org.fbi.gwk.domain.tps.MultiRecordMsg;
import org.fbi.gwk.domain.tps.SingleRecordMsg;
import org.fbi.gwk.domain.tps.base.PaybackDetlConverter;
import org.fbi.gwk.domain.tps.base.TpsMsg;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;
import org.fbi.gwk.domain.tps.base.TpsMsgHead;
import org.fbi.gwk.domain.tps.record.*;
import org.fbi.gwk.enums.TxnRtnCode;
import org.fbi.gwk.helper.MybatisFactory;
import org.fbi.gwk.helper.ProjectConfigManager;
import org.fbi.gwk.helper.TpsSocketClient;
import org.fbi.gwk.internal.AppActivator;
import org.fbi.gwk.repository.dao.gwk.LsPaybackinfoMapper;
import org.fbi.gwk.repository.model.gwk.LsPaybackinfo;
import org.fbi.gwk.repository.model.gwk.LsPaybackinfoExample;
import org.fbi.gwk.tpsserver.hdserver.MsgCommHeader;
import org.fbi.linking.codec.dataformat.FixedLengthTextDataFormat;
import org.fbi.linking.codec.dataformat.SeperatedTextDataFormat;
import org.fbi.linking.processor.ProcessorException;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhanrui
 * 20150316.
 * 1381000查询还款明细
 */
public class T1000Processor extends AbstractTxnProcessor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doRequest(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        String txnDate = request.getHeader("txnTime").substring(0, 8);
        String hostTxnsn = request.getHeader("serialNo");

        CbsTia1000 tia = new CbsTia1000();
        try {
            SeperatedTextDataFormat dataFormat = new SeperatedTextDataFormat(tia.getClass().getPackage().getName());
            tia = (CbsTia1000) dataFormat.fromMessage(new String(request.getRequestBody(), "GBK"), "CbsTia1000");
        } catch (Exception e) {
            logger.error("[sn=" + hostTxnsn + "] " + "特色业务平台请求报文解析错误.", e);
            throw new RuntimeException(e);
        }
        //业务逻辑处理
        CbsRtnInfo cbsRtnInfo = null;
        try {
            CbsToa1000 toa = new CbsToa1000();
            cbsRtnInfo = processTxn(tia, toa,request,response);
            //特色平台响应
            response.setHeader("rtnCode", cbsRtnInfo.getRtnCode().getCode());
            String cbsRespMsg = cbsRtnInfo.getRtnMsg();
            if (cbsRtnInfo.getRtnCode().equals(TxnRtnCode.TXN_EXECUTE_SECCESS)) {
                cbsRespMsg = marshalCbsResponseMsg(toa);
            }
            response.setResponseBody(cbsRespMsg.getBytes(response.getCharacterEncoding()));
        } catch (Exception e) {
            logger.error("[sn=" + hostTxnsn + "] " + "交易处理异常.", e);
            throw new RuntimeException("交易处理异常", e);
        }

    }


    private CbsRtnInfo processTxn(CbsTia1000 tia, CbsToa1000 toa,Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) {

        //检查本地数据库信息
        LsPaybackinfo paybackinfo = selectPayBackInfoByVouch(tia.getVchNo(),tia.getAreaCode());
        CbsRtnInfo cbsRtnInfo = new CbsRtnInfo();
        try {
            if (paybackinfo != null) {
                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
                cbsRtnInfo.setRtnMsg("流水号重复");

                return cbsRtnInfo;
            }
            String strToa = "";
            String strTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //国土局端处理 2102交易
            SingleRecordMsg tpsToa = new SingleRecordMsg();
            TpsMsgHead head = new TpsMsgHead();
            head.setSrc(this.src);
            head.setDes(this.des);
            head.setDataType("2102");
            head.setMsgId("210237" + strTime);
            head.setMsgRef("210237" + strTime);
            head.setWorkDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            tpsToa.setHead(head);

            Record2102 record2102 = new Record2102();
            record2102.setSet_year(new SimpleDateFormat("yyyy").format(new Date())); //TODO
            record2102.setBill_no(tia.getVchNo());
            record2102.setBank_code(this.bankCode);
            record2102.setDownload_type("0");
            record2102.setPack_no("");
            record2102.setChild_pack_no("");
            tpsToa.getBody().getRecords().add(record2102);
            String reqXml = tpsToa.toXml(tpsToa);


            TpsContext tpsContext = new TpsContext();
            tpsContext.setTpsTiaTxnCode("2102");
            tpsContext.setTpsTiaXml(reqXml);
            processThirdPartyServer(tpsContext);
            String msgtia = tpsContext.getTpsToaXml();
            String rtnDataType = substr(msgtia, "<dataType>", "</dataType>").trim();
            if ("9910".equals(rtnDataType)) { //技术性异常报文 9910
                msgtia = substrEnd(msgtia,"<?xml","</Root>");
                TpsMsg msgBean = new SingleRecordMsg();
                msgBean = msgBean.toBean(msgtia,  Record9910.class);
                SingleRecordMsg tiaMsg = (SingleRecordMsg)msgBean;
                Record9910 record9910 = (Record9910)tiaMsg.getBody().getRecords().get(0);
                String errType = record9910.getResult();
                String errMsg = record9910.getAdd_word();
                if (StringUtils.isNotEmpty(errType) && "E301".equals(errType)) { //发起签到交易
                    T9905Processor t9905Processor = new T9905Processor();
                    t9905Processor.doRequest(request, response);
                    errMsg = "授权码变动,请重新发起交易.";
                } else { //返回前台错误信息
                    if (StringUtils.isEmpty(errMsg)) errMsg = "财政返回:服务器异常";
                    else errMsg = "财政返回:" + errMsg;
                }
                logger.info("===第三方服务器返回报文(异常业务信息类)：\n" + record9910.toString());
                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
                cbsRtnInfo.setRtnMsg(TxnRtnCode.TXN_EXECUTE_FAILED.getTitle());
                return cbsRtnInfo;
            } else if ("1102".equals(rtnDataType)) {//正常报文
                msgtia = substrEnd(msgtia,"<?xml","</Root>");
                TpsMsg msgBean = new MultiRecordMsg();
                msgBean = msgBean.toBean(msgtia,  Record1102.class);
                MultiRecordMsg tiaMsg = (MultiRecordMsg)msgBean;
                Record1102 record1102 = (Record1102)tiaMsg.getBody().getRecords().get(0);
                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_SECCESS);
                cbsRtnInfo.setRtnMsg(TxnRtnCode.TXN_EXECUTE_SECCESS.getTitle());
            } else {
                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
                cbsRtnInfo.setRtnMsg(TxnRtnCode.TXN_EXECUTE_FAILED.getTitle());
            }
            return cbsRtnInfo;
        } catch (Exception e) {
            cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
            cbsRtnInfo.setRtnMsg("处理异常");
            return cbsRtnInfo;
        }
    }

    private TpsMsg getTpsToaBean(String xml) {
        TpsMsg msgBean = new MultiRecordMsg();
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(MultiRecordMsg.class);
        xs.processAnnotations(Record1102.class);
        xs.processAnnotations(PaybackDetlRecord.class);
        xs.registerConverter(new PaybackDetlConverter(
                xs.getConverterLookup().lookupConverterForType(TpsMsgBodyBatchRecord.class),
                xs.getReflectionProvider()));
        return (TpsMsg) xs.fromXML(xml);
    }

    private TpsMsg getTpsToaBean9910(String xml) {
        TpsMsg msgBean = new MultiRecordMsg();
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(MultiRecordMsg.class);
        xs.processAnnotations(Record1102.class);

        xs.registerConverter(new PaybackDetlConverter(
                xs.getConverterLookup().lookupConverterForType(TpsMsgBodyBatchRecord.class),
                xs.getReflectionProvider()));
        return (TpsMsg) xs.fromXML(xml);
    }

    //查找未还款的记录
    private LsPaybackinfo selectPayBackInfoByVouch(String vchNo,String areaCode) {
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession session = null;
        List<LsPaybackinfo> infos = null;
        try {
            sqlSessionFactory = MybatisFactory.ORACLE.getInstance();
            session = sqlSessionFactory.openSession();
//            session.getConnection().setAutoCommit(false);
            LsPaybackinfoMapper mapper = session.getMapper(LsPaybackinfoMapper.class);
            mapper = session.getMapper(LsPaybackinfoMapper.class);
            LsPaybackinfoExample example = new LsPaybackinfoExample();
            example.createCriteria()
                    .andVoucheridEqualTo(vchNo)
                    .andAreacodeEqualTo(areaCode);
            infos = mapper.selectByExample(example);
            if (infos.size() == 0) {
                return null;
            }
            if (infos.size() != 1) { //同一个缴款单号，未撤销的在表中只能存在一条记录
                throw new RuntimeException("记录状态错误.");
            }
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return infos.get(0);
    }



}
