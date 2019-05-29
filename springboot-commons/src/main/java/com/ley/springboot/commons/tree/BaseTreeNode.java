package com.ley.springboot.commons.tree;

import lombok.Data;

import java.util.List;

/**
 * base tree node
 **/
@Data
public class BaseTreeNode<T> {

    private T id;

    private T pid;

    //不要带上泛型,去掉泛型.防止在设置属性时,会过滤掉子类相关属性
    private List<BaseTreeNode> children;
}
