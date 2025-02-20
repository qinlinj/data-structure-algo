package org.qinlinj.nonlinear.tree.binarytree.practice;

import org.qinlinj.nonlinear.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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

    // recursion version 2
    class Solution2 {
        public int maxDepth1(TreeNode root) {
            if (root == null) return 0;
            int leftMaxDepth = maxDepth1(root.left);
            int rightMaxDepth = maxDepth1(root.right);
            int maxDepth = Math.max(leftMaxDepth, rightMaxDepth) + 1;
            return maxDepth;
        }
    }

    class Solution3 {
        // BFS
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int maxDepth = 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curr = queue.poll();
                    if (curr.left != null) queue.offer(curr.left);
                    if (curr.right != null) queue.offer(curr.right);
                }
                maxDepth++;
            }
            return maxDepth;
        }
    }
}
