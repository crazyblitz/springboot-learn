package com.ley.formatter.autoconfigure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/24 17:24
 * @describe json formatter
 */
public class JsonFormatter implements Formatter {

    private final ObjectMapper objectMapper;

    public JsonFormatter() {
        this(new ObjectMapper());
    }

    public JsonFormatter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String format(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // 解析失败返回非法参数异常
            throw new IllegalArgumentException(e);
        }
    }
}
