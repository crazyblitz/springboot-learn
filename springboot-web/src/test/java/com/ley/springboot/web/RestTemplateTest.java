package com.ley.springboot.web;

import com.google.gson.Gson;
import com.ley.springboot.web.bean.User;
import com.ley.springboot.web.http.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = {WebApplication.class})
@RunWith(SpringRunner.class)
@Slf4j
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_PREFIX = "http://localhost:9090/api/user";

    @Autowired
    private Gson gson;

    /**
     * 没有参数
     **/
    @Test
    public void testGet1() {
        //getForEntity
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL_PREFIX, String.class);
        log.info("response: {}", responseEntity.getBody());


        //getForObject
        Result result = restTemplate.getForObject(URL_PREFIX, Result.class);
        log.info("response: {}", result);
    }


    /**
     * 有一个参数
     **/
    @Test
    public void testGet2() {
        //getForEntity
        String url = URL_PREFIX + "/{1}";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, 1);
        log.info("response: {}", responseEntity.getBody());


        //getForObject
        Result result = restTemplate.getForObject(url, Result.class, 1);
        log.info("response: {}", result);


        //getForEntity
        String url1 = URL_PREFIX + "/{id}";
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        ResponseEntity<String> responseEntity1 = restTemplate.getForEntity(url1, String.class, params);
        log.info("response1: {}", responseEntity1.getBody());


        //getForObject
        Result result1 = restTemplate.getForObject(url1, Result.class, params);
        log.info("response1: {}", result);
    }


    /**
     * post 有参数
     **/
    @Test
    public void testPost1() {
        User user = new User(5, "刘恩源5", 25);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_PREFIX,
                user, String.class);
        log.info("response: {}", responseEntity);

        Result result = restTemplate.postForObject(URL_PREFIX, user, Result.class);

        log.info("result: {}", result);
    }


    /**
     * 实现以POST请求提交资源,返回新资源的URI
     **/
    @Test
    public void testPost2() {
        User user = new User(6, "刘恩源6", 25);

        URI responseURI = restTemplate.postForLocation(URL_PREFIX + "/add1",
                user);

        log.info("response: {}", responseURI);
    }

    /**
     * put测试
     **/
    @Test
    public void testPut() {
        restTemplate.put(URL_PREFIX + "?name={1}&age={2}&id={3}",
                null, "刘恩燕", 23, "2");
    }


    @Test
    public void testDelete() {
        restTemplate.delete(URL_PREFIX + "/{1}", 1);
    }

}
