package com.ley.servlet.container.servlet;


import javax.servlet.*;
import java.io.IOException;


/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/5/6 10:37
 * @describe
 */
public class MyServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("my servlet init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        res.getWriter().write("Hello World!");
        res.getWriter().flush();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("my servlet destroy");
    }
}
