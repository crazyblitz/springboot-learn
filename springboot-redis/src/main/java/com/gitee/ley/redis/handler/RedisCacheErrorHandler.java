package com.gitee.ley.redis.handler;

import com.gitee.ley.redis.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * @author liuenyuan
 **/
@Slf4j
public class RedisCacheErrorHandler implements CacheErrorHandler {


    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.error("redis 异常: {},get key: {}", exception.getMessage(), RedisConfig.CACHE_KEY_PREFIX + key);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        log.error("redis 异常: {},put key: {} and value: {}", exception.getMessage(), RedisConfig.CACHE_KEY_PREFIX + key, value);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.error("redis 异常: {},delete key: {}", exception.getMessage(), RedisConfig.CACHE_KEY_PREFIX + key);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.error("redis 异常: {}", exception.getMessage());
    }
}
