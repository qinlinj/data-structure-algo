package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_3_refix_sum_hash_table;

/**
 * Subarray Sum Equals K (LeetCode 560)
 * <p>
 * Key Points:
 * 1. Combines prefix sum technique with hash map for counting subarrays
 * 2. Unlike sliding window, this approach handles arrays with negative numbers
 * 3. Uses a hash map to count occurrences of each prefix sum
 * 4. For each prefix sum, checks how many previous prefix sums would make a subarray with sum k
 * 5. Time complexity: O(n) - single pass through the array
 * 6. Space complexity: O(n) for the hash map
 * 7. Demonstrates the power of prefix sums for solving subarray sum problems
 */
public class SubarraySum {
    /**
     * Example usage
     */
    public static void main(String[] args) {
        SubarraySum solution = new SubarraySum();

        // Example 1: [1, 1, 1], k = 2
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println("Example 1: " + solution.subarraySum(nums1, k1));  // Expected: 2
        System.out.println("Example 1 (optimized): " + solution.subarraySumOptimized(nums1, k1));  // Expected: 2

        // Example 2: [1, 2, 3], k = 3
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println("Example 2: " + solution.subarraySum(nums2, k2));  // Expected: 2
        System.out.println("Example 2 (optimized): " + solution.subarraySumOptimized(nums2, k2));  // Expected: 2

        // Additional example with negative numbers: [1, -1, 1, 1, -1], k = 1
        int[] nums3 = {1, -1, 1, 1, -1};
        int k3 = 1;
        System.out.println("Example with negatives: " + solution.subarraySum(nums3, k3));  // Expected: 5
        System.out.println("Example with negatives (optimized): " + solution.subarraySumOptimized(nums3, k3));  // Expected: 5
    }

    /**
     * Counts the number of continuous subarrays that sum to k
     *
     * @param nums Array of integers (may contain negative numbers)
     * @param k    Target sum
     * @return Number of subarrays with sum k
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;

        // Prefix sum array
        int[] preSum = new int[n + 1];
        preSum[0] = 0;

        // Map to count occurrences of each prefix sum
        java.util.HashMap<Integer, Integer> count = new java.util.HashMap<>();
        count.put(0, 1);  // Empty subarray has sum 0

        int result = 0;

        // Calculate prefix sums and count subarrays
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];

            // For current sum preSum[i], find how many previous prefix sums were (preSum[i] - k)
            // Each such prefix sum creates a subarray with sum k
            int need = preSum[i] - k;

            if (count.containsKey(need)) {
                result += count.get(need);
            }

            // Update the count of current prefix sum
            count.put(preSum[i], count.getOrDefault(preSum[i], 0) + 1);
        }

        return result;
    }

    /**
     * Optimized version that eliminates the prefix sum array
     * Uses a running sum variable instead
     */
    public int subarraySumOptimized(int[] nums, int k) {
        // Map to count occurrences of each running sum
        java.util.HashMap<Integer, Integer> count = new java.util.HashMap<>();
        count.put(0, 1);  // Empty subarray has sum 0

        int result = 0;
        int sum = 0;

        for (int num : nums) {
            // Update running sum
            sum += num;

            // Check if there are any previous running sums that would create a subarray with sum k
            if (count.containsKey(sum - k)) {
                result += count.get(sum - k);
            }

            // Update the count of current running sum
            count.put(sum, count.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

    /**
     * Solution explanation:
     *
     * 1. Why not sliding window? Sliding window techniques typically assume that shrinking the window
     *    decreases the sum and expanding increases it. With negative numbers, this assumption doesn't hold.
     *
     * 2. The key insight: If the sum of subarray nums[i..j] is k, then:
     *    - preSum[j+1] - preSum[i] = k
     *    - preSum[i] = preSum[j+1] - k
     *
     * 3. For each position j, we're looking for how many previous positions i have
     *    prefix sums equal to (preSum[j+1] - k)
     *
     * 4. We use a hash map to track the count of each prefix sum we've seen so far
     *
     * 5. The optimized approach eliminates the prefix sum array by using a running sum variable
     *    and improves readability
     *
     * 6. This approach handles negative numbers correctly and finds all valid subarrays in a single pass
     */
}
