package org.fbi.gwk.domain.tps;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsg;
import org.fbi.gwk.domain.tps.base.TpsMsgBody;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;
import org.fbi.gwk.domain.tps.base.XMLSequence;
import org.fbi.gwk.domain.tps.record.Record1101;
import org.fbi.gwk.domain.tps.record.Record2102;

import java.util.ArrayList;
import java.util.List;

/**
 4.1.3	������ϸ���ر�������2102��
 ��������->������
 */
@XMLSequence({
        "head",
        "body",
        "signs",
})
@XStreamAlias("Root")
public class T2102 extends TpsMsg {
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
        T2102 tia = new T2102();

        Record2102 record = new Record2102();
        record.setBank_code("CCB");//�����б���
        record.setBill_no("001");
        record.setDownload_type("0");//�������� Ҫ���ִ��ʽ   0---������ϸ(Ĭ��)   1������ϸ
        record.setPack_no("1");//TODO �������
        record.setChild_pack_no("001");
        record.setSet_year("2015");

        tia.body.getRecords().add(record);

        String xml = tia.toXml(tia);
        System.out.println(xml);

        //----
        TpsMsg msgBean = new T2102();
        msgBean = msgBean.toBean(xml, record.getClass());
        System.out.println(msgBean);

    }
}
