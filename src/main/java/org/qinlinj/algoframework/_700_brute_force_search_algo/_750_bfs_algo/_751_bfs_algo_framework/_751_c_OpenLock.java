package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._751_bfs_algo_framework; /**
 * Open Lock Problem (LeetCode 752)
 * --------------------------------
 * <p>
 * Summary:
 * This class solves the "Open Lock" problem where we need to find the minimum number of moves
 * to unlock a 4-digit combination lock. Each move can rotate one digit up or down by one,
 * and we need to avoid certain "deadend" combinations.
 * <p>
 * Key Concepts:
 * 1. Represent the problem as a graph where:
 * - Each node is a 4-digit combination
 * - Edges connect combinations that differ by one digit rotation
 * 2. Use BFS to find the shortest path from "0000" to the target combination
 * 3. Use a set to track visited combinations and deadends
 * 4. For each combination, generate 8 possible neighbors (each digit can be rotated up or down)
 * <p>
 * Time Complexity: O(10^4) = O(10000) as there are 10000 possible combinations
 * Space Complexity: O(10^4) for the queue and visited set
 */

import java.util.*;

public class _751_c_OpenLock {

    // Example usage
    public static void main(String[] args) {
        _751_c_OpenLock solution = new _751_c_OpenLock();

        // Example 1: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
        String[] deadends1 = {"0201", "0101", "0102", "1212", "2002"};
        String target1 = "0202";
        System.out.println("Example 1: " + solution.openLock(deadends1, target1)); // Expected: 6

        // Example 2: deadends = ["8888"], target = "0009"
        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println("Example 2: " + solution.openLock(deadends2, target2)); // Expected: 1

        // Example 3: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
        String[] deadends3 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target3 = "8888";
        System.out.println("Example 3: " + solution.openLock(deadends3, target3)); // Expected: -1
    }

    /**
     * Finds the minimum number of moves to open the lock
     *
     * @param deadends Combinations that will lock the lock permanently
     * @param target   The target combination to reach
     * @return Minimum number of moves or -1 if impossible
     */
    public int openLock(String[] deadends, String target) {
        // Create a set of deadend combinations
        Set<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }

        // If the starting position is a deadend, we can't solve it
        if (deads.contains("0000")) {
            return -1;
        }

        // BFS Algorithm Framework
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // Start BFS from "0000"
        queue.offer("0000");
        visited.add("0000");

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // Check if we've reached the target
                if (current.equals(target)) {
                    return step;
                }

                // Generate all possible next combinations
                for (String neighbor : getNeighbors(current)) {
                    if (!visited.contains(neighbor) && !deads.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            step++;
        }

        // If we can't reach the target
        return -1;
    }

    /**
     * Get all possible combinations by rotating one digit
     *
     * @param combination Current lock combination
     * @return List of all possible next combinations
     */
    private List<String> getNeighbors(String combination) {
        List<String> neighbors = new ArrayList<>();

        // For each of the 4 digits
        for (int i = 0; i < 4; i++) {
            // Rotate up
            neighbors.add(plusOne(combination, i));
            // Rotate down
            neighbors.add(minusOne(combination, i));
        }

        return neighbors;
    }

    /**
     * Rotate the digit at position j upward (9 -> 0)
     *
     * @param s Current combination
     * @param j Position to rotate
     * @return New combination after rotation
     */
    private String plusOne(String s, int j) {
        char[] chars = s.toCharArray();

        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j]++;
        }

        return new String(chars);
    }

    /**
     * Rotate the digit at position j downward (0 -> 9)
     *
     * @param s Current combination
     * @param j Position to rotate
     * @return New combination after rotation
     */
    private String minusOne(String s, int j) {
        char[] chars = s.toCharArray();

        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j]--;
        }

        return new String(chars);
    }
}