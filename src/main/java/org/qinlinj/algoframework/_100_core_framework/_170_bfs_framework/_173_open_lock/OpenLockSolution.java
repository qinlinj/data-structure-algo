package org.qinlinj.algoframework._100_core_framework._170_bfs_framework._173_open_lock;

/**
 * LeetCode 752: Open the Lock
 * <p>
 * Problem Description:
 * You have a lock with 4 circular wheels, each with digits 0-9. The initial state is "0000".
 * Each wheel can be turned forward (+1) or backward (-1), with '9' wrapping to '0' and '0' wrapping to '9'.
 * Given a target string and a list of "deadends" (states that will lock the lock permanently),
 * find the minimum number of moves required to reach the target, or -1 if impossible.
 * <p>
 * Key Concepts:
 * 1. Graph Abstraction:
 * - Each possible lock combination is a node in the graph
 * - Adjacent nodes are combinations that differ by one wheel turn
 * - Each lock combination has 8 neighbors (4 wheels × 2 directions)
 * - We need to find the shortest path from "0000" to the target
 * <p>
 * 2. BFS Approach:
 * - Start BFS from "0000"
 * - For each combination, try turning each wheel in both directions
 * - Track visited combinations to avoid cycles
 * - Exclude deadend combinations
 * - Return the minimum number of turns when target is found
 * <p>
 * 3. Implementation Considerations:
 * - Handle special cases (e.g., starting combination is a deadend)
 * - Efficiently generate valid neighbor states
 * - Track visited states to avoid redundant exploration
 */
public class OpenLockSolution {
}
