package org.fbi.gwk.domain.tps.base;

import java.io.Serializable;
/*
    ����ͷ����������ͷ��������ʶ�������ε������Ϣ�������׼�¼�ϴ󣨽������1000�ʣ����������Ҫ��ȡ�ְ������ְ�����Ϣ������ͷ�ж��塣
    XML�ڵ�TAG ��Root/Body/BatchHead

    pack_no	����ˮ��	NString	12	���ݰ���ʶ�ţ���Ϊ���ϲ���Ψһ��ʶ,�ɷ��𷽶��塣	M
    all_num	�ܱ���	Integer		δ�ְ��Ľ����ܱ���	M
    all_amt	�ܽ��	Currency		δ�ְ��Ľ����ܽ��	O
    child_pack_count	�Ӱ�����	Integer		�������ʱ�İ�����δ���ʱ�Ӱ�����Ϊ1	M
    child_pack_no	�������	Integer		�������ʱΪ��ǰ������ţ�δ���ʱ�������Ϊ1	M
    child_pack_num	��������	Integer		��ǰ��������ϸ�������鲻����1000	M
    child_pack_amt	�������	Currency		��ǰ���Ľ����ܽ��	O
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
