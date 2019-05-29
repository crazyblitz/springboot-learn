package com.ley.springboot.aop.code.service.impl;

import com.ley.springboot.aop.code.service.CodeService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {


    /**
     * 修改类,不要出现"自调用 "的情况:这是Spring文档中推荐的“最佳”方案;
     **/
    @Override
    public int add(int a, int b) {
        Object currentProxy = AopContext.currentProxy();
        CodeService codeService = (CodeService) currentProxy;
        codeService.sayHello("Hello: " + a + "," + b);
        return a + b;
    }

    @Override
    public void sayHello(String name) {
        System.out.println("Hello: " + name);
    }
}
