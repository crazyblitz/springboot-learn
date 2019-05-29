package com.ley.springboot.aop.introduction.aspect;

import com.ley.springboot.aop.introduction.service.Animal;
import com.ley.springboot.aop.introduction.service.impl.AnimalImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 测试引介增强
 **/
@Aspect
@Component
public class IntroductionAspect {

    /**
     * "+"表示person的所有子类；defaultImpl 表示默认需要添加的新的类
     **/
    @DeclareParents(value = "com.ley.springboot.aop.introduction.service.Person+", defaultImpl = AnimalImpl.class)
    private Animal animal;
}
