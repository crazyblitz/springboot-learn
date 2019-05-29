package com.ley.formatter.autoconfigure;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/24 17:22
 * @describe data formatter
 * @see 1.0.0
 */
public interface Formatter {

    /**
     * 数据格式化操作
     *
     * @param object 待格式化对象
     * @return 格式化后的对象
     */
    String format(Object object);

}
