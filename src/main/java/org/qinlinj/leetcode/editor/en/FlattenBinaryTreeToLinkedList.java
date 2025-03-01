package org.qinlinj.leetcode.editor.en;

import org.qinlinj.nonlinear.tree.binarysearchtree.TreeNode;

// [114] Flatten Binary Tree to Linked List
public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
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
        public void flatten(TreeNode root) {
            childrenFlatten(root);
        }

        public void childrenFlatten(TreeNode root) {
            if (root == null) {
                return;
            }

            childrenFlatten(root.left);
            childrenFlatten(root.right);
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = null;
            root.right = left;

            TreeNode p = root;
            while (p.right != null) {
                p = p.right;
            }
            p.right = right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}


//Given the root of a binary tree, flatten the tree into a "linked list":
//
// 
// The "linked list" should use the same TreeNode class where the right child 
//pointer points to the next node in the list and the left child pointer is always 
//null. 
// The "linked list" should be in the same order as a pre-order traversal of 
//the binary tree. 
// 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,2,5,3,4,null,6]
//Output: [1,null,2,null,3,null,4,null,5,null,6]
// 
//
// Example 2: 
//
// 
//Input: root = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: root = [0]
//Output: [0]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 2000]. 
// -100 <= Node.val <= 100 
// 
//
// 
//Follow up: Can you flatten the tree in-place (with 
//O(1) extra space)?
//
// Related TopicsLinked List | Stack | Tree | Depth-First Search | Binary Tree 
//
// 👍 12778, 👎 573bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
