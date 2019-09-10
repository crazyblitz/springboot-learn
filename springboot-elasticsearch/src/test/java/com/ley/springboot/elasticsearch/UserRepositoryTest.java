package com.ley.springboot.elasticsearch;

import com.ley.springboot.elasticsearch.bean.User;
import com.ley.springboot.elasticsearch.dao.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ElasticsearchApplication.class})
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testElasticsearchRepository() {
        List<User> users = new ArrayList<>();
        users.add(new User(UUID.randomUUID().toString(), "刘恩源", 20));
        users.add(new User(UUID.randomUUID().toString(), "刘恩源1", 21));
        users.add(new User(UUID.randomUUID().toString(), "刘恩源2", 22));
        users.add(new User(UUID.randomUUID().toString(), "刘恩源3", 23));
        users.add(new User(UUID.randomUUID().toString(), "刘恩源4", 24));
        users.add(new User(UUID.randomUUID().toString(), "刘恩源5", 25));
        userRepository.saveAll(users);
    }


    @Test
    public void testFind() {
        log.info("users: {}", userRepository.findUsersByNameAndAgeIsGreaterThan("刘", 21));
    }

    @Test
    public void testAtomicFieldUpdater() {
        // 设置柯南的年龄是10岁
        AtomicIntegerFieldUpdaterTest atomicIntegerFieldUpdaterTest = new AtomicIntegerFieldUpdaterTest();
        atomicIntegerFieldUpdaterTest.test();
    }

}

class AtomicIntegerFieldUpdaterTest {
    // 创建原子更新器，并设置需要更新的对象类和对象的属性
    private static AtomicIntegerFieldUpdater<com.ley.springboot.elasticsearch.User> a =
            AtomicIntegerFieldUpdater.newUpdater(com.ley.springboot.elasticsearch.User.class, "old");


    public void test() {
        // 设置柯南的年龄是10岁
        com.ley.springboot.elasticsearch.User conan = new com.ley.springboot.elasticsearch.User("conan", 10);
        // 柯南长了一岁，但是仍然会输出旧的年龄
        System.out.println(a.getAndIncrement(conan));
        // 输出柯南现在的年龄
        System.out.println(a.get(conan));
    }
}