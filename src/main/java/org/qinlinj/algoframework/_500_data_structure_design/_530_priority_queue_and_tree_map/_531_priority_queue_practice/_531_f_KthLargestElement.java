package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_f_KthLargestElement
 * <p>
 * LeetCode #215: Kth Largest Element in an Array
 * <p>
 * This solution finds the kth largest element in an unsorted array.
 * We use a min-heap (priority queue) of size k to efficiently track the k largest elements.
 * <p>
 * Approach:
 * 1. Create a min-heap (priority queue)
 * 2. Process each element in the array:
 * - Add the element to the heap
 * - If heap size exceeds k, remove the smallest element
 * 3. After processing all elements, the heap contains the k largest elements
 * with the kth largest at the top
 * <p>
 * Time Complexity: O(n log k) where n is the array length
 * Space Complexity: O(k) for the heap
 */

import java.util.*;

public class _531_f_KthLargestElement {

    public static void main(String[] args) {
        _531_f_KthLargestElement solution = new _531_f_KthLargestElement();

        // Test case 1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Array: " + arrayToString(nums1));
        System.out.println(k1 + "nd largest element: " + solution.findKthLargest(nums1, k1));
        // Expected output: 5

        // Test case 2
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println("\nTest Case 2:");
        System.out.println("Array: " + arrayToString(nums2));
        System.out.println(k2 + "th largest element: " + solution.findKthLargest(nums2, k2));
        // Expected output: 4
    }

    // Helper method to convert an array to string
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int findKthLargest(int[] nums, int k) {
        // Min heap to keep track of the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Process each element
        for (int num : nums) {
            // Add the current element to the heap
            minHeap.offer(num);

            // If the heap size exceeds k, remove the smallest element
            // This ensures the heap always contains the k largest elements seen so far
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The heap's top element is the kth largest
        return minHeap.peek();
    }

    // Alternative solution using max heap
    public int findKthLargestMaxHeap(int[] nums, int k) {
        // Max heap containing all elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all elements to the max heap
        for (int num : nums) {
            maxHeap.offer(num);
        }

        // Extract elements k-1 times
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }

        // The top element is now the kth largest
        return maxHeap.peek();
    }
}