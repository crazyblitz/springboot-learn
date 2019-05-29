package com.gitee.ley.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService<String, Object> {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    private final static long TIMEOUT = 1L;

    private final static TimeUnit HOUR = TimeUnit.HOURS;

    @Override
    public void putCache(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    @Override
    public Object getCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object getCache(String key, CachePredicate<String, Object> cachePredicate) {
        return doGetCache(key, cachePredicate, TIMEOUT, HOUR);
    }

    @Override
    public Object getCache(String key, CachePredicate<String, Object> cachePredicate, long timeout, TimeUnit timeUnit) {
        return doGetCache(key, cachePredicate, timeout, timeUnit);
    }

    @Override
    public void deleteCache(String key) {
        redisTemplate.delete(key);
    }


    private Object doGetCache(String key, CachePredicate<String, Object> cachePredicate, long timeout, TimeUnit timeUnit) {
        Object result = getCache(key);
        if (result == null) {
            Object execute = cachePredicate.execute(key);
            putCache(key, execute, timeout, timeUnit);
            result = execute;
        }
        return result;
    }
}
