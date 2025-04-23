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
}
