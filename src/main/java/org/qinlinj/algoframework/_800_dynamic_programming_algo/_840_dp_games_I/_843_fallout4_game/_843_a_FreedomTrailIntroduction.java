package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._843_fallout4_game;

import java.util.*;

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

    private static void visualizeRing(String ring) {
        System.out.println("\nRing visualization (pointer starts at 12 o'clock):");
        System.out.println("     " + ring.charAt(0) + " ←(pointer)");

        int n = ring.length();
        for (int i = 1; i < n; i++) {
            if (i == 1) System.out.println("   " + ring.charAt(n - 1) + "   " + ring.charAt(i));
            else if (i == 2) System.out.println(" " + ring.charAt(n - 2) + "       " + ring.charAt(i));
            else if (i == n - 2) System.out.println(" " + ring.charAt(i) + "       " + ring.charAt(n - 1));
            else if (i == n - 1) System.out.println("   " + ring.charAt(i) + "   " + ring.charAt(1));
        }

        System.out.println("     " + ring.charAt(n / 2));
    }

    private static void findBestRotation(String ring, int currentPos, char target) {
        System.out.println("\nFinding best rotation for '" + target + "':");

        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < ring.length(); i++) {
            if (ring.charAt(i) == target) {
                positions.add(i);
            }
        }

        System.out.println("'" + target + "' found at positions: " + positions);

        for (int pos : positions) {
            int clockwise = Math.abs(pos - currentPos);
            int counterclockwise = ring.length() - clockwise;
            int minSteps = Math.min(clockwise, counterclockwise);
            String direction = (clockwise <= counterclockwise) ? "clockwise" : "counterclockwise";

            System.out.printf("Position %d: %d steps %s\n", pos, minSteps, direction);
        }

        // Find minimum
        int bestPos = positions.get(0);
        int minSteps = Integer.MAX_VALUE;
        for (int pos : positions) {
            int steps = Math.min(Math.abs(pos - currentPos), ring.length() - Math.abs(pos - currentPos));
            if (steps < minSteps) {
                minSteps = steps;
                bestPos = pos;
            }
        }

        System.out.println("Best choice: position " + bestPos + " with " + minSteps + " steps");
    }
}
