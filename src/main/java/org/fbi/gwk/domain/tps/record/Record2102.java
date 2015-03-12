package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

/**
 * Created by zhanrui on 2015/3/11.
 */
@XStreamAlias("Record")
public class Record2102 extends TpsMsgBodyBatchRecord {
    private String bank_code = "";
    private String bill_no = "";
    private String download_type = "";
    private String pack_no = "";
    private String child_pack_no = "";
    private String set_year = "";

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getDownload_type() {
        return download_type;
    }

    public void setDownload_type(String download_type) {
        this.download_type = download_type;
    }

    public String getPack_no() {
        return pack_no;
    }

    public void setPack_no(String pack_no) {
        this.pack_no = pack_no;
    }

    public String getChild_pack_no() {
        return child_pack_no;
    }

    public void setChild_pack_no(String child_pack_no) {
        this.child_pack_no = child_pack_no;
    }

    public String getSet_year() {
        return set_year;
    }

    public void setSet_year(String set_year) {
        this.set_year = set_year;
    }

    @Override
    public String toString() {
        return "Record2102{" +
                "bank_code='" + bank_code + '\'' +
                ", bill_no='" + bill_no + '\'' +
                ", download_type='" + download_type + '\'' +
                ", pack_no='" + pack_no + '\'' +
                ", child_pack_no='" + child_pack_no + '\'' +
                ", set_year='" + set_year + '\'' +
                '}';
    }
}
