package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

public class _864_c_JumpGameSolution {

    /**
     * Brute Force Solution - Explores all possible paths
     * Time Complexity: O(k^n) where k is average jump distance
     * Space Complexity: O(n) for recursion stack
     * <p>
     * This approach times out for large inputs but shows the exhaustive nature
     */
    public static boolean canJumpBruteForce(int[] nums) {
        return canJumpHelper(nums, 0);
    }

    private static boolean canJumpHelper(int[] nums, int position) {
        // Base case: reached or passed the last index
        if (position >= nums.length - 1) {
            return true;
        }

        // Try all possible jumps from current position
        int maxJump = nums[position];
        for (int jump = 1; jump <= maxJump; jump++) {
            if (canJumpHelper(nums, position + jump)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Dynamic Programming Solution with Memoization
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     */
    public static boolean canJumpDP(int[] nums) {
        Boolean[] memo = new Boolean[nums.length];
        return canJumpDPHelper(nums, 0, memo);
    }

    private static boolean canJumpDPHelper(int[] nums, int position, Boolean[] memo) {
        if (position >= nums.length - 1) {
            return true;
        }

        if (memo[position] != null) {
            return memo[position];
        }

        int maxJump = nums[position];
        for (int jump = 1; jump <= maxJump; jump++) {
            if (canJumpDPHelper(nums, position + jump, memo)) {
                memo[position] = true;
                return true;
            }
        }

        memo[position] = false;
        return false;
    }


    /**
     * Greedy Solution - Optimal approach
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * <p>
     * Key insight: We only need to track the farthest reachable position
     */
    public static boolean canJumpGreedy(int[] nums) {
        int n = nums.length;
        int farthest = 0;

        // Iterate through array (except last element)
        for (int i = 0; i < n - 1; i++) {
            // Update farthest reachable position
            farthest = Math.max(farthest, i + nums[i]);

            // If we can't progress further, we're stuck
            if (farthest <= i) {
                return false;
            }
        }

        // Check if we can reach the last index
        return farthest >= n - 1;
    }

    /**
     * Alternative Greedy Implementation - Bottom-up approach
     * Tracks the leftmost "good" position that can reach the end
     */
    public static boolean canJumpGreedyBottomUp(int[] nums) {
        int n = nums.length;
        int lastGoodIndex = n - 1;

        // Work backwards from the end
        for (int i = n - 2; i >= 0; i--) {
            // Check if current position can reach the last good position
            if (i + nums[i] >= lastGoodIndex) {
                lastGoodIndex = i;
            }
        }

        return lastGoodIndex == 0;
    }

}
