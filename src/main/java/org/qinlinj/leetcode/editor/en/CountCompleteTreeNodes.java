package org.qinlinj.leetcode.editor.en;

import org.qinlinj.nonlinear.tree.binarytree.TreeNode;

// [222] Count Complete Tree Nodes
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        Solution solution = new CountCompleteTreeNodes().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public int countNodes(TreeNode root) {
            TreeNode lc = root;
            TreeNode rc = root;
            int left = 0;
            int right = 0;
            while (lc != null) {
                lc = lc.left;
                left++;
            }
            while (rc != null) {
                rc = rc.right;
                right++;
            }
            if (left == right) {
                return (int) (Math.pow(2, left)) - 1;
            }
            return countNodesR(root);
        }

        public int countNodesR(TreeNode root) {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}


//Given the root of a complete binary tree, return the number of the nodes in
//the tree. 
//
// According to Wikipedia, every level, except possibly the last, is completely 
//filled in a complete binary tree, and all nodes in the last level are as far 
//left as possible. It can have between 1 and 2ʰ nodes inclusive at the last level h.
// 
//
// Design an algorithm that runs in less than O(n) time complexity. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,2,3,4,5,6]
//Output: 6
// 
//
// Example 2: 
//
// 
//Input: root = []
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: root = [1]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 5 * 10⁴]. 
// 0 <= Node.val <= 5 * 10⁴ 
// The tree is guaranteed to be complete. 
// 
//
// Related TopicsBinary Search | Bit Manipulation | Tree | Binary Tree 
//
// 👍 8965, 👎 557bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
