package com.gitee.ley.mybatis.utils;

/**
 * datasource type enum(数据源名称枚举,尽量是数据库名称)
 **/
public enum DBTypeEnum {

    /**
     * datasource 1
     **/
    DB_1("db1"),

    /**
     * datasource 2
     **/
    DB_2("db2");

    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
