package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._431_postorder_position_practice_I;

import java.util.*;

/**
 * Problem 508: Most Frequent Subtree Sum
 * <p>
 * Description:
 * Given the root of a binary tree, return the most frequent subtree sum.
 * If there is a tie, return all the values with the highest frequency in any order.
 * <p>
 * The subtree sum of a node is defined as the sum of all the node values formed
 * by the subtree rooted at that node (including the node itself).
 * <p>
 * Key Concepts:
 * - Uses post-order traversal to calculate subtree sums
 * - Maintains a frequency map for all subtree sums
 * - Demonstrates effective use of post-order traversal position for calculations
 * - Shows how to track and compute frequencies in a recursive process
 * <p>
 * Time Complexity: O(n), where n is the number of nodes in the tree
 * Space Complexity: O(n) for the hash map and result array
 */
public class _431_b_MostFrequentSubtreeSum {
    // Map to track frequency of each subtree sum
    private HashMap<Integer, Integer> sumFrequencyMap = new HashMap<>();

    /**
     * Main function to find the most frequent subtree sum(s)
     *
     * @param root Root of the binary tree
     * @return Array of most frequent subtree sums
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        // Calculate subtree sums and populate the frequency map
        calculateSubtreeSum(root);

        // Find the maximum frequency
        int maxFrequency = 0;
        for (int frequency : sumFrequencyMap.values()) {
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        // Collect all sums with the maximum frequency
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumFrequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                result.add(entry.getKey());
            }
        }

        // Convert list to array
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }

    /**
     * Helper function to calculate subtree sum and update frequency map
     *
     * @param node Root of the current subtree
     * @return Sum of all values in the subtree
     */
    private int calculateSubtreeSum(TreeNode node) {
        // Base case: null node contributes 0 to sum
        if (node == null) {
            return 0;
        }

        // Calculate sum of left and right subtrees
        int leftSum = calculateSubtreeSum(node.left);
        int rightSum = calculateSubtreeSum(node.right);

        // Calculate total sum of current subtree
        int currentSum = node.val + leftSum + rightSum;

        // Post-order position: update frequency map
        sumFrequencyMap.put(currentSum, sumFrequencyMap.getOrDefault(currentSum, 0) + 1);

        return currentSum;
    }

    /**
     * Alternative implementation with separate frequency counting
     */
    public int[] findFrequentTreeSumAlternative(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        // First pass: calculate all subtree sums
        Map<TreeNode, Integer> nodeSums = new HashMap<>();
        calculateNodeSums(root, nodeSums);

        // Second pass: count frequencies
        Map<Integer, Integer> frequencies = new HashMap<>();
        int maxFrequency = 0;

        for (int sum : nodeSums.values()) {
            int frequency = frequencies.getOrDefault(sum, 0) + 1;
            frequencies.put(sum, frequency);
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        // Collect results
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                result.add(entry.getKey());
            }
        }

        // Convert to array
        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Helper function for alternative implementation
     */
    private int calculateNodeSums(TreeNode node, Map<TreeNode, Integer> nodeSums) {
        if (node == null) {
            return 0;
        }

        int leftSum = calculateNodeSums(node.left, nodeSums);
        int rightSum = calculateNodeSums(node.right, nodeSums);
        int totalSum = node.val + leftSum + rightSum;

        nodeSums.put(node, totalSum);
        return totalSum;
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
