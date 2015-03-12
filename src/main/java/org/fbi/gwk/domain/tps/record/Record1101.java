package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

/**
 * Created by zhanrui on 2015/3/11.
 */
@XStreamAlias("Record")
public class Record1101 extends TpsMsgBodyBatchRecord {
    private String card_no = "";
    private String serial_no = "";
    private String consume_datetime = "";
    private String consume_datetimee = "";
    private String original_amount = "";
    private String download_type = "";
    private String en_code = "";
    private String reg_code = "";
    private String pack_no = "";
    private String child_pack_no = "";

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getConsume_datetime() {
        return consume_datetime;
    }

    public void setConsume_datetime(String consume_datetime) {
        this.consume_datetime = consume_datetime;
    }

    public String getConsume_datetimee() {
        return consume_datetimee;
    }

    public void setConsume_datetimee(String consume_datetimee) {
        this.consume_datetimee = consume_datetimee;
    }

    public String getOriginal_amount() {
        return original_amount;
    }

    public void setOriginal_amount(String original_amount) {
        this.original_amount = original_amount;
    }

    public String getDownload_type() {
        return download_type;
    }

    public void setDownload_type(String download_type) {
        this.download_type = download_type;
    }

    public String getEn_code() {
        return en_code;
    }

    public void setEn_code(String en_code) {
        this.en_code = en_code;
    }

    public String getReg_code() {
        return reg_code;
    }

    public void setReg_code(String reg_code) {
        this.reg_code = reg_code;
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

    @Override
    public String toString() {
        return "\n\t\tRecord1101{" +
                "card_no='" + card_no + '\'' +
                ", serial_no='" + serial_no + '\'' +
                ", consume_datetime='" + consume_datetime + '\'' +
                ", consume_datetimee='" + consume_datetimee + '\'' +
                ", original_amount='" + original_amount + '\'' +
                ", download_type='" + download_type + '\'' +
                ", en_code='" + en_code + '\'' +
                ", reg_code='" + reg_code + '\'' +
                ", pack_no='" + pack_no + '\'' +
                ", child_pack_no='" + child_pack_no + '\'' +
                '}';
    }
}
