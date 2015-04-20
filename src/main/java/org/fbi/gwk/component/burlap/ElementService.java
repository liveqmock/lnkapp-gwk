package org.fbi.gwk.component.burlap;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 12-1-5
 * Time: обнГ9:26
 */
/*
 serverUrl : /remoting/service/elementservice
 */
public interface ElementService {
    List queryAllElementCode(String applicationid, String elementCode, int year);
    Map queryElementVersion(String applicationid, int year, List elements);
    List syncElementCode(String applicationid, int year, Map element);
}
