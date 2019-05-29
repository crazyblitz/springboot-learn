package com.gitee.ley.redis.cache;

public interface CachePredicate<K, V> {

    V execute(K key);
}
