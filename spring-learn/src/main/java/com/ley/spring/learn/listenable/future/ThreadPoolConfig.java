package com.ley.spring.learn.listenable.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;

/**
 * 打卡任务线程池配置
 *
 * @author zhiyuan
 **/
@Configuration
@Slf4j
public class ThreadPoolConfig {

    /**
     * 打卡任务线程池
     **/
    @Bean(name = "clockPunchThreadPool", destroyMethod = "destroy")
    public ThreadPoolTaskExecutor clockPunchThreadPool() {
        ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        builder.setNameFormat("clock-punch-thread-pool-%d").setUncaughtExceptionHandler((thread, ex)
                -> log.error("thread: {},throw exception message: {}", thread.getName(), ex.getMessage()));
        ThreadFactory threadFactory = builder.build();
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(4);
        threadPool.setMaxPoolSize(4);
        threadPool.setKeepAliveSeconds(0);
        threadPool.setQueueCapacity(2000);
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        threadPool.setThreadFactory(threadFactory);
        threadPool.initialize();
        return threadPool;
    }
}