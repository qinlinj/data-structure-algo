package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._331_sliding_window_template;

/**
 * Sliding Window Algorithm Framework and Examples
 * <p>
 * Key Concepts:
 * 1. The sliding window technique is an efficient algorithm pattern used to process arrays or strings
 * by maintaining a "window" that slides through the data.
 * <p>
 * 2. Time Complexity: O(N) vs. Brute Force O(N²)
 * - The sliding window achieves O(N) because each element enters and exits the window exactly once
 * - In contrast, brute force nested loops process some elements multiple times
 * <p>
 * 3. When to Use:
 * - Finding subarrays/substrings that meet certain conditions
 * - Problems involving continuous sequences or ranges
 * - When looking for optimal subarrays (max/min sum, etc.)
 * <p>
 * 4. Framework Structure:
 * - Initialize window bounds (left and right pointers)
 * - Expand window by moving right pointer
 * - Contract window by moving left pointer when needed
 * - Update result during expansion or contraction
 * <p>
 * 5. Important Note:
 * - Sliding window does NOT enumerate all possible subarrays
 * - It intelligently prunes the search space
 */
public class SlidingWindowAlgorithm {
}
