package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._314_palindrome_linked_list;

/**
 * PALINDROME LINKED LIST DETECTION
 * <p>
 * This class demonstrates techniques for determining if a linked list is a palindrome.
 * A palindrome reads the same forward and backward (e.g., 1->2->2->1).
 * <p>
 * KEY TECHNIQUES:
 * <p>
 * 1. Recursive Approach
 * - Uses the recursive call stack to simulate backwards traversal
 * - Compares nodes from front and back simultaneously
 * - Simple but requires O(n) space due to the call stack
 * <p>
 * 2. Optimized Two-Pointer Approach
 * - Finds the middle of the list using fast/slow pointers
 * - Reverses the second half of the list
 * - Compares the first half with the reversed second half
 * - Achieves O(1) space complexity
 * <p>
 * 3. Array Conversion Approach
 * - Converts the linked list to an array
 * - Uses traditional palindrome checking with two pointers
 * - Simple but requires O(n) extra space
 */
public class PalindromeLinkedList {
}
