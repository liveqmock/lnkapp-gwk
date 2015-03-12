package org.fbi.gwk.domain.tps.base;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.reflection.FieldDictionary;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Serializable;

public class TpsMsg implements Serializable {
    @XStreamAlias("Head")
    private TpsMsgHead head = new TpsMsgHead();
    @XStreamAlias("Signs")
    private TpsMsgSigns signs = new TpsMsgSigns();

    public  TpsMsg toBean(String xml, Class recordType) {
        XStream xs = new XStream(new DomDriver());
        xs.processAnnotations(this.getClass());
        xs.processAnnotations(recordType);
        xs.alias("Record", TpsMsgBodyBatchRecord.class, recordType);
        return (TpsMsg) xs.fromXML(xml);
    }

    public  String toXml(TpsMsg bean) {
        XStream xs = new XStream(new PureJavaReflectionProvider(new FieldDictionary(new FbiFieldKeySorter())), new XppDriver(new NoNameCoder()));
        xs.autodetectAnnotations(true);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>\n"+xs.toXML(bean);
    }


    //=======
    public TpsMsgHead getHead() {
        return head;
    }

    public void setHead(TpsMsgHead head) {
        this.head = head;
    }

    public TpsMsgSigns getSigns() {
        return signs;
    }

    public void setSigns(TpsMsgSigns signs) {
        this.signs = signs;
    }

    public TpsMsgBody getBody() {
        return null;
    }


    @Override
    public String toString() {
        return "TpsMsg{\n" +
                " head=" + head + "\n" +
                " body=" + getBody() + "\n" +
                " signs=" + signs + "\n" +
                '}';
    }
}
