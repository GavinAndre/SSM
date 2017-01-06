package com.java.gavinandre.controller.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by gavinandre on 17-1-6.
 */
public class StringTrimConverter implements Converter<String, String> {
    @Override
    public String convert(String source) {
        try {
            //去掉字符串两边的空格,如果去除后为空则返回null
            if (source != null) {
                source = source.trim();
                if (source.equals("")) {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return source;
    }
}
