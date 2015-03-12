package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

/**
 * Created by zhanrui on 2015/3/11.
 */
@XStreamAlias("Record")
public class Record9910 extends TpsMsgBodyBatchRecord {
    private String ori_datatype = "";
    private String result = "";
    private String add_word = "";
    private String information = "";

    public String getOri_datatype() {
        return ori_datatype;
    }

    public void setOri_datatype(String ori_datatype) {
        this.ori_datatype = ori_datatype;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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
        return "\n\t\tRecord9910{" +
                "ori_datatype='" + ori_datatype + '\'' +
                ", information='" + information + '\'' +
                ", result='" + result + '\'' +
                ", add_word='" + add_word + '\'' +
                '}';
    }
}
