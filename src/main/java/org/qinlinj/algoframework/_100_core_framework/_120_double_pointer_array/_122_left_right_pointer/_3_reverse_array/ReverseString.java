package org.qinlinj.algoframework._100_core_framework._120_double_pointer_array._2_left_right_pointer._3_reverse_array;

// @formatter:off
public class ReverseString {
    /**
     * Reverses a character array in-place.
     *
     * This method uses the two-pointer technique (left-right pointers) to efficiently
     * reverse a character array without using extra space.
     *
     * Visual example:
     * For array: ['h', 'e', 'l', 'l', 'o']
     *
     * Initial state:
     * ['h', 'e', 'l', 'l', 'o']
     *   l               r
     * (left = 0, right = 4)
     *
     * After first swap:
     * ['o', 'e', 'l', 'l', 'h']
     *        l       r
     * (left = 1, right = 3)
     *
     * After second swap:
     * ['o', 'l', 'l', 'e', 'h']
     *            lr
     * (left = 2, right = 2, pointers meet, loop exits)
     *
     * Final result: ['o', 'l', 'l', 'e', 'h'] (reversed)
     *
     * Time Complexity: O(n) where n is the length of the array
     * Space Complexity: O(1) as we're reversing in-place
     *
     * @param s The character array to be reversed
     */
    void reverseString(char[] s) {
        // Initialize two pointers - left at start, right at end
        int left = 0, right = s.length - 1;

        // Continue swapping while pointers haven't crossed
        while (left < right) {
            // Swap characters at left and right pointers
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // Move pointers toward each other
            left++;
            right--;
        }
    }
}
