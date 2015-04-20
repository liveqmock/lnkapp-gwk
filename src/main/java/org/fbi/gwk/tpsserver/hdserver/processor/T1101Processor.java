package org.fbi.gwk.tpsserver.hdserver.processor;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.fbi.gwk.domain.tps.MultiRecordMsg;
import org.fbi.gwk.domain.tps.SingleRecordMsg;
import org.fbi.gwk.domain.tps.T1101;
import org.fbi.gwk.domain.tps.base.TpsMsg;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchHead;
import org.fbi.gwk.domain.tps.base.TpsMsgHead;
import org.fbi.gwk.domain.tps.base.TpsMsgSigns;
import org.fbi.gwk.domain.tps.record.Record1101;
import org.fbi.gwk.domain.tps.record.Record2101;
import org.fbi.gwk.domain.tps.record.Record9000;
import org.fbi.gwk.helper.MybatisFactory;
import org.fbi.gwk.helper.ProjectConfigManager;
import org.fbi.gwk.repository.dao.gwk.LsConsumeinfoMapper;
import org.fbi.gwk.repository.model.gwk.LsConsumeinfo;
import org.fbi.gwk.repository.model.gwk.LsConsumeinfoExample;
import org.fbi.gwk.tpsserver.hdserver.MsgCommHeader;
import org.fbi.gwk.tpsserver.hdserver.Processor;
import org.fbi.gwk.tpsserver.hdserver.TxnContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanrui on 2015/03/10.
 * 4.1.1	消费记录下载请求（1101）
 */
public class T1101Processor implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(T1101Processor.class);

    private String src = "";
    private String des = "";
    private String authCode = "";
    private String bankCode="";
    private String bankName = "";
    private String payeeSumAccount = "";
    private String payeeSumName = "";
    private String payeeRgCode = "";

    public T1101Processor(){
        this.src = ProjectConfigManager.getInstance().getProperty("tps.txn.login.src");
        this.des = ProjectConfigManager.getInstance().getProperty("tps.txn.login.des");
        this.authCode = ProjectConfigManager.getInstance().getProperty("authCode");
        this.bankCode = ProjectConfigManager.getInstance().getProperty("bankCode");
        this.bankName = ProjectConfigManager.getInstance().getProperty("bankName");
        this.payeeSumAccount = ProjectConfigManager.getInstance().getProperty("payeeSumAccount");
        this.payeeSumName = ProjectConfigManager.getInstance().getProperty("payeeSumName");
        this.payeeRgCode = ProjectConfigManager.getInstance().getProperty("payeeRgCode");
    }


    @Override
    public void service(TxnContext ctx) {
        try {
            String toa = "";
            String msgtia = ctx.getMsgtia();
            msgtia = substr(msgtia,"<?xml","</Root>");
            TpsMsg msgBean = new SingleRecordMsg();
            msgBean = msgBean.toBean(msgtia,  Record1101.class);
            processTxn(msgBean, ctx);
        } catch (Exception e) {
            logger.error("交易处理失败", e);
//            toa.getBody().setResult("99");
//            toa.getBody().setAddWord("系统错误， 交易处理失败。");
//            ctx.setMsgtoa(toa.toXml(toa));
        }
    }
    private void processTxn(TpsMsg tia,TxnContext ctx){
        MultiRecordMsg toa = new MultiRecordMsg();
        SingleRecordMsg tiaMsg = (SingleRecordMsg)tia;
        Record1101 record1101 = (Record1101)tiaMsg.getBody().getRecords().get(0);
        String cardNo = record1101.getCard_no();
        String consumeDate = record1101.getConsume_datetime();
        String amt =record1101.getOriginal_amount();
        String strToa = "";
        String strTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        try{
            List<LsConsumeinfo> consumeinfoList = queryConsumeRecord(cardNo,amt,consumeDate);
            if (consumeinfoList.size()==0) {
                //添加head
                TpsMsgHead head = new TpsMsgHead();
                head.setSrc(this.src);
                head.setDes(this.des);
                head.setDataType("9000");
                head.setMsgId("210137" + strTime);
                head.setMsgRef(tiaMsg.getHead().getMsgId());
                head.setWorkDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                toa.setHead(head);
                //添加body下的batchHead
                TpsMsgBodyBatchHead batchHead = new TpsMsgBodyBatchHead();
                batchHead.setPack_no(strTime);
                batchHead.setAll_num("1");
                batchHead.setAll_amt("0");
                batchHead.setChild_pack_count("1");
                batchHead.setChild_pack_no("1");
                batchHead.setChild_pack_num("1");
                batchHead.setChild_pack_amt("0");
                toa.getBody().setBatchHead(batchHead);
                //添加9000报文
                Record9000 record9000 = new Record9000();
                record9000.setOri_datatype_code("1101");
                record9000.setOri_send_orgcode(tia.getHead().getSrc());
                record9000.setOri_entrust_date(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                record9000.setResult("2101007");
                record9000.setAdd_word("无消费信息返回");

                toa.getBody().getRecords().add(record9000);

                TpsMsgSigns signs = new TpsMsgSigns();
                signs.setSign(this.authCode);
                signs.setStamp("");
                toa.setSigns(signs);
                String bizMsg = toa.toXml(toa);

                int bizMsgLen = bizMsg.getBytes("GBK").length;
                String authoCode = this.authCode;

                MsgCommHeader msgCommHeader = new MsgCommHeader();
                msgCommHeader.setSenderCode(this.src);
                msgCommHeader.setRecverCode(this.des);
                msgCommHeader.setDataType("9000");
                msgCommHeader.setSendTime(strTime);
                msgCommHeader.setSignFlag("0");

                msgCommHeader.setAuthoCode(authoCode);
                int authoCodeLen = authoCode.getBytes("GBK").length;
                msgCommHeader.setAuthoCodeLen("" + authoCodeLen);


                int headerLen = msgCommHeader.getMsgHeaderLength();
                int msgLen = bizMsgLen + headerLen;
                msgCommHeader.setMsgLen("" + msgLen);

                String headerStr = msgCommHeader.marshal(); //已包括授权码
                strToa = headerStr + bizMsg;
            }else{
                //添加head
                TpsMsgHead head = new TpsMsgHead();
                head.setSrc(this.src);
                head.setDes(this.des);
                head.setDataType("2101");
                head.setMsgId("210137" + strTime);
                head.setMsgRef(tiaMsg.getHead().getMsgId());
                head.setWorkDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                toa.setHead(head);

                BigDecimal totalAmt = new BigDecimal("0.00");
                int totalCount = 0;
                for(LsConsumeinfo consume : consumeinfoList){
                    Record2101 record2101 = new Record2101();
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(consume.getBusidate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    record2101.setConsume_datetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                    record2101.setSerial_no(consume.getRefBatchId());
                    record2101.setCard_no(consume.getAccount());
                    record2101.setCard_holder(consume.getCardname());
                    record2101.setOriginal_amount(consume.getBusimoney().toString());
                    record2101.setMerchant_name(consume.getBusiname());
                    record2101.setTransaction_addr("");
                    record2101.setBank_code(this.bankCode);
                    record2101.setBank_name(this.bankName);
                    record2101.setPayee_account(consume.getAccount());
                    record2101.setPayee_name(consume.getCardname());
                    record2101.setPayee_sum_code(this.payeeSumAccount);
                    record2101.setPayee_sum_name(this.payeeSumName);
                    record2101.setPayee_rg_code(this.payeeRgCode);
                    toa.getBody().getRecords().add(record2101);
                    totalCount++;
                    totalAmt = totalAmt.add(consume.getBusimoney());
                }
                //添加body下的batchHead
                TpsMsgBodyBatchHead batchHead = new TpsMsgBodyBatchHead();
                batchHead.setPack_no(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                batchHead.setAll_num("" + totalCount);
                batchHead.setAll_amt(totalAmt.toString());
                batchHead.setChild_pack_count("1");
                batchHead.setChild_pack_no("1");
                batchHead.setChild_pack_num(""+totalCount);
                batchHead.setChild_pack_amt(totalAmt.toString());
                toa.getBody().setBatchHead(batchHead);

                TpsMsgSigns signs = new TpsMsgSigns();
                signs.setSign(this.authCode);
                signs.setStamp("");
                toa.setSigns(signs);
                String bizMsg = toa.toXml(toa);

                int bizMsgLen = bizMsg.getBytes("GBK").length;
                String authoCode = this.authCode;

                MsgCommHeader msgCommHeader = new MsgCommHeader();
                msgCommHeader.setSenderCode(this.src);
                msgCommHeader.setRecverCode(this.des);
                msgCommHeader.setDataType("2101");
                msgCommHeader.setSendTime(strTime);
                msgCommHeader.setSignFlag("0");

                msgCommHeader.setAuthoCode(authoCode);
                int authoCodeLen = authoCode.getBytes("GBK").length;
                msgCommHeader.setAuthoCodeLen("" + authoCodeLen);


                int headerLen = msgCommHeader.getMsgHeaderLength();
                int msgLen = bizMsgLen + headerLen;
                msgCommHeader.setMsgLen("" + msgLen);

                String headerStr = msgCommHeader.marshal(); //已包括授权码
                strToa = headerStr + bizMsg;
        }
        }catch(UnsupportedEncodingException e){
            throw new RuntimeException("编码错误", e);
        }
        ctx.setMsgtoa(strToa);
    }


private List<LsConsumeinfo> queryConsumeRecord(String cardNo,String tradeSum,String consumeDate){
    SqlSessionFactory sqlSessionFactory = null;
    SqlSession session = null;
    List<LsConsumeinfo> consumeinfoList = null;
    try{
        sqlSessionFactory = MybatisFactory.ORACLE.getInstance();
        session = sqlSessionFactory.openSession();
        session.getConnection().setAutoCommit(false);
        LsConsumeinfoMapper mapper = session.getMapper(LsConsumeinfoMapper.class);

        LsConsumeinfoExample example = new LsConsumeinfoExample();
        if (StringUtils.isEmpty(tradeSum)){
            BigDecimal bigDecimalAmt = new BigDecimal(tradeSum);
            example.createCriteria().andAccountEqualTo(cardNo).andInacAmtEqualTo(bigDecimalAmt).andBusidateEqualTo(consumeDate);
        }else{
            example.createCriteria().andAccountEqualTo(cardNo).andBusidateEqualTo(consumeDate);
        }
        consumeinfoList = mapper.selectByExample(example);
        session.commit();
    }catch (Exception e) {
        if (session != null) {
            session.rollback();
        }
        throw new RuntimeException(e);
    } finally {
        if (session != null) {
            session.close();
        }
    }
    return consumeinfoList;
}
    protected String substr(String content, String startStr, String endStr) {
        int length = endStr.length();
        int start = content.indexOf(startStr);
        int end = content.indexOf(endStr)+length;
        return content.substring(start, end);
    }
}
