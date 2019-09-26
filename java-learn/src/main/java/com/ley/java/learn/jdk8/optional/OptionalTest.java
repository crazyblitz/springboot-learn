package com.ley.java.learn.jdk8.optional;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @see java.util.Optional
 **/
public class OptionalTest {

    @Test
    public void testOptional() {
        User user = new User();
        user.setName("");
        user.setAddress("中国");
        User userTmp=Optional.of(user).filter(u -> StringUtils.hasText(u.getName()))
                .filter(u -> StringUtils.hasText(u.getAddress())).orElseThrow(() ->
                new IllegalArgumentException("user can't not be null."));
        System.out.println(userTmp);

    }
}
