package com.ley.spring.learn.schema.xml.nest;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ComponentNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("component", new ComponentBeanDefinitionParser());
    }
}