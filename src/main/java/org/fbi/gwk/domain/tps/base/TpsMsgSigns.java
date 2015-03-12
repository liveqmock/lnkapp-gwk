package org.fbi.gwk.domain.tps.base;

import java.io.Serializable;import java.lang.Override;import java.lang.String;


public class TpsMsgSigns implements Serializable {
    private String Sign = "";
    private String Stamp = "";

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public String getStamp() {
        return Stamp;
    }

    public void setStamp(String stamp) {
        Stamp = stamp;
    }

    @Override
    public String toString() {
        return "TpsMsgSigns{" +
                "Sign='" + Sign + '\'' +
                ", Stamp='" + Stamp + '\'' +
                '}';
    }
}
