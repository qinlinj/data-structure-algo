package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._722_ball_box_enumeration_model;

/**
 * THE BOX-BALL MODEL FOR BACKTRACKING ALGORITHMS
 * <p>
 * This class explains the core concept of the box-ball model that underlies all
 * backtracking algorithms for permutation, combination, and subset problems.
 * <p>
 * Key insights:
 * 1. All exhaustive enumeration algorithms can be viewed through the "box-ball model"
 * 2. There are two perspectives for enumeration: the "ball" perspective and the "box" perspective
 * 3. While mathematically equivalent, the implementation complexity may differ between these perspectives
 * <p>
 * In mathematics:
 * - P(n,k): Number of ways to arrange k elements from n distinct elements (permutation)
 * - C(n,k): Number of ways to select k elements from n distinct elements (combination)
 * <p>
 * The box-ball model perspectives:
 * - Box perspective: Each position (box) selects an element (ball)
 * - Ball perspective: Each element (ball) decides whether to be placed and in which position (box)
 */
public class _722_a_BoxBallModel {

    /**
     * Main method to demonstrate the box-ball model concept
     */
    public static void main(String[] args) {
        System.out.println("THE BOX-BALL MODEL FOR BACKTRACKING ALGORITHMS");
        System.out.println("==============================================");

        // Explain the box-ball model
        System.out.println("\nCore Concept:");
        System.out.println("The box-ball model is a fundamental way to understand all backtracking problems.");
        System.out.println("It provides two perspectives for exhaustive enumeration:");

        // Box perspective explanation
        System.out.println("\n1. Box Perspective:");
        System.out.println("   - Each position (box) selects an element (ball)");
        System.out.println("   - We iterate through positions and for each position");
        System.out.println("     we consider all possible elements to place there");
        System.out.println("   - This is like asking: 'For this position, which element should I choose?'");

        // Ball perspective explanation
        System.out.println("\n2. Ball Perspective:");
        System.out.println("   - Each element (ball) decides whether/where to be placed");
        System.out.println("   - We iterate through elements and for each element");
        System.out.println("     we consider all possible positions or whether to include it");
        System.out.println("   - This is like asking: 'For this element, should I include it and where?'");

        // Mathematical relationships
        System.out.println("\nMathematical Formulations:");
        System.out.println("Permutation (P(n,k)):");
        System.out.println("Box perspective: P(n,k) = n * P(n-1, k-1)");
        System.out.println("Ball perspective: P(n,k) = P(n-1,k) + k * P(n-1,k-1)");

        System.out.println("\nCombination (C(n,k)):");
        System.out.println("Box perspective: C(n,k) = (n * C(n-1,k-1)) / k");
        System.out.println("Ball perspective: C(n,k) = C(n-1,k) + C(n-1,k-1)");

        // Practical implications
        System.out.println("\nPractical Implications:");
        System.out.println("1. Both perspectives are valid and yield correct results");
        System.out.println("2. The implementation complexity may differ");
        System.out.println("3. The box perspective often allows for optimizations (e.g., using swap instead of used[] array)");
        System.out.println("4. Choose the perspective that leads to cleaner, more efficient code for your specific problem");
    }
}