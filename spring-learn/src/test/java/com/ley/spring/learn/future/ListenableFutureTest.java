package com.ley.spring.learn.future;

import com.ley.spring.learn.listenable.future.ListenableFutureApplication;
import com.ley.spring.learn.listenable.future.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = {ListenableFutureApplication.class})
@RunWith(SpringRunner.class)
public class ListenableFutureTest {

    @Resource
    private UserService userService;

    @Test
    public void test() throws InterruptedException {
        System.out.println(userService.count());
    }
}
