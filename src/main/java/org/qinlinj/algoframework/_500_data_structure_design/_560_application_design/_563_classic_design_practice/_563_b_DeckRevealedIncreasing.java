package org.qinlinj.algoframework._500_data_structure_design._560_application_design._563_classic_design_practice; /**
 * LeetCode Problem 950: Reveal Cards In Increasing Order
 * <p>
 * This problem involves figuring out the initial deck order that will result in
 * cards being revealed in increasing order according to a specific reveal process.
 * <p>
 * Problem Description:
 * - You have a deck of cards, each with a unique integer value
 * - You want to arrange them in an initial order such that when revealed according to a specific process:
 * 1. Reveal the top card of the deck
 * 2. Take the next card and put it at the bottom of the deck
 * 3. Repeat until all cards are revealed
 * - The revealed cards must be in increasing order of their values
 * - Return the deck's initial ordering
 * <p>
 * Key Insight:
 * - We can solve this by simulating the reveal process in reverse:
 * 1. Sort the cards in descending order
 * 2. Insert each card into a result deck using the reverse of the reveal process
 * 3. For each card, if the result deck is not empty, move the bottom card to the top,
 * then add the current card to the top
 * <p>
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(n) for the result deck
 */

import java.util.*;

public class _563_b_DeckRevealedIncreasing {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        int[] deck1 = {17, 13, 11, 2, 3, 5, 7};
        int[] result1 = solution.deckRevealedIncreasing(deck1);
        System.out.println("Input: " + Arrays.toString(deck1));
        System.out.println("Output: " + Arrays.toString(result1));

        // Visual demonstration of the reveal process
        System.out.println("\nDemonstration of reveal process:");
        demonstrateRevealProcess(result1);
    }

    /**
     * Demonstrates the reveal process to show that the solution is correct
     */
    private static void demonstrateRevealProcess(int[] deck) {
        LinkedList<Integer> cards = new LinkedList<>();
        for (int card : deck) {
            cards.add(card);
        }

        LinkedList<Integer> revealed = new LinkedList<>();

        System.out.println("Initial deck: " + cards);

        while (!cards.isEmpty()) {
            // Reveal top card
            revealed.add(cards.removeFirst());
            System.out.println("Revealed: " + revealed.getLast());

            // Move next card to the bottom (if there are any cards left)
            if (!cards.isEmpty()) {
                cards.addLast(cards.removeFirst());
                System.out.println("Moved next card to bottom, deck is now: " + cards);
            }
        }

        System.out.println("Final revealed sequence: " + revealed);
    }

    static class Solution {
        public int[] deckRevealedIncreasing(int[] deck) {
            int n = deck.length;

            // Sort the deck in ascending order
            Arrays.sort(deck);

            // Linked list to build the result (head is top of deck, tail is bottom)
            LinkedList<Integer> result = new LinkedList<>();

            // Process the sorted deck in reverse (from largest to smallest)
            for (int i = n - 1; i >= 0; i--) {
                if (!result.isEmpty()) {
                    // Move the bottom card to the top
                    result.addFirst(result.removeLast());
                }
                // Add the current card to the top
                result.addFirst(deck[i]);
            }

            // Convert the result LinkedList back to an array
            int[] resultArray = new int[n];
            for (int i = 0; i < n; i++) {
                resultArray[i] = result.get(i);
            }

            return resultArray;
        }
    }
}