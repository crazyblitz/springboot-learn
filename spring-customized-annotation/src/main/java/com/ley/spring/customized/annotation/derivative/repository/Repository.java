package com.ley.spring.customized.annotation.derivative.repository;


import java.lang.annotation.*;

/**
 * @author liuenyuan
 * @see RepositoryScan
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Repository {

}
