package com.ley.spring.learn.schema.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MynsNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("dateFormat", new MynsBeanDefinitionParser());
    }
}
