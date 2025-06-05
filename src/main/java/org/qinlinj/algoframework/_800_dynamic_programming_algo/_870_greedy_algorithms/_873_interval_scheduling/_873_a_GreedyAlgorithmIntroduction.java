package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._873_interval_scheduling; /**
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

import java.util.*;

public class _873_a_GreedyAlgorithmIntroduction {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 GREEDY ALGORITHM INTRODUCTION               ║");
        System.out.println("║             Concepts, Examples, and Applications            ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Demonstrate greedy working example
        int[] denominations = {100, 50, 20, 10};
        BillSelectionExample.selectBillsGreedy(denominations, 10);
        BillSelectionExample.demonstrateGreedyProperty();

        // Demonstrate greedy failing example
        List<CardGameExample.Card> hand = Arrays.asList(
                new CardGameExample.Card(4, "Pair"),
                new CardGameExample.Card(7, "Pair"),
                new CardGameExample.Card(15, "Joker")
        );
        CardGameExample.Card opponentCard = new CardGameExample.Card(3, "Pair");

        CardGameExample.playCardGreedy(hand, opponentCard);
        CardGameExample.playCardStrategic(hand, opponentCard, true);
        CardGameExample.demonstrateWhyGreedyFails();

        // Analyze complexity hierarchy
        ComplexityAnalysis.demonstrateComplexityHierarchy();

        // Provide identification guidelines
        GreedyApplicability.greedyIdentificationChecklist();
        GreedyApplicability.commonGreedyPatterns();

        System.out.println("\n=== Key Takeaways ===");
        System.out.println("1. Greedy algorithms are a special case of dynamic programming");
        System.out.println("2. They require the greedy choice property to work correctly");
        System.out.println("3. When applicable, they provide significant efficiency improvements");
        System.out.println("4. Not all problems have the greedy choice property");
        System.out.println("5. Always verify that local optimal choices lead to global optimum");
        System.out.println();
        System.out.println("Next: We'll explore interval scheduling as a classic greedy problem!");
    }

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

        /**
         * Demonstrate why greedy works for this problem
         */
        public static void demonstrateGreedyProperty() {
            System.out.println("\n=== Why Greedy Works for Bill Selection ===");
            System.out.println("Greedy Choice Property Analysis:");
            System.out.println("1. Local optimal choice: Always select highest denomination available");
            System.out.println("2. Why it leads to global optimum:");
            System.out.println("   - Each bill slot should be filled with maximum possible value");
            System.out.println("   - No future choice can make a previously selected bill 'wrong'");
            System.out.println("   - 100 yuan is always better than 50 yuan, regardless of future choices");
            System.out.println("3. Mathematical proof: max(100×a + 50×b) where a+b=10 → maximize a");
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

        /**
         * Strategic choice: Sometimes play powerful cards early
         */
        public static Card playCardStrategic(List<Card> hand, Card opponentCard,
                                             boolean opponentHasFewCards) {
            System.out.println("\n=== Card Game - Strategic Choice ===");
            System.out.println("Opponent played: " + opponentCard);
            System.out.println("Opponent has few cards left: " + opponentHasFewCards);

            if (opponentHasFewCards) {
                // Use powerful card to prevent opponent from winning
                Card strongestCard = hand.stream()
                        .filter(card -> card.value > opponentCard.value)
                        .max((a, b) -> Integer.compare(a.value, b.value))
                        .orElse(null);

                System.out.println("Strategic choice: Use powerful card " + strongestCard);
                return strongestCard;
            } else {
                return playCardGreedy(hand, opponentCard);
            }
        }

        /**
         * Demonstrate why greedy fails in card games
         */
        public static void demonstrateWhyGreedyFails() {
            System.out.println("\n=== Why Greedy Fails in Card Games ===");
            System.out.println("Reasons greedy doesn't work:");
            System.out.println("1. Future game state matters for current decisions");
            System.out.println("2. Opponent's remaining cards affect optimal strategy");
            System.out.println("3. Sometimes 'suboptimal' current moves lead to better final outcomes");
            System.out.println("4. Game theory involves predicting and countering opponent moves");
            System.out.println();
            System.out.println("This requires Dynamic Programming or Game Theory approaches!");
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

    /**
     * Complexity analysis and comparison
     */
    public static class ComplexityAnalysis {

        /**
         * Demonstrate complexity hierarchy with concrete examples
         */
        public static void demonstrateComplexityHierarchy() {
            System.out.println("\n=== Algorithm Complexity Hierarchy ===");
            System.out.println();

            System.out.println("1. EXPONENTIAL TIME - Brute Force O(2^n):");
            System.out.println("   Example: Try all possible subsets of items");
            System.out.println("   Problem: 30 items → 2^30 = 1 billion combinations");
            System.out.println("   Use case: When no better algorithm is known");
            System.out.println();

            System.out.println("2. POLYNOMIAL TIME - Dynamic Programming O(n²) or O(n³):");
            System.out.println("   Example: Solve subproblems and combine results");
            System.out.println("   Problem: 1000 items → 1,000,000 operations");
            System.out.println("   Improvement: Eliminate overlapping subproblems");
            System.out.println();

            System.out.println("3. LINEAR TIME - Greedy Algorithm O(n) or O(n log n):");
            System.out.println("   Example: Make optimal choice at each step");
            System.out.println("   Problem: 1000 items → 1,000 operations");
            System.out.println("   Requirement: Problem must have greedy choice property");
            System.out.println();

            // Demonstrate with concrete timing
            demonstrateTimingComparison();
        }

        /**
         * Show actual timing differences
         */
        private static void demonstrateTimingComparison() {
            System.out.println("=== Timing Comparison Example ===");
            System.out.println("Problem size: n = 20");
            System.out.println();

            int n = 20;

            // Simulate exponential time
            long exponentialOps = 1L << n; // 2^n
            System.out.printf("Exponential O(2^n): %,d operations%n", exponentialOps);

            // Simulate polynomial time
            long polynomialOps = (long) n * n; // n^2
            System.out.printf("Polynomial O(n²): %,d operations%n", polynomialOps);

            // Simulate linear time
            long linearOps = n; // n
            System.out.printf("Linear O(n): %,d operations%n", linearOps);

            System.out.println();
            System.out.printf("Speedup from exponential to polynomial: %.1fx%n",
                    (double) exponentialOps / polynomialOps);
            System.out.printf("Speedup from polynomial to linear: %.1fx%n",
                    (double) polynomialOps / linearOps);
            System.out.printf("Total speedup: %.1fx%n",
                    (double) exponentialOps / linearOps);
        }
    }

    /**
     * Identifying when to use greedy algorithms
     */
    public static class GreedyApplicability {

        /**
         * Checklist for identifying greedy problems
         */
        public static void greedyIdentificationChecklist() {
            System.out.println("\n=== Greedy Algorithm Identification Checklist ===");
            System.out.println();

            System.out.println("✓ INDICATORS THAT GREEDY MIGHT WORK:");
            System.out.println("1. Optimization problem (maximize or minimize something)");
            System.out.println("2. Can make a series of choices");
            System.out.println("3. Each choice doesn't depend on future unknown information");
            System.out.println("4. Local optimal choice seems 'obviously' correct");
            System.out.println("5. Problem has natural ordering or priority");
            System.out.println();

            System.out.println("✗ INDICATORS THAT GREEDY WON'T WORK:");
            System.out.println("1. Current choice affects future options significantly");
            System.out.println("2. Need to consider multiple future scenarios");
            System.out.println("3. Optimal solution requires 'looking ahead'");
            System.out.println("4. Problem involves game theory or adversarial scenarios");
            System.out.println("5. Multiple conflicting optimization criteria");
            System.out.println();

            System.out.println("🧪 VERIFICATION METHODS:");
            System.out.println("1. Try small examples by hand");
            System.out.println("2. Attempt to construct counterexamples");
            System.out.println("3. Prove greedy choice property mathematically");
            System.out.println("4. Compare with brute force on small inputs");
        }

        /**
         * Common greedy algorithm patterns
         */
        public static void commonGreedyPatterns() {
            System.out.println("\n=== Common Greedy Algorithm Patterns ===");
            System.out.println();

            System.out.println("1. SCHEDULING PROBLEMS:");
            System.out.println("   Pattern: Sort by end time, select non-overlapping intervals");
            System.out.println("   Example: Activity selection, meeting room scheduling");
            System.out.println();

            System.out.println("2. RESOURCE ALLOCATION:");
            System.out.println("   Pattern: Sort by efficiency ratio, allocate greedily");
            System.out.println("   Example: Fractional knapsack, job assignment");
            System.out.println();

            System.out.println("3. GRAPH PROBLEMS:");
            System.out.println("   Pattern: Always choose minimum weight edge/vertex");
            System.out.println("   Example: Minimum spanning tree (Kruskal's, Prim's)");
            System.out.println();

            System.out.println("4. HUFFMAN CODING:");
            System.out.println("   Pattern: Always merge two least frequent nodes");
            System.out.println("   Example: Data compression, optimal prefix codes");
            System.out.println();

            System.out.println("5. COIN CHANGE (SPECIFIC SYSTEMS):");
            System.out.println("   Pattern: Always use largest denomination possible");
            System.out.println("   Example: US coin system, Euro coin system");
        }
    }
}