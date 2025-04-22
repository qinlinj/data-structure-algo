package org.qinlinj.algoframework._200_core_framework._230_algo_complexity_analysis._233_recursive_algorithm_analysis;

/**
 * Recursive Algorithm Analysis
 * <p>
 * This class demonstrates how to analyze the time and space complexity
 * of recursive algorithms by viewing them as tree traversals.
 * <p>
 * Key Principles:
 * <p>
 * 1. Complexity Analysis Framework:
 * - Time Complexity = Number of Recursive Calls × Time Complexity of Each Function Call
 * - Space Complexity = Recursion Stack Depth + Additional Storage Space
 * <p>
 * Or more intuitively:
 * - Time Complexity = Number of Nodes in Recursion Tree × Time Complexity at Each Node
 * - Space Complexity = Height of Recursion Tree + Additional Storage Space
 * <p>
 * 2. Dynamic Programming Analysis:
 * - For brute force recursion: exponential time complexity (e.g., O(K^N))
 * - With memoization: complexity reduces to O(States × Time per State)
 * - Bottom-up iteration: same time complexity as memoization but better space efficiency
 * <p>
 * 3. Backtracking Algorithm Analysis:
 * - Permutation problems: approximately O(N^2 × N!) time complexity
 * - Subset (power set) problems: approximately O(N × 2^N) time complexity
 * - Analysis focuses on counting recursion tree nodes and work at each node
 */
public class RecursiveAlgorithmAnalysis {
    /**
     * SECTION 1: DYNAMIC PROGRAMMING EXAMPLES
     */

    /**
     * Example 1: Coin Change Problem - Brute Force Recursion
     * <p>
     * Time Complexity: O(K^(N+1)) where:
     * - K is the number of coin denominations
     * - N is the target amount
     * <p>
     * Space Complexity: O(N) for recursion stack
     */
    public static int coinChangeBruteForce(int[] coins, int amount) {
        // Base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        // Time complexity O(K) where K is the number of coin denominations
        for (int coin : coins) {
            int subProblem = coinChangeBruteForce(coins, amount - coin);
            if (subProblem == -1) continue;
            res = Math.min(res, subProblem + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * Example 2: Coin Change Problem - Memoization Optimization
     * <p>
     * Time Complexity: O(N × K) where:
     * - N is the number of states (target amount)
     * - K is the time to compute each state (number of coins)
     * <p>
     * Space Complexity: O(N) for recursion stack and memoization array
     */
    public static int coinChangeMemoization(int[] coins, int amount) {
        // Memoization array, space complexity O(N)
        int[] memo = new int[amount + 1];
        // Initialize with a value that indicates "not calculated yet"
        for (int i = 0; i <= amount; i++) {
            memo[i] = -666;
        }

        return coinChangeMemoHelper(coins, amount, memo);
    }

    private static int coinChangeMemoHelper(int[] coins, int amount, int[] memo) {
        // Base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // Check memo to avoid duplicate calculations
        if (memo[amount] != -666) {
            return memo[amount];
        }

        int res = Integer.MAX_VALUE;
        // Time complexity O(K)
        for (int coin : coins) {
            int subProblem = coinChangeMemoHelper(coins, amount - coin, memo);
            if (subProblem == -1) continue;
            res = Math.min(res, subProblem + 1);
        }

        // Store result in memo
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }

    /**
     * Example 3: Coin Change Problem - Bottom-up DP
     * <p>
     * Time Complexity: O(N × K) - same as memoization approach
     * <p>
     * Space Complexity: O(N) for dp array, but no recursion stack overhead
     */
    public static int coinChangeBottomUp(int[] coins, int amount) {
        // DP array, space complexity O(N)
        int[] dp = new int[amount + 1];

        // Fill with a large value (amount + 1 works as "infinity")
        for (int i = 0; i <= amount; i++) {
            dp[i] = amount + 1;
        }

        // Base case
        dp[0] = 0;

        // Bottom-up calculation
        // Time complexity: O(N × K)
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    /**
     * SECTION 2: BACKTRACKING EXAMPLES
     */

    /**
     * Example 1: Permutation Problem (No Duplicates, No Reuse)
     * <p>
     * Time Complexity: O(N^2 × N!) where:
     * - Each node takes O(N) time
     * - There are approximately N! × N nodes in the recursion tree
     * <p>
     * Space Complexity: O(N) for recursion stack + O(N × N!) for results
     */
    public static java.util.List<java.util.List<Integer>> permute(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.LinkedList<Integer> track = new java.util.LinkedList<>();
        boolean[] used = new boolean[nums.length];

        permuteBacktrack(nums, track, used, result);
        return result;
    }

    private static void permuteBacktrack(
            int[] nums,
            java.util.LinkedList<Integer> track,
            boolean[] used,
            java.util.List<java.util.List<Integer>> result) {

        // Reached leaf node, collect result (time: O(N) to copy the list)
        if (track.size() == nums.length) {
            result.add(new java.util.ArrayList<>(track));
            return;
        }

        // For each position, try each number (time: O(N) for the loop)
        for (int i = 0; i < nums.length; i++) {
            // Skip used numbers
            if (used[i]) continue;

            // Make choice
            used[i] = true;
            track.addLast(nums[i]);

            // Recurse
            permuteBacktrack(nums, track, used, result);

            // Undo choice (backtrack)
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Example 2: Subset Problem (Power Set)
     * <p>
     * Time Complexity: O(N × 2^N) where:
     * - Each node takes O(N) time
     * - There are 2^N nodes in the recursion tree
     * <p>
     * Space Complexity: O(N) for recursion stack + O(N × 2^N) for results
     */
    public static java.util.List<java.util.List<Integer>> subsets(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.LinkedList<Integer> track = new java.util.LinkedList<>();

        subsetsBacktrack(nums, 0, track, result);
        return result;
    }

    private static void subsetsBacktrack(
            int[] nums,
            int start,
            java.util.LinkedList<Integer> track,
            java.util.List<java.util.List<Integer>> result) {

        // Add current combination to result (time: O(N) to copy the list)
        result.add(new java.util.ArrayList<>(track));

        // Try adding each remaining element (time: O(N) for the loop)
        for (int i = start; i < nums.length; i++) {
            // Make choice
            track.addLast(nums[i]);

            // Recurse with next starting position
            subsetsBacktrack(nums, i + 1, track, result);

            // Undo choice (backtrack)
            track.removeLast();
        }
    }

    /**
     * SECTION 3: VISUALIZATION OF RECURSION TREES
     */

    /**
     * Visual representation of a permutation recursion tree for [1,2,3]
     * <p>
     * This method doesn't actually run, it's just to illustrate the structure
     * of the recursion tree discussed in the analysis.
     */
    public static void visualizePermutationTree() {
        /*
         * For nums = [1,2,3], the recursion tree looks like:
         *
         *                     []
         *          /           |           \
         *       [1]           [2]           [3]
         *      /   \         /   \         /   \
         *   [1,2] [1,3]   [2,1] [2,3]   [3,1] [3,2]
         *     |     |       |     |       |     |
         * [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]
         *
         * Tree properties:
         * - Level 0: C(3,0) = 1 node
         * - Level 1: P(3,1) = 3 nodes
         * - Level 2: P(3,2) = 6 nodes
         * - Level 3: P(3,3) = 6 nodes
         * - Total nodes: approximately N! × N = 3! × 3 = 18 nodes
         * - Each node performs O(N) work
         * - Total time complexity: O(N^2 × N!) = O(3^2 × 3!) = O(9 × 6) = O(54)
         */
    }

    /**
     * Visual representation of a subset recursion tree for [1,2,3]
     * <p>
     * This method doesn't actually run, it's just to illustrate the structure
     * of the recursion tree discussed in the analysis.
     */
    public static void visualizeSubsetTree() {
        /*
         * For nums = [1,2,3], the recursion tree looks like:
         *
         *                     []
         *          /           |           \
         *       [1]           [2]           [3]
         *      /   \           |
         *   [1,2]  [1,3]     [2,3]
         *     |
         * [1,2,3]
         *
         * Tree properties:
         * - Level 0: C(3,0) = 1 node
         * - Level 1: C(3,1) = 3 nodes
         * - Level 2: C(3,2) = 3 nodes
         * - Level 3: C(3,3) = 1 node
         * - Total nodes: 2^N = 2^3 = 8 nodes (equals the number of possible subsets)
         * - Each node performs O(N) work
         * - Total time complexity: O(N × 2^N) = O(3 × 2^3) = O(3 × 8) = O(24)
         */
    }

    /**
     * SECTION 4: TIME AND SPACE COMPLEXITY CALCULATIONS
     */

    /**
     * Demonstrates how to calculate time complexity for a recursive function
     */
    public static void timeComplexityCalculation() {
        /*
         * For a recursive algorithm:
         * Time Complexity = Number of Recursive Calls × Complexity of Each Call
         *
         * Example: Permutation backtracking
         * - Each recursive call takes O(N) time
         * - Total number of calls is approximately N! × N
         * - Therefore, time complexity is O(N^2 × N!)
         *
         * Example: Subset generation
         * - Each recursive call takes O(N) time
         * - Total number of calls is 2^N (one for each possible subset)
         * - Therefore, time complexity is O(N × 2^N)
         */
    }

    /**
     * Demonstrates how to calculate space complexity for a recursive function
     */
    public static void spaceComplexityCalculation() {
        /*
         * For a recursive algorithm:
         * Space Complexity = Recursion Stack Depth + Additional Storage
         *
         * Example: Permutation backtracking
         * - Recursion stack depth is O(N)
         * - Additional storage for results is O(N × N!)
         * - Therefore, space complexity is O(N × N!)
         *
         * Example: Subset generation
         * - Recursion stack depth is O(N)
         * - Additional storage for results is O(N × 2^N)
         * - Therefore, space complexity is O(N × 2^N)
         */
    }
}
