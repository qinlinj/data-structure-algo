package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._861_house_robber_pattern_practice;

// ==================== Problem 6: Largest Element After Merge ====================
class _861_f_LargestElementAfterMerge {
    public static void main(String[] args) {
        _861_f_LargestElementAfterMerge solution = new _861_f_LargestElementAfterMerge();

        // Test case 1
        int[] nums1 = {2, 3, 7, 9, 3};
        System.out.println("Test 1 - Expected: 21, Got: " + solution.maxArrayValue(nums1));

        // Test case 2
        int[] nums2 = {5, 3, 3};
        System.out.println("Test 2 - Expected: 11, Got: " + solution.maxArrayValue(nums2));

        // Test case 3 - edge case
        int[] nums3 = {1};
        System.out.println("Test 3 - Expected: 1, Got: " + solution.maxArrayValue(nums3));
    }

    /**
     * LeetCode 2789: Largest Element After Merge Operations
     * <p>
     * Problem: Given array, can merge nums[i] with nums[i+1] if nums[i] <= nums[i+1].
     * Merged element = nums[i] + nums[i+1]. Find maximum possible element.
     * <p>
     * Key Insight: Greedy approach - merge from right to left to maximize opportunities.
     * When we merge rightward, we might create new merge opportunities.
     * <p>
     * Pattern: Sometimes greedy beats DP when order of operations matters
     */

    public long maxArrayValue(int[] nums) {
        long maxValue = 0;
        int i = nums.length - 1;

        // Process from right to left
        while (i >= 0) {
            long blockSum = nums[i];

            // Merge all possible non-decreasing adjacent elements
            while (i > 0 && blockSum >= nums[i - 1]) {
                blockSum += nums[i - 1];
                i--;
            }

            // Update maximum value found
            maxValue = Math.max(maxValue, blockSum);
            i--;
        }

        return maxValue;
    }
}