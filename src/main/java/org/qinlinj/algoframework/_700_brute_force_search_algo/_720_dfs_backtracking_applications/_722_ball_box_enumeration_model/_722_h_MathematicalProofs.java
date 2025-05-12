package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._722_ball_box_enumeration_model;

/**
 * MATHEMATICAL PROOFS FOR PERMUTATION AND COMBINATION FORMULAS
 * <p>
 * This class presents the mathematical derivations of permutation and combination
 * formulas from both the box and ball perspectives of the box-ball model.
 * <p>
 * Key insights:
 * 1. Both perspectives lead to the same mathematical formulas but through different recursive relationships
 * 2. The derivations explain why there are n!/((n-k)!) permutations and n!/(k!(n-k)!) combinations
 * 3. Understanding these mathematical foundations deepens our understanding of backtracking algorithms
 */
public class _722_h_MathematicalProofs {

    /**
     * Main method to demonstrate the mathematical proofs
     */
    public static void main(String[] args) {
        System.out.println("MATHEMATICAL PROOFS FOR PERMUTATION AND COMBINATION FORMULAS");
        System.out.println("=========================================================");

        // Permutation proofs
        System.out.println("\nPERMUTATION FORMULA PROOFS");
        System.out.println("-------------------------");

        System.out.println("\nBox Perspective Derivation:");
        System.out.println("P(n,k) represents the number of ways to arrange k elements from n distinct elements.");
        System.out.println("From the box perspective, we place elements into k ordered boxes:");
        System.out.println("- For the first box, we can choose any of the n elements");
        System.out.println("- For the second box, we have n-1 elements remaining");
        System.out.println("- And so on until we've filled k boxes");
        System.out.println("\nThis gives us the recursive relationship:");
        System.out.println("P(n,k) = n * P(n-1, k-1)");
        System.out.println("\nExpanding this recursion:");
        System.out.println("P(n,k) = n * (n-1) * (n-2) * ... * (n-k+1)");
        System.out.println("P(n,k) = n! / (n-k)!");

        System.out.println("\nBall Perspective Derivation:");
        System.out.println("From the ball perspective, each element decides where (or whether) to go:");
        System.out.println("- The first element can either not be used, or be placed in one of the k positions");
        System.out.println("- This gives us the recursive relationship:");
        System.out.println("P(n,k) = P(n-1,k) + k * P(n-1,k-1)");
        System.out.println("\nWhere:");
        System.out.println("- P(n-1,k): number of ways when first element is not used");
        System.out.println("- k * P(n-1,k-1): number of ways when first element is used (in any of k positions)");
        System.out.println("\nThis recursive formula also resolves to:");
        System.out.println("P(n,k) = n! / (n-k)!");

        // Combination proofs
        System.out.println("\nCOMBINATION FORMULA PROOFS");
        System.out.println("-------------------------");

        System.out.println("\nBox Perspective Derivation:");
        System.out.println("C(n,k) represents the number of ways to select k elements from n distinct elements.");
        System.out.println("From the box perspective with a single box of capacity k:");
        System.out.println("- We first select any element from n possibilities");
        System.out.println("- Then we need to select k-1 more elements from the remaining n-1 elements");
        System.out.println("- However, the order doesn't matter, so we need to adjust for overcounting");
        System.out.println("\nThis gives us the recursive relationship:");
        System.out.println("C(n,k) = (n * C(n-1,k-1)) / k");
        System.out.println("\nWhich resolves to:");
        System.out.println("C(n,k) = n! / (k! * (n-k)!)");

        System.out.println("\nBall Perspective Derivation:");
        System.out.println("From the ball perspective, each element decides whether to be included:");
        System.out.println("- The first element can either not be included, or be included");
        System.out.println("- This gives us the recursive relationship:");
        System.out.println("C(n,k) = C(n-1,k) + C(n-1,k-1)");
        System.out.println("\nWhere:");
        System.out.println("- C(n-1,k): number of ways when first element is not included");
        System.out.println("- C(n-1,k-1): number of ways when first element is included");
        System.out.println("\nThis is the well-known Pascal's identity, and it resolves to:");
        System.out.println("C(n,k) = n! / (k! * (n-k)!)");

        // Key insights
        System.out.println("\nKEY INSIGHTS FROM MATHEMATICAL PROOFS");
        System.out.println("----------------------------------");
        System.out.println("1. Both perspectives lead to the same formulas, showing their mathematical equivalence");
        System.out.println("2. The ball perspective naturally leads to the binary tree structure for subsets");
        System.out.println("3. The box perspective naturally leads to the n-ary tree structure for permutations");
        System.out.println("4. Understanding these derivations helps us understand why:");
        System.out.println("   - There are 2^n subsets of a set with n elements");
        System.out.println("   - There are n! permutations of n distinct elements");
        System.out.println("   - There are C(n,k) ways to select k elements from n elements");
    }

    /**
     * Calculates factorial of n
     */
    private static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    /**
     * Calculates permutation P(n,k)
     */
    private static long permutation(int n, int k) {
        return factorial(n) / factorial(n - k);
    }

    /**
     * Calculates combination C(n,k)
     */
    private static long combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }
}