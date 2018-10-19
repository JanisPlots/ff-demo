package com.ff.demo.util;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper extends DozerBeanMapper {

    public <T, U> ArrayList<U> map(final List<T> source, final Class<U> destType) {
        final ArrayList<U> dest = new ArrayList<>();

        if (source != null) {
            for (T element : source) {
                if (element == null) {
                    continue;
                }

                U obj = this.map(element, destType);
                if (obj != null) {
                    dest.add(obj);
                }
            }
        }

        return dest;
    }
}
