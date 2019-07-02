package com.ley.spring.learn.aspect;


import org.aopalliance.aop.Advice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public AspectJProxyFactory aspectJProxyFactory() {
        AspectJProxyFactory proxyFactoryBean = new AspectJProxyFactory(new UserService());
        String expression = "execution(* com.ley.spring.learn.aspect.UserService.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        Advice advice = new UserServiceMethodBeforeAdvice();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        proxyFactoryBean.addAdvisor(advisor);
        return proxyFactoryBean;
    }


    @Bean(name = "proxyFactory")
    public ProxyFactory proxyFactory() {
        ProxyFactory proxyFactory = new ProxyFactory(new UserService());
        String expression = "execution(* com.ley.spring.learn.aspect.UserService.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        Advice advice = new UserServiceMethodBeforeAdvice();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        proxyFactory.addAdvisors(advisor);
        return proxyFactory;
    }


    @Bean(name = "personService")
    public PersonService personService() {
        ProxyFactory proxyFactory = new ProxyFactory(new PersonServiceImpl());
        String expression = "execution(* com.ley.spring.learn.aspect.PersonService.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        Advice advice = new UserServiceMethodBeforeAdvice();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        proxyFactory.addAdvisors(advisor);
        return (PersonService) proxyFactory.getProxy();
    }

}
