package org.qinlinj.algoframework._1_core_framework._1_double_pointer_linked_list._1_merge_k_sorted_lists;

import java.util.*;
import org.qinlinj.leetcode.editor.common.ListNode;

public class MergeKLists {
    /**
     * Merges K sorted linked lists into a single sorted linked list
     * <p>
     * Algorithm Strategy: Priority Queue (Min-Heap) Approach
     * <p>
     * Key Concepts:
     * - Use a min-heap to efficiently select the smallest element
     * - Continuously poll the smallest element from the queue
     * - Add next element from the list to maintain heap property
     * <p>
     * Visualization:
     * Input Lists:
     * List 1: 1 -> 4 -> 5
     * List 2: 1 -> 3 -> 4
     * List 3: 2 -> 6
     * <p>
     * Merge Process:
     * 1. Initial Heap: [1(List1), 1(List2), 2(List3)]
     * 2. Poll 1 from List1
     * 3. Add next from List1 (4)
     * 4. Continue until all lists are merged
     * <p>
     * Final Output:
     * 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
     * <p>
     * Advantages:
     * - Efficient for merging multiple sorted lists
     * - Handles lists of different lengths
     * - Minimizes comparisons
     * <p>
     * Time Complexity: O(N log k)
     * - N: Total number of nodes
     * - k: Number of linked lists
     * - log k: Heap operations
     * <p>
     * Space Complexity: O(k)
     * - Heap stores at most k elements at a time
     *
     * @param lists Array of sorted linked list heads
     * @return Merged sorted linked list
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // Handle empty input array
        if (lists.length == 0) return null;

        // Create dummy head to simplify list construction
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        // Create a min-heap (priority queue)
        // Custom comparator sorts nodes by their values
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length,
                // Comparator to order nodes by ascending value
                (a, b) -> (a.val - b.val)
        );

        // Add the head of each non-empty list to the priority queue
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        // Merge lists by continuously polling smallest element
        while (!pq.isEmpty()) {
            // Get the node with the smallest value
            ListNode node = pq.poll();

            // Connect to the result list
            p.next = node;

            // If the current list has more nodes, add next node to heap
            if (node.next != null) {
                pq.add(node.next);
            }

            // Move result list pointer forward
            p = p.next;
        }

        // Return merged list (skip dummy head)
        return dummy.next;
    }
}
