package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._711_backtracking_framework;

import java.util.*;

/**
 * DECISION TREE VISUALIZATION IN BACKTRACKING
 * ===========================================
 * <p>
 * Key Concepts:
 * 1. Backtracking algorithm traverses a decision tree
 * 2. Each node in the tree represents a state in the problem-solving process
 * 3. Each node has attributes:
 * - Path: Choices already made to reach this node
 * - Choice list: Available options at this node
 * <p>
 * Decision Tree Properties:
 * - Root: Starting point with empty path and all choices available
 * - Internal nodes: Partial solutions with some choices made
 * - Leaf nodes: Complete solutions (paths that satisfy end conditions)
 * <p>
 * Traversal Timing:
 * - "Pre-order" position: Before exploring a node's children (when making a choice)
 * - "Post-order" position: After exploring a node's children (when undoing a choice)
 * <p>
 * This class provides a visual representation of how the decision tree
 * for the permutation problem would look like, and how the backtracking
 * algorithm traverses this tree.
 */
public class _711_c_DecisionTreeVisualization {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _711_c_DecisionTreeVisualization visualization = new _711_c_DecisionTreeVisualization();
        visualization.visualizeDecisionTree();
    }

    /**
     * This method simulates the traversal of a decision tree for [1,2,3] permutation
     * with console output to visualize the process
     */
    public void visualizeDecisionTree() {
        int[] nums = {1, 2, 3};
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();

        System.out.println("Visualizing decision tree traversal for [1,2,3] permutation:");
        System.out.println("Root: Path=[], Available=[1,2,3]");

        backtrackWithVisualization(nums, path, used, 1);
    }

    /**
     * Backtracking with visualization to show the decision tree traversal
     *
     * @param nums  Input array
     * @param path  Current path
     * @param used  Used elements tracking
     * @param depth Current depth in the tree for indentation
     */
    private void backtrackWithVisualization(int[] nums, LinkedList<Integer> path,
                                            boolean[] used, int depth) {

        // If path contains all elements, we've reached a leaf node
        if (path.size() == nums.length) {
            printIndent(depth);
            System.out.println("Leaf Node Reached: " + path + " (Complete permutation)");
            return;
        }

        // Try each number as next in permutation
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            // Get current available choices for visualization
            List<Integer> available = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (!used[j]) available.add(nums[j]);
            }

            // Visualize the pre-order position (before making choice)
            printIndent(depth);
            System.out.println("At node: Path=" + path + ", Available=" + available);
            printIndent(depth);
            System.out.println("→ Choosing " + nums[i] + " from available options");

            // Make choice
            path.add(nums[i]);
            used[i] = true;

            // Recurse
            backtrackWithVisualization(nums, path, used, depth + 1);

            // Visualize the post-order position (after undoing choice)
            printIndent(depth);
            System.out.println("← Backtracking: Removing " + nums[i] + " from path " + path);

            // Undo choice
            path.removeLast();
            used[i] = false;
        }
    }

    /**
     * Helper method to print indentation based on depth
     */
    private void printIndent(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
    }

    // Helper class to simulate LinkedList for the example
    private static class LinkedList<E> extends ArrayList<E> {
        public void removeLast() {
            if (!isEmpty()) {
                remove(size() - 1);
            }
        }
    }
}
