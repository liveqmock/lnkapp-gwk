package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanrui on 2015/3/11.
 */
@XStreamAlias("Record")
public class Record1102 extends TpsMsgBodyBatchRecord {
    private String bill_no = "";
    private String en_code = "";
    private String payee_account_no = "";
    private String payee_account_name = "";
    private String pay_money = "";

    @XStreamAlias("Object")
    private List<PaybackDetlRecord> detls = new ArrayList<>();


    //=====

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getEn_code() {
        return en_code;
    }

    public void setEn_code(String en_code) {
        this.en_code = en_code;
    }

    public String getPayee_account_no() {
        return payee_account_no;
    }

    public void setPayee_account_no(String payee_account_no) {
        this.payee_account_no = payee_account_no;
    }

    public String getPayee_account_name() {
        return payee_account_name;
    }

    public void setPayee_account_name(String payee_account_name) {
        this.payee_account_name = payee_account_name;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public List<PaybackDetlRecord> getDetls() {
        return detls;
    }

    public void setDetls(List<PaybackDetlRecord> detls) {
        this.detls = detls;
    }

    @Override
    public String toString() {
        return "\n\t\tRecord1102{" +
                "bill_no='" + bill_no + '\'' +
                ", en_code='" + en_code + '\'' +
                ", payee_account_no='" + payee_account_no + '\'' +
                ", payee_account_name='" + payee_account_name + '\'' +
                ", pay_money='" + pay_money + '\'' +
                ", detls=" + detls +
                '}';
    }
}
