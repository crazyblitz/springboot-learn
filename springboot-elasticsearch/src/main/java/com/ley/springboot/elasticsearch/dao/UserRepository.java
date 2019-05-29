package com.ley.springboot.elasticsearch.dao;

import com.ley.springboot.elasticsearch.bean.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends ElasticsearchRepository<User, String> {

    List<User> findUsersByNameAndAgeIsGreaterThan(String name, Integer age);
}
