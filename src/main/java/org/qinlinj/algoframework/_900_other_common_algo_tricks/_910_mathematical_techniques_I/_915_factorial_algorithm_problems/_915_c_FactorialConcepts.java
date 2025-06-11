package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._915_factorial_algorithm_problems;

/**
 * FACTORIAL TRAILING ZEROS - MATHEMATICAL CONCEPTS AND THEORY
 * <p>
 * FUNDAMENTAL CONCEPTS:
 * 1. Trailing zeros in factorials come from factors of 10 = 2 × 5
 * 2. In factorials, factors of 2 are more abundant than factors of 5
 * 3. The number of trailing zeros = number of times 10 divides n!
 * 4. This equals the number of pairs (2,5) in the prime factorization
 * <p>
 * KEY MATHEMATICAL INSIGHTS:
 * 1. Every even number contributes at least one factor of 2
 * 2. Every 5th number contributes at least one factor of 5
 * 3. Some numbers contribute multiple factors of 5 (25, 125, etc.)
 * 4. The trailingZeroes function is monotonically non-decreasing
 * <p>
 * APPLICATIONS:
 * - LeetCode 172: Count trailing zeros in n!
 * - LeetCode 793: Count how many n give exactly K trailing zeros
 * - Mathematical analysis of factorial properties
 * - Understanding binary search on functions
 * <p>
 * TIME COMPLEXITIES:
 * - Counting trailing zeros: O(log n)
 * - Finding preimage size: O(log K)
 */

public class _915_c_FactorialConcepts {
    /**
     * Demonstrates why factors of 5 are the limiting factor
     */
    public static void explainFactorDistribution(int n) {
        System.out.println("Analyzing factor distribution in " + n + "!:");
        System.out.println("-".repeat(40));

        // Count factors of 2
        int factors2 = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num % 2 == 0) {
                factors2++;
                num /= 2;
            }
        }

        // Count factors of 5
        int factors5 = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num % 5 == 0) {
                factors5++;
                num /= 5;
            }
        }

        System.out.println("Total factors of 2: " + factors2);
        System.out.println("Total factors of 5: " + factors5);
        System.out.println("Trailing zeros (min of above): " + Math.min(factors2, factors5));
        System.out.println("Ratio (factors of 2 : factors of 5): " +
                String.format("%.2f", (double) factors2 / factors5));
    }

    /**
     * Shows which numbers contribute multiple factors of 5
     */
    public static void analyzeMultipleFactors(int n) {
        System.out.println("\nNumbers contributing multiple factors of 5 up to " + n + ":");
        System.out.println("Number\tFactors of 5\tContribution");
        System.out.println("-".repeat(40));

        for (int i = 5; i <= n; i += 5) {
            int factors = 0;
            int temp = i;
            while (temp % 5 == 0) {
                factors++;
                temp /= 5;
            }
            if (factors > 1) {
                System.out.printf("%d\t%d\t\t%d extra\n", i, factors, factors - 1);
            }
        }
    }

    /**
     * Demonstrates the mathematical formula for counting factors of 5
     */
    public static void demonstrateFormula(int n) {
        System.out.println("\nMathematical formula breakdown for n = " + n + ":");
        System.out.println("Factors of 5 = ⌊n/5⌋ + ⌊n/25⌋ + ⌊n/125⌋ + ...");

        int total = 0;
        int divisor = 5;
        int term = 1;

        while (divisor <= n) {
            int contribution = n / divisor;
            System.out.printf("Term %d: ⌊%d/%d⌋ = %d\n", term, n, divisor, contribution);
            total += contribution;
            divisor *= 5;
            term++;
        }

        System.out.println("Total: " + total + " factors of 5");
        System.out.println("Therefore: " + total + " trailing zeros");
    }

    /**
     * Illustrates the binary search concept with a visual representation
     */
    public static void visualizeBinarySearch(int target) {
        System.out.println("\nBinary search visualization for K = " + target + ":");
        System.out.println("Finding n where trailingZeroes(n) = " + target);

        // Show some values around the target
        long estimate = target * 5; // Rough estimate

        System.out.println("n\ttrailingZeroes(n)");
        System.out.println("-".repeat(25));

        for (long n = Math.max(0, estimate - 10); n <= estimate + 10; n++) {
            long zeros = trailingZeroes(n);
            String marker = (zeros == target) ? " ← TARGET" : "";
            System.out.printf("%d\t%d%s\n", n, zeros, marker);
        }
    }
}
