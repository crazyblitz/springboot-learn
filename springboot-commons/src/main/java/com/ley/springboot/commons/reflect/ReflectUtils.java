package com.ley.springboot.commons.reflect;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import lombok.NonNull;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuenyuan
 **/
public class ReflectUtils extends ReflectionUtils {

    /**
     * 获取父类的泛型
     *
     * @param subClass sub class
     * @return return super generic type
     **/
    public static <T> Class<T> getClassFromGenericType(@NonNull Class<?> subClass) {
        Object genericSuperclass = subClass.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            //获取当前类的带有泛型的父类类型
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            //返回实际参数类型(泛型可以写多个)
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null && types.length > 0) {
                Type runtimeType = types[0];
                //com.google.common.reflect.TypeToken
                Class<T> type = (Class<T>) TypeToken.of(runtimeType).getRawType();
                return type;
            }
        }
        return null;
    }


    /**
     * 获取一个类及其所有除了Object Class的类所有属性
     **/
    public static List<Field> getAllFieldsExpectObject(Class<?> clazz) {

        List<Field> fieldList = Lists.newArrayListWithCapacity(16);

        if (clazz != Object.class) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }

        boolean endFlag = true;
        Class<?> tmp = clazz;
        while (endFlag && tmp != Object.class) {
            Class<?> superClass = tmp.getSuperclass();
            if (superClass != null) {
                fieldList.addAll(Arrays.asList(superClass.getDeclaredFields()));
                tmp = superClass;
            }
        }
        return fieldList;
    }


    /**
     * 获取一个类属性的类型
     **/
    public static Class<?> getFieldType(Field field) {
        return field.getType();
    }


    /**
     * 获取一个类及其所有除了Object Class的类方法
     **/
    public static List<Method> getAllMethodsExpectObject(Class<?> clazz) {

        List<Method> methodLists = Lists.newArrayListWithCapacity(16);

        if (clazz != Object.class) {
            methodLists.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        }

        boolean endFlag = true;
        Class<?> tmp = clazz;
        while (endFlag && tmp != Object.class) {
            Class<?> superClass = tmp.getSuperclass();
            if (superClass != null) {
                methodLists.addAll(Arrays.asList(superClass.getDeclaredMethods()));
                tmp = superClass;
            }
        }
        return methodLists;
    }

}
