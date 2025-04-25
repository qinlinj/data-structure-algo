package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._327_difference_array;

/**
 * Difference Array Technique
 * <p>
 * Key Points:
 * 1. While prefix sum is useful for querying sum ranges in an immutable array,
 * difference array is useful for frequent range updates in an array
 * 2. A difference array 'diff' is constructed where diff[i] = nums[i] - nums[i-1] (with diff[0] = nums[0])
 * 3. To add a value to a range nums[i..j], we only need to set diff[i] += val and diff[j+1] -= val
 * 4. After all updates, the original array can be reconstructed from the difference array
 * 5. Time complexity: O(1) for each range update, O(n) to construct or reconstruct the array
 * 6. Space complexity: O(n) for the difference array
 * 7. Perfect for scenarios with many range updates followed by few queries
 */
public class DifferenceArray {
}
