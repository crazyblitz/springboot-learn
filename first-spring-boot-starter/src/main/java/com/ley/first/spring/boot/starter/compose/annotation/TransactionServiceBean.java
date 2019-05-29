package com.ley.first.spring.boot.starter.compose.annotation;

/**
 * 类描述:
 *
 * @author liuenyuan
 * @date 2019/4/27 11:03
 * @describe
 */
@TransactionalService(name = "transactionServiceBean", manager = "txManager2")
public class TransactionServiceBean {

    public void save() {
        System.out.println("保存操作...");
    }
}
