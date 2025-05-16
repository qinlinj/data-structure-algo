package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._812_longest_increasing_subsequence;

/**
 * Dynamic Programming - Advanced Problem-Solving Techniques
 * =======================================================
 * <p>
 * This class summarizes the key concepts and techniques for approaching dynamic programming
 * problems, with emphasis on finding state transitions for complex problems like the Longest
 * Increasing Subsequence (LIS) and its variants.
 * <p>
 * Key Insights:
 * <p>
 * 1. Mathematical Induction in Dynamic Programming:
 * - The core design principle of DP is mathematical induction
 * - Assume solutions for smaller subproblems are known
 * - Use those solutions to derive the solution for the current state
 * <p>
 * 2. Finding State Transitions:
 * - Clearly define what dp[i] represents - this is often the most critical step
 * - Think about how current state relates to previous states
 * - Focus on the relationship between dp[i] and dp[0...i-1]
 * - Consider what choices are available at the current state
 * <p>
 * 3. General Workflow for DP Problems:
 * - Define the dp array/function meaning clearly and precisely
 * - Identify base cases
 * - Formulate the state transition equation
 * - Determine the final answer from the completed dp array
 * <p>
 * 4. Optimizing Solutions:
 * - Standard DP solutions can often be optimized for time or space
 * - Dimension reduction techniques (like in Russian Doll Envelopes)
 * - Using more efficient data structures (like binary search for LIS)
 * <p>
 * This class provides utility methods that compare different algorithms for solving
 * LIS-type problems and demonstrates the progression from basic DP to optimized solutions.
 */

import java.util.*;

public class _812_d_DPProblemSolvingTechniques {

    public static void main(String[] args) {
        _812_d_DPProblemSolvingTechniques techniques = new _812_d_DPProblemSolvingTechniques();

        // Display general DP problem-solving framework
        techniques.dpProblemSolvingFramework();

        System.out.println("\n" + "=".repeat(80) + "\n");

        // Display common pitfalls and tips
        techniques.commonPitfallsAndTips();

        System.out.println("\n" + "=".repeat(80) + "\n");

        // Compare LIS solutions with a medium-sized array
        int[] nums = new int[100];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 1000); // Random array for testing
        }
        techniques.compareLISSolutions(nums);

        System.out.println("\n" + "=".repeat(80) + "\n");

        // Demonstrate dimension reduction technique
        int[][] envelopes = {
                {5, 4}, {6, 4}, {6, 7}, {2, 3}, {7, 8}, {3, 5}, {9, 6}
        };
        techniques.demonstrateDimensionReduction(envelopes);
    }

    /**
     * This method demonstrates the comparison between dynamic programming and binary search
     * approaches for solving the Longest Increasing Subsequence problem.
     */
    public void compareLISSolutions(int[] nums) {
        System.out.println("Comparing solutions for array: " + Arrays.toString(nums));

        // Measure time for DP solution
        long startTimeDP = System.nanoTime();
        int lisLengthDP = lengthOfLIS_DP(nums);
        long endTimeDP = System.nanoTime();
        double durationDP = (endTimeDP - startTimeDP) / 1000000.0; // Convert to milliseconds

        // Measure time for Binary Search solution
        long startTimeBS = System.nanoTime();
        int lisLengthBS = lengthOfLIS_BinarySearch(nums);
        long endTimeBS = System.nanoTime();
        double durationBS = (endTimeBS - startTimeBS) / 1000000.0; // Convert to milliseconds

        System.out.println("\nResults:");
        System.out.println("DP solution result: " + lisLengthDP + " (took " + durationDP + " ms)");
        System.out.println("Binary Search solution result: " + lisLengthBS + " (took " + durationBS + " ms)");

        // Performance analysis
        System.out.println("\nPerformance Analysis:");
        System.out.println("- DP solution: O(n²) time complexity, O(n) space complexity");
        System.out.println("- Binary Search solution: O(n log n) time complexity, O(n) space complexity");

        if (durationBS < durationDP) {
            System.out.println("Binary Search solution was " + (durationDP / durationBS) +
                    "x faster for this input");
        } else {
            System.out.println("DP solution was " + (durationBS / durationDP) +
                    "x faster for this input (unexpected for large inputs)");
        }
    }

    /**
     * Dynamic Programming approach for the Longest Increasing Subsequence problem.
     * Time Complexity: O(n²)
     */
    private int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    /**
     * Binary Search approach for the Longest Increasing Subsequence problem.
     * Time Complexity: O(n log n)
     */
    private int lengthOfLIS_BinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] top = new int[nums.length];
        int piles = 0;

        for (int num : nums) {
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (top[mid] >= num) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if (left == piles) {
                piles++;
            }
            top[left] = num;
        }

        return piles;
    }

    /**
     * Demonstrates approach to solving a 2D extension of LIS (Russian Doll Envelopes)
     */
    public void demonstrateDimensionReduction(int[][] envelopes) {
        System.out.println("Demonstration of Dimension Reduction Technique");
        System.out.println("Input envelopes: " + Arrays.deepToString(envelopes));

        System.out.println("\nKey Insight: Sort by one dimension (width) and apply LIS on the other (height)");
        System.out.println("1. Sort envelopes by width (ascending)");
        System.out.println("2. For ties in width, sort by height (descending) to prevent multiple same-width envelopes");
        System.out.println("3. Apply LIS algorithm on the heights");

        // Sort the envelopes
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // Descending height for same width
            }
            return a[0] - b[0]; // Ascending width
        });

        System.out.println("\nAfter sorting: " + Arrays.deepToString(envelopes));

        // Extract heights
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }

        System.out.println("Heights array for LIS: " + Arrays.toString(heights));

        // Compute LIS on heights
        int lisLength = lengthOfLIS_BinarySearch(heights);
        System.out.println("Result (maximum number of nested envelopes): " + lisLength);
    }

    /**
     * Provides guidelines for approaching dynamic programming problems
     */
    public void dpProblemSolvingFramework() {
        System.out.println("Dynamic Programming Problem-Solving Framework");
        System.out.println("=============================================");

        System.out.println("\n1. Define the State and DP Array");
        System.out.println("   - Clearly establish what dp[i] or dp[i][j] represents");
        System.out.println("   - Choose the right dimensions for your dp structure");
        System.out.println("   - Example: In LIS, dp[i] = length of longest increasing subsequence ending at index i");

        System.out.println("\n2. Establish Base Cases");
        System.out.println("   - Identify the simplest subproblems with known answers");
        System.out.println("   - Example: In LIS, dp[i] = 1 initially (single element is a valid subsequence)");

        System.out.println("\n3. Formulate State Transitions");
        System.out.println("   - Use mathematical induction: assume dp[0...i-1] are known");
        System.out.println("   - Ask: How can I use previous results to compute dp[i]?");
        System.out.println("   - Example: In LIS, dp[i] = max(dp[j] + 1) for all j < i where nums[j] < nums[i]");

        System.out.println("\n4. Determine Iteration Order");
        System.out.println("   - Ensure you compute dp values in an order where dependencies are resolved first");
        System.out.println("   - Example: In LIS, process array from left to right (i from 0 to n-1)");

        System.out.println("\n5. Compute Final Answer from DP Array");
        System.out.println("   - The final answer may not be directly dp[n] or dp[n][m]");
        System.out.println("   - Example: In LIS, answer is max(dp[i]) for all i from 0 to n-1");

        System.out.println("\n6. Optimize if Necessary");
        System.out.println("   - Look for ways to reduce time or space complexity");
        System.out.println("   - Consider if you can use a more efficient algorithm (e.g., binary search for LIS)");
        System.out.println("   - Explore dimensionality reduction where applicable");
    }

    /**
     * Showcases common pitfalls and troubleshooting tips for DP problems
     */
    public void commonPitfallsAndTips() {
        System.out.println("Common Pitfalls and Troubleshooting Tips for DP Problems");
        System.out.println("=======================================================");

        System.out.println("\n1. Incorrect State Definition");
        System.out.println("   - Problem: DP array doesn't capture all necessary information");
        System.out.println("   - Solution: Reconsider what dp[i] should represent, possibly add dimensions");

        System.out.println("\n2. Missing or Incorrect Base Cases");
        System.out.println("   - Problem: Algorithm starts with wrong assumptions");
        System.out.println("   - Solution: Carefully identify the simplest subproblems and their correct answers");

        System.out.println("\n3. Incorrect State Transitions");
        System.out.println("   - Problem: Relationship between states is wrong");
        System.out.println("   - Solution: Verify your transition formula with small examples");

        System.out.println("\n4. Wrong Iteration Order");
        System.out.println("   - Problem: Trying to use values that haven't been computed yet");
        System.out.println("   - Solution: Ensure dependencies are computed before they're needed");

        System.out.println("\n5. Off-by-One Errors");
        System.out.println("   - Problem: Index issues, especially with array bounds and base cases");
        System.out.println("   - Solution: Double-check array indices and edge cases");

        System.out.println("\n6. Overlooking Optimization Opportunities");
        System.out.println("   - Problem: Implementing standard DP when a more efficient approach exists");
        System.out.println("   - Solution: After a working solution, consider optimizations (space/time)");
    }
}
