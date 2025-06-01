package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._843_fallout4_game;

/**
 * PIANO FINGERING OPTIMIZATION ANALOGY
 * <p>
 * Key Insights from Piano Practice:
 * 1. State: Current finger positions + next note to play
 * 2. Choice: Which finger to use for the next note
 * 3. Cost Function: "Awkwardness" of finger transitions
 * 4. Goal: Minimize total awkwardness for smooth performance
 * <p>
 * Parallels to Freedom Trail Problem:
 * - Piano keys ↔ Ring characters
 * - Finger positions ↔ Pointer position
 * - Note sequence ↔ Key string to spell
 * - Finger transitions ↔ Ring rotations
 * - Awkwardness cost ↔ Rotation cost
 * <p>
 * DP State Design Lessons:
 * - State must capture current position and remaining task
 * - Choices are the possible ways to handle next element
 * - Cost functions can be customized based on constraints
 * - Global optimization requires considering entire sequence
 */

import java.util.*;

public class _843_b_PianoFingeringAnalogy {

    /**
     * Demonstrate the analogy between piano fingering and ring rotation
     */
    public static void demonstrateAnalogy() {
        System.out.println("=== Piano Fingering ↔ Ring Rotation Analogy ===");

        System.out.println("\nPiano Fingering Problem:");
        System.out.println("- State: Current finger positions + next note");
        System.out.println("- Choice: Which finger to use for next note");
        System.out.println("- Cost: Awkwardness of finger transition");
        System.out.println("- Goal: Minimize total awkwardness");

        System.out.println("\nRing Rotation Problem:");
        System.out.println("- State: Current pointer position + next character");
        System.out.println("- Choice: Which occurrence of character to target");
        System.out.println("- Cost: Number of rotation steps");
        System.out.println("- Goal: Minimize total rotations");

        System.out.println("\nShared DP Pattern:");
        System.out.println("1. Define state space clearly");
        System.out.println("2. Identify all possible choices at each state");
        System.out.println("3. Define cost function for each choice");
        System.out.println("4. Use recursion + memoization for optimization");
        System.out.println("5. Consider global optimization, not just local");

        demonstrateLocalVsGlobalOptimization();
    }

    /**
     * Show why local optimization fails in both domains
     */
    private static void demonstrateLocalVsGlobalOptimization() {
        System.out.println("\n=== Local vs Global Optimization ===");

        System.out.println("\nPiano Example:");
        System.out.println("Playing C-D-E sequence:");
        System.out.println("- Local optimal: Use thumb(1) for each note");
        System.out.println("- Problem: Thumb can't stretch that far comfortably");
        System.out.println("- Global optimal: Use thumb(1)-index(2)-middle(3)");
        System.out.println("- Result: Smoother finger transitions");

        System.out.println("\nRing Example:");
        System.out.println("Ring=\"abcabc\", Key=\"ac\"");
        System.out.println("- Local optimal: Use nearest 'a' and nearest 'c'");
        System.out.println("- Problem: May require more total rotations");
        System.out.println("- Global optimal: Choose 'a' and 'c' positions that minimize total cost");
        System.out.println("- Result: Fewer total operations");

        System.out.println("\nKey Insight: Future decisions affect current optimal choice!");
    }

    /**
     * Simple demonstration with actual piano-like example
     */
    public static void runSimplePianoExample() {
        System.out.println("\n=== Simple Piano Fingering Example ===");

        // Simple 5-note sequence: C(60) - D(62) - E(64) - F(65) - G(67)
        int[] notes = {60, 62, 64, 65, 67};
        PianoPiece piece = new PianoPiece(notes);

        System.out.println("Note sequence: C-D-E-F-G");
        System.out.println("MIDI positions: " + Arrays.toString(notes));

        PianoState initialState = new PianoState();
        // Start with fingers at middle C position
        Arrays.fill(initialState.fingerPositions, 60);

        PianoFingeringOptimizer optimizer = new PianoFingeringOptimizer();
        int optimalCost = optimizer.optimizeFingering(initialState, piece);

        System.out.println("Optimal awkwardness cost: " + optimalCost);
        System.out.println("(Lower cost = smoother fingering)");
    }

    public static void main(String[] args) {
        demonstrateAnalogy();
        runSimplePianoExample();

        System.out.println("\n=== Takeaway ===");
        System.out.println("Both problems teach us that:");
        System.out.println("1. State design is crucial for DP success");
        System.out.println("2. Cost functions can be domain-specific");
        System.out.println("3. Global optimization often differs from local optimization");
        System.out.println("4. Memoization transforms exponential to polynomial complexity");
    }

    /**
     * Piano fingering state representation
     * In reality, this would be much more complex with hand positions,
     * but simplified here for educational purposes
     */
    static class PianoState {
        int[] fingerPositions; // Position of each finger (5 fingers)
        boolean[] fingersPressed; // Which fingers are currently pressed

        public PianoState() {
            fingerPositions = new int[5]; // 0=thumb, 1=index, ..., 4=pinky
            fingersPressed = new boolean[5];
        }

        /**
         * Calculate "awkwardness" cost of using finger for next note
         */
        public int calculateTransitionCost(int finger, int notePosition) {
            // Simplified cost function - in reality much more complex
            int currentPos = fingerPositions[finger];
            int distance = Math.abs(notePosition - currentPos);

            // Different fingers have different flexibility
            int[] fingerFlexibility = {3, 4, 3, 2, 1}; // thumb most flexible

            return distance / fingerFlexibility[finger];
        }
    }

    /**
     * Simplified piano piece representation
     */
    static class PianoPiece {
        int[] notes; // Sequence of note positions (0-87 for 88-key piano)
        int currentNoteIndex;

        public PianoPiece(int[] notes) {
            this.notes = notes;
            this.currentNoteIndex = 0;
        }

        public boolean hasMoreNotes() {
            return currentNoteIndex < notes.length;
        }

        public int getCurrentNote() {
            return hasMoreNotes() ? notes[currentNoteIndex] : -1;
        }
    }

    /**
     * Piano fingering optimizer using DP principles
     */
    public static class PianoFingeringOptimizer {
        private Map<String, Integer> memo = new HashMap<>();

        /**
         * Find optimal fingering for a piece
         *
         * @param state current piano state
         * @param piece the piece being played
         * @return minimum awkwardness cost
         */
        public int optimizeFingering(PianoState state, PianoPiece piece) {
            // Base case: no more notes to play
            if (!piece.hasMoreNotes()) {
                return 0;
            }

            // Create memo key from state
            String memoKey = createMemoKey(state, piece.currentNoteIndex);
            if (memo.containsKey(memoKey)) {
                return memo.get(memoKey);
            }

            int currentNote = piece.getCurrentNote();
            int minCost = Integer.MAX_VALUE;

            // Try each finger for the current note
            for (int finger = 0; finger < 5; finger++) {
                // Calculate cost of using this finger
                int transitionCost = state.calculateTransitionCost(finger, currentNote);

                // Create new state after using this finger
                PianoState newState = simulateFingerPress(state, finger, currentNote);
                PianoPiece newPiece = new PianoPiece(Arrays.copyOf(piece.notes, piece.notes.length));
                newPiece.currentNoteIndex = piece.currentNoteIndex + 1;

                // Recursively solve for remaining notes
                int futureCost = optimizeFingering(newState, newPiece);

                // Update minimum cost
                minCost = Math.min(minCost, transitionCost + futureCost);
            }

            memo.put(memoKey, minCost);
            return minCost;
        }

        private String createMemoKey(PianoState state, int noteIndex) {
            return Arrays.toString(state.fingerPositions) + "_" +
                    Arrays.toString(state.fingersPressed) + "_" + noteIndex;
        }

        private PianoState simulateFingerPress(PianoState state, int finger, int notePos) {
            PianoState newState = new PianoState();
            System.arraycopy(state.fingerPositions, 0, newState.fingerPositions, 0, 5);
            System.arraycopy(state.fingersPressed, 0, newState.fingersPressed, 0, 5);

            // Update finger position
            newState.fingerPositions[finger] = notePos;
            newState.fingersPressed[finger] = true;

            return newState;
        }
    }
}