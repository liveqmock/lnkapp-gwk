package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

/**
 * Created by zhanrui on 2015/3/11.
 */
@XStreamAlias("Record")
public class Record9906 extends TpsMsgBodyBatchRecord {
    private String login_result = "";
    private String accredit_code = "";
    private String add_word = "";

    public String getLogin_result() {
        return login_result;
    }

    public void setLogin_result(String login_result) {
        this.login_result = login_result;
    }

    public String getAccredit_code() {
        return accredit_code;
    }

    public void setAccredit_code(String accredit_code) {
        this.accredit_code = accredit_code;
    }

    public String getAdd_word() {
        return add_word;
    }

    public void setAdd_word(String add_word) {
        this.add_word = add_word;
    }

    @Override
    public String toString() {
        return "\n\t\tRecord9906{" +
                "login_result='" + login_result + '\'' +
                ", accredit_code='" + accredit_code + '\'' +
                ", add_word='" + add_word + '\'' +
                '}';
    }
}
