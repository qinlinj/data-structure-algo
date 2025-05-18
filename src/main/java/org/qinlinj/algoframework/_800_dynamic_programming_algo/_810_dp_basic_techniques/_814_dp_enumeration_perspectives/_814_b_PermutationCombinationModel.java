package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._814_dp_enumeration_perspectives;

/**
 * Permutation and Combination: The "Ball-Box" Model
 * <p>
 * Summary:
 * - The ball-box model is an abstract way to understand enumeration problems in combinatorics
 * - Two important formulas in combinatorics:
 * - P(n,k): Number of permutations when selecting k elements from n distinct elements
 * - C(n,k): Number of combinations when selecting k elements from n distinct elements
 * - The key difference between permutation and combination is whether order matters
 * - There are two perspectives when solving permutation problems:
 * 1. Box perspective: Each box must select a ball
 * 2. Ball perspective: Each ball decides whether to go into a box or not
 * - These different perspectives lead to different recursive formulas but ultimately
 * yield the same mathematical result
 * - However, in programming, different enumeration perspectives can lead to
 * implementations with significant performance differences
 */
public class _814_b_PermutationCombinationModel {

    /**
     * Calculates P(n,k) - the number of permutations when selecting k elements from n distinct elements
     *
     * @param n Total number of distinct elements
     * @param k Number of elements to select
     * @return Number of possible permutations
     */
    public static long permutation(int n, int k) {
        if (k > n) return 0;
        long result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
        }
        return result;
    }

    /**
     * Calculates C(n,k) - the number of combinations when selecting k elements from n distinct elements
     *
     * @param n Total number of distinct elements
     * @param k Number of elements to select
     * @return Number of possible combinations
     */
    public static long combination(int n, int k) {
        if (k > n) return 0;
        // For optimization, we can calculate C(n,k) as C(n,min(k,n-k))
        k = Math.min(k, n - k);

        long result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }

    /**
     * Box perspective: Each box selects a ball
     * This represents the approach of calculating P(n,k) from the box perspective
     */
    public static long permutationBoxPerspective(int n, int k) {
        if (k == 0) return 1;
        // First box can select any of the n balls
        // Then we need to arrange the remaining k-1 boxes with n-1 balls
        return n * permutationBoxPerspective(n - 1, k - 1);
    }

    /**
     * Ball perspective: Each ball decides whether to enter a box
     * This represents the approach of calculating P(n,k) from the ball perspective
     */
    public static long permutationBallPerspective(int n, int k) {
        if (k == 0) return 1;
        if (n == 0) return 0;

        // First ball can either:
        // 1. Not go into any box: solve for (n-1, k)
        // 2. Go into one of k boxes: solve for (n-1, k-1) * k
        return permutationBallPerspective(n - 1, k) +
                k * permutationBallPerspective(n - 1, k - 1);
    }

    public static void main(String[] args) {
        // Demonstrate that both perspectives yield the same results
        int n = 5, k = 3;
        System.out.println("P(" + n + "," + k + ") using formula: " + permutation(n, k));
        System.out.println("P(" + n + "," + k + ") using box perspective: " + permutationBoxPerspective(n, k));
        System.out.println("P(" + n + "," + k + ") using ball perspective: " + permutationBallPerspective(n, k));
    }
}
