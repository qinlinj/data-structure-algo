package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._334_binary_search_template;

/**
 * Binary Search Framework
 * <p>
 * This class introduces the basic template for binary search algorithms.
 * <p>
 * Key points:
 * 1. Binary search requires a sorted array
 * 2. The basic framework consists of:
 * - Setting left and right boundaries
 * - Finding the middle element
 * - Comparing target with middle element
 * - Adjusting search boundaries
 * 3. Binary search has O(log n) time complexity
 * 4. To prevent integer overflow when calculating mid:
 * - Use: mid = left + (right - left) / 2
 * - Instead of: mid = (left + right) / 2
 * 5. Always write out conditions explicitly with else-if statements
 * to better understand the logic and avoid errors
 */
public class _334_a_BinarySearchFramework {
}
