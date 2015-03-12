package org.fbi.gwk.domain.tps.record;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.fbi.gwk.domain.tps.base.TpsMsgBodyBatchRecord;

/**
 * Created by zhanrui on 2015/3/11.
 */
@XStreamAlias("Record")
public class Record9905 extends TpsMsgBodyBatchRecord {
    private String user_code = "";
    private String password = "";
    private String new_password = "";

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    @Override
    public String toString() {
        return "\n\t\tRecord9905{" +
                "user_code='" + user_code + '\'' +
                ", password='" + password + '\'' +
                ", new_password='" + new_password + '\'' +
                '}';
    }
}
