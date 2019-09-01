package com.ley.first.spring.boot.starter.utils;

import com.ley.first.spring.boot.starter.spring.factories.Goods;
import com.ley.first.spring.boot.starter.spring.factories.GoodsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.instantiateClass;

/**
 * 类描述: {@link org.springframework.core.io.support.SpringFactoriesLoader}
 *
 * @author liuenyuan
 * @date 2019/5/29 23:02
 * @describe
 */
public class SpringFactoriesUtils {

    /**
     * get spring factories instance
     *
     * @param type class type
     **/
    public static <T> Collection<T> getSpringFactoriesInstances(Class<T> type) {
        return getSpringFactoriesInstances(type, new Class<?>[]{});
    }


    /**
     * get spring factories instance
     *
     * @param type           class type
     * @param parameterTypes parameter types
     * @param args           parameter args
     **/
    public static <T> Collection<T> getSpringFactoriesInstances(Class<T> type,
                                                                Class<?>[] parameterTypes, Object... args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // Use names and ensure unique to protect against duplicates
        Set<String> names = new LinkedHashSet<>(
                SpringFactoriesLoader.loadFactoryNames(type, classLoader));
        List<T> instances = createSpringFactoriesInstances(type, parameterTypes,
                classLoader, args, names);
        AnnotationAwareOrderComparator.sort(instances);
        return instances;
    }


    /**
     * create spring factories instance
     *
     * @param type           class type
     * @param parameterTypes parameter types
     * @param classLoader    class loader
     * @param args           parameter  args
     * @param names          加载的全限定类名
     **/
    private static <T> List<T> createSpringFactoriesInstances(Class<T> type,
                                                              Class<?>[] parameterTypes, ClassLoader classLoader, Object[] args,
                                                              Set<String> names) {
        List<T> instances = new ArrayList<>(names.size());
        for (String name : names) {
            try {
                Class<?> instanceClass = ClassUtils.forName(name, classLoader);
                Assert.isAssignable(type, instanceClass);
                Constructor<?> constructor = instanceClass
                        .getDeclaredConstructor(parameterTypes);
                T instance = (T) instantiateClass(constructor, args);
                instances.add(instance);
            } catch (Throwable ex) {
                throw new IllegalArgumentException(
                        "Cannot instantiate " + type + " : " + name, ex);
            }
        }
        return instances;
    }


    public static void main(String[] args) {
        List<Goods> goods = new ArrayList<>();
        goods.addAll(SpringFactoriesUtils.getSpringFactoriesInstances(Goods.class));
        goods.stream().map(Goods::getGoods).forEach(System.out::println);
    }
}
