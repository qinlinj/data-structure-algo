package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_3_refix_sum_hash_table;

/**
 * Contiguous Array (LeetCode 525)
 * <p>
 * Key Points:
 * 1. Combines prefix sum technique with hash map for optimal solution
 * 2. Treats 0's as -1 and 1's as 1 to transform the problem
 * 3. Finding subarrays with equal 0's and 1's becomes finding subarrays with sum 0
 * 4. Uses a hash map to track prefix sum values and their first occurrence indices
 * 5. Time complexity: O(n) - single pass through the array
 * 6. Space complexity: O(n) for the hash map and prefix sum array
 * 7. Demonstrates how problem transformation can simplify solution
 */
public class FindMaxLength {
    /**
     * Example usage
     */
    public static void main(String[] args) {
        FindMaxLength solution = new FindMaxLength();

        // Example 1: [0, 1]
        int[] nums1 = {0, 1};
        System.out.println("Example 1: " + solution.findMaxLength(nums1));  // Expected: 2
        System.out.println("Example 1 (optimized): " + solution.findMaxLengthOptimized(nums1));  // Expected: 2

        // Example 2: [0, 1, 0]
        int[] nums2 = {0, 1, 0};
        System.out.println("Example 2: " + solution.findMaxLength(nums2));  // Expected: 2
        System.out.println("Example 2 (optimized): " + solution.findMaxLengthOptimized(nums2));  // Expected: 2

        // Additional example: [0, 0, 1, 0, 0, 0, 1, 1]
        int[] nums3 = {0, 0, 1, 0, 0, 0, 1, 1};
        System.out.println("Additional example: " + solution.findMaxLength(nums3));  // Expected: 6
        System.out.println("Additional example (optimized): " + solution.findMaxLengthOptimized(nums3));  // Expected: 6
    }

    /**
     * Finds the length of the longest contiguous subarray with an equal number of 0's and 1's
     *
     * @param nums Binary array containing only 0's and 1's
     * @return Length of the longest subarray with equal 0's and 1's
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;

        // Calculate prefix sum, treating 0 as -1 and 1 as 1
        // This transforms the problem to finding subarrays with sum 0
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (nums[i] == 0 ? -1 : 1);
        }

        // Map to store the first occurrence index of each prefix sum
        java.util.HashMap<Integer, Integer> valToIndex = new java.util.HashMap<>();
        int maxLength = 0;

        for (int i = 0; i < preSum.length; i++) {
            // If this prefix sum hasn't been seen before, record its index
            if (!valToIndex.containsKey(preSum[i])) {
                valToIndex.put(preSum[i], i);
            } else {
                // If we've seen this prefix sum before, we've found a subarray with sum 0
                // Calculate its length and update maxLength if needed
                maxLength = Math.max(maxLength, i - valToIndex.get(preSum[i]));
            }
        }

        return maxLength;
    }

    /**
     * Optimized version that eliminates the prefix sum array
     * Uses a running sum variable instead
     */
    public int findMaxLengthOptimized(int[] nums) {
        int maxLength = 0;
        int sum = 0;

        // Map to store running sum -> index
        java.util.HashMap<Integer, Integer> valToIndex = new java.util.HashMap<>();
        // Initialize with sum 0 at index -1 (before array starts)
        valToIndex.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            // Update running sum (treat 0 as -1, 1 as 1)
            sum += (nums[i] == 0) ? -1 : 1;

            // If we've seen this sum before, we found a zero-sum subarray
            if (valToIndex.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - valToIndex.get(sum));
            } else {
                // First occurrence of this sum
                valToIndex.put(sum, i);
            }
        }

        return maxLength;
    }

    /**
     * Solution explanation:
     *
     * 1. We transform the problem by treating 0's as -1 and 1's as 1
     *    This way, a subarray with equal number of 0's and 1's will have a sum of 0
     *
     * 2. We use a prefix sum approach to track the running sum at each position
     *
     * 3. Key insight: If we see the same prefix sum twice, the subarray between these positions has a sum of 0
     *    (which means equal number of 0's and 1's in the original array)
     *
     * 4. We use a hash map to store the first occurrence of each prefix sum
     *    When we see a prefix sum again, we calculate the length of the zero-sum subarray
     *    and update our maximum length if needed
     *
     * 5. The optimized version eliminates the prefix sum array by using a running sum variable
     *    and initializes the map with sum 0 at index -1 to handle subarrays starting from index 0
     */
}
