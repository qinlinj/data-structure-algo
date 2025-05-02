package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

/**
 * Problem 1367: Linked List in Binary Tree
 * <p>
 * Description:
 * Given a binary tree root and a linked list with head as the first node,
 * return true if there is a path in the binary tree that represents the exact sequence
 * of values in the linked list (from head to tail).
 * The path must go downwards in the tree (i.e., only from parent to child nodes).
 * <p>
 * Key Concepts:
 * - Combines binary tree traversal with linked list matching
 * - Uses a nested traversal approach:
 * 1. Outer traversal to find potential starting nodes
 * 2. Inner traversal to check if the linked list can be embedded from that node
 * - Carefully handles null checks and path constraints
 * <p>
 * Time Complexity: O(N*min(L,H)), where:
 * - N is the number of nodes in the binary tree
 * - L is the length of the linked list
 * - H is the height of the binary tree
 * Space Complexity: O(H), where H is the height of the tree
 */
public class _423_i_LinkedListInBinaryTree {
    public boolean isSubPath(ListNode head, TreeNode root) {
        // Base case: empty linked list is always a path
        if (head == null) {
            return true;
        }

        // Base case: empty tree cannot contain any path
        if (root == null) {
            return false;
        }

        // Try to match the linked list starting from current node
        if (head.val == root.val && checkPath(head, root)) {
            return true;
        }

        // Try to find the path in left or right subtree
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    /**
     * Check if the linked list can be embedded starting from the current tree node
     *
     * @param head Current position in the linked list
     * @param root Current position in the binary tree
     * @return true if the linked list can be embedded from this point
     */
    private boolean checkPath(ListNode head, TreeNode root) {
        // If we've reached the end of the linked list, path is found
        if (head == null) {
            return true;
        }

        // If we've reached the end of the tree but not the list, path doesn't exist
        if (root == null) {
            return false;
        }

        // Current values must match
        if (head.val != root.val) {
            return false;
        }

        // Move to next node in linked list, and try both left and right children
        // Remember: path must go downwards, so we only go deeper in the tree
        return checkPath(head.next, root.left) || checkPath(head.next, root.right);
    }

    /**
     * Alternative implementation with more explicit traversal
     */
    public boolean isSubPathAlternative(ListNode head, TreeNode root) {
        // Check all possible starting points in the tree
        return dfs(root, head);
    }

    /**
     * DFS to find all possible starting positions in the tree
     */
    private boolean dfs(TreeNode root, ListNode head) {
        if (root == null) {
            return false;
        }

        // Try starting the path from current node
        if (match(root, head)) {
            return true;
        }

        // Try starting from children
        return dfs(root.left, head) || dfs(root.right, head);
    }

    /**
     * Check if the linked list matches a path starting from current tree node
     */
    private boolean match(TreeNode root, ListNode head) {
        // End of list - we've found the entire path
        if (head == null) {
            return true;
        }

        // End of tree path but list continues - no match
        if (root == null) {
            return false;
        }

        // Values must match to continue
        if (root.val != head.val) {
            return false;
        }

        // Continue matching down the tree and along the list
        return match(root.left, head.next) || match(root.right, head.next);
    }

    // Definition for a binary tree node
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

    // Definition for singly-linked list
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
