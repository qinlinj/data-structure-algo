package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

import java.util.*;

/**
 * LeetCode 368. Largest Divisible Subset - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair (Si, Sj) satisfies: Si % Sj == 0 or Sj % Si == 0.
 * <p>
 * KEY CONCEPTS:
 * 1. Transform into Longest Increasing Subsequence (LIS) problem
 * 2. Key insight: After sorting, if a > b > c and a%b==0, b%c==0, then a%c==0
 * 3. Sorting ensures we only need to check divisibility with the largest element
 * 4. State Definition: dp[i] = largest divisible subset ending at nums[i]
 * 5. State Transition: For each j < i, if nums[i] % nums[j] == 0,
 * then dp[i] can extend dp[j]
 * <p>
 * TIME COMPLEXITY: O(n²)
 * SPACE COMPLEXITY: O(n²) for storing subsets, O(n) for length tracking
 * <p>
 * EXAMPLE:
 * Input: [1,2,4,8]
 * Output: [1,2,4,8] (each element divides the next)
 */


public class _863_e_LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;

        // Step 1: Sort the array to ensure smaller elements come first
        Arrays.sort(nums);

        // Step 2: dp[i] stores the largest divisible subset ending at index i
        List<Integer>[] dp = new List[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();
        }

        // Step 3: Base case - each element forms a subset of size 1
        dp[0].add(nums[0]);

        // Step 4: Fill DP table
        for (int i = 1; i < n; i++) {
            int maxSubsetLen = 0;
            int bestPrevIndex = -1;

            // Find the longest subset that nums[i] can extend
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j].size() > maxSubsetLen) {
                    maxSubsetLen = dp[j].size();
                    bestPrevIndex = j;
                }
            }

            // Build dp[i] by extending the best previous subset
            if (bestPrevIndex != -1) {
                dp[i].addAll(dp[bestPrevIndex]);
            }
            dp[i].add(nums[i]);
        }

        // Step 5: Find the longest subset among all dp[i]
        List<Integer> result = dp[0];
        for (int i = 1; i < n; i++) {
            if (dp[i].size() > result.size()) {
                result = dp[i];
            }
        }

        return result;
    }

    // Space-optimized version that only tracks lengths and reconstructs the subset
    public List<Integer> largestDivisibleSubsetOptimized(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        // dp[i] = length of longest divisible subset ending at i
        int[] dp = new int[n];
        // parent[i] = previous index in the optimal subset ending at i
        int[] parent = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);

        int maxLen = 1;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // Reconstruct the subset by backtracking
        List<Integer> result = new ArrayList<>();
        int current = maxIndex;
        while (current != -1) {
            result.add(nums[current]);
            current = parent[current];
        }

        Collections.reverse(result);
        return result;
    }
}
