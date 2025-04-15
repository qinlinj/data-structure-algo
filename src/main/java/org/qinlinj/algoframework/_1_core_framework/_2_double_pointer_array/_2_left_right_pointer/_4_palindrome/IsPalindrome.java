package org.qinlinj.algoframework._1_core_framework._2_double_pointer_array._2_left_right_pointer._4_palindrome;

// @formatter:off
public class IsPalindrome {
    /**
     * Determines if a string is a palindrome.
     *
     * This method uses the two-pointer technique (left-right pointers) to efficiently
     * check if a string reads the same forward and backward without creating a reversed copy.
     *
     * Visual example:
     * For string: "racecar"
     *
     * Initial state:
     * "racecar"
     *  l     r
     * (left = 0, right = 6, chars match: 'r' == 'r')
     *
     * After first iteration:
     * "racecar"
     *   l   r
     * (left = 1, right = 5, chars match: 'a' == 'a')
     *
     * After second iteration:
     * "racecar"
     *    l r
     * (left = 2, right = 4, chars match: 'c' == 'c')
     *
     * After third iteration:
     * "racecar"
     *     lr
     * (left = 3, right = 3, pointers meet, loop exits)
     *
     * Return true (string is a palindrome)
     *
     * For string: "hello"
     * First iteration: 'h' != 'o', return false immediately
     *
     * Time Complexity: O(n/2) which simplifies to O(n) where n is the length of the string
     * Space Complexity: O(1) as we're checking in-place
     *
     * @param s The string to check
     * @return true if the string is a palindrome, false otherwise
     */
    boolean isPalindrome(String s) {
        // Initialize two pointers - left at start, right at end
        int left = 0, right = s.length() - 1;

        // Continue checking while pointers haven't crossed
        while (left < right) {
            // If characters at left and right positions don't match
            if (s.charAt(left) != s.charAt(right)) {
                // String is not a palindrome
                return false;
            }

            // Move pointers toward each other
            left++;
            right--;
        }

        // If we made it through the loop, all characters matched
        return true;
    }
}
