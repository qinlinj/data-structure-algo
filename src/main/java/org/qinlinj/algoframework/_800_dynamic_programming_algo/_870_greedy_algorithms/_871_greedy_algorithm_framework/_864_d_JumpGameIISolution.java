package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

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
}
