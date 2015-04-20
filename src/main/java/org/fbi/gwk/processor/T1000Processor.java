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
 * 1381000��ѯ������ϸ
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
            logger.error("[sn=" + hostTxnsn + "] " + "��ɫҵ��ƽ̨�����Ľ�������.", e);
            throw new RuntimeException(e);
        }
        //ҵ���߼�����
        CbsRtnInfo cbsRtnInfo = null;
        try {
            CbsToa1000 toa = new CbsToa1000();
            cbsRtnInfo = processTxn(tia, toa,request,response);
            //��ɫƽ̨��Ӧ
            response.setHeader("rtnCode", cbsRtnInfo.getRtnCode().getCode());
            String cbsRespMsg = cbsRtnInfo.getRtnMsg();
            if (cbsRtnInfo.getRtnCode().equals(TxnRtnCode.TXN_EXECUTE_SECCESS)) {
                cbsRespMsg = marshalCbsResponseMsg(toa);
            }
            response.setResponseBody(cbsRespMsg.getBytes(response.getCharacterEncoding()));
        } catch (Exception e) {
            logger.error("[sn=" + hostTxnsn + "] " + "���״����쳣.", e);
            throw new RuntimeException("���״����쳣", e);
        }

    }


    private CbsRtnInfo processTxn(CbsTia1000 tia, CbsToa1000 toa,Stdp10ProcessorRequest request, Stdp10ProcessorResponse response) {

        //��鱾�����ݿ���Ϣ
        LsPaybackinfo paybackinfo = selectPayBackInfoByVouch(tia.getVchNo(),tia.getAreaCode());
        CbsRtnInfo cbsRtnInfo = new CbsRtnInfo();
        try {
            if (paybackinfo != null) {
                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
                cbsRtnInfo.setRtnMsg("��ˮ���ظ�");

                return cbsRtnInfo;
            }
            String strToa = "";
            String strTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //�����ֶ˴��� 2102����
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
            if ("9910".equals(rtnDataType)) { //�������쳣���� 9910
                msgtia = substrEnd(msgtia,"<?xml","</Root>");
                TpsMsg msgBean = new SingleRecordMsg();
                msgBean = msgBean.toBean(msgtia,  Record9910.class);
                SingleRecordMsg tiaMsg = (SingleRecordMsg)msgBean;
                Record9910 record9910 = (Record9910)tiaMsg.getBody().getRecords().get(0);
                String errType = record9910.getResult();
                String errMsg = record9910.getAdd_word();
                if (StringUtils.isNotEmpty(errType) && "E301".equals(errType)) { //����ǩ������
                    T9905Processor t9905Processor = new T9905Processor();
                    t9905Processor.doRequest(request, response);
                    errMsg = "��Ȩ��䶯,�����·�����.";
                } else { //����ǰ̨������Ϣ
                    if (StringUtils.isEmpty(errMsg)) errMsg = "��������:�������쳣";
                    else errMsg = "��������:" + errMsg;
                }
                logger.info("===���������������ر���(�쳣ҵ����Ϣ��)��\n" + record9910.toString());
                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
                cbsRtnInfo.setRtnMsg(TxnRtnCode.TXN_EXECUTE_FAILED.getTitle());
                return cbsRtnInfo;
            } else if ("1102".equals(rtnDataType)) {//��������
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
            cbsRtnInfo.setRtnMsg("�����쳣");
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

    //����δ����ļ�¼
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
            if (infos.size() != 1) { //ͬһ���ɿ�ţ�δ�������ڱ���ֻ�ܴ���һ����¼
                throw new RuntimeException("��¼״̬����.");
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
