package org.qinlinj.nonlinear.tree.binarytree.practice;

public class _100_SameTree {
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

    //-------------------------------------------
}
