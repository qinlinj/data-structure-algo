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

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _335_b_KokoEatingBananas solution = new _335_b_KokoEatingBananas();

        // Example 1
        int[] piles1 = {3, 6, 7, 11};
        int H1 = 8;
        System.out.println("Example 1: " + solution.minEatingSpeed(piles1, H1)); // Expected: 4

        // Example 2
        int[] piles2 = {30, 11, 23, 4, 20};
        int H2 = 5;
        System.out.println("Example 2: " + solution.minEatingSpeed(piles2, H2)); // Expected: 30

        // Example 3
        int[] piles3 = {30, 11, 23, 4, 20};
        int H3 = 6;
        System.out.println("Example 3: " + solution.minEatingSpeed(piles3, H3)); // Expected: 23
    }

    /**
     * Main function to solve the problem
     *
     * @param piles Array of banana piles
     * @param H     Hours before guards return
     * @return Minimum eating speed required
     */
    public int minEatingSpeed(int[] piles, int H) {
        // Initialize search boundaries
        int left = 1; // Minimum possible speed is 1 banana per hour
        int right = 1_000_000_000 + 1; // Based on the constraints (10^9)

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (hoursNeeded(piles, mid) <= H) {
                // If we can eat all bananas within H hours at speed mid,
                // try to find a slower speed (search left)
                right = mid;
            } else {
                // Otherwise, we need a faster speed (search right)
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Calculates how many hours are needed to eat all bananas at speed x
     *
     * @param piles Array of banana piles
     * @param speed Eating speed (bananas per hour)
     * @return Hours needed to eat all bananas
     */
    private long hoursNeeded(int[] piles, int speed) {
        long hours = 0;
        for (int pile : piles) {
            // Divide pile by speed, rounding up
            hours += (pile + speed - 1) / speed; // Equivalent to Math.ceil(pile / (double) speed)

            // Alternative calculation:
            // hours += pile / speed;
            // if (pile % speed > 0) {
            //     hours++;
            // }
        }
        return hours;
    }
}