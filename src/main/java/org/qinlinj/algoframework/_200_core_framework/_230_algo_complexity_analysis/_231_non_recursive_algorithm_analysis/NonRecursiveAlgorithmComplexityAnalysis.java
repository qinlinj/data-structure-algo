package org.qinlinj.algoframework._200_core_framework._230_algo_complexity_analysis._231_non_recursive_algorithm_analysis;

import java.util.*;

/**
 * Algorithm Complexity Analysis Guide
 * <p>
 * This class summarizes key concepts for analyzing time and space complexity
 * of algorithms, including practical tips and common pitfalls.
 * <p>
 * Key Points:
 * <p>
 * 1. Using complexity analysis to infer solution approaches:
 * - Pay attention to input data size constraints in problems
 * - Determine allowed time complexity based on input size
 * - Use complexity constraints to narrow down potential algorithms
 * <p>
 * 2. Common coding mistakes leading to inefficient algorithms:
 * - Using unnecessary standard output during algorithm execution
 * - Passing by value instead of reference (especially in recursive calls)
 * - Misunderstanding underlying implementations of interface objects
 * <p>
 * 3. Big O Notation basics:
 * - Only keep the fastest-growing term (dropping constants and slower terms)
 * - Big O represents an upper bound on growth rate
 * - Sometimes a loose upper bound is acceptable when exact analysis is difficult
 * <p>
 * 4. Non-recursive algorithm analysis:
 * - For nested loops, typically multiply each loop's complexity
 * - Special patterns (like sliding window, non-retreating pointers) can be linear
 * - Key insight: Focus on what the algorithm actually does, not just its structure
 */
public class NonRecursiveAlgorithmComplexityAnalysis {

    /**
     * SECTION 1: COMPLEXITY ANALYSIS TO INFER SOLUTION APPROACHES
     */

    /**
     * Example: Based on input size, we can infer appropriate algorithms
     * <p>
     * When array length ≈ 10^6:
     * - O(n²) is too slow (10^12 operations)
     * - Need O(n log n) or O(n) solution
     * - Consider: sorting, prefix sum, two pointers, 1D DP
     * <p>
     * When array length ≤ 20:
     * - Exponential solutions may be required
     * - Consider: backtracking, brute force enumeration
     */
    public static void inferSolutionApproach() {
        int[] largeArray = new int[1000000]; // 10^6 elements

        // For this size, we should use:
        // Linear approach - O(n)
        int sum = sumArray(largeArray);

        // O(n log n) approach - merge sort
        mergeSort(largeArray, 0, largeArray.length - 1);

        int[] smallArray = new int[15]; // Small array of 15 elements

        // For this size, exponential solutions are acceptable:
        // Generate all subsets - O(2^n)
        generateAllSubsets(smallArray);
    }

    /**
     * SECTION 2: COMMON CODING MISTAKES
     */

    /**
     * Example 1: Inefficient - Using unnecessary standard output
     */
    public static int inefficientSumWithPrinting(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            System.out.println("Current sum: " + sum); // Slows down execution
        }
        return sum;
    }

    /**
     * Example 2: Pass by value vs. pass by reference
     * Note: In Java, objects are passed by reference, but these examples
     * illustrate the concept for languages where this matters more
     */
    // Inefficient (if in C++ or similar language)
    public static void processArrayByValue(int[] array) {
        // In some languages, this would create a copy (not in Java)
        // Expensive in time and space, especially for recursive calls
    }

    // Efficient (if in C++ or similar language)
    public static void processArrayByReference(int[] array) {
        // No copying of the array, just passing the reference
    }

    /**
     * SECTION 3: BIG O NOTATION EXAMPLES
     */

    /**
     * Examples of Big O simplification:
     *
     * O(2n + 100) = O(n)              // Drop constants
     * O(n³ + 999n² + 999n) = O(n³)    // Keep fastest growing term
     * O(n + m) cannot be simplified    // Multiple variables may be kept
     */

    /**
     * Example of using looser upper bounds when exact analysis is difficult
     */
    public static int coinChange(int[] coins, int amount) {
        // This is a recursive solution with complex time complexity
        // Exact analysis is difficult, so we use a loose upper bound

        // If k is the number of coin denominations and n is the amount
        // Worst case complexity is O(k^n) - exponential

        if (amount <= 0) return 0;

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int subproblem = coinChange(coins, amount - coin);
                if (subproblem != -1) {
                    minCoins = Math.min(minCoins, subproblem + 1);
                }
            }
        }

        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    /**
     * SECTION 4: NON-RECURSIVE ALGORITHM ANALYSIS
     */

    /**
     * Example 1: Nested loops with O(n*w) complexity
     */
    public static int knapsackDP(int[] weights, int[] values, int n, int capacity) {
        int[][] dp = new int[n + 1][capacity + 1];

        // Time complexity: O(n * capacity)
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                // Skip item or take item
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * Example 2: Nested loops with variable inner bound - O(n²)
     */
    public static int triangularSum(int n) {
        int sum = 0;

        // Time complexity: O(n²) because inner loop iterations form an arithmetic sequence
        // 1 + 2 + 3 + ... + n = n(n+1)/2 which is O(n²)
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                sum += i * j;
            }
        }

        return sum;
    }

    /**
     * Example 3: Two pointers - Linear time complexity despite nested loops
     * This is the key insight: Focus on what the algorithm actually does
     */
    public static int[] twoSum(int[] nums, int target) {
        // Sort the array first (O(n log n))
        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;

        // Despite nested loops, this is O(n) time complexity
        // because the pointers never retreat
        while (left < right) {
            int sum = nums[left] + nums[right];
            int leftVal = nums[left], rightVal = nums[right];

            if (sum < target) {
                // Move left pointer right until different value
                while (left < right && nums[left] == leftVal) left++;
            } else if (sum > target) {
                // Move right pointer left until different value
                while (left < right && nums[right] == rightVal) right--;
            } else {
                // Found target sum
                return new int[]{left, right};
            }
        }

        return new int[]{-1, -1}; // No solution
    }

    /**
     * Example 4: Sliding window - Linear time complexity despite nested loops
     */
    public static int maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < k) return -1;

        int maxSum = 0;
        int windowSum = 0;

        // First window
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        maxSum = windowSum;

        // Slide window, this is O(n) despite nested loop appearance
        // because each element is processed exactly once
        int left = 0, right = k;
        while (right < n) {
            // Add element entering window
            windowSum += nums[right];
            right++;

            // Remove element leaving window
            windowSum -= nums[left];
            left++;

            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    /**
     * Example 5: Standard nested loops - O(n²) complexity
     * Contrast with previous examples
     */
    public static int[][] matrixMultiplication(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];

        // Classic O(n³) complexity for matrix multiplication
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    /**
     * HELPER METHODS FOR THE EXAMPLES ABOVE
     */

    private static int sumArray(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // Merge implementation omitted for brevity
    }

    private static void generateAllSubsets(int[] arr) {
        // Subset generation implementation omitted for brevity
    }

    /**
     * Main method with example execution
     */
    public static void main(String[] args) {
        System.out.println("Time Complexity Analysis Examples:");

        // Example with two pointers
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("Two Sum result indices: [" + result[0] + ", " + result[1] + "]");

        // Example with sliding window
        int[] windowArray = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 3;
        int maxSum = maxSubarraySum(windowArray, k);
        System.out.println("Maximum sum of subarray of length " + k + ": " + maxSum);

        System.out.println("\nKey insights about time complexity analysis:");
        System.out.println("1. Pay attention to data size constraints in problems");
        System.out.println("2. Focus on what algorithms actually do, not just loop structure");
        System.out.println("3. Non-retreating pointers often result in linear complexity");
        System.out.println("4. Big O represents upper bounds - sometimes loose bounds are acceptable");
    }
}