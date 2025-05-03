package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._437_binary_search_tree_classic_practice_II; /**
 * Problem 1305: All Elements in Two Binary Search Trees (Medium)
 * <p>
 * Problem Description:
 * Given two binary search trees root1 and root2, return a list containing all the integers
 * from both trees sorted in ascending order.
 * <p>
 * Key Concepts:
 * - BST Iterator: Using an iterator to traverse BSTs in sorted order
 * - Merge Operation: Similar to merging sorted arrays or linked lists
 * - Lazy Evaluation: Processing elements only when needed
 * - Space Optimization: Avoiding full conversion to arrays
 * <p>
 * Two Approaches:
 * 1. Simple: Perform inorder traversal on both trees, then merge the sorted arrays
 * 2. Optimal: Use BST iterators and merge on-the-fly
 * <p>
 * Time Complexity: O(n + m) where n and m are the sizes of the trees
 * Space Complexity: O(n + m) for storing all elements
 */

import java.util.*;

class _437_f_MergedBSTs {
    // Main method for testing
    public static void main(String[] args) {
        _437_f_MergedBSTs solution = new _437_f_MergedBSTs();

        // Example 1: root1 = [2,1,4], root2 = [1,0,3]
        TreeNode root1 = solution.new TreeNode(2);
        root1.left = solution.new TreeNode(1);
        root1.right = solution.new TreeNode(4);

        TreeNode root2 = solution.new TreeNode(1);
        root2.left = solution.new TreeNode(0);
        root2.right = solution.new TreeNode(3);

        System.out.println("Example 1:");
        System.out.println("Tree 1 Structure:");
        solution.printTree(root1, "", false);
        System.out.println("Tree 2 Structure:");
        solution.printTree(root2, "", false);
        System.out.println(Arrays.toString(solution.getAllElements(root1, root2).toArray()));
    }

    /**
     * Approach 1: Simple - Convert to lists then merge
     */
    public List<Integer> getAllElements_Simple(TreeNode root1, TreeNode root2) {
        // Perform inorder traversal on both trees to get sorted lists
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        inorderTraversal(root1, list1);
        inorderTraversal(root2, list2);

        // Merge the two sorted lists
        return mergeSortedLists(list1, list2);
    }

    /**
     * Helper method for inorder traversal
     */
    private void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    /**
     * Helper method to merge two sorted lists
     */
    private List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>(list1.size() + list2.size());
        int i = 0, j = 0;

        // Standard merge operation for sorted arrays
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                result.add(list1.get(i++));
            } else {
                result.add(list2.get(j++));
            }
        }

        // Add remaining elements from either list
        while (i < list1.size()) {
            result.add(list1.get(i++));
        }

        while (j < list2.size()) {
            result.add(list2.get(j++));
        }

        return result;
    }

    /**
     * Approach 2: Optimal - Use BST iterators
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // Create BST iterators for both trees
        BSTIterator iterator1 = new BSTIterator(root1);
        BSTIterator iterator2 = new BSTIterator(root2);

        List<Integer> result = new ArrayList<>();

        // Merge results from both iterators
        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (iterator1.peek() <= iterator2.peek()) {
                result.add(iterator1.next());
            } else {
                result.add(iterator2.next());
            }
        }

        // Add remaining elements from first tree
        while (iterator1.hasNext()) {
            result.add(iterator1.next());
        }

        // Add remaining elements from second tree
        while (iterator2.hasNext()) {
            result.add(iterator2.next());
        }

        return result;
    }

    /**
     * Helper method to create a BST from a sorted array
     */
    private TreeNode createBSTFromSortedArray(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = createBSTFromSortedArray(nums, start, mid - 1);
        root.right = createBSTFromSortedArray(nums, mid + 1, end);

        return root;
    }

    /**
     * Helper method to print the inorder traversal of a tree
     */
    private void printInorder(TreeNode root) {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    /**
     * Helper method to print tree structure
     */
    private void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "null");
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);

        printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
        printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
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

    /**
     * BST Iterator implementation with peek functionality
     */
    private class BSTIterator {
        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            pushLeftBranch(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode node = stack.pop();
            pushLeftBranch(node.right);
            return node.val;
        }

        // Peek at the next value without advancing
        public int peek() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator");
            }
            return stack.peek().val;
        }

        private void pushLeftBranch(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }
}