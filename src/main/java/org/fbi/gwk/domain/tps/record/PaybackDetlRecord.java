package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

/**
 * Created by zhanrui on 2015/3/11.
 * 每张卡的明细还款记录
 */
@XStreamAlias("Record")
public class PaybackDetlRecord extends TpsMsgBodyBatchRecord {
    private String card_no = "";
    private String consume_datetime = "";
    private String serial_no = "";
    private String auditing_amount = "";
    private String payee_account = "";

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getConsume_datetime() {
        return consume_datetime;
    }

    public void setConsume_datetime(String consume_datetime) {
        this.consume_datetime = consume_datetime;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getAuditing_amount() {
        return auditing_amount;
    }

    public void setAuditing_amount(String auditing_amount) {
        this.auditing_amount = auditing_amount;
    }

    public String getPayee_account() {
        return payee_account;
    }

    public void setPayee_account(String payee_account) {
        this.payee_account = payee_account;
    }

    @Override
    public String toString() {
        return "\n\t\t\tDetailEntry{" +
                "card_no='" + card_no + '\'' +
                ", consume_datetime='" + consume_datetime + '\'' +
                ", serial_no='" + serial_no + '\'' +
                ", auditing_amount='" + auditing_amount + '\'' +
                ", payee_account='" + payee_account + '\'' +
                '}';
    }
}
