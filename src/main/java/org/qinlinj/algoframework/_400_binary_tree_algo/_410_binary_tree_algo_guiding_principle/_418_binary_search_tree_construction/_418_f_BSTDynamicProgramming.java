package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._418_binary_search_tree_construction;

/**
 * Binary Search Tree Theory - Dynamic Programming Approach
 * <p>
 * This class focuses on the application of dynamic programming to BST problems,
 * specifically for the unique BST counting problem.
 * <p>
 * Key insights about dynamic programming in BST problems:
 * 1. Identifying overlapping subproblems:
 * - In BST counting/generation, the subproblems are defined by ranges [lo, hi]
 * - Many ranges are recomputed multiple times in a naive recursive approach
 * <p>
 * 2. Memoization implementation:
 * - Using a 2D table where memo[lo][hi] stores the count for range [lo, hi]
 * - This reduces time complexity from exponential to O(n²)
 * <p>
 * 3. Base case importance:
 * - Empty ranges (lo > hi) must return 1, not 0
 * - This is crucial because an empty subtree is still a valid configuration
 * <p>
 * 4. Time and space complexity analysis:
 * - Time: O(n²) with memoization (vs exponential without it)
 * - Space: O(n²) for the memoization table
 */
public class _418_f_BSTDynamicProgramming {

    public static void main(String[] args) {
        _418_f_BSTDynamicProgramming dp = new _418_f_BSTDynamicProgramming();

        // Compare different DP approaches for small values
        System.out.println("Comparing different approaches:");
        System.out.println("\nn\tRecursive+Memo\tBottom-Up\tCatalan Formula");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + "\t" + dp.numTrees(i) + "\t\t" +
                    dp.numTreesBottomUp(i) + "\t\t" +
                    dp.numTreesUsingCatalanFormula(i));
        }

        // Demonstrate memoization for n = 4
        System.out.println("\nDetailed memoization trace for n = 4:");
        dp.explainMemoization(4);

        // Time performance comparison
        System.out.println("\nTime performance comparison for n = 15:");

        long startTime = System.nanoTime();
        int result1 = dp.numTrees(15);
        long endTime = System.nanoTime();
        System.out.println("Recursive+Memo: " + result1 + " (" +
                (endTime - startTime) / 1000000 + " ms)");

        startTime = System.nanoTime();
        int result2 = dp.numTreesBottomUp(15);
        endTime = System.nanoTime();
        System.out.println("Bottom-Up DP: " + result2 + " (" +
                (endTime - startTime) / 1000000 + " ms)");

        startTime = System.nanoTime();
        int result3 = dp.numTreesUsingCatalanFormula(15);
        endTime = System.nanoTime();
        System.out.println("Catalan Formula: " + result3 + " (" +
                (endTime - startTime) / 1000000 + " ms)");
    }

    /**
     * Solution for counting unique BSTs using dynamic programming
     * with detailed steps and explanations
     */
    public int numTrees(int n) {
        // Initialize memoization table
        int[][] memo = new int[n + 1][n + 1];
        return countBSTs(1, n, memo);
    }

    /**
     * Recursive function with memoization to count BSTs
     */
    private int countBSTs(int lo, int hi, int[][] memo) {
        // Base case: empty range represents null node (1 possibility)
        if (lo > hi) {
            return 1;
        }

        // Check memoization table to avoid redundant calculations
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int count = 0;
        // Try each value as root node
        for (int root = lo; root <= hi; root++) {
            // Count possible left subtrees with values < root
            int leftCount = countBSTs(lo, root - 1, memo);

            // Count possible right subtrees with values > root
            int rightCount = countBSTs(root + 1, hi, memo);

            // Total trees with this root = left count × right count
            count += leftCount * rightCount;
        }

        // Save result in memo table
        memo[lo][hi] = count;
        return count;
    }

    /**
     * Alternative bottom-up dynamic programming approach
     * This avoids recursion entirely
     */
    public int numTreesBottomUp(int n) {
        // dp[i] = number of unique BSTs with i nodes
        int[] dp = new int[n + 1];

        // Base case: empty tree (0 nodes) has 1 configuration
        dp[0] = 1;

        // Build solutions from small to large
        for (int i = 1; i <= n; i++) {
            // Calculate number of BSTs with i nodes
            for (int root = 1; root <= i; root++) {
                // For a root at position 'root':
                // - Left subtree contains (root-1) nodes
                // - Right subtree contains (i-root) nodes
                dp[i] += dp[root - 1] * dp[i - root];
            }
        }

        return dp[n];
    }

    /**
     * Explanation of memoization table growth
     * This method demonstrates how the memoization table gets filled
     */
    public void explainMemoization(int n) {
        int[][] memo = new int[n + 1][n + 1];

        // First, manually compute and display a few key values
        System.out.println("Manual computation of key subproblems:");
        System.out.println("[1,1]: 1 (single node tree)");
        System.out.println("[2,2]: 1 (single node tree)");
        System.out.println("[1,2]: 2 (two possible trees with nodes 1,2)");

        // Then show the process of building up larger solutions
        System.out.println("\nComputing count for n = " + n + ":");
        int result = countBSTsWithTracing(1, n, memo);

        // Display filled memoization table
        System.out.println("\nFinal memoization table:");
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (memo[i][j] > 0) {
                    System.out.println("[" + i + "," + j + "]: " + memo[i][j]);
                }
            }
        }

        System.out.println("\nFinal result: " + result);
    }

    /**
     * Version of countBSTs that traces its execution
     */
    private int countBSTsWithTracing(int lo, int hi, int[][] memo) {
        if (lo > hi) {
            System.out.println("  Base case: range [" + lo + "," + hi + "] -> 1");
            return 1;
        }

        if (memo[lo][hi] != 0) {
            System.out.println("  Memo hit: range [" + lo + "," + hi + "] -> " + memo[lo][hi]);
            return memo[lo][hi];
        }

        System.out.println("Computing range [" + lo + "," + hi + "]:");

        int count = 0;
        for (int root = lo; root <= hi; root++) {
            System.out.println("  Root = " + root + ":");

            int leftCount = countBSTsWithTracing(lo, root - 1, memo);
            int rightCount = countBSTsWithTracing(root + 1, hi, memo);

            System.out.println("    Left [" + lo + "," + (root - 1) + "] = " + leftCount);
            System.out.println("    Right [" + (root + 1) + "," + hi + "] = " + rightCount);
            System.out.println("    Combinations: " + leftCount + " × " + rightCount + " = " + (leftCount * rightCount));

            count += leftCount * rightCount;
        }

        System.out.println("  Total for [" + lo + "," + hi + "] = " + count);

        memo[lo][hi] = count;
        return count;
    }

    /**
     * Mathematical perspective: Catalan Numbers
     * The number of unique BSTs with n nodes is the nth Catalan number
     */
    public int numTreesUsingCatalanFormula(int n) {
        // Calculate C(n) = (2n)! / ((n+1)! * n!)

        // To avoid overflow, calculate using the recursive formula:
        // C(n) = C(n-1) * 2(2n-1) / (n+1)

        long catalan = 1; // C(0) = 1

        for (int i = 1; i <= n; i++) {
            catalan = catalan * 2 * (2 * i - 1) / (i + 1);
        }

        return (int) catalan;
    }
}