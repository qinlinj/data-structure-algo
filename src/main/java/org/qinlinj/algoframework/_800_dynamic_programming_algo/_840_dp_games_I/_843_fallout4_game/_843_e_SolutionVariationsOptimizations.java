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

    /**
     * Bottom-up DP solution (iterative approach)
     */
    public static class BottomUpDPSolution {
        public int findRotateSteps(String ring, String key) {
            int m = ring.length();
            int n = key.length();

            // Build character positions map
            Map<Character, List<Integer>> charToIndex = new HashMap<>();
            for (int i = 0; i < m; i++) {
                char c = ring.charAt(i);
                charToIndex.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }

            // DP table: dp[i][j] = min steps to spell key[0..i-1] ending at ring position j
            int[][] dp = new int[n + 1][m];

            // Initialize: no characters spelled, can start at any position with 0 cost
            // But we must start at position 0, so set others to infinity
            for (int j = 1; j < m; j++) {
                dp[0][j] = Integer.MAX_VALUE;
            }
            dp[0][0] = 0; // Start at position 0

            // Fill DP table
            for (int i = 1; i <= n; i++) {
                char target = key.charAt(i - 1);
                Arrays.fill(dp[i], Integer.MAX_VALUE);

                // For each possible ending position of previous character
                for (int prevPos = 0; prevPos < m; prevPos++) {
                    if (dp[i - 1][prevPos] == Integer.MAX_VALUE) continue;

                    // Try all positions of current target character
                    for (int currPos : charToIndex.get(target)) {
                        int rotationCost = calculateMinRotation(prevPos, currPos, m);
                        int totalCost = dp[i - 1][prevPos] + 1 + rotationCost;
                        dp[i][currPos] = Math.min(dp[i][currPos], totalCost);
                    }
                }
            }

            // Find minimum among all possible ending positions
            int result = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                result = Math.min(result, dp[n][j]);
            }

            return result;
        }

        private int calculateMinRotation(int from, int to, int size) {
            int direct = Math.abs(to - from);
            return Math.min(direct, size - direct);
        }
    }

    /**
     * Space-optimized DP solution
     * Reduces space complexity from O(m*n) to O(m)
     */
    public static class SpaceOptimizedDPSolution {
        public int findRotateSteps(String ring, String key) {
            int m = ring.length();
            int n = key.length();

            // Build character positions map
            Map<Character, List<Integer>> charToIndex = new HashMap<>();
            for (int i = 0; i < m; i++) {
                char c = ring.charAt(i);
                charToIndex.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }

            // Space optimization: only keep previous and current row
            int[] prev = new int[m];
            int[] curr = new int[m];

            // Initialize: start at position 0
            Arrays.fill(prev, Integer.MAX_VALUE);
            prev[0] = 0;

            // Process each character in key
            for (int i = 0; i < n; i++) {
                char target = key.charAt(i);
                Arrays.fill(curr, Integer.MAX_VALUE);

                // For each possible previous position
                for (int prevPos = 0; prevPos < m; prevPos++) {
                    if (prev[prevPos] == Integer.MAX_VALUE) continue;

                    // Try all positions of current target character
                    for (int currPos : charToIndex.get(target)) {
                        int rotationCost = calculateMinRotation(prevPos, currPos, m);
                        int totalCost = prev[prevPos] + 1 + rotationCost;
                        curr[currPos] = Math.min(curr[currPos], totalCost);
                    }
                }

                // Swap arrays for next iteration
                int[] temp = prev;
                prev = curr;
                curr = temp;
            }

            // Find minimum result
            int result = Integer.MAX_VALUE;
            for (int cost : prev) {
                result = Math.min(result, cost);
            }

            return result;
        }

        private int calculateMinRotation(int from, int to, int size) {
            int direct = Math.abs(to - from);
            return Math.min(direct, size - direct);
        }
    }

    /**
     * Alternative state representation using BFS approach
     */
    public static class BFSAlternativeSolution {
        public int findRotateSteps(String ring, String key) {
            // Build character positions map
            Map<Character, List<Integer>> charToIndex = new HashMap<>();
            for (int i = 0; i < ring.length(); i++) {
                char c = ring.charAt(i);
                charToIndex.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }

            // BFS with state compression
            Map<String, Integer> visited = new HashMap<>();
            PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

            pq.offer(new State(0, 0, 0));

            while (!pq.isEmpty()) {
                State curr = pq.poll();

                // Check if we've completed the key
                if (curr.keyPos == key.length()) {
                    return curr.cost;
                }

                // Create state key for visited check
                String stateKey = curr.ringPos + "," + curr.keyPos;
                if (visited.containsKey(stateKey) && visited.get(stateKey) <= curr.cost) {
                    continue;
                }
                visited.put(stateKey, curr.cost);

                // Try all positions of next target character
                char target = key.charAt(curr.keyPos);
                for (int nextPos : charToIndex.get(target)) {
                    int rotationCost = calculateMinRotation(curr.ringPos, nextPos, ring.length());
                    int newCost = curr.cost + 1 + rotationCost; // +1 for button press
                    pq.offer(new State(nextPos, curr.keyPos + 1, newCost));
                }
            }

            return -1; // Should never reach here for valid inputs
        }

        private int calculateMinRotation(int from, int to, int size) {
            int direct = Math.abs(to - from);
            return Math.min(direct, size - direct);
        }

        static class State {
            int ringPos;
            int keyPos;
            int cost;

            State(int ringPos, int keyPos, int cost) {
                this.ringPos = ringPos;
                this.keyPos = keyPos;
                this.cost = cost;
            }
        }
    }
}
