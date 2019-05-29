package com.ley.springboot.aop.introduction;

import com.ley.springboot.aop.introduction.config.IntroductionConfig;
import com.ley.springboot.aop.introduction.service.Animal;
import com.ley.springboot.aop.introduction.service.Person;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * AOP之引介增强
 *
 * @see org.springframework.aop.support.DelegatingIntroductionInterceptor
 * @see org.springframework.aop.IntroductionAdvisor
 * @see org.springframework.aop.IntroductionInterceptor
 * @see org.springframework.aop.DynamicIntroductionAdvice
 * @see org.springframework.aop.IntroductionInfo
 * @see DeclareParents
 **/
public class IntroductionApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntroductionConfig.class);
        Person person = (Person) context.getBean("personImpl");
        person.likePerson();
        Animal animal = (Animal) person;
        animal.eat();
    }
}
