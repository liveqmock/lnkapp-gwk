package org.fbi.gwk.domain.cbs.T1000Request;

import org.fbi.linking.codec.dataformat.annotation.DataField;
import org.fbi.linking.codec.dataformat.annotation.SeperatedTextMessage;

@SeperatedTextMessage(separator = "\\|", mainClass = true)
public class CbsTia1000 {
    @DataField(seq = 1)
    private String areaCode;
    @DataField(seq = 2)
    private String vchNo;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getVchNo() {
        return vchNo;
    }

    public void setVchNo(String vchNo) {
        this.vchNo = vchNo;
    }

    @Override
    public String toString() {
        return "CbsTia1000{" +
                "areaCode='" + areaCode + '\'' +
                ", vchNo='" + vchNo + '\'' +
                '}';
    }
}