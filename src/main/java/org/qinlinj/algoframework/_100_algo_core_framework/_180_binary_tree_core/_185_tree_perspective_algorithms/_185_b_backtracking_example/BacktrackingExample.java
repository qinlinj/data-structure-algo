package org.qinlinj.algoframework._100_algo_core_framework._180_binary_tree_core._185_tree_perspective_algorithms._185_b_backtracking_example;

import java.util.*;
import org.qinlinj.algoframework._100_algo_core_framework._180_binary_tree_core._185_tree_perspective_algorithms.TreeAlgorithmsComparison;

public class BacktrackingExample {

    // =====================================================
    // EXAMPLE 2: BACKTRACKING - FOCUS ON "BRANCHES"
    // =====================================================

    // Example 2A: Print the paths taken during tree traversal
    public void traverseWithBranchTracking(TreeAlgorithmsComparison.TreeNode root) {
        traverseWithBranchTracking(root, "Root");
    }

    private void traverseWithBranchTracking(TreeAlgorithmsComparison.TreeNode root, String path) {
        if (root == null) return;

        // Process left branch
        if (root.left != null) {
            // Make choice - enter the branch to left child
            System.out.println("Going from node " + root.val + " to node " + root.left.val);
            traverseWithBranchTracking(root.left, path + " -> " + root.left.val);
            // Unmake choice - exit the branch
            System.out.println("Returning from node " + root.left.val + " to node " + root.val);
        }

        // Process right branch
        if (root.right != null) {
            // Make choice - enter the branch to right child
            System.out.println("Going from node " + root.val + " to node " + root.right.val);
            traverseWithBranchTracking(root.right, path + " -> " + root.right.val);
            // Unmake choice - exit the branch
            System.out.println("Returning from node " + root.right.val + " to node " + root.val);
        }
    }

    // Example 2B: N-ary tree traversal with branch tracking
    public void traverseNaryWithBranchTracking(TreeAlgorithmsComparison.Node root) {
        if (root == null) return;

        for (TreeAlgorithmsComparison.Node child : root.children) {
            // Make choice - enter this branch
            System.out.println("Going from node " + root.val + " to node " + child.val);
            traverseNaryWithBranchTracking(child);
            // Unmake choice - exit this branch
            System.out.println("Returning from node " + child.val + " to node " + root.val);
        }
    }

    // Example 2C: Classic backtracking - permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(nums, track, used, result);
        return result;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used, List<List<Integer>> result) {
        // Base case: found a valid permutation
        if (track.size() == nums.length) {
            result.add(new ArrayList<>(track));
            return;
        }

        // Try all possible next elements
        for (int i = 0; i < nums.length; i++) {
            // Skip used elements
            if (used[i]) continue;

            // Make choice - add this element to permutation
            used[i] = true;
            track.add(nums[i]);
            System.out.println("Added " + nums[i] + " to permutation: " + track);

            // Explore further with this choice
            backtrack(nums, track, used, result);

            // Unmake choice - remove this element from permutation
            track.removeLast();
            used[i] = false;
            System.out.println("Removed " + nums[i] + " from permutation: " + track);
        }
    }
}
