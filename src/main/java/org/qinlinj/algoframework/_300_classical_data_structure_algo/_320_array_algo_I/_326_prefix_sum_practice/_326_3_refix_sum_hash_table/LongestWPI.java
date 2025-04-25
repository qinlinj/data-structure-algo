package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_3_refix_sum_hash_table;

/**
 * Longest Well-Performing Interval (LeetCode 1124)
 * <p>
 * Key Points:
 * 1. Combines prefix sum technique with hash map for optimal solution
 * 2. Transforms the problem by converting hours > 8 to +1 and hours ≤ 8 to -1
 * 3. A "well-performing interval" becomes a subarray with positive sum
 * 4. Uses a hash map to track the first occurrence of each prefix sum
 * 5. Time complexity: O(n) - single pass through the array
 * 6. Space complexity: O(n) for the hash map and prefix sum array
 * 7. Special case handling for positive prefix sums
 */
public class LongestWPI {
    /**
     * Example usage
     */
    public static void main(String[] args) {
        LongestWPI solution = new LongestWPI();

        // Example 1: [9, 9, 6, 0, 6, 6, 9]
        int[] hours1 = {9, 9, 6, 0, 6, 6, 9};
        System.out.println("Example 1: " + solution.longestWPI(hours1));  // Expected: 3
        System.out.println("Example 1 (optimized): " + solution.longestWPIOptimized(hours1));  // Expected: 3
        System.out.println("Example 1 (further optimized): " + solution.longestWPIOptimizedFurther(hours1));  // Expected: 3

        // Example 2: [6, 6, 6]
        int[] hours2 = {6, 6, 6};
        System.out.println("Example 2: " + solution.longestWPI(hours2));  // Expected: 0
        System.out.println("Example 2 (optimized): " + solution.longestWPIOptimized(hours2));  // Expected: 0
        System.out.println("Example 2 (further optimized): " + solution.longestWPIOptimizedFurther(hours2));  // Expected: 0

        // Additional example: [9, 6, 9, 9, 6, 9, 6, 9, 6]
        int[] hours3 = {9, 6, 9, 9, 6, 9, 6, 9, 6};
        System.out.println("Additional example: " + solution.longestWPI(hours3));  // Expected: 7
        System.out.println("Additional example (optimized): " + solution.longestWPIOptimized(hours3));  // Expected: 7
        System.out.println("Additional example (further optimized): " + solution.longestWPIOptimizedFurther(hours3));  // Expected: 7
    }

    /**
     * Finds the length of the longest well-performing interval
     * A well-performing interval has more days with hours > 8 than days with hours ≤ 8
     *
     * @param hours Array of daily working hours
     * @return Length of the longest well-performing interval
     */
    public int longestWPI(int[] hours) {
        int n = hours.length;

        // Prefix sum array
        int[] preSum = new int[n + 1];
        preSum[0] = 0;

        // Calculate prefix sum, treating hours > 8 as +1 and hours ≤ 8 as -1
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (hours[i] > 8 ? 1 : -1);
        }

        // Map to store the first occurrence index of each prefix sum
        java.util.HashMap<Integer, Integer> valToIndex = new java.util.HashMap<>();
        int maxLength = 0;

        for (int i = 0; i < preSum.length; i++) {
            // If this prefix sum hasn't been seen before, record its index
            if (!valToIndex.containsKey(preSum[i])) {
                valToIndex.put(preSum[i], i);
            }
            // We don't update existing entries to keep the earliest occurrence

            // Case 1: If the current prefix sum is positive, the entire subarray [0, i-1] is well-performing
            if (preSum[i] > 0) {
                maxLength = Math.max(maxLength, i);
            }
            // Case 2: Look for a previous prefix sum that would make a well-performing interval
            else {
                // Look for preSum[j] = preSum[i] - 1, which means the subarray (j, i-1] has sum 1 (well-performing)
                if (valToIndex.containsKey(preSum[i] - 1)) {
                    int j = valToIndex.get(preSum[i] - 1);
                    maxLength = Math.max(maxLength, i - j);
                }
            }
        }

        return maxLength;
    }

    /**
     * Optimized version that eliminates the prefix sum array
     * Uses a running sum variable instead
     */
    public int longestWPIOptimized(int[] hours) {
        // Map to store the first occurrence index of each running sum
        java.util.HashMap<Integer, Integer> valToIndex = new java.util.HashMap<>();

        int maxLength = 0;
        int sum = 0;

        for (int i = 0; i < hours.length; i++) {
            // Update running sum
            sum += (hours[i] > 8 ? 1 : -1);

            // Case 1: If current sum is positive, the entire subarray [0, i] is well-performing
            if (sum > 0) {
                maxLength = Math.max(maxLength, i + 1);
            }
            // Case 2: Check if we can find a previous sum that creates a well-performing interval
            else {
                // First encounter of this sum, record the index
                if (!valToIndex.containsKey(sum)) {
                    valToIndex.put(sum, i);
                }

                // Look for a position where sum was (current sum - 1)
                if (valToIndex.containsKey(sum - 1)) {
                    maxLength = Math.max(maxLength, i - valToIndex.get(sum - 1));
                }
            }
        }

        return maxLength;
    }

    /**
     * Further optimized solution using an insight about prefix sums
     * A key observation: we only need to check for sum-1, not other values
     */
    public int longestWPIOptimizedFurther(int[] hours) {
        int n = hours.length;

        // Map to store the first occurrence index of each sum
        java.util.HashMap<Integer, Integer> valToIndex = new java.util.HashMap<>();

        int maxLength = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            // Update running sum
            sum += (hours[i] > 8 ? 1 : -1);

            // Case 1: If running sum is positive, the entire subarray [0, i] is well-performing
            if (sum > 0) {
                maxLength = i + 1;
            }
            // Case 2: Otherwise, check for a well-performing interval
            else {
                // Only store first occurrence of each sum
                if (!valToIndex.containsKey(sum)) {
                    valToIndex.put(sum, i);
                }

                // Check if there exists a j such that sum[0...j] = sum - 1
                if (valToIndex.containsKey(sum - 1)) {
                    maxLength = Math.max(maxLength, i - valToIndex.get(sum - 1));
                }
            }
        }

        return maxLength;
    }

    /**
     * Solution explanation:
     *
     * 1. We transform the problem by replacing each hour with:
     *    - +1 if the hour is > 8 (tiring day)
     *    - -1 if the hour is ≤ 8 (not tiring day)
     *
     * 2. With this transformation, a "well-performing interval" is a subarray with more +1s than -1s,
     *    which means a subarray with a positive sum
     *
     * 3. For any position i, we need to find the longest subarray ending at i with a positive sum
     *
     * 4. There are two cases to consider:
     *    - If preSum[i] > 0, the entire subarray [0, i-1] is well-performing
     *    - Otherwise, we need to find the earliest j such that preSum[i] - preSum[j] > 0
     *      This simplifies to finding the earliest j such that preSum[j] = preSum[i] - 1
     *
     * 5. We use a hash map to track the first occurrence of each prefix sum
     *
     * 6. The optimized versions eliminate the prefix sum array and improve readability
     *
     * 7. A subtle optimization: we only need to check for (sum-1) in the hash map,
     *    not for all values less than the current sum
     */
}
