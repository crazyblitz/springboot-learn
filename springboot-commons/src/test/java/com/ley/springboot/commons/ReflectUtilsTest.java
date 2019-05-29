package com.ley.springboot.commons;

import com.ley.springboot.commons.reflect.Person;
import com.ley.springboot.commons.reflect.RotateList;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;


public class ReflectUtilsTest {

    @Test
    public void testReflect() {
        Person person = new Person();
        Field nameField = ReflectionUtils.findField(Person.class, "name");
        nameField.setAccessible(true);
        ReflectionUtils.setField(nameField, person, "刘恩源");
        System.out.println(person);
        ReflectionUtils.doWithFields(Person.class, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                field.setAccessible(true);
                if (field.getName().equals("address")) {
                    System.out.println("filed name: " + field.getName());
                    field.set(person, "河南省信阳市商城县");
                }
            }
        });
        System.out.println(person);
    }
}
