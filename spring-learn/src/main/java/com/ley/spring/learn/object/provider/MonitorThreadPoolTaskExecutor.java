package com.ley.spring.learn.object.provider;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;


/**
 * 带监控的线程池
 *
 * @author Aliue*/
@Slf4j
public class MonitorThreadPoolTaskExecutor extends ThreadPoolExecutor {

    /**
     * 保存任务开始执行的时间,当任务结束时,用任务结束时间减去开始时间计算任务执行时间
     **/
    private ConcurrentHashMap<String, Date> startTimes;

    /**
     * 线程池名称前缀
     **/
    private String poolNamePrefix;

    /**
     * 调用父类的构造方法,并初始化HashMap和线程池名称
     *
     * @param corePoolSize    线程池核心线程数
     * @param maximumPoolSize 线程池最大线程数
     * @param keepAliveTime   线程的最大空闲时间
     * @param unit            空闲时间的单位
     * @param queueCapacity   保存被提交任务的队列,建议设置队列的长度
     * @param poolNamePrefix  线程池名称前缀,例如 rpc-%d
     */
    public MonitorThreadPoolTaskExecutor(String poolNamePrefix, int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                         TimeUnit unit, int queueCapacity, RejectedExecutionHandler rejectedExecutionHandler,
                                         Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new LinkedBlockingQueue<>(queueCapacity),
                new MonitorThreadFactory(poolNamePrefix, uncaughtExceptionHandler).getThreadFactoryBuilder().build(), rejectedExecutionHandler);
        this.startTimes = new ConcurrentHashMap<>(256);
        this.poolNamePrefix = poolNamePrefix;
    }

    /**
     * 线程池延迟关闭时(等待线程池里的任务都执行完毕),统计线程池情况
     */
    @Override
    public void shutdown() {
        // 统计已执行任务、正在执行任务、未执行任务数量
        log.info(String.format(this.poolNamePrefix + " Going to shutdown. Executed tasks: %d, Running tasks: %d, Pending tasks: %d",
                this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size()));
        super.shutdown();
    }

    /**
     * 线程池立即关闭时,统计线程池情况
     */
    @Override
    public List<Runnable> shutdownNow() {
        // 统计已执行任务、正在执行任务、未执行任务数量
        log.info(
                String.format(this.poolNamePrefix + " Going to immediately shutdown. Executed tasks: %d, Running tasks: %d, Pending tasks: %d",
                        this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size()));
        return super.shutdownNow();
    }

    /**
     * 任务执行之前,记录任务开始时间
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTimes.put(String.valueOf(r.hashCode()), new Date(System.currentTimeMillis()));
    }

    /**
     * 任务执行之后,计算任务结束时间
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
        if (startDate == null) {
            startDate = new Date(0L);
        }
        Date finishDate = new Date(System.currentTimeMillis());
        long costTime = finishDate.getTime() - startDate.getTime();
        // 统计任务耗时、初始线程数、核心线程数、正在执行的任务数量、已完成任务数量、任务总数、队列里缓存的任务数量、池中存在的最大线程数、最大允许的线程数、线程空闲时间、线程池是否关闭、线程池是否终止
        log.info(String.format(this.poolNamePrefix
                        + ": Duration: %d ms, PoolSize: %d, CorePoolSize: %d, Active: %d, Completed: %d, Task: %d, Queue: %d, LargestPoolSize: %d, MaximumPoolSize: %d,KeepAliveTime: %d, isShutdown: %s, isTerminated: %s",
                costTime, this.getPoolSize(), this.getCorePoolSize(), this.getActiveCount(), this.getCompletedTaskCount(), this.getTaskCount(),
                this.getQueue().size(), this.getLargestPoolSize(), this.getMaximumPoolSize(), this.getKeepAliveTime(TimeUnit.MILLISECONDS),
                this.isShutdown(), this.isTerminated()));

    }

    /**
     * 生成线程池所用的线程,只是改写了线程池默认的线程工厂,传入线程池名称,便于问题追踪
     */
    static class MonitorThreadFactory {

        private final ThreadFactoryBuilder threadFactoryBuilder;

        MonitorThreadFactory(String poolNamePrefix, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            threadFactoryBuilder = new ThreadFactoryBuilder();
            threadFactoryBuilder.setNameFormat(poolNamePrefix);
            threadFactoryBuilder.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }

        ThreadFactoryBuilder getThreadFactoryBuilder() {
            return threadFactoryBuilder;
        }
    }

}
