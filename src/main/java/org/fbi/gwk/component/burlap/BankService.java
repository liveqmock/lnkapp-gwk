package org.fbi.gwk.component.burlap;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 12-1-5
 * Time: 下午9:38
 */
// /remoting/service/bankservice
public interface BankService {
    List writeOfficeCard(String applicationid, String bankcode, String year, String fanCode, List cardList);
    //新的接口发送卡信息，增加行政区划编码（admdiv） 2012-10-29
    List writeOfficeCard(String applicationid, String bankcode, String year, String admdiv, String fanCode, List cardList);
    List writeConsumeInfo(String applicationid, String bankcode, String year, String fanCode, List cardList);
    //新的接口发送消费信息，增加行政区划编码（admdiv） 2012-10-29
    List writeConsumeInfo(String applicationid, String bankcode, String year, String admdiv, String fanCode, List cardList);
    List getOfficeCardStatus(String applicationid, String bankcode, String year, String fanCode, String guid);
    //新的接口，根据支付令查询消费信息， 2012-10-29
    List queryVoucherByBillCode(String applicationid, String bankcode, String year, String admdiv, String fanCode,
                                String billtype, String billcode);
}
