package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_a_MergeKSortedLists
 * <p>
 * LeetCode #23: Merge K Sorted Lists
 * <p>
 * This solution demonstrates how to use a priority queue to merge multiple sorted linked lists
 * efficiently. The priority queue helps us quickly identify the smallest element among
 * the heads of all linked lists.
 * <p>
 * Approach:
 * 1. Create a min-heap (priority queue) where elements are ordered based on node values
 * 2. Add the head of each list to the priority queue
 * 3. While the priority queue is not empty:
 * - Poll the smallest node from the queue
 * - Add it to the result list
 * - If the node has a next element, add that to the priority queue
 * <p>
 * Time Complexity: O(N log K) where N is the total number of nodes and K is the number of lists
 * Space Complexity: O(K) for the priority queue
 */

import java.util.*;

class ListNode {
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

public class _531_a_MergeKSortedLists {

    // Helper method to print a linked list
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Helper method to create a linked list from an array
    private static ListNode createList(int[] values) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        _531_a_MergeKSortedLists solution = new _531_a_MergeKSortedLists();

        // Test case from the problem
        ListNode[] lists = new ListNode[3];
        lists[0] = createList(new int[]{1, 4, 5});
        lists[1] = createList(new int[]{1, 3, 4});
        lists[2] = createList(new int[]{2, 6});

        System.out.println("Input lists:");
        for (ListNode list : lists) {
            printList(list);
        }

        ListNode merged = solution.mergeKLists(lists);

        System.out.println("Merged list:");
        printList(merged);
        // Expected output: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6 -> null
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Create a dummy node to build our result list
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // Create a min-heap priority queue
        // The comparator sorts nodes by their values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                lists.length, (a, b) -> a.val - b.val);

        // Add the head of each list to the priority queue
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        // Process the nodes in ascending order
        while (!minHeap.isEmpty()) {
            // Get the smallest node
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            // If there are more nodes in this list, add the next one to the queue
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        return dummy.next;
    }
}