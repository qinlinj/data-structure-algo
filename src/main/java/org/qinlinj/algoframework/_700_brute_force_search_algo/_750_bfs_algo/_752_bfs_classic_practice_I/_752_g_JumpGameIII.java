package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Jump Game III (LeetCode 1306)
 * ----------------------------
 * <p>
 * Summary:
 * In this problem, we are given an array of non-negative integers and a starting index.
 * From each index i, we can jump to either i + arr[i] or i - arr[i]. We need to determine
 * if it's possible to reach any index with value 0 from the starting position.
 * <p>
 * Key Concepts:
 * 1. Graph traversal problem where each index is a node
 * 2. From each index, we can make two possible jumps (forward and backward)
 * 3. BFS/DFS to explore all reachable indices from the starting position
 * 4. Tracking visited indices to avoid cycles and redundant work
 * <p>
 * Approach:
 * - Use either BFS or DFS starting from the given start index
 * - For each index, try both i + arr[i] and i - arr[i] jumps
 * - Keep track of visited indices to avoid cycles
 * - Return true if we reach any index with value 0, false otherwise
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(n) for the visited array and the queue/stack
 */

import java.util.*;

public class _752_g_JumpGameIII {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _752_g_JumpGameIII solution = new _752_g_JumpGameIII();

        // Example 1: arr = [4,2,3,0,3,1,2], start = 5
        int[] arr1 = {4, 2, 3, 0, 3, 1, 2};
        int start1 = 5;

        boolean result1BFS = solution.canReach(arr1, start1);
        boolean result1DFS = solution.canReachDFS(arr1, start1);

        System.out.println("Example 1 (BFS): " + result1BFS); // Should output true
        System.out.println("Example 1 (DFS): " + result1DFS); // Should output true

        // Example 2: arr = [4,2,3,0,3,1,2], start = 0
        int[] arr2 = {4, 2, 3, 0, 3, 1, 2};
        int start2 = 0;

        boolean result2BFS = solution.canReach(arr2, start2);
        boolean result2DFS = solution.canReachDFS(arr2, start2);

        System.out.println("Example 2 (BFS): " + result2BFS); // Should output true
        System.out.println("Example 2 (DFS): " + result2DFS); // Should output true

        // Example 3: arr = [3,0,2,1,2], start = 2
        int[] arr3 = {3, 0, 2, 1, 2};
        int start3 = 2;

        boolean result3BFS = solution.canReach(arr3, start3);
        boolean result3DFS = solution.canReachDFS(arr3, start3);

        System.out.println("Example 3 (BFS): " + result3BFS); // Should output false
        System.out.println("Example 3 (DFS): " + result3DFS); // Should output false
    }

    /**
     * Determine if it's possible to reach any index with value 0
     * @param arr Array of non-negative integers
     * @param start Starting index
     * @return True if a zero-valued index can be reached, false otherwise
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];

        // BFS implementation
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();

            // Check if we've reached a zero-valued index
            if (arr[currentIndex] == 0) {
                return true;
            }

            // Try forward jump
            int forwardJump = currentIndex + arr[currentIndex];
            if (forwardJump < n && !visited[forwardJump]) {
                queue.offer(forwardJump);
                visited[forwardJump] = true;
            }

            // Try backward jump
            int backwardJump = currentIndex - arr[currentIndex];
            if (backwardJump >= 0 && !visited[backwardJump]) {
                queue.offer(backwardJump);
                visited[backwardJump] = true;
            }
        }

        // If we've explored all reachable indices and didn't find a zero
        return false;
    }

    /**
     * DFS implementation to determine if it's possible to reach any index with value 0
     * @param arr Array of non-negative integers
     * @param start Starting index
     * @return True if a zero-valued index can be reached, false otherwise
     */
    public boolean canReachDFS(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return dfs(arr, start, visited);
    }

    /**
     * DFS helper method
     * @param arr Array of non-negative integers
     * @param index Current index
     * @param visited Array to track visited indices
     * @return True if a zero-valued index can be reached from current index
     */
    private boolean dfs(int[] arr, int index, boolean[] visited) {
        // Check bounds and if already visited
        if (index < 0 || index >= arr.length || visited[index]) {
            return false;
        }

        // Mark as visited
        visited[index] = true;

        // Check if we've reached a zero-valued index
        if (arr[index] == 0) {
            return true;
        }

        // Try both forward and backward jumps
        return dfs(arr, index + arr[index], visited) ||
                dfs(arr, index - arr[index], visited);
    }
}