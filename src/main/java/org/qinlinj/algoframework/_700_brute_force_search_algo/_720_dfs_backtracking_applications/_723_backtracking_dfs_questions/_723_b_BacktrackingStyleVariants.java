package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._723_backtracking_dfs_questions; /**
 * BACKTRACKING STYLE VARIANTS
 * <p>
 * This class demonstrates and explains the two different coding styles
 * for implementing backtracking algorithms:
 * <p>
 * 1. Standard backtracking style: Making and undoing choices inside the for-loop
 * 2. DFS-style backtracking: Making and undoing choices outside the for-loop
 * <p>
 * The article discusses the differences between these styles and when each might be appropriate.
 */

import java.util.*;

public class _723_b_BacktrackingStyleVariants {

    /**
     * Standard backtracking framework template
     * Choices are made and undone inside the for-loop
     */
    public static void backtrackStandard(int[] nums, List<Integer> track, List<List<Integer>> result) {
//        if (/* reached leaf node condition */) {
//            // At leaf node, end recursion
//            result.add(new LinkedList<>(track));
//            return;
//        }

        for (int i = 0; i < nums.length; i++) {
            // Make choice
            track.add(nums[i]);

            // Recurse to next level
            backtrackStandard(nums, track, result);

            // Undo choice
//            track.removeLast();
        }
    }

    /**
     * DFS-style backtracking framework template
     * Choices are made before the for-loop and undone after it
     */
    public static void backtrackDFSStyle(int[] nums, List<Integer> track, List<List<Integer>> result) {
//        if (/* reached leaf node condition */) {
//            // At leaf node, end recursion
//            result.add(new LinkedList<>(track));
//            return;
//        }

        // Make choice
        // (This would be a decision at the node level)
//        track.add(/* some value */);

        for (int i = 0; i < nums.length; i++) {
            backtrackDFSStyle(nums, track, result);
        }

        // Undo choice
//        track.removeLast();
    }

    /**
     * Example implementation of permutation problem using standard backtracking
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        permuteBacktrack(nums, track, used, result);
        return result;
    }

    private static void permuteBacktrack(int[] nums, LinkedList<Integer> track,
                                         boolean[] used, List<List<Integer>> result) {
        // Base case: reached leaf node
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        // Standard backtracking framework
        for (int i = 0; i < nums.length; i++) {
            // Skip used elements
            if (used[i]) {
                continue;
            }

            // Make choice
            used[i] = true;
            track.addLast(nums[i]);

            // Recurse to next level
            permuteBacktrack(nums, track, used, result);

            // Undo choice
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Main method with examples
     */
    public static void main(String[] args) {
        System.out.println("BACKTRACKING STYLE VARIANTS");
        System.out.println("===========================");

        // Example using standard backtracking
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);

        System.out.println("\nPermutations using standard backtracking:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }

        System.out.println("\nExplanations of the two styles:");
        System.out.println("\nStandard Backtracking Framework:");
        System.out.println("void backtrack(...) {");
        System.out.println("    if (reached leaf node) {");
        System.out.println("        // At leaf node, end recursion");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println();
        System.out.println("    for (int i = 0; i < n; i++) {");
        System.out.println("        // Make choice");
        System.out.println("        ...");
        System.out.println();
        System.out.println("        backtrack(...)");
        System.out.println();
        System.out.println("        // Undo choice");
        System.out.println("        ...");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\nDFS-Style Backtracking Framework:");
        System.out.println("void dfs(...) {");
        System.out.println("    if (reached leaf node) {");
        System.out.println("        // At leaf node, end recursion");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println();
        System.out.println("    // Make choice");
        System.out.println("    ...");
        System.out.println();
        System.out.println("    for (int i = 0; i < n; i++) {");
        System.out.println("        dfs(...)");
        System.out.println("    }");
        System.out.println();
        System.out.println("    // Undo choice");
        System.out.println("    ...");
        System.out.println("}");

        System.out.println("\nKey differences:");
        System.out.println("1. Standard backtracking focuses on branches/edges (tree edges)");
        System.out.println("2. DFS-style focuses on nodes (tree nodes)");
        System.out.println("3. Functionally they're similar, but suit different problems");
        System.out.println("4. Standard backtracking is more appropriate for permutation/combination problems");
        System.out.println("   where choices are made on branches");
    }
}