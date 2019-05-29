package com.ley.formatter.autoconfigure;

import java.util.Objects;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/24 17:23
 * @describe default formatter
 */
public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return Objects.toString(object);
    }
}
