package com.gitee.ley.redis.cache;

import java.util.concurrent.TimeUnit;

public interface CacheService<K, V> {

    /**
     * 设置缓存
     *
     * @param key      缓存Key
     * @param value    缓存值
     * @param timeout  缓存过期时间
     * @param timeUnit 缓存过期时间单位
     **/
    void putCache(K key, V value, long timeout, TimeUnit timeUnit);


    /**
     * 获取缓存
     *
     * @param key 缓存Key
     **/
    V getCache(K key);


    /**
     * 获取缓存
     *
     * @param key          缓存Key
     * @param cachePredicate 缓存判断器
     **/
    V getCache(K key, CachePredicate<K, V> cachePredicate);


    /**
     * 获取缓存
     *
     * @param key          缓存Key
     * @param cachePredicate 缓存判断器
     * @param timeout      缓存过期时间
     * @param timeUnit     缓存过期时间单位
     **/
    V getCache(K key, CachePredicate<K, V> cachePredicate, long timeout,
               TimeUnit timeUnit);


    /**
     * 删除缓存
     *
     * @param key 缓存Key
     **/
    void deleteCache(K key);
}
