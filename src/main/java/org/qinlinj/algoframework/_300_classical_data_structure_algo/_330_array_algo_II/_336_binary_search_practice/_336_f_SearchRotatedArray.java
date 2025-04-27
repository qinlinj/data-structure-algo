package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._336_binary_search_practice;

/**
 * Binary Search in Special Arrays - Search in Rotated Sorted Array
 * <p>
 * Key Concepts:
 * 1. Binary search in a rotated sorted array (containing a "cliff")
 * 2. Determining which side of the array is sorted
 * 3. Making decisions based on whether target is in the sorted portion
 * <p>
 * This class implements LeetCode 33: Search in Rotated Sorted Array
 * Problem: Search for a target value in a rotated sorted array
 * <p>
 * A rotated array is created by taking a sorted array and rotating it at some pivot.
 * Example: [0,1,2,4,5,6,7] -> [4,5,6,7,0,1,2] (rotated at index 3)
 * <p>
 * Approach:
 * 1. Determine if mid is on the left or right side of the "cliff"
 * 2. Determine which portion of the array is sorted
 * 3. Check if target is in the sorted portion, and adjust search range accordingly
 */
public class _336_f_SearchRotatedArray {
}
