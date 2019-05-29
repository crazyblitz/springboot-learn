package com.ley.springboot.autoconfig.bean.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            User user = (User) bean;
            user.setAge("12");
            user.setName("刘恩源");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof  User){
            User user = (User) bean;
            user.setName("刘恩源2");
            System.out.println(bean);
        }
        return bean;
    }
}
