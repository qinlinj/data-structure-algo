package org.qinlinj.algoframework._100_core_framework._170_bfs_framework._174_bidirectional_bfs;

import java.util.*;

// @formatter:off
/**
 * Enhanced Bidirectional BFS Solutions for Classic Puzzles
 *
 * This class provides implementations of both standard BFS and optimized bidirectional BFS
 * for two classic puzzle problems:
 *
 * 1. Sliding Puzzle (LeetCode 773): A 2x3 board where numbers can slide into an empty space
 * 2. Open the Lock (LeetCode 752): A 4-wheel combination lock with possible deadends
 *
 * ==================================================================================
 * BIDIRECTIONAL BFS CONCEPT:
 * ==================================================================================
 *
 * Traditional BFS:
 *   Start → ●
 *          /|\
 *         ● ● ●
 *        /|\ /|\
 *       ● ● ● ● ● ●
 *             ... until reaching target
 *
 * Bidirectional BFS:
 *   Start → ●     ● ← Target
 *          /|\   /|\
 *         ● ● ● ● ● ●
 *          \|/   \|/
 *           ● = ●    (meet in the middle)
 *
 * WHY IT'S MORE EFFICIENT:
 * If each node has b neighbors and the shortest path has length d:
 * - Traditional BFS explores O(b^d) nodes
 * - Bidirectional BFS explores O(2*b^(d/2)) nodes
 *
 * For b=8, d=10:
 * - Traditional: 8^10 ≈ 1 billion nodes
 * - Bidirectional: 2*8^5 ≈ 65,536 nodes (16,000 times fewer!)
 */

public class BidirectionalBFSSolutions {
    /**
     * ==================================================================================
     * SLIDING PUZZLE IMPLEMENTATION (LEETCODE 773)
     * ==================================================================================
     *
     * Problem description:
     * - We have a 2x3 board with numbers 0-5 (0 represents the empty space)
     * - We can move the empty space up, down, left, or right (swapping with adjacent numbers)
     * - Goal: Find minimum moves to reach the target state [[1,2,3],[4,5,0]]
     *
     * Visual representation of the board:
     * [0][1][2]
     * [3][4][5]
     *
     * Example board state: [[4,1,2],[5,0,3]] is represented as the string "412503"
     * Target state: [[1,2,3],[4,5,0]] is represented as the string "123450"
     */

    /**
     * This main method demonstrates the advantage of bidirectional BFS by comparing
     * the two implementations across both problems with detailed explanations.
     */
    public static void main(String[] args) {
        BidirectionalBFSSolutions solution = new BidirectionalBFSSolutions();

        System.out.println("===== SLIDING PUZZLE PROBLEM =====");

        // Example 1: Solvable sliding puzzle
        int[][] slidingBoard1 = {{4, 1, 2}, {5, 0, 3}};
        System.out.println("Example 1: Board = [[4,1,2],[5,0,3]]");
        System.out.println("Visual Representation:");
        System.out.println("[4][1][2]");
        System.out.println("[5][0][3]  (0 is the empty space)");
        System.out.println("\nTarget State:");
        System.out.println("[1][2][3]");
        System.out.println("[4][5][0]");

        long startTime = System.nanoTime();
        int standardResult = solution.slidingPuzzle(slidingBoard1);
        long standardTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int biResult = solution.slidingPuzzleBidirectional(slidingBoard1);
        long biTime = System.nanoTime() - startTime;

        System.out.println("\nStandard BFS result: " + standardResult + " moves");
        System.out.println("Bidirectional BFS result: " + biResult + " moves");
        System.out.println("Time comparison: Standard=" + standardTime/1000000.0 + "ms, Bidirectional=" + biTime/1000000.0 + "ms");
        System.out.println();

        // Example 2: Unsolvable sliding puzzle
        int[][] slidingBoard2 = {{1, 2, 3}, {5, 4, 0}};
        System.out.println("Example 2: Board = [[1,2,3],[5,4,0]]");
        System.out.println("Visual Representation:");
        System.out.println("[1][2][3]");
        System.out.println("[5][4][0]  (0 is the empty space)");
        System.out.println("\nThis board is unsolvable because the parity is wrong.");

        standardResult = solution.slidingPuzzle(slidingBoard2);
        biResult = solution.slidingPuzzleBidirectional(slidingBoard2);
        System.out.println("\nStandard BFS result: " + standardResult);
        System.out.println("Bidirectional BFS result: " + biResult);
        System.out.println();

        System.out.println("===== OPEN THE LOCK PROBLEM =====");

        // Example 1: Solvable lock
        String[] deadends1 = {"0201", "0101", "0102", "1212", "2002"};
        String target1 = "0202";
        System.out.println("Example 1: Target = 0202, Deadends include 0201, 0101, etc.");
        System.out.println("Visual Representation of Solution Path:");
        System.out.println("0000 → 1000 → 1100 → 1200 → 1201 → 1202 → 0202");
        System.out.println("(Each arrow represents turning one wheel by one digit)");

        startTime = System.nanoTime();
        standardResult = solution.openLock(deadends1, target1);
        standardTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        biResult = solution.openLockBidirectional(deadends1, target1);
        biTime = System.nanoTime() - startTime;

        System.out.println("\nStandard BFS result: " + standardResult + " turns");
        System.out.println("Bidirectional BFS result: " + biResult + " turns");
        System.out.println("Time comparison: Standard=" + standardTime/1000000.0 + "ms, Bidirectional=" + biTime/1000000.0 + "ms");
        System.out.println();

        // Example 2: Unsolvable lock
        String[] deadends2 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target2 = "8888";
        System.out.println("Example 2: Target = 8888, Deadends surround the target");
        System.out.println("This is unsolvable because all neighbors of 8888 are deadends:");
        System.out.println("- 8888's neighbors: 7888, 9888, 8788, 8988, 8878, 8898, 8887, 8889");
        System.out.println("- All of these are in the deadends list, so 8888 is unreachable");

        standardResult = solution.openLock(deadends2, target2);
        biResult = solution.openLockBidirectional(deadends2, target2);
        System.out.println("\nStandard BFS result: " + standardResult);
        System.out.println("Bidirectional BFS result: " + biResult);
        System.out.println();

        // Explanation of bidirectional BFS advantage with visualization
        System.out.println("===== BIDIRECTIONAL BFS VISUALIZATION =====");
        System.out.println("Consider a graph where each node has 3 neighbors:");
        System.out.println();
        System.out.println("Standard BFS from Start:");
        System.out.println("Level 0:          S          (1 node)");
        System.out.println("Level 1:       /  |  \\        (3 nodes)");
        System.out.println("Level 2:    / /   |   \\ \\       (9 nodes)");
        System.out.println("Level 3:   ⋯⋯⋯⋯⋯⋯⋯⋯⋯⋯⋯      (27 nodes)");
        System.out.println("Level 4:   ⋯⋯⋯⋯⋯⋯⋯⋯⋯⋯⋯      (81 nodes)");
        System.out.println("Total:                      (121 nodes)");
        System.out.println();
        System.out.println("Bidirectional BFS:");
        System.out.println("From Start:       S          (1 node)");
        System.out.println("Level 1:       /  |  \\        (3 nodes)");
        System.out.println("Level 2:    / /   |   \\ \\       (9 nodes)");
        System.out.println();
        System.out.println("From Target:      T          (1 node)");
        System.out.println("Level 1:       /  |  \\        (3 nodes)");
        System.out.println("Level 2:    / /   |   \\ \\       (9 nodes)");
        System.out.println();
        System.out.println("When they meet in the middle: (13 + 13 = 26 nodes)");
        System.out.println("That's only ~21% of the nodes explored by standard BFS!");
        System.out.println();
        System.out.println("===== IMPLEMENTATION DETAILS =====");
        System.out.println("Key Optimizations in the Bidirectional BFS:");
        System.out.println("1. Using Sets instead of Queues:");
        System.out.println("   - Allows O(1) lookups to check if frontiers intersect");
        System.out.println("   - Example: if (endSet.contains(neighbor)) { return step + 1; }");
        System.out.println();
        System.out.println("2. Always expanding the smaller frontier:");
        System.out.println("   - if (startSet.size() > endSet.size()) { swap(startSet, endSet); }");
        System.out.println("   - Ensures we always expand the smaller set, reducing total nodes");
        System.out.println("   - Think of it as: 'Always dig from the side with less dirt'");
        System.out.println();
        System.out.println("3. Early termination when frontiers meet:");
        System.out.println("   - Stop as soon as any node appears in both frontiers");
        System.out.println("   - Don't need to process entire levels");
        System.out.println();
        System.out.println("In summary, bidirectional BFS is a powerful optimization technique");
        System.out.println("when both start and end states are known. It can dramatically reduce");
        System.out.println("the search space, especially for problems with large branching factors.");
    }

    /**
     * Solves the sliding puzzle using standard BFS.
     *
     * VISUALIZATION OF STANDARD BFS:
     *
     * Starting with board [[4,1,2],[5,0,3]] ("412503"):
     *
     * Step 0: Queue = ["412503"]
     *
     * Step 1: Dequeue "412503", find its neighbors by moving the empty space (0):
     *   - Swap 0 with 1: "402513"
     *   - Swap 0 with 5: "415203"
     *   - Queue = ["402513", "415203"]
     *
     * Step 2: Dequeue "402513", find neighbors...
     *   - Swap 0 with 4: "420513"
     *   - Swap 0 with 2: "042513"
     *   - Queue = ["415203", "420513", "042513"]
     *
     * And so on until we reach "123450" or exhaust all possibilities.
     *
     * @param board Initial state of the board as 2x3 array
     * @return Minimum moves to reach target, or -1 if impossible
     */
    public int slidingPuzzle(int[][] board) {
        // Target state: "123450" represents [[1,2,3],[4,5,0]]
        String target = "123450";

        // Convert initial board to string representation
        // Example: [[4,1,2],[5,0,3]] becomes "412503"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();

        // If we're already at the target state, return 0
        if (start.equals(target)) {
            return 0;
        }

        // Initialize BFS data structures
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Start BFS from initial state
        queue.offer(start);
        visited.add(start);

        // Track number of moves (levels in BFS)
        int step = 0;

        // Standard BFS loop
        while (!queue.isEmpty()) {
            // Process all states at current level (step count)
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // Check if we've reached the target
                if (current.equals(target)) {
                    return step;
                }

                // Generate all possible next states
                List<String> neighbors = getSlidingNeighbors(current);

                // Add unvisited neighbors to queue
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            // Increment step count after processing all states at current level
            step++;
        }

        // If we exhaust all possibilities without finding target, it's impossible
        return -1;
    }

    /**
     * Solves the sliding puzzle using bidirectional BFS for optimization.
     *
     * VISUALIZATION OF BIDIRECTIONAL BFS:
     *
     * Start state: [[4,1,2],[5,0,3]] ("412503")
     * Target state: [[1,2,3],[4,5,0]] ("123450")
     *
     * Step 0:
     *   - startSet = {"412503"}
     *   - endSet = {"123450"}
     *
     * Step 1: Expand startSet (smaller set)
     *   - From "412503", generate neighbors: {"402513", "415203"}
     *   - Check if any neighbor is in endSet (no)
     *   - startSet becomes {"402513", "415203"}
     *
     * Step 2: Expand startSet again (if still smaller)
     *   - From "402513", generate neighbors: {"420513", "042513", ...}
     *   - From "415203", generate neighbors: {"145203", "415023", ...}
     *   - Check if any neighbor is in endSet (no)
     *   - startSet becomes {new neighbors...}
     *
     * This continues, alternating between expanding the smaller set until
     * we find a state that exists in both sets, indicating a path has been found.
     *
     * @param board Initial state of the board as 2x3 array
     * @return Minimum moves to reach target, or -1 if impossible
     */
    public int slidingPuzzleBidirectional(int[][] board) {
        // Target state: "123450" represents [[1,2,3],[4,5,0]]
        String target = "123450";

        // Convert initial board to string representation
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();

        // Handle edge case: already at target
        if (start.equals(target)) {
            return 0;
        }

        // Initialize two sets for bidirectional search
        // - startSet: states reachable from the start
        // - endSet: states that can reach the target
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        // Track visited states to avoid cycles
        Set<String> visited = new HashSet<>();

        // Initialize sets with starting points
        startSet.add(start);
        endSet.add(target);

        // Track number of moves
        int step = 0;

        // Bidirectional BFS loop
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            // KEY OPTIMIZATION: Always expand the smaller set
            // This significantly reduces the search space
            if (startSet.size() > endSet.size()) {
                // Swap sets if endSet is smaller
                Set<String> temp = startSet;
                startSet = endSet;
                endSet = temp;
            }

            // Set to hold next level's states
            Set<String> nextSet = new HashSet<>();

            // Expand each state in the current level
            for (String current : startSet) {
                // Generate all possible next states
                List<String> neighbors = getSlidingNeighbors(current);

                // Process each neighbor
                for (String neighbor : neighbors) {
                    // If the other set contains this neighbor, we've found a path!
                    // This is the key insight of bidirectional BFS - we check for intersection
                    if (endSet.contains(neighbor)) {
                        // Add 1 because we're moving from current to neighbor
                        return step + 1;
                    }

                    // Otherwise, if not visited, add to next level
                    if (!visited.contains(neighbor)) {
                        nextSet.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            // Update for next iteration - the new frontier becomes nextSet
            startSet = nextSet;
            step++;
        }

        // If sets become empty without finding intersection, no solution exists
        return -1;
    }

    /**
     * Gets all possible next states for the sliding puzzle by moving the empty space.
     *
     * ADJACENCY MAPPING VISUALIZATION:
     *
     * Board positions are numbered:
     * [0][1][2]
     * [3][4][5]
     *
     * For each position, the valid moves (adjacent positions) are:
     * - Position 0: can move to positions 1, 3
     *   [0]→[1][2]     [3][1][2]
     *   [3][4][5]  or  [0][4][5]
     *
     * - Position 1: can move to positions 0, 2, 4
     *   [1]→[0][2]     [0][1]→[2]     [0][4][2]
     *   [3][4][5]  or  [3][4][5]  or  [3][1][5]
     *
     * - Position 2: can move to positions 1, 5
     *   [0][2]→[1]     [0][1][5]
     *   [3][4][5]  or  [3][4][2]
     *
     * And so on for positions 3, 4, and 5.
     *
     * @param state Current state as a string (e.g., "412503")
     * @return List of all possible next states
     */
    private List<String> getSlidingNeighbors(String state) {
        // Define adjacency mapping for each position in the 2x3 board
        // This mapping shows which positions are adjacent to each position
        int[][] neighbors = new int[][] {
                {1, 3},       // Position 0 can move to 1 and 3
                {0, 2, 4},    // Position 1 can move to 0, 2, and 4
                {1, 5},       // Position 2 can move to 1 and 5
                {0, 4},       // Position 3 can move to 0 and 4
                {1, 3, 5},    // Position 4 can move to 1, 3, and 5
                {2, 4}        // Position 5 can move to 2 and 4
        };

        List<String> result = new ArrayList<>();

        // Find the position of the empty space (0)
        int emptyPos = state.indexOf('0');

        // Generate all possible moves by swapping empty space with its neighbors
        for (int adj : neighbors[emptyPos]) {
            // Create new state by swapping
            result.add(swap(state, emptyPos, adj));
        }

        return result;
    }

    /**
     * ==================================================================================
     * OPEN THE LOCK IMPLEMENTATION (LEETCODE 752)
     * ==================================================================================
     *
     * Problem description:
     * - We have a 4-wheel lock, each wheel with digits 0-9
     * - Each wheel can be turned forward (+1) or backward (-1)
     *   (9 wraps to 0, and 0 wraps to 9)
     * - Given deadends and a target, find minimum turns to reach target
     *
     * Visual representation of the lock:
     * [0][0][0][0] -> Initial state
     *
     * Example deadends: ["0201","0101","0102","1212","2002"]
     * Example target: "0202"
     */

    /**
     * Swaps two characters in a string to create a new string.
     *
     * Example:
     * swap("412503", 4, 1) would swap the characters at positions 4 and 1
     * "412503" becomes "402513"
     *    ↑ ↑
     *    positions to swap
     *
     * @param s The original string
     * @param i First position
     * @param j Second position
     * @return New string with characters at positions i and j swapped
     */
    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    /**
     * Solves the lock problem using standard BFS.
     *
     * VISUALIZATION OF STANDARD BFS FOR LOCK:
     *
     * Starting with "0000":
     *
     * Step 0: Queue = ["0000"]
     *
     * Step 1: Dequeue "0000", find its neighbors by turning each wheel:
     *   - Turn 1st wheel: "1000", "9000"
     *   - Turn 2nd wheel: "0100", "0900"
     *   - Turn 3rd wheel: "0010", "0090"
     *   - Turn 4th wheel: "0001", "0009"
     *   - Queue = ["1000", "9000", "0100", "0900", "0010", "0090", "0001", "0009"]
     *   - (Excluding any deadends)
     *
     * Step 2: Dequeue "1000", find neighbors...
     *   - Turn 1st wheel: "2000", "0000" (already visited, skip)
     *   - Turn 2nd wheel: "1100", "1900"
     *   - Turn 3rd wheel: "1010", "1090"
     *   - Turn 4th wheel: "1001", "1009"
     *   - Queue = ["9000", "0100", ... , "1100", "1900", "1010", "1090", "1001", "1009"]
     *
     * And so on until we reach the target or exhaust all possibilities.
     *
     * @param deadends Combinations that will lock the lock
     * @param target Target combination
     * @return Minimum moves to reach target, or -1 if impossible
     */
    public int openLock(String[] deadends, String target) {
        // Convert deadends array to a set for O(1) lookup
        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));

        // If starting position "0000" is a deadend, return -1 immediately
        if (deadendSet.contains("0000")) {
            return -1;
        }

        // Handle edge case: already at target
        if (target.equals("0000")) {
            return 0;
        }

        // Initialize BFS data structures
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Start BFS from "0000"
        queue.offer("0000");
        visited.add("0000");

        // Track number of turns
        int step = 0;

        // Standard BFS loop
        while (!queue.isEmpty()) {
            // Process all combinations at current turn count
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // Check if we've reached the target
                if (current.equals(target)) {
                    return step;
                }

                // Generate all possible next combinations
                List<String> neighbors = getLockNeighbors(current);

                // Add valid and unvisited neighbors to the queue
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor) && !deadendSet.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            // Increment turn count after processing all combinations at current level
            step++;
        }

        // If we exhaust all possibilities without finding target, it's impossible
        return -1;
    }

    /**
     * Solves the lock problem using bidirectional BFS for optimization.
     *
     * VISUALIZATION OF BIDIRECTIONAL BFS FOR LOCK:
     *
     * Start state: "0000"
     * Target state: "0202"
     *
     * Step 0:
     *   - startSet = {"0000"}
     *   - endSet = {"0202"}
     *
     * Step 1: Expand startSet (smaller set)
     *   - From "0000", generate 8 neighbors (turn each wheel once in each direction)
     *     {"1000", "9000", "0100", "0900", "0010", "0090", "0001", "0009"}
     *   - Check if any neighbor is in endSet (no)
     *   - startSet becomes these new neighbors (minus any deadends)
     *
     * Step 2: If endSet is now smaller, expand it instead
     *   - From "0202", generate neighbors:
     *     {"1202", "9202", "0302", "0102", "0212", "0292", "0203", "0201"}
     *   - Check if any neighbor is in startSet (no)
     *   - endSet becomes these new neighbors
     *
     * This continues, alternating between expanding the smaller set until
     * we find a state that exists in both sets, indicating a path has been found.
     *
     * @param deadends Combinations that will lock the lock
     * @param target Target combination
     * @return Minimum moves to reach target, or -1 if impossible
     */
    public int openLockBidirectional(String[] deadends, String target) {
        // Convert deadends array to a set for O(1) lookup
        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));

        // Handle edge cases
        if (deadendSet.contains("0000")) {
            return -1;
        }
        if (target.equals("0000")) {
            return 0;
        }

        // Initialize two sets for bidirectional search
        // Note: Using sets instead of queues allows for quick intersection checking
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        // Track visited states to avoid cycles
        Set<String> visited = new HashSet<>();

        // Initialize sets with starting points
        startSet.add("0000");
        endSet.add(target);

        // Add starting points to visited to avoid re-processing
        visited.add("0000");
        visited.add(target);

        // Track number of turns
        int step = 0;

        // Bidirectional BFS loop
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            // KEY OPTIMIZATION: Always expand the smaller set
            // This significantly reduces the search space
            if (startSet.size() > endSet.size()) {
                // Swap sets if endSet is smaller
                Set<String> temp = startSet;
                startSet = endSet;
                endSet = temp;
            }

            // Set to hold next level's states
            Set<String> nextSet = new HashSet<>();

            // Expand each state in the current level
            for (String current : startSet) {
                // Generate all possible next states (8 neighbors)
                List<String> neighbors = getLockNeighbors(current);

                // Process each neighbor
                for (String neighbor : neighbors) {
                    // If the other set contains this neighbor, we've found a path!
                    if (endSet.contains(neighbor)) {
                        // Add 1 because we're making one more turn
                        return step + 1;
                    }

                    // Skip deadends and already visited states
                    if (deadendSet.contains(neighbor) || visited.contains(neighbor)) {
                        continue;
                    }

                    // Add to next level and mark as visited
                    nextSet.add(neighbor);
                    visited.add(neighbor);
                }
            }

            // Update for next iteration
            startSet = nextSet;
            step++;
        }

        // If sets become empty without finding intersection, no solution exists
        return -1;
    }

    /**
     * Gets all possible next combinations for the lock by turning each wheel once.
     *
     * VISUALIZATION OF WHEEL TURNING:
     *
     * For lock state "3721":
     *
     * Turn 1st wheel:
     *   Forward:  "4721" (3+1=4)
     *   Backward: "2721" (3-1=2)
     *
     * Turn 2nd wheel:
     *   Forward:  "3821" (7+1=8)
     *   Backward: "3621" (7-1=6)
     *
     * Turn 3rd wheel:
     *   Forward:  "3731" (2+1=3)
     *   Backward: "3711" (2-1=1)
     *
     * Turn 4th wheel:
     *   Forward:  "3722" (1+1=2)
     *   Backward: "3720" (1-1=0)
     *
     * Special cases for wraparound:
     * - "9" forward becomes "0"
     * - "0" backward becomes "9"
     *
     * @param combination Current lock combination (e.g., "0000")
     * @return List of all possible next combinations (8 neighbors)
     */
    private List<String> getLockNeighbors(String combination) {
        List<String> neighbors = new ArrayList<>();

        // For each of the 4 wheels
        for (int i = 0; i < 4; i++) {
            // Turn wheel forward (+1)
            neighbors.add(plusOne(combination, i));

            // Turn wheel backward (-1)
            neighbors.add(minusOne(combination, i));
        }

        return neighbors;
    }

    /**
     * Turns a wheel forward (increment by 1).
     *
     * Examples:
     * plusOne("0000", 0) => "1000" (first wheel 0→1)
     * plusOne("0000", 1) => "0100" (second wheel 0→1)
     * plusOne("9999", 0) => "0999" (first wheel 9→0, wrapping around)
     *
     * @param s Current combination
     * @param position Position to turn (0-3, left to right)
     * @return New combination after turning
     */
    private String plusOne(String s, int position) {
        char[] chars = s.toCharArray();

        // Handle wraparound: 9 becomes 0
        if (chars[position] == '9') {
            chars[position] = '0';
        } else {
            // Normal case: increment by 1
            chars[position]++;
        }

        return new String(chars);
    }

    /**
     * Turns a wheel backward (decrement by 1).
     *
     * Examples:
     * minusOne("0000", 0) => "9000" (first wheel 0→9, wrapping around)
     * minusOne("0000", 1) => "0900" (second wheel 0→9, wrapping around)
     * minusOne("1111", 0) => "0111" (first wheel 1→0)
     *
     * @param s Current combination
     * @param position Position to turn (0-3, left to right)
     * @return New combination after turning
     */
    private String minusOne(String s, int position) {
        char[] chars = s.toCharArray();

        // Handle wraparound: 0 becomes 9
        if (chars[position] == '0') {
            chars[position] = '9';
        } else {
            // Normal case: decrement by 1
            chars[position]--;
        }

        return new String(chars);
    }
}
