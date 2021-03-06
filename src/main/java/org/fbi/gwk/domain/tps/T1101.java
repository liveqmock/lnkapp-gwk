package org.fbi.gwk.domain.tps;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsg;
import org.fbi.gwk.domain.tps.base.TpsMsgBody;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;
import org.fbi.gwk.domain.tps.base.XMLSequence;
import org.fbi.gwk.domain.tps.record.Record1101;

import java.util.ArrayList;
import java.util.List;

/**
 4.1.1	消费记录下载请求
 方向：财政局->银行
 */
@XMLSequence({
        "head",
        "body",
        "signs",
})
@XStreamAlias("Root")
public class T1101 extends TpsMsg {
    @XStreamAlias("Body")
    private Body body=new Body();

    public static class Body extends TpsMsgBody{
        @XStreamAlias("Object")
        private List<TpsMsgBodyBatchRecord> records = new ArrayList<>();

        public List<TpsMsgBodyBatchRecord> getRecords() {
            return records;
        }

        public void setRecords(List<TpsMsgBodyBatchRecord> records) {
            this.records = records;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "records=" + records +
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
        T1101 tia = new T1101();

        Record1101 record = new Record1101();
        record.setCard_no("123");
        tia.body.getRecords().add(record);
        record = new Record1101();
        record.setCard_no("123123");
        tia.body.getRecords().add(record);

        String xml = tia.toXml(tia);
        System.out.println(xml);

        //----
        TpsMsg msgBean = new T1101();
        msgBean = msgBean.toBean(xml, record.getClass());
        System.out.println(msgBean);

    }
}
