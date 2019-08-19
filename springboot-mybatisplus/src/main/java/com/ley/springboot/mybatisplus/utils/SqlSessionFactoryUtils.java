package com.ley.springboot.mybatisplus.utils;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * sql session factory utility class
 *
 * @author zhiyuan
 **/
@Component
public class SqlSessionFactoryUtils {

    private SqlSessionFactory sqlSessionFactory;

    private final static int DEFAULT_BATCH_SIZE = 1000;

    public SqlSessionFactoryUtils(ObjectProvider<SqlSessionFactory> sqlSessionFactoryProvider) {
        this.sqlSessionFactory = sqlSessionFactoryProvider.getIfAvailable();
    }

    /**
     * @param entityList   update entity list
     * @param sqlStatement {@link MappedStatement#getId()}
     * @param batchSize    update batch size
     **/
    public <T> void updateBatch(Collection<T> entityList, String sqlStatement, Integer batchSize) {
        Assert.isTrue(CollectionUtils.isEmpty(entityList), "error: entityList must not be empty");
        Assert.isTrue(StringUtils.hasText(sqlStatement), "sqlStatement不能为空");
        if (batchSize == null) {
            batchSize = DEFAULT_BATCH_SIZE;
        }
        try (SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactory, ExecutorType.BATCH, null)) {
            int i = 0;
            for (T record : entityList) {
                sqlSession.update(sqlStatement, record);
                if (i >= 1 && i % batchSize == 0) {
                    sqlSession.flushStatements();
                }
                i++;
            }

        }
    }

    /**
     * @param entityList   insert entity list
     * @param sqlStatement {@link MappedStatement#getId()}
     * @param batchSize    insert batch size
     **/
    public <T> void insertBatch(Collection<T> entityList, String sqlStatement, Integer batchSize) {
        Assert.isTrue(!CollectionUtils.isEmpty(entityList), "error: entityList must not be empty");
        Assert.isTrue(StringUtils.hasText(sqlStatement), "sqlStatement不能为空");
        if (batchSize == null) {
            batchSize = DEFAULT_BATCH_SIZE;
        }
        try (SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactory, ExecutorType.BATCH, null)) {
            int i = 0;
            for (T record : entityList) {
                sqlSession.insert(sqlStatement, record);
                if (i >= 1 && i % batchSize == 0) {
                    sqlSession.flushStatements();
                }
                i++;
            }
        }
    }
}
