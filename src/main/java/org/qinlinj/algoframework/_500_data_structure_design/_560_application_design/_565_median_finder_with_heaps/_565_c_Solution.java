package org.qinlinj.algoframework._500_data_structure_design._560_application_design._565_median_finder_with_heaps; /**
 * TWO-HEAP SOLUTION FOR FINDING MEDIAN IN A DATA STREAM
 * <p>
 * Key Insight:
 * This solution uses two priority queues (heaps) to maintain the data stream in a way that allows
 * efficient computation of the median.
 * <p>
 * Implementation Details:
 * 1. We use two heaps:
 * - small: A max-heap containing the smaller half of the numbers
 * - large: A min-heap containing the larger half of the numbers
 * <p>
 * 2. We maintain two invariants:
 * a. The size difference between the heaps is at most 1
 * b. Every element in large is greater than or equal to every element in small
 * <p>
 * 3. The median can then be computed in O(1) time:
 * - If the heaps have equal size, the median is the average of their top elements
 * - If one heap has more elements, the median is its top element
 * <p>
 * Time Complexity:
 * - addNum: O(log n) for heap operations
 * - findMedian: O(1) to access the heap tops
 * <p>
 * Space Complexity:
 * - O(n) to store all elements
 */

import java.util.*;

public class _565_c_Solution {

    /*
     * Visual representation of the two-heap approach:
     *                 /|\
     *                / | \      large (min-heap with larger elements)
     *               /  |  \
     *    --------------------
     *     \  |  /
     *      \ | /           small (max-heap with smaller elements)
     *       \|/
     */
    public static void main(String[] args) {

        // Demo of the MedianFinder class
        MedianFinder medianFinder = new MedianFinder();

        System.out.println("Adding elements and calculating median:");

        // First element
        medianFinder.addNum(1);
        System.out.println("Added 1, median = " + medianFinder.findMedian()); // Should be 1.0

        // Second element
        medianFinder.addNum(2);
        System.out.println("Added 2, median = " + medianFinder.findMedian()); // Should be 1.5

        // Third element
        medianFinder.addNum(3);
        System.out.println("Added 3, median = " + medianFinder.findMedian()); // Should be 2.0

        // Fourth element
        medianFinder.addNum(4);
        System.out.println("Added 4, median = " + medianFinder.findMedian()); // Should be 2.5

        // Fifth element
        medianFinder.addNum(5);
        System.out.println("Added 5, median = " + medianFinder.findMedian()); // Should be 3.0
    }

    public static class MedianFinder {
        // Min-heap for the larger half of numbers
        private PriorityQueue<Integer> large;

        // Max-heap for the smaller half of numbers
        private PriorityQueue<Integer> small;

        /**
         * Initialize your data structure here.
         */
        public MedianFinder() {
            // Min heap for large elements
            large = new PriorityQueue<>();

            // Max heap for small elements (using a comparator to reverse the order)
            small = new PriorityQueue<>((a, b) -> b - a);
        }

        /**
         * Adds a number into the data structure.
         * <p>
         * The implementation ensures our two invariants:
         * 1. Size difference between heaps is at most 1
         * 2. Every element in large is >= every element in small
         */
        public void addNum(int num) {
            // First technique: balance by count and maintain heap property
            if (small.size() >= large.size()) {
                // Add to small first, then move the largest element to large
                small.offer(num);
                large.offer(small.poll());
            } else {
                // Add to large first, then move the smallest element to small
                large.offer(num);
                small.offer(large.poll());
            }
        }

        /**
         * Returns the median of all elements so far.
         */
        public double findMedian() {
            // If the heaps have different sizes, the larger heap's top is the median
            if (small.size() > large.size()) {
                return small.peek();
            } else if (large.size() > small.size()) {
                return large.peek();
            }

            // If the heaps have the same size, the median is the average of their tops
            return (small.peek() + large.peek()) / 2.0;
        }
    }
}