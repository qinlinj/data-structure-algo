package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_framework._751_bfs_algo; /**
 * Sliding Puzzle Problem (LeetCode 773)
 * --------------------------------------
 * <p>
 * Summary:
 * This class solves the sliding puzzle problem using BFS. The puzzle is represented as a 2x3 board
 * where one position contains 0 (empty space). The goal is to reach the target state by moving
 * the empty space up, down, left, or right.
 * <p>
 * Key Concepts:
 * 1. Abstract the problem as a graph where:
 * - Nodes are states of the puzzle board
 * - Edges connect states that can be reached with a single move
 * 2. Use BFS to find the shortest path from initial state to target state
 * 3. Convert 2D board to string for easier comparison and storage in visited set
 * 4. Create a mapping to track adjacency relationships in the string representation
 * <p>
 * Time Complexity: O(m*n * m!*n!) where m=2, n=3 for this problem
 * Space Complexity: O(m*n * m!*n!) for storing all possible states
 */

import java.util.*;

public class _751_b_SlidingPuzzle {

    // Example usage
    public static void main(String[] args) {
        _751_b_SlidingPuzzle solution = new _751_b_SlidingPuzzle();

        // Example 1: [[1,2,3],[4,0,5]]
        int[][] board1 = {{1, 2, 3}, {4, 0, 5}};
        System.out.println("Example 1: " + solution.slidingPuzzle(board1)); // Expected: 1

        // Example 2: [[4,1,2],[5,0,3]]
        int[][] board2 = {{4, 1, 2}, {5, 0, 3}};
        System.out.println("Example 2: " + solution.slidingPuzzle(board2)); // Expected: 5

        // Example 3: [[1,2,3],[5,4,0]]
        int[][] board3 = {{1, 2, 3}, {5, 4, 0}};
        System.out.println("Example 3: " + solution.slidingPuzzle(board3)); // Expected: -1
    }

    /**
     * Solves the sliding puzzle using BFS to find minimum number of moves
     * @param board The initial state of the puzzle as a 2x3 array
     * @return Minimum number of moves to solve or -1 if impossible
     */
    public int slidingPuzzle(int[][] board) {
        // Target state represented as a string
        String target = "123450";

        // Convert initial board to string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();

        // BFS Algorithm Framework
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // Check if we've reached the target state
                if (target.equals(current)) {
                    return step;
                }

                // Get all possible next states by moving the empty space
                for (String neighbor : getNeighbors(current)) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            step++;
        }

        // If we can't reach the target state
        return -1;
    }

    /**
     * Get all possible next states by moving the empty space
     * @param board Current state as a string
     * @return List of possible next states
     */
    private List<String> getNeighbors(String board) {
        // This mapping represents adjacent positions in the flattened 2x3 board
        // For each position, list all positions that the empty space can move to
        int[][] mapping = new int[][]{
                {1, 3},     // Position 0 can move to positions 1 and 3
                {0, 2, 4},  // Position 1 can move to positions 0, 2 and 4
                {1, 5},     // Position 2 can move to positions 1 and 5
                {0, 4},     // Position 3 can move to positions 0 and 4
                {1, 3, 5},  // Position 4 can move to positions 1, 3 and 5
                {2, 4}      // Position 5 can move to positions 2 and 4
        };

        // Find the position of the empty space (0)
        int zeroIndex = board.indexOf('0');
        List<String> neighbors = new ArrayList<>();

        // Generate all possible next states by moving the empty space
        for (int adjacentPos : mapping[zeroIndex]) {
            String newBoard = swap(board.toCharArray(), zeroIndex, adjacentPos);
            neighbors.add(newBoard);
        }

        return neighbors;
    }

    /**
     * Swap two characters in a string
     * @param chars String as char array
     * @param i First position
     * @param j Second position
     * @return New string after swapping
     */
    private String swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}