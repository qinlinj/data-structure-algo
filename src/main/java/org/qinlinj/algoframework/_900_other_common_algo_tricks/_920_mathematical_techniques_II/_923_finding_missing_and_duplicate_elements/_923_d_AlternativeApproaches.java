package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._923_finding_missing_and_duplicate_elements;

import java.util.*;

public class _923_d_AlternativeApproaches {
    /**
     * Approach 1: Sorting Method
     * Time: O(N log N), Space: O(1) if in-place sort allowed
     */
    public static int[] findErrorNumsSorting(int[] nums) {
        Arrays.sort(nums);
        int duplicate = -1;
        int missing = 1;  // Start checking from 1

        // Find duplicate
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                duplicate = nums[i];
                break;
            }
        }

        // Find missing by checking sequence
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == missing) {
                missing++;  // This number exists, check next
            }
        }

        return new int[]{duplicate, missing};
    }
}
