package com.gitee.ley.redis;

import com.gitee.ley.redis.bean.BusRefImg;
import com.gitee.ley.redis.config.RedisConfig;
import com.gitee.ley.redis.service.RedisService;
import com.gitee.ley.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
@Slf4j
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisService redisService;


    @Test
    public void testRedisUtils() {
        redisUtils.set("host", "localhost");
        log.info("host: {}", redisUtils.get("host"));
    }


    @Test
    public void testAutoDeleteCache() {
        redisService.deleteOne("");
    }

    @Test
    public void testAutoCache() {
        String id = UUID.randomUUID().toString();
        redisService.findOne(id);
        String key = RedisConfig.CACHE_KEY_PREFIX + id;
        log.info("find one: {}", redisUtils.get(key));
    }


    @Test
    public void testSave() {
        String id = UUID.randomUUID().toString();
        redisService.saveOne(new BusRefImg(id, UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        System.out.println(redisUtils.get(RedisConfig.CACHE_KEY_PREFIX + id));
    }

    @Test
    public void testDelete() {
        redisService.deleteOne("eaadaa4e-923e-4be4-82d5-ab50450bc6b8");
    }


    @Test
    public void testRedisUtils0() {
        BusRefImg busRefImg = new BusRefImg();
        String id = UUID.randomUUID().toString();
        busRefImg.setId(id);
        busRefImg.setBusId(id);
        busRefImg.setImgId(id);
        redisUtils.set(id, busRefImg);
        System.out.println(redisUtils.get(id));
    }
}
