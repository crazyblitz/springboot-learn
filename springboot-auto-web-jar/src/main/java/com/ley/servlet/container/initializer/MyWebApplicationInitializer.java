package com.ley.servlet.container.initializer;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 类描述: my web application initializer
 *
 * @author liuenyuan
 * @date 2019/5/6 10:30
 * @describe
 */
public interface MyWebApplicationInitializer {


    void onStartup(ServletContext servletContext) throws ServletException;
}
