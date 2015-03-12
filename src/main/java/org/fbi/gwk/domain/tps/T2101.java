package org.fbi.gwk.domain.tps;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.*;
import org.fbi.gwk.domain.tps.record.Record2101;
import org.fbi.gwk.domain.tps.record.Record9000;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 4.1.2	���Ѽ�¼���ػ�ִ��2101��
 ��������->������
 */
@XMLSequence({
        "head",
        "body",
        "signs",
})
@XStreamAlias("Root")
public class T2101 extends TpsMsg {
    @XStreamAlias("Body")
    private Body body=new Body();

    public static class Body extends TpsMsgBody{
        @XStreamAlias("BatchHead")
        private TpsMsgBodyBatchHead batchHead = new TpsMsgBodyBatchHead();

        @XStreamAlias("Object")
        private List<TpsMsgBodyBatchRecord> records = new ArrayList<>();

        public List<TpsMsgBodyBatchRecord> getRecords() {
            return records;
        }

        public void setRecords(List<TpsMsgBodyBatchRecord> records) {
            this.records = records;
        }

        public TpsMsgBodyBatchHead getBatchHead() {
            return batchHead;
        }

        public void setBatchHead(TpsMsgBodyBatchHead batchHead) {
            this.batchHead = batchHead;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "batchHead=" + batchHead +
                    ", records=" + records +
                    '}';
        }
    }


    @Override
    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }



    //=======================================
    public static void main(String ... argv){
        T2101 tia = new T2101();
        TpsMsgBodyBatchHead batchHead = tia.getBody().getBatchHead();
        //TODO
        batchHead.setPack_no(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        batchHead.setAll_num("1");
        batchHead.setAll_amt("0.00");
        batchHead.setChild_pack_count("1");
        batchHead.setChild_pack_no("1");
        batchHead.setChild_pack_num("1");
        batchHead.setChild_pack_amt("0.00");


        //TODO �ο�1101��������
        Record9000 record9000 = new Record9000();
        record9000.setOri_datatype("1101");
        record9000.setOri_send_orgcode("CZJ");
        record9000.setOri_entrust_date("000000");//TODO ԭί�����ڵ�������workdate
        record9000.setResult("111"); //TODO ���󷵻�������������ķ��ر���+��ˮ��
        record9000.setAdd_word("���޴˿�");
        //tia.getBody().getRecords().add(record9000);

        //��������
        Record2101 record = new Record2101();
        record.setConsume_datetime("����ʱ��");
        record.setSerial_no("001");
        tia.getBody().getRecords().add(record);
        record = new Record2101();
        record.setConsume_datetime("����ʱ��");
        record.setSerial_no("002");
        tia.getBody().getRecords().add(record);


        //TODO Head ����
        //tia.getHead().setDataType("9000");
        tia.getHead().setDataType("2101");

        String xml = tia.toXml(tia);
        System.out.println(xml);

        //----
        TpsMsg msgBean = new T2101();
        //msgBean = msgBean.toBean(xml, record9000.getClass());
        msgBean = msgBean.toBean(xml, record.getClass());
        System.out.println(msgBean);

    }
}
