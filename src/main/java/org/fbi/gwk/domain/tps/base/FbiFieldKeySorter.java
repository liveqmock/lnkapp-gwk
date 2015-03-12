package org.fbi.gwk.domain.tps.base;

import com.thoughtworks.xstream.converters.reflection.FieldKey;
import com.thoughtworks.xstream.converters.reflection.FieldKeySorter;
import com.thoughtworks.xstream.core.util.OrderRetainingMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhanrui on 2015/3/11.
 */
public class FbiFieldKeySorter implements FieldKeySorter {
    @Override
    public Map sort(final Class clazz, final Map orderDefMap) {
        Annotation sequence = clazz.getAnnotation(XMLSequence.class);
        if (sequence != null) {
            final String[] fieldsOrder = ((XMLSequence) sequence).value();
            Map result = new OrderRetainingMap();
            Set<Map.Entry<FieldKey, Field>> fields = orderDefMap.entrySet();
            for (String fieldName : fieldsOrder) {
                if (fieldName != null) {
                    for (Map.Entry<FieldKey, Field> fieldEntry : fields) {
                        if (fieldName.equals(fieldEntry.getKey().getFieldName())) {
                            result.put(fieldEntry.getKey(), fieldEntry.getValue());
                        }
                    }
                }
            }
            return result;
        } else {
            return orderDefMap;
        }
    }
}
