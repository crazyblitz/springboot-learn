package com.gitee.ley.redis;

import com.gitee.ley.redis.cache.CachePredicate;
import com.gitee.ley.redis.cache.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
@Slf4j
public class CacheServiceTest {


    @Autowired
    private CacheService<String, Object> cacheService;


    @Test
    public void testCacheService() {
        String cacheKey = "1001";
        System.out.println(cacheService.getCache(cacheKey, cacheValue -> {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return "";
        }));
    }
}
