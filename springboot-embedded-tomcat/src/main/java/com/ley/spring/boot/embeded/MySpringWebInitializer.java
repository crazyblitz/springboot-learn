package com.ley.spring.boot.embeded;

import com.ley.spring.boot.embeded.root.RootConfig;
import com.ley.spring.boot.embeded.web.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContainerInitializer;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/5 21:05
 * @describe
 * @see ServletContainerInitializer
 */
public class MySpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return of(RootConfig.class);
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Filter[] filters = getServletFilters();
        for (Filter filter : filters) {
            System.out.println(filter.toString());
        }
        return of(WebConfig.class);
    }

    @Override
    protected String[] getServletMappings() {
        return of("/*");
    }


    private static <T> T[] of(T... values) {
        return values;
    }


    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return of(new CharacterEncodingFilter());
    }
}
