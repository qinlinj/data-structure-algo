package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_h_KthLargestElementInStream
 * <p>
 * LeetCode #703: Kth Largest Element in a Stream
 * <p>
 * This class maintains a data structure that can efficiently find the
 * kth largest element in a stream of integers.
 * <p>
 * Approach:
 * 1. Use a min-heap (priority queue) of size k to track the k largest elements
 * 2. When initializing, add all initial elements to the heap
 * and maintain size k by removing smaller elements
 * 3. When adding a new element, insert it into the heap and remove the smallest
 * if heap size exceeds k
 * 4. The smallest element in the heap is the kth largest in the stream
 * <p>
 * Time Complexity:
 * - Constructor: O(n log k) where n is the initial array length
 * - add(): O(log k) per operation
 * <p>
 * Space Complexity: O(k) for the heap
 */

import java.util.*;

public class _531_h_KthLargestElementInStream {

    private final int k;
    private final PriorityQueue<Integer> minHeap;

    public _531_h_KthLargestElementInStream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();

        // Add all initial elements
        for (int num : nums) {
            add(num);
        }
    }

    public static void main(String[] args) {
        // Test case from the problem
        int k = 3;
        int[] nums = {4, 5, 8, 2};

        _531_h_KthLargestElementInStream kthLargest = new _531_h_KthLargestElementInStream(k, nums);

        System.out.println("Initial array: [4, 5, 8, 2], k = 3");

        System.out.println("Add 3: " + kthLargest.add(3));    // Output: 4
        System.out.println("Add 5: " + kthLargest.add(5));    // Output: 5
        System.out.println("Add 10: " + kthLargest.add(10));  // Output: 5
        System.out.println("Add 9: " + kthLargest.add(9));    // Output: 8
        System.out.println("Add 4: " + kthLargest.add(4));    // Output: 8

        // Explanation:
        // After initializing with [4, 5, 8, 2], the 3rd largest is 4
        // After adding 3, stream is [2, 3, 4, 5, 8], 3rd largest is 4
        // After adding 5, stream is [2, 3, 4, 5, 5, 8], 3rd largest is 5
        // After adding 10, stream is [2, 3, 4, 5, 5, 8, 10], 3rd largest is 5
        // After adding 9, stream is [2, 3, 4, 5, 5, 8, 9, 10], 3rd largest is 8
        // After adding 4, stream is [2, 3, 4, 4, 5, 5, 8, 9, 10], 3rd largest is 8
    }

    public int add(int val) {
        // Add the new element to the heap
        minHeap.offer(val);

        // If heap size exceeds k, remove the smallest element
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        // Return the kth largest element (top of the min heap)
        return minHeap.peek();
    }
}