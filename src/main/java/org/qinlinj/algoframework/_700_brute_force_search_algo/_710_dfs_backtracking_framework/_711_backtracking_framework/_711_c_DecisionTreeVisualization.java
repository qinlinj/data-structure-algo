package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._711_backtracking_framework;

import java.util.*;

public class _711_c_DecisionTreeVisualization {
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
}
