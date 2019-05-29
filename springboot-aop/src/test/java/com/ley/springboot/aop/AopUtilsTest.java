package com.ley.springboot.aop;

import com.ley.springboot.aop.annotation.NeedLogin;
import com.ley.springboot.commons.aop.AopUtils;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;

public class AopUtilsTest {

    @Test
    public void test() {
        Annotation annotation = AopUtils.getAnnotation(NeedLogin.class, Target.class).get();
        System.out.println(annotation);
    }
}
