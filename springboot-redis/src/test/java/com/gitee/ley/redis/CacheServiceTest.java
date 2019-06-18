package com.gitee.ley.redis;

import com.gitee.ley.redis.cache.CachePredicate;
import com.gitee.ley.redis.cache.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
@Slf4j
public class CacheServiceTest {


    @Autowired
    private CacheService<String, Object> cacheService;


    @Test
    public void testCacheService() {
        String cacheKey = "1001";
        System.out.println(cacheService.getCache(cacheKey, cacheValue -> "1002"));
    }
}
