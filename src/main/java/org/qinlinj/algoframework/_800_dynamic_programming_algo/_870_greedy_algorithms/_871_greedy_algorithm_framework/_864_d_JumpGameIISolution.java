package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

import java.util.*;

public class _864_d_JumpGameIISolution {

    /**
     * Brute Force Solution - Explores all possible jump sequences
     * Time Complexity: O(k^n) where k is average jump distance
     * Space Complexity: O(n) for recursion stack
     */
    public static int jumpBruteForce(int[] nums) {
        return jumpHelper(nums, 0);
    }

    private static int jumpHelper(int[] nums, int position) {
        // Base case: reached or passed the last index
        if (position >= nums.length - 1) {
            return 0;
        }

        int minJumps = Integer.MAX_VALUE;
        int maxJump = nums[position];

        // Try all possible jumps from current position
        for (int jump = 1; jump <= maxJump; jump++) {
            int nextPosition = position + jump;
            if (nextPosition < nums.length) {
                int result = jumpHelper(nums, nextPosition);
                if (result != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, result + 1);
                }
            }
        }

        return minJumps;
    }

    /**
     * Dynamic Programming Solution with Memoization
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     */
    public static int jumpDP(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return jumpDPHelper(nums, 0, memo);
    }

    private static int jumpDPHelper(int[] nums, int position, int[] memo) {
        if (position >= nums.length - 1) {
            return 0;
        }

        if (memo[position] != -1) {
            return memo[position];
        }

        int minJumps = Integer.MAX_VALUE;
        int maxJump = nums[position];

        for (int jump = 1; jump <= maxJump; jump++) {
            int nextPosition = position + jump;
            if (nextPosition < nums.length) {
                int result = jumpDPHelper(nums, nextPosition, memo);
                if (result != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, result + 1);
                }
            }
        }

        memo[position] = minJumps;
        return minJumps;
    }
}
