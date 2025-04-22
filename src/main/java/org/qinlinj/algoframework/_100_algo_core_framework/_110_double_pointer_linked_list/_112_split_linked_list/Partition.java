package org.qinlinj.algoframework._100_algo_core_framework._110_double_pointer_linked_list._112_split_linked_list;

import org.qinlinj.leetcode.editor.common.ListNode;

public class Partition {
    /**
     * Partitions a linked list around a given value x
     * <p>
     * Algorithm Strategy: Two-List Partitioning
     * <p>
     * Key Concepts:
     * - Create two separate lists:
     * 1. List for nodes smaller than x
     * 2. List for nodes greater than or equal to x
     * - Maintain original relative order within each partition
     * - Combine the two lists at the end
     * <p>
     * Visualization:
     * Input: 1 -> 4 -> 3 -> 2 -> 5 -> 2, x = 3
     * <p>
     * Smaller List (< 3):
     * 1 -> 2 -> 2
     * <p>
     * Larger/Equal List (>= 3):
     * 4 -> 3 -> 5
     * <p>
     * Final Merged List:
     * 1 -> 2 -> 2 -> 4 -> 3 -> 5
     * <p>
     * Algorithm Steps:
     * 1. Use dummy nodes to simplify list construction
     * 2. Traverse original list
     * 3. Categorize nodes into two lists
     * 4. Disconnect nodes from original list
     * 5. Merge lists
     * <p>
     * Advantages:
     * - Single-pass solution
     * - Preserves relative order of nodes
     * - In-place partitioning
     * <p>
     * Time Complexity: O(n)
     * - Single pass through the list
     * <p>
     * Space Complexity: O(1)
     * - Reuses existing nodes
     * - Only creates two dummy nodes
     *
     * @param head Head of the original linked list
     * @param x    Partition value
     * @return Head of the partitioned list
     */
    public ListNode partition(ListNode head, int x) {
        // Create dummy heads for two separate lists
        // First list: nodes smaller than x
        // Second list: nodes greater than or equal to x
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);

        // Pointers to track the end of each list
        ListNode p1 = dummy1, p2 = dummy2;

        // Pointer to traverse the original list
        ListNode p = head;

        // Iterate through the original list
        while (p != null) {
            // Categorize nodes based on their value
            if (p.val >= x) {
                // Add to the "larger" list
                p2.next = p;
                p2 = p2.next;
            } else {
                // Add to the "smaller" list
                p1.next = p;
                p1 = p1.next;
            }

            // Move to next node and disconnect current node
            // This prevents cycles in the original list
            ListNode temp = p.next;
            p.next = null;  // Crucial step to break original list connections
            p = temp;
        }

        // Connect the two lists
        // Smaller list points to the start of larger list
        p1.next = dummy2.next;

        // Return the head of the partitioned list
        return dummy1.next;
    }
}
