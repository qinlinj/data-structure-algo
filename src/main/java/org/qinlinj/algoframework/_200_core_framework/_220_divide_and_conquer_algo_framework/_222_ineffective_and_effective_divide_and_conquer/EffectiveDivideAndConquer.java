package org.qinlinj.algoframework._200_core_framework._220_divide_and_conquer_algo_framework._222_ineffective_and_effective_divide_and_conquer;

/**
 * Effective vs. Ineffective Divide and Conquer Strategies
 * <p>
 * This class demonstrates when divide and conquer strategies are effective
 * and when they are ineffective by analyzing different implementations.
 * <p>
 * Key Points:
 * <p>
 * 1. Ineffective Divide and Conquer:
 * - Many iterative algorithms can be rewritten using recursion and divide and conquer
 * - However, this doesn't always improve efficiency and may actually increase space complexity
 * - Example: Array sum calculation doesn't benefit from divide and conquer approach
 * - When recursive node time complexity is independent of tree depth (O(1)),
 * divide and conquer may not provide benefits
 * <p>
 * 2. Effective Divide and Conquer:
 * - Divide and conquer is effective when it reduces redundant computations
 * - Using binary splitting to create a balanced recursive tree with O(log n) height can optimize algorithms
 * - Example: Merging k sorted lists benefits from divide and conquer approach
 * - When recursive node time complexity is related to tree depth, divide and conquer can improve efficiency
 * <p>
 * 3. Key Analysis Method:
 * - Visualize the algorithm as a recursive tree
 * - If the time complexity at each node relates to tree depth, divide and conquer can help balance the tree
 * - A balanced tree with height O(log n) is more efficient than an unbalanced tree with height O(n)
 * <p>
 * 4. Time and Space Complexity Analysis:
 * - Ineffective approaches often maintain the same time complexity but increase space complexity
 * - Effective divide and conquer can reduce time complexity (e.g., from O(N²) to O(N log N))
 * - Space complexity relates to recursive stack depth (tree height)
 */
public class EffectiveDivideAndConquer {

    /**
     * SECTION 1: INEFFECTIVE DIVIDE AND CONQUER EXAMPLES
     */

    /**
     * Example 1: Iterative sum calculation
     * Simple O(n) time and O(1) space approach
     */
    public static int getSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
