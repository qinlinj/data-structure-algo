package org.qinlinj.algoframework._100_core_framework._170_bfs_framework._173_open_lock;

import java.util.*;

/**
 * LeetCode 752: Open the Lock
 * <p>
 * Problem Description:
 * You have a lock with 4 circular wheels, each with digits 0-9. The initial state is "0000".
 * Each wheel can be turned forward (+1) or backward (-1), with '9' wrapping to '0' and '0' wrapping to '9'.
 * Given a target string and a list of "deadends" (states that will lock the lock permanently),
 * find the minimum number of moves required to reach the target, or -1 if impossible.
 * <p>
 * Key Concepts:
 * 1. Graph Abstraction:
 * - Each possible lock combination is a node in the graph
 * - Adjacent nodes are combinations that differ by one wheel turn
 * - Each lock combination has 8 neighbors (4 wheels × 2 directions)
 * - We need to find the shortest path from "0000" to the target
 * <p>
 * 2. BFS Approach:
 * - Start BFS from "0000"
 * - For each combination, try turning each wheel in both directions
 * - Track visited combinations to avoid cycles
 * - Exclude deadend combinations
 * - Return the minimum number of turns when target is found
 * <p>
 * 3. Implementation Considerations:
 * - Handle special cases (e.g., starting combination is a deadend)
 * - Efficiently generate valid neighbor states
 * - Track visited states to avoid redundant exploration
 */
public class OpenLockSolution {
    /**
     * Example usage with test cases.
     */
    public static void main(String[] args) {
        OpenLockSolution solution = new OpenLockSolution();

        // Test Case 1
        String[] deadends1 = {"0201", "0101", "0102", "1212", "2002"};
        String target1 = "0202";
        System.out.println("Test Case 1:");
        System.out.println("Deadends: " + Arrays.toString(deadends1));
        System.out.println("Target: " + target1);
        System.out.println("Minimum moves: " + solution.openLock(deadends1, target1));
        System.out.println("Expected: 6");
        System.out.println();

        // Test Case 2
        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println("Test Case 2:");
        System.out.println("Deadends: " + Arrays.toString(deadends2));
        System.out.println("Target: " + target2);
        System.out.println("Minimum moves: " + solution.openLock(deadends2, target2));
        System.out.println("Expected: 1");
        System.out.println();

        // Test Case 3
        String[] deadends3 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target3 = "8888";
        System.out.println("Test Case 3:");
        System.out.println("Deadends: " + Arrays.toString(deadends3));
        System.out.println("Target: " + target3);
        System.out.println("Minimum moves: " + solution.openLock(deadends3, target3));
        System.out.println("Expected: -1");

        // Visualization of the lock structure and possible turns
        System.out.println("\nLock Visualization:");
        System.out.println("Starting state: 0000");
        System.out.println("Each wheel (0-9) can turn in two directions:");
        System.out.println("- Forward: 0→1→2→...→9→0");
        System.out.println("- Backward: 0→9→8→...→1→0");
        System.out.println("\nFor example, from 0000, we can reach these 8 combinations in one turn:");
        List<String> initialNeighbors = solution.getNeighbors("0000");
        for (String neighbor : initialNeighbors) {
            System.out.println("- " + neighbor);
        }
    }

    /**
     * Main method to find minimum number of moves to open the lock.
     * Uses BFS to explore all possible lock combinations.
     *
     * @param deadends Array of forbidden lock combinations
     * @param target   The target lock combination
     * @return Minimum number of moves required, or -1 if impossible
     */
    public int openLock(String[] deadends, String target) {
        // Initialize set of deadend combinations
        Set<String> deadendSet = new HashSet<>();
        for (String deadend : deadends) {
            deadendSet.add(deadend);
        }

        // If starting position is a deadend, return -1 immediately
        if (deadendSet.contains("0000")) {
            return -1;
        }

        // Initialize BFS data structures
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Start BFS from "0000"
        queue.offer("0000");
        visited.add("0000");

        // Track number of moves
        int moves = 0;

        // BFS traversal
        while (!queue.isEmpty()) {
            // Process all combinations at current move count
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currentCombination = queue.poll();

                // Check if we've reached the target
                if (currentCombination.equals(target)) {
                    return moves;
                }

                // Generate all neighboring combinations by turning each wheel
                List<String> neighbors = getNeighbors(currentCombination);

                // Add valid and unvisited neighbors to the queue
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor) && !deadendSet.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            // Increment move count after processing all combinations at current level
            moves++;
        }

        // If we exit the loop without finding the target, it's impossible
        return -1;
    }

    private List<String> getNeighbors(String combination) {
        List<String> neighbors = new ArrayList<>();

        // For each of the 4 wheels
        for (int i = 0; i < 4; i++) {
            // Turn wheel forward (+1)
            neighbors.add(turnDigit(combination, i, true));

            // Turn wheel backward (-1)
            neighbors.add(turnDigit(combination, i, false));
        }

        return neighbors;
    }

    private String turnDigit(String combination, int position, boolean forward) {
        char[] chars = combination.toCharArray();
        char digit = chars[position];

        if (forward) {
            // Turn forward: 0->1, 1->2, ..., 9->0
            chars[position] = (digit == '9') ? '0' : (char) (digit + 1);
        } else {
            // Turn backward: 0->9, 9->8, ..., 1->0
            chars[position] = (digit == '0') ? '9' : (char) (digit - 1);
        }

        return new String(chars);
    }

    /**
     * Alternative implementation for generating neighbors.
     * This version explicitly implements plusOne and minusOne operations.
     */
    private List<String> getNeighborsAlternative(String combination) {
        List<String> neighbors = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            neighbors.add(plusOne(combination, i));
            neighbors.add(minusOne(combination, i));
        }

        return neighbors;
    }

    /**
     * Turns a wheel backward (decrement by 1).
     *
     * @param s        Current combination
     * @param position Position to turn (0-3)
     * @return New combination after turning
     */
    private String minusOne(String s, int position) {
        char[] chars = s.toCharArray();
        if (chars[position] == '0') {
            chars[position] = '9';
        } else {
            chars[position]--;
        }
        return new String(chars);
    }

    /**
     * Turns a wheel forward (increment by 1).
     *
     * @param s        Current combination
     * @param position Position to turn (0-3)
     * @return New combination after turning
     */
    private String plusOne(String s, int position) {
        char[] chars = s.toCharArray();
        if (chars[position] == '9') {
            chars[position] = '0';
        } else {
            chars[position]++;
        }
        return new String(chars);
    }
}
