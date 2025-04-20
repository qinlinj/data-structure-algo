package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._182_understanding_traversals;

/**
 * DEEPER UNDERSTANDING OF BINARY TREE TRAVERSALS
 * ==============================================
 * <p>
 * Beyond the Textbook Understanding:
 * Pre-order, in-order, and post-order are not merely three differently ordered lists.
 * They represent three SPECIAL TIMING POINTS during the traversal of each node:
 * <p>
 * - Pre-order position: Code executes IMMEDIATELY UPON ENTERING a node
 * - In-order position: Code executes AFTER traversing the left subtree but BEFORE traversing the right subtree
 * - Post-order position: Code executes JUST BEFORE LEAVING a node (after both subtrees are traversed)
 * <p>
 * Key Insights:
 * 1. Each node has its unique pre-order, in-order, and post-order positions
 * 2. N-ary trees don't have in-order positions because nodes can have multiple children
 * and don't have a unique moment of "switching from left to right"
 * 3. Binary tree algorithms boil down to injecting clever logic at these positions
 * 4. Post-order has special significance as it processes children before parents
 * <p>
 * The recursive traversal framework handles the movement between nodes;
 * you only need to decide what each individual node should do at each position.
 */
public class BinaryTreeTraversalInsights {

    // =====================================================
    // MAIN METHOD FOR TESTING
    // =====================================================

    public static void main(String[] args) {
        BinaryTreeTraversalInsights insights = new BinaryTreeTraversalInsights();

        // Create a binary tree for testing
        //      1
        //     / \
        //    2   3
        //   / \   \
        //  4   5   6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("=== TRAVERSAL DEMONSTRATION ===");
        System.out.println("\n1. Demonstrating traversal positions on node 1:");
        insights.unifiedTraversal(root);

        System.out.println("\n2. Traditional traversal results:");
        StringBuilder preorder = new StringBuilder();
        StringBuilder inorder = new StringBuilder();
        StringBuilder postorder = new StringBuilder();

        insights.preorderTraversal(root, preorder);
        insights.inorderTraversal(root, inorder);
        insights.postorderTraversal(root, postorder);

        System.out.println("Preorder traversal: " + preorder);
        System.out.println("Inorder traversal: " + inorder);
        System.out.println("Postorder traversal: " + postorder);

        System.out.println("\n3. Linked list reverse printing (post-order application):");
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.print("Original list: 1 2 3 4 5, Reversed: ");
        insights.printLinkedListReverse(head);

        System.out.println("\n\n4. Tree height calculation (post-order application):");
        int height = insights.calculateHeight(root);
        System.out.println("Tree height: " + height);

        System.out.println("\n5. Node depth calculation (pre-order application):");
        insights.computeDepth(root, 0, new int[100]);

        // Create an N-ary tree
        NaryTreeNode naryRoot = new NaryTreeNode(1);
        naryRoot.children.add(new NaryTreeNode(2));
        naryRoot.children.add(new NaryTreeNode(3));
        naryRoot.children.add(new NaryTreeNode(4));
        naryRoot.children.get(0).children.add(new NaryTreeNode(5));
        naryRoot.children.get(0).children.add(new NaryTreeNode(6));

        System.out.println("\n6. N-ary tree traversal (demonstrates absence of in-order):");
        insights.traverseNaryTree(naryRoot);
    }

    // =====================================================
    // COMPARING TRAVERSAL STRUCTURES
    // =====================================================

    // Iterative array traversal
    public void traverseArrayIterative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Process arr[i]
            System.out.println("Processing element: " + arr[i]);
        }
    }

    // Recursive array traversal with pre and post positions
    public void traverseArrayRecursive(int[] arr, int i) {
        if (i == arr.length) {
            return;
        }

        // Pre-order position - before recursion
        System.out.println("Pre-order: About to process " + arr[i]);

        traverseArrayRecursive(arr, i + 1);

        // Post-order position - after recursion
        System.out.println("Post-order: Finished processing " + arr[i]);
    }

    // Iterative linked list traversal
    public void traverseLinkedListIterative(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            // Process p
            System.out.println("Processing node: " + p.val);
        }
    }

    // =====================================================
    // PRACTICAL APPLICATION OF TRAVERSAL POSITIONS
    // =====================================================

    // Recursive linked list traversal with pre and post positions
    public void traverseLinkedListRecursive(ListNode head) {
        if (head == null) {
            return;
        }

        // Pre-order position - before recursion
        System.out.println("Pre-order: About to process " + head.val);

        traverseLinkedListRecursive(head.next);

        // Post-order position - after recursion
        System.out.println("Post-order: Finished processing " + head.val);
    }

    // Example 1: Printing a linked list in reverse using post-order position
    public void printLinkedListReverse(ListNode head) {
        if (head == null) {
            return;
        }

        // Recurse first (go to the end of the list)
        printLinkedListReverse(head.next);

        // Post-order position - print after visiting all descendants
        System.out.print(head.val + " ");
    }

    // Example 2: Understanding binary tree traversals
    // The three classic traversal orders as we typically understand them
    public void preorderTraversal(TreeNode root, StringBuilder result) {
        if (root == null) return;

        // Pre-order position - visit node first
        result.append(root.val).append(" ");

        preorderTraversal(root.left, result);
        preorderTraversal(root.right, result);
    }

    public void inorderTraversal(TreeNode root, StringBuilder result) {
        if (root == null) return;

        inorderTraversal(root.left, result);

        // In-order position - visit node between subtrees
        result.append(root.val).append(" ");

        inorderTraversal(root.right, result);
    }

    // =====================================================
    // UNIFIED TRAVERSAL DEMONSTRATING ALL THREE POSITIONS
    // =====================================================

    public void postorderTraversal(TreeNode root, StringBuilder result) {
        if (root == null) return;

        postorderTraversal(root.left, result);
        postorderTraversal(root.right, result);

        // Post-order position - visit node after subtrees
        result.append(root.val).append(" ");
    }

    // =====================================================
    // PRACTICAL EXAMPLES OF PRE/IN/POST-ORDER USAGE
    // =====================================================

    // This single function demonstrates all three positions in action
    public void unifiedTraversal(TreeNode root) {
        if (root == null) return;

        // Pre-order position - when we first encounter the node
        System.out.println("Pre-order: " + root.val);

        unifiedTraversal(root.left);

        // In-order position - after left subtree, before right subtree
        System.out.println("In-order: " + root.val);

        unifiedTraversal(root.right);

        // Post-order position - after both subtrees are processed
        System.out.println("Post-order: " + root.val);
    }

    // Example 1: Calculate tree height - uses post-order position
    public int calculateHeight(TreeNode root) {
        if (root == null) return 0;

        // Process the subtrees first
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        // Post-order position - ideal for bottom-up calculations
        // We combine results from subtrees to calculate current node's result
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Example 2: Precompute tree depth for each node - uses pre-order position
    public void computeDepth(TreeNode root, int depth, int[] depths) {
        if (root == null) return;

        // Pre-order position - ideal for top-down information passing
        // We know the depth as soon as we visit the node
        System.out.println("Node " + root.val + " is at depth " + depth);

        computeDepth(root.left, depth + 1, depths);
        computeDepth(root.right, depth + 1, depths);
    }

    // Example 3: Binary Search Tree validation - uses in-order position
    public boolean validateBST(TreeNode root) {
        return validateBSTHelper(root, null, null);
    }

    // =====================================================
    // N-ARY TREE TRAVERSAL (NO IN-ORDER POSITION!)
    // =====================================================

    private boolean validateBSTHelper(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        // Validate current node's constraints
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }

        // In-order traversal ensures we process nodes in ascending order
        // This is why in-order traversal is special for BSTs
        return validateBSTHelper(root.left, min, root.val) &&
                validateBSTHelper(root.right, root.val, max);
    }

    // N-ary tree traversal has pre-order and post-order, but no in-order
    public void traverseNaryTree(NaryTreeNode root) {
        if (root == null) return;

        // Pre-order position
        System.out.println("Pre-order (N-ary): " + root.val);

        // Process all children
        for (NaryTreeNode child : root.children) {
            traverseNaryTree(child);
        }

        // Post-order position
        System.out.println("Post-order (N-ary): " + root.val);

        // Note: There is NO unique "in-order" position for N-ary trees
        // because there's no single point where we've finished exactly half the children
    }

    // Definition for an N-ary tree node
    public static class NaryTreeNode {
        int val;
        java.util.List<NaryTreeNode> children;

        NaryTreeNode(int x) {
            val = x;
            children = new java.util.ArrayList<>();
        }
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Definition for a singly linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
