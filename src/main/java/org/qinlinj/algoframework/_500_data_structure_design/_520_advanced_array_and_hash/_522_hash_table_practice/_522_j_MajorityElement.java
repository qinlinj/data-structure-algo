package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice;

import java.util.*;

/**
 * _522_j_MajorityElement
 * <p>
 * LeetCode #169: Majority Element
 * <p>
 * Problem:
 * Given an array nums of size n, return the majority element, which appears
 * more than ⌊n/2⌋ times. You may assume that the majority element always exists.
 * <p>
 * Approach:
 * We use the Boyer-Moore Voting Algorithm, which is elegant and efficient:
 * <p>
 * 1. Initialize a candidate element and a counter to 0
 * 2. For each element in the array:
 * - If counter is 0, set the current element as the candidate
 * - If current element matches the candidate, increment counter
 * - If current element differs from candidate, decrement counter
 * 3. The final candidate will be the majority element
 * <p>
 * The algorithm works because the majority element appears more than n/2 times,
 * so it will "cancel out" all other elements and remain as the candidate.
 * <p>
 * Imagine the majority element as having positive charge and all other elements
 * as having negative charge. The net charge of the array must be positive, and
 * the element with the most positive contribution must be the majority element.
 * <p>
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - constant extra space
 */
public class _522_j_MajorityElement {

    public static void main(String[] args) {
        _522_j_MajorityElement solution = new _522_j_MajorityElement();

        // Test case 1
        int[] nums1 = {3, 2, 3};
        int result1 = solution.majorityElement(nums1);
        System.out.println("Example 1: " + result1);
        // Expected output: 3

        // Test case 2
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        int result2 = solution.majorityElement(nums2);
        System.out.println("Example 2: " + result2);
        // Expected output: 2

        // Test case 3
        int[] nums3 = {6, 5, 5};
        int result3 = solution.majorityElement(nums3);
        System.out.println("Example 3: " + result3);
        // Expected output: 5
    }

    public int majorityElement(int[] nums) {
        // Candidate for the majority element
        int candidate = 0;
        // Counter to track the "votes"
        int count = 0;

        // Boyer-Moore Voting Algorithm
        for (int num : nums) {
            // If counter is 0, set the current element as the candidate
            if (count == 0) {
                candidate = num;
                count = 1;
            }
            // If current element matches the candidate, increment counter
            else if (num == candidate) {
                count++;
            }
            // If current element differs from candidate, decrement counter
            else {
                count--;
            }
        }

        // The final candidate is guaranteed to be the majority element
        // (We don't need a second pass to verify since the problem guarantees
        // a majority element exists)
        return candidate;
    }

    /**
     * Alternative implementation using a HashMap
     * Less efficient but easier to understand
     */
    public int majorityElementWithHashMap(int[] nums) {
        // Count occurrences of each element
        HashMap<Integer, Integer> counts = new HashMap<>();

        // Threshold for majority element
        int threshold = nums.length / 2;

        for (int num : nums) {
            // Update count
            int count = counts.getOrDefault(num, 0) + 1;
            counts.put(num, count);

            // Check if we've found the majority element
            if (count > threshold) {
                return num;
            }
        }

        // Should never reach here if a majority element exists
        return -1;
    }
}