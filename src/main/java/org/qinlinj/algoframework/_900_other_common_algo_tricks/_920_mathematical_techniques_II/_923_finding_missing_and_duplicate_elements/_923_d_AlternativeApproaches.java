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

    /**
     * Approach 2: XOR Bit Manipulation
     * Uses property: a ^ a = 0, a ^ 0 = a
     * Time: O(N), Space: O(1)
     */
    public static int[] findErrorNumsXOR(int[] nums) {
        int n = nums.length;
        int xorAll = 0;

        // XOR all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xorAll ^= i;
        }

        // XOR all numbers in the array
        for (int num : nums) {
            xorAll ^= num;
        }

        // xorAll now contains (missing ^ duplicate)
        // Need additional logic to separate them

        // Find rightmost set bit to distinguish missing and duplicate
        int rightmostBit = xorAll & (-xorAll);

        int type1 = 0, type2 = 0;

        // Divide numbers into two groups based on rightmost bit
        for (int i = 1; i <= n; i++) {
            if ((i & rightmostBit) == 0) {
                type1 ^= i;
            } else {
                type2 ^= i;
            }
        }

        for (int num : nums) {
            if ((num & rightmostBit) == 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }

        // One of type1/type2 is duplicate, other is missing
        // Check which one appears in the array
        for (int num : nums) {
            if (num == type1) {
                return new int[]{type1, type2};  // type1 is duplicate
            }
        }

        return new int[]{type2, type1};  // type2 is duplicate
    }
}
