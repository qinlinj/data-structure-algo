package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._922_efficient_modular_exponentiation;

import java.util.*;

public class _922_a_SuperPowerMain {

    private static final int BASE = 1337;

    public static void main(String[] args) {
        _922_a_SuperPowerMain solution = new _922_a_SuperPowerMain();

        // Test Case 1: Small example
        System.out.println("=== Super Power Algorithm Demo ===\n");

        int[] b1 = {1, 5, 6, 4};
        int result1 = solution.superPow(2, b1);
        System.out.println("Test 1: 2^1564 mod 1337 = " + result1);
        System.out.println("Array representation: [1,5,6,4] means exponent 1564\n");

        // Test Case 2: Single digit
        int[] b2 = {3};
        int result2 = solution.superPow(2, b2);
        System.out.println("Test 2: 2^3 mod 1337 = " + result2);
        System.out.println("Expected: 8 (since 2^3 = 8)\n");

        // Test Case 3: Large number
        int[] b3 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int result3 = solution.superPow(2, b3);
        System.out.println("Test 3: 2^100000000000 mod 1337 = " + result3);
        System.out.println("This demonstrates handling very large exponents\n");

        // Demonstrate the recursive breakdown
        System.out.println("=== Algorithm Breakdown Demo ===");
        demonstrateRecursiveBreakdown();
    }

    private static void demonstrateRecursiveBreakdown() {
        System.out.println("For a^1564, the algorithm breaks it down as:");
        System.out.println("1564 -> last digit: 4, remaining: 156");
        System.out.println("a^1564 = a^4 * (a^156)^10");
        System.out.println();
        System.out.println("156 -> last digit: 6, remaining: 15");
        System.out.println("a^156 = a^6 * (a^15)^10");
        System.out.println();
        System.out.println("15 -> last digit: 5, remaining: 1");
        System.out.println("a^15 = a^5 * (a^1)^10");
        System.out.println();
        System.out.println("1 -> last digit: 1, remaining: empty");
        System.out.println("a^1 = a^1 * (1)^10 = a");
    }

    /**
     * Main solution for Super Power problem
     * Time Complexity: O(n) where n is length of array b
     * Space Complexity: O(n) due to recursion stack
     */
    public int superPow(int a, int[] b) {
        // Convert array to list for easier manipulation
        List<Integer> bList = new ArrayList<>();
        for (int digit : b) {
            bList.add(digit);
        }
        return superPowHelper(a, bList);
    }

    private int superPowHelper(int a, List<Integer> b) {
        // Base case: empty array means exponent is 0, result is 1
        if (b.isEmpty()) return 1;

        // Extract the last digit
        int lastDigit = b.remove(b.size() - 1);

        // Recursive decomposition:
        // a^(b[0]b[1]...b[n-1]) = a^(last_digit) * (a^(remaining_digits))^10
        int part1 = myPow(a, lastDigit);
        int part2 = myPow(superPowHelper(a, b), 10);

        // Apply modular arithmetic to prevent overflow
        return (part1 * part2) % BASE;
    }

    /**
     * Basic power calculation with modular arithmetic
     * This is the simple O(k) approach
     */
    private int myPow(int a, int k) {
        if (k == 0) return 1;

        // Apply modulo to base to keep numbers manageable
        a %= BASE;
        int result = 1;

        // Simple multiplication approach
        for (int i = 0; i < k; i++) {
            result = (result * a) % BASE;
        }

        return result;
    }
}
