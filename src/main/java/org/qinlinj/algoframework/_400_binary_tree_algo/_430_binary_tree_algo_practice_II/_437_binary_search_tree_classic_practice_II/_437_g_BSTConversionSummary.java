package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._437_binary_search_tree_classic_practice_II; /**
 * Summary of Binary Search Tree Conversion and Manipulation Techniques
 * <p>
 * This class provides a comprehensive overview of techniques for converting between
 * different data structures and BSTs, as well as manipulating BSTs for various purposes.
 * <p>
 * Key BST Conversion Patterns:
 * <p>
 * 1. Array/List to BST Conversion:
 * - Middle Element as Root: Select middle element for balanced structure
 * - Recursive Division: Split array into left/right halves for subtrees
 * - Inorder Property: Maintaining inorder traversal order during construction
 * <p>
 * 2. BST Balancing Techniques:
 * - Inorder Traversal to Array: Extract sorted elements
 * - Rebuild from Sorted Array: Construct balanced tree from sorted elements
 * - Reference-Preserving Methods: Techniques that maintain node references vs. rebuild
 * <p>
 * 3. BST Serialization/Deserialization:
 * - Traversal Order Choice: Preorder traversal as natural serialization order
 * - BST Property Utilization: Use value ranges to reduce space requirements
 * - Format Optimization: Strategies to minimize serialized representation size
 * <p>
 * 4. Accessing BST Elements:
 * - Iterator Pattern: Controlled traversal for on-demand element access
 * - Stack-Based Simulation: Non-recursive traversal implementation
 * - Lazy Evaluation: Process only necessary elements for efficiency
 * <p>
 * The implementations covered in this module demonstrate these techniques through
 * LeetCode problems 108, 109, 173, 449, 1305, and 1382.
 */

import java.util.*;

class _437_g_BSTConversionSummary {
    /**
     * Pattern 2: Converting Sorted Linked List to BST (Problem 109)
     * Approach using inorder simulation for O(n) time complexity
     */
    private ListNode globalHead;

    // Main method summarizing key concepts and techniques
    public static void main(String[] args) {
        System.out.println("Binary Search Tree Conversion and Manipulation Techniques");
        System.out.println("======================================================");

        System.out.println("\n1. Converting Between Data Structures and BSTs");
        System.out.println("   - Array to BST: Choose middle element as root");
        System.out.println("   - Linked List to BST: Count nodes or use inorder simulation");
        System.out.println("   - BST to Array: Use inorder traversal for sorted result");

        System.out.println("\n2. BST Balancing Strategies");
        System.out.println("   - Extract-and-Rebuild: Inorder traversal + array to BST");
        System.out.println("   - In-place Rotation: AVL/Red-Black tree algorithms (more complex)");
        System.out.println("   - Selection of Appropriate Strategy: Based on constraints");

        System.out.println("\n3. BST Serialization Optimization");
        System.out.println("   - BST Property Utilization: Avoid null markers");
        System.out.println("   - Traversal Order Selection: Preorder optimal for BST");
        System.out.println("   - Range-Based Deserialization: Efficient reconstruction");

        System.out.println("\n4. BST Element Access Patterns");
        System.out.println("   - Iterator Pattern: Controlled in-order traversal");
        System.out.println("   - On-Demand Processing: Lazy evaluation");
        System.out.println("   - Multi-Tree Operations: Combining multiple BSTs efficiently");

        System.out.println("\n5. Space-Time Tradeoffs in BST Operations");
        System.out.println("   - Conversion Space: O(n) for complete rebuild");
        System.out.println("   - Iterator Space: O(h) for stack-based traversal");
        System.out.println("   - Serialization Space: O(n) with optimizations possible");

        System.out.println("\nThese techniques demonstrate the versatility of BSTs as a data structure");
        System.out.println("and provide efficient solutions to a variety of algorithmic problems.");
    }

    /**
     * Pattern 1: Converting Sorted Array to BST (Problem 108)
     * Key insight: Choose middle element as root for balanced structure
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // Find middle element to be the root
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build left and right subtrees
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        // Count nodes
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }

        // Initialize global pointer
        globalHead = head;

        // Build BST using simulated inorder traversal
        return buildBSTFromList(0, count - 1);
    }

    private TreeNode buildBSTFromList(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        // Recursively build left subtree (simulating inorder traversal)
        TreeNode left = buildBSTFromList(start, mid - 1);

        // Process current node (inorder position)
        TreeNode root = new TreeNode(globalHead.val);
        root.left = left;

        // Move pointer to next list node
        globalHead = globalHead.next;

        // Recursively build right subtree
        root.right = buildBSTFromList(mid + 1, end);

        return root;
    }

    /**
     * Pattern 3: Balancing a BST (Problem 1382)
     * Two-step approach: Convert to sorted array, then rebuild balanced
     */
    public TreeNode balanceBST(TreeNode root) {
        // Step 1: Extract values using inorder traversal
        List<Integer> values = new ArrayList<>();
        inorderTraversal(root, values);

        // Step 2: Rebuild balanced BST
        return buildBalancedBST(values, 0, values.size() - 1);
    }

    private void inorderTraversal(TreeNode root, List<Integer> values) {
        if (root == null) return;
        inorderTraversal(root.left, values);
        values.add(root.val);
        inorderTraversal(root.right, values);
    }

    private TreeNode buildBalancedBST(List<Integer> values, int start, int end) {
        if (start > end) return null;

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(values.get(mid));

        root.left = buildBalancedBST(values, start, mid - 1);
        root.right = buildBalancedBST(values, mid + 1, end);

        return root;
    }

    /**
     * Pattern 4: Serializing and Deserializing BST (Problem 449)
     * Uses preorder traversal and BST properties for efficient representation
     */
    // Serialize a BST to a string
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) return;

        // Preorder traversal
        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Deserialize string back to BST
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;

        String[] values = data.split(",");
        Queue<Integer> queue = new LinkedList<>();

        for (String value : values) {
            if (!value.isEmpty()) {
                queue.offer(Integer.parseInt(value));
            }
        }

        return deserializeHelper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode deserializeHelper(Queue<Integer> queue, int min, int max) {
        if (queue.isEmpty()) return null;

        int value = queue.peek();
        if (value < min || value > max) return null;

        queue.poll(); // Remove the value we're using
        TreeNode root = new TreeNode(value);

        root.left = deserializeHelper(queue, min, value);
        root.right = deserializeHelper(queue, value, max);

        return root;
    }

    /**
     * Pattern 6: Merging Multiple BSTs (Problem 1305)
     * Using iterators to merge on-the-fly
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // Create iterators with peek capability
        BSTIteratorWithPeek it1 = new BSTIteratorWithPeek(root1);
        BSTIteratorWithPeek it2 = new BSTIteratorWithPeek(root2);

        List<Integer> result = new ArrayList<>();

        // Merge sorted sequences
        while (it1.hasNext() && it2.hasNext()) {
            if (it1.peek() <= it2.peek()) {
                result.add(it1.next());
            } else {
                result.add(it2.next());
            }
        }

        // Add remaining elements
        while (it1.hasNext()) result.add(it1.next());
        while (it2.hasNext()) result.add(it2.next());

        return result;
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

    /**
     * Pattern 5: BST Iterator Implementation (Problem 173)
     * Controlled inorder traversal using stack
     */
    public class BSTIterator {
        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            pushLeftBranch(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode node = stack.pop();
            pushLeftBranch(node.right);
            return node.val;
        }

        private void pushLeftBranch(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    // Enhanced iterator with peek functionality
    private class BSTIteratorWithPeek {
        private Stack<TreeNode> stack;

        public BSTIteratorWithPeek(TreeNode root) {
            stack = new Stack<>();
            pushLeftBranch(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode node = stack.pop();
            pushLeftBranch(node.right);
            return node.val;
        }

        public int peek() {
            return stack.isEmpty() ? Integer.MAX_VALUE : stack.peek().val;
        }

        private void pushLeftBranch(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }
}