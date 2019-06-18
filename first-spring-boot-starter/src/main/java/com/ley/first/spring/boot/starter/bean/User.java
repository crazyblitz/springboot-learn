package com.ley.first.spring.boot.starter.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/24 17:38
 * @describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;

    public static void main(String[] args) {
        User user = new User();
        ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(User.class, "setName",
                String.class), user,"刘恩源");
        System.out.println(user);
    }
}
