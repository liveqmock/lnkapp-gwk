package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

/**
 * Created by zhanrui on 2015/3/11.
 */
@XStreamAlias("Record")
public class Record2101 extends TpsMsgBodyBatchRecord {
    private String consume_datetime = "";
    private String serial_no = "";
    private String card_no = "";
    private String card_holder = "";
    private String original_money = "";
    private String merchant_name = "";
    private String transaction_addr = "";
    private String bank_code = "";
    private String bank_name = "";
    private String payee_account = "";
    private String payee_name = "";
    private String payee_sum_code = "";
    private String payee_sum_name = "";
    private String sum_account_bank = "";
    private String payee_rg_code = "";

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

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public String getOriginal_money() {
        return original_money;
    }

    public void setOriginal_money(String original_money) {
        this.original_money = original_money;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getTransaction_addr() {
        return transaction_addr;
    }

    public void setTransaction_addr(String transaction_addr) {
        this.transaction_addr = transaction_addr;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getPayee_account() {
        return payee_account;
    }

    public void setPayee_account(String payee_account) {
        this.payee_account = payee_account;
    }

    public String getPayee_name() {
        return payee_name;
    }

    public void setPayee_name(String payee_name) {
        this.payee_name = payee_name;
    }

    public String getPayee_sum_code() {
        return payee_sum_code;
    }

    public void setPayee_sum_code(String payee_sum_code) {
        this.payee_sum_code = payee_sum_code;
    }

    public String getPayee_sum_name() {
        return payee_sum_name;
    }

    public void setPayee_sum_name(String payee_sum_name) {
        this.payee_sum_name = payee_sum_name;
    }

    public String getSum_account_bank() {
        return sum_account_bank;
    }

    public void setSum_account_bank(String sum_account_bank) {
        this.sum_account_bank = sum_account_bank;
    }

    public String getPayee_rg_code() {
        return payee_rg_code;
    }

    public void setPayee_rg_code(String payee_rg_code) {
        this.payee_rg_code = payee_rg_code;
    }

    @Override
    public String toString() {
        return "Record2101{" +
                "consume_datetime='" + consume_datetime + '\'' +
                ", serial_no='" + serial_no + '\'' +
                ", card_no='" + card_no + '\'' +
                ", card_holder='" + card_holder + '\'' +
                ", original_money='" + original_money + '\'' +
                ", merchant_name='" + merchant_name + '\'' +
                ", transaction_addr='" + transaction_addr + '\'' +
                ", bank_code='" + bank_code + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", payee_account='" + payee_account + '\'' +
                ", payee_name='" + payee_name + '\'' +
                ", payee_sum_code='" + payee_sum_code + '\'' +
                ", payee_sum_name='" + payee_sum_name + '\'' +
                ", sum_account_bank='" + sum_account_bank + '\'' +
                ", payee_rg_code='" + payee_rg_code + '\'' +
                '}';
    }
}
