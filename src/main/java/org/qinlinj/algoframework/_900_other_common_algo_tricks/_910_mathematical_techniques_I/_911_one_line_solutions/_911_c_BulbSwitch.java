package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._911_one_line_solutions;

/**
 * BULB SWITCH - Brain Teaser Algorithm Problem
 * <p>
 * Problem: You have n bulbs, all initially off. Perform n rounds of operations:
 * Round 1: Toggle every bulb (1, 2, 3, 4, ...)
 * Round 2: Toggle every 2nd bulb (2, 4, 6, 8, ...)
 * Round 3: Toggle every 3rd bulb (3, 6, 9, 12, ...)
 * ...
 * Round n: Toggle only the nth bulb
 * <p>
 * Question: How many bulbs are on after n rounds?
 * <p>
 * Key Insight: A bulb is ON if it's toggled an ODD number of times.
 * Bulb i is toggled in round j if and only if j divides i evenly.
 * So bulb i is toggled once for each divisor of i.
 * <p>
 * Most numbers have even number of divisors (they come in pairs: d and n/d).
 * Exception: Perfect squares! For perfect squares, one divisor (√n) pairs with itself.
 * <p>
 * Example: Bulb 16 has divisors: 1,2,4,8,16 (from pairs 1×16, 2×8, 4×4)
 * That's 5 toggles (odd) → bulb stays ON
 * <p>
 * Solution: Count perfect squares ≤ n, which is floor(√n)
 * <p>
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
public class _911_c_BulbSwitch {

    public static void main(String[] args) {
        _911_c_BulbSwitch bulbGame = new _911_c_BulbSwitch();

        System.out.println("=== Bulb Switch Test Cases ===");

        // Test various values
        int[] testCases = {1, 3, 4, 9, 16, 25, 100};

        for (int n : testCases) {
            int mathResult = bulbGame.bulbSwitch(n);
            int simResult = bulbGame.simulateBulbSwitch(n);

            System.out.printf("n=%d: Math solution=%d, Simulation=%d ✓\n",
                    n, mathResult, simResult);
        }

        System.out.println("\n=== Detailed Analysis ===");

        // Demonstrate why certain bulbs stay on
        int[] analyzeList = {1, 4, 9, 16, 25};
        for (int bulb : analyzeList) {
            bulbGame.demonstrateBulb(bulb);
            System.out.println();
        }

        System.out.println("=== Pattern Recognition ===");
        System.out.println("Perfect squares ≤ 100:");
        for (int i = 1; i * i <= 100; i++) {
            System.out.print(i * i + " ");
        }
        System.out.println("\nCount: " + (int) Math.sqrt(100));

        System.out.println("\n=== Why This Works ===");
        System.out.println("• Most numbers have even divisors (pairs: d and n/d)");
        System.out.println("• Perfect squares have odd divisors (√n pairs with itself)");
        System.out.println("• Odd toggles = ON, Even toggles = OFF");
        System.out.println("• Therefore: Only perfect squares stay ON");
        System.out.println("• Answer: floor(√n) = count of perfect squares ≤ n");
    }

    /**
     * Calculates how many bulbs are on after n rounds of toggling
     *
     * @param n number of bulbs and rounds
     * @return number of bulbs that are on
     */
    public int bulbSwitch(int n) {
        // Only perfect squares will be on (odd number of divisors)
        // Count of perfect squares ≤ n is floor(√n)
        return (int) Math.sqrt(n);
    }

    /**
     * Demonstrates the divisor pattern for a specific bulb
     */
    public void demonstrateBulb(int bulbNum) {
        System.out.println("Analyzing bulb " + bulbNum + ":");

        // Find all divisors
        java.util.List<Integer> divisors = new java.util.ArrayList<>();
        for (int i = 1; i <= bulbNum; i++) {
            if (bulbNum % i == 0) {
                divisors.add(i);
            }
        }

        System.out.println("Divisors: " + divisors);
        System.out.println("Number of toggles: " + divisors.size());

        boolean isPerfectSquare = (int) Math.sqrt(bulbNum) * (int) Math.sqrt(bulbNum) == bulbNum;
        String status = (divisors.size() % 2 == 1) ? "ON" : "OFF";

        System.out.println("Perfect square: " + isPerfectSquare);
        System.out.println("Final status: " + status);

        // Show factor pairs
        System.out.print("Factor pairs: ");
        for (int d : divisors) {
            int pair = bulbNum / d;
            if (d <= pair) {
                if (d == pair) {
                    System.out.print(d + "×" + d + " ");
                } else {
                    System.out.print(d + "×" + pair + " ");
                }
            }
        }
        System.out.println();
    }

    /**
     * Simulates the actual bulb switching process (for verification)
     */
    public int simulateBulbSwitch(int n) {
        boolean[] bulbs = new boolean[n + 1]; // 1-indexed

        // Perform n rounds
        for (int round = 1; round <= n; round++) {
            for (int bulb = round; bulb <= n; bulb += round) {
                bulbs[bulb] = !bulbs[bulb]; // toggle
            }
        }

        // Count bulbs that are on
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (bulbs[i]) count++;
        }

        return count;
    }
}