package com.ley.springboot.commons;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;

/**
 * 获取泛型擦除的类型
 * **/
public class TypeTokenTest {

    public static void main(String[] args) {

        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println("intList type is " + intList.getClass());
        System.out.println("stringList type is " + stringList.getClass());
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));

        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {};
        TypeToken<?> genericTypeToken = typeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        System.out.println(genericTypeToken.getType());
    }
}
