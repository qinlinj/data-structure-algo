package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * _334_f_BinarySearchUnified.java
 * <p>
 * This class provides a unified framework for all three types of binary search:
 * - Standard binary search: Find any occurrence of target
 * - Left-bound binary search: Find leftmost occurrence of target
 * - Right-bound binary search: Find rightmost occurrence of target
 * <p>
 * UNIFIED BINARY SEARCH FRAMEWORK:
 * ------------------------------
 * 1. All implementations use a closed interval [left, right]
 * <p>
 * 2. All implementations share the same initialization and loop structure:
 * - Initialize left = 0, right = nums.length - 1
 * - Loop condition: while (left <= right)
 * - Mid calculation: mid = left + (right - left) / 2
 * <p>
 * 3. The only difference is how we handle nums[mid] == target:
 * - Standard search: return immediately
 * - Left-bound search: move right boundary to mid-1
 * - Right-bound search: move left boundary to mid+1
 * <p>
 * 4. Different return logic:
 * - Standard search: return -1 if not found
 * - Left-bound search: check nums[left] == target
 * - Right-bound search: check nums[right] == target
 */
public class _334_f_BinarySearchUnified {
}
