package com.gitee.ley.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;

/**
 * @since 1.5
 **/
//public class CustomizedRedisCacheManager extends RedisCacheManager {
//
//    @Value("${spring.cache.name.regex:##}")
//    private String cacheNameRegex;
//
//    public CustomizedRedisCacheManager(RedisOperations redisOperations) {
//        super(redisOperations);
//    }
//
//
//    @Override
//    public Cache getCache(String name) {
//        String[] cacheParams = name.split(cacheNameRegex);
//        String cacheName = cacheParams[0];
//        Long expireTime = computeExpiration(cacheName);
//        if (cacheParams.length > 1) {
//            expireTime = Long.parseLong(cacheParams[1]);
//            this.setDefaultExpiration(expireTime);
//        }
//        Cache cache = super.getCache(cacheName);
//        return cache;
//    }
//}
