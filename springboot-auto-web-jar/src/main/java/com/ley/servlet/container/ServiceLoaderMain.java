package com.ley.servlet.container;

import javax.servlet.ServletContainerInitializer;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/6 12:43
 * @describe
 */
public class ServiceLoaderMain {

    public static void main(String[] args) {
        ServiceLoader<ServletContainerInitializer> initializers = ServiceLoader.load(ServletContainerInitializer.class);
        Iterator<ServletContainerInitializer> iterator = initializers.iterator();
        while (iterator.hasNext()) {
            ServletContainerInitializer initializer = iterator.next();
            System.out.println(initializer);
        }
    }
}
