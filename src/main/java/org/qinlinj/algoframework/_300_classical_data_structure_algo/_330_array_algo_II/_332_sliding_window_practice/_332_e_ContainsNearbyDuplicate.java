package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

import java.util.*;

/**
 * LeetCode 219: Contains Duplicate II
 * <p>
 * Problem Description:
 * Given an integer array nums and an integer k, determine if there are two distinct
 * indices i and j in the array where nums[i] == nums[j] and abs(i - j) <= k.
 * <p>
 * Key Insight:
 * This problem can be solved using a sliding window approach with a fixed window size of k.
 * We maintain a set of elements within the window and check if any new element is already
 * in the set before adding it.
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(min(n, k)) for storing elements in the sliding window
 */

public class _332_e_ContainsNearbyDuplicate {

    /**
     * Visual representation of the sliding window algorithm:
     * <p>
     * Example: nums = [1,2,3,1], k = 3
     * <p>
     * Iteration steps:
     * <p>
     * Initialize: window = {}
     * <p>
     * 1. i=0, nums[0]=1: window={1}
     * <p>
     * 2. i=1, nums[1]=2: window={1,2}
     * <p>
     * 3. i=2, nums[2]=3: window={1,2,3}
     * <p>
     * 4. i=3, nums[3]=1:
     * 1 is already in window, return true
     * <p>
     * Example: nums = [1,2,3,4], k = 3
     * <p>
     * Initialize: window = {}
     * <p>
     * 1. i=0, nums[0]=1: window={1}
     * <p>
     * 2. i=1, nums[1]=2: window={1,2}
     * <p>
     * 3. i=2, nums[2]=3: window={1,2,3}
     * <p>
     * 4. i=3, nums[3]=4:
     * Window size exceeds k, remove nums[0]=1: window={2,3}
     * Add nums[3]=4: window={2,3,4}
     * No duplicates found, continue...
     * <p>
     * Eventually return false as no duplicates within distance k are found.
     */

    public static void main(String[] args) {
        _332_e_ContainsNearbyDuplicate solution = new _332_e_ContainsNearbyDuplicate();

        // Example 1
        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + solution.containsNearbyDuplicate(nums1, k1)); // Expected: true
        System.out.println("Window Output: " + solution.containsNearbyDuplicateWithWindow(nums1, k1)); // Expected: true

        // Example 2
        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + solution.containsNearbyDuplicate(nums2, k2)); // Expected: true
        System.out.println("Window Output: " + solution.containsNearbyDuplicateWithWindow(nums2, k2)); // Expected: true

        // Example 3
        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;
        System.out.println("\nExample 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + solution.containsNearbyDuplicate(nums3, k3)); // Expected: false
        System.out.println("Window Output: " + solution.containsNearbyDuplicateWithWindow(nums3, k3)); // Expected: false
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Edge cases
        if (nums == null || nums.length <= 1 || k <= 0) {
            return false;
        }

        // HashSet to store elements in the current window
        HashSet<Integer> window = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // If window size exceeds k, remove the leftmost element
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }

            // If current element already exists in window, return true
            if (!window.add(nums[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Alternative implementation using the sliding window framework
     * with explicit left and right pointers
     */
    public boolean containsNearbyDuplicateWithWindow(int[] nums, int k) {
        int left = 0, right = 0;
        HashSet<Integer> window = new HashSet<>();

        // Sliding window algorithm
        while (right < nums.length) {
            // If current element is already in window, we found duplicates within distance k
            if (window.contains(nums[right])) {
                return true;
            }

            // Add current element to window
            window.add(nums[right]);
            right++;

            // If window size exceeds k, remove leftmost element
            if (right - left > k) {
                window.remove(nums[left]);
                left++;
            }
        }

        return false;
    }
}