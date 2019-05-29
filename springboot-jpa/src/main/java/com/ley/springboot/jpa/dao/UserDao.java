package com.ley.springboot.jpa.dao;

import com.ley.springboot.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, Serializable {
}