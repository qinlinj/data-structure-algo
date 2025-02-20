package org.qinlinj.nonlinear.tree.binarytree.practice;

import java.util.LinkedList;
import java.util.Queue;

public class _100_SameTree {
    //-------------------------------------------
    // BFS using iteration
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1.val != node2.val) return false;
            TreeNode left1 = node1.left, right1 = node1.right;
            TreeNode left2 = node2.left, right2 = node2.right;
            if (left1 == null ^ left2 == null) return false;
            if (right1 == null ^ right2 == null) return false;

            if (left1 != null) queue1.offer(left1);
            if (right1 != null) queue1.offer(right1);
            if (left2 != null) queue2.offer(left2);
            if (right2 != null) queue2.offer(right2);
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }

    class Solution1 {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null ^ q == null) {
                return false;
            }
            if (p == null && q == null) {
                return true;
            }
            if (p.val != q.val) {
                return false;
            }
            if ((p.left != null ^ q.left != null) || (p.right != null ^ q.right != null)) {
                return false;
            } else {
                if (p.left != null && q.left != null) {
                    if (!isSameTree(p.left, q.left)) {
                        return false;
                    }
                }
                if (p.right != null && q.right != null) {
                    if (!isSameTree(p.right, q.right)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    // ---------------------------------------
    // more concise version using recursion
    class Solution2 {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null || q == null) return false;
            if (p.val != q.val) return false;

            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
