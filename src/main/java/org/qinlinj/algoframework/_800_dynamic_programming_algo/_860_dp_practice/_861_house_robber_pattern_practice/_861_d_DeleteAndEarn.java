package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._861_house_robber_pattern_practice;

// ==================== Problem 4: Delete and Earn ====================
class _861_d_DeleteAndEarn {
    public static void main(String[] args) {
        _861_d_DeleteAndEarn solution = new _861_d_DeleteAndEarn();

        // Test case 1
        int[] nums1 = {3, 4, 2};
        System.out.println("Test 1 - Expected: 6, Got: " + solution.deleteAndEarn(nums1));

        // Test case 2
        int[] nums2 = {2, 2, 3, 3, 3, 4};
        System.out.println("Test 2 - Expected: 9, Got: " + solution.deleteAndEarn(nums2));
    }

    /**
     * LeetCode 740: Delete and Earn
     * <p>
     * Problem: Given array nums, when you pick nums[i], you earn nums[i] points
     * but must delete all nums[i]-1 and nums[i]+1. Find maximum points.
     * <p>
     * Key Insight: Transform to House Robber problem
     * 1. Create points array where points[i] = sum of all occurrences of value i
     * 2. Apply House Robber logic: can't pick adjacent values
     * <p>
     * Pattern: Transform problem to fit known pattern
     */

    public int deleteAndEarn(int[] nums) {
        // Step 1: Transform to points array
        int[] points = new int[10001];
        for (int num : nums) {
            points[num] += num;
        }

        // Step 2: Apply House Robber algorithm
        return rob(points);
    }

    private int rob(int[] nums) {
        int n = nums.length;
        int dp_i_2 = 0; // dp[i+2]
        int dp_i_1 = 0; // dp[i+1]
        int dp_i = 0;   // dp[i]

        for (int i = n - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }

        return dp_i;
    }
}