package org.fbi.gwk.processor;


import org.fbi.gwk.enums.TxnRtnCode;

/**
 * Created by zhanrui on 2014/10/19.
 * CBS��Ӧ��Ϣ
 */
public class CbsRtnInfo {
    TxnRtnCode rtnCode;
    String rtnMsg = "";

    public TxnRtnCode getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(TxnRtnCode rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }
}
