package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._741_backtracking_classic_practice_I;

/**
 * 79. Word Search
 * <p>
 * Problem Summary:
 * Given an m x n grid of characters and a word, determine if the word can be constructed from
 * adjacent cells in the grid. Adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * Key Insights:
 * - This is a classic DFS/backtracking problem similar to island problems
 * - We need to search the entire grid for possible starting points
 * - From each starting point, we explore in four directions (up, down, left, right)
 * - We need to mark visited cells to avoid revisiting them
 * - A common trick is negating the character value as a marker for visited cells
 * <p>
 * Time Complexity: O(m*n*4^L) where m,n are grid dimensions and L is word length
 * Space Complexity: O(L) for recursion stack
 */
class _741_i_WordSearch {

    boolean found = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, word, 0);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    // Start DFS from (i,j), trying to match word[p..]
    void dfs(char[][] board, int i, int j, String word, int p) {
        if (p == word.length()) {
            // The entire word has been matched
            found = true;
            return;
        }
        if (found) {
            // Already found an answer, no need to continue
            return;
        }

        int m = board.length, n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (board[i][j] != word.charAt(p)) {
            return;
        }

        // Mark the current cell as visited by negating its value
        board[i][j] = (char) (-board[i][j]);

        // Explore in all four directions
        dfs(board, i + 1, j, word, p + 1);
        dfs(board, i, j + 1, word, p + 1);
        dfs(board, i - 1, j, word, p + 1);
        dfs(board, i, j - 1, word, p + 1);

        // Restore the cell's original value for backtracking
        board[i][j] = (char) (-board[i][j]);
    }
}