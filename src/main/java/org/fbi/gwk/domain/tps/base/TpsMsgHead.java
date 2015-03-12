package org.fbi.gwk.domain.tps.base;

import java.io.Serializable;

public class TpsMsgHead implements Serializable {
    private String src = "";
    private String des = "";
    private String dataType = "";
    private String msgId = "";
    private String msgRef = "";
    private String workDate = "";

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgRef() {
        return msgRef;
    }

    public void setMsgRef(String msgRef) {
        this.msgRef = msgRef;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    @Override
    public String toString() {
        return "TpsMsgHead{" +
                "src='" + src + '\'' +
                ", des='" + des + '\'' +
                ", dataType='" + dataType + '\'' +
                ", msgId='" + msgId + '\'' +
                ", msgRef='" + msgRef + '\'' +
                ", workDate='" + workDate + '\'' +
                '}';
    }
}
