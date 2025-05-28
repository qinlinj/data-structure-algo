package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._843_fallout4_game;

import java.util.*;

/**
 * COMPLETE FREEDOM TRAIL SOLUTION WITH COMPREHENSIVE ANALYSIS
 * <p>
 * Problem: Given a circular ring of characters and a key string,
 * find minimum operations to spell the key by rotating ring and pressing button.
 * <p>
 * Solution Approach:
 * 1. DP State: (current_ring_position, current_key_index)
 * 2. Transition: For each occurrence of key[j], try both rotation directions
 * 3. Cost: rotation_steps + 1 (button press) + future_cost
 * 4. Optimization: Memoization to avoid recomputing same states
 * <p>
 * Key Insights:
 * - Multiple occurrences of same character create choice points
 * - Must consider global optimization, not just local minimum distance
 * - Circular nature means rotation can go both clockwise/counterclockwise
 * <p>
 * Time Complexity: O(ring.length × key.length × avg_char_occurrences)
 * Space Complexity: O(ring.length × key.length) for memoization
 * <p>
 * LeetCode 514: Freedom Trail (Hard)
 */

public class _843_d_CompleteFreedomTrailSolution {
    /**
     * Main solution class for Freedom Trail problem
     */
    public static class FreedomTrailSolver {
        // Character to positions mapping for quick lookup
        private Map<Character, List<Integer>> charToIndex;
        // Memoization table to store computed results
        private int[][] memo;

        /**
         * Main entry point for solving Freedom Trail problem
         *
         * @param ring circular ring of characters
         * @param key  target string to spell
         * @return minimum operations needed
         */
        public int findRotateSteps(String ring, String key) {
            int m = ring.length();
            int n = key.length();

            // Initialize memoization table
            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            // Build character to positions mapping
            buildCharacterMap(ring);

            // Start DP from position 0 (12 o'clock) and key index 0
            return dp(ring, 0, key, 0);
        }

        /**
         * Build mapping from characters to their positions in the ring
         */
        private void buildCharacterMap(String ring) {
            charToIndex = new HashMap<>();
            for (int i = 0; i < ring.length(); i++) {
                char c = ring.charAt(i);
                charToIndex.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }
        }

        /**
         * DP function with memoization
         *
         * @param ring the circular ring string
         * @param i    current position of pointer on ring
         * @param key  the target string to spell
         * @param j    current index in key string
         * @return minimum operations to spell key[j..] starting from ring[i]
         */
        private int dp(String ring, int i, String key, int j) {
            // Base case: finished spelling the entire key
            if (j == key.length()) {
                return 0;
            }

            // Check memoization table
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            int n = ring.length();
            int result = Integer.MAX_VALUE;
            char targetChar = key.charAt(j);

            // Try all positions where target character appears
            List<Integer> positions = charToIndex.get(targetChar);
            for (int k : positions) {
                // Calculate rotation cost to reach position k from position i
                int rotationCost = calculateRotationCost(i, k, n);

                // Recursively solve for remaining key string
                int futureCost = dp(ring, k, key, j + 1);

                // Total cost = rotation + button press + future operations
                int totalCost = rotationCost + 1 + futureCost;
                result = Math.min(result, totalCost);
            }

            // Store result in memoization table
            memo[i][j] = result;
            return result;
        }

        /**
         * Calculate minimum rotation steps between two positions on circular ring
         *
         * @param from     starting position
         * @param to       target position
         * @param ringSize size of the ring
         * @return minimum rotation steps (clockwise or counterclockwise)
         */
        private int calculateRotationCost(int from, int to, int ringSize) {
            int clockwise = Math.abs(to - from);
            int counterclockwise = ringSize - clockwise;
            return Math.min(clockwise, counterclockwise);
        }
    }
}
