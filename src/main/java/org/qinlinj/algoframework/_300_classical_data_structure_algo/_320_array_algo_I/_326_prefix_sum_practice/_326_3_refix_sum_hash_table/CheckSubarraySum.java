package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_3_refix_sum_hash_table;

/**
 * Continuous Subarray Sum (LeetCode 523)
 * <p>
 * Key Points:
 * 1. Combines prefix sum technique with hash map for modular arithmetic
 * 2. Uses property: if (preSum[i] - preSum[j]) % k = 0, then preSum[i] % k = preSum[j] % k
 * 3. Stores remainder of prefix sum divided by k in hash map with its index
 * 4. Time complexity: O(n) - single pass through the array
 * 5. Space complexity: O(min(n, k)) for the hash map
 * 6. Special case handling for k = 0 and length requirement of at least 2
 * 7. Demonstrates how prefix sums can be used with modular arithmetic
 */
public class CheckSubarraySum {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        CheckSubarraySum solution = new CheckSubarraySum();

        // Example 1: [23, 2, 4, 6, 7], k = 6
        int[] nums1 = {23, 2, 4, 6, 7};
        int k1 = 6;
        System.out.println("Example 1: " + solution.checkSubarraySum(nums1, k1));  // Expected: true
        System.out.println("Example 1 (optimized): " + solution.checkSubarraySumOptimized(nums1, k1));  // Expected: true

        // Example 2: [23, 2, 6, 4, 7], k = 6
        int[] nums2 = {23, 2, 6, 4, 7};
        int k2 = 6;
        System.out.println("Example 2: " + solution.checkSubarraySum(nums2, k2));  // Expected: true
        System.out.println("Example 2 (optimized): " + solution.checkSubarraySumOptimized(nums2, k2));  // Expected: true

        // Example 3: [23, 2, 6, 4, 7], k = 13
        int[] nums3 = {23, 2, 6, 4, 7};
        int k3 = 13;
        System.out.println("Example 3: " + solution.checkSubarraySum(nums3, k3));  // Expected: false
        System.out.println("Example 3 (optimized): " + solution.checkSubarraySumOptimized(nums3, k3));  // Expected: false
    }

    /**
     * Checks if there exists a subarray of length at least 2 with a sum that is a multiple of k
     *
     * @param nums Array of integers
     * @param k    The divisor
     * @return True if a valid subarray exists, false otherwise
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;

        // Calculate prefix sum array
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // Map to store remainder -> index
        // We only need to keep track of the first occurrence of each remainder
        java.util.HashMap<Integer, Integer> valToIndex = new java.util.HashMap<>();

        for (int i = 0; i < preSum.length; i++) {
            // Calculate remainder when divided by k
            int val = k == 0 ? preSum[i] : preSum[i] % k;

            // If this remainder hasn't been seen before, record its index
            if (!valToIndex.containsKey(val)) {
                valToIndex.put(val, i);
            }
            // If we've seen this remainder before and the distance between indices is at least 2
            else if (i - valToIndex.get(val) >= 2) {
                return true;
            }
            // Otherwise, we keep the original index (to maximize the potential subarray length)
        }

        return false;
    }

    /**
     * Optimized version that eliminates the prefix sum array
     * Uses a running sum variable instead
     */
    public boolean checkSubarraySumOptimized(int[] nums, int k) {
        // Edge case: if array has fewer than 2 elements
        if (nums.length < 2) {
            return false;
        }

        // Map to store remainder -> index
        java.util.HashMap<Integer, Integer> remainderToIndex = new java.util.HashMap<>();
        // Initialize with remainder 0 at index -1 to handle cases starting from index 0
        remainderToIndex.put(0, -1);

        int runningSum = 0;

        for (int i = 0; i < nums.length; i++) {
            // Update running sum
            runningSum += nums[i];

            // Calculate the remainder when divided by k
            int remainder = k == 0 ? runningSum : runningSum % k;

            // If we've seen this remainder before
            if (remainderToIndex.containsKey(remainder)) {
                // Check if the distance between indices is at least 2
                if (i - remainderToIndex.get(remainder) >= 2) {
                    return true;
                }
            } else {
                // First occurrence of this remainder
                remainderToIndex.put(remainder, i);
            }
        }

        return false;
    }

    /**
     * Solution explanation:
     *
     * 1. The key insight is based on modular arithmetic:
     *    - If (preSum[i] - preSum[j]) % k = 0, then preSum[i] % k = preSum[j] % k
     *    - This means that if we find two prefix sums with the same remainder when divided by k,
     *      the subarray between these positions has a sum that is a multiple of k
     *
     * 2. We use a hash map to track the first occurrence of each remainder (preSum[i] % k)
     *
     * 3. For each position i, we check if we've seen the same remainder before at position j,
     *    and verify that i - j >= 2 to ensure the subarray length is at least 2
     *
     * 4. The optimized approach uses a running sum instead of a prefix sum array and
     *    initializes the map with remainder 0 at index -1 to handle subarrays starting from index 0
     *
     * 5. Time complexity is O(n) because we make a single pass through the array
     *    Space complexity is O(min(n, k)) because there are at most k possible remainders
     */
}