package com.ley.spring.learn.aspect;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AspectApplication {


    @Autowired
    private AspectJProxyFactory aspectJProxyFactory;

    @Autowired
    private ProxyFactory proxyFactoryBean;


    @Autowired
    private PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(AspectApplication.class, args);
    }

    @PostConstruct
    public void userServiceAspectTest() {
        UserService userService = aspectJProxyFactory.getProxy();
        System.out.println(userService.listUserNames());
        System.out.println(aspectJProxyFactory.getAdvisors());
        System.out.println(aspectJProxyFactory.getAdvisorChainFactory());
        System.out.println(aspectJProxyFactory.getAopProxyFactory());
    }

    @PostConstruct
    public void userServiceAspectTest1() {
        UserService userService = (UserService) proxyFactoryBean.getProxy();
        System.out.println(userService.listUserNames());
        System.out.println(aspectJProxyFactory.getAdvisors());
        System.out.println(aspectJProxyFactory.getAdvisorChainFactory());
        System.out.println(aspectJProxyFactory.getAopProxyFactory());
    }

    @PostConstruct
    public void personService() {
        System.out.println(personService);
        System.out.println(personService.listPersonNames());
        System.out.println(AopUtils.isAopProxy(personService));
        System.out.println(AopUtils.isJdkDynamicProxy(personService));
        System.out.println(AopUtils.getTargetClass(personService));
    }
}
