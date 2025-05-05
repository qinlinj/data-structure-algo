package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_i_TopKFrequentElements
 * <p>
 * LeetCode #347: Top K Frequent Elements
 * <p>
 * This solution finds the k most frequent elements in an array.
 * <p>
 * Approach 1 (Priority Queue):
 * 1. Count the frequency of each element using a HashMap
 * 2. Use a min-heap to track the k most frequent elements
 * - The heap orders elements by their frequency (smallest frequency at the top)
 * - If heap size exceeds k, remove the element with smallest frequency
 * 3. The remaining elements in the heap are the k most frequent
 * <p>
 * Approach 2 (Bucket Sort):
 * 1. Count the frequency of each element
 * 2. Create buckets where the index is the frequency
 * 3. Iterate through buckets from highest to lowest frequency
 * <p>
 * Time Complexity:
 * - Approach 1: O(n log k) where n is the array length
 * - Approach 2: O(n) for bucket sort approach
 * <p>
 * Space Complexity: O(n) for both approaches
 */

import java.util.*;

public class _531_i_TopKFrequentElements {

    public static void main(String[] args) {
        _531_i_TopKFrequentElements solution = new _531_i_TopKFrequentElements();

        // Test case 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] result1 = solution.topKFrequent(nums1, k1);

        System.out.println("Input: " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + Arrays.toString(result1));
        // Expected output: [1, 2]

        // Test case 2
        int[] nums2 = {1};
        int k2 = 1;
        int[] result2 = solution.topKFrequent(nums2, k2);

        System.out.println("\nInput: " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + Arrays.toString(result2));
        // Expected output: [1]

        // Test case using bucket sort approach
        int[] nums3 = {4, 1, 1, 1, 2, 2, 3};
        int k3 = 2;
        int[] result3 = solution.topKFrequentBucket(nums3, k3);

        System.out.println("\nInput (Bucket Sort): " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + Arrays.toString(result3));
        // Expected output: [1, 2]
    }

    // Approach 1: Using Priority Queue
    public int[] topKFrequent(int[] nums, int k) {
        // Count frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create a min heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue() - b.getValue()
        );

        // Add elements to the heap, maintaining size k
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Create result array
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }

    // Approach 2: Using Bucket Sort
    public int[] topKFrequentBucket(int[] nums, int k) {
        // Count frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create buckets where bucket[i] contains elements with frequency i
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Fill the buckets
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            buckets[freq].add(num);
        }

        // Collect the k most frequent elements
        int[] result = new int[k];
        int index = 0;

        // Iterate from highest frequency to lowest
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            List<Integer> bucket = buckets[i];
            for (int num : bucket) {
                if (index < k) {
                    result[index++] = num;
                } else {
                    break;
                }
            }
        }

        return result;
    }
}