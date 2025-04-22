package org.qinlinj.algoframework._200_core_framework._230_algo_complexity_analysis._231_non_recursive_algorithm_analysis;


/**
 * Algorithm Complexity Analysis Guide
 * <p>
 * This class summarizes key concepts for analyzing time and space complexity
 * of algorithms, including practical tips and common pitfalls.
 * <p>
 * Key Points:
 * <p>
 * 1. Using complexity analysis to infer solution approaches:
 * - Pay attention to input data size constraints in problems
 * - Determine allowed time complexity based on input size
 * - Use complexity constraints to narrow down potential algorithms
 * <p>
 * 2. Common coding mistakes leading to inefficient algorithms:
 * - Using unnecessary standard output during algorithm execution
 * - Passing by value instead of reference (especially in recursive calls)
 * - Misunderstanding underlying implementations of interface objects
 * <p>
 * 3. Big O Notation basics:
 * - Only keep the fastest-growing term (dropping constants and slower terms)
 * - Big O represents an upper bound on growth rate
 * - Sometimes a loose upper bound is acceptable when exact analysis is difficult
 * <p>
 * 4. Non-recursive algorithm analysis:
 * - For nested loops, typically multiply each loop's complexity
 * - Special patterns (like sliding window, non-retreating pointers) can be linear
 * - Key insight: Focus on what the algorithm actually does, not just its structure
 * <p>
 * public class NonRecursiveAlgorithmComplexityAnalysis {
 * }
