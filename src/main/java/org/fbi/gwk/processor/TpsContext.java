package org.fbi.gwk.processor;


import org.fbi.gwk.tpsserver.hdserver.MsgCommHeader;

/**
 * Created by zhanrui on 2015/3/17.
 */
public class TpsContext {
    private MsgCommHeader tpsTiaHeader;
    private String tpsTiaXml;
    private String tpsTiaTxnCode;
    private MsgCommHeader tpsToaHeader;
    private String tpsToaXml;
    private String tpsToaTxnCode;

    public MsgCommHeader getTpsTiaHeader() {
        return tpsTiaHeader;
    }

    public void setTpsTiaHeader(MsgCommHeader tpsTiaHeader) {
        this.tpsTiaHeader = tpsTiaHeader;
    }

    public String getTpsTiaXml() {
        return tpsTiaXml;
    }

    public void setTpsTiaXml(String tpsTiaXml) {
        this.tpsTiaXml = tpsTiaXml;
    }

    public MsgCommHeader getTpsToaHeader() {
        return tpsToaHeader;
    }

    public void setTpsToaHeader(MsgCommHeader tpsToaHeader) {
        this.tpsToaHeader = tpsToaHeader;
    }

    public String getTpsToaXml() {
        return tpsToaXml;
    }

    public void setTpsToaXml(String tpsToaXml) {
        this.tpsToaXml = tpsToaXml;
    }

    public String getTpsTiaTxnCode() {
        return tpsTiaTxnCode;
    }

    public void setTpsTiaTxnCode(String tpsTiaTxnCode) {
        this.tpsTiaTxnCode = tpsTiaTxnCode;
    }

    public String getTpsToaTxnCode() {
        return tpsToaTxnCode;
    }

    public void setTpsToaTxnCode(String tpsToaTxnCode) {
        this.tpsToaTxnCode = tpsToaTxnCode;
    }
}
