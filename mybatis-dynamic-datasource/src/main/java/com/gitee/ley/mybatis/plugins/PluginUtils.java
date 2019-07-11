package com.gitee.ley.mybatis.plugins;

import lombok.NonNull;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.StringUtils;

import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * <p>
 * 插件工具类
 * </p>
 *
 * @author liuenyuan
 * @see org.apache.ibatis.plugin.Plugin
 */
public abstract class PluginUtils {

    /**
     * delegate boundSql sql
     **/
    public static final String DELEGATE_BOUNDSQL_SQL = "delegate.boundSql.sql";


    private PluginUtils() {
    }


    /**
     * <p>
     * 获得真正的处理对象,可能多层代理.
     * </p>
     */
    public static Object realTarget(@NonNull Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return target;
    }


    /**
     * <p>
     * 根据 key 获取 Properties 的值
     * </p>
     */
    public static String getProperty(Properties properties, String key) {
        String value = properties.getProperty(key);
        return StringUtils.isEmpty(value) ? null : value;
    }
}
