package org.qinlinj.algoframework._200_other_algo_framework._220_divide_and_conquer_algo_framework._222_ineffective_and_effective_divide_and_conquer;

/**
 * Effective vs. Ineffective Divide and Conquer Strategies
 * <p>
 * This class demonstrates when divide and conquer strategies are effective
 * and when they are ineffective by analyzing different implementations.
 * <p>
 * Key Points:
 * <p>
 * 1. Ineffective Divide and Conquer:
 * - Many iterative algorithms can be rewritten using recursion and divide and conquer
 * - However, this doesn't always improve efficiency and may actually increase space complexity
 * - Example: Array sum calculation doesn't benefit from divide and conquer approach
 * - When recursive node time complexity is independent of tree depth (O(1)),
 * divide and conquer may not provide benefits
 * <p>
 * 2. Effective Divide and Conquer:
 * - Divide and conquer is effective when it reduces redundant computations
 * - Using binary splitting to create a balanced recursive tree with O(log n) height can optimize algorithms
 * - Example: Merging k sorted lists benefits from divide and conquer approach
 * - When recursive node time complexity is related to tree depth, divide and conquer can improve efficiency
 * <p>
 * 3. Key Analysis Method:
 * - Visualize the algorithm as a recursive tree
 * - If the time complexity at each node relates to tree depth, divide and conquer can help balance the tree
 * - A balanced tree with height O(log n) is more efficient than an unbalanced tree with height O(n)
 * <p>
 * 4. Time and Space Complexity Analysis:
 * - Ineffective approaches often maintain the same time complexity but increase space complexity
 * - Effective divide and conquer can reduce time complexity (e.g., from O(N²) to O(N log N))
 * - Space complexity relates to recursive stack depth (tree height)
 */
public class EffectiveDivideAndConquer {

    /**
     * SECTION 1: INEFFECTIVE DIVIDE AND CONQUER EXAMPLES
     */

    /**
     * Example 1: Iterative sum calculation
     * Simple O(n) time and O(1) space approach
     */
    public static int getSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    /**
     * Example 2: Recursive sum calculation (linear recursion)
     * This is an ineffective divide and conquer approach
     * O(n) time and O(n) space due to recursion stack
     * The recursive tree degenerates into a linked list with O(n) height
     */
    public static int getSum2(int[] nums, int start) {
        // Base case
        if (start == nums.length) {
            return 0;
        }
        // Breaking problem into first element + sum of rest
        return nums[start] + getSum2(nums, start + 1);
    }

    /**
     * Example 3: Recursive sum with binary splitting
     * Still ineffective for sum calculation but demonstrates binary splitting
     * O(n) time and O(log n) space
     * Creates a balanced binary tree with O(log n) height
     */
    public static int getSum3(int[] nums, int start, int end) {
        // Base case
        if (start == end) {
            return nums[start];
        }

        int mid = start + (end - start) / 2;
        // Calculate sum of left half
        int leftSum = getSum3(nums, start, mid);
        // Calculate sum of right half
        int rightSum = getSum3(nums, mid + 1, end);

        // Combine results
        return leftSum + rightSum;
    }

    /**
     * SECTION 2: EFFECTIVE DIVIDE AND CONQUER EXAMPLES
     */

    /**
     * Merge two sorted linked lists
     * O(l1 + l2) time complexity where l1 and l2 are lengths of the two lists
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Dummy head
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {
            // Compare p1 and p2, connect smaller value to p
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            // Advance p pointer
            p = p.next;
        }

        // Connect remaining nodes
        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;
    }

    /**
     * Ineffective approach to merge k sorted lists
     * O(N²/k) time complexity where N is total number of nodes
     * Each list gets processed multiple times
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        // Merge all lists into the first one
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    /**
     * Ineffective recursive approach (linear recursion)
     * Similar to the previous approach, but using recursion
     * The recursive tree degenerates into a linked list with O(k) height
     */
    public static ListNode mergeKLists2(ListNode[] lists, int start) {
        if (start >= lists.length) {
            return null;
        }
        if (start == lists.length - 1) {
            return lists[start];
        }

        // Merge lists[start+1...] first
        ListNode subProblem = mergeKLists2(lists, start + 1);

        // Then merge lists[start] with the result
        return mergeTwoLists(lists[start], subProblem);
    }

    /**
     * Effective divide and conquer approach for merging k sorted lists
     * O(N log k) time complexity where N is total number of nodes and k is number of lists
     * Creates a balanced binary recursive tree with O(log k) height
     * Each list is processed O(log k) times
     */
    public static ListNode mergeKLists3(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return lists[start];
        }

        // Divide the lists into two halves
        int mid = start + (end - start) / 2;

        // Merge left half lists[start...mid]
        ListNode left = mergeKLists3(lists, start, mid);

        // Merge right half lists[mid+1...end]
        ListNode right = mergeKLists3(lists, mid + 1, end);

        // Combine results
        return mergeTwoLists(left, right);
    }

    /**
     * Main method with examples
     */
    public static void main(String[] args) {
        // Example for array sum calculations
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};

        System.out.println("Iterative sum: " + getSum(nums));
        System.out.println("Recursive linear sum: " + getSum2(nums, 0));
        System.out.println("Recursive binary sum: " + getSum3(nums, 0, nums.length - 1));

        // Example for merging k sorted lists
        ListNode list1 = createList(new int[]{1, 4, 5});
        ListNode list2 = createList(new int[]{1, 3, 4});
        ListNode list3 = createList(new int[]{2, 6});

        ListNode[] lists = new ListNode[]{list1, list2, list3};

        System.out.println("\nMerging k sorted lists:");
        System.out.println("Original lists:");
        for (ListNode list : lists) {
            printList(list);
        }

        System.out.println("\nIneffective approach result:");
        ListNode result1 = mergeKLists(copyLists(lists));
        printList(result1);

        System.out.println("\nIneffective recursive approach result:");
        ListNode result2 = mergeKLists2(copyLists(lists), 0);
        printList(result2);

        System.out.println("\nEffective divide and conquer approach result:");
        ListNode result3 = mergeKLists3(copyLists(lists), 0, lists.length - 1);
        printList(result3);

        System.out.println("\nTime Complexity Analysis:");
        System.out.println("- Iterative sum: O(n) time, O(1) space");
        System.out.println("- Recursive linear sum: O(n) time, O(n) space");
        System.out.println("- Recursive binary sum: O(n) time, O(log n) space");
        System.out.println("- Ineffective merge k lists: O(N²/k) time");
        System.out.println("- Effective divide and conquer merge k lists: O(N log k) time, O(log k) space");
    }

    /**
     * Utility method to create a linked list from an array
     */
    private static ListNode createList(int[] values) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        for (int val : values) {
            p.next = new ListNode(val);
            p = p.next;
        }

        return dummy.next;
    }

    /**
     * Utility method to print a linked list
     */
    private static void printList(ListNode head) {
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " -> ");
            p = p.next;
        }
        System.out.println("null");
    }

    /**
     * Utility method to create a deep copy of the lists array
     */
    private static ListNode[] copyLists(ListNode[] lists) {
        ListNode[] copy = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            copy[i] = copyList(lists[i]);
        }
        return copy;
    }

    /**
     * Utility method to create a deep copy of a linked list
     */
    private static ListNode copyList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newHead = new ListNode(head.val);
        ListNode p = head.next;
        ListNode q = newHead;

        while (p != null) {
            q.next = new ListNode(p.val);
            p = p.next;
            q = q.next;
        }

        return newHead;
    }

    /**
     * Definition for singly-linked list node
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
