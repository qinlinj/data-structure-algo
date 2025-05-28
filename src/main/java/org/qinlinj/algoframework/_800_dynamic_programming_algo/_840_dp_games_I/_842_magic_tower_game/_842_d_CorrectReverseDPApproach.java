package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._842_magic_tower_game;

/**
 * CORRECT REVERSE DP APPROACH
 * <p>
 * Key Insights:
 * 1. Reverse thinking: Start from destination (bottom-right) and work backwards
 * 2. Correct DP definition: dp(i,j) = minimum health needed AT cell (i,j) to reach the end
 * 3. State transition: Choose path that requires minimum health at current cell
 * 4. Base case: At destination, need enough health to survive the final room
 * <p>
 * Why This Works:
 * - We know exactly what health is required at future states
 * - Can make optimal decisions based on complete information
 * - State transitions have clear logical foundation
 * <p>
 * State Transition Logic:
 * - res = min(dp(i+1,j), dp(i,j+1)) - grid[i][j]
 * - dp(i,j) = max(1, res) // Health must be at least 1
 */

public class _842_d_CorrectReverseDPApproach {
}
