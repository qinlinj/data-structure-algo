package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Keys and Rooms (LeetCode 841)
 * ----------------------------
 * <p>
 * Summary:
 * This problem simulates visiting rooms in a building. Each room contains keys to other rooms.
 * Starting from room 0, we need to determine if it's possible to visit all rooms by collecting
 * keys and using them to unlock other rooms.
 * <p>
 * Key Concepts:
 * 1. Graph traversal - we can view rooms as nodes and keys as directed edges
 * 2. BFS/DFS to explore all reachable rooms from room 0
 * 3. Tracking visited rooms to ensure we don't revisit them
 * <p>
 * Approach:
 * - Implement either DFS or BFS starting from room 0
 * - Use a visited array/set to keep track of which rooms we've visited
 * - For each room, collect all keys and continue the exploration
 * - At the end, check if all rooms have been visited
 * <p>
 * Time Complexity: O(n + k) where n is the number of rooms and k is the total number of keys
 * Space Complexity: O(n) for the visited array and the queue/stack
 */

import java.util.*;

public class _752_f_KeysAndRooms {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _752_f_KeysAndRooms solution = new _752_f_KeysAndRooms();

        // Example 1: [[1],[2],[3],[]]
        // Room 0 has key to room 1
        // Room 1 has key to room 2
        // Room 2 has key to room 3
        // Room 3 has no keys
        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(Arrays.asList(1));
        rooms1.add(Arrays.asList(2));
        rooms1.add(Arrays.asList(3));
        rooms1.add(Arrays.asList());

        boolean result1 = solution.canVisitAllRooms(rooms1);
        boolean result1BFS = solution.canVisitAllRoomsBFS(rooms1);
        System.out.println("Example 1 (DFS): " + result1); // Should output true
        System.out.println("Example 1 (BFS): " + result1BFS); // Should output true

        // Example 2: [[1,3],[3,0,1],[2],[0]]
        // Room 0 has keys to rooms 1 and 3
        // Room 1 has keys to rooms 0, 1, and 3
        // Room 2 has key to room 2
        // Room 3 has key to room 0
        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));
        rooms2.add(Arrays.asList(3, 0, 1));
        rooms2.add(Arrays.asList(2));
        rooms2.add(Arrays.asList(0));

        boolean result2 = solution.canVisitAllRooms(rooms2);
        boolean result2BFS = solution.canVisitAllRoomsBFS(rooms2);
        System.out.println("Example 2 (DFS): " + result2); // Should output false
        System.out.println("Example 2 (BFS): " + result2BFS); // Should output false

        // Additional example
        List<List<Integer>> rooms3 = new ArrayList<>();
        rooms3.add(Arrays.asList(1, 2, 3));  // Room 0 has keys to all other rooms
        rooms3.add(Arrays.asList());         // Room 1 has no keys
        rooms3.add(Arrays.asList());         // Room 2 has no keys
        rooms3.add(Arrays.asList());         // Room 3 has no keys

        boolean result3 = solution.canVisitAllRooms(rooms3);
        boolean result3BFS = solution.canVisitAllRoomsBFS(rooms3);
        System.out.println("Example 3 (DFS): " + result3); // Should output true
        System.out.println("Example 3 (BFS): " + result3BFS); // Should output true
    }

    /**
     * Determine if all rooms can be visited starting from room 0
     * @param rooms List of rooms where each room contains a list of keys
     * @return True if all rooms can be visited, false otherwise
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];

        // Start with room 0
        dfs(rooms, 0, visited);

        // Check if all rooms have been visited
        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false;
            }
        }

        return true;
    }

    /**
     * DFS implementation to explore all reachable rooms
     * @param rooms List of rooms
     * @param currentRoom Current room being visited
     * @param visited Array to track visited rooms
     */
    private void dfs(List<List<Integer>> rooms, int currentRoom, boolean[] visited) {
        // Mark current room as visited
        visited[currentRoom] = true;

        // Visit all rooms reachable from current room
        for (int key : rooms.get(currentRoom)) {
            if (!visited[key]) {
                dfs(rooms, key, visited);
            }
        }
    }

    /**
     * BFS implementation to explore all reachable rooms
     * @param rooms List of rooms
     * @return True if all rooms can be visited, false otherwise
     */
    public boolean canVisitAllRoomsBFS(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        // Start with room 0
        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();

            // Collect keys and visit all unlocked rooms
            for (int key : rooms.get(currentRoom)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.offer(key);
                }
            }
        }

        // Check if all rooms have been visited
        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false;
            }
        }

        return true;
    }
}