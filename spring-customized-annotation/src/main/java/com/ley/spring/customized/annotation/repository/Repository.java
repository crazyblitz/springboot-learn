package com.ley.spring.customized.annotation.repository;


import java.lang.annotation.*;

/**
 * @see RepositoryScan
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Repository {

}
