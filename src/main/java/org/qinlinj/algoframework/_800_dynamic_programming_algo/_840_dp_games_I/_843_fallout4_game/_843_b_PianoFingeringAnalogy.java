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

public class _843_b_PianoFingeringAnalogy {
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

}
