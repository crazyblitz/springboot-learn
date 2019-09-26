package com.ley.springboot.commons.tree;

import com.ley.springboot.commons.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * tree utils
 **/
public abstract class BaseTreeUtils<T extends BaseTreeNode> {


    /**
     * 建树
     *
     * @param nodes 节点集合
     **/
    public List<T> buildTree(List<T> nodes) {
        List<T> rootNodes = new ArrayList<>(16);

        //获取根节点集合
        for (T node : nodes) {
            if (isRootNode(node)) {
                rootNodes.add(node);
            }
        }

        //为根节点设置子节点
        for (T rootNode : rootNodes) {
            //获取根节点下的所有子节点,使用getChildren()
            List<T> children = getChildren(rootNode, nodes);
            rootNode.setChildren(children);
        }

        return rootNodes;
    }


    /**
     * 获取子节点
     **/
    private List<T> getChildren(T rootNode, List<T> nodes) {
        List<T> children = new ArrayList<>(256);

        for (T node : nodes) {
            // 遍历所有节点,将所有父id与传过来的根节点的id比较
            //相等说明:为该根节点的子节点
            if (rootNode.getId().equals(node.getPid())) {
                children.add(node);
            }
        }

        // 递归设置子节点集合
        for (T child : children) {
            child.setChildren(getChildren(child, nodes));
        }


        // 结束递归
        if (CollectionUtils.isEmpty(children)) {
            return Collections.emptyList();
        }

        return children;
    }


    /**
     * 判断一个节点是否是根节点
     *
     * @return return {@code true} when node is root node.
     **/
    protected abstract boolean isRootNode(T treeNode);


}
