package org.qinlinj.algoframework._100_algo_core_framework._180_binary_tree_core._185_tree_perspective_algorithms._185_c_dfs_example;

import org.qinlinj.algoframework._100_algo_core_framework._180_binary_tree_core._185_tree_perspective_algorithms.TreeAlgorithmsComparison;

public class DFSExample {
    // =====================================================
    // EXAMPLE 3: DFS - FOCUS ON "NODES"
    // =====================================================

    // Example 3A: Increment every node value in a binary tree
    public void incrementTreeValues(TreeAlgorithmsComparison.TreeNode root) {
        if (root == null) return;

        // Process this node
        root.val++;
        System.out.println("Incremented node value to: " + root.val);

        // Continue DFS to children
        incrementTreeValues(root.left);
        incrementTreeValues(root.right);
    }

    // Example 3B: DFS on a grid (island problem)
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsGrid(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfsGrid(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check boundaries and if current cell is land
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
            return;
        }

        // Mark as visited
        grid[r][c] = '0';
        System.out.println("Visited cell at: [" + r + "," + c + "]");

        // Visit all neighbors (4-directional)
        dfsGrid(grid, r + 1, c);
        dfsGrid(grid, r - 1, c);
        dfsGrid(grid, r, c + 1);
        dfsGrid(grid, r, c - 1);
    }

    // Example 3C: DFS with node entry/exit tracking
    public void dfsWithTracking(TreeAlgorithmsComparison.TreeNode root) {
        if (root == null) return;

        // Node entry - processing happens outside loop
        System.out.println("Entering node " + root.val);

        // Visit children
        if (root.left != null) dfsWithTracking(root.left);
        if (root.right != null) dfsWithTracking(root.right);

        // Node exit - processing happens outside loop
        System.out.println("Exiting node " + root.val);
    }
}
