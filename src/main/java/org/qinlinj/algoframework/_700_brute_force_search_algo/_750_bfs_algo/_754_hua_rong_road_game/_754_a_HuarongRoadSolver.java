package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._754_hua_rong_road_game; /**
 * Huarong Road Puzzle Solver (华容道 Solver)
 * ----------------------------------------
 * <p>
 * Summary:
 * This class implements a BFS (Breadth-First Search) algorithm to solve the classic Chinese
 * Huarong Road (华容道) sliding block puzzle. The puzzle consists of pieces of different shapes
 * that must be moved around a board until a specific piece (typically representing the character
 * Cao Cao) reaches the exit position.
 * <p>
 * Key Concepts:
 * 1. BFS for exhaustive search of all possible board states
 * 2. State space optimization by treating pieces of the same shape as equivalent
 * 3. Board representation and move generation
 * 4. Path tracking to record the sequence of moves
 * <p>
 * Main Differences from Numeric Slider Puzzle:
 * 1. Pieces have different shapes and occupy different numbers of cells (Cao Cao: 2×2,
 * Guan Yu: a 2×1 piece, generals: 1×2 pieces, soldiers: 1×1 pieces)
 * 2. We track the complete move sequence rather than just the minimum steps
 * <p>
 * Optimization:
 * A critical optimization is treating pieces of the same shape as equivalent when generating
 * the hash representation of the board. This significantly reduces the search space by avoiding
 * redundant exploration of board states that are functionally identical.
 * <p>
 * Time Complexity: O(S) where S is the state space size (can be very large)
 * Space Complexity: O(S) for storing visited states and the queue
 */

import java.util.*;

public class _754_a_HuarongRoadSolver {
    // Board dimensions
    private static final int ROWS = 5, COLS = 4;

    /**
     * Represent the board as a categorized string for hashing and deduplication
     * Pieces with the same shape are assigned the same category character
     *
     * @param board Current board state
     * @return String representation of the board
     */
    private static String boardHash(int[][] board) {
        // Critical optimization: treat pieces of the same shape as equivalent
        // This enables more effective pruning and reduces the search space
        Map<Integer, Character> pidToCategory = new HashMap<>();
        // Empty spaces (0x0) - category 0
        pidToCategory.put(0, '0');
        // Four soldiers (1x1) - category 1
        pidToCategory.put(7, '1');
        pidToCategory.put(8, '1');
        pidToCategory.put(9, '1');
        pidToCategory.put(10, '1');
        // Four generals (1x2): Zhang Fei, Zhao Yun, Ma Chao, Huang Zhong - category 2
        pidToCategory.put(3, '2');
        pidToCategory.put(4, '2');
        pidToCategory.put(5, '2');
        pidToCategory.put(6, '2');
        // Guan Yu (2x1) - category 3
        pidToCategory.put(2, '3');
        // Cao Cao (2x2) - category 4
        pidToCategory.put(1, '4');

        // Convert the board to a category string
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                sb.append(pidToCategory.get(board[r][c]));
            }
        }
        return sb.toString();
    }

    /**
     * Collect all cells occupied by each piece
     *
     * @param board Current board state
     * @return Map from piece ID to list of cell coordinates
     */
    private static Map<Integer, List<int[]>> getPositions(int[][] board) {
        Map<Integer, List<int[]>> positions = new HashMap<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int pid = board[r][c];
                if (pid != 0) {
                    positions.computeIfAbsent(pid, k -> new ArrayList<>()).add(new int[]{r, c});
                }
            }
        }
        return positions;
    }

    /**
     * Check if Cao Cao (piece ID 1) has reached the exit position
     * The exit is at position (3,1) for the top-left cell of Cao Cao
     *
     * @param board Current board state
     * @return True if Cao Cao is at the exit position
     */
    private static boolean isSolved(int[][] board) {
        List<int[]> cells = getPositions(board).get(1);
        int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE;
        for (int[] p : cells) {
            minR = Math.min(minR, p[0]);
            minC = Math.min(minC, p[1]);
        }
        return minR == 3 && minC == 1;
    }

    /**
     * Check if a piece can move in a specified direction
     *
     * @param board Current board state
     * @param cells Cells occupied by the piece
     * @param dr    Row direction (-1: up, 1: down, 0: no vertical movement)
     * @param dc    Column direction (-1: left, 1: right, 0: no horizontal movement)
     * @return True if the move is valid
     */
    private static boolean canMove(int[][] board, List<int[]> cells, int dr, int dc) {
        // Find the boundaries of the piece
        int minR = Integer.MAX_VALUE, maxR = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE, maxC = Integer.MIN_VALUE;
        for (int[] p : cells) {
            minR = Math.min(minR, p[0]);
            maxR = Math.max(maxR, p[0]);
            minC = Math.min(minC, p[1]);
            maxC = Math.max(maxC, p[1]);
        }

        // Check if the move would go out of bounds
        if (minR + dr < 0 || maxR + dr >= ROWS || minC + dc < 0 || maxC + dc >= COLS) {
            return false;
        }

        // Check if the target cells are empty
        if (dr != 0) {
            // Vertical move - check row
            int checkRow = dr < 0 ? minR + dr : maxR + dr;
            for (int c = minC; c <= maxC; c++) {
                if (board[checkRow][c] != 0) return false;
            }
        } else {
            // Horizontal move - check column
            int checkCol = dc < 0 ? minC + dc : maxC + dc;
            for (int r = minR; r <= maxR; r++) {
                if (board[r][checkCol] != 0) return false;
            }
        }
        return true;
    }

    /**
     * Apply a move to create a new board state
     *
     * @param board Current board state
     * @param pid   Piece ID to move
     * @param cells Cells occupied by the piece
     * @param dr    Row direction
     * @param dc    Column direction
     * @return New board state after the move
     */
    private static int[][] applyMove(int[][] board, int pid, List<int[]> cells, int dr, int dc) {
        // Create a deep copy of the current board
        int[][] newBoard = new int[ROWS][COLS];
        for (int r = 0; r < ROWS; r++) {
            newBoard[r] = board[r].clone();
        }

        // Clear the old positions
        for (int[] p : cells) {
            newBoard[p[0]][p[1]] = 0;
        }

        // Set the new positions
        for (int[] p : cells) {
            newBoard[p[0] + dr][p[1] + dc] = pid;
        }

        return newBoard;
    }

    /**
     * Create a deep copy of the board
     */
    private static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    /**
     * Solve the Huarong Road puzzle using BFS and print the solution
     *
     * @param board Initial board state
     */
    public static void printHuarongRoadSolution(int[][] board) {
        // Hash the initial board state
        String startKey = boardHash(board);

        // Set to track visited states
        Set<String> visited = new HashSet<>();
        visited.add(startKey);

        // Queue for BFS
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(deepCopy(board), new ArrayList<>()));

        int steps = 1;

        // BFS loop
        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            System.out.printf("BFS steps: %d, current level states count: %d%n", steps, levelCount);

            for (int i = 0; i < levelCount; i++) {
                Pair current = queue.poll();
                int[][] currentBoard = current.board;
                List<Move> currentPath = current.path;

                // Get positions of all pieces
                Map<Integer, List<int[]>> pidToPositions = getPositions(currentBoard);

                // Try moving each piece in each direction
                for (Map.Entry<Integer, List<int[]>> entry : pidToPositions.entrySet()) {
                    int pid = entry.getKey();
                    List<int[]> cells = entry.getValue();

                    for (String direction : Arrays.asList("up", "down", "left", "right")) {
                        int dr = 0, dc = 0;
                        switch (direction) {
                            case "up":
                                dr = -1;
                                break;
                            case "down":
                                dr = 1;
                                break;
                            case "left":
                                dc = -1;
                                break;
                            case "right":
                                dc = 1;
                                break;
                        }

                        // Check if the move is valid
                        if (!canMove(currentBoard, cells, dr, dc)) continue;

                        // Apply the move
                        int[][] nextBoard = applyMove(currentBoard, pid, cells, dr, dc);
                        String key = boardHash(nextBoard);

                        // Skip if this state has been visited
                        if (visited.contains(key)) continue;

                        // Mark as visited
                        visited.add(key);

                        // Create a new path with the current move
                        List<Move> newPath = new ArrayList<>(currentPath);
                        newPath.add(new Move(pid, direction));

                        // Check if the puzzle is solved
                        if (isSolved(nextBoard)) {
                            // Print the solution
                            System.out.println("Solution found in " + steps + " steps:");
                            for (Move move : newPath) {
                                System.out.printf("gameHandler.move(%d, '%s')%n", move.pid, move.dir);
                            }
                            return;
                        }

                        // Add the new state to the queue
                        queue.add(new Pair(nextBoard, newPath));
                    }
                }
            }

            steps++;
        }

        System.out.println("No solution found.");
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        // Initial board configuration:
        // 1: Cao Cao (2x2)
        // 2: Guan Yu (2x1)
        // 3-6: Four generals (1x2)
        // 7-10: Four soldiers (1x1)
        // 0: Empty spaces
        int[][] initialBoard = {
                {3, 1, 1, 4},
                {3, 1, 1, 4},
                {5, 2, 2, 6},
                {5, 7, 8, 6},
                {9, 0, 0, 10}
        };

        System.out.println("Solving Huarong Road puzzle...");
        printHuarongRoadSolution(initialBoard);
    }

    /**
     * Represent a move for tracking the solution path
     */
    private static class Move {
        int pid;       // Piece ID
        String dir;    // Direction ("up", "down", "left", "right")

        Move(int pid, String dir) {
            this.pid = pid;
            this.dir = dir;
        }
    }

    /**
     * Pair class to store a board state and its move history
     */
    private static class Pair {
        int[][] board;
        List<Move> path;

        Pair(int[][] board, List<Move> path) {
            this.board = board;
            this.path = path;
        }
    }
}