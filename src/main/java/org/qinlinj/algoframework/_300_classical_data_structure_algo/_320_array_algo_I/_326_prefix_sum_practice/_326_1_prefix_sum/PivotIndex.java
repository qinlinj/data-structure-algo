package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_1_prefix_sum;

/**
 * Find Pivot Index (LeetCode 724)
 * <p>
 * Key Points:
 * 1. Uses prefix sum technique to find the index where left sum equals right sum
 * 2. A pivot index is a position where the sum of all elements to the left equals the sum of all elements to the right
 * 3. Time complexity: O(n) - one pass to build prefix sum, one pass to find pivot
 * 4. Space complexity: O(n) for the prefix sum array
 * 5. Edge cases: leftmost and rightmost positions are handled by considering empty sides as sum 0
 * 6. This problem demonstrates how prefix sums can be used to quickly compare sums of different parts of an array
 */
public class PivotIndex {
    /**
     * Finds the leftmost pivot index in the array
     * A pivot index has the property that the sum of elements to its left
     * equals the sum of elements to its right
     *
     * @param nums Input array
     * @return The leftmost pivot index, or -1 if no pivot exists
     */
    public int pivotIndex(int[] nums) {
        int n = nums.length;

        // Create and populate prefix sum array
        // preSum[i] represents the sum of nums[0] through nums[i-1]
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // Check each position to find a pivot index
        for (int i = 1; i <= n; i++) {
            // Calculate sum of elements to the left and right of the current position
            int leftSum = preSum[i - 1] - preSum[0];         // Sum of nums[0] to nums[i-2]
            int rightSum = preSum[n] - preSum[i];            // Sum of nums[i] to nums[n-1]

            if (leftSum == rightSum) {
                // Found pivot index (convert back to 0-indexed)
                return i - 1;
            }
        }

        // No pivot index found
        return -1;
    }

    /**
     * Alternative implementation with improved space complexity
     * Uses a single pass approach without storing the entire prefix sum array
     *
     * @param nums Input array
     * @return The leftmost pivot index, or -1 if no pivot exists
     */
    public int pivotIndexOptimized(int[] nums) {
        int total = 0;
        int leftSum = 0;

        // Calculate total sum
        for (int num : nums) {
            total += num;
        }

        // Check each position by comparing leftSum with remaining sum
        for (int i = 0; i < nums.length; i++) {
            // At position i, rightSum = total - leftSum - nums[i]
            // If leftSum == rightSum, then leftSum == total - leftSum - nums[i]
            if (leftSum == total - leftSum - nums[i]) {
                return i;
            }

            // Update leftSum for the next position
            leftSum += nums[i];
        }

        return -1;
    }
}
