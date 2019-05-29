package com.ley.springboot.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 类描述: {@link RequestMapping#consumes()}指定请求提交内容的类型(Content-Type)
 * <br/>{@link RequestMapping#produces()}指定返回值的类型及其编码
 * <br/>
 * {@link Selector},使用maven-compiler-plugin 3.7.0,指定
 * <configuration>
 * <parameters>true</parameters>
 * </configuration>
 *
 * @author liuenyuan
 * @date 2019/5/19 9:59
 * @describe
 */
@Endpoint(id = PersonEndpoint.PERSON_PREFIX)
public class PersonEndpoint {

    public final static String PERSON_PREFIX = "person";

    private final Map<String, String> people = new HashMap<>();

    public PersonEndpoint() {

    }

    @PostConstruct
    protected void init() {
        this.people.put("mike", "Michael Redlich");
        this.people.put("rowena", "Rowena Redlich");
        this.people.put("barry", "Barry Burd");
    }

    @ReadOperation
    public Map<String, String> getAll() {
        throw new UnsupportedOperationException("getAll()");
    }


    @ReadOperation
    public String getPerson(@Selector String person) {
        return this.people.get(person);
    }

    @WriteOperation(produces = {"application/json"})
    public void updatePerson(String name, String person) {
        this.people.put(name, person);
    }

}
