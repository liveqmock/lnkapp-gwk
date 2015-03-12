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
 4.1.3	还款明细下载报文请求（2102）
 方向：银行->财政局
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
        record.setBank_code("CCB");//发卡行编码
        record.setBill_no("001");
        record.setDownload_type("0");//下载类型 要求回执格式   0---不带明细(默认)   1—带明细
        record.setPack_no("1");//TODO 多包处理
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
