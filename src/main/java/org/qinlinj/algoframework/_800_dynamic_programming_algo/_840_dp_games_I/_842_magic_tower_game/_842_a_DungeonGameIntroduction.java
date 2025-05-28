package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

/**
 * DUNGEON GAME PROBLEM INTRODUCTION
 * <p>
 * Key Concepts:
 * 1. Classic dungeon-style game where you need to rescue a princess
 * 2. Knight moves from top-left to bottom-right (only right or down moves)
 * 3. Rooms contain demons (negative values) or health potions (positive values)
 * 4. Goal: Find minimum initial health needed to complete the journey
 * 5. Health must always remain > 0 throughout the journey
 * <p>
 * Problem Analysis:
 * - This is NOT about finding maximum path sum
 * - Key insight: Minimize health loss, not maximize health gain
 * - Requires dynamic programming with reverse thinking
 * <p>
 * Example:
 * Grid: [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Answer: 7 (following path: right -> right -> down -> down)
 */
public class _842_a_DungeonGameIntroduction {
}
