package org.fbi.gwk.tpsserver.hdserver;

import org.apache.commons.lang.StringUtils;

/**
 * Created by zhanrui on 2015/3/12.
 */
public class MsgCommHeader {
    private static int MSG_HEADER_LENGTH = 77;//不包括授权码

    private String msgLen = "";
    private String dataType = "";
    private String senderCode = "";
    private String recverCode = "";
    private String sendTime = "";
    private String signFlag = "";
    private String authoCodeLen = "";
    private String reserve = "";

    private String authoCode = "";

    public MsgCommHeader() {

    }


    public MsgCommHeader(String header) {
        if (header == null || header.getBytes().length < MSG_HEADER_LENGTH) {
            throw new IllegalArgumentException("报文头长度错误.");
        }

        int index = 0;

        int step = 8;
        msgLen = header.substring(index, index + step).trim();
        index += step;

        step = 6;
        dataType = header.substring(index, index + step).trim();
        index += step;

        step = 15;
        senderCode = header.substring(index, index + step).trim();
        index += step;

        step = 15;
        recverCode = header.substring(index, index + step).trim();
        index += step;

        step = 14;
        sendTime = header.substring(index, index + step).trim();
        index += step;

        step = 1;
        signFlag = header.substring(index, index + step).trim();
        index += step;

        step = 3;
        authoCodeLen = header.substring(index, index + step).trim();
        index += step;

        step = 15;
        reserve = header.substring(index, index + step).trim();
        index += step;

        step = Integer.parseInt(authoCodeLen);
        authoCode = header.substring(index, index + step).trim();
    }

    public String marshal() {
        return
                StringUtils.leftPad(msgLen, 8, "0") +
                        StringUtils.leftPad(dataType, 6, " ") +
                        StringUtils.leftPad(senderCode, 15, " ") +
                        StringUtils.leftPad(recverCode, 15, " ") +
                        StringUtils.leftPad(sendTime, 14, " ") +
                        signFlag +
                        StringUtils.leftPad(authoCodeLen, 3, "0") +
                        StringUtils.leftPad(reserve, 15, " ") +
                        authoCode;

    }

    @Override
    public String toString() {
        return "MsgCommHeader{" +
                "msgLen='" + msgLen + '\'' +
                ", dataType='" + dataType + '\'' +
                ", senderCode='" + senderCode + '\'' +
                ", recverCode='" + recverCode + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", signFlag='" + signFlag + '\'' +
                ", authoCodeLen='" + authoCodeLen + '\'' +
                ", reserve='" + reserve + '\'' +
                ", authoCode='" + authoCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MsgCommHeader that = (MsgCommHeader) o;

        if (authoCode != null ? !authoCode.equals(that.authoCode) : that.authoCode != null) return false;
        if (authoCodeLen != null ? !authoCodeLen.equals(that.authoCodeLen) : that.authoCodeLen != null) return false;
        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (msgLen != null ? !msgLen.equals(that.msgLen) : that.msgLen != null) return false;
        if (recverCode != null ? !recverCode.equals(that.recverCode) : that.recverCode != null) return false;
        if (reserve != null ? !reserve.equals(that.reserve) : that.reserve != null) return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;
        if (senderCode != null ? !senderCode.equals(that.senderCode) : that.senderCode != null) return false;
        if (signFlag != null ? !signFlag.equals(that.signFlag) : that.signFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = msgLen != null ? msgLen.hashCode() : 0;
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (senderCode != null ? senderCode.hashCode() : 0);
        result = 31 * result + (recverCode != null ? recverCode.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (signFlag != null ? signFlag.hashCode() : 0);
        result = 31 * result + (authoCodeLen != null ? authoCodeLen.hashCode() : 0);
        result = 31 * result + (reserve != null ? reserve.hashCode() : 0);
        result = 31 * result + (authoCode != null ? authoCode.hashCode() : 0);
        return result;
    }

    //===================
    public String getMsgLen() {
        return msgLen;
    }

    public void setMsgLen(String msgLen) {
        this.msgLen = msgLen;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getRecverCode() {
        return recverCode;
    }

    public void setRecverCode(String recverCode) {
        this.recverCode = recverCode;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag;
    }

    public String getAuthoCodeLen() {
        return authoCodeLen;
    }

    public void setAuthoCodeLen(String authoCodeLen) {
        this.authoCodeLen = authoCodeLen;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getAuthoCode() {
        return authoCode;
    }

    public void setAuthoCode(String authoCode) {
        this.authoCode = authoCode;
    }

    public  int getMsgHeaderLength() {
        return MSG_HEADER_LENGTH + Integer.parseInt(getAuthoCodeLen());
    }
}
