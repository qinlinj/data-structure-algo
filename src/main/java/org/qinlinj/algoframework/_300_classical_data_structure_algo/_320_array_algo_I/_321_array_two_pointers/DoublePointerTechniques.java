package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._321_array_two_pointers;

/**
 * Double Pointer Techniques in Arrays and Linked Lists
 * ===================================================
 * <p>
 * This class demonstrates various double pointer techniques commonly used in array and linked list algorithms.
 * Double pointer techniques can be classified into two main categories:
 * <p>
 * 1. Fast-Slow Pointers: Two pointers moving in the same direction at different speeds
 * 2. Left-Right Pointers: Two pointers moving toward or away from each other
 * <p>
 * FAST-SLOW POINTER TECHNIQUES:
 * -----------------------------
 * 1. In-place Array Modification:
 * - Used when we need to modify arrays without using extra space
 * - Common operations include removing duplicates, removing specific elements
 * - Slow pointer maintains the boundary of the processed section
 * - Fast pointer scans through the entire array
 * <p>
 * 2. Sliding Window:
 * - Used for finding subarrays that satisfy certain conditions
 * - Two pointers define the boundaries of the window
 * - Window expands/contracts based on specific conditions
 * <p>
 * LEFT-RIGHT POINTER TECHNIQUES:
 * -----------------------------
 * 1. Binary Search:
 * - Efficient search in sorted arrays
 * - Left and right pointers define the search space
 * <p>
 * 2. Two Sum (and n-Sum):
 * - Finding elements that sum to a target in a sorted array
 * - Two pointers move toward each other
 * <p>
 * 3. Array Reversal:
 * - Swapping elements from opposite ends
 * <p>
 * 4. Palindrome Detection:
 * - Check if a string reads the same backward as forward
 * - Two pointers moving from outside to inside or inside to outside
 */

public class DoublePointerTechniques {

    /**
     * Demo method showing different double pointer techniques
     */
    public static void main(String[] args) {
        DoublePointerTechniques solution = new DoublePointerTechniques();

        // Example for removeDuplicates
        int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = solution.removeDuplicates(nums1);
        System.out.println("After removing duplicates, length = " + len);
        System.out.print("Array: ");
        for (int i = 0; i < len; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println();

        // Example for removeElement
        int[] nums2 = {3, 2, 2, 3};
        len = solution.removeElement(nums2, 3);
        System.out.println("After removing value 3, length = " + len);
        System.out.print("Array: ");
        for (int i = 0; i < len; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println();

        // Example for moveZeroes
        int[] nums3 = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums3);
        System.out.print("After moving zeroes: ");
        for (int num : nums3) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Example for twoSum
        int[] nums4 = {2, 7, 11, 15};
        int[] result = solution.twoSum(nums4, 9);
        System.out.println("Indices of two numbers that add up to 9: [" + result[0] + ", " + result[1] + "]");

        // Example for isPalindrome
        String s1 = "racecar";
        System.out.println("Is '" + s1 + "' a palindrome? " + solution.isPalindrome(s1));

        // Example for longestPalindrome
        String s2 = "babad";
        System.out.println("Longest palindromic substring in '" + s2 + "': " + solution.longestPalindrome(s2));
    }

    /*** FAST-SLOW POINTER TECHNIQUES ***/

    /**
     * 1.1 Remove duplicates from a sorted array in-place
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // Maintain nums[0..slow] without duplicates
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // Array length is index + 1
        return slow + 1;
    }

    /**
     * 1.2 Remove duplicates from a sorted linked list
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                // Similar to array: nums[slow] = nums[fast]
                slow.next = fast;
                // Similar to array: slow++
                slow = slow.next;
            }
            // Similar to array: fast++
            fast = fast.next;
        }
        // Disconnect from any remaining duplicates
        slow.next = null;
        return head;
    }

    /**
     * 1.3 Remove elements matching a value from an array in-place
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * 1.4 Move all zeros to the end of an array while maintaining the order of non-zero elements
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void moveZeroes(int[] nums) {
        // First remove all zeros (which returns the length of non-zero elements)
        int p = removeElement(nums, 0);
        // Then fill the rest with zeros
        for (; p < nums.length; p++) {
            nums[p] = 0;
        }
    }

    /**
     * 2. Sliding Window Algorithm Template
     * This is a general framework for sliding window problems
     * Time Complexity: Typically O(n)
     * Space Complexity: Depends on window data structure
     */
    public void slidingWindowTemplate(int[] nums) {
//        int left = 0, right = 0;
//
//        while (right < nums.length) {
//            // Expand the window
//            // window.add(nums[right]);
//            right++;
//
//            // When the window needs to shrink
//            while (/* condition to shrink window */) {
//                // Shrink the window
//                // window.remove(nums[left]);
//                left++;
//            }
//        }
    }

    /*** LEFT-RIGHT POINTER TECHNIQUES ***/

    /**
     * 1. Binary Search
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int binarySearch(int[] nums, int target) {
        // Left and right pointers moving toward each other
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevent integer overflow
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    /**
     * 2. Two Sum in a sorted array
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        // Left and right pointers moving toward each other
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                // +1 because problem uses 1-indexed array
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                // Need to increase sum
                left++;
            } else {
                // Need to decrease sum
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 3. Reverse an array or string
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void reverseString(char[] s) {
        // Left and right pointers moving toward each other
        int left = 0, right = s.length - 1;
        while (left < right) {
            // Swap elements at left and right
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 4.1 Check if a string is a palindrome
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean isPalindrome(String s) {
        // Left and right pointers moving toward each other
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 4.2 Find the longest palindromic substring
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // Find palindrome with odd length (single character center)
            String s1 = expandAroundCenter(s, i, i);
            // Find palindrome with even length (two character center)
            String s2 = expandAroundCenter(s, i, i + 1);
            // Update result with the longest palindrome found
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    /**
     * Helper method for finding palindromic substrings by expanding from center
     */
    private String expandAroundCenter(String s, int left, int right) {
        // Expand as long as characters match and indices are valid
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // Expand outward in both directions
            left--;
            right++;
        }
        // Return the palindromic substring (note that indices need adjustment)
        return s.substring(left + 1, right);
    }

    // Definition for singly-linked list node
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}