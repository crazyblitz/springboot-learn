package com.crazyblitz.springboot.shiro.utils.web.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * RequestInfoFilter用来拦截请求响应时间
 **/
@Slf4j
public class RequestInfoFilter implements Filter {


    /**
     * 忽略拦截的文件类型
     **/
    private static final List<String> ignoreList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("enter 请求信息 filter");
    }

    @Override
    public void destroy() {
        log.info("leave 请求信息 filter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        try {
            long startTime = System.currentTimeMillis();
            String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
            if (!isIgnore(uri)) {
                log.info("==================== RequestInfoFilter Start ====================");
                log.info(request.getMethod() + " : " + uri); // 打印请求的url
                log.info("session存活时间：" + request.getSession().getMaxInactiveInterval());// 打印请求session最大存活时间
                this.logHeaders(request); //http请求头信息
                this.logParams(request);  //http请求参数
                this.logAttr(request);  //http请求附加信息
                chain.doFilter(request, response);
                long endTime = System.currentTimeMillis();
                log.info(request.getMethod() + " " + "耗时：" + (endTime - startTime) + " ms");
                log.info("==================== RequestInfoFilter End ====================");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 打印头信息日志
     **/
    private void logHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            String headName = (String) headers.nextElement();
            if (headName != null && !"".equals(headName)) {
                headerMap.put(headName, request.getHeader(headName));
            }
        }

        headerMap.put("RemoteHost", request.getRemoteHost() + ":" + request.getRemotePort());
        log.info("请求头信息: " + headerMap.toString());
    }

    /**
     * 打印HTTP参数信息
     **/
    private void logParams(HttpServletRequest request) {
        Map<String, String> maps = new HashMap<>();
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (StringUtils.isNotEmpty(key)) {
                String values = request.getParameter(key);
                maps.put(key, values);
            }
        }
        log.info(maps.toString());
    }

    /**
     * 打印额外属性信息
     **/
    private void logAttr(HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<>();
        Enumeration<String> keys = request.getAttributeNames();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (StringUtils.isNotEmpty(key)) {
                Object values = request.getAttribute(key);
                maps.put(key, values);
            }
        }
        log.info(maps.toString());
    }

    private static final boolean isIgnore(String url) {
        boolean ignore = false;
        int index = url.lastIndexOf(".");
        if (index > 0) {
            String suffix = url.substring(index + 1, url.length());
            if (ignoreList.contains(suffix)) {
                ignore = true;
            }
        }
        return ignore;
    }

}