package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._332_sliding_window_practice;

import java.util.*;

/**
 * LeetCode 1004: Max Consecutive Ones III
 * <p>
 * Problem Description:
 * Given a binary array nums and an integer k, return the maximum number of
 * consecutive 1's in the array if you can flip at most k 0's to 1's.
 * <p>
 * Key Insight:
 * This problem can be reframed as: "Find the longest subarray that contains at most k zeros."
 * The sliding window technique is perfect for this because:
 * 1. We expand the window to include more elements
 * 2. When we have more than k zeros in the window, we shrink it from the left
 * 3. The window size at each valid state represents a potential answer
 * <p>
 * Time Complexity: O(n) where n is the length of the array
 * Space Complexity: O(1) as we only use a constant amount of extra space
 */
public class _332_c_MaxConsecutiveOnesIII {

    /**
     * Visual representation of the sliding window algorithm:
     * <p>
     * Example: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * <p>
     * Iteration steps:
     * <p>
     * Initialize: left=0, right=0, zeroCount=0, maxLength=0
     * <p>
     * 1. Add nums[0]=1: zeroCount=0, window=[1]
     * maxLength = max(0, 1) = 1
     * <p>
     * 2. Add nums[1]=1: zeroCount=0, window=[1,1]
     * maxLength = max(1, 2) = 2
     * <p>
     * 3. Add nums[2]=1: zeroCount=0, window=[1,1,1]
     * maxLength = max(2, 3) = 3
     * <p>
     * 4. Add nums[3]=0: zeroCount=1, window=[1,1,1,0]
     * maxLength = max(3, 4) = 4
     * <p>
     * 5. Add nums[4]=0: zeroCount=2, window=[1,1,1,0,0]
     * maxLength = max(4, 5) = 5
     * <p>
     * 6. Add nums[5]=0: zeroCount=3, window=[1,1,1,0,0,0]
     * zeroCount(3) > k(2), shrink window
     * Remove nums[0]=1: zeroCount=3, window=[1,1,0,0,0]
     * Remove nums[1]=1: zeroCount=3, window=[1,0,0,0]
     * Remove nums[2]=1: zeroCount=3, window=[0,0,0]
     * Remove nums[3]=0: zeroCount=2, window=[0,0]
     * maxLength = max(5, 2) = 5
     * <p>
     * 7. Continue this process...
     * <p>
     * Final window will be [0,0,1,1,1,1] (with flipped zeros) or [1,1,1,1] (without flipped zeros)
     * Final maxLength = 6
     */

    public static void main(String[] args) {
        _332_c_MaxConsecutiveOnesIII solution = new _332_c_MaxConsecutiveOnesIII();

        // Example 1
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + solution.longestOnes(nums1, k1)); // Expected: 6
        System.out.println("Alternative Output: " + solution.longestOnesAlternative(nums1, k1)); // Expected: 6

        // Example 2
        int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + solution.longestOnes(nums2, k2)); // Expected: 10
        System.out.println("Alternative Output: " + solution.longestOnesAlternative(nums2, k2)); // Expected: 10

        // Additional example
        int[] nums3 = {0, 0, 0, 1};
        int k3 = 4;
        System.out.println("\nAdditional example:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + solution.longestOnes(nums3, k3)); // Expected: 4
        System.out.println("Alternative Output: " + solution.longestOnesAlternative(nums3, k3)); // Expected: 4
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int zeroCount = 0;   // Count of zeros in current window
        int maxLength = 0;   // Maximum consecutive ones (after flipping)

        while (right < nums.length) {
            // If current element is 0, increment zero count
            if (nums[right] == 0) {
                zeroCount++;
            }

            // Expand window
            right++;

            // If zero count exceeds k, shrink window from left
            // until we have at most k zeros in the window
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update maximum length after ensuring window is valid
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }

    /**
     * Alternative implementation that's slightly more efficient
     * by using a single calculation for window size that needs adjustment
     */
    public int longestOnesAlternative(int[] nums, int k) {
        int left = 0, right = 0;
        int windowOneCount = 0; // Count of ones in current window
        int maxLength = 0;

        while (right < nums.length) {
            // Expand window
            if (nums[right] == 1) {
                windowOneCount++;
            }
            right++;

            // If number of zeros in window exceeds k, shrink window
            // zeros in window = window size - ones in window
            while (right - left - windowOneCount > k) {
                if (nums[left] == 1) {
                    windowOneCount--;
                }
                left++;
            }

            // Update maximum length
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }
}
