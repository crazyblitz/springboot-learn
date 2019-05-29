package com.gitee.ley.redis.service;

import com.gitee.ley.redis.bean.BusRefImg;
import com.gitee.ley.redis.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * redis use for example
 *
 * @author liuenyuan
 * @email [2036185346@qq.com]
 **/
@Service
@CacheConfig(cacheNames = RedisConfig.REDIS_CACHE_NAME)
@Slf4j
public class RedisService{

    /**
     * #p0,代表第一个参数
     * Cacheable如果缓存没有值,从则执行方法并缓存数据,如果缓存有值,则从缓存中获取值.
     **/
    @Cacheable(key = "#p0")
    public BusRefImg findOne(String id) {
        BusRefImg busRefImg = new BusRefImg(id, UUID.randomUUID().toString(), UUID.randomUUID().toString());
        log.info("add redis cache key: {},value: {}", id, busRefImg);
        return busRefImg;
    }

    /**
     * 删除
     **/
    @CacheEvict(key = "#p0")
    public boolean deleteOne(String id) {
        return true;
    }


    /**
     * 新增
     **/
    @CachePut(key = "#p0.id")
    public BusRefImg saveOne(BusRefImg busRefImg) {
        return busRefImg;
    }


    /**
     * 修改
     * **/
    @CacheEvict(key = "#p0.id")
    public BusRefImg updateOne(BusRefImg busRefImg) {
        log.info("remove redis cache key: {}", busRefImg.getId());
        return busRefImg;
    }
}
