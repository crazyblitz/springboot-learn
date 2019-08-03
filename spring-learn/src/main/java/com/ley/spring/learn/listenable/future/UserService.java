package com.ley.spring.learn.listenable.future;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class UserService {

    @Autowired
    @Qualifier("clockPunchThreadPool")
    private ThreadPoolTaskExecutor executor;

    public Double count() throws InterruptedException {
        ListenableFuture<Double> future = executor.submitListenable(() -> {
            throw new IllegalArgumentException("参数不合法.");
        });
        CountDownLatch latch = new CountDownLatch(1);
        StringBuilder builder = new StringBuilder();
        future.addCallback(new ListenableFutureCallback<Double>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("count异常: {}", ExceptionUtils.getStackTrace(ex));
                latch.countDown();
            }

            @Override
            public void onSuccess(Double result) {
                builder.append(result);
                latch.countDown();
            }
        });
        latch.await();
        return Double.parseDouble(builder.toString());
    }
}
