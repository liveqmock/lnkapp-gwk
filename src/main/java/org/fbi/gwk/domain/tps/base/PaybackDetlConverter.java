package org.fbi.gwk.domain.tps.base;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.fbi.gwk.domain.tps.record.PaybackDetlRecord;
import org.fbi.gwk.domain.tps.record.Record1102;

/**
 * Created by zhanrui on 2015/3/12.
 */
public class PaybackDetlConverter implements Converter {
    private final Converter defaultConverter;
    private final ReflectionProvider reflectionProvider;

    public PaybackDetlConverter(Converter defaultConverter, ReflectionProvider reflectionProvider) {
        this.defaultConverter = defaultConverter;
        this.reflectionProvider = reflectionProvider;
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        defaultConverter.marshal(source, writer, context);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        reader.moveDown();
        String nodeName = reader.getNodeName();
        reader.moveUp();

        Class<?> resultType = Record1102.class;
        if ("card_no".equalsIgnoreCase(nodeName)) {
            resultType = PaybackDetlRecord.class;
        }
        Object result = reflectionProvider.newInstance(resultType);
        return context.convertAnother(result, resultType, defaultConverter);
    }

    @Override
    public boolean canConvert(Class type) {
        if (PaybackDetlConverter.class == type) {
            System.out.println(type);
        }
        if (TpsMsgBodyBatchRecord.class == type) {
            System.out.println(type);
        }
        if (TpsMsgBodyBatchRecord.class.isAssignableFrom(type)) {
            System.out.println(type);
            return true;
        }

        return false;
    }
}
