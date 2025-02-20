package org.qinlinj.nonlinear.tree.binarytree.practice;

import org.qinlinj.nonlinear.tree.binarytree.TreeNode;

public class _104_MaximumDepthOfBinaryTree {
    // recursion version 1
    class Solution1 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int maxCount = 0;
            return getDepth(root, maxCount);
        }

        public int getDepth(TreeNode node, int maxCount) {
            if (node == null) {
                return maxCount;
            }
            maxCount++;
            return Math.max(getDepth(node.left, maxCount), getDepth(node.right, maxCount));
        }
    }
    
}
