package org.qinlinj.algoframework._100_algo_core_framework._120_double_pointer_array._121_fast_slow_pointer._121_2_sliding_window;

// @formatter:off
/**
 * Sliding Window Algorithm Framework
 *
 * This framework demonstrates the fast-slow pointer technique used in sliding window algorithms.
 * The 'right' pointer expands the window, while the 'left' pointer shrinks it when needed.
 *
 * Visual example:
 * For array: [2, 3, 1, 2, 4, 3] with target sum 7
 *
 * Initial state:
 * [2, 3, 1, 2, 4, 3]
 *  l
 *  r
 * Window: [] (empty)
 *
 * After expansion:
 * [2, 3, 1, 2, 4, 3]
 *  l        r
 * Window: [2,3,1,2] (sum = 8 > 7, need to shrink)
 *
 * After shrinking:
 * [2, 3, 1, 2, 4, 3]
 *     l     r
 * Window: [3,1,2] (sum = 6 < 7, need to expand again)
 *
 * Final minimum window that meets criteria:
 * [2, 3, 1, 2, 4, 3]
 *           l  r
 * Window: [2,4] (sum = 6, length = 2)
 */
public class SlidingWindow {
    /**
     * Generic sliding window algorithm template.
     *
     * @param nums The input array or string
     * @return The result based on the specific problem
     */
    public int slidingWindowTemplate(int[] nums) {
        // Initialize the left and right pointers
        int left = 0, right = 0;
        // Result variable (to be customized based on the problem)
        int result = 0;
        // Window state (could be a sum, HashMap for character frequencies, etc.)
        // For this example, we'll use a simple sum
        int windowSum = 0;

        // Expand the window by moving right pointer
        while (right < nums.length) {
            // Add the current element to the window
            windowSum += nums[right];
            // Move right pointer forward to expand window
            right++;

            // Condition to shrink the window
            // (customize based on specific problem requirements)
            while (windowNeedsShrink(windowSum)) {
                // Update result if needed before shrinking
                result = updateResult(result, right - left);

                // Remove the leftmost element from window
                windowSum -= nums[left];
                // Move left pointer forward to shrink window
                left++;
            }

            // Update result after window adjustment if needed
            // Some problems require updating here instead of in the shrink loop
            result = updateResult(result, right - left);
        }

        return result;
    }

    /**
     * Helper method to determine if window needs to shrink.
     * This should be customized based on the problem.
     */
    private boolean windowNeedsShrink(int windowSum) {
        // Example condition: if sum exceeds a target value
        int target = 10; // This would be a parameter in an actual implementation
        return windowSum > target;
    }

    /**
     * Helper method to update the result based on current window.
     * This should be customized based on the problem.
     */
    private int updateResult(int currentResult, int windowSize) {
        // Example: tracking minimum window size
        return (currentResult == 0) ? windowSize : Math.min(currentResult, windowSize);
    }
}

