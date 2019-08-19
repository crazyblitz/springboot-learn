package com.ley.spring.learn.object.provider;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ThreadPoolExecutor;

@Service
public class UserService {

    private ThreadPoolTaskExecutor threadPool;

    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    @Qualifier("clockPunchThreadPool1")
    public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
        this.threadPool = threadPool;
        System.out.println(this.threadPool.getThreadNamePrefix());
    }

    @Autowired
    @Qualifier("clockPunchThreadPool2")
    public void setThreadPool(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public ThreadPoolTaskExecutor getThreadPool() {
        System.out.println(this.threadPool.getThreadPoolExecutor().toString());
        return threadPool;
    }

    public void objectProvider() {
        StringBuilder builder = new StringBuilder();
        builder.append("Hello World.");
        ListenableFuture<String> future = threadPool.submitListenable(() -> {
            System.out.println(Thread.currentThread().getName());
            return builder.toString();
        });
        future.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable ex) {

            }

            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }
        });
    }

    public void objectProvider2() {
        StringBuilder builder = new StringBuilder();
        builder.append("Hello World.");
        threadPoolExecutor.submit(() -> System.out.println("Hello World 2."));
    }
}
