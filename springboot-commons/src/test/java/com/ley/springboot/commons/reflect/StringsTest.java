package com.ley.springboot.commons.reflect;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.junit.Test;

public class StringsTest {

    //获得两个字符串相同的前缀或者后缀
    @Test
    public void testStringsEqualPrefixOrSuffix() {
        //Strings.commonPrefix(a,b) demo
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a, b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c, d);
        System.out.println("c,d common suffix is " + ourSuffix);
    }


    //使用Splitter类来拆分字符串
    @Test
    public void splitter() {
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }
    }
}
