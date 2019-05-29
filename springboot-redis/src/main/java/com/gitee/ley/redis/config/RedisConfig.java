package com.gitee.ley.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitee.ley.redis.handler.RedisCacheErrorHandler;
import com.gitee.ley.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @author liuenyuan
 **/
@Configuration
@EnableCaching
@EnableRedisRepositories
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisProperties redisProperties;

    public static final String REDIS_CACHE_NAME = "redis-cache";


    public static final String CACHE_KEY_PREFIX = REDIS_CACHE_NAME + "::";

    /**
     * 配置自动缓存
     *
     * @see CacheManager
     * @see org.springframework.cache.support.AbstractCacheManager
     * @see org.springframework.cache.annotation.Cacheable
     * @see org.springframework.cache.annotation.CachePut
     * @see org.springframework.cache.annotation.CacheEvict
     **/
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        //设置value的序列化方式为Jackson2JsonRedisSerializer
        //设置key的序列化方式为StringRedisSerializer
        //设置key的前缀
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(valueSerializer()))
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(keySerializer()))
                .computePrefixWith(CacheKeyPrefix.simple());
        log.info("key prefix:{}", defaultCacheConfig.getKeyPrefixFor(RedisConfig.REDIS_CACHE_NAME));

        //设置默认超过期时间是100秒
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(-1L));

        //初始化RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);

        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig,
                RedisConfig.REDIS_CACHE_NAME);
        //设置缓存事物
        cacheManager.setTransactionAware(true);
        cacheManager.afterPropertiesSet();
        return cacheManager;

    }


    /**
     * key redis serializer: {@link StringRedisSerializer} and
     * key redis serializer: {@link Jackson2JsonRedisSerializer}
     **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Autowired RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setKeySerializer(keySerializer());
        template.setValueSerializer(valueSerializer());
        template.setHashKeySerializer(keySerializer());
        template.setHashValueSerializer(valueSerializer());
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();

        log.info("redis server host:{},port:{},database:{}",
                redisProperties.getHost(), redisProperties.getPort(), redisProperties.getDatabase());
        return template;
    }

    @Bean
    public RedisUtils redisUtils(@Autowired RedisTemplate redisTemplate) {
        log.info("redis template: {}", redisTemplate);
        return new RedisUtils(redisTemplate);
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new RedisCacheErrorHandler();
    }

    /**
     * key serializer
     **/
    protected StringRedisSerializer keySerializer() {
        return new StringRedisSerializer();
    }


    /**
     * value serializer
     **/
    protected Jackson2JsonRedisSerializer<Object> valueSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}
