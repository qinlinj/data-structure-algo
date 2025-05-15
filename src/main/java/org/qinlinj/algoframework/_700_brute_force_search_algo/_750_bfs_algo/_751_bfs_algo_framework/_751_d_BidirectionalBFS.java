package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._751_bfs_algo_framework; /**
 * Bidirectional BFS Optimization
 * ------------------------------
 * <p>
 * Summary:
 * This class demonstrates bidirectional BFS, which optimizes the standard BFS algorithm by
 * searching from both the start and goal simultaneously. When the two search fronts meet,
 * a path has been found.
 * <p>
 * Key Concepts:
 * 1. Start BFS from both the start node and the end node
 * 2. When the two searches intersect, we've found the shortest path
 * 3. Use sets instead of queues for faster intersection checks
 * 4. Always expand the smaller set to minimize search space
 * <p>
 * Advantages:
 * - Can significantly reduce the search space
 * - More efficient for problems with known endpoints
 * <p>
 * Limitations:
 * - Must know the target state in advance
 * - More complex implementation
 * - Additional memory overhead
 * <p>
 * Applied to the "Open Lock" problem from LeetCode 752
 */

import java.util.*;

public class _751_d_BidirectionalBFS {

    // Example usage with performance comparison
    public static void main(String[] args) {
        _751_d_BidirectionalBFS biDirSolution = new _751_d_BidirectionalBFS();
        _751_c_OpenLock standardSolution = new _751_c_OpenLock();

        // Example case for performance comparison
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        // Time the standard BFS
        long startTime = System.currentTimeMillis();
        int standardResult = standardSolution.openLock(deadends, target);
        long standardTime = System.currentTimeMillis() - startTime;

        // Time the bidirectional BFS
        startTime = System.currentTimeMillis();
        int biDirResult = biDirSolution.openLock(deadends, target);
        long biDirTime = System.currentTimeMillis() - startTime;

        System.out.println("Standard BFS Result: " + standardResult + " (took " + standardTime + " ms)");
        System.out.println("Bidirectional BFS Result: " + biDirResult + " (took " + biDirTime + " ms)");
        System.out.println("Bidirectional BFS is " +
                (standardTime > 0 ? (float) standardTime / biDirTime : "N/A") +
                "x faster");

        // Additional examples
        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println("\nExample 2 Result: " + biDirSolution.openLock(deadends2, target2)); // Expected: 1

        String[] deadends3 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target3 = "8888";
        System.out.println("Example 3 Result: " + biDirSolution.openLock(deadends3, target3)); // Expected: -1
    }

    /**
     * Finds the minimum number of moves to open the lock using bidirectional BFS
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

        // Base cases
        if (deads.contains("0000")) {
            return -1;
        }
        if (target.equals("0000")) {
            return 0;
        }

        // Bidirectional BFS - using sets for efficient intersection checks
        Set<String> set1 = new HashSet<>();  // Forward search from "0000"
        Set<String> set2 = new HashSet<>();  // Backward search from target
        Set<String> visited = new HashSet<>();

        set1.add("0000");
        set2.add(target);
        visited.add("0000");
        visited.add(target);

        int step = 0;

        while (!set1.isEmpty() && !set2.isEmpty()) {
            // Always choose the smaller set to expand
            if (set1.size() > set2.size()) {
                Set<String> temp = set1;
                set1 = set2;
                set2 = temp;
            }

            // Expand the smaller set
            Set<String> nextSet = new HashSet<>();

            for (String current : set1) {
                // Generate all possible next combinations
                for (String neighbor : getNeighbors(current)) {
                    // If the other direction already visited this node, we've found a path
                    if (set2.contains(neighbor)) {
                        return step + 1;  // +1 because we need one more step to reach the intersection
                    }

                    if (!visited.contains(neighbor) && !deads.contains(neighbor)) {
                        nextSet.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            // Update set1 to contain the new frontier
            set1 = nextSet;
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