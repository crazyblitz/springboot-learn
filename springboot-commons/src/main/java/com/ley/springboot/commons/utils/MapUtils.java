package com.ley.springboot.commons.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * map utility class<br/>
 * Java bean 和 Map相互转换
 **/
public class MapUtils {

    /**
     * java bean to map
     *
     * @see BeanInfo
     * @see PropertyDescriptor
     **/
    public static Map<String, Object> java2Map(Object javaBean) {
        Map<String, Object> map = new HashMap<>(32);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null;
                Object propertyValue = null;
                int length = propertyDescriptors.length;

                for (int i = 0; i < length; ++i) {
                    PropertyDescriptor pd = propertyDescriptors[i];
                    propertyName = pd.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = pd.getReadMethod();
                        propertyValue = readMethod.invoke(javaBean);
                        map.put(propertyName, propertyValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * map to java bean
     **/
    public static <T> T map2Java(T javaBean, Map<String, Object> map) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            Object obj = javaBean.getClass().newInstance();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null;
                Object propertyValue = null;
                int length = propertyDescriptors.length;

                for (int i = 0; i < length; ++i) {
                    PropertyDescriptor pd = propertyDescriptors[i];
                    propertyName = pd.getName();
                    if (map.containsKey(propertyName)) {
                        propertyValue = map.get(propertyName);
                        pd.getWriteMethod().invoke(obj, propertyValue);
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * map to java bean list
     **/
    public static <T> List<T> map2Java(T javaBean, List<Map<String, Object>> mapList) {
        if (mapList != null && !mapList.isEmpty()) {
            List<T> objectList = new ArrayList();
            T object = null;
            Iterator iterator = mapList.iterator();

            while (iterator.hasNext()) {
                Map<String, Object> map = (Map<String, Object>) iterator.next();
                if (map != null) {
                    object = map2Java(javaBean, map);
                    objectList.add(object);
                }
            }
            return objectList;
        } else {
            return null;
        }
    }
}
