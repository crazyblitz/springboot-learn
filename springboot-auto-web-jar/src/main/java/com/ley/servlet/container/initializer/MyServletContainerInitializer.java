package com.ley.servlet.container.initializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 类描述: my servlet container initializer
 *
 * @author liuenyuan
 * @date 2019/5/6 10:33
 * @describe
 */
@HandlesTypes(MyWebApplicationInitializer.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
        List<MyWebApplicationInitializer> initializers = new LinkedList<>();
        if (webAppInitializerClasses != null) {
            for (Class<?> waiClass : webAppInitializerClasses) {
                // Be defensive: Some servlet containers provide us with invalid classes,
                // no matter what @HandlesTypes says...
                if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
                        MyWebApplicationInitializer.class.isAssignableFrom(waiClass)) {
                    try {
                        initializers.add((MyWebApplicationInitializer) accessibleConstructor(waiClass).newInstance());
                    } catch (Throwable ex) {
                        throw new ServletException("Failed to instantiate MyWebApplicationInitializer class", ex);
                    }
                }
            }
        }
        servletContext.log(initializers.toString());
        if (initializers.isEmpty()) {
            servletContext.log("No My WebApplicationInitializer types detected on classpath");
            return;
        }

        servletContext.log(initializers.size() + " My WebApplicationInitializers detected on classpath");
        for (MyWebApplicationInitializer initializer : initializers) {
            initializer.onStartup(servletContext);
        }
    }

    private static <T> Constructor<T> accessibleConstructor(Class<T> clazz, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        Constructor<T> ctor = clazz.getDeclaredConstructor(parameterTypes);
        makeAccessible(ctor);
        return ctor;
    }

    private static void makeAccessible(Constructor<?> ctor) {
        boolean result = (!Modifier.isPublic(ctor.getModifiers()) ||
                !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible();
        if (result) {
            ctor.setAccessible(true);
        }
    }
}
