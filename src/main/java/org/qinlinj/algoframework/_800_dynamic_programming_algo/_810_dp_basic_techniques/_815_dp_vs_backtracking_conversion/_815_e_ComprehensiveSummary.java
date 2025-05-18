package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._815_dp_vs_backtracking_conversion;

import java.util.*;

/**
 * Comprehensive Summary of Recursive Problem-Solving Approaches
 * <p>
 * This class provides a complete summary of the concepts covered in the previous classes,
 * combining insights about traversal (backtracking) and problem decomposition (dynamic programming)
 * approaches to recursive problem-solving.
 * <p>
 * Key Concepts:
 * <p>
 * 1. Two Main Recursive Problem-Solving Approaches:
 * - Traversal (Backtracking): Explores the entire solution space by building solutions incrementally
 * and using recursion to explore branches of a conceptual search tree.
 * - Problem Decomposition (Dynamic Programming): Breaks down problems into overlapping subproblems
 * and builds solutions by combining results from smaller subproblems.
 * <p>
 * 2. Backtracking Characteristics:
 * - Uses a recursive function to explore all possible solutions
 * - Maintains a "path" or "track" of choices made
 * - Makes decisions at each step (choose/explore/unchoose)
 * - Time complexity is often exponential without optimization
 * - Well-suited for problems requiring all possible solutions
 * <p>
 * 3. Dynamic Programming Characteristics:
 * - Breaks problems into overlapping subproblems
 * - Uses memoization to avoid redundant calculations
 * - Often reduces exponential time complexity to polynomial
 * - Ideal for optimization problems with optimal substructure
 * <p>
 * 4. Overlapping Subproblems:
 * - Both approaches can use memoization to avoid redundant calculations
 * - Backtracking can use memoization to "prune" branches that won't lead to solutions
 * - DP uses memoization as a fundamental part of its approach
 * <p>
 * 5. Case Studies from Word Break Problems:
 * - Word Break I: Determine if a string can be segmented into words from a dictionary
 * * Both approaches work, but DP is more efficient due to many overlapping subproblems
 * - Word Break II: Find all possible ways to segment a string
 * * Both approaches have exponential complexity in worst case
 * * Backtracking is often more intuitive for collecting all solutions
 * <p>
 * 6. Selecting the Right Approach:
 * - Consider the problem characteristics
 * - Assess whether you need one solution or all possible solutions
 * - Evaluate the degree of overlapping subproblems
 * - Think about code readability and maintenance
 */
public class _815_e_ComprehensiveSummary {

    public static void main(String[] args) {
        System.out.println("Comprehensive Summary of Recursive Problem-Solving Approaches");
        System.out.println("===========================================================");

        // Example problem: Count the ways to climb n stairs, taking 1 or 2 steps at a time
        int n = 5;

        System.out.println("Problem: Count ways to climb " + n + " stairs (1 or 2 steps at a time)");
        System.out.println();

        // Backtracking approach (inefficient for this problem)
        System.out.println("Backtracking approach: " + countWaysBacktracking(n) + " ways");

        // DP approach with memoization
        System.out.println("DP with memoization: " + countWaysDPMemo(n) + " ways");

        // DP approach with tabulation
        System.out.println("DP with tabulation: " + countWaysDPTabulation(n) + " ways");

        System.out.println("\nApproach Selection Guidelines:");
        printApproachSelectionGuidelines();

        System.out.println("\nConclusion:");
        printConclusion();
    }

    /**
     * Backtracking approach to count ways to climb stairs
     * This approach is inefficient due to excessive redundant calculations
     */
    public static int countWaysBacktracking(int n) {
        // Base cases
        if (n == 0) return 1; // Found a valid way
        if (n < 0) return 0;  // Invalid path

        // Try taking 1 step
        int oneStep = countWaysBacktracking(n - 1);

        // Try taking 2 steps
        int twoSteps = countWaysBacktracking(n - 2);

        // Total ways = ways with 1 step + ways with 2 steps
        return oneStep + twoSteps;
    }

    /**
     * DP approach with memoization to count ways to climb stairs
     */
    public static int countWaysDPMemo(int n) {
        // Memoization array
        Integer[] memo = new Integer[n + 1];
        return dpMemoHelper(n, memo);
    }

    private static int dpMemoHelper(int n, Integer[] memo) {
        // Base cases
        if (n == 0) return 1;
        if (n < 0) return 0;

        // Check memo
        if (memo[n] != null) {
            return memo[n];
        }

        // Calculate result
        int result = dpMemoHelper(n - 1, memo) + dpMemoHelper(n - 2, memo);

        // Store in memo
        memo[n] = result;
        return result;
    }

    /**
     * DP approach with tabulation to count ways to climb stairs
     */
    public static int countWaysDPTabulation(int n) {
        // Handle base cases
        if (n <= 1) return 1;

        // Tabulation array
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1;
        dp[1] = 1;

        // Fill the tabulation array bottom-up
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Print guidelines for selecting the appropriate approach
     */
    private static void printApproachSelectionGuidelines() {
        System.out.println("1. Choose Backtracking when:");
        System.out.println("   - You need all possible solutions, not just an optimal one");
        System.out.println("   - The problem involves making sequential choices to build a solution");
        System.out.println("   - Early constraint validation can prune many branches");
        System.out.println("   - The solution space has a tree-like structure");
        System.out.println("   - Memory efficiency is more important than time efficiency");
        System.out.println();

        System.out.println("2. Choose Dynamic Programming when:");
        System.out.println("   - The problem has optimal substructure");
        System.out.println("   - There are many overlapping subproblems");
        System.out.println("   - You're looking for an optimal solution (max, min, count, etc.)");
        System.out.println("   - The problem can be defined with a clear state representation");
        System.out.println("   - Time efficiency is more important than memory usage");
        System.out.println();

        System.out.println("3. Consider a Hybrid Approach when:");
        System.out.println("   - You need all solutions but have significant overlapping subproblems");
        System.out.println("   - The problem requires both exploring all possibilities and optimizing calculations");
        System.out.println("   - The solution space is large but contains many redundant paths");
    }

    /**
     * Print concluding remarks
     */
    private static void printConclusion() {
        System.out.println("The Word Break problems demonstrate how the same problem can be approached");
        System.out.println("using different recursive paradigms. While both backtracking and dynamic");
        System.out.println("programming can solve these problems, the efficiency and implementation");
        System.out.println("complexity vary significantly.");
        System.out.println();
        System.out.println("For Word Break I (determining if segmentation is possible), the dynamic");
        System.out.println("programming approach with memoization clearly outperforms backtracking due");
        System.out.println("to the high number of overlapping subproblems.");
        System.out.println();
        System.out.println("For Word Break II (finding all possible segmentations), both approaches");
        System.out.println("have theoretical exponential complexity in the worst case. The backtracking");
        System.out.println("approach may be more intuitive for collecting all solutions, while the DP");
        System.out.println("approach with memoization eliminates some redundancy at the cost of more");
        System.out.println("complex implementation and higher memory usage.");
        System.out.println();
        System.out.println("Understanding both paradigms allows programmers to select the most appropriate");
        System.out.println("approach based on the specific characteristics of each problem, leading to");
        System.out.println("more efficient and elegant solutions.");
    }

    /**
     * Example class that demonstrates a problem where a hybrid approach might be beneficial
     */
    public static class HybridExample {
        private Map<String, Boolean> canFormMemo = new HashMap<>();
        private Map<String, List<List<String>>> allFormsMemo = new HashMap<>();

        /**
         * Determines if a string can be segmented using words from a dictionary
         * Uses memoization to avoid redundant calculations
         */
        public boolean canForm(String s, Set<String> wordDict) {
            if (s.isEmpty()) return true;

            if (canFormMemo.containsKey(s)) {
                return canFormMemo.get(s);
            }

            for (int i = 1; i <= s.length(); i++) {
                String prefix = s.substring(0, i);
                if (wordDict.contains(prefix) && canForm(s.substring(i), wordDict)) {
                    canFormMemo.put(s, true);
                    return true;
                }
            }

            canFormMemo.put(s, false);
            return false;
        }

        /**
         * Finds all ways a string can be segmented using words from a dictionary
         * Uses memoization to avoid redundant calculations
         */
        public List<List<String>> getAllForms(String s, Set<String> wordDict) {
            if (s.isEmpty()) {
                List<List<String>> result = new ArrayList<>();
                result.add(new ArrayList<>());
                return result;
            }

            if (allFormsMemo.containsKey(s)) {
                return allFormsMemo.get(s);
            }

            List<List<String>> result = new ArrayList<>();

            for (int i = 1; i <= s.length(); i++) {
                String prefix = s.substring(0, i);
                if (wordDict.contains(prefix)) {
                    List<List<String>> subProblems = getAllForms(s.substring(i), wordDict);

                    for (List<String> subProblem : subProblems) {
                        List<String> newForm = new ArrayList<>();
                        newForm.add(prefix);
                        newForm.addAll(subProblem);
                        result.add(newForm);
                    }
                }
            }

            allFormsMemo.put(s, result);
            return result;
        }
    }
}
