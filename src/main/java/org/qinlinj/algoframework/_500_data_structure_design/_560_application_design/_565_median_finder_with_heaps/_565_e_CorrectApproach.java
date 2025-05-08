package org.qinlinj.algoframework._500_data_structure_design._560_application_design._565_median_finder_with_heaps; /**
 * DETAILED EXPLANATION OF THE CORRECT APPROACH
 * <p>
 * This class provides a detailed explanation of why the correct two-heap approach works
 * and how it maintains both of our critical invariants:
 * 1. Size balance between heaps (difference of at most 1)
 * 2. Heap property (every element in large ≥ every element in small)
 * <p>
 * Key Technique:
 * The correct approach uses an indirect insertion strategy:
 * - To add to large: First add to small, then move small's max to large
 * - To add to small: First add to large, then move large's min to small
 * <p>
 * This ensures that elements are correctly "filtered" through the opposing heap
 * before being placed in their final heap, maintaining our heap property.
 * <p>
 * For every element being added:
 * - If we want it in large, we first offer it to small, then transfer small's maximum to large
 * - If we want it in small, we first offer it to large, then transfer large's minimum to small
 * <p>
 * This crossover technique ensures that:
 * - small always contains the smaller half of the elements
 * - large always contains the larger half of the elements
 */

import java.util.*;

public class _565_e_CorrectApproach {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        System.out.println("Tracing the correct approach with detailed explanation:");

        // Adding 1
        System.out.println("\nAdding 1:");
        medianFinder.addNum(1);
        System.out.println("- small.size() == large.size() (both 0), so 1 first goes to small");
        System.out.println("- Then small's max (1) gets moved to large");
        System.out.println("- Now large = [1], small = []");
        System.out.println("- Median = 1.0");

        // Adding 2
        System.out.println("\nAdding 2:");
        medianFinder.addNum(2);
        System.out.println("- large.size() > small.size() (1 vs 0), so 2 first goes to large");
        System.out.println("- Then large's min (1) gets moved to small");
        System.out.println("- Now large = [2], small = [1]");
        System.out.println("- Median = (1 + 2) / 2 = 1.5");

        // Adding 3
        System.out.println("\nAdding 3:");
        medianFinder.addNum(3);
        System.out.println("- small.size() == large.size() (both 1), so 3 first goes to small");
        System.out.println("- Then small's max (3) gets moved to large");
        System.out.println("- Now large = [2, 3], small = [1]");
        System.out.println("- Median = 2.0");

        System.out.println("\nCritical insight:");
        System.out.println("This approach ensures that every element goes through the 'filter' of");
        System.out.println("the opposite heap, which maintains our crucial heap property.");
        System.out.println("small always contains the smaller half of numbers and large always");
        System.out.println("contains the larger half of numbers.");
    }

    public static class MedianFinder {
        // Min-heap for the larger half of numbers
        private PriorityQueue<Integer> large;

        // Max-heap for the smaller half of numbers
        private PriorityQueue<Integer> small;

        public MedianFinder() {
            // Min heap for large elements
            large = new PriorityQueue<>();

            // Max heap for small elements
            small = new PriorityQueue<>((a, b) -> b - a);
        }

        /**
         * The correct implementation that maintains both size balance and heap property
         */
        public void addNum(int num) {
            // If small should get the new element (to maintain size balance)
            if (large.size() > small.size()) {
                // First add to large, then move large's smallest to small
                large.offer(num);
                small.offer(large.poll());
            }
            // If large should get the new element (to maintain size balance)
            else {
                // First add to small, then move small's largest to large
                small.offer(num);
                large.offer(small.poll());
            }
        }

        public double findMedian() {
            if (large.size() < small.size()) {
                return small.peek();
            } else if (large.size() > small.size()) {
                return large.peek();
            }
            return (large.peek() + small.peek()) / 2.0;
        }
    }
}