package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._314_palindrome_linked_list;

/**
 * OPTIMIZED PALINDROME LINKED LIST DETECTION
 * <p>
 * This class focuses on space-optimized techniques for determining if a linked list
 * is a palindrome, with a primary focus on the two-pointer approach that achieves
 * O(1) space complexity.
 * <p>
 * APPROACH:
 * <p>
 * 1. Find the middle of the linked list using fast/slow pointers
 * - Slow pointer moves one step at a time
 * - Fast pointer moves two steps at a time
 * - When fast reaches the end, slow is at the middle
 * <p>
 * 2. Reverse the second half of the linked list
 * - This is a key step for achieving O(1) space complexity
 * - No need for additional data structures to store values
 * <p>
 * 3. Compare the first half with the reversed second half
 * - Use two pointers to check if corresponding values match
 * <p>
 * 4. (Optional) Restore the original list structure
 * - Reverse the second half again to restore the original list
 * - Useful when the input list should not be modified
 */
public class OptimizedPalindromeLinkedList {

    /**
     * Helper method to create a linked list from an array
     */
    public static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * Helper method to print a linked list
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    /**
     * Helper method to verify that two linked lists are identical
     * Used to check if restoration was successful
     */
    public static boolean areListsIdentical(ListNode list1, ListNode list2) {
        while (list1 != null && list2 != null) {
            if (list1.val != list2.val) {
                return false;
            }
            list1 = list1.next;
            list2 = list2.next;
        }

        return list1 == null && list2 == null;
    }

    /**
     * Main method to demonstrate the optimized palindrome detection
     */
    public static void main(String[] args) {
        OptimizedPalindromeLinkedList solution = new OptimizedPalindromeLinkedList();

        // Test cases
        testPalindrome(solution, new int[]{1, 2, 2, 1}, "Even length palindrome");
        testPalindrome(solution, new int[]{1, 2, 3, 2, 1}, "Odd length palindrome");
        testPalindrome(solution, new int[]{1, 2}, "Non-palindrome");
        testPalindrome(solution, new int[]{1}, "Single element");

        // Test to verify list restoration
        System.out.println("\nVerifying list restoration:");
        int[] values = {1, 2, 3, 4, 5};
        ListNode original = createList(values);
        System.out.print("Original list: ");
        printList(original);

        // Create a copy to compare after restoration
        int[] valuesCopy = {1, 2, 3, 4, 5};
        ListNode expectedAfterRestore = createList(valuesCopy);

        solution.isPalindrome(original);

        System.out.print("List after palindrome check: ");
        printList(original);

        boolean restored = areListsIdentical(original, expectedAfterRestore);
        System.out.println("Successfully restored to original: " + restored);
    }

    /**
     * Helper method to test a palindrome case
     */
    private static void testPalindrome(OptimizedPalindromeLinkedList solution, int[] values, String description) {
        System.out.println("\nTesting: " + description);
        ListNode list = createList(values);
        System.out.print("List: ");
        printList(list);

        boolean isPalindrome = solution.isPalindrome(list);
        System.out.println("Is palindrome: " + isPalindrome);
    }

    /**
     * Main method to determine if a linked list is a palindrome
     * using O(1) space complexity.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;  // Empty list or single node is palindrome
        }

        // Step 1: Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // If list length is odd, move slow one step further
        // This ensures we're at the start of the second half
        if (fast != null) {
            slow = slow.next;
        }

        // Step 2: Reverse the second half of the linked list
        ListNode secondHalfReversed = reverse(slow);

        // Save the head of the reversed half for restoration later
        ListNode secondHalfHead = secondHalfReversed;

        // Step 3: Compare the first half with the reversed second half
        ListNode firstHalf = head;
        boolean isPalindrome = true;

        while (secondHalfReversed != null) {
            if (firstHalf.val != secondHalfReversed.val) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalfReversed = secondHalfReversed.next;
        }

        // Step 4: Restore the original list by reversing the second half again
        // Find the midpoint again to reconnect the two halves
        slow = head;
        fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half back to its original order
        slow.next = reverse(secondHalfHead);

        return isPalindrome;
    }

    /**
     * Helper method to reverse a linked list
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        return prev;
    }

    /**
     * Simplified version that does not restore the original list structure
     * Use this if preserving the input list is not required
     */
    public boolean isPalindromeSimple(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Find the middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Adjust for odd length
        if (fast != null) {
            slow = slow.next;
        }

        // Reverse second half
        ListNode right = reverse(slow);
        ListNode left = head;

        // Compare
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }

    // Definition for singly-linked list
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
