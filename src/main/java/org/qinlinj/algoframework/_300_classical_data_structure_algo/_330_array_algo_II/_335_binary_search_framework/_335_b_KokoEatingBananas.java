package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._335_binary_search_framework;

/**
 * Example 1: Koko Eating Bananas (LeetCode 875)
 * <p>
 * Problem:
 * - Koko loves eating bananas and wants to eat all N piles of bananas before guards return in H hours
 * - Each hour, Koko chooses one pile and eats K bananas from it
 * - If the pile has less than K bananas, she eats all of them and won't eat any more bananas during this hour
 * - Find the minimum integer K such that Koko can eat all the bananas within H hours
 * <p>
 * This problem can be solved using binary search by:
 * - Variable x: Koko's eating speed (bananas per hour)
 * - Function f(x): The hours needed to eat all bananas at speed x
 * - Target: The given H hours
 * - Goal: Find the minimum speed x where f(x) <= H
 */
public class _335_b_KokoEatingBananas {
}
