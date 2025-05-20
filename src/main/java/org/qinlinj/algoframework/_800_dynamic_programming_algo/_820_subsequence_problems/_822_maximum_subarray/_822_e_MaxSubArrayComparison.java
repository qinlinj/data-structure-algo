package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._822_maximum_subarray;

/**
 * Comparative Analysis of Different Approaches to the Maximum Subarray Problem
 * <p>
 * This class provides a comprehensive comparison of the three approaches:
 * 1. Sliding Window
 * 2. Dynamic Programming
 * 3. Prefix Sum
 * <p>
 * Each approach has its own strengths and insights:
 * <p>
 * Sliding Window:
 * - Intuitive for those familiar with the sliding window technique
 * - Uses O(1) space
 * - The key insight is expanding when the sum is positive and shrinking when negative
 * <p>
 * Dynamic Programming:
 * - Uses the classic DP paradigm with carefully defined subproblems
 * - Can be optimized to use O(1) space
 * - The key insight is defining dp[i] as "maximum sum ending at index i"
 * <p>
 * Prefix Sum:
 * - Leverages mathematical properties of cumulative sums
 * - Easy to understand if familiar with prefix sum technique
 * - The key insight is finding the minimum prefix sum to subtract
 * <p>
 * This comparison demonstrates how different algorithm design techniques can be
 * applied to the same problem, each providing a unique perspective and potential
 * advantages in different contexts.
 */
public class _822_e_MaxSubArrayComparison {

    /**
     * Main method to demonstrate all approaches
     */
    public static void main(String[] args) {
        _822_e_MaxSubArrayComparison solution = new _822_e_MaxSubArrayComparison();

        // Standard example
        int[] nums1 = {-3, 1, 3, -1, 2, -4, 2};
        System.out.println("Example 1: Mixed positive and negative");
        solution.compareApproaches(nums1);

        // All negative example
        int[] nums2 = {-2, -1, -3, -4, -1, -2, -5};
        System.out.println("\nExample 2: All negative");
        solution.compareApproaches(nums2);

        // All positive example
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("\nExample 3: All positive");
        solution.compareApproaches(nums3);

        // Larger example to better measure performance
        int[] nums4 = new int[10000];
        for (int i = 0; i < nums4.length; i++) {
            nums4[i] = (int) (Math.random() * 200) - 100; // Random values between -100 and 100
        }
        System.out.println("\nExample 4: Large random array");
        solution.compareApproaches(nums4);

        // Summary of approaches
        System.out.println("\nSummary of Approaches:");
        System.out.println("1. Sliding Window:");
        System.out.println("   - Expands when sum is positive, shrinks when negative");
        System.out.println("   - O(n) time complexity, O(1) space complexity");
        System.out.println("   - Intuitive for those familiar with sliding window technique");

        System.out.println("\n2. Dynamic Programming:");
        System.out.println("   - Defines dp[i] as max subarray sum ending at index i");
        System.out.println("   - O(n) time complexity, O(1) space when optimized");
        System.out.println("   - Classic DP approach with careful subproblem definition");

        System.out.println("\n3. Prefix Sum:");
        System.out.println("   - Uses prefix sums to calculate subarray sums in O(1)");
        System.out.println("   - O(n) time complexity, O(n) space complexity");
        System.out.println("   - Leverages mathematical properties of cumulative sums");
    }

    /**
     * Sliding Window approach
     */
    public int maxSubArraySlidingWindow(int[] nums) {
        int left = 0, right = 0;
        int windowSum = 0, maxSum = Integer.MIN_VALUE;

        while (right < nums.length) {
            // Expand window
            windowSum += nums[right];
            right++;

            // Update maximum
            maxSum = Math.max(maxSum, windowSum);

            // Shrink window if sum becomes negative
            while (windowSum < 0) {
                windowSum -= nums[left];
                left++;
            }
        }

        return maxSum;
    }

    /**
     * Dynamic Programming approach (space-optimized)
     */
    public int maxSubArrayDP(int[] nums) {
        int prevMax = nums[0];
        int maxSum = prevMax;

        for (int i = 1; i < nums.length; i++) {
            prevMax = Math.max(nums[i], nums[i] + prevMax);
            maxSum = Math.max(maxSum, prevMax);
        }

        return maxSum;
    }

    /**
     * Prefix Sum approach
     */
    public int maxSubArrayPrefixSum(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];

        // Calculate prefix sums
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int maxSum = Integer.MIN_VALUE;
        int minPrefixSum = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            minPrefixSum = Math.min(minPrefixSum, preSum[i]);
            maxSum = Math.max(maxSum, preSum[i + 1] - minPrefixSum);
        }

        return maxSum;
    }

    /**
     * Method to compare performance of all approaches
     */
    public void compareApproaches(int[] nums) {
        long startTime, endTime;

        // Sliding Window
        startTime = System.nanoTime();
        int slidingWindowResult = maxSubArraySlidingWindow(nums);
        endTime = System.nanoTime();
        long slidingWindowTime = endTime - startTime;

        // Dynamic Programming
        startTime = System.nanoTime();
        int dpResult = maxSubArrayDP(nums);
        endTime = System.nanoTime();
        long dpTime = endTime - startTime;

        // Prefix Sum
        startTime = System.nanoTime();
        int prefixSumResult = maxSubArrayPrefixSum(nums);
        endTime = System.nanoTime();
        long prefixSumTime = endTime - startTime;

        // Print results
        System.out.println("Results Comparison:");
        System.out.println("Sliding Window: " + slidingWindowResult + " (Time: " + slidingWindowTime + " ns)");
        System.out.println("Dynamic Programming: " + dpResult + " (Time: " + dpTime + " ns)");
        System.out.println("Prefix Sum: " + prefixSumResult + " (Time: " + prefixSumTime + " ns)");
    }
}