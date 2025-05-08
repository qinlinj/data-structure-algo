package org.qinlinj.algoframework._500_data_structure_design._560_application_design._565_median_finder_with_heaps; /**
 * ANALYSIS OF AN INCORRECT APPROACH TO THE MEDIAN FINDER PROBLEM
 * <p>
 * This class demonstrates a seemingly logical but incorrect implementation of the MedianFinder.
 * <p>
 * The Incorrect Approach:
 * The intuitive approach might be to simply add elements to either heap based on their relative
 * sizes, without any additional balancing mechanism:
 * - If small has more or equal elements than large, add to large
 * - Otherwise, add to small
 * <p>
 * Problem with this approach:
 * While this maintains the size balance between heaps, it fails to maintain the crucial property
 * that every element in large must be greater than or equal to every element in small.
 * <p>
 * Example of Failure:
 * 1. Add 1: It goes to large (since both heaps are empty)
 * 2. Add 2: It goes to small (since large has more elements)
 * 3. Add 3: It goes to large (since both heaps have equal elements)
 * <p>
 * At this point:
 * - large contains [1, 3]
 * - small contains [2]
 * <p>
 * When we call findMedian(), we expect 2, but the actual computation would give (1 + 2) / 2 = 1.5
 * This is incorrect because large's smallest element (1) is smaller than small's largest element (2)
 * <p>
 * The key insight is that we need to maintain both:
 * 1. Size balance
 * 2. Heap property (large's smallest ≥ small's largest)
 */

import java.util.*;

public class _565_d_IncorrectApproach {

    public static void main(String[] args) {
        // Demonstrating the incorrect implementation
        IncorrectMedianFinder medianFinder = new IncorrectMedianFinder();

        System.out.println("Using incorrect implementation:");

        // First element
        medianFinder.addNum(1);
        System.out.println("Added 1, median = " + medianFinder.findMedian());

        // Second element
        medianFinder.addNum(2);
        System.out.println("Added 2, median = " + medianFinder.findMedian());

        // Third element
        medianFinder.addNum(3);
        System.out.println("Added 3, expected median = 2.0, but got = " + medianFinder.findMedian());

        // Showing the state of the heaps
        System.out.println("\nAfter adding [1,2,3], our heaps are not properly organized:");
        System.out.println("large (should contain larger elements): has 1 as its minimum");
        System.out.println("small (should contain smaller elements): has 2 as its maximum");

        System.out.println("\nThis violates our required heap property!");
        System.out.println("The correct implementation would ensure that small's maximum <= large's minimum");
    }

    public static class IncorrectMedianFinder {
        // Min-heap for the larger half of numbers
        private PriorityQueue<Integer> large;

        // Max-heap for the smaller half of numbers
        private PriorityQueue<Integer> small;

        public IncorrectMedianFinder() {
            // Min heap for large elements
            large = new PriorityQueue<>();

            // Max heap for small elements
            small = new PriorityQueue<>((a, b) -> b - a);
        }

        /**
         * This is an INCORRECT implementation that only balances heap sizes
         * but fails to maintain the heap property
         */
        public void addNum(int num) {
            // This approach only maintains size balance but not the heap property
            if (small.size() >= large.size()) {
                large.offer(num);
            } else {
                small.offer(num);
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