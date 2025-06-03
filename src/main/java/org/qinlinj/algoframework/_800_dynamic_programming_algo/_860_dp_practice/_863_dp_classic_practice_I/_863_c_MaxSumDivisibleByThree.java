package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._863_dp_classic_practice_I;

/**
 * LeetCode 1262. Greatest Sum Divisible by Three - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given an array of integers, find the maximum sum of elements that is divisible by 3.
 * <p>
 * KEY CONCEPTS:
 * 1. State-based Dynamic Programming with remainder consideration
 * 2. State Definition: dp[i][j] = maximum sum using nums[0..i] with remainder j when divided by 3
 * 3. Three possible remainders: 0, 1, 2
 * 4. State Transition based on current number's remainder:
 * - If nums[i] % 3 == 0: Adding it doesn't change remainder
 * - If nums[i] % 3 == 1: remainder changes: 0→1, 1→2, 2→0
 * - If nums[i] % 3 == 2: remainder changes: 0→2, 1→0, 2→1
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(n) for 2D DP, can be optimized to O(1)
 * <p>
 * EXAMPLE:
 * Input: [3,6,5,1,8]
 * Output: 18 (select 3,6,1,8: sum=18, 18%3=0)
 */

public class _863_c_MaxSumDivisibleByThree {

    public static void main(String[] args) {
        _863_c_MaxSumDivisibleByThree solution = new _863_c_MaxSumDivisibleByThree();

        System.out.println("=== Maximum Sum Divisible by Three Tests ===");

        // Test case 1
        int[] nums1 = {3, 6, 5, 1, 8};
        int result1 = solution.maxSumDivThree(nums1);
        int result1_opt = solution.maxSumDivThreeOptimized(nums1);

        System.out.println("Test Case 1:");
        System.out.print("Input: [");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i]);
            if (i < nums1.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.printf("Output (2D DP): %d\n", result1);
        System.out.printf("Output (Optimized): %d\n", result1_opt);
        System.out.println("Explanation: Select 3, 6, 1, 8. Sum = 18, 18 % 3 = 0\n");

        // Test case 2
        int[] nums2 = {4};
        int result2 = solution.maxSumDivThree(nums2);

        System.out.println("Test Case 2:");
        System.out.println("Input: [4]");
        System.out.printf("Output: %d\n", result2);
        System.out.println("Explanation: 4 cannot be divided by 3, so return 0\n");

        // Test case 3
        int[] nums3 = {1, 2, 3, 4, 4};
        int result3 = solution.maxSumDivThree(nums3);

        System.out.println("Test Case 3:");
        System.out.print("Input: [");
        for (int i = 0; i < nums3.length; i++) {
            System.out.print(nums3[i]);
            if (i < nums3.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.printf("Output: %d\n", result3);
        System.out.println("Explanation: Select 1, 3, 4, 4. Sum = 12, 12 % 3 = 0\n");

        // Demonstrate the DP state transitions
        System.out.println("=== DP State Transition Example ===");
        System.out.println("For nums1 = [3, 6, 5, 1, 8]:");
        System.out.println("Initial: dp[0] = 0, dp[1] = -∞, dp[2] = -∞");
        System.out.println("After 3: dp[0] = 3, dp[1] = -∞, dp[2] = -∞");
        System.out.println("After 6: dp[0] = 9, dp[1] = -∞, dp[2] = -∞");
        System.out.println("After 5: dp[0] = 9, dp[1] = 14, dp[2] = 5");
        System.out.println("After 1: dp[0] = 15, dp[1] = 10, dp[2] = 14");
        System.out.println("After 8: dp[0] = 18, dp[1] = 18, dp[2] = 22");
        System.out.println("Final answer: dp[0] = 18");
    }

    public int maxSumDivThree(int[] nums) {
        // dp[i][j] = maximum sum using nums[0..i] with remainder j when divided by 3
        int[][] dp = new int[nums.length + 1][3];

        // Base case: without any numbers
        dp[0][0] = 0;                    // remainder 0 is possible with sum 0
        dp[0][1] = Integer.MIN_VALUE;    // remainder 1 is impossible initially
        dp[0][2] = Integer.MIN_VALUE;    // remainder 2 is impossible initially

        // Fill the DP table
        for (int i = 1; i <= nums.length; i++) {
            int curNum = nums[i - 1];
            int remainder = curNum % 3;

            if (remainder == 0) {
                // Adding a number divisible by 3 doesn't change remainder
                dp[i][0] = dp[i - 1][0] + curNum;
                dp[i][1] = dp[i - 1][1] + curNum;
                dp[i][2] = dp[i - 1][2] + curNum;
            } else if (remainder == 1) {
                // Adding number with remainder 1: 0→1, 1→2, 2→0
                dp[i][0] = Math.max(dp[i - 1][2] + curNum, dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][0] + curNum, dp[i - 1][1]);
                dp[i][2] = Math.max(dp[i - 1][1] + curNum, dp[i - 1][2]);
            } else { // remainder == 2
                // Adding number with remainder 2: 0→2, 1→0, 2→1
                dp[i][0] = Math.max(dp[i - 1][1] + curNum, dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][2] + curNum, dp[i - 1][1]);
                dp[i][2] = Math.max(dp[i - 1][0] + curNum, dp[i - 1][2]);
            }
        }

        return dp[nums.length][0]; // Return maximum sum with remainder 0
    }

    // Space-optimized version using only O(1) space
    public int maxSumDivThreeOptimized(int[] nums) {
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = Integer.MIN_VALUE;
        dp[2] = Integer.MIN_VALUE;

        for (int num : nums) {
            int[] temp = dp.clone();
            int remainder = num % 3;

            if (remainder == 0) {
                dp[0] = temp[0] + num;
                dp[1] = temp[1] + num;
                dp[2] = temp[2] + num;
            } else if (remainder == 1) {
                dp[0] = Math.max(temp[2] + num, temp[0]);
                dp[1] = Math.max(temp[0] + num, temp[1]);
                dp[2] = Math.max(temp[1] + num, temp[2]);
            } else {
                dp[0] = Math.max(temp[1] + num, temp[0]);
                dp[1] = Math.max(temp[2] + num, temp[1]);
                dp[2] = Math.max(temp[0] + num, temp[2]);
            }
        }

        return dp[0];
    }
}