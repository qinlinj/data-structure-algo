package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._843_fallout4_game;

/**
 * DYNAMIC PROGRAMMING STATE DESIGN FOR FREEDOM TRAIL
 * <p>
 * Key DP Design Principles:
 * 1. State Definition: What information do we need to make optimal decisions?
 * 2. State Variables: Current pointer position (i) + current key index (j)
 * 3. Transition: For each occurrence of key[j], calculate cost and recurse
 * 4. Base Case: When all characters in key are processed (j == key.length)
 * 5. Optimization: Use memoization to avoid recalculating same states
 * <p>
 * State Space Analysis:
 * - Total states: O(ring.length × key.length)
 * - Each state computes in O(occurrences of character)
 * - Overall complexity: O(ring.length × key.length × average_occurrences)
 * <p>
 * Choice Analysis:
 * - For each target character, multiple ring positions may contain it
 * - Must consider both clockwise and counterclockwise rotation
 * - Choose position that minimizes total cost (current + future)
 */

import java.util.*;

public class _843_c_DPStateDesign {

    /**
     * Demonstrate why state design matters
     */
    public static void demonstrateStateImportance() {
        System.out.println("=== Why State Design Matters ===");

        System.out.println("\nIncorrect state design attempts:");
        System.out.println("1. State = only key index");
        System.out.println("   Problem: Don't know current ring position");
        System.out.println("   Can't calculate rotation costs");

        System.out.println("\n2. State = only ring position");
        System.out.println("   Problem: Don't know progress in key string");
        System.out.println("   Can't determine what character to target next");

        System.out.println("\n3. State = (ring position, key index) ✓");
        System.out.println("   Complete information for optimal decisions");
        System.out.println("   Can calculate all transition costs");
        System.out.println("   Can determine remaining work");

        System.out.println("\nState design principles:");
        System.out.println("- Include ALL information needed for decision making");
        System.out.println("- Minimize state space for efficiency");
        System.out.println("- Ensure states have clear transitions");
        System.out.println("- Verify base cases are well-defined");
    }

    public static void main(String[] args) {
        String ring = "godding";
        String key = "gd";

        // Analyze state space
        StateSpaceAnalyzer analyzer = new StateSpaceAnalyzer(ring, key);
        analyzer.analyzeStateSpace();

        System.out.println("\n" + "=".repeat(50));

        // Demonstrate DP solution
        DPSolutionDemo demo = new DPSolutionDemo(ring, key);
        int result = demo.solve();

        System.out.println("\n" + "=".repeat(50));

        // Explain state design importance
        demonstrateStateImportance();

        System.out.println("\nFinal result for ring=\"" + ring + "\", key=\"" + key + "\": " + result);
    }

    /**
     * DP State representation for the Freedom Trail problem
     */
    static class FreedomTrailState {
        int ringPosition;    // Current position of pointer on ring
        int keyIndex;        // Current index in key string being processed

        public FreedomTrailState(int ringPos, int keyIdx) {
            this.ringPosition = ringPos;
            this.keyIndex = keyIdx;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof FreedomTrailState)) return false;
            FreedomTrailState other = (FreedomTrailState) obj;
            return this.ringPosition == other.ringPosition && this.keyIndex == other.keyIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ringPosition, keyIndex);
        }

        @Override
        public String toString() {
            return String.format("State(ring=%d, key=%d)", ringPosition, keyIndex);
        }
    }

    /**
     * Demonstrates the state space and transitions
     */
    public static class StateSpaceAnalyzer {
        private String ring;
        private String key;
        private Map<Character, List<Integer>> charToPositions;

        public StateSpaceAnalyzer(String ring, String key) {
            this.ring = ring;
            this.key = key;
            this.charToPositions = buildCharacterMap(ring);
        }

        private Map<Character, List<Integer>> buildCharacterMap(String ring) {
            Map<Character, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < ring.length(); i++) {
                char c = ring.charAt(i);
                map.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }
            return map;
        }

        public void analyzeStateSpace() {
            System.out.println("=== State Space Analysis ===");
            System.out.println("Ring: \"" + ring + "\" (length: " + ring.length() + ")");
            System.out.println("Key: \"" + key + "\" (length: " + key.length() + ")");

            System.out.println("\nCharacter positions on ring:");
            for (Map.Entry<Character, List<Integer>> entry : charToPositions.entrySet()) {
                System.out.println("'" + entry.getKey() + "': " + entry.getValue());
            }

            int totalStates = ring.length() * key.length();
            System.out.println("\nTotal possible states: " + totalStates);

            analyzeTransitions();
        }

        private void analyzeTransitions() {
            System.out.println("\n=== State Transitions Analysis ===");

            // Example: analyze transitions for first few states
            for (int keyIdx = 0; keyIdx < Math.min(2, key.length()); keyIdx++) {
                char targetChar = key.charAt(keyIdx);
                System.out.println("\nFor key[" + keyIdx + "] = '" + targetChar + "':");

                List<Integer> positions = charToPositions.get(targetChar);
                if (positions == null) {
                    System.out.println("  Character not found in ring!");
                    continue;
                }

                // Show transitions from a few starting positions
                for (int startPos = 0; startPos < Math.min(3, ring.length()); startPos++) {
                    System.out.println("  From ring position " + startPos + ":");

                    for (int targetPos : positions) {
                        int rotationCost = calculateRotationCost(startPos, targetPos, ring.length());
                        System.out.println("    To position " + targetPos + ": " +
                                rotationCost + " rotations + 1 button press");
                    }
                }
            }
        }

        private int calculateRotationCost(int from, int to, int ringSize) {
            int clockwise = Math.abs(to - from);
            int counterclockwise = ringSize - clockwise;
            return Math.min(clockwise, counterclockwise);
        }
    }

    /**
     * Step-by-step DP solution demonstration
     */
    public static class DPSolutionDemo {
        private String ring;
        private String key;
        private Map<Character, List<Integer>> charToPositions;
        private Map<FreedomTrailState, Integer> memo;
        private int recursionDepth;

        public DPSolutionDemo(String ring, String key) {
            this.ring = ring;
            this.key = key;
            this.charToPositions = buildCharacterMap(ring);
            this.memo = new HashMap<>();
            this.recursionDepth = 0;
        }

        private Map<Character, List<Integer>> buildCharacterMap(String ring) {
            Map<Character, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < ring.length(); i++) {
                char c = ring.charAt(i);
                map.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            }
            return map;
        }

        public int solve() {
            System.out.println("=== DP Solution Step-by-Step ===");
            return dp(0, 0);
        }

        private int dp(int ringPos, int keyIdx) {
            // Create state for memoization
            FreedomTrailState state = new FreedomTrailState(ringPos, keyIdx);

            // Indentation for visualization
            String indent = "  ".repeat(recursionDepth);
            System.out.println(indent + "dp(" + ringPos + ", " + keyIdx + ") - " +
                    "ring[" + ringPos + "]='" + ring.charAt(ringPos) +
                    "', remaining key=\"" + key.substring(keyIdx) + "\"");

            // Base case
            if (keyIdx == key.length()) {
                System.out.println(indent + "  Base case: all characters processed, return 0");
                return 0;
            }

            // Check memo
            if (memo.containsKey(state)) {
                int result = memo.get(state);
                System.out.println(indent + "  Memo hit: returning " + result);
                return result;
            }

            char targetChar = key.charAt(keyIdx);
            List<Integer> positions = charToPositions.get(targetChar);

            System.out.println(indent + "  Target: '" + targetChar + "' at positions " + positions);

            int minCost = Integer.MAX_VALUE;
            recursionDepth++;

            for (int targetPos : positions) {
                int rotationCost = calculateRotationCost(ringPos, targetPos, ring.length());
                System.out.println(indent + "  Trying position " + targetPos +
                        ": rotation cost = " + rotationCost);

                int futureCost = dp(targetPos, keyIdx + 1);
                int totalCost = 1 + rotationCost + futureCost; // +1 for button press

                System.out.println(indent + "  Total cost via position " + targetPos +
                        ": " + totalCost + " (1 + " + rotationCost + " + " + futureCost + ")");

                minCost = Math.min(minCost, totalCost);
            }

            recursionDepth--;

            memo.put(state, minCost);
            System.out.println(indent + "  Optimal cost: " + minCost);
            return minCost;
        }

        private int calculateRotationCost(int from, int to, int ringSize) {
            int clockwise = Math.abs(to - from);
            int counterclockwise = ringSize - clockwise;
            return Math.min(clockwise, counterclockwise);
        }
    }
}