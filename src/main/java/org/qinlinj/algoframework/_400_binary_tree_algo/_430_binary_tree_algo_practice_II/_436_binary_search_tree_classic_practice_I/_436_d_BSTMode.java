package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._436_binary_search_tree_classic_practice_I; /**
 * Problem 501: Find Mode in Binary Search Tree (Easy)
 * <p>
 * Problem Description:
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s)
 * (i.e., the most frequently occurring element) in it.
 * If the tree has more than one mode, return them in any order.
 * <p>
 * Key Concepts:
 * - BST Inorder Property: Inorder traversal of a BST produces values in sorted order
 * - Consecutive Equal Values: Equal values appear consecutively in inorder traversal
 * - Count Tracking: Maintain counts of current value and tracking maximum frequency
 * - Space Optimization: Can be solved without extra data structures by using inorder traversal
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n) for storing the modes (output size), O(h) for recursion stack
 */

import java.util.*;

class _436_d_BSTMode {
    // List to store modes (most frequent values)
    private List<Integer> modes = new ArrayList<>();
    // Reference to previous node in inorder traversal
    private TreeNode prev = null;
    // Count of current value
    private int currentCount = 0;
    // Maximum frequency seen so far
    private int maxCount = 0;

    // Main method for testing
    public static void main(String[] args) {
        _436_d_BSTMode solution = new _436_d_BSTMode();

        // Example 1: [1,null,2,2]
        TreeNode root1 = solution.new TreeNode(1);
        root1.right = solution.new TreeNode(2);
        root1.right.left = solution.new TreeNode(2);

        int[] result1 = solution.findMode(root1);
        System.out.print("Example 1 - Mode(s): ");
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();
        // Expected output: 2

        // Also test with alternative implementation
        int[] result1Alt = solution.findModeWithMap(root1);
        System.out.print("Alternative implementation result: ");
        for (int num : result1Alt) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Example 2: [0]
        TreeNode root2 = solution.new TreeNode(0);

        int[] result2 = solution.findMode(root2);
        System.out.print("\nExample 2 - Mode(s): ");
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();
        // Expected output: 0

        // Example 3: Create a tree with multiple modes [1,null,2,2,3,null,null,3]
        TreeNode root3 = solution.new TreeNode(1);
        root3.right = solution.new TreeNode(2);
        root3.right.left = solution.new TreeNode(2);
        root3.right.right = solution.new TreeNode(3);
        root3.right.right.left = solution.new TreeNode(3);

        int[] result3 = solution.findMode(root3);
        System.out.print("\nExample 3 - Mode(s): ");
        for (int num : result3) {
            System.out.print(num + " ");
        }
        System.out.println();
        // Expected output: 2 3 (in any order)
    }

    /**
     * Main function to find the mode(s) in a BST
     *
     * @param root The root of the binary search tree
     * @return An array containing all the modes of the tree
     */
    public int[] findMode(TreeNode root) {
        // Reset state variables
        modes.clear();
        prev = null;
        currentCount = 0;
        maxCount = 0;

        // Perform inorder traversal to find modes
        inorderTraversal(root);

        // Convert list to array
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }

        return result;
    }

    /**
     * Inorder traversal to find modes
     */
    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        // Visit left subtree
        inorderTraversal(root.left);

        // Process current node (inorder position)
        if (prev == null) {
            // First node in inorder traversal
            currentCount = 1;
            maxCount = 1;
            modes.add(root.val);
        } else {
            if (root.val == prev.val) {
                // Same value as previous node, increment count
                currentCount++;

                if (currentCount == maxCount) {
                    // Found another mode with same frequency
                    modes.add(root.val);
                } else if (currentCount > maxCount) {
                    // Found new mode with higher frequency
                    modes.clear();
                    maxCount = currentCount;
                    modes.add(root.val);
                }
            } else {
                // Different value, reset count
                currentCount = 1;

                if (currentCount == maxCount) {
                    // If single occurrence equals current max, it's also a mode
                    modes.add(root.val);
                }
            }
        }

        // Update prev pointer
        prev = root;

        // Visit right subtree
        inorderTraversal(root.right);
    }

    /**
     * Alternative implementation using HashMap for frequency counting
     */
    public int[] findModeWithMap(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        // Map to store frequency of each value
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Populate frequency map
        populateFrequencyMap(root, freqMap);

        // Find maximum frequency
        int maxFreq = 0;
        for (int freq : freqMap.values()) {
            maxFreq = Math.max(maxFreq, freq);
        }

        // Collect modes (values with maximum frequency)
        List<Integer> modesList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == maxFreq) {
                modesList.add(entry.getKey());
            }
        }

        // Convert list to array
        int[] result = new int[modesList.size()];
        for (int i = 0; i < modesList.size(); i++) {
            result[i] = modesList.get(i);
        }

        return result;
    }

    /**
     * Helper method to populate frequency map
     */
    private void populateFrequencyMap(TreeNode node, Map<Integer, Integer> freqMap) {
        if (node == null) {
            return;
        }

        // Increment frequency for current node's value
        freqMap.put(node.val, freqMap.getOrDefault(node.val, 0) + 1);

        // Process children
        populateFrequencyMap(node.left, freqMap);
        populateFrequencyMap(node.right, freqMap);
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
}