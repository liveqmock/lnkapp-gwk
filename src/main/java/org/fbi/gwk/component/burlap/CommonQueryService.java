package org.fbi.gwk.component.burlap;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 12-1-5
 * Time: обнГ9:49
 */
// /remoting/service/commonqueryservice
public interface CommonQueryService {
    //         m.put("VOUCHERID", "10-016001-000001");
    // List getQueryListBySql("BANK.CCB", "queryConsumeInfo", m, "2010");
    List getQueryListBySql(String applicationid, String method, Map voucherMap, String year);
}
