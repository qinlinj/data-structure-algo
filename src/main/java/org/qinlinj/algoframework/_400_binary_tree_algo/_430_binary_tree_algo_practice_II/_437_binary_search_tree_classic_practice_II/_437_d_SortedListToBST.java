package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._437_binary_search_tree_classic_practice_II;

/**
 * Problem 109: Convert Sorted List to Binary Search Tree (Medium)
 * <p>
 * Problem Description:
 * Given the head of a singly linked list where elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 * <p>
 * Key Concepts:
 * - Linked List vs Array: No random access in linked list, requiring different approaches
 * - Finding Middle Element: Using slow/fast pointers or counting nodes
 * - Inorder Simulation: Leveraging the relationship between inorder traversal and sorted elements
 * - Recursion with Global Pointer: An optimized approach avoiding finding middle each time
 * <p>
 * Three Approaches:
 * 1. Convert to array first (simplest but uses O(n) extra space)
 * 2. Find middle node recursively (O(n log n) time complexity)
 * 3. Simulate inorder traversal (O(n) time complexity, optimal)
 * <p>
 * Time Complexity: O(n) for the optimal solution
 * Space Complexity: O(log n) for recursion stack in balanced tree
 */

class _437_d_SortedListToBST {
    /**
     * Approach 3: Simulate inorder traversal (optimal)
     * Maintains a global pointer to the list and builds tree without finding middle
     * O(n) time complexity
     */
    private ListNode globalHead;

    // Main method for testing
    public static void main(String[] args) {
        _437_d_SortedListToBST solution = new _437_d_SortedListToBST();

        // Example 1: [-10,-3,0,5,9]
        int[] values1 = {-10, -3, 0, 5, 9};
        ListNode list1 = solution.createLinkedList(values1);

        System.out.println("Example 1 - Original Linked List:");
        solution.printLinkedList(list1);

        // Test all three approaches
        System.out.println("\nApproach 1 (Array Conversion):");
        TreeNode root1A = solution.sortedListToBST_Array(list1);
        solution.printTree(root1A, "", false);
        System.out.println("Is balanced: " + solution.isBalanced(root1A));
        System.out.println("Inorder traversal (should match original list):");
        solution.printInorder(root1A);
        System.out.println();

        System.out.println("\nApproach 2 (Find Middle):");
        // Need to recreate the list since Approach 2 modifies it
        ListNode list1Copy = solution.createLinkedList(values1);
        TreeNode root1B = solution.sortedListToBST_FindMiddle(list1Copy);
        solution.printTree(root1B, "", false);
        System.out.println("Is balanced: " + solution.isBalanced(root1B));
        System.out.println("Inorder traversal (should match original list):");
        solution.printInorder(root1B);
        System.out.println();

        System.out.println("\nApproach 3 (Inorder Simulation):");
        // Need to recreate the list again
        ListNode list1Copy2 = solution.createLinkedList(values1);
        TreeNode root1C = solution.sortedListToBST(list1Copy2);
        solution.printTree(root1C, "", false);
        System.out.println("Is balanced: " + solution.isBalanced(root1C));
        System.out.println("Inorder traversal (should match original list):");
        solution.printInorder(root1C);
        System.out.println();

        // Example 2: [1,3]
        int[] values2 = {1, 3};
        ListNode list2 = solution.createLinkedList(values2);

        System.out.println("\n\nExample 2 - Original Linked List:");
        solution.printLinkedList(list2);

        TreeNode root2 = solution.sortedListToBST(list2);

        System.out.println("\nBalanced BST:");
        solution.printTree(root2, "", false);
        System.out.println("Is balanced: " + solution.isBalanced(root2));
        System.out.println("Inorder traversal (should match original list):");
        solution.printInorder(root2);
        System.out.println();
    }

    /**
     * Approach 1: Convert list to array, then build BST
     * Simple but uses O(n) extra space
     */
    public TreeNode sortedListToBST_Array(ListNode head) {
        // Count the number of nodes in the linked list
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }

        // Convert linked list to array
        int[] nums = new int[count];
        current = head;
        for (int i = 0; i < count; i++) {
            nums[i] = current.val;
            current = current.next;
        }

        // Build BST from sorted array (similar to Problem 108)
        return buildBSTFromArray(nums, 0, count - 1);
    }

    /**
     * Helper method to build BST from sorted array
     */
    private TreeNode buildBSTFromArray(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBSTFromArray(nums, left, mid - 1);
        root.right = buildBSTFromArray(nums, mid + 1, right);

        return root;
    }

    /**
     * Approach 2: Find middle element recursively
     * O(n log n) time complexity due to repeatedly finding middle
     */
    public TreeNode sortedListToBST_FindMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        // Find the middle node using slow and fast pointers
        ListNode prev = null;  // Track the node before middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        // 'slow' is now the middle node, 'prev' is the node before it

        // Disconnect the list at the middle
        prev.next = null;

        // Create root node from middle value
        TreeNode root = new TreeNode(slow.val);

        // Recursively build left subtree from the first half
        root.left = sortedListToBST_FindMiddle(head);

        // Recursively build right subtree from the second half
        root.right = sortedListToBST_FindMiddle(slow.next);

        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        // Count the number of nodes in the linked list
        int n = 0;
        ListNode current = head;
        while (current != null) {
            n++;
            current = current.next;
        }

        // Initialize global pointer to head
        this.globalHead = head;

        // Build BST by simulating inorder traversal
        return inorderBuildBST(0, n - 1);
    }

    /**
     * Helper method for inorder BST construction
     * The key insight is that inorder traversal of BST matches the order of sorted list
     */
    private TreeNode inorderBuildBST(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        // Recursively build left subtree
        TreeNode left = inorderBuildBST(start, mid - 1);

        // Process current node (inorder position)
        // Use the global head pointer to get the next value
        TreeNode root = new TreeNode(globalHead.val);
        root.left = left;

        // Move global pointer to next list node
        globalHead = globalHead.next;

        // Recursively build right subtree
        root.right = inorderBuildBST(mid + 1, end);

        return root;
    }

    /**
     * Alternate implementation using left and right boundary approach
     * Useful when list structure can't be modified
     */
    public TreeNode sortedListToBST_Boundary(ListNode head) {
        return buildBST(head, null);
    }

    /**
     * Helper method for boundary-based approach
     * Builds BST from a linked list segment defined by [start, end)
     * (end is exclusive, like a half-open interval)
     */
    private TreeNode buildBST(ListNode start, ListNode end) {
        // Empty segment
        if (start == end) {
            return null;
        }

        // Find the middle node using slow/fast pointers
        ListNode slow = start;
        ListNode fast = start;

        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Create root node from middle value
        TreeNode root = new TreeNode(slow.val);

        // Recursively build left and right subtrees
        root.left = buildBST(start, slow);
        root.right = buildBST(slow.next, end);

        return root;
    }

    /**
     * Helper method to print the inorder traversal of the tree
     * Inorder traversal of a BST should match the original sorted list
     */
    private void printInorder(TreeNode root) {
        if (root == null) return;

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    /**
     * Helper method to print tree structure
     */
    private void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "null");
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);

        printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
        printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    /**
     * Helper method to check if a tree is height-balanced
     */
    private boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * Helper method to check height and balance of a tree
     */
    private int checkHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Helper method to create a linked list from an array
     */
    private ListNode createLinkedList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
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
    private void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Definition for singly-linked list
    public class ListNode {
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