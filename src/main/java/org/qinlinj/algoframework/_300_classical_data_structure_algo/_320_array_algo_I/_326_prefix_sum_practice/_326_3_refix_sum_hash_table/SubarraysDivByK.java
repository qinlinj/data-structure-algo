package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_3_refix_sum_hash_table;

/**
 * Subarray Sums Divisible by K (LeetCode 974)
 * <p>
 * Key Points:
 * 1. Combines prefix sum technique with modular arithmetic
 * 2. Uses a hash map to count occurrences of each prefix sum remainder
 * 3. Key insight: If preSum[i] % k = preSum[j] % k, then subarray (i,j] sum is divisible by k
 * 4. Special handling for negative remainders in languages like Java
 * 5. Time complexity: O(n) - single pass through the array
 * 6. Space complexity: O(min(n,k)) - at most k different remainders possible
 * 7. Demonstrates how prefix sums can be combined with modular arithmetic
 */
public class SubarraysDivByK {
    /**
     * Example usage
     */
    public static void main(String[] args) {
        SubarraysDivByK solution = new SubarraysDivByK();

        // Example 1: [4, 5, 0, -2, -3, 1], k = 5
        int[] nums1 = {4, 5, 0, -2, -3, 1};
        int k1 = 5;
        System.out.println("Example 1: " + solution.subarraysDivByK(nums1, k1));  // Expected: 7
        System.out.println("Example 1 (optimized): " + solution.subarraysDivByKOptimized(nums1, k1));  // Expected: 7
        System.out.println("Example 1 (array optimized): " + solution.subarraysDivByKArrayOptimized(nums1, k1));  // Expected: 7

        // Example 2: [5], k = 9
        int[] nums2 = {5};
        int k2 = 9;
        System.out.println("Example 2: " + solution.subarraysDivByK(nums2, k2));  // Expected: 0
        System.out.println("Example 2 (optimized): " + solution.subarraysDivByKOptimized(nums2, k2));  // Expected: 0
        System.out.println("Example 2 (array optimized): " + solution.subarraysDivByKArrayOptimized(nums2, k2));  // Expected: 0
    }

    /**
     * Counts the number of continuous subarrays with sum divisible by k
     *
     * @param nums Array of integers
     * @param k    Divisor
     * @return Number of subarrays with sum divisible by k
     */
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;

        // Prefix sum array (not strictly necessary but helps with explanation)
        int[] preSum = new int[n + 1];
        preSum[0] = 0;

        // Map to count occurrences of each remainder
        java.util.HashMap<Integer, Integer> remainderToCount = new java.util.HashMap<>();
        remainderToCount.put(0, 1);  // Empty subarray has sum 0, which is divisible by any k

        int result = 0;

        // Calculate prefix sums and count subarrays
        for (int i = 0; i < n; i++) {
            // Calculate current prefix sum
            preSum[i + 1] = preSum[i] + nums[i];

            // Calculate remainder when divided by k
            int curRemainder = preSum[i + 1] % k;

            // Handle negative remainders (Java's % operator preserves the sign)
            if (curRemainder < 0) {
                curRemainder += k;
            }

            // If we've seen this remainder before, it means there are subarrays divisible by k
            if (remainderToCount.containsKey(curRemainder)) {
                int count = remainderToCount.get(curRemainder);
                result += count;
                remainderToCount.put(curRemainder, count + 1);
            } else {
                // First occurrence of this remainder
                remainderToCount.put(curRemainder, 1);
            }
        }

        return result;
    }

    /**
     * Optimized version that eliminates the prefix sum array
     * Uses a running sum variable instead
     */
    public int subarraysDivByKOptimized(int[] nums, int k) {
        // Map to count occurrences of each remainder
        java.util.HashMap<Integer, Integer> remainderToCount = new java.util.HashMap<>();
        remainderToCount.put(0, 1);  // Empty subarray has sum 0

        int result = 0;
        int sum = 0;

        for (int num : nums) {
            // Update running sum
            sum += num;

            // Calculate remainder when divided by k
            int remainder = sum % k;

            // Handle negative remainders
            if (remainder < 0) {
                remainder += k;
            }

            // If we've seen this remainder before, add to result
            if (remainderToCount.containsKey(remainder)) {
                result += remainderToCount.get(remainder);
            }

            // Update the count of current remainder
            remainderToCount.put(remainder, remainderToCount.getOrDefault(remainder, 0) + 1);
        }

        return result;
    }

    /**
     * Further optimized version using an array instead of a hash map
     * Since remainders are limited to range [0, k-1]
     */
    public int subarraysDivByKArrayOptimized(int[] nums, int k) {
        // Array to count occurrences of each remainder
        int[] remainderCounts = new int[k];
        remainderCounts[0] = 1;  // Empty subarray has sum 0

        int result = 0;
        int sum = 0;

        for (int num : nums) {
            // Update running sum
            sum += num;

            // Calculate remainder when divided by k
            int remainder = sum % k;

            // Handle negative remainders
            if (remainder < 0) {
                remainder += k;
            }

            // Add current count of this remainder to result
            result += remainderCounts[remainder];

            // Update the count of current remainder
            remainderCounts[remainder]++;
        }

        return result;
    }

    /**
     * Solution explanation:
     *
     * 1. The key insight: For subarrays nums[i+1..j] with sum divisible by k:
     *    - (preSum[j] - preSum[i]) % k = 0
     *    - preSum[j] % k = preSum[i] % k
     *
     * 2. This means if we find two prefix sums with the same remainder when divided by k,
     *    the subarray between them has a sum divisible by k
     *
     * 3. We use a hash map (or array for further optimization) to count occurrences of each remainder
     *
     * 4. For each position, we check how many previous positions had the same remainder
     *    Each such position forms a valid subarray with the current position
     *
     * 5. Special care is needed for negative remainders in Java:
     *    - Java's modulo operator preserves the sign: -1 % 5 = -1
     *    - But we need a positive remainder in the range [0, k-1]
     *    - Therefore, we add k to negative remainders
     *
     * 6. The array optimization further improves space efficiency since there are at most k different remainders
     */
}
