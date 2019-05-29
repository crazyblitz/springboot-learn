package com.ley.springboot.logger;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ReflectionUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

//@SpringBootApplication
public class LoggerApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(LoggerApplication.class, args);
//    }

    String a = "123";
    String b = "123";

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Field field = LoggerApplication.class.getDeclaredField("a");
        Field field1 = LoggerApplication.class.getDeclaredField("b");
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        System.out.println( unsafe.objectFieldOffset(field));
        System.out.println( unsafe.objectFieldOffset(field1));

        System.out.println(a == b);
    }
}
