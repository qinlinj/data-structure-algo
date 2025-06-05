package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._873_interval_scheduling;

/**
 * GREEDY ALGORITHM INTRODUCTION AND CORE CONCEPTS
 * <p>
 * Key Concepts:
 * 1. Greedy Algorithm Definition: A special case of dynamic programming
 * 2. Greedy Choice Property: Making locally optimal choices leads to globally optimal solution
 * 3. Efficiency Hierarchy: Exponential → Polynomial (DP) → Linear (Greedy)
 * 4. Applicability: Only certain problems possess the greedy choice property
 * <p>
 * Core Principle:
 * At each step, make the locally optimal choice without considering future consequences.
 * The key insight is that for problems with greedy choice property, local optimum
 * automatically leads to global optimum.
 * <p>
 * Examples of Greedy vs Non-Greedy Problems:
 * - Greedy: Selecting maximum denomination bills (100 yuan > 50 yuan > 20 yuan)
 * - Non-Greedy: Playing cards in games like Dou Di Zhu (might use powerful cards early)
 * <p>
 * Algorithm Complexity Evolution:
 * - Brute Force: O(2^n) - try all combinations
 * - Dynamic Programming: O(n²) or O(n³) - eliminate overlapping subproblems
 * - Greedy Algorithm: O(n log n) or O(n) - make optimal local choices
 * <p>
 * Greedy Algorithm Requirements:
 * 1. Optimal Substructure: Problem can be broken into optimal subproblems
 * 2. Greedy Choice Property: Local optimal choice leads to global optimum
 * 3. No dependency on future choices when making current decision
 * <p>
 * This class demonstrates the fundamental concepts through concrete examples
 * and comparisons with other algorithmic approaches.
 */

public class _873_a_GreedyAlgorithmIntroduction {
}
