package com.ley.springboot.commons;

import com.ley.springboot.commons.tree.BaseTreeNode;
import com.ley.springboot.commons.tree.BaseTreeUtils;
import com.ley.springboot.commons.web.json.GsonUtils;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;

import java.util.List;

public class TreeUtilsTest {


    @Data
    @ToString(callSuper = true)
    public class TreeNode extends BaseTreeNode<String> {
        private String name;

    }

    public class TreeUtils extends BaseTreeUtils<TreeNode> {

        @Override
        protected boolean isRootNode(TreeNode treeNode) {
            if (treeNode.getPid() == null) {
                return true;
            } else {
                return false;
            }
        }
    }


    @Test
    public void test() {
        List<TreeNode> nodes = new ArrayList<>();

        TreeNode treeNode = new TreeNode();
        treeNode.setId("1");
        treeNode.setName("我的世界");

        TreeNode treeNode1 = new TreeNode();
        treeNode1.setId("2");
        treeNode1.setName("我的世界2");
        treeNode1.setPid("1");

        TreeNode treeNode2 = new TreeNode();
        treeNode2.setId("3");
        treeNode2.setName("我的世界1");
        treeNode2.setPid("1");


        TreeNode treeNode3 = new TreeNode();
        treeNode3.setId("4");
        treeNode3.setName("我的世界3");
        treeNode3.setPid("2");

        TreeNode treeNode4 = new TreeNode();
        treeNode4.setId("5");
        treeNode4.setName("我的世界4");
        treeNode4.setPid("3");

        nodes.add(treeNode);
        nodes.add(treeNode1);
        nodes.add(treeNode2);
        nodes.add(treeNode3);
        nodes.add(treeNode4);


        TreeUtils treeUtils = new TreeUtils();
        List<TreeNode> treeNodes = treeUtils.buildTree(nodes);

        System.out.println(GsonUtils.toJson(treeNodes));
    }
}
