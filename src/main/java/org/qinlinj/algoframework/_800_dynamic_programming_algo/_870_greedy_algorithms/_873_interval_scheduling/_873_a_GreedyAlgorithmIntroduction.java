package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._873_interval_scheduling;

import java.util.*;

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
    /**
     * Example 1: Greedy works - Selecting maximum value bills
     * Problem: You have 100 yuan bills and 50 yuan bills, select 10 bills for maximum value
     */
    public static class BillSelectionExample {

        /**
         * Greedy solution: Always pick the highest denomination
         */
        public static int selectBillsGreedy(int[] denominations, int numBills) {
            // Sort denominations in descending order
            Arrays.sort(denominations);
            // Reverse to get descending order
            for (int i = 0; i < denominations.length / 2; i++) {
                int temp = denominations[i];
                denominations[i] = denominations[denominations.length - 1 - i];
                denominations[denominations.length - 1 - i] = temp;
            }

            int totalValue = 0;
            int billsSelected = 0;

            System.out.println("=== Greedy Bill Selection ===");
            System.out.println("Available denominations: " + Arrays.toString(denominations));
            System.out.println("Number of bills to select: " + numBills);
            System.out.println();

            for (int denomination : denominations) {
                while (billsSelected < numBills) {
                    totalValue += denomination;
                    billsSelected++;
                    System.out.println("Selected: " + denomination + " yuan, Total: " +
                            totalValue + " yuan, Bills used: " + billsSelected);

                    if (billsSelected >= numBills) break;
                }
                if (billsSelected >= numBills) break;
            }

            return totalValue;
        }
    }

    /**
     * Example 2: Greedy doesn't work - Card game strategy
     * Problem: In Dou Di Zhu, opponent plays pair of 3s, what should you play?
     */
    public static class CardGameExample {

        /**
         * Greedy strategy: Play smallest card that beats opponent
         */
        public static Card playCardGreedy(List<Card> hand, Card opponentCard) {
            System.out.println("=== Card Game - Greedy Strategy ===");
            System.out.println("Opponent played: " + opponentCard);
            System.out.println("Your hand: " + hand);

            // Find smallest card that beats opponent
            Card greedyChoice = null;
            for (Card card : hand) {
                if (card.value > opponentCard.value) {
                    if (greedyChoice == null || card.value < greedyChoice.value) {
                        greedyChoice = card;
                    }
                }
            }

            System.out.println("Greedy choice: " + greedyChoice);
            return greedyChoice;
        }

        static class Card {
            int value;
            String type;

            Card(int value, String type) {
                this.value = value;
                this.type = type;
            }

            @Override
            public String toString() {
                return type + "(" + value + ")";
            }
        }

    }
}
