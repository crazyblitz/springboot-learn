package com.gitee.ley.mybatis.utils;

/**
 * datasource context holder(用于保存当前线程的数据源名称)
 *
 * @author liuenyuan
 **/
public class DbContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dbTypeEnum
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        CONTEXT_HOLDER.set(dbTypeEnum.getValue());
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDbType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }
}
