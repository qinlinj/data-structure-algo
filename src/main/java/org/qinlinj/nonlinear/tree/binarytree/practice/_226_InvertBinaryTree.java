package org.qinlinj.nonlinear.tree.binarytree.practice;

import java.util.Stack;

public class _226_InvertBinaryTree {
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                invert(curr);
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }

            return root;
        }

        public void invert(TreeNode node) {
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
    }

    
}
