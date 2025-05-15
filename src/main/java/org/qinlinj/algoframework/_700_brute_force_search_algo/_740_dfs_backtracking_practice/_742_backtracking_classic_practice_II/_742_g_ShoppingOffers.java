package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._742_backtracking_classic_practice_II;

import java.util.*;

/**
 * 638. Shopping Offers
 * <p>
 * Problem Summary:
 * In LeetCode store, there are n items to sell, each with a price. There are also special offers,
 * each represented as an array where the last element is the price of the offer and the rest elements
 * indicate how many specific items are included. Given prices, special offers, and needs, find the
 * lowest price to buy exactly what you need.
 * <p>
 * Key Insights:
 * - This is a combination problem where elements can be used multiple times ("elements without repetition but can be reselected")
 * - We can use backtracking to try different combinations of special offers
 * - Key optimizations:
 * 1. Filter out offers that are not actually discounted (price of offer > sum of individual prices)
 * 2. Use a greedy approach: prefer special offers whenever possible
 * 3. When no more special offers can be used, buy remaining items individually
 * <p>
 * Time Complexity: O(m^n) where m is the number of special offers and n is the maximum quantity needed
 * Space Complexity: O(n) for recursion stack
 */
class _742_g_ShoppingOffers {

    List<Integer> price;
    List<List<Integer>> specials;

    // Track current cost during backtracking
    int trackCost = 0;
    // Track minimum cost found
    int minCost = Integer.MAX_VALUE;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> specials, List<Integer> needs) {
        // Filter out offers that are not actually discounted
        List<List<Integer>> newSpecials = new ArrayList<>();
        for (int i = 0; i < specials.size(); i++) {
            List<Integer> special = specials.get(i);
            int cost = 0;
            for (int j = 0; j < special.size() - 1; j++) {
                cost += special.get(j) * price.get(j);
            }
            if (cost > special.get(special.size() - 1)) {
                newSpecials.add(special);
            }
        }

        this.price = price;
        this.specials = newSpecials;

        backtrack(needs, 0);
        return minCost;
    }

    // Core backtracking framework
    // Logic similar to "combination with elements that can be reselected" problem
    void backtrack(List<Integer> needs, int start) {
        if (trackCost >= minCost) {
            // Pruning: no need to continue if current cost exceeds minimum found
            return;
        }

        boolean haveUsedSpecial = false;

        // Combination problem needs to start from 'start' index
        for (int i = start; i < specials.size(); i++) {
            List<Integer> targetSpecial = specials.get(i);
            if (!canUseSpecial(targetSpecial, needs)) {
                continue;
            }

            haveUsedSpecial = true;

            // Make choice: buy this special offer
            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) - targetSpecial.get(j));
            }
            trackCost += targetSpecial.get(targetSpecial.size() - 1);

            // Since each special can be used multiple times, continue from the same index
            backtrack(needs, i);

            // Undo choice
            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) + targetSpecial.get(j));
            }
            trackCost -= targetSpecial.get(targetSpecial.size() - 1);
        }

        if (!haveUsedSpecial) {
            // Cannot use special offers anymore, buy remaining items individually
            int sum = 0;
            for (int i = 0; i < needs.size(); i++) {
                sum += needs.get(i) * price.get(i);
            }
            // Update minimum cost
            minCost = Math.min(minCost, sum + trackCost);
        }
    }

    // Helper function to check if a special offer can be used
    boolean canUseSpecial(List<Integer> special, List<Integer> need) {
        for (int i = 0; i < need.size(); i++) {
            if (need.get(i) < special.get(i)) {
                return false;
            }
        }
        return true;
    }
}