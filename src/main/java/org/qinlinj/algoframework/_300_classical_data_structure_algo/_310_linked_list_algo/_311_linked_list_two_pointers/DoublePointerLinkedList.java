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
    // Main method for testing
    public static void main(String[] args) {
        DoublePointerLinkedList solution = new DoublePointerLinkedList();

        // Example: Create two sorted lists
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        // Test merge two lists
        System.out.println("Merged list: ");
        printList(solution.mergeTwoLists(l1, l2));

        // Add more test cases for other methods as needed
    }

    // Helper method to print a linked list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    /**
     * 1. Merging two sorted linked lists
     * Time Complexity: O(n+m) where n and m are the lengths of the lists
     * Space Complexity: O(1) as we're reusing the existing nodes
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Create a dummy node to simplify edge cases
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        // Traverse both lists and attach smaller node to result
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        // Attach remaining nodes (if any)
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;

        return dummy.next;
    }

    /**
     * 2. Partitioning a linked list around value x
     * All nodes less than x come before nodes greater than or equal to x
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode partition(ListNode head, int x) {
        // Create two dummy nodes for two separate lists
        ListNode dummyLess = new ListNode(-1);
        ListNode dummyGreater = new ListNode(-1);
        ListNode less = dummyLess;
        ListNode greater = dummyGreater;

        // Traverse original list and distribute nodes
        while (head != null) {
            // Save next pointer before detaching
            ListNode next = head.next;
            head.next = null;  // Detach node from original list

            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }

            head = next;
        }

        // Connect the two lists
        less.next = dummyGreater.next;

        return dummyLess.next;
    }

    /**
     * 3. Merging k sorted linked lists using a priority queue
     * Time Complexity: O(N log k) where N is total number of nodes, k is number of lists
     * Space Complexity: O(k) for the priority queue
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Create a min heap comparing nodes by their values
        java.util.PriorityQueue<ListNode> pq = new java.util.PriorityQueue<>(
                (a, b) -> a.val - b.val
        );

        // Add the head of each list to the priority queue
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        // Process nodes from priority queue
        while (!pq.isEmpty()) {
            // Get the smallest node
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;

            // Add the next node from the same list
            if (node.next != null) {
                pq.add(node.next);
            }
        }

        return dummy.next;
    }

    /**
     * 4. Finding the kth node from the end
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode findKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        // Move fast pointer k steps ahead
        for (int i = 0; i < k; i++) {
            if (fast == null) return null; // List is shorter than k
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // Slow is now at the kth node from the end
    }

    /**
     * 5. Removing the kth node from the end
     * Uses findKthFromEnd technique with a dummy node
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // Find the node before the one to be removed
        ListNode prev = findKthFromEnd(dummy, n + 1);

        // Remove the target node
        if (prev != null && prev.next != null) {
            prev.next = prev.next.next;
        }

        return dummy.next;
    }

    /**
     * 6. Finding the middle node of a linked list
     * For even length lists, returns the second middle node
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Fast moves twice as fast as slow
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // Slow is at the middle when fast reaches the end
    }

    /**
     * 7. Detecting a cycle in a linked list
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If pointers meet, there is a cycle
            if (slow == fast) {
                return true;
            }
        }

        return false; // Fast reached the end, no cycle
    }

    /**
     * 8. Finding the start node of a cycle
     * First detects if there's a cycle, then finds the entrance
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode detectCycleStart(ListNode head) {
        if (head == null) return null;

        // First check if there's a cycle and find meeting point
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) return null;

        // Reset slow to head and move both pointers at same speed
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // Meeting point is the cycle start
    }

    /**
     * 9. Finding intersection of two linked lists
     * Method 1: Using the two-pointer technique
     * Time Complexity: O(m+n)
     * Space Complexity: O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pA = headA;
        ListNode pB = headB;

        // If the pointers haven't met, continue traversing
        while (pA != pB) {
            // When reaching the end of one list, switch to the other
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA; // Either intersection point or null if no intersection
    }

    /**
     * 10. Alternative method for finding intersection
     * By calculating lengths first
     * Time Complexity: O(m+n)
     * Space Complexity: O(1)
     */
    public ListNode getIntersectionNodeAlt(ListNode headA, ListNode headB) {
        // Calculate lengths of both lists
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        // Adjust starting positions to ensure same distance to potential intersection
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }

        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }

        // Move both pointers until they meet or reach the end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA; // Either intersection point or null
    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    // Definition for a singly linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
