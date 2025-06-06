package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._912_common_bit_operations;

/**
 * n & (n-1) - ELIMINATE LAST SET BIT TECHNIQUE
 * <p>
 * This class demonstrates the powerful bit manipulation technique n & (n-1) which
 * eliminates the rightmost set bit (last 1) in the binary representation of n.
 * <p>
 * Key Concepts:
 * 1. Core Operation: n & (n-1) removes the rightmost 1 bit from n
 * - Works because (n-1) flips the rightmost 1 and all bits to its right
 * - AND operation then clears only the original rightmost 1
 * <p>
 * 2. Applications:
 * - Count set bits (Hamming Weight): Keep removing 1s until n becomes 0
 * - Check power of 2: A power of 2 has exactly one 1 bit
 * - Various bit manipulation algorithms
 * <p>
 * 3. How it works:
 * Example: n = 12 (1100), n-1 = 11 (1011)
 * n & (n-1) = 1100 & 1011 = 1000 (removed rightmost 1)
 * <p>
 * Time Complexity: O(k) where k is number of set bits, O(log n) worst case
 * Space Complexity: O(1)
 */
public class _912_c_EliminateLastBit {

    public static void main(String[] args) {
        _912_c_EliminateLastBit eliminator = new _912_c_EliminateLastBit();

        // Demonstrate bit elimination process
        int[] testNumbers = {12, 15, 7, 8, 31};
        for (int num : testNumbers) {
            eliminator.demonstrateElimination(num);
        }

        // Explain the mechanism
        eliminator.explainMechanism(12);

        // Test power of 2 detection
        eliminator.testPowerOfTwo();

        // Compare counting methods
        System.out.println();
        eliminator.compareCountingMethods(255);
        eliminator.compareCountingMethods(1023);

        // LeetCode examples
        System.out.println("=== LeetCode Examples ===");

        // Example 1: Hamming Weight
        System.out.println("LeetCode 191 - Number of 1 Bits:");
        int[] hammingTests = {11, 128, 2147483645};
        for (int test : hammingTests) {
            int count = eliminator.hammingWeight(test);
            System.out.printf("Input: %d, Binary: %s, Output: %d\n",
                    test, Integer.toBinaryString(test), count);
        }

        System.out.println("\nLeetCode 231 - Power of Two:");
        int[] powerTests = {1, 16, 3, 4, 5};
        for (int test : powerTests) {
            boolean isPower = eliminator.isPowerOfTwo(test);
            System.out.printf("Input: %d, Output: %b\n", test, isPower);
        }

        System.out.println("\n=== Key Insights ===");
        System.out.println("1. n & (n-1) eliminates the rightmost 1 bit");
        System.out.println("2. Useful for counting set bits efficiently");
        System.out.println("3. Powers of 2 have exactly one 1 bit");
        System.out.println("4. More efficient than checking all bits individually");
        System.out.println("5. Time complexity depends on number of 1s, not total bits");
    }

    /**
     * Counts the number of 1s in binary representation (Hamming Weight)
     * LeetCode 191: Number of 1 Bits
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);  // Remove rightmost 1
            count++;
        }
        return count;
    }

    /**
     * Alternative implementation treating n as unsigned value
     */
    public int hammingWeightUnsigned(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);  // Remove rightmost 1
            count++;
        }
        return count;
    }

    /**
     * Checks if a number is power of 2
     * LeetCode 231: Power of Two
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    /**
     * Demonstrates step-by-step elimination of bits
     */
    public void demonstrateElimination(int n) {
        System.out.printf("=== Eliminating bits from %d ===\n", n);
        System.out.printf("Binary: %s\n", Integer.toBinaryString(n));

        int original = n;
        int step = 0;

        while (n != 0) {
            int prev = n;
            n = n & (n - 1);
            step++;

            System.out.printf("Step %d: %s & %s = %s (%d -> %d)\n",
                    step,
                    String.format("%8s", Integer.toBinaryString(prev)).replace(' ', '0'),
                    String.format("%8s", Integer.toBinaryString(prev - 1)).replace(' ', '0'),
                    String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0'),
                    prev, n);
        }

        System.out.printf("Total 1s in %d: %d\n\n", original, step);
    }

    /**
     * Visualizes why n & (n-1) works
     */
    public void explainMechanism(int n) {
        System.out.printf("=== Why n & (n-1) eliminates rightmost 1 ===\n");
        System.out.printf("n = %d (binary: %s)\n", n, Integer.toBinaryString(n));
        System.out.printf("n-1 = %d (binary: %s)\n", n - 1, Integer.toBinaryString(n - 1));

        // Find rightmost 1 position
        int rightmostOne = n & (-n);  // Isolate rightmost 1
        System.out.printf("Rightmost 1 is at position: %s\n", Integer.toBinaryString(rightmostOne));

        System.out.println("\nWhat happens when we subtract 1:");
        System.out.println("1. The rightmost 1 becomes 0");
        System.out.println("2. All 0s to the right become 1s");
        System.out.println("3. All bits to the left remain unchanged");

        System.out.println("\nWhen we AND n with (n-1):");
        System.out.println("1. The flipped rightmost 1 becomes 0");
        System.out.println("2. The flipped trailing 0s become 0 (since n has 0s there)");
        System.out.println("3. Left bits remain unchanged (both have same values)");

        int result = n & (n - 1);
        System.out.printf("\nResult: %d (binary: %s)\n\n", result, Integer.toBinaryString(result));
    }

    /**
     * Tests power of 2 detection with various numbers
     */
    public void testPowerOfTwo() {
        System.out.println("=== Power of 2 Detection ===");
        int[] testNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 15, 16, 31, 32, 63, 64, 100, 128};

        for (int num : testNumbers) {
            boolean isPower = isPowerOfTwo(num);
            String binary = Integer.toBinaryString(num);
            int setBits = hammingWeight(num);

            System.out.printf("%3d: %8s (set bits: %d) -> Power of 2: %5b\n",
                    num, binary, setBits, isPower);
        }

        System.out.println("\nPattern: Powers of 2 have exactly 1 set bit!");
    }

    /**
     * Compares different methods to count set bits
     */
    public void compareCountingMethods(int n) {
        System.out.printf("=== Comparing bit counting methods for %d ===\n", n);

        // Method 1: Built-in function
        int builtin = Integer.bitCount(n);

        // Method 2: Loop through all bits
        int loopCount = 0;
        int temp = n;
        while (temp != 0) {
            if ((temp & 1) == 1) loopCount++;
            temp >>= 1;
        }

        // Method 3: n & (n-1) technique
        int eliminateCount = hammingWeight(n);

        System.out.printf("Built-in Integer.bitCount(): %d\n", builtin);
        System.out.printf("Loop through all bits: %d\n", loopCount);
        System.out.printf("n & (n-1) technique: %d\n", eliminateCount);
        System.out.printf("All methods agree: %b\n\n",
                builtin == loopCount && loopCount == eliminateCount);
    }
}