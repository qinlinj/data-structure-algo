package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._923_finding_missing_and_duplicate_elements;

import java.util.*;

public class _923_a_ProblemIntroduction {
    /**
     * Basic solution using HashMap to track number frequencies
     *
     * @param nums array with one duplicate and one missing number
     * @return array containing [duplicate, missing]
     */
    public static int[] findErrorNumsBasic(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();

        // Count frequency of each number in the array
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int duplicate = -1;
        int missing = -1;

        // Check numbers from 1 to n to find duplicate and missing
        for (int i = 1; i <= n; i++) {
            int frequency = count.getOrDefault(i, 0);
            if (frequency == 2) {
                duplicate = i;  // Found the duplicate number
            } else if (frequency == 0) {
                missing = i;    // Found the missing number
            }
        }

        return new int[]{duplicate, missing};
    }
}
