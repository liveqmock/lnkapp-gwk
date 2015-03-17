package org.fbi.gwk.domain.cbs.T1000Response;


import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.OneToManySeperatedTextMessage;

import java.math.BigDecimal;

/**
 * zhanrui on 20150316.
 */
@OneToManySeperatedTextMessage(separator = ",")
public class CbsToa1000Item {
    @DataField(seq = 1)
    private String acctNo;
    @DataField(seq = 2)
    private String custName;
    @DataField(seq = 3)
    private BigDecimal txnAmt;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public BigDecimal getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
    }

    @Override
    public String toString() {
        return "CbsToa1000Item{" +
                "acctNo='" + acctNo + '\'' +
                ", custName='" + custName + '\'' +
                ", txnAmt=" + txnAmt +
                '}';
    }
}
