package com.ley.servlet.container.initializer;

import com.ley.servlet.container.servlet.MyServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 类描述: my servlet web application initializer
 *
 * @author liuenyuan
 * @date 2019/5/6 10:39
 * @describe
 */
public class MyServletWebApplicationInitializer implements MyWebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic registration = servletContext.addServlet("myServlet",
                new MyServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/test");
        servletContext.log(registration.toString());
    }
}
