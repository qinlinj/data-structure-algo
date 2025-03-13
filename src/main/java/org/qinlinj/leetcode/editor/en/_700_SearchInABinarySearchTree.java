package org.qinlinj.leetcode.editor.en;

// [700] Search in a Binary Search Tree
public class _700_SearchInABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new _700_SearchInABinarySearchTree().new Solution();
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
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            if (root.val == val) {
                return root;
            } else if (root.val < val) {
                return searchBST(root.right, val);
            } else {
                return searchBST(root.left, val);
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


//You are given the root of a binary search tree (BST) and an integer val.
//
// Find the node in the BST that the node's value equals val and return the 
//subtree rooted with that node. If such a node does not exist, return null. 
//
// 
// Example 1: 
// 
// 
//Input: root = [4,2,7,1,3], val = 2
//Output: [2,1,3]
// 
//
// Example 2: 
// 
// 
//Input: root = [4,2,7,1,3], val = 5
//Output: []
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 5000]. 
// 1 <= Node.val <= 10⁷ 
// root is a binary search tree. 
// 1 <= val <= 10⁷ 
// 
//
// Related TopicsTree | Binary Search Tree | Binary Tree 
//
// 👍 6152, 👎 199bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
