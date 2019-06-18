package com.ley.springboot.commons.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 类描述: class utilities
 *
 * @author liuenyuan
 * @date 2019/6/12 18:44
 * @describe
 */
public class ClassUtils {

    private static final Log logger = LogFactory.getLog(ClassUtils.class.getClass());

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
     * <p>
     * 注意泛型必须定义在父类处. 这是唯一可以通过反射从泛型获得Class实例的地方.
     * <p>
     * 如无法找到, 返回Object.class.
     * <p>
     * 如public UserDao extends HibernateDao<User,Long>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic declaration, start from 0.
     * @return the index generic declaration, or Object.class if cannot be determined
     */
    public static Class resolveGenericType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();
        boolean isWarnedEnabled = logger.isWarnEnabled();

        if (!(genType instanceof ParameterizedType)) {
            if (isWarnedEnabled) {
                logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            }
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if ((index >= params.length) || (index < 0)) {
            if (isWarnedEnabled) {
                logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                        + params.length);
            }
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            if (isWarnedEnabled) {
                logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            }
            return Object.class;
        }

        return (Class) params[index];
    }
}
