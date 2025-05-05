package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._521_constant_time_array_operations;

import java.util.*;

/**
 * _513_b_RandomPickWithBlacklist
 * <p>
 * Random Number Generator that Avoids Blacklisted Numbers
 * <p>
 * Key concepts:
 * 1. Problem: Generate random numbers in the range [0,N) excluding blacklisted numbers
 * with equal probability for each valid number.
 * <p>
 * 2. Implementation approach:
 * - Conceptually, we want to "move" all blacklisted numbers to the end of the range
 * - Create a virtual array of size (N - blacklist.length) containing only valid numbers
 * - Use a HashMap to map blacklisted numbers in range [0, validSize) to valid numbers
 * in range [validSize, N)
 * <p>
 * 3. Key optimization techniques:
 * - We only need to map blacklisted numbers that are in the range [0, validSize)
 * - When finding valid numbers to map to, we must skip numbers that are also blacklisted
 * - This approach allows O(1) random number generation with minimal calls to the random function
 * <p>
 * 4. The solution efficiently handles the constraints by creating a virtual remapping
 * rather than physically rearranging elements, which would be impossible since we
 * don't actually have an array with N elements.
 */
public class _521_b_RandomPickWithBlacklist {
    private int size; // The effective size (N - blacklist.length)
    private Map<Integer, Integer> mapping; // Maps blacklisted numbers to valid ones
    private Random rand;

    /**
     * Initialize the solution with range [0,N) and blacklisted numbers
     *
     * @param N         The upper bound of the range (exclusive)
     * @param blacklist Array of blacklisted numbers
     */
    public _521_b_RandomPickWithBlacklist(int N, int[] blacklist) {
        // The size of the virtual array containing only valid numbers
        size = N - blacklist.length;
        mapping = new HashMap<>();
        rand = new Random();

        // Create a set of all blacklisted numbers for O(1) lookup
        Set<Integer> blackSet = new HashSet<>();
        for (int b : blacklist) {
            blackSet.add(b);
        }

        // Start mapping from the end of the range
        int last = N - 1;

        for (int b : blacklist) {
            // Skip blacklisted numbers that are already in the "virtual" excluded portion
            if (b >= size) {
                continue;
            }

            // Find a valid number to map to
            // Skip numbers that are also blacklisted
            while (blackSet.contains(last)) {
                last--;
            }

            // Map the blacklisted number to a valid one
            mapping.put(b, last);
            last--;
        }
    }

    public static void main(String[] args) {
        // Example: N=5, blacklist=[1,3]
        // Valid numbers are 0, 2, 4
        _521_b_RandomPickWithBlacklist solution = new _521_b_RandomPickWithBlacklist(5, new int[]{1, 3});

        // Count frequencies to verify equal probability
        Map<Integer, Integer> freq = new HashMap<>();
        int trials = 3000;

        for (int i = 0; i < trials; i++) {
            int num = solution.pick();
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        System.out.println("Random picks distribution:");
        for (int key : freq.keySet()) {
            System.out.println(key + ": " + freq.get(key) + " times (" +
                    (freq.get(key) * 100.0 / trials) + "%)");
        }
    }

    /**
     * Returns a random valid number (not in blacklist) with equal probability
     *
     * @return a random number in the range [0,N) that is not blacklisted
     */
    public int pick() {
        // Generate a random index in the range [0, size)
        int index = rand.nextInt(size);

        // If the index is a blacklisted number, return its mapped valid number
        if (mapping.containsKey(index)) {
            return mapping.get(index);
        }

        // Otherwise, the index itself is a valid number
        return index;
    }
}