package org.fbi.gwk.processor;


import org.fbi.gwk.domain.tps.SingleRecordMsg;
import org.fbi.gwk.domain.tps.base.TpsMsg;
import org.fbi.gwk.domain.tps.base.TpsMsgHead;
import org.fbi.gwk.domain.tps.record.Record1102;
import org.fbi.gwk.domain.tps.record.Record2102;
import org.fbi.gwk.domain.tps.record.Record9905;
import org.fbi.gwk.domain.tps.record.Record9906;
import org.fbi.linking.processor.ProcessorContext;
import org.fbi.linking.processor.ProcessorException;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorRequest;
import org.fbi.linking.processor.standprotocol10.Stdp10ProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhanrui on 13-12-31.
 * ǩ�����ף��Զ�����
 */
public class T9905Processor extends AbstractTxnProcessor {
    protected static String CONTEXT_TPS_AUTHCODE = "CONTEXT_TPS_AUTHCODE";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void doRequest(Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) throws ProcessorException, IOException {
        SingleRecordMsg tpsToa = new SingleRecordMsg();
        String strTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TpsMsgHead head = new TpsMsgHead();
        head.setSrc(this.src);
        head.setDes(this.des);
        head.setDataType("9905");
        head.setMsgId("990537" + strTime);
        head.setMsgRef("990537" + strTime);
        head.setWorkDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        tpsToa.setHead(head);

        Record9905 record9905 = new Record9905();
        record9905.setUser_code(this.userCode);
        record9905.setPassword(this.password);
        record9905.setNew_password("");
        tpsToa.getBody().getRecords().add(record9905);
        String reqXml = tpsToa.toXml(tpsToa);

        TpsContext tpsContext = new TpsContext();
        tpsContext.setTpsTiaTxnCode("9905");
        tpsContext.setTpsTiaXml(reqXml);

        try {
            processThirdPartyServer(tpsContext);
            String msgtia = tpsContext.getTpsToaXml();
            String rtnDataType = substr(msgtia, "<dataType>", "</dataType>").trim();
            if ("9906".equals(rtnDataType)) { //ҵ������������ 9906
/*                byte[] recvTpsBuf = msgtia.getBytes("GBK");
                int authLen = Integer.parseInt(new String(recvTpsBuf, 51, 3));
                String msgdata = new String(recvTpsBuf, 69 + authLen, recvTpsBuf.length - 69 - authLen);
                logger.info("===���������������ر���(ҵ����Ϣ��)��\n" + msgdata);*/
                msgtia = substrEnd(msgtia,"<?xml","</Root>");
                TpsMsg msgBean = new SingleRecordMsg();
                msgBean = msgBean.toBean(msgtia,  Record9906.class);
                SingleRecordMsg tiaMsg = (SingleRecordMsg)msgBean;
                Record9906 record9906 = (Record9906)tiaMsg.getBody().getRecords().get(0);
                //������Ȩ����Ϣ
                ProcessorContext context = request.getProcessorContext();
                context.setAttribute(CONTEXT_TPS_AUTHCODE, record9906.getAccredit_code());
            } else {
                //TODO
                logger.error("ǩ�����׷����쳣.");
                throw new RuntimeException("ǩ�����׷����쳣.");
            }
        } catch (Exception e) {
            logger.error("�������������ͨѶ�����쳣.", e);
            throw new RuntimeException("�������������ͨѶ�����쳣.",e);
        }
    }


}
