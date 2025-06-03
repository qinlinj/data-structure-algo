package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

/**
 * LeetCode 718. Maximum Length of Repeated Subarray - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given two integer arrays nums1 and nums2, return the maximum length of a
 * subarray that appears in both arrays.
 * <p>
 * KEY CONCEPTS:
 * 1. 2D Dynamic Programming for common prefix/suffix problems
 * 2. State Definition: dp[i][j] = length of common subarray ending at nums1[i] and nums2[j]
 * 3. Key insight: This is about finding longest common "subarray" (contiguous),
 * not "subsequence" (non-contiguous)
 * 4. State Transition:
 * - If nums1[i] == nums2[j]: dp[i][j] = dp[i-1][j-1] + 1
 * - Else: dp[i][j] = 0 (subarray must be contiguous)
 * 5. Optimization: Can reduce space from O(mn) to O(min(m,n))
 * <p>
 * TIME COMPLEXITY: O(m × n) where m,n are array lengths
 * SPACE COMPLEXITY: O(m × n) for 2D DP, O(min(m,n)) for optimized version
 * <p>
 * EXAMPLE:
 * nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Common subarray: [3,2,1] with length 3
 */

public class _863_f_LongestCommonSubarray {

    public static void main(String[] args) {
        _863_f_LongestCommonSubarray solution = new _863_f_LongestCommonSubarray();

        System.out.println("=== Longest Common Subarray Tests ===");

        // Test case 1
        int[] nums1_1 = {1, 2, 3, 2, 1};
        int[] nums2_1 = {3, 2, 1, 4, 7};

        System.out.println("Test Case 1:");
        System.out.print("nums1: ");
        printArray(nums1_1);
        System.out.print("nums2: ");
        printArray(nums2_1);

        int result1 = solution.findLength(nums1_1, nums2_1);
        int result1_opt = solution.findLengthOptimized(nums1_1, nums2_1);
        int result1_alt = solution.findLengthAlternative(nums1_1, nums2_1);

        System.out.printf("Result (2D DP): %d\n", result1);
        System.out.printf("Result (Optimized): %d\n", result1_opt);
        System.out.printf("Result (Alternative): %d\n", result1_alt);
        System.out.println("Explanation: Common subarray [3,2,1] has length 3\n");

        // Test case 2
        int[] nums1_2 = {0, 0, 0, 0, 0};
        int[] nums2_2 = {0, 0, 0, 0, 0};

        System.out.println("Test Case 2:");
        System.out.print("nums1: ");
        printArray(nums1_2);
        System.out.print("nums2: ");
        printArray(nums2_2);

        int result2 = solution.findLength(nums1_2, nums2_2);
        System.out.printf("Result: %d\n", result2);
        System.out.println("Explanation: Both arrays are identical, so entire length is common\n");

        // Test case 3 - No common subarray
        int[] nums1_3 = {1, 2, 3};
        int[] nums2_3 = {4, 5, 6};

        System.out.println("Test Case 3:");
        System.out.print("nums1: ");
        printArray(nums1_3);
        System.out.print("nums2: ");
        printArray(nums2_3);

        int result3 = solution.findLength(nums1_3, nums2_3);
        System.out.printf("Result: %d\n", result3);
        System.out.println("Explanation: No common elements, so length is 0\n");

        // DP state visualization
        System.out.println("=== DP State Visualization ===");
        System.out.println("For nums1=[1,2,3,2,1], nums2=[3,2,1,4,7]:");
        System.out.println("DP table (dp[i][j] = common subarray starting at i,j):");
        System.out.println("    3 2 1 4 7");
        System.out.println("1 [ 0 0 1 0 0 ]");
        System.out.println("2 [ 0 1 0 0 0 ]");
        System.out.println("3 [ 1 0 0 0 0 ]");
        System.out.println("2 [ 0 2 0 0 0 ]");
        System.out.println("1 [ 0 0 3 0 0 ]");
        System.out.println();
        System.out.println("Maximum value: 3 (at position [4][2])");
        System.out.println("This corresponds to subarray [3,2,1] starting at nums1[2] and nums2[0]");

        // Algorithm comparison
        System.out.println("\n=== Algorithm Comparison ===");
        System.out.println("1. Brute Force: O(n³) - check all pairs of starting positions");
        System.out.println("2. 2D DP: O(n²) time, O(n²) space - bottom-up approach");
        System.out.println("3. Optimized DP: O(n²) time, O(min(m,n)) space - rolling array");
        System.out.println("4. All approaches handle the 'contiguous' constraint correctly");
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }

    // Approach 1: Standard 2D DP with bottom-up traversal
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // dp[i][j] represents the length of common subarray
        // starting from nums1[i] and nums2[j]
        int[][] dp = new int[m][n];
        int maxLength = 0;

        // Fill base cases for last row and last column
        for (int j = 0; j < n; j++) {
            dp[m - 1][j] = nums1[m - 1] == nums2[j] ? 1 : 0;
            maxLength = Math.max(maxLength, dp[m - 1][j]);
        }

        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = nums1[i] == nums2[n - 1] ? 1 : 0;
            maxLength = Math.max(maxLength, dp[i][n - 1]);
        }

        // Fill DP table from bottom-right to top-left
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = 0; // Subarray must be contiguous
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength;
    }

    // Approach 2: Space-optimized using 1D array
    public int findLengthOptimized(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // Make nums1 the shorter array for space optimization
        if (m > n) {
            return findLengthOptimized(nums2, nums1);
        }

        int[] dp = new int[m + 1];
        int maxLength = 0;

        for (int j = 0; j < n; j++) {
            for (int i = m - 1; i >= 0; i--) {
                if (nums1[i] == nums2[j]) {
                    dp[i] = dp[i + 1] + 1;
                } else {
                    dp[i] = 0;
                }
                maxLength = Math.max(maxLength, dp[i]);
            }
        }

        return maxLength;
    }

    // Approach 3: Alternative DP definition (ending at current position)
    public int findLengthAlternative(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // dp[i][j] = length of common subarray ending at nums1[i-1] and nums2[j-1]
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
                // No else clause needed as dp array is initialized to 0
            }
        }

        return maxLength;
    }
}