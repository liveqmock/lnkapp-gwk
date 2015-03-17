package org.fbi.gwk.domain.tps;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.fbi.gwk.domain.tps.base.*;
import org.fbi.gwk.domain.tps.record.PaybackDetlRecord;
import org.fbi.gwk.domain.tps.record.Record1102;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  含多笔明细数据报文格式
 */
@XMLSequence({
        "head",
        "body",
        "signs",
})
@XStreamAlias("Root")
public class MultiRecordMsg extends TpsMsg {
    @XStreamAlias("Body")
    private Body body = new Body();

    public static class Body extends TpsMsgBody {
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
    public static void main(String... argv) {
        MultiRecordMsg tia = new MultiRecordMsg();


        TpsMsgBodyBatchHead batchHead = tia.getBody().getBatchHead();
        //TODO
        batchHead.setPack_no(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        batchHead.setAll_num("1");
        batchHead.setAll_amt("0.00");
        batchHead.setChild_pack_count("1");
        batchHead.setChild_pack_no("1");
        batchHead.setChild_pack_num("1");
        batchHead.setChild_pack_amt("0.00");


        //正常报文
        Record1102 record = new Record1102();
        record.setBill_no("billno");

//        Record1102.DetailEntry detailEntry = new Record1102.DetailEntry();
        PaybackDetlRecord detailEntry = new PaybackDetlRecord();
        detailEntry.setSerial_no("01");
        record.getDetls().add(detailEntry);
        detailEntry = new PaybackDetlRecord();
        detailEntry.setSerial_no("02");
        record.getDetls().add(detailEntry);
        tia.body.getRecords().add(record);


        String xml = tia.toXml(tia);
        System.out.println(xml);

        //----
        TpsMsg msgBean = new MultiRecordMsg();

        //msgBean = msgBean.toBean(xml, record.getClass());
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(MultiRecordMsg.class);
        xs.processAnnotations(Record1102.class);
        xs.processAnnotations(PaybackDetlRecord.class);
        //xs.alias("Record", TpsMsgBodyBatchRecord.class, record.getClass());

        //xs.registerConverter(new PaybackDetlConverter());
        xs.registerConverter(new PaybackDetlConverter(
                xs.getConverterLookup().lookupConverterForType(TpsMsgBodyBatchRecord.class),
                xs.getReflectionProvider()));

        msgBean = (TpsMsg) xs.fromXML(xml);


        System.out.println(msgBean);
    }
}
