package org.qinlinj.algoframework._300_classical_data_structure_algo._310_linked_list_algo._311_linked_list_two_pointers;

/**
 * ESSENTIAL TECHNIQUES FOR SINGLY LINKED LISTS
 * <p>
 * This class summarizes key techniques for working with singly linked lists,
 * each accompanied by a practical code example.
 * <p>
 * KEY TECHNIQUES:
 * <p>
 * 1. Using a Dummy Node
 * - Creates a placeholder node at the beginning of a list
 * - Simplifies edge cases by eliminating null pointer checks
 * - Commonly used when creating new linked lists
 * <p>
 * 2. Merging Two Sorted Lists
 * - Combines two sorted linked lists while maintaining order
 * - Uses a dummy node and two pointers to track current position
 * - Similar to merging in merge sort
 * <p>
 * 3. Partitioning a Linked List
 * - Splits a list into two parts based on a value
 * - Uses dummy nodes to track the head of each new list
 * - Preserves the original relative ordering of elements
 * <p>
 * 4. Merging K Sorted Lists
 * - Uses a priority queue (min heap) to efficiently select next node
 * - More efficient than merging lists one by one
 * - Time complexity: O(N log k) where N is total nodes, k is number of lists
 * <p>
 * 5. Finding the Kth Node from the End
 * - Uses fast and slow pointers
 * - Fast pointer moves k steps ahead, then both pointers move together
 * - When fast pointer reaches end, slow pointer is at the kth node from end
 * <p>
 * 6. Finding the Middle Node
 * - Uses fast and slow pointers
 * - Fast pointer moves twice as fast as slow pointer
 * - When fast pointer reaches end, slow pointer is at the middle
 * <p>
 * 7. Detecting a Cycle
 * - Uses fast and slow pointers (tortoise and hare algorithm)
 * - If pointers meet, a cycle exists
 * - Can be extended to find the start of the cycle
 * <p>
 * 8. Finding Intersection of Two Lists
 * - Either use a set to track nodes in the first list
 * - Or use a two-pointer technique with traversal switching
 * - Can also convert to cycle detection problem
 */
public class DoublePointerLinkedList {
}
