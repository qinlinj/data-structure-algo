package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

import java.util.*;

/**
 * 89. Gray Code
 * <p>
 * Problem Summary:
 * An n-bit gray code sequence is a sequence of 2^n integers where:
 * 1. Every integer is in the range [0, 2^n - 1]
 * 2. The first integer is 0
 * 3. An integer appears no more than once in the sequence
 * 4. The binary representation of adjacent integers differs by exactly one bit
 * 5. The binary representation of the first and last integer differs by exactly one bit
 * <p>
 * Return any valid n-bit gray code sequence.
 * <p>
 * Key Insights:
 * - This problem can be solved using backtracking
 * - We can flip one bit at a time to generate the next valid number
 * - We need to track visited numbers to avoid duplicates
 * - The sequence is valid when all 2^n numbers have been used and the last number differs from the first by one bit
 * <p>
 * Time Complexity: O(n * 2^n) where n is the number of bits
 * Space Complexity: O(2^n) to track visited numbers and store the result
 */
class _741_g_GrayCode {

    Set<Integer> used = new HashSet<>();
    LinkedList<Integer> path = new LinkedList<>();
    List<Integer> res = null;

    public List<Integer> grayCode(int n) {
        traverse(0, n);
        return res;
    }

    void traverse(int root, int n) {
        if (res != null) {
            return;
        }

        if (path.size() == (1 << n)) {
            res = new LinkedList<>(path);
            return;
        }

        if (used.contains(root)) {
            return;
        }

        // Pre-order position of multi-way tree traversal
        used.add(root);
        path.addLast(root);

        // Try flipping each bit of the current number to generate children
        for (int i = 0; i < n; i++) {
            int next = flipBit(root, i);
            traverse(next, n);
        }

        // Post-order position of multi-way tree traversal
        used.remove(root);
        path.pop();
    }

    // Flip the i-th bit (0 to 1, 1 to 0)
    int flipBit(int x, int i) {
        return x ^ (1 << i);
    }
}