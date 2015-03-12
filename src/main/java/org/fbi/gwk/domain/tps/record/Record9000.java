package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

/**
 * Created by zhanrui on 2015/3/11.
 */
@XStreamAlias("Record")
public class Record9000 extends TpsMsgBodyBatchRecord {
    private String ori_datatype = "";
    private String ori_send_orgcode = "";
    private String ori_entrust_date = "";
    private String result = "";
    private String add_word = "";

    public String getOri_datatype() {
        return ori_datatype;
    }

    public void setOri_datatype(String ori_datatype) {
        this.ori_datatype = ori_datatype;
    }

    public String getOri_send_orgcode() {
        return ori_send_orgcode;
    }

    public void setOri_send_orgcode(String ori_send_orgcode) {
        this.ori_send_orgcode = ori_send_orgcode;
    }

    public String getOri_entrust_date() {
        return ori_entrust_date;
    }

    public void setOri_entrust_date(String ori_entrust_date) {
        this.ori_entrust_date = ori_entrust_date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAdd_word() {
        return add_word;
    }

    public void setAdd_word(String add_word) {
        this.add_word = add_word;
    }

    @Override
    public String toString() {
        return "\n\t\tRecord9000{" +
                "ori_datatype='" + ori_datatype + '\'' +
                ", ori_send_orgcode='" + ori_send_orgcode + '\'' +
                ", ori_entrust_date='" + ori_entrust_date + '\'' +
                ", result='" + result + '\'' +
                ", add_word='" + add_word + '\'' +
                '}';
    }
}
