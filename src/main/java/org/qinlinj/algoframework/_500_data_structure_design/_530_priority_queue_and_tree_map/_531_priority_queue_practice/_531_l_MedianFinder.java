package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_l_MedianFinder
 * <p>
 * LeetCode #295: Find Median from Data Stream
 * <p>
 * This class efficiently finds the median of a data stream using two heaps:
 * - A max heap for the smaller half of the numbers
 * - A min heap for the larger half of the numbers
 * <p>
 * By maintaining these two heaps with balanced size (or the max heap having at most
 * one more element), we can quickly find the median:
 * - If both heaps have the same size, the median is the average of their top elements
 * - If the max heap has one more element, the median is its top element
 * <p>
 * Approach:
 * 1. Use two heaps: maxHeap for smaller half, minHeap for larger half
 * 2. Balance the heaps after each insertion to maintain their size property
 * 3. For even number of elements, median is average of both heap tops
 * 4. For odd number of elements, median is top of the max heap
 * <p>
 * Time Complexity:
 * - addNum: O(log n)
 * - findMedian: O(1)
 * <p>
 * Space Complexity: O(n) for storing all elements
 */

import java.util.*;

public class _531_l_MedianFinder {
    // Max heap for the smaller half of numbers
    private final PriorityQueue<Integer> maxHeap;

    // Min heap for the larger half of numbers
    private final PriorityQueue<Integer> minHeap;

    public _531_l_MedianFinder() {
        // Initialize heaps
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max heap
        minHeap = new PriorityQueue<>(); // Min heap
    }

    public static void main(String[] args) {
        _531_l_MedianFinder medianFinder = new _531_l_MedianFinder();

        System.out.println("Adding numbers and finding median:");

        medianFinder.addNum(1);
        System.out.println("After adding 1, median = " + medianFinder.findMedian());  // Output: 1.0

        medianFinder.addNum(2);
        System.out.println("After adding 2, median = " + medianFinder.findMedian());  // Output: 1.5

        medianFinder.addNum(3);
        System.out.println("After adding 3, median = " + medianFinder.findMedian());  // Output: 2.0

        medianFinder.addNum(4);
        System.out.println("After adding 4, median = " + medianFinder.findMedian());  // Output: 2.5

        medianFinder.addNum(5);
        System.out.println("After adding 5, median = " + medianFinder.findMedian());  // Output: 3.0

        System.out.println("\nStructure of the heaps:");
        System.out.println("Max heap (smaller half): " + medianFinder.maxHeap);
        System.out.println("Min heap (larger half): " + medianFinder.minHeap);
    }

    /**
     * Adds a number to the data structure
     * @param num the number to add
     */
    public void addNum(int num) {
        // Add the number to the appropriate heap
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // Balance the heaps
        // The max heap can have at most one more element than min heap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Finds the median of all elements added so far
     * @return the median value
     */
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // Even number of elements, average the two middle values
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            // Odd number of elements, return the middle value
            // (max heap always has the extra element)
            return maxHeap.peek();
        }
    }

    /**
     * Alternative implementation of addNum that always adds to maxHeap first
     * and then rebalances
     */
    public void addNumAlternative(int num) {
        // Always add to maxHeap first
        maxHeap.offer(num);

        // Ensure the largest element in maxHeap is smaller than the smallest in minHeap
        minHeap.offer(maxHeap.poll());

        // Ensure maxHeap has at least as many elements as minHeap
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
}