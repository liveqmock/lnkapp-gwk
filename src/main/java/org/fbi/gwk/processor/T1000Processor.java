package org.fbi.gwk.processor;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fbi.gwk.domain.cbs.T1000Request.CbsTia1000;
import org.fbi.gwk.domain.cbs.T1000Response.CbsToa1000;
import org.fbi.gwk.domain.tps.MultiRecordMsg;
import org.fbi.gwk.domain.tps.SingleRecordMsg;
import org.fbi.gwk.domain.tps.base.PaybackDetlConverter;
import org.fbi.gwk.domain.tps.base.TpsMsg;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;
import org.fbi.gwk.domain.tps.record.PaybackDetlRecord;
import org.fbi.gwk.domain.tps.record.Record1102;
import org.fbi.gwk.domain.tps.record.Record2102;
import org.fbi.gwk.enums.TxnRtnCode;
import org.fbi.gwk.helper.MybatisFactory;
import org.fbi.gwk.helper.ProjectConfigManager;
import org.fbi.gwk.helper.TpsSocketClient;
import org.fbi.gwk.internal.AppActivator;
import org.fbi.gwk.repository.dao.gwk.LsPaybackinfoMapper;
import org.fbi.gwk.repository.model.gwk.LsPaybackinfo;
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
            cbsRtnInfo = processTxn(tia, toa);
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


    private CbsRtnInfo processTxn(CbsTia1000 tia, CbsToa1000 toa) {
        CbsRtnInfo cbsRtnInfo = new CbsRtnInfo();
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession session = null;
        try {
            sqlSessionFactory = MybatisFactory.ORACLE.getInstance();
            session = sqlSessionFactory.openSession();
            session.getConnection().setAutoCommit(false);
            LsPaybackinfoMapper mapper = session.getMapper(LsPaybackinfoMapper.class);

/*
            LsPaybackinfo txn = mapper.selectByExample();
            if (txn != null) {
                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
                cbsRtnInfo.setRtnMsg("流水号重复");
                return cbsRtnInfo;
            }
*/

            //国土局端处理 2102交易
            SingleRecordMsg tpsTia = new SingleRecordMsg();
            Record2102 record2102 = new Record2102();
            record2102.setSet_year(new SimpleDateFormat("yyyy").format(new Date())); //TODO
            record2102.setBill_no(tia.getVchNo());
            tpsTia.getBody().getRecords().add(record2102);
            String reqXml = tpsTia.toXml(tpsTia);


            TpsContext tpsContext = new TpsContext();
            tpsContext.setTpsTiaTxnCode("2102");
            tpsContext.setTpsTiaXml(reqXml);

            processThirdPartyServer(tpsContext);

            if ("2102".equals(tpsContext.getTpsToaTxnCode())) {//正常报文
                session.commit();

                TpsMsg msgBean = getTpsToaBean(tpsContext.getTpsToaXml());


//                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_SECCESS);
//                cbsRtnInfo.setRtnMsg(TxnRtnCode.TXN_EXECUTE_SECCESS.getTitle());
            } else {
                session.rollback();
//                cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
//                cbsRtnInfo.setRtnMsg(resultCode + getTpsRtnErrorMsg(resultCode));

            }
            return cbsRtnInfo;
        } catch (SQLException e) {
            session.rollback();
            cbsRtnInfo.setRtnCode(TxnRtnCode.TXN_EXECUTE_FAILED);
            cbsRtnInfo.setRtnMsg("数据库处理异常");
            return cbsRtnInfo;
        } finally {
            if (session != null) {
                session.close();
            }
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

}
