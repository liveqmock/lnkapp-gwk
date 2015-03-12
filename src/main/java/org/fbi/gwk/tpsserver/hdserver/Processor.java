package org.fbi.gwk.tpsserver.hdserver;


/**
 * Created by zhanrui on 2014/11/6.
 */
public interface Processor {
    public void service(TxnContext ctx);
}
