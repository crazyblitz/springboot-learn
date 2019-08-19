package com.ley.spring.learn.object.provider;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        builder.setNameFormat("object-thread-pool-%d").setUncaughtExceptionHandler((thread, ex)
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


    /**
     * 打卡任务线程池
     **/
    @Bean(name = "clockPunchThreadPool1", destroyMethod = "destroy")
    public ThreadPoolTaskExecutor clockPunchThreadPool1() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(4);
        threadPool.setMaxPoolSize(4);
        threadPool.setKeepAliveSeconds(0);
        threadPool.setQueueCapacity(2000);
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        threadPool.setThreadNamePrefix("object-thread-pool1-");
        threadPool.initialize();
        return threadPool;
    }

    /**
     * 打卡任务线程池
     **/
    @Bean(name = "clockPunchThreadPool2", destroyMethod = "shutdown")
    public ThreadPoolExecutor clockPunchThreadPool2() {
        MonitorThreadPoolTaskExecutor threadPoolTaskExecutor = new MonitorThreadPoolTaskExecutor("object-thread-pool1-",
                4, 4, 0L, TimeUnit.MICROSECONDS, 100, new ThreadPoolExecutor.AbortPolicy(),
                (t, e) -> log.error("thread: {},执行异常: {}", t.getName(), e.getMessage()));
        return threadPoolTaskExecutor;
    }
}
