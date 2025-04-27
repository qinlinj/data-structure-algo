package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * _334_d_BinarySearchRightBound.java
 * <p>
 * This class implements binary search algorithms for finding the rightmost occurrence of a target.
 * <p>
 * RIGHT-BOUND BINARY SEARCH:
 * ------------------------
 * 1. Purpose: Find the rightmost/last occurrence of target in a sorted array.
 * <p>
 * 2. Two common implementations:
 * a) Half-open interval approach: [left, right) where right = nums.length
 * b) Closed interval approach: [left, right] where right = nums.length - 1
 * <p>
 * 3. Key characteristic: When target is found, DON'T return immediately.
 * Instead, continue searching in the right subarray to find the rightmost occurrence.
 * <p>
 * 4. Behavior when target doesn't exist:
 * - Returns index of the largest element smaller than target
 * - Can be used as part of range search implementations
 * - Can be modified to return -1 when target doesn't exist
 */
public class _334_d_BinarySearchRightBound {


}