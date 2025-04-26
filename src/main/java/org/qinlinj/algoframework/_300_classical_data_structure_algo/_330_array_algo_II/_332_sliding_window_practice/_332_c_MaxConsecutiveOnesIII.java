package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

/**
 * LeetCode 1004: Max Consecutive Ones III
 * <p>
 * Problem Description:
 * Given a binary array nums and an integer k, return the maximum number of
 * consecutive 1's in the array if you can flip at most k 0's to 1's.
 * <p>
 * Key Insight:
 * This problem can be reframed as: "Find the longest subarray that contains at most k zeros."
 * The sliding window technique is perfect for this because:
 * 1. We expand the window to include more elements
 * 2. When we have more than k zeros in the window, we shrink it from the left
 * 3. The window size at each valid state represents a potential answer
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) as we only use a constant amount of extra space
 */
public class _332_c_MaxConsecutiveOnesIII {
}
