package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._423_traversal_thinking_practice_III;

import java.util.*;

/**
 * Problem 1104: Path in Zigzag Labeled Binary Tree
 * <p>
 * Description:
 * In an infinite binary tree where every node has two children:
 * - The nodes on each level are labeled in a zigzag manner, where:
 * - Odd levels (1, 3, 5...) are labeled from left to right
 * - Even levels (2, 4, 6...) are labeled from right to left
 * Given a label, return the path from the root to the node with that label.
 * <p>
 * Key Concepts:
 * - Uses mathematical relationships in a complete binary tree
 * - Handles zigzag labeling by calculating the "mirror" position
 * - Understanding level ranges: level k contains 2^k to 2^(k+1)-1 nodes
 * - Using binary tree parent-child relationship: parent(node) = node/2
 * <p>
 * Time Complexity: O(log n), where n is the label value
 * Space Complexity: O(log n) for storing the path
 */
public class _423_e_PathInZigzagLabeledBinaryTree {
    public List<Integer> pathInZigZagTree(int label) {
        ArrayList<Integer> path = new ArrayList<>();

        // Start from the given label
        int currentLabel = label;

        // Add labels to path and work our way up to the root
        while (currentLabel >= 1) {
            // Add current label to path
            path.add(currentLabel);

            // Calculate parent label
            currentLabel = currentLabel / 2;

            // Calculate current depth (0-indexed)
            int depth = (int) (Math.log(currentLabel) / Math.log(2));

            // Get the value range for the current level
            int[] range = getLevelRange(depth);

            // If parent is on a zigzag level, adjust the parent value
            // This "mirrors" the parent on the zigzag level
            currentLabel = range[1] - (currentLabel - range[0]);
        }

        // Reverse path to get it from root to label
        Collections.reverse(path);

        return path;
    }

    /**
     * Get the range of values [min, max] for a given level in the binary tree
     *
     * @param level The level in the tree (0-indexed)
     * @return An array containing [min, max] values for that level
     */
    private int[] getLevelRange(int level) {
        // For level k (0-indexed):
        // - Minimum value: 2^k
        // - Maximum value: 2^(k+1) - 1
        int minValue = (int) Math.pow(2, level);
        int maxValue = (int) Math.pow(2, level + 1) - 1;

        return new int[]{minValue, maxValue};
    }

    /**
     * Alternative implementation with better clarity
     * Focuses on understanding the zigzag pattern and level computation
     */
    public List<Integer> pathInZigZagTreeAlternative(int label) {
        // Calculate the level of the given label (1-indexed)
        int level = (int) (Math.log(label) / Math.log(2)) + 1;

        LinkedList<Integer> result = new LinkedList<>();

        // Start from the given label
        while (label > 0) {
            result.addFirst(label);

            // Get the range for the current level
            int levelStart = (int) Math.pow(2, level - 1);
            int levelEnd = (int) Math.pow(2, level) - 1;

            // Calculate the position of the node from the right of its level
            int posFromRight = levelEnd - label;

            // Calculate the position of the parent from the left of its level
            int parentPosFromLeft = (posFromRight + 1) / 2;

            // Calculate the value of the parent
            // If parent level is even, we need to count from right to left
            level--;
            int parentLevelStart = (int) Math.pow(2, level - 1);
            int parentLevelEnd = (int) Math.pow(2, level) - 1;

            if (level % 2 == 0) {
                // Even level - labeled from right to left
                label = parentLevelEnd - parentPosFromLeft + 1;
            } else {
                // Odd level - labeled from left to right
                label = parentLevelStart + parentPosFromLeft - 1;
            }
        }

        return result;
    }
}
