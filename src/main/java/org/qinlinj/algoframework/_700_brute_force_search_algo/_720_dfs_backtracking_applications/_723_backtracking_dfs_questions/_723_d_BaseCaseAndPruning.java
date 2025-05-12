package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._723_backtracking_dfs_questions; /**
 * BASE CASE AND PRUNING PLACEMENT IN RECURSIVE FUNCTIONS
 * <p>
 * This class discusses where to place base cases and pruning logic
 * in recursive functions, with examples and best practices.
 * <p>
 * Key recommendations:
 * 1. Place base cases at the beginning of the function for clarity
 * 2. Place pruning logic before making choices in backtracking for efficiency
 * 3. Be consistent in your approach to make code more readable
 */

import java.util.*;

public class _723_d_BaseCaseAndPruning {

    /**
     * Example 1: Standard binary tree traversal
     * Base case at the beginning
     */
    public static void traverseStandard(TreeNode root) {
        // Base case at the beginning
        if (root == null) {
            return;
        }

        // Pre-order position
        System.out.println(root.val);

        // Traverse left and right subtrees
        traverseStandard(root.left);
        // In-order position
        traverseStandard(root.right);
        // Post-order position
    }

    /**
     * Example 2: Alternative binary tree traversal
     * Condition checks before each recursive call
     */
    public static void traverseAlternative(TreeNode root) {
        // No base case at function start

        // Check before each recursive call
        if (root != null && root.left != null) {
            traverseAlternative(root.left);
        }

        if (root != null && root.right != null) {
            traverseAlternative(root.right);
        }
    }

    /**
     * Example 3: Backtracking with pruning
     * Pruning logic inside the for loop, before making choices
     */
    public static void backtrackWithPruning(int[] nums, int start, List<Integer> track, List<List<Integer>> result) {
        // Base case
//        if (/* reached leaf node condition */) {
//            result.add(new ArrayList<>(track));
//            return;
//        }
//
//        for (int i = start; i < nums.length; i++) {
//            // Pruning logic before making choices
//            if (/* pruning condition */) {
//                // Skip this choice
//                continue;
//            }
//
//            // Make choice
//            track.add(nums[i]);
//
//            // Recursive call
//            backtrackWithPruning(nums, i + 1, track, result);
//
//            // Undo choice
//            track.remove(track.size() - 1);
//        }
    }

    /**
     * Example of a practical backtracking problem (combination sum)
     * with pruning logic placed before making choices
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(candidates, 0, target, track, result);
        return result;
    }

    private static void backtrack(int[] candidates, int start, int target,
                                  LinkedList<Integer> track, List<List<Integer>> result) {
        // Base case: target reached
        if (target == 0) {
            result.add(new ArrayList<>(track));
            return;
        }

        // Base case: target exceeded (pruning at function beginning)
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Pruning logic example (e.g., skip duplicates)
            // if (i > start && candidates[i] == candidates[i-1]) continue;

            // Make choice
            track.add(candidates[i]);

            // Recursive call (allowing reuse of current element)
            backtrack(candidates, i, target - candidates[i], track, result);

            // Undo choice
            track.removeLast();
        }
    }

    /**
     * Main method with examples and explanations
     */
    public static void main(String[] args) {
        System.out.println("BASE CASE AND PRUNING PLACEMENT");
        System.out.println("===============================");

        System.out.println("\nStandard Binary Tree Traversal:");
        System.out.println("void traverse(TreeNode root) {");
        System.out.println("    // Base case at the beginning");
        System.out.println("    if (root == null) {");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println("    // Pre-order position");
        System.out.println("    traverse(root.left);");
        System.out.println("    // In-order position");
        System.out.println("    traverse(root.right);");
        System.out.println("    // Post-order position");
        System.out.println("}");

        System.out.println("\nAlternative Binary Tree Traversal:");
        System.out.println("void traverse(TreeNode root) {");
        System.out.println("    // No base case at function start");
        System.out.println("    if (root != null && root.left != null) {");
        System.out.println("        traverse(root.left);");
        System.out.println("    }");
        System.out.println("    if (root != null && root.right != null) {");
        System.out.println("        traverse(root.right);");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\nBacktracking with Pruning:");
        System.out.println("void backtrack(...) {");
        System.out.println("    // Base case");
        System.out.println("    if (reached the leaf node) {");
        System.out.println("        // At leaf node, end recursion");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println();
        System.out.println("    for (int i = 0; i < n; i++) {");
        System.out.println("        // Pruning logic");
        System.out.println("        if (...) {");
        System.out.println("            // Skip this choice");
        System.out.println("            continue;");
        System.out.println("        }");
        System.out.println();
        System.out.println("        // Make choice");
        System.out.println("        ...");
        System.out.println();
        System.out.println("        backtrack(...)");
        System.out.println();
        System.out.println("        // Undo choice");
        System.out.println("        ...");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\nRecommendations for Base Case and Pruning Placement:");
        System.out.println("1. Base cases should be at the beginning of the function");
        System.out.println("   - Makes the termination conditions clear");
        System.out.println("   - Separates base cases from the core recursive logic");

        System.out.println("\n2. Pruning in backtracking should be before making choices");
        System.out.println("   - Prunes branches early to avoid unnecessary recursion");
        System.out.println("   - Keeps the logic of 'invalid choices' separate from the choice mechanism");

        System.out.println("\n3. Be consistent in your approach");
        System.out.println("   - Helps with code readability");
        System.out.println("   - Makes pre/in/post-order positions clearer in traversal algorithms");

        System.out.println("\n4. Alternative approaches can work too");
        System.out.println("   - As long as the solution is correct");
        System.out.println("   - Personal coding style preferences are acceptable");
        System.out.println("   - The most important thing is clarity and correctness");
    }

    /**
     * Tree node class for examples
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}