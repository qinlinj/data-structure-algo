package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

public class _863_g_DynamicProgrammingSummary {
    public static void main(String[] args) {
        System.out.println("=== DYNAMIC PROGRAMMING CLASSIC PROBLEMS DEMONSTRATION ===\n");

        // Problem 1: Integer Break
        System.out.println("1. INTEGER BREAK PROBLEM");
        System.out.println("   Concept: Optimization DP with enumeration");
        _863_a_IntegerBreak integerBreak = new _863_a_IntegerBreak();
        System.out.printf("   Example: integerBreak(10) = %d\n", integerBreak.integerBreak(10));
        System.out.println("   Key insight: Choose between continuing to break vs keeping as-is\n");

        // Problem 2: Unique Paths with Obstacles
        System.out.println("2. UNIQUE PATHS WITH OBSTACLES");
        System.out.println("   Concept: Path counting DP with constraints");
        _863_b_UniquePathsWithObstacles uniquePaths = new _863_b_UniquePathsWithObstacles();
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.printf("   Example: uniquePaths(3x3 with center obstacle) = %d\n",
                uniquePaths.uniquePathsWithObstacles2D(grid));
        System.out.println("   Key insight: Handle obstacles while maintaining path counting logic\n");

        // Problem 3: Maximum Sum Divisible by Three
        System.out.println("3. MAXIMUM SUM DIVISIBLE BY THREE");
        System.out.println("   Concept: State-based DP with modular arithmetic");
        _863_c_MaxSumDivisibleByThree maxSum = new _863_c_MaxSumDivisibleByThree();
        int[] nums = {3, 6, 5, 1, 8};
        System.out.printf("   Example: maxSumDivThree([3,6,5,1,8]) = %d\n",
                maxSum.maxSumDivThree(nums));
        System.out.println("   Key insight: Track best sums for each remainder state (0,1,2)\n");

        // Problem 4: Triangle Minimum Path Sum
        System.out.println("4. TRIANGLE MINIMUM PATH SUM");
        System.out.println("   Concept: Path optimization with structural constraints");
        _863_d_TriangleMinimumPath triangle = new _863_d_TriangleMinimumPath();
        java.util.List<java.util.List<Integer>> triangleData = java.util.Arrays.asList(
                java.util.Arrays.asList(2),
                java.util.Arrays.asList(3, 4),
                java.util.Arrays.asList(6, 5, 7),
                java.util.Arrays.asList(4, 1, 8, 3)
        );
        System.out.printf("   Example: minimumTotal(triangle) = %d\n",
                triangle.minimumTotal(triangleData));
        System.out.println("   Key insight: Adjacent constraint limits movement options\n");

        // Problem 5: Largest Divisible Subset
        System.out.println("5. LARGEST DIVISIBLE SUBSET");
        System.out.println("   Concept: LIS variant with divisibility");
        _863_e_LargestDivisibleSubset divisible = new _863_e_LargestDivisibleSubset();
        int[] divisibleNums = {1, 2, 4, 8};
        System.out.printf("   Example: largestDivisibleSubset([1,2,4,8]) = %s\n",
                divisible.largestDivisibleSubset(divisibleNums));
        System.out.println("   Key insight: Sorting + transitivity makes subset→sequence transformation\n");

        // Problem 6: Longest Common Subarray
        System.out.println("6. LONGEST COMMON SUBARRAY");
        System.out.println("   Concept: 2D DP for sequence matching");
        _863_f_LongestCommonSubarray commonArray = new _863_f_LongestCommonSubarray();
        int[] arr1 = {1, 2, 3, 2, 1};
        int[] arr2 = {3, 2, 1, 4, 7};
        System.out.printf("   Example: findLength([1,2,3,2,1], [3,2,1,4,7]) = %d\n",
                commonArray.findLength(arr1, arr2));
        System.out.println("   Key insight: Contiguous requirement resets DP when mismatch occurs\n");

        // Summary of patterns
        System.out.println("=== DYNAMIC PROGRAMMING PATTERNS SUMMARY ===");
        System.out.println("• Optimization DP: Find optimal value (min/max)");
        System.out.println("• Counting DP: Count number of ways/paths");
        System.out.println("• State-based DP: Track multiple states simultaneously");
        System.out.println("• Sequence DP: Process arrays/strings with order dependency");
        System.out.println("• 2D DP: Handle two-dimensional relationships");
        System.out.println("• Space Optimization: Reduce memory using rolling arrays");
        System.out.println();
        System.out.println("Remember: DP = Optimal Substructure + Overlapping Subproblems");
        System.out.println("Always identify these properties before applying DP!");
    }
}
