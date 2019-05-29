package com.gitee.ley.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisApplication.class})
@Slf4j
public class DruidDataSourceConfigTest {

    @Autowired
    @Qualifier("db1")
    private DataSource db1;

    @Autowired
    @Qualifier("db2")
    private DataSource db2;

    @Test
    public void testDataSource() {
        log.info("DB_1: {}", db1.toString());
        log.info("DB_2: {}", db2.toString());
    }
}
