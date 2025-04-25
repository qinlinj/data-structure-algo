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
}
