package org.qinlinj.algoframework._600_graph_algo._620_union_find._622_union_find_practice;

import java.util.*;

/**
 * LeetCode 130: Surrounded Regions
 * <p>
 * Problem Description:
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally
 * surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * Surrounded regions are those that cannot reach the border of the board.
 * <p>
 * This problem demonstrates a creative application of Union-Find:
 * 1. Create a "dummy" node to represent the border
 * 2. Connect all border 'O's and their adjacent 'O's to this dummy node
 * 3. After processing, any 'O' not connected to the dummy node is surrounded and should be flipped
 * <p>
 * Key Insights:
 * - Border cells are special because they can never be captured
 * - Any 'O' connected to a border 'O' (directly or indirectly) cannot be captured
 * - Union-Find provides an elegant way to track these connections
 * - Adding a virtual "dummy" node simplifies the connectivity check
 * <p>
 * Time Complexity: O(m*n) where m and n are the dimensions of the board
 * Space Complexity: O(m*n) for the Union-Find data structure
 */
public class _622_b_SurroundedRegions {

    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        _622_b_SurroundedRegions solution = new _622_b_SurroundedRegions();

        // Example 1
        char[][] board1 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        System.out.println("Example 1 - Before:");
        solution.printBoard(board1);

        solution.solve(board1);

        System.out.println("Example 1 - After:");
        solution.printBoard(board1);
        // Expected: The 'O's in the middle should be captured (turned to 'X')
        // The 'O' at the bottom edge should remain 'O'

        // Example 2
        char[][] board2 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        System.out.println("Example 2 - Before:");
        solution.printBoard(board2);

        solution.solve(board2);

        System.out.println("Example 2 - After:");
        solution.printBoard(board2);
        // Expected: None of the 'O's should be captured because they connect to the border
    }

    /**
     * Solves the Surrounded Regions problem using Union-Find
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int rows = board.length;
        int cols = board[0].length;

        // Create Union-Find data structure with an extra node for the "dummy" border node
        UnionFind uf = new UnionFind(rows * cols + 1);
        int dummyNode = rows * cols;

        // Connect first and last column 'O's with the dummy node
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                uf.union(i * cols, dummyNode);
            }
            if (board[i][cols - 1] == 'O') {
                uf.union(i * cols + cols - 1, dummyNode);
            }
        }

        // Connect first and last row 'O's with the dummy node
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') {
                uf.union(j, dummyNode);
            }
            if (board[rows - 1][j] == 'O') {
                uf.union((rows - 1) * cols + j, dummyNode);
            }
        }

        // Direction arrays for exploring adjacent cells
        int[][] directions = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

        // Connect inner 'O's with their adjacent 'O's
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == 'O') {
                    // Check all four adjacent cells
                    for (int[] dir : directions) {
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];

                        if (board[newRow][newCol] == 'O') {
                            // Connect current 'O' with adjacent 'O'
                            uf.union(i * cols + j, newRow * cols + newCol);
                        }
                    }
                }
            }
        }

        // Flip all 'O's that are not connected to the dummy node (border)
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (board[i][j] == 'O' && !uf.connected(i * cols + j, dummyNode)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * Helper method to print the board for visualization
     */
    private void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    /**
     * Union-Find implementation
     */
    class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;  // Using rank instead of size for this implementation

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) return;

            // Union by rank: attach smaller rank tree under root of higher rank tree
            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }

            count--;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int count() {
            return count;
        }
    }
}