package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._815_dp_vs_backtracking_conversion;

import java.util.*;

/**
 * Comparison and Analysis of Recursive Problem-Solving Approaches
 * <p>
 * Summary:
 * - This class provides a comprehensive analysis comparing the two main recursive problem-solving approaches:
 * 1. Traversal (Backtracking)
 * 2. Problem Decomposition (Dynamic Programming)
 * <p>
 * - Key insights from comparing these approaches:
 * - Both approaches can solve the same problems but differ in implementation and efficiency
 * - Traversal approach is more suitable for finding all possible solutions
 * - Problem decomposition is better for optimization problems with overlapping subproblems
 * - The choice between approaches depends on:
 * * The specific problem characteristics
 * * Whether we need one solution or all solutions
 * * The degree of overlapping subproblems
 * <p>
 * - Performance considerations:
 * - Backtracking: Time complexity typically O(2^n) without optimization
 * - DP with memoization: Can reduce to polynomial time for problems with many overlapping subproblems
 * - For combinatorial problems requiring all solutions, the theoretical complexity remains exponential,
 * but practical performance may vary based on implementation details
 * <p>
 * - When to use each approach:
 * - Backtracking: When exploring all possibilities, especially for problems requiring all solutions
 * - DP: When optimal substructure and overlapping subproblems are present, especially for optimization
 */
public class _815_d_RecursiveApproachesComparison {

    /**
     * Demonstrates the differences between traversal and problem decomposition
     * approaches on a simple example: generating all subsets of a set
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        System.out.println("Recursive Problem-Solving Approaches Comparison");
        System.out.println("================================================");

        System.out.println("Problem: Generate all subsets of [1, 2, 3]");
        System.out.println();

        System.out.println("Approach 1: Traversal (Backtracking)");
        List<List<Integer>> backtrackingResult = generateSubsetsBacktracking(nums);
        System.out.println("Result: " + backtrackingResult);
        System.out.println();

        System.out.println("Approach 2: Problem Decomposition (DP)");
        List<List<Integer>> dpResult = generateSubsetsDP(nums);
        System.out.println("Result: " + dpResult);
        System.out.println();

        System.out.println("Analysis:");
        compareApproaches();
    }

    /**
     * Generate all subsets using backtracking approach
     */
    public static List<List<Integer>> generateSubsetsBacktracking(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(nums, 0, current, result);
        return result;
    }

    private static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // Add the current subset to the result
        result.add(new ArrayList<>(current));

        // Try including each remaining element
        for (int i = start; i < nums.length; i++) {
            // Include nums[i]
            current.add(nums[i]);
            // Explore further with nums[i] included
            backtrack(nums, i + 1, current, result);
            // Exclude nums[i] (backtrack)
            current.remove(current.size() - 1);
        }
    }

    /**
     * Generate all subsets using problem decomposition (DP) approach
     */
    public static List<List<Integer>> generateSubsetsDP(int[] nums) {
        return dp(nums, 0);
    }

    private static List<List<Integer>> dp(int[] nums, int index) {
        List<List<Integer>> result = new ArrayList<>();

        // Base case: no more elements to consider
        if (index == nums.length) {
            // Empty subset
            result.add(new ArrayList<>());
            return result;
        }

        // Get subsets from the rest of the array
        List<List<Integer>> subsets = dp(nums, index + 1);

        // Create new result by:
        // 1. Including all subsets from the recursive call
        result.addAll(new ArrayList<>(subsets));

        // 2. Including all subsets with the current element added
        for (List<Integer> subset : subsets) {
            List<Integer> newSubset = new ArrayList<>(subset);
            newSubset.add(0, nums[index]);
            result.add(newSubset);
        }

        return result;
    }

    /**
     * Performance comparison between the two approaches
     */
    private static void compareApproaches() {
        System.out.println("1. Algorithm Structure:");
        System.out.println("   - Backtracking: Uses a single function that tracks the current state");
        System.out.println("     and recursively builds solutions by making/undoing choices.");
        System.out.println("   - DP: Uses a function that returns solutions for subproblems,");
        System.out.println("     which are combined to solve larger problems.");
        System.out.println();

        System.out.println("2. Memory Usage:");
        System.out.println("   - Backtracking: Maintains a single path in memory at any time,");
        System.out.println("     with space complexity typically proportional to the recursion depth.");
        System.out.println("   - DP with memoization: Stores results for all subproblems,");
        System.out.println("     potentially using more memory but avoiding redundant calculations.");
        System.out.println();

        System.out.println("3. Time Complexity Analysis:");
        System.out.println("   - Backtracking without pruning: O(2^n) for problems with binary choices");
        System.out.println("   - DP with memoization: Can reduce to polynomial time if there are many");
        System.out.println("     overlapping subproblems, but remains exponential for problems requiring");
        System.out.println("     all possible combinations.");
        System.out.println();

        System.out.println("4. When to Use Each Approach:");
        System.out.println("   - Backtracking is preferable when:");
        System.out.println("     * You need to find all possible solutions");
        System.out.println("     * The problem has constraints that allow early pruning");
        System.out.println("     * You need to maintain a specific path through the solution space");
        System.out.println();
        System.out.println("   - Dynamic Programming is preferable when:");
        System.out.println("     * The problem exhibits optimal substructure");
        System.out.println("     * There are many overlapping subproblems");
        System.out.println("     * You need to find an optimal solution rather than all solutions");
        System.out.println("     * The state space can be clearly defined with a small number of parameters");
        System.out.println();

        System.out.println("5. Practical Considerations:");
        System.out.println("   - For Word Break I (finding if a segmentation exists):");
        System.out.println("     DP is clearly superior due to extensive overlapping subproblems");
        System.out.println();
        System.out.println("   - For Word Break II (finding all possible segmentations):");
        System.out.println("     Both approaches have theoretical exponential complexity");
        System.out.println("     Backtracking may be more intuitive and potentially more efficient");
        System.out.println("     for problems with few valid solutions");
        System.out.println();

        System.out.println("6. Code Readability and Maintainability:");
        System.out.println("   - Backtracking often follows a standard template that's consistent");
        System.out.println("     across different problems");
        System.out.println("   - DP requires careful definition of the state space and transition logic,");
        System.out.println("     which can vary significantly between problems");
    }

    /**
     * Example of a combined approach that uses backtracking with memoization
     * to optimize performance for problems with overlapping subproblems
     */
    public static class HybridApproach {
        // Track current path
        private List<Integer> currentPath = new ArrayList<>();
        // Results collection
        private List<List<Integer>> results = new ArrayList<>();
        // Memoization for problem states that have already been explored
        private Map<String, List<List<Integer>>> memo = new HashMap<>();

        /**
         * Generate all subsets using a hybrid approach
         */
        public List<List<Integer>> generateSubsets(int[] nums) {
            backtrackWithMemo(nums, 0);
            return results;
        }

        private List<List<Integer>> backtrackWithMemo(int[] nums, int start) {
            // Create a key for the current state
            String key = start + ":" + currentPath.toString();

            // Check if we've already computed this state
            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            // Store results from this state
            List<List<Integer>> stateResults = new ArrayList<>();

            // Add the current subset
            stateResults.add(new ArrayList<>(currentPath));

            // Try including each remaining element
            for (int i = start; i < nums.length; i++) {
                // Include nums[i]
                currentPath.add(nums[i]);

                // Explore further with nums[i] included
                // The results from recursive calls are not immediately used
                // but they are stored in the global results list
                backtrackWithMemo(nums, i + 1);

                // Exclude nums[i] (backtrack)
                currentPath.remove(currentPath.size() - 1);
            }

            // Store in memo
            memo.put(key, stateResults);
            return stateResults;
        }
    }
}
