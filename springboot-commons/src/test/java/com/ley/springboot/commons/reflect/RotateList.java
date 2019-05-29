package com.ley.springboot.commons.reflect;

import java.util.ArrayList;

public class RotateList extends ArrayList<Integer> {

    public static void main(String[] args) {
        System.out.println(ReflectUtils.getClassFromGenericType(RotateList.class));
    }
}
