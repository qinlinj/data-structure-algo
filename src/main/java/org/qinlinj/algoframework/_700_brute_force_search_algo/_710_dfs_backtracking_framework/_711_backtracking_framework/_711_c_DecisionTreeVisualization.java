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
}
