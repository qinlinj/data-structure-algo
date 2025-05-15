package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._755_lian_lian_kan_connect_game; /**
 * Lianliankan Game Connect Algorithm
 * -----------------------------------------------------
 * <p>
 * Summary:
 * This class implements the core connect algorithm for the Lianliankan (连连看) game,
 * focusing on determining if two selected pieces can be connected with at most two turns.
 * Lianliankan is a popular puzzle game where players need to match identical pieces
 * by connecting them with a path that has at most two turns.
 * <p>
 * Key Concepts:
 * 1. BFS (Breadth-First Search) with state tracking for finding valid paths
 * 2. Complex state representation in BFS for handling multiple conditions
 * 3. Path tracking to record the exact connecting path between pieces
 * 4. Special boundary conditions for game board edges
 * <p>
 * The BFS Implementation:
 * - Unlike simpler BFS problems, this implementation requires maintaining multiple states:
 * * Current position (row, col)
 * * Current direction of movement
 * * Number of turns used so far (must not exceed 2)
 * * Path traveled from start to current position
 * <p>
 * - The visited set needs to track position + direction + turns to avoid unnecessary exploration
 * <p>
 * - Special consideration for the game board boundaries:
 * * Paths can extend one unit beyond the board edges
 * * This allows for "wrapping around" the board edges
 * <p>
 * Time Complexity: O(m*n) where m and n are the dimensions of the board
 * Space Complexity: O(m*n) for the queue and visited set
 */

import java.util.*;

public class _755_a_LianliankanConnectAlgorithm {

    /**
     * Check if two pieces can be connected with at most two turns
     * @param board Game board (0 for empty cell, non-zero for pieces)
     * @param row1 Row index of first piece
     * @param col1 Column index of first piece
     * @param row2 Row index of second piece
     * @param col2 Column index of second piece
     * @return List of coordinates representing the connecting path, or empty list if not possible
     */
    public static List<int[]> connect(int[][] board, int row1, int col1, int row2, int col2) {
        int m = board.length;
        int n = board[0].length;

        // Check if valid piece positions and matching pieces
        if (row1 < 0 || row1 >= m || col1 < 0 || col1 >= n ||
                row2 < 0 || row2 >= m || col2 < 0 || col2 >= n) {
            return new ArrayList<>(); // Invalid positions
        }

        // Check if same position or different piece types
        if ((row1 == row2 && col1 == col2) || (board[row1][col1] != board[row2][col2])) {
            return new ArrayList<>(); // Cannot connect
        }

        // Define four directions: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Queue for BFS
        Queue<State> queue = new LinkedList<>();

        // Set to track visited states (position + direction + turns)
        Set<String> visited = new HashSet<>();

        // Start BFS from the first piece
        List<int[]> initialPath = new ArrayList<>();
        initialPath.add(new int[]{row1, col1});
        queue.offer(new State(row1, col1, -1, 0, initialPath));

        while (!queue.isEmpty()) {
            State current = queue.poll();
            int curRow = current.row;
            int curCol = current.col;
            int curDir = current.direction;
            int curTurns = current.turns;
            List<int[]> curPath = current.path;

            // Try all four directions
            for (int i = 0; i < 4; i++) {
                int[] dir = directions[i];
                int newRow = curRow + dir[0];
                int newCol = curCol + dir[1];

                // Calculate new turns count
                int newTurns = curTurns;
                if (curDir != -1 && curDir != i) {
                    // Direction changed, add a turn
                    newTurns++;
                }

                // Skip if exceeding turn limit
                if (newTurns > 2) {
                    continue;
                }

                // Create state key for visited set
                String stateKey = newRow + "," + newCol + "," + i + "," + newTurns;
                if (visited.contains(stateKey)) {
                    continue; // Skip if already visited
                }
                visited.add(stateKey);

                // Check if reached the second piece
                if (newRow == row2 && newCol == col2) {
                    // Found a valid path
                    List<int[]> resultPath = new ArrayList<>(curPath);
                    resultPath.add(new int[]{newRow, newCol});
                    return resultPath;
                }

                // Check if the new position is valid for the path
                if (!canPass(board, newRow, newCol)) {
                    continue;
                }

                // Add new state to queue
                List<int[]> newPath = new ArrayList<>(curPath);
                newPath.add(new int[]{newRow, newCol});
                queue.offer(new State(newRow, newCol, i, newTurns, newPath));
            }
        }

        // No valid path found
        return new ArrayList<>();
    }

    /**
     * Check if a position is valid for the connecting path
     * Note: In Lianliankan, paths can extend one unit beyond the board boundaries
     * @param board Game board
     * @param r Row index
     * @param c Column index
     * @return True if the position is valid for the path
     */
    private static boolean canPass(int[][] board, int r, int c) {
        int m = board.length;
        int n = board[0].length;

        // Check if position is too far outside the board
        if (r < -1 || r > m || c < -1 || c > n) {
            return false;
        }

        // Allow positions just outside the board (one unit beyond borders)
        if ((r == -1 || r == m) && c >= 0 && c < n) {
            return true; // Just above or below the board
        }
        if ((c == -1 || c == n) && r >= 0 && r < m) {
            return true; // Just to the left or right of the board
        }

        // Inside the board, must be an empty cell (0)
        if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 0) {
            return true;
        }

        return false;
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        // Example 1: Simple board where pieces can be connected
        int[][] board1 = {
                {1, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 1}
        };

        // Connect (0,0) with (3,3) - should find a path
        List<int[]> path1 = connect(board1, 0, 0, 3, 3);
        System.out.println("Example 1 - Can connect: " + !path1.isEmpty());
        System.out.print("Path: ");
        for (int[] point : path1) {
            System.out.print("(" + point[0] + "," + point[1] + ") ");
        }
        System.out.println();

        // Example 2: Board where pieces cannot be connected with ≤ 2 turns
        int[][] board2 = {
                {1, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 1}
        };

        // Connect (0,0) with (3,3) - should not find a path (requires > 2 turns)
        List<int[]> path2 = connect(board2, 0, 0, 3, 3);
        System.out.println("Example 2 - Can connect: " + !path2.isEmpty());

        // Example 3: Board demonstrating edge paths
        int[][] board3 = {
                {1, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 1}
        };

        // Connect (0,0) with (3,3) - should find a path that goes beyond board edges
        List<int[]> path3 = connect(board3, 0, 0, 3, 3);
        System.out.println("Example 3 - Can connect: " + !path3.isEmpty());
        System.out.print("Path: ");
        for (int[] point : path3) {
            System.out.print("(" + point[0] + "," + point[1] + ") ");
        }
        System.out.println();
    }

    /**
     * Represents a state in the BFS search
     */
    private static class State {
        int row, col;     // Current position
        int direction;    // Current direction (0: up, 1: right, 2: down, 3: left, -1: start)
        int turns;        // Number of turns used so far
        List<int[]> path; // Path from start to current position

        State(int row, int col, int direction, int turns, List<int[]> path) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.turns = turns;
            this.path = path;
        }
    }
}