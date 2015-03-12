package org.fbi.gwk.domain.tps.base;

import java.io.Serializable;
/*
    批量头在批量报文头中用来标识报文批次的相关信息，当交易记录较大（建议大于1000笔）的情况下需要采取分包处理，分包的信息在批量头中定义。
    XML节点TAG ：Root/Body/BatchHead

    pack_no	包流水号	NString	12	数据包标识号，作为包合并的唯一标识,由发起方定义。	M
    all_num	总笔数	Integer		未分包的交易总笔数	M
    all_amt	总金额	Currency		未分包的交易总金额	O
    child_pack_count	子包总数	Integer		拆包处理时的包数，未拆包时子包总数为1	M
    child_pack_no	本包序号	Integer		拆包处理时为当前包的序号，未拆包时本包序号为1	M
    child_pack_num	本包笔数	Integer		当前包的总明细数，建议不大于1000	M
    child_pack_amt	本包金额	Currency		当前包的交易总金额	O
 */
public class TpsMsgBodyBatchHead implements Serializable {
    private String pack_no = "";
    private String all_num = "";
    private String all_amt = "";
    private String child_pack_count = "";
    private String child_pack_no = "";
    private String child_pack_num = "";
    private String child_pack_amt = "";


    public String getPack_no() {
        return pack_no;
    }

    public void setPack_no(String pack_no) {
        this.pack_no = pack_no;
    }

    public String getAll_num() {
        return all_num;
    }

    public void setAll_num(String all_num) {
        this.all_num = all_num;
    }

    public String getAll_amt() {
        return all_amt;
    }

    public void setAll_amt(String all_amt) {
        this.all_amt = all_amt;
    }

    public String getChild_pack_count() {
        return child_pack_count;
    }

    public void setChild_pack_count(String child_pack_count) {
        this.child_pack_count = child_pack_count;
    }

    public String getChild_pack_no() {
        return child_pack_no;
    }

    public void setChild_pack_no(String child_pack_no) {
        this.child_pack_no = child_pack_no;
    }

    public String getChild_pack_num() {
        return child_pack_num;
    }

    public void setChild_pack_num(String child_pack_num) {
        this.child_pack_num = child_pack_num;
    }

    public String getChild_pack_amt() {
        return child_pack_amt;
    }

    public void setChild_pack_amt(String child_pack_amt) {
        this.child_pack_amt = child_pack_amt;
    }

    @Override
    public String toString() {
        return "\n\t\tTpsMsgBodyBatchHead{" +
                "pack_no='" + pack_no + '\'' +
                ", all_num='" + all_num + '\'' +
                ", all_amt='" + all_amt + '\'' +
                ", child_pack_count='" + child_pack_count + '\'' +
                ", child_pack_no='" + child_pack_no + '\'' +
                ", child_pack_num='" + child_pack_num + '\'' +
                ", child_pack_amt='" + child_pack_amt + '\'' +
                '}';
    }
}
