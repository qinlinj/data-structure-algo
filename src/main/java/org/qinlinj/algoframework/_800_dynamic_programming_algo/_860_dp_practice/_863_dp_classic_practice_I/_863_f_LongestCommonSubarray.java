package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

public class _863_f_LongestCommonSubarray {
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
}
