package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_m_AdvantageShuffle
 * <p>
 * LeetCode #870: Advantage Shuffle
 * <p>
 * This solution maximizes the "advantage" of one array over another by finding
 * the optimal permutation of the first array.
 * <p>
 * Approach:
 * 1. Sort the first array (A) in ascending order
 * 2. Use a priority queue (max heap) to sort the second array (B) in descending order
 * along with its original indices
 * 3. For each element in B (starting from largest):
 * - If A's largest element can beat it, use that element (gain advantage)
 * - Otherwise, use A's smallest element (sacrifice a weak card)
 * 4. Build the result array using the determined assignments
 * <p>
 * This is similar to the "Tjele racing horses" strategy where:
 * - When you can win, use your slightly stronger horse
 * - When you can't win, use your weakest horse
 * <p>
 * Time Complexity: O(n log n) for the sorting operations
 * Space Complexity: O(n) for the arrays and heap
 */

import java.util.*;

public class _531_m_AdvantageShuffle {

    public static void main(String[] args) {
        _531_m_AdvantageShuffle solution = new _531_m_AdvantageShuffle();

        // Test case 1
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {1, 10, 4, 11};
        int[] result1 = solution.advantageCount(nums1, nums2);

        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));
        System.out.println("Result: " + Arrays.toString(result1));
        // Expected output: [2, 11, 7, 15] or any valid permutation

        // Test case 2
        int[] nums3 = {12, 24, 8, 32};
        int[] nums4 = {13, 25, 32, 11};
        int[] result2 = solution.advantageCount(nums3, nums4);

        System.out.println("\nnums1: " + Arrays.toString(nums3));
        System.out.println("nums2: " + Arrays.toString(nums4));
        System.out.println("Result: " + Arrays.toString(result2));
        // Expected output: [24, 32, 8, 12] or any valid permutation
    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] result = new int[n];

        // Sort nums1 in ascending order
        Arrays.sort(nums1);

        // Create a max heap of [index, value] pairs from nums2
        // This allows us to process nums2 elements from largest to smallest
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < n; i++) {
            maxHeap.offer(new int[]{i, nums2[i]});
        }

        // Track the left and right pointers for nums1
        int left = 0;
        int right = n - 1;

        // Process nums2 elements from largest to smallest
        while (!maxHeap.isEmpty()) {
            int[] pair = maxHeap.poll();
            int index = pair[0];  // Original index in nums2
            int value = pair[1];  // Value in nums2

            if (nums1[right] > value) {
                // If nums1's largest remaining element can beat nums2's element,
                // use it to gain an advantage
                result[index] = nums1[right--];
            } else {
                // Otherwise, use nums1's smallest element as a sacrifice
                result[index] = nums1[left++];
            }
        }

        return result;
    }

    /**
     * Alternative implementation using a greedy approach without a heap
     */
    public int[] advantageCountGreedy(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);  // Initialize with -1 (undefined)

        // Sort nums1
        Arrays.sort(nums1);

        // Create a sorted copy of nums2 with original indices
        int[][] sortedNums2 = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedNums2[i] = new int[]{nums2[i], i};
        }
        Arrays.sort(sortedNums2, Comparator.comparingInt(a -> a[0]));

        // Use two pointers approach
        int left = 0;  // pointer for nums1
        int right = n - 1;  // pointer for result array

        // Iterate through sortedNums2 from largest to smallest
        for (int i = n - 1; i >= 0; i--) {
            int value = sortedNums2[i][0];
            int originalIndex = sortedNums2[i][1];

            if (nums1[right] > value) {
                // If current largest in nums1 can beat current in nums2, use it
                result[originalIndex] = nums1[right--];
            } else {
                // Otherwise, assign smallest from nums1
                result[originalIndex] = nums1[left++];
            }
        }

        return result;
    }
}