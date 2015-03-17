package org.fbi.gwk.domain.cbs.T1000Response;

import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.OneToMany;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;
import org.fbi.linking.codec.dataformat.format.BigDecimalFormat;

import java.math.BigDecimal;
import java.util.List;

/**
 * zhanrui on 2015-03-16.
 */
@SeperatedTextMessage(separator = "\\|", mainClass = true)
public class CbsToa1000 {
    @DataField(seq = 1)
    private BigDecimal totalAmt;

    @DataField(seq = 2)
    private String itemNum;

    @DataField(seq = 3)
    @OneToMany(mappedTo = "org.fbi.gwk.domain.cbs.T1000Response.CbsToa1000Item", totalNumberField = "itemNum")
    private java.util.List<CbsToa1000Item> items;

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public List<CbsToa1000Item> getItems() {
        return items;
    }

    public void setItems(List<CbsToa1000Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CbsToa1000{" +
                "totalAmt=" + totalAmt +
                ", itemNum='" + itemNum + '\'' +
                ", items=" + items +
                '}';
    }
}
