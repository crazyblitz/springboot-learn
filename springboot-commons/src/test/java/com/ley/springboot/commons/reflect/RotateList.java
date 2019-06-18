package com.ley.springboot.commons.reflect;

import com.ley.springboot.commons.utils.ClassUtils;
import org.springframework.core.ResolvableType;

import java.util.ArrayList;

public class RotateList extends ArrayList<Integer> {

    public static void main(String[] args) {
        System.out.println(ClassUtils.resolveGenericType(RotateList.class, 0));
        System.out.println(ResolvableType.forClass(RotateList.class).getGeneric(0).resolve());
    }
}
