package org.qinlinj.algoframework._100_core_framework._170_bfs_framework._172_sliding_puzzle;

import java.util.*;

/**
 * LeetCode 773: Sliding Puzzle Solution
 * <p>
 * Problem Description:
 * You are given a 2x3 board with tiles numbered 0-5, where 0 represents an empty space.
 * You can move any tile adjacent to the empty space into the empty space.
 * The goal is to reach the target state [[1,2,3],[4,5,0]] in the minimum number of moves.
 * If it's impossible to reach the target, return -1.
 * <p>
 * Key Concepts:
 * 1. Problem Abstraction:
 * - Initial board state can be considered as the starting node in a graph
 * - Target board state is the destination node
 * - Each valid move creates a new board state (a neighboring node)
 * - We need to find the shortest path from start to target
 * <p>
 * 2. BFS Application:
 * - BFS is ideal for finding the shortest path in unweighted graphs
 * - Each board state is a node in our graph
 * - Neighbors are states reachable with one valid move
 * - We track visited states to avoid cycles
 * <p>
 * 3. Implementation Challenges:
 * - Representing the 2D board as a hashable type for the visited set
 * - Efficiently finding valid neighboring states
 * - Handling the conversion between board representation formats
 * <p>
 * This class provides a complete solution using BFS with detailed comments.
 */
public class SlidingPuzzleSolution {
    /**
     * Solves the sliding puzzle problem using BFS.
     *
     * @param board Initial board state as a 2x3 array
     * @return Minimum number of moves to reach target state, or -1 if impossible
     */
    public int slidingPuzzle(int[][] board) {
        // Target configuration represented as a string for easy comparison
        final String TARGET = "123450";

        // Convert initial board to string representation
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();

        // If we're already at the target state, return 0
        if (start.equals(TARGET)) {
            return 0;
        }

        // BFS algorithm implementation
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Add starting state to queue and visited set
        queue.offer(start);
        visited.add(start);

        // Track number of moves
        int moves = 0;

        // BFS traversal
        while (!queue.isEmpty()) {
            // Process all states at current move count
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currentState = queue.poll();

                // Check if we've reached the target state
                if (TARGET.equals(currentState)) {
                    return moves;
                }

                // Generate all possible next states by moving the empty space
                List<String> neighbors = getNeighborStates(currentState);

                // Add unvisited neighbors to the queue
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            // Increment move count after processing all states at current level
            moves++;
        }

        // If we exit the loop without finding the target, it's impossible
        return -1;
    }

    private List<String> getNeighborStates(String currentState) {
        // Define adjacency mapping for each position in the 2x3 board
        int[][] adjacencyMap = new int[][]{
                {1, 3},       // Position 0 can move to 1 and 3
                {0, 2, 4},    // Position 1 can move to 0, 2, and 4
                {1, 5},       // Position 2 can move to 1 and 5
                {0, 4},       // Position 3 can move to 0 and 4
                {1, 3, 5},    // Position 4 can move to 1, 3, and 5
                {2, 4}        // Position 5 can move to 2 and 4
        };

        // Find the position of the empty space (0)
        int emptyPosition = currentState.indexOf('0');

        List<String> neighbors = new ArrayList<>();

        // Generate all possible moves by swapping the empty space with adjacent tiles
        for (int adjacentPos : adjacencyMap[emptyPosition]) {
            // Create a new state by swapping the empty space with the adjacent tile
            String newState = swapCharacters(currentState, emptyPosition, adjacentPos);
            neighbors.add(newState);
        }

        return neighbors;
    }

    /**
     * Swaps two characters in a string.
     *
     * @param str The original string
     * @param i   First position
     * @param j   Second position
     * @return New string with the characters at positions i and j swapped
     */
    private String swapCharacters(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
