package org.qinlinj.nonlinear.tree.binarytree.practice;

import java.util.LinkedList;
import java.util.Queue;

public class _101_SymmetricTree {
    // recursion
    class Solution1 {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return compareTwoTree(root.left, root.right);
        }

        public boolean compareTwoTree(TreeNode node1, TreeNode node2) {
            if (node1 == null && node2 == null) {
                return true;
            }
            if (node1 == null || node2 == null) {
                return false;
            }

            if (node1.val != node2.val) {
                return false;
            }

            return (compareTwoTree(node1.left, node2.right) && compareTwoTree(node1.right, node2.left));
        }
    }

    //---------------------------------
    // BFS
    class Solution2 {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode t1 = queue.poll();
                TreeNode t2 = queue.poll();
                if (t1 == null && t2 == null)
                    continue;
                if (t1 == null || t2 == null)
                    return false;
                if (t1.val != t2.val)
                    return false;
                queue.offer(t1.left);
                queue.offer(t2.right);
                queue.offer(t1.right);
                queue.offer(t2.left);
            }
            return true;
        }
    }
}
