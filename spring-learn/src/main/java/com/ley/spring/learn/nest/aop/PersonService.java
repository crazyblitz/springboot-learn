package com.ley.spring.learn.nest.aop;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class PersonService {

    private final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public void nestAop() {
        hello2();
        System.out.println("nestAop");
    }

    public void nestAopHandle() {
        PersonService personService = (PersonService) AopContext.currentProxy();
        personService.hello2();
        System.out.println("nest aop handle");
    }

    public void aopInThread() {
        PersonService personService = (PersonService) AopContext.currentProxy();
        Object currentProxy = AopContext.currentProxy();
        ListeningExecutorService service = MoreExecutors.listeningDecorator(threadPool);
        ListenableFuture<?> listenableFuture = service.submit(() -> {
            Method method = ReflectionUtils.findMethod(MyAopContext.class, "setCurrentProxy", Object.class);
            ReflectionUtils.makeAccessible(method);
            try {
                ReflectionUtils.invokeMethod(method, BeanUtils.instantiateClass(MyAopContext.class), currentProxy);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            personService.hello3();
        });
        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@Nullable Object o) {
                log.info("执行成功");
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.error(throwable.getMessage(), throwable);
            }
        }, threadPool);
        System.out.println("nest in multi thread");
    }

    protected void hello2() {
        System.out.println("Hello2");
    }

    protected void hello3() {
        try {
            PersonService personService = (PersonService) AopContext.currentProxy();
            personService.hello4();
            System.out.println("hello3");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void hello4() {
        System.out.println("hello4");
    }
}
