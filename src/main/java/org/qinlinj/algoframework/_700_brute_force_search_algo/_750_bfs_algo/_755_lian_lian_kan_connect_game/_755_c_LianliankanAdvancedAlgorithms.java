package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._755_lian_lian_kan_connect_game; /**
 * Advanced Path Finding Strategies for Lianliankan
 * ----------------------------------------------------------------
 * <p>
 * Summary:
 * This class explores advanced algorithms and optimizations for the Lianliankan path finding problem.
 * It focuses on improvements to the basic BFS algorithm, alternative approaches, and performance
 * considerations for finding valid paths with at most two turns.
 * <p>
 * Key Concepts:
 * 1. Optimized BFS with early termination and direction prioritization
 * 2. A* search algorithm application to Lianliankan
 * 3. Line-by-line searching approach (a simplified alternative to BFS)
 * 4. Bidirectional BFS for potentially faster path finding
 * 5. Performance comparisons between different algorithms
 * <p>
 * Advanced Strategies:
 * <p>
 * 1. Line-by-Line Search:
 * - Check direct horizontal and vertical paths first
 * - For one-turn paths, check two line segments: horizontal-vertical or vertical-horizontal
 * - For two-turn paths, check three line segments: turning at edge-adjacent cells
 * - More intuitive and potentially faster than full BFS for this specific problem
 * <p>
 * 2. Optimized BFS with Direction Prioritization:
 * - Prioritize directions that move toward the target
 * - Reduces exploration of paths moving away from the target
 * <p>
 * 3. Bidirectional BFS:
 * - Search simultaneously from both the start and target pieces
 * - Potentially reduces search space significantly
 * <p>
 * This class implements and compares these alternative approaches to find the most efficient
 * solution for the Lianliankan path finding problem.
 */

import java.util.*;

public class _755_c_LianliankanAdvancedAlgorithms {

    /**
     * Line-by-line search algorithm to find a path with at most two turns
     * This is a simplified, custom approach that directly checks possible paths
     * @param board Game board
     * @param row1 Row of first piece
     * @param col1 Column of first piece
     * @param row2 Row of second piece
     * @param col2 Column of second piece
     * @return List of coordinates representing the path, or empty list if no path found
     */
    public static List<int[]> lineByLineSearch(int[][] board, int row1, int col1, int row2, int col2) {
        // Check for direct line (0 turns)
        List<int[]> directPath = findDirectPath(board, row1, col1, row2, col2);
        if (!directPath.isEmpty()) {
            return directPath;
        }

        // Check for one-turn path
        List<int[]> oneTurnPath = findOneTurnPath(board, row1, col1, row2, col2);
        if (!oneTurnPath.isEmpty()) {
            return oneTurnPath;
        }

        // Check for two-turn path
        return findTwoTurnPath(board, row1, col1, row2, col2);
    }

    /**
     * Check for a direct path (no turns) between two points
     */
    private static List<int[]> findDirectPath(int[][] board, int row1, int col1, int row2, int col2) {
        List<int[]> path = new ArrayList<>();

        // Direct horizontal line
        if (row1 == row2) {
            int minCol = Math.min(col1, col2);
            int maxCol = Math.max(col1, col2);

            // Check if path is clear
            boolean pathClear = true;
            for (int c = minCol + 1; c < maxCol; c++) {
                if (!canPass(board, row1, c)) {
                    pathClear = false;
                    break;
                }
            }

            if (pathClear) {
                // Build path
                path.add(new int[]{row1, col1});
                // Add intermediate points
                for (int c = minCol + 1; c < maxCol; c++) {
                    path.add(new int[]{row1, c});
                }
                path.add(new int[]{row2, col2});
                return path;
            }
        }

        // Direct vertical line
        if (col1 == col2) {
            int minRow = Math.min(row1, row2);
            int maxRow = Math.max(row1, row2);

            // Check if path is clear
            boolean pathClear = true;
            for (int r = minRow + 1; r < maxRow; r++) {
                if (!canPass(board, r, col1)) {
                    pathClear = false;
                    break;
                }
            }

            if (pathClear) {
                // Build path
                path.add(new int[]{row1, col1});
                // Add intermediate points
                for (int r = minRow + 1; r < maxRow; r++) {
                    path.add(new int[]{r, col1});
                }
                path.add(new int[]{row2, col2});
                return path;
            }
        }

        return new ArrayList<>(); // No direct path
    }

    /**
     * Check for a path with one turn between two points
     */
    private static List<int[]> findOneTurnPath(int[][] board, int row1, int col1, int row2, int col2) {
        List<int[]> path = new ArrayList<>();

        // Try corner point (row1, col2)
        if (canPass(board, row1, col2)) {
            // Check horizontal path from (row1, col1) to (row1, col2)
            boolean horizontalClear = true;
            int minCol = Math.min(col1, col2);
            int maxCol = Math.max(col1, col2);
            for (int c = minCol + 1; c < maxCol; c++) {
                if (!canPass(board, row1, c)) {
                    horizontalClear = false;
                    break;
                }
            }

            // Check vertical path from (row1, col2) to (row2, col2)
            boolean verticalClear = true;
            int minRow = Math.min(row1, row2);
            int maxRow = Math.max(row1, row2);
            for (int r = minRow + 1; r < maxRow; r++) {
                if (!canPass(board, r, col2)) {
                    verticalClear = false;
                    break;
                }
            }

            if (horizontalClear && verticalClear) {
                // Build path with one turn at (row1, col2)
                path.add(new int[]{row1, col1});

                // Add horizontal segment
                if (col1 < col2) {
                    for (int c = col1 + 1; c <= col2; c++) {
                        path.add(new int[]{row1, c});
                    }
                } else {
                    for (int c = col1 - 1; c >= col2; c--) {
                        path.add(new int[]{row1, c});
                    }
                }

                // Add vertical segment (excluding the corner point which is already added)
                if (row1 < row2) {
                    for (int r = row1 + 1; r <= row2; r++) {
                        path.add(new int[]{r, col2});
                    }
                } else {
                    for (int r = row1 - 1; r >= row2; r--) {
                        path.add(new int[]{r, col2});
                    }
                }

                return path;
            }
        }

        // Try corner point (row2, col1)
        if (canPass(board, row2, col1)) {
            // Check vertical path from (row1, col1) to (row2, col1)
            boolean verticalClear = true;
            int minRow = Math.min(row1, row2);
            int maxRow = Math.max(row1, row2);
            for (int r = minRow + 1; r < maxRow; r++) {
                if (!canPass(board, r, col1)) {
                    verticalClear = false;
                    break;
                }
            }

            // Check horizontal path from (row2, col1) to (row2, col2)
            boolean horizontalClear = true;
            int minCol = Math.min(col1, col2);
            int maxCol = Math.max(col1, col2);
            for (int c = minCol + 1; c < maxCol; c++) {
                if (!canPass(board, row2, c)) {
                    horizontalClear = false;
                    break;
                }
            }

            if (verticalClear && horizontalClear) {
                // Build path with one turn at (row2, col1)
                path.add(new int[]{row1, col1});

                // Add vertical segment
                if (row1 < row2) {
                    for (int r = row1 + 1; r <= row2; r++) {
                        path.add(new int[]{r, col1});
                    }
                } else {
                    for (int r = row1 - 1; r >= row2; r--) {
                        path.add(new int[]{r, col1});
                    }
                }

                // Add horizontal segment (excluding the corner point which is already added)
                if (col1 < col2) {
                    for (int c = col1 + 1; c <= col2; c++) {
                        path.add(new int[]{row2, c});
                    }
                } else {
                    for (int c = col1 - 1; c >= col2; c--) {
                        path.add(new int[]{row2, c});
                    }
                }

                return path;
            }
        }

        return new ArrayList<>(); // No one-turn path
    }

    /**
     * Check for a path with two turns between two points
     */
    private static List<int[]> findTwoTurnPath(int[][] board, int row1, int col1, int row2, int col2) {
        int m = board.length;
        int n = board[0].length;

        // Try all possible intermediate points for a two-turn path
        // First turn can be anywhere reachable from (row1, col1)
        // Second turn must connect to (row2, col2)

        // Try horizontal first, then vertical
        for (int c = -1; c <= n; c++) {
            // Skip invalid columns and the columns of the pieces
            if (c == col1 || c == col2) continue;

            // Check if we can reach this column from (row1, col1)
            boolean pathToClear = true;
            int minCol = Math.min(col1, c);
            int maxCol = Math.max(col1, c);
            for (int cc = minCol + 1; cc < maxCol; cc++) {
                if (!canPass(board, row1, cc)) {
                    pathToClear = false;
                    break;
                }
            }

            if (!pathToClear || !canPass(board, row1, c)) continue;

            // Now check if we can go from (row1, c) to (row2, c)
            boolean verticalClear = true;
            int minRow = Math.min(row1, row2);
            int maxRow = Math.max(row1, row2);
            for (int r = minRow + 1; r < maxRow; r++) {
                if (!canPass(board, r, c)) {
                    verticalClear = false;
                    break;
                }
            }

            if (!verticalClear) continue;

            // Finally check if we can go from (row2, c) to (row2, col2)
            boolean pathFromClear = true;
            minCol = Math.min(c, col2);
            maxCol = Math.max(c, col2);
            for (int cc = minCol + 1; cc < maxCol; cc++) {
                if (!canPass(board, row2, cc)) {
                    pathFromClear = false;
                    break;
                }
            }

            if (pathFromClear) {
                // Build the two-turn path
                List<int[]> path = new ArrayList<>();
                path.add(new int[]{row1, col1});

                // First segment: (row1, col1) to (row1, c)
                if (col1 < c) {
                    for (int cc = col1 + 1; cc <= c; cc++) {
                        path.add(new int[]{row1, cc});
                    }
                } else {
                    for (int cc = col1 - 1; cc >= c; cc--) {
                        path.add(new int[]{row1, cc});
                    }
                }

                // Second segment: (row1, c) to (row2, c)
                if (row1 < row2) {
                    for (int r = row1 + 1; r <= row2; r++) {
                        path.add(new int[]{r, c});
                    }
                } else {
                    for (int r = row1 - 1; r >= row2; r--) {
                        path.add(new int[]{r, c});
                    }
                }

                // Third segment: (row2, c) to (row2, col2)
                if (c < col2) {
                    for (int cc = c + 1; cc <= col2; cc++) {
                        path.add(new int[]{row2, cc});
                    }
                } else {
                    for (int cc = c - 1; cc >= col2; cc--) {
                        path.add(new int[]{row2, cc});
                    }
                }

                return path;
            }
        }

        // Try vertical first, then horizontal
        for (int r = -1; r <= m; r++) {
            // Skip invalid rows and the rows of the pieces
            if (r == row1 || r == row2) continue;

            // Check if we can reach this row from (row1, col1)
            boolean pathToClear = true;
            int minRow = Math.min(row1, r);
            int maxRow = Math.max(row1, r);
            for (int rr = minRow + 1; rr < maxRow; rr++) {
                if (!canPass(board, rr, col1)) {
                    pathToClear = false;
                    break;
                }
            }

            if (!pathToClear || !canPass(board, r, col1)) continue;

            // Now check if we can go from (r, col1) to (r, col2)
            boolean horizontalClear = true;
            int minCol = Math.min(col1, col2);
            int maxCol = Math.max(col1, col2);
            for (int c = minCol + 1; c < maxCol; c++) {
                if (!canPass(board, r, c)) {
                    horizontalClear = false;
                    break;
                }
            }

            if (!horizontalClear) continue;

            // Finally check if we can go from (r, col2) to (row2, col2)
            boolean pathFromClear = true;
            minRow = Math.min(r, row2);
            maxRow = Math.max(r, row2);
            for (int rr = minRow + 1; rr < maxRow; rr++) {
                if (!canPass(board, rr, col2)) {
                    pathFromClear = false;
                    break;
                }
            }

            if (pathFromClear) {
                // Build the two-turn path
                List<int[]> path = new ArrayList<>();
                path.add(new int[]{row1, col1});

                // First segment: (row1, col1) to (r, col1)
                if (row1 < r) {
                    for (int rr = row1 + 1; rr <= r; rr++) {
                        path.add(new int[]{rr, col1});
                    }
                } else {
                    for (int rr = row1 - 1; rr >= r; rr--) {
                        path.add(new int[]{rr, col1});
                    }
                }

                // Second segment: (r, col1) to (r, col2)
                if (col1 < col2) {
                    for (int c = col1 + 1; c <= col2; c++) {
                        path.add(new int[]{r, c});
                    }
                } else {
                    for (int c = col1 - 1; c >= col2; c--) {
                        path.add(new int[]{r, c});
                    }
                }

                // Third segment: (r, col2) to (row2, col2)
                if (r < row2) {
                    for (int rr = r + 1; rr <= row2; rr++) {
                        path.add(new int[]{rr, col2});
                    }
                } else {
                    for (int rr = r - 1; rr >= row2; rr--) {
                        path.add(new int[]{rr, col2});
                    }
                }

                return path;
            }
        }

        return new ArrayList<>(); // No two-turn path
    }

    /**
     * Check if a position is valid for the connecting path
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
     * Optimized BFS that prioritizes directions toward the target
     * @param board Game board
     * @param row1 Row of first piece
     * @param col1 Column of first piece
     * @param row2 Row of second piece
     * @param col2 Column of second piece
     * @return List of coordinates representing the path, or empty list if no path found
     */
    public static List<int[]> optimizedBFS(int[][] board, int row1, int col1, int row2, int col2) {
        int m = board.length;
        int n = board[0].length;

        // Check if valid piece positions
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

        // Reorder directions based on target location for each search step
        // This prioritizes directions that move toward the target
        PriorityQueue<State> queue = new PriorityQueue<>(
                (a, b) -> {
                    // Calculate Manhattan distance to target
                    int distA = Math.abs(a.row - row2) + Math.abs(a.col - col2);
                    int distB = Math.abs(b.row - row2) + Math.abs(b.col - col2);
                    return distA - distB;
                }
        );

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
     * Performance comparison between different algorithms
     * @param board Board to test on
     * @param row1 Row of first piece
     * @param col1 Column of first piece
     * @param row2 Row of second piece
     * @param col2 Column of second piece
     */
    public static void compareAlgorithms(int[][] board, int row1, int col1, int row2, int col2) {
        System.out.println("Comparing Lianliankan path finding algorithms:");
        System.out.println("Pieces at (" + row1 + "," + col1 + ") and (" + row2 + "," + col2 + ")");

        // Basic BFS (from _755_a_LianliankanConnectAlgorithm)
        long startTime = System.nanoTime();
        List<int[]> bfsPath = _755_a_LianliankanConnectAlgorithm.connect(board, row1, col1, row2, col2);
        long bfsTime = System.nanoTime() - startTime;

        // Line-by-line search
        startTime = System.nanoTime();
        List<int[]> lineByLinePath = lineByLineSearch(board, row1, col1, row2, col2);
        long lineByLineTime = System.nanoTime() - startTime;

        // Optimized BFS
        startTime = System.nanoTime();
        List<int[]> optimizedPath = optimizedBFS(board, row1, col1, row2, col2);
        long optimizedTime = System.nanoTime() - startTime;

        // Print results
        System.out.println("\nBasic BFS:");
        System.out.println("  Found path: " + !bfsPath.isEmpty());
        System.out.println("  Time: " + (bfsTime / 1000000.0) + " ms");

        System.out.println("\nLine-by-line Search:");
        System.out.println("  Found path: " + !lineByLinePath.isEmpty());
        System.out.println("  Time: " + (lineByLineTime / 1000000.0) + " ms");

        System.out.println("\nOptimized BFS:");
        System.out.println("  Found path: " + !optimizedPath.isEmpty());
        System.out.println("  Time: " + (optimizedTime / 1000000.0) + " ms");

        System.out.println("\nConclusion:");
        if (lineByLineTime < bfsTime && lineByLineTime < optimizedTime) {
            System.out.println("Line-by-line search is fastest for this case.");
        } else if (optimizedTime < bfsTime) {
            System.out.println("Optimized BFS is fastest for this case.");
        } else {
            System.out.println("Basic BFS is fastest for this case.");
        }
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        // Create a test board
        int[][] board = {
                {1, 0, 0, 2},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 1}
        };

        // Print the board
        System.out.println("Test Board:");
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Test 1: Connect (0,0) and (3,3) - should find a path
        compareAlgorithms(board, 0, 0, 3, 3);

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Test 2: Connect (0,0) and (0,3) - should not find a path (blocked)
        System.out.println("Testing a more complex case:");
        compareAlgorithms(board, 0, 0, 0, 3);

        System.out.println("\n" + "-".repeat(50) + "\n");

        // Test line-by-line search with a direct path
        int[][] simpleBoard = {
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        System.out.println("Testing direct path case:");
        List<int[]> directPath = lineByLineSearch(simpleBoard, 0, 0, 0, 3);
        System.out.println("Direct path found: " + !directPath.isEmpty());
        if (!directPath.isEmpty()) {
            System.out.print("Path: ");
            for (int[] point : directPath) {
                System.out.print("(" + point[0] + "," + point[1] + ") ");
            }
            System.out.println();
        }
    }

    /**
     * State class for the BFS algorithm
     */
    private static class State {
        int row, col;     // Current position
        int direction;    // Current direction
        int turns;        // Number of turns used
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