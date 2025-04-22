package org.qinlinj.algoframework._100_algo_core_framework._170_bfs_framework._172_sliding_puzzle;

import java.util.*;

public class SlidingPuzzleSolution {

    /**
     * Example usage with test cases.
     */
    public static void main(String[] args) {
        SlidingPuzzleSolution solution = new SlidingPuzzleSolution();

        // Test Case 1: Solvable puzzle, should take 5 moves
        int[][] board1 = {{4, 1, 2}, {5, 0, 3}};
        System.out.println("Test Case 1: Board = [[4,1,2],[5,0,3]]");
        System.out.println("Minimum moves: " + solution.slidingPuzzle(board1));
        System.out.println();

        // Test Case 2: Unsolvable puzzle, should return -1
        int[][] board2 = {{1, 2, 3}, {5, 4, 0}};
        System.out.println("Test Case 2: Board = [[1,2,3],[5,4,0]]");
        System.out.println("Minimum moves: " + solution.slidingPuzzle(board2));
        System.out.println();

        // Test Case 3: Already solved, should return 0
        int[][] board3 = {{1, 2, 3}, {4, 5, 0}};
        System.out.println("Test Case 3: Board = [[1,2,3],[4,5,0]]");
        System.out.println("Minimum moves: " + solution.slidingPuzzle(board3));

        // Visualization of the board structure and indices:
        System.out.println("\nBoard Structure (2x3):");
        System.out.println("[0][1][2]");
        System.out.println("[3][4][5]");
    }

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

    /**
     * Generates all possible board states that can be reached with one move.
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
     * @param currentState Current board state as a string
     * @return List of all possible next states
     */
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

    /**
     * Alternative approach for larger puzzles:
     * For an m×n board, we can compute adjacent positions dynamically:
     *
     * private List<String> getNeighborsForLargeBoard(String board, int m, int n) {
     *     int[] dx = {-1, 0, 1, 0};  // Up, Right, Down, Left (row change)
     *     int[] dy = {0, 1, 0, -1};  // Up, Right, Down, Left (column change)
     *
     *     int idx = board.indexOf('0');
     *     int x = idx / n;  // Row of empty space
     *     int y = idx % n;  // Column of empty space
     *
     *     List<String> neighbors = new ArrayList<>();
     *
     *     for (int i = 0; i < 4; i++) {
     *         int newX = x + dx[i];
     *         int newY = y + dy[i];
     *
     *         // Check if new position is valid
     *         if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
     *             int newIdx = newX * n + newY;
     *             String newBoard = swapCharacters(board, idx, newIdx);
     *             neighbors.add(newBoard);
     *         }
     *     }
     *
     *     return neighbors;
     * }
     */
}
