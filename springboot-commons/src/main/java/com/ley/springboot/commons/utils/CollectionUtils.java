package com.ley.springboot.commons.utils;


import java.util.Collection;
import java.util.Map;

/**
 * collection utility class
 *
 * @author liuenyuan
 **/
public final class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && collection.size() > 0;
    }

    public static boolean isEmpty(Collection collection) {
        return !isNotEmpty(collection);
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && map.size() > 0;
    }

    public static boolean isEmpty(Map map) {
        return !isNotEmpty(map);
    }

    public static <T> boolean isNotEmpty(T[] t) {
        return t != null && t.length > 0;
    }

    public static <T> boolean isEmpty(T[] t) {
        return !isNotEmpty(t);
    }
}
