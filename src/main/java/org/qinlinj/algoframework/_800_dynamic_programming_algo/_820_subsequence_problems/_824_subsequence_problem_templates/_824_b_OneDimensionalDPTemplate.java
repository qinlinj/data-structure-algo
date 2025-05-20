package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._824_subsequence_problem_templates;

/**
 * One-Dimensional DP Template for Subsequence Problems
 * <p>
 * Key Concepts:
 * 1. The one-dimensional DP template uses an array where each element represents
 * the optimal solution ending at a specific index.
 * 2. This approach is typically used for problems involving a single sequence,
 * such as the Longest Increasing Subsequence (LIS).
 * 3. The template follows this pattern:
 * - Define dp[i] to represent the optimal solution ending at index i
 * - Compare the current element with previous elements to determine state transitions
 * - Build the solution incrementally by considering all previous states
 * 4. Time complexity is generally O(n²) due to the nested loop structure.
 * <p>
 * This class demonstrates the one-dimensional DP template with the classic
 * Longest Increasing Subsequence problem.
 */
public class _824_b_OneDimensionalDPTemplate {

    /**
     * Implements the Longest Increasing Subsequence algorithm
     * using the one-dimensional DP template
     *
     * @param nums an array of integers
     * @return the length of the longest strictly increasing subsequence
     */
    public static int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // dp[i] represents the length of the longest increasing subsequence
        // that ends with nums[i]
        int[] dp = new int[n];

        // Base case: every single element is an increasing subsequence of length 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Fill the dp array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If the current element is greater than a previous element,
                // we can extend that previous subsequence
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Find the maximum value in dp array
        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    /**
     * Visualizes the LIS algorithm to illustrate the one-dimensional DP template
     */
    public static void visualizeLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("\nStep-by-step LIS calculation:");

        // Base case
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        System.out.println("Initialize dp array to: " + java.util.Arrays.toString(dp));

        // Fill the dp array with visualization
        for (int i = 1; i < n; i++) {
            System.out.println("\nProcessing index " + i + " (value: " + nums[i] + "):");

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    int newLength = dp[j] + 1;
                    if (newLength > dp[i]) {
                        System.out.println("  Found increasing: " + nums[j] + " -> " + nums[i]);
                        System.out.println("  Update dp[" + i + "] from " + dp[i] + " to " + newLength);
                        dp[i] = newLength;
                    }
                }
            }

            System.out.println("  After processing index " + i + ", dp array: " + java.util.Arrays.toString(dp));
        }

        // Find the maximum
        int maxLength = 0;
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        System.out.println("\nFinal dp array: " + java.util.Arrays.toString(dp));
        System.out.println("Longest Increasing Subsequence length: " + maxLength);

        // Reconstruct the LIS
        StringBuilder lis = new StringBuilder();
        int[] sequence = new int[maxLength];
        int k = maxLength - 1;
        sequence[k--] = nums[maxIndex];

        for (int i = maxIndex - 1; i >= 0 && k >= 0; i--) {
            if (nums[i] < nums[maxIndex] && dp[i] == dp[maxIndex] - 1) {
                sequence[k--] = nums[i];
                maxIndex = i;
            }
        }

        for (int num : sequence) {
            lis.append(num).append(" ");
        }

        System.out.println("One possible LIS: " + lis.toString());
    }

    /**
     * Another example of the one-dimensional DP template: Maximum Subarray Sum
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // dp[i] represents the maximum subarray sum ending at index i
        int[] dp = new int[n];
        dp[0] = nums[0];

        int maxSum = dp[0];

        for (int i = 1; i < n; i++) {
            // Either start a new subarray at i or extend the previous subarray
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    /**
     * Main method to demonstrate the one-dimensional DP template
     */
    public static void main(String[] args) {
        System.out.println("=== ONE-DIMENSIONAL DP TEMPLATE FOR SUBSEQUENCE PROBLEMS ===\n");

        System.out.println("The one-dimensional DP template is used when:");
        System.out.println("- We're dealing with a single sequence");
        System.out.println("- We can define our dp[i] to represent the solution ending at index i");
        System.out.println("- We can build our solution by considering all previous states\n");

        // Example 1: Longest Increasing Subsequence
        System.out.println("Example 1: Longest Increasing Subsequence");
        System.out.println("----------------------------------------");
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        int lisLength = longestIncreasingSubsequence(nums1);
        System.out.println("Array: " + java.util.Arrays.toString(nums1));
        System.out.println("Length of LIS: " + lisLength);

        System.out.println("\nVisualization of the LIS calculation:");
        visualizeLIS(nums1);

        // Example 2: Maximum Subarray Sum
        System.out.println("\nExample 2: Maximum Subarray Sum");
        System.out.println("-------------------------------");
        int[] nums2 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSubArray(nums2);
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        System.out.println("Maximum Subarray Sum: " + maxSum);

        System.out.println("\nBoth of these problems use the one-dimensional DP template:");
        System.out.println("- Define dp[i] as the optimal solution ending at index i");
        System.out.println("- Find the recurrence relation based on the problem");
        System.out.println("- Build the solution incrementally");
    }
}