package org.qinlinj.nonlinear.tree.binarytree.practice;

import java.util.Stack;

public class _112_PathSum {
    class Solution1 {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }

            Stack<Node> stack = new Stack<>();
            stack.push(new Node(root, root.val));
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                TreeNode currNode = node.treeNode;
                int currVal = node.sumRes;
                if (currVal == targetSum && currNode.left == null && currNode.right == null) {
                    return true;
                }
                if (currNode.right != null) {
                    stack.push(new Node(currNode.right, currVal + currNode.right.val));
                }
                if (currNode.left != null) {
                    stack.push(new Node(currNode.left, currVal + currNode.left.val));
                }
            }
            return false;
        }

        class Node {
            TreeNode treeNode;
            int sumRes;

            Node(TreeNode treeNode, int sumRes) {
                this.treeNode = treeNode;
                this.sumRes = sumRes;
            }
        }
    }
}
