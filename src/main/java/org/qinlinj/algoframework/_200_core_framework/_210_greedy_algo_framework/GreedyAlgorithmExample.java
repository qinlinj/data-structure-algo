package org.qinlinj.algoframework._200_core_framework._210_greedy_algo_framework;

/**
 * Greedy Algorithm Explanation
 * ===========================
 * <p>
 * This class demonstrates the concept of greedy algorithms through a simple example:
 * <p>
 * Problem: You can choose 10 bills, each either worth $1 or $100, to maximize the total amount.
 * <p>
 * Key Insights:
 * <p>
 * 1. The Essence of Algorithms is Exhaustive Enumeration:
 * - At its core, all algorithms involve exploring possible solutions.
 * - For this problem, we have 2^10 possible combinations (choosing either $1 or $100 for each position).
 * <p>
 * 2. Recursive Thinking:
 * - The problem can be visualized as a binary tree with height 10.
 * - Each node represents a choice between $1 and $100.
 * - The complete tree would represent all possible combinations.
 * <p>
 * 3. Greedy Choice Property:
 * - In this problem, the optimal strategy is always to choose the higher denomination ($100).
 * - This "greedy" choice at each step leads to the globally optimal solution.
 * <p>
 * 4. Optimization Path:
 * - We start with a recursive solution (exponential complexity: O(2^n))
 * - Then optimize by recognizing we always choose $100 (linear complexity: O(n))
 * - Finally optimize to a direct calculation (constant complexity: O(1))
 * <p>
 * 5. Greedy Algorithm Essence:
 * - Make the locally optimal choice at each step.
 * - In appropriate problems, this leads to the globally optimal solution.
 * - This dramatically reduces computational complexity.
 * <p>
 * Note: Not all problems can be solved optimally with greedy algorithms.
 * The problem must have the "greedy choice property" where local optimal
 * choices lead to a global optimum.
 */
public class GreedyAlgorithmExample {
    /**
     * Initial recursive approach - exponential complexity O(2^n)
     * This represents the exhaustive enumeration approach
     */
    public static int findMaxRecursive(int n) {
        if (n == 0) return 0;

        // Choose $1 bill for this position, then solve for remaining positions
        int result1 = 1 + findMaxRecursive(n - 1);

        // Choose $100 bill for this position, then solve for remaining positions
        int result2 = 100 + findMaxRecursive(n - 1);

        // Return the better of the two choices
        return Math.max(result1, result2);
    }
}
