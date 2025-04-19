package org.qinlinj.algoframework._100_core_framework._170_bfs_framework._172_sliding_puzzle;

/**
 * LeetCode 773: Sliding Puzzle Solution
 * <p>
 * Problem Description:
 * You are given a 2x3 board with tiles numbered 0-5, where 0 represents an empty space.
 * You can move any tile adjacent to the empty space into the empty space.
 * The goal is to reach the target state [[1,2,3],[4,5,0]] in the minimum number of moves.
 * If it's impossible to reach the target, return -1.
 * <p>
 * Key Concepts:
 * 1. Problem Abstraction:
 * - Initial board state can be considered as the starting node in a graph
 * - Target board state is the destination node
 * - Each valid move creates a new board state (a neighboring node)
 * - We need to find the shortest path from start to target
 * <p>
 * 2. BFS Application:
 * - BFS is ideal for finding the shortest path in unweighted graphs
 * - Each board state is a node in our graph
 * - Neighbors are states reachable with one valid move
 * - We track visited states to avoid cycles
 * <p>
 * 3. Implementation Challenges:
 * - Representing the 2D board as a hashable type for the visited set
 * - Efficiently finding valid neighboring states
 * - Handling the conversion between board representation formats
 * <p>
 * This class provides a complete solution using BFS with detailed comments.
 */
public class SlidingPuzzleSolution {
}
