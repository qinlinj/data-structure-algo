package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._843_fallout4_game;

/**
 * FREEDOM TRAIL PROBLEM INTRODUCTION
 * <p>
 * Key Concepts:
 * 1. Circular dial with characters arranged in a ring
 * 2. Pointer initially points to 12 o'clock position (ring[0])
 * 3. Can rotate dial clockwise or counterclockwise
 * 4. Need to spell out a target string by rotating and pressing button
 * 5. Goal: Find minimum operations to input the entire key string
 * <p>
 * Operations:
 * - Rotate dial one position: 1 operation
 * - Press center button: 1 operation
 * <p>
 * Problem Analysis:
 * - Multiple occurrences of same character create choices
 * - Need to consider global optimization, not just local minimum
 * - Classic dynamic programming problem for finding optimal sequence
 * <p>
 * Real-world Connection:
 * - Similar to combination locks, rotary phones, piano fingering optimization
 * - Demonstrates importance of state-based decision making
 */

public class _843_a_FreedomTrailIntroduction {
    /**
     * Problem Statement:
     * Given a circular ring of characters and a key string to spell,
     * find the minimum number of operations (rotations + button presses)
     * needed to input the entire key string.
     * <p>
     * Example:
     * ring = "godding", key = "gd"
     * - Start at 'g' (position 0), press button (1 operation)
     * - Rotate counterclockwise 2 steps to 'd' (2 operations)
     * - Press button (1 operation)
     * Total: 4 operations
     */

    public static void demonstrateProblem() {
        String ring = "godding";
        String key = "gd";

        System.out.println("=== Freedom Trail Problem Demonstration ===");
        System.out.println("Ring: " + ring);
        System.out.println("Key to spell: " + key);

        visualizeRing(ring);

        System.out.println("\nStep-by-step solution:");
        System.out.println("1. Initial position: pointer at '" + ring.charAt(0) + "' (index 0)");
        System.out.println("2. Target: '" + key.charAt(0) + "' - already at correct position!");
        System.out.println("3. Press button (1 operation)");
        System.out.println("4. Target: '" + key.charAt(1) + "' - need to find best 'd' position");

        findBestRotation(ring, 0, 'd');

        System.out.println("5. Press button (1 operation)");
        System.out.println("Total operations: 4");
    }
}
