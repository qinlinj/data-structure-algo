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

    /**
     * Performance comparison and analysis
     */
    public static class PerformanceComparator {

        public void compareAllSolutions() {
            System.out.println("=== Solution Performance Comparison ===\n");

            // Test cases of varying complexity
            String[][] testCases = {
                    {"godding", "gd"},
                    {"abcabc", "abc"},
                    {"caotmcaataijjxi", "oatjiioicitatajtijciocjcaaxaaatmctxjjjjtxjjjjtcaacttaxmaacjcocaja"},
                    {generateLargeRing(50), generateLargeKey(30)},
                    {generateLargeRing(100), generateLargeKey(50)}
            };

            TopDownDPSolution topDown = new TopDownDPSolution();
            BottomUpDPSolution bottomUp = new BottomUpDPSolution();
            SpaceOptimizedDPSolution spaceOpt = new SpaceOptimizedDPSolution();
            BFSAlternativeSolution bfs = new BFSAlternativeSolution();

            for (int i = 0; i < testCases.length; i++) {
                String ring = testCases[i][0];
                String key = testCases[i][1];

                System.out.printf("Test Case %d: Ring=%d chars, Key=%d chars\n",
                        i + 1, ring.length(), key.length());

                // Test all solutions
                testSolution("Top-Down DP", () -> topDown.findRotateSteps(ring, key));
                testSolution("Bottom-Up DP", () -> bottomUp.findRotateSteps(ring, key));
                testSolution("Space Optimized", () -> spaceOpt.findRotateSteps(ring, key));

                // Skip BFS for large test cases (too slow)
                if (i < 3) {
                    testSolution("BFS Alternative", () -> bfs.findRotateSteps(ring, key));
                }

                System.out.println();
            }

            analyzeComplexities();
        }

        private void testSolution(String name, java.util.function.Supplier<Integer> solver) {
            long startTime = System.nanoTime();
            int result = solver.get();
            long endTime = System.nanoTime();

            double timeMs = (endTime - startTime) / 1_000_000.0;
            System.out.printf("  %-20s: %d operations in %.2f ms\n", name, result, timeMs);
        }

        private String generateLargeRing(int size) {
            StringBuilder sb = new StringBuilder();
            Random rand = new Random(42);
            for (int i = 0; i < size; i++) {
                sb.append((char) ('a' + rand.nextInt(10))); // Limited alphabet for more duplicates
            }
            return sb.toString();
        }

        private String generateLargeKey(int size) {
            StringBuilder sb = new StringBuilder();
            Random rand = new Random(42);
            for (int i = 0; i < size; i++) {
                sb.append((char) ('a' + rand.nextInt(10)));
            }
            return sb.toString();
        }

        private void analyzeComplexities() {
            System.out.println("=== Complexity Analysis ===");
            System.out.println("┌─────────────────────┬──────────────────┬──────────────────┬─────────────────┐");
            System.out.println("│ Solution            │ Time Complexity  │ Space Complexity │ Implementation  │");
            System.out.println("├─────────────────────┼──────────────────┼──────────────────┼─────────────────┤");
            System.out.println("│ Top-Down DP         │ O(R × K × C)     │ O(R × K)         │ Simple          │");
            System.out.println("│ Bottom-Up DP        │ O(R × K × C)     │ O(R × K)         │ Medium          │");
            System.out.println("│ Space Optimized     │ O(R × K × C)     │ O(R)             │ Medium          │");
            System.out.println("│ BFS Alternative     │ O(R × K × C)     │ O(R × K)         │ Complex         │");
            System.out.println("└─────────────────────┴──────────────────┴──────────────────┴─────────────────┘");

            System.out.println("\nWhere:");
            System.out.println("R = Ring length");
            System.out.println("K = Key length");
            System.out.println("C = Average character occurrences in ring");

            System.out.println("\nRecommendations:");
            System.out.println("• Top-Down DP: Best for interviews (clear logic, easy to understand)");
            System.out.println("• Space Optimized: Best for memory-constrained environments");
            System.out.println("• Bottom-Up DP: Best for avoiding recursion stack overflow");
            System.out.println("• BFS: Educational value, demonstrates alternative thinking");
        }
    }

    /**
     * Educational insights about different approaches
     */
    public static class EducationalInsights {

        public static void explainApproachDifferences() {
            System.out.println("=== Understanding Different Approaches ===\n");

            explainTopDownVsBottomUp();
            explainSpaceOptimization();
            explainBFSApproach();
            discussTradeoffs();
        }

        private static void explainTopDownVsBottomUp() {
            System.out.println("1. Top-Down vs Bottom-Up DP:");
            System.out.println("-----------------------------");

            System.out.println("Top-Down (Memoization):");
            System.out.println("• Natural recursive structure");
            System.out.println("• Only computes needed subproblems");
            System.out.println("• Easier to understand and implement");
            System.out.println("• Risk of stack overflow for deep recursion");

            System.out.println("\nBottom-Up (Tabulation):");
            System.out.println("• Iterative approach, no recursion");
            System.out.println("• Computes all subproblems systematically");
            System.out.println("• Better space locality, cache-friendly");
            System.out.println("• More complex state transition logic");

            System.out.println("\nChoice depends on:");
            System.out.println("• Problem structure and constraints");
            System.out.println("• Memory limitations");
            System.out.println("• Performance requirements");
        }

        private static void explainSpaceOptimization() {
            System.out.println("\n2. Space Optimization Technique:");
            System.out.println("--------------------------------");

            System.out.println("Observation: Each DP state only depends on previous layer");
            System.out.println("• Original: O(R × K) space for full DP table");
            System.out.println("• Optimized: O(R) space using only two arrays");
            System.out.println("• Trade-off: Cannot reconstruct optimal path");

            System.out.println("\nImplementation pattern:");
            System.out.println("1. Use two 1D arrays: prev[] and curr[]");
            System.out.println("2. Process one key character at a time");
            System.out.println("3. Swap arrays after each iteration");
            System.out.println("4. Final result in prev[] array");
        }

        private static void explainBFSApproach() {
            System.out.println("\n3. BFS Alternative Approach:");
            System.out.println("----------------------------");

            System.out.println("Key insight: Treat as shortest path problem");
            System.out.println("• States: (ring_position, key_index) pairs");
            System.out.println("• Edges: Valid transitions with rotation costs");
            System.out.println("• Goal: Find shortest path to final state");

            System.out.println("\nAdvantages:");
            System.out.println("• Guarantees optimal solution");
            System.out.println("• Can handle complex cost functions");
            System.out.println("• Natural for graph-based thinking");

            System.out.println("\nDisadvantages:");
            System.out.println("• Higher memory usage (priority queue)");
            System.out.println("• More complex implementation");
            System.out.println("• Slower than DP for this specific problem");
        }

        private static void discussTradeoffs() {
            System.out.println("\n4. Approach Trade-offs Summary:");
            System.out.println("-------------------------------");

            System.out.println("┌─────────────────┬───────────┬──────────┬─────────────┬─────────────┐");
            System.out.println("│ Approach        │ Simplicity│ Memory   │ Performance │ Flexibility │");
            System.out.println("├─────────────────┼───────────┼──────────┼─────────────┼─────────────┤");
            System.out.println("│ Top-Down DP     │ High      │ Medium   │ Good        │ High        │");
            System.out.println("│ Bottom-Up DP    │ Medium    │ Medium   │ Good        │ Medium      │");
            System.out.println("│ Space Optimized │ Medium    │ Low      │ Good        │ Low         │");
            System.out.println("│ BFS Alternative │ Low       │ High     │ Fair        │ High        │");
            System.out.println("└─────────────────┴───────────┴──────────┴─────────────┴─────────────┘");

            System.out.println("\nWhen to use each:");
            System.out.println("• Interviews: Top-Down DP (clear logic)");
            System.out.println("• Production: Bottom-Up DP (no stack overflow risk)");
            System.out.println("• Memory-constrained: Space Optimized");
            System.out.println("• Research/Complex costs: BFS Alternative");
        }
    }
}
