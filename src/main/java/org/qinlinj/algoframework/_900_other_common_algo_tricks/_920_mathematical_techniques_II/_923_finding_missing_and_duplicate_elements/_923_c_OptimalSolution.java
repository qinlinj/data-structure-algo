package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._923_finding_missing_and_duplicate_elements;

public class _923_c_OptimalSolution {
    /**
     * Optimal solution with O(1) space complexity
     *
     * @param nums array with one duplicate and one missing element
     * @return array containing [duplicate, missing]
     */
    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int duplicate = -1;

        // Step 1: Find duplicate by negative marking
        for (int i = 0; i < n; i++) {
            int element = Math.abs(nums[i]);  // Get original value (ignore sign)
            int targetIndex = element - 1;    // Convert to 0-based index

            if (nums[targetIndex] < 0) {
                // Already marked negative = duplicate found
                duplicate = element;
            } else {
                // First visit, mark as negative
                nums[targetIndex] *= -1;
            }
        }


        return new int[]{duplicate, missing};
    }

}
