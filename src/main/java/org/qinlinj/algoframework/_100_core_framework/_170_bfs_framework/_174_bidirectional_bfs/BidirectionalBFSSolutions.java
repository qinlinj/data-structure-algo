package org.qinlinj.algoframework._100_core_framework._170_bfs_framework._174_bidirectional_bfs;

import java.util.*;

/**
 * Bidirectional BFS Solutions for Classic Puzzles
 * <p>
 * This class implements solutions to two classic puzzle problems using both standard BFS
 * and optimized bidirectional BFS approaches:
 * <p>
 * 1. LeetCode 773: Sliding Puzzle - A 2x3 sliding puzzle where we need to find the minimum
 * number of moves to reach the target state.
 * <p>
 * 2. LeetCode 752: Open the Lock - A 4-wheel combination lock where we need to find the
 * minimum number of moves to reach the target combination while avoiding deadends.
 * <p>
 * Key Concept: Bidirectional BFS Optimization
 * <p>
 * Traditional BFS starts from the beginning and expands outward until reaching the target.
 * Bidirectional BFS starts from both the beginning and end simultaneously, expanding both
 * frontiers until they meet. This significantly reduces the search space.
 * <p>
 * The optimization is especially effective because:
 * - If a graph has branching factor b and solution depth d, traditional BFS explores O(b^d) nodes
 * - Bidirectional BFS explores approximately O(2*b^(d/2)) nodes, which is much smaller for large b and d
 * <p>
 * Limitations: Bidirectional BFS can only be used when both the start and end states are known.
 */
public class BidirectionalBFSSolutions {
    /**
     * ===== SOLUTION FOR SLIDING PUZZLE (LEETCODE 773) =====
     *
     * In the sliding puzzle problem:
     * - We have a 2x3 board with numbers 0-5
     * - 0 represents the empty space that can be swapped with adjacent numbers
     * - We need to find the minimum number of moves to reach [[1,2,3],[4,5,0]]
     */
    /**
     * Solves the sliding puzzle using standard BFS.
     *
     * @param board Initial state of the board as 2x3 array
     * @return Minimum moves to reach target, or -1 if impossible
     */
    public int slidingPuzzle(int[][] board) {
        // Target state: "123450"
        String target = "123450";

        // Convert initial board to string representation
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();

        // Standard BFS
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(target)) {
                    return step;
                }

                // Get all possible next states
                for (String neighbor : getSlidingNeighbors(current)) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    /**
     * Gets all possible next states for the sliding puzzle by moving the empty space.
     * <p>
     * In a 2x3 board, the mapping of adjacent positions for each index is:
     * - Position 0: can move to positions 1, 3
     * - Position 1: can move to positions 0, 2, 4
     * - Position 2: can move to positions 1, 5
     * - Position 3: can move to positions 0, 4
     * - Position 4: can move to positions 1, 3, 5
     * - Position 5: can move to positions 2, 4
     * <p>
     * This mapping represents the physical adjacency in the 2x3 grid:
     * [0][1][2]
     * [3][4][5]
     *
     * @param state Current state as a string
     * @return List of all possible next states
     */
    private List<String> getSlidingNeighbors(String state) {
        // Define adjacency mapping for each position in the 2x3 board
        int[][] neighbors = new int[][]{
                {1, 3},       // Position 0 can move to 1 and 3
                {0, 2, 4},    // Position 1 can move to 0, 2, and 4
                {1, 5},       // Position 2 can move to 1 and 5
                {0, 4},       // Position 3 can move to 0 and 4
                {1, 3, 5},    // Position 4 can move to 1, 3, and 5
                {2, 4}        // Position 5 can move to 2 and 4
        };

        List<String> result = new ArrayList<>();
        int emptyPos = state.indexOf('0');

        for (int adj : neighbors[emptyPos]) {
            result.add(swap(state, emptyPos, adj));
        }

        return result;
    }

    /**
     * Swaps two characters in a string.
     */
    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
