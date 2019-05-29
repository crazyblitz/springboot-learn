package com.ley.springboot.autoconfig;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.stream.Collectors;

public class BeanInfoTest {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Server.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
            System.out.println(propertyDescriptor.getReadMethod()+":"+propertyDescriptor.getWriteMethod());
            System.out.println(propertyDescriptor.getName());
        }
    }
}
