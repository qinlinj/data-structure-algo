package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._843_fallout4_game;

import java.util.*;

public class _843_e_SolutionVariationsOptimizations {
    /**
     * Original top-down DP solution with memoization
     */
    public static class TopDownDPSolution {
        private Map<Character, List<Integer>> charToIndex;
        private int[][] memo;

        public int findRotateSteps(String ring, String key) {
            buildCharacterMap(ring);
            memo = new int[ring.length()][key.length()];
            for (int[] row : memo) Arrays.fill(row, -1);

            return dp(ring, 0, key, 0);
        }

        private void buildCharacterMap(String ring) {
            charToIndex = new HashMap<>();
            for (int i = 0; i < ring.length(); i++) {
                char c = ring.charAt(i);
                charToIndex.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }
        }

        private int dp(String ring, int ringPos, String key, int keyPos) {
            if (keyPos == key.length()) return 0;
            if (memo[ringPos][keyPos] != -1) return memo[ringPos][keyPos];

            int result = Integer.MAX_VALUE;
            char target = key.charAt(keyPos);

            for (int pos : charToIndex.get(target)) {
                int rotationCost = calculateMinRotation(ringPos, pos, ring.length());
                int totalCost = 1 + rotationCost + dp(ring, pos, key, keyPos + 1);
                result = Math.min(result, totalCost);
            }

            return memo[ringPos][keyPos] = result;
        }

        private int calculateMinRotation(int from, int to, int size) {
            int direct = Math.abs(to - from);
            return Math.min(direct, size - direct);
        }
    }
}
