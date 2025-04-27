package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * Standard Binary Search Implementation
 * <p>
 * This class implements the standard binary search algorithm to find a specific target value
 * in a sorted array. If the target exists, it returns the index; otherwise, it returns -1.
 * <p>
 * Key characteristics of this implementation:
 * 1. Search Interval: Closed interval [left, right]
 * 2. Loop Condition: while (left <= right)
 * 3. Target Found: Return immediately when found
 * 4. Boundary Updates:
 * - When nums[mid] < target: left = mid + 1
 * - When nums[mid] > target: right = mid - 1
 * <p>
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 * <p>
 * Limitations:
 * - This algorithm only returns one occurrence of the target.
 * - If multiple occurrences exist, there's no guarantee which one will be returned.
 * - When you need to find the leftmost or rightmost occurrence, use specialized binary search variants.
 */
public class _334_b_BinarySearchStandard {
}
