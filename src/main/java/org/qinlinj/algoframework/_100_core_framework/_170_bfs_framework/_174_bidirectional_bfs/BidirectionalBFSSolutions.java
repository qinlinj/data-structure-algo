package org.qinlinj.algoframework._100_core_framework._170_bfs_framework._174_bidirectional_bfs;

/**
 * Bidirectional BFS Solutions for Classic Puzzles
 * <p>
 * This class implements solutions to two classic puzzle problems using both standard BFS
 * and optimized bidirectional BFS approaches:
 * <p>
 * 1. LeetCode 773: Sliding Puzzle - A 2x3 sliding puzzle where we need to find the minimum
 * number of moves to reach the target state.
 * <p>
 * 2. LeetCode 752: Open the Lock - A 4-wheel combination lock where we need to find the
 * minimum number of moves to reach the target combination while avoiding deadends.
 * <p>
 * Key Concept: Bidirectional BFS Optimization
 * <p>
 * Traditional BFS starts from the beginning and expands outward until reaching the target.
 * Bidirectional BFS starts from both the beginning and end simultaneously, expanding both
 * frontiers until they meet. This significantly reduces the search space.
 * <p>
 * The optimization is especially effective because:
 * - If a graph has branching factor b and solution depth d, traditional BFS explores O(b^d) nodes
 * - Bidirectional BFS explores approximately O(2*b^(d/2)) nodes, which is much smaller for large b and d
 * <p>
 * Limitations: Bidirectional BFS can only be used when both the start and end states are known.
 */
public class BidirectionalBFSSolutions {
    /**
     * ===== SOLUTION FOR SLIDING PUZZLE (LEETCODE 773) =====
     *
     * In the sliding puzzle problem:
     * - We have a 2x3 board with numbers 0-5
     * - 0 represents the empty space that can be swapped with adjacent numbers
     * - We need to find the minimum number of moves to reach [[1,2,3],[4,5,0]]
     */

}
