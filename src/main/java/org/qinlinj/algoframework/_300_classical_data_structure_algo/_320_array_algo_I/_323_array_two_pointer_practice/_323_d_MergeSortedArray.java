package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._323_array_two_pointer_practice;

/**
 * Merge Sorted Array (LeetCode 88)
 * ===============================
 * <p>
 * This class implements a solution to merge two sorted arrays in-place.
 * <p>
 * Problem:
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note that nums1 has a size of m+n where only the first m elements are initialized,
 * and the last n positions are set to 0 to accommodate the merged result.
 * <p>
 * Examples:
 * - nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3 -> [1,2,2,3,5,6]
 * - nums1 = [1], m = 1, nums2 = [], n = 0 -> [1]
 * - nums1 = [0], m = 0, nums2 = [1], n = 1 -> [1]
 * <p>
 * Approach:
 * The key insight is to merge from the end of the arrays rather than from the beginning.
 * This way, we avoid overwriting elements in nums1 that we still need to process.
 * <p>
 * 1. Start with pointers at the end of the actual elements in both arrays
 * 2. Compare elements and place the larger one at the end of nums1
 * 3. Move backwards through the arrays
 * 4. If nums2 still has elements after nums1 is processed, copy them to nums1
 * <p>
 * Time Complexity: O(m+n) - we process each element exactly once
 * Space Complexity: O(1) - we modify nums1 in-place using only a few pointers
 */
public class _323_d_MergeSortedArray {
    /**
     * Merge Sorted Array (LeetCode 88)
     * ===============================
     * <p>
     * This class implements a solution to merge two sorted arrays in-place.
     * <p>
     * Problem:
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * Note that nums1 has a size of m+n where only the first m elements are initialized,
     * and the last n positions are set to 0 to accommodate the merged result.
     * <p>
     * Examples:
     * - nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3 -> [1,2,2,3,5,6]
     * - nums1 = [1], m = 1, nums2 = [], n = 0 -> [1]
     * - nums1 = [0], m = 0, nums2 = [1], n = 1 -> [1]
     * <p>
     * Approach:
     * The key insight is to merge from the end of the arrays rather than from the beginning.
     * This way, we avoid overwriting elements in nums1 that we still need to process.
     * <p>
     * 1. Start with pointers at the end of the actual elements in both arrays
     * 2. Compare elements and place the larger one at the end of nums1
     * 3. Move backwards through the arrays
     * 4. If nums2 still has elements after nums1 is processed, copy them to nums1
     * <p>
     * Time Complexity: O(m+n) - we process each element exactly once
     * Space Complexity: O(1) - we modify nums1 in-place using only a few pointers
     */

    public class MergeSortedArray {

        /**
         * Merges nums2 into nums1 in-place, producing a sorted result.
         *
         * @param nums1 First sorted array with space for merged result
         * @param m     Number of initialized elements in nums1
         * @param nums2 Second sorted array
         * @param n     Number of elements in nums2
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // Initialize pointers at the end of each array's actual elements
            int i = m - 1;      // Last element in nums1
            int j = n - 1;      // Last element in nums2
            int p = m + n - 1;  // Position to place the next largest element

            // Merge from the end, placing the larger element at the end of nums1
            while (i >= 0 && j >= 0) {
                if (nums1[i] > nums2[j]) {
                    nums1[p] = nums1[i];
                    i--;
                } else {
                    nums1[p] = nums2[j];
                    j--;
                }
                p--;
            }

            // If there are remaining elements in nums2, copy them to nums1
            // (Note: if nums1 has remaining elements, they're already in the right place)
            while (j >= 0) {
                nums1[p] = nums2[j];
                j--;
                p--;
            }
        }
    }
}
