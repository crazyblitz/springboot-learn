package com.ley.springboot.commons.security;

/**
 * base64 utility class
 *
 * @author liuenyuan
 **/
public class Base64Utils extends org.springframework.util.Base64Utils {

    public static void main(String[] args) {
        String test = "刘恩源";
        String test2Byte64 = Base64Utils.encodeToString(test.getBytes());
        System.out.println("base64 encode: " + test2Byte64);
        System.out.println("base64 decode: " + new String(Base64Utils.decodeFromString(test2Byte64)));
    }
}
