package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._436_binary_search_tree_classic_practice_I; /**
 * Summary of Binary Search Tree Techniques and Applications
 * <p>
 * This class provides a comprehensive overview of common patterns and techniques
 * for working with Binary Search Trees (BSTs) in various problem scenarios.
 * <p>
 * Key BST Properties:
 * <p>
 * 1. Structural Properties:
 * - For every node: all values in the left subtree < node value < all values in the right subtree
 * - The inorder traversal of a BST produces sorted values in ascending order
 * - Height is O(log n) for balanced BSTs, potentially O(n) for skewed trees
 * <p>
 * 2. Common Problem-Solving Patterns:
 * - BST as Sorted Array: Treat BST as a sorted array using inorder traversal
 * - Range-Based Operations: Use BST property to define value bounds for each node
 * - Reconstruction: Build BST from traversal sequences using recursive approaches
 * - Violation Detection: Find nodes that violate the BST property
 * <p>
 * 3. Traversal Techniques:
 * - Inorder Traversal: For accessing elements in sorted order
 * - Traversal with State: Maintaining additional state during traversal (prev node, count, etc.)
 * - Two-Pointer Equivalent: Implementing sorted array algorithms on BSTs
 * <p>
 * 4. BST Modifications:
 * - Node Deletion: Removing nodes while maintaining BST property
 * - Tree Trimming: Removing subtrees based on value ranges
 * - Value Swapping: Repairing BST by swapping node values
 * <p>
 * This summary covers techniques from the following LeetCode problems:
 * - 99: Recover Binary Search Tree
 * - 669: Trim a Binary Search Tree
 * - 671: Second Minimum Node in a Binary Tree
 * - 501: Find Mode in Binary Search Tree
 * - 530/783: Minimum Absolute Difference in BST
 * - 653: Two Sum IV - Input is a BST
 * - 1008: Construct Binary Search Tree from Preorder Traversal
 */

import java.util.*;

class _436_h_BSTSummary {
    // Main method with a summary of BST problem-solving strategies
    public static void main(String[] args) {
        System.out.println("Binary Search Tree (BST) Problem-Solving Techniques Summary");
        System.out.println("-----------------------------------------------------------");

        System.out.println("\n1. Key BST Properties to Leverage:");
        System.out.println("   - Inorder traversal produces sorted values");
        System.out.println("   - Left subtree values < node value < right subtree values");
        System.out.println("   - Recursive structure allows divide-and-conquer approaches");

        System.out.println("\n2. Problem Types and Strategies:");
        System.out.println("   a) Validation Problems (e.g., is it a valid BST?)");
        System.out.println("      Strategy: Use range-based validation during traversal");

        System.out.println("   b) Search Problems (e.g., find nodes with specific properties)");
        System.out.println("      Strategy: Use properties to prune search or convert to sorted array");

        System.out.println("   c) Modification Problems (e.g., trimming, swapping nodes)");
        System.out.println("      Strategy: Use range-based recursion for efficient subtree operations");

        System.out.println("   d) Construction Problems (e.g., build from traversal arrays)");
        System.out.println("      Strategy: Use recursive approaches with clear BST property boundaries");

        System.out.println("\n3. Common Pitfalls to Avoid:");
        System.out.println("   - Forgetting to handle duplicates (if allowed)");
        System.out.println("   - Not considering edge cases (empty tree, single node)");
        System.out.println("   - Using unnecessary space (O(n)) when O(1) or O(h) is possible");
        System.out.println("   - Missing optimization opportunities from BST properties");

        System.out.println("\n4. Advanced Techniques:");
        System.out.println("   - Morris Traversal for O(1) space inorder traversal");
        System.out.println("   - Threaded Binary Tree for efficient traversal without recursion");
        System.out.println("   - Self-balancing BSTs (AVL, Red-Black) for guaranteed O(log n) operations");

        System.out.println("\nFor each BST problem, remember to identify which properties can be");
        System.out.println("leveraged to develop an efficient solution strategy.");
    }

    /**
     * Demonstrates inorder traversal of a BST - the core technique for many BST problems
     * Inorder traversal visits nodes in ascending order of values
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode root, List<Integer> result) {
        if (root == null) return;

        inorderHelper(root.left, result);   // Visit left subtree
        result.add(root.val);               // Visit current node
        inorderHelper(root.right, result);  // Visit right subtree
    }

    /**
     * Pattern 1: Traversal with State Tracking
     * Demonstrates finding BST violations by maintaining previous node reference
     * Used in Problem 99: Recover BST and Problem 530: Minimum Absolute Difference
     */
    public void findViolationsExample(TreeNode root) {
        TreeNode prev = null;
        TreeNode first = null, second = null;

        // Implementation of violation detection
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Process current node
            curr = stack.pop();

            // Check order violation with previous node
            if (prev != null && curr.val < prev.val) {
                // Found violation
                if (first == null) {
                    first = prev;  // First violation: mark previous node
                }
                second = curr;     // Second (or potentially second) violation
            }

            prev = curr;  // Update previous node
            curr = curr.right;  // Move to right subtree
        }

        // At this point, first and second would contain the nodes to swap
        System.out.println("Nodes to swap: " + (first != null ? first.val : "null") +
                " and " + (second != null ? second.val : "null"));
    }

    /**
     * Pattern 2: Range-Based Recursion
     * Demonstrates trimming a BST based on value ranges
     * Used in Problem 669: Trim a BST
     */
    public TreeNode rangeBoundExample(TreeNode root, int low, int high) {
        if (root == null) return null;

        // If root is less than low, entire left subtree should be discarded
        if (root.val < low) {
            return rangeBoundExample(root.right, low, high);
        }

        // If root is greater than high, entire right subtree should be discarded
        if (root.val > high) {
            return rangeBoundExample(root.left, low, high);
        }

        // Root is within range, process children
        root.left = rangeBoundExample(root.left, low, high);
        root.right = rangeBoundExample(root.right, low, high);

        return root;
    }

    /**
     * Pattern 3: Leveraging Sorted Property (Two-Pointer Approach)
     * Demonstrates finding pairs with given sum in a BST
     * Used in Problem 653: Two Sum IV - Input is a BST
     */
    public boolean twoSumBSTExample(TreeNode root, int k) {
        // Convert BST to sorted array (inorder traversal)
        List<Integer> values = new ArrayList<>();
        inorderHelper(root, values);

        // Apply two-pointer technique on the sorted array
        int left = 0;
        int right = values.size() - 1;

        while (left < right) {
            int sum = values.get(left) + values.get(right);

            if (sum == k) {
                return true;  // Found the pair
            } else if (sum < k) {
                left++;  // Need larger sum
            } else {
                right--;  // Need smaller sum
            }
        }

        return false;  // No pair found
    }

    /**
     * Pattern 4: Recursive Construction
     * Demonstrates building a BST from preorder traversal
     * Used in Problem 1008: Construct BST from Preorder Traversal
     */
    public TreeNode constructFromTraversalExample(int[] preorder) {
        return buildBST(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildBST(int[] preorder, int start, int end) {
        if (start > end) return null;

        // Root is the first element in current range
        TreeNode root = new TreeNode(preorder[start]);

        // Find boundary between left and right subtrees
        int rightStartIdx = start + 1;
        while (rightStartIdx <= end && preorder[rightStartIdx] < root.val) {
            rightStartIdx++;
        }

        // Build left and right subtrees recursively
        root.left = buildBST(preorder, start + 1, rightStartIdx - 1);
        root.right = buildBST(preorder, rightStartIdx, end);

        return root;
    }

    /**
     * Pattern 5: Frequency Counting in BST
     * Demonstrates finding modes (most frequent values) in a BST
     * Used in Problem 501: Find Mode in Binary Search Tree
     */
    public int[] findModeExample(TreeNode root) {
        TreeNode prev = null;
        int currentCount = 0;
        int maxCount = 0;
        List<Integer> modes = new ArrayList<>();

        // Implementation with iterative inorder traversal
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Process current node
            curr = stack.pop();

            // Count frequencies (values appear consecutively in inorder traversal)
            if (prev == null || prev.val != curr.val) {
                // New value encountered
                currentCount = 1;
            } else {
                // Same value as previous node
                currentCount++;
            }

            // Update modes list
            if (currentCount > maxCount) {
                maxCount = currentCount;
                modes.clear();
                modes.add(curr.val);
            } else if (currentCount == maxCount) {
                modes.add(curr.val);
            }

            prev = curr;
            curr = curr.right;
        }

        // Convert list to array
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }

        return result;
    }

    /**
     * Pattern 6: Special Tree Properties
     * Demonstrates finding second minimum in a special binary tree
     * Used in Problem 671: Second Minimum Node In a Binary Tree
     */
    public int findSecondMinimumExample(TreeNode root) {
        // Special case
        if (root == null || (root.left == null && root.right == null)) {
            return -1;
        }

        // In this special tree, root always has the minimum value
        int rootValue = root.val;

        // Find the second minimum value
        long secondMin = Long.MAX_VALUE;
        boolean found = false;

        // Use BFS to traverse the tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // If current value is greater than root but smaller than current second min
            if (node.val > rootValue && node.val < secondMin) {
                secondMin = node.val;
                found = true;
            }

            // Add children to queue
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return found ? (int) secondMin : -1;
    }

    /**
     * Pattern 7: Finding Minimum Difference in BST
     * Demonstrates finding minimum absolute difference between any two nodes
     * Used in Problem 530/783: Minimum Absolute Difference in BST
     */
    public int getMinimumDifferenceExample(TreeNode root) {
        TreeNode prev = null;
        int minDiff = Integer.MAX_VALUE;

        // Iterative inorder traversal
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();

            // Calculate minimum difference with previous node
            if (prev != null) {
                minDiff = Math.min(minDiff, curr.val - prev.val);
            }

            prev = curr;
            curr = curr.right;
        }

        return minDiff;
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
}