package org.fbi.gwk.component.burlap;

import com.caucho.burlap.client.BurlapProxyFactory;
import org.fbi.gwk.helper.ProjectConfigManager;

import java.net.MalformedURLException;

/**
 * Burlap Service ¹¤³§
 */
public class GwkBurlapServiceFactory {

    private static GwkBurlapServiceFactory instance = new GwkBurlapServiceFactory();
    private static BurlapProxyFactory burlapProxyFactory = new BurlapProxyFactory();

    public static GwkBurlapServiceFactory getInstance() {
        if (instance == null) {
            instance = new GwkBurlapServiceFactory();
        }
        if (burlapProxyFactory == null) {
            burlapProxyFactory = new BurlapProxyFactory();
        }
        return instance;
    }

    private GwkBurlapServiceFactory() {
    }

    public ElementService getElementService(String areaCode) throws MalformedURLException {
        String url = ProjectConfigManager.getInstance().getProperty("ccbgwk.endpoint.url.gwk." + areaCode + ".elementservice");
        ElementService elementService = (ElementService) burlapProxyFactory.create(ElementService.class, url);
        return elementService;
    }

    public BankService getBankService(String areaCode) throws MalformedURLException {
        String url = ProjectConfigManager.getInstance().getProperty("ccbgwk.endpoint.url.gwk." + areaCode + ".bankservice");
        BankService bankservice = (BankService) burlapProxyFactory.create(BankService.class, url);
        return bankservice;
    }

    public CommonQueryService getCommonQueryService(String areaCode) throws MalformedURLException {
        String url = ProjectConfigManager.getInstance().getProperty("ccbgwk.endpoint.url.gwk." + areaCode + ".commonqueryservice");
        CommonQueryService commonQueryService = (CommonQueryService) burlapProxyFactory.create(CommonQueryService.class, url);
        return commonQueryService;
    }
}
