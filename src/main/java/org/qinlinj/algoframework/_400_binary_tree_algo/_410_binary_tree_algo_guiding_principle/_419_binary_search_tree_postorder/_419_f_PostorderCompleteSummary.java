package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._419_binary_search_tree_postorder;

/**
 * Binary Search Tree - Postorder Traversal Complete Summary
 * <p>
 * This class provides a comprehensive summary of all key concepts related to postorder traversal
 * in Binary Search Trees as covered in the original text, consolidating knowledge from previous classes.
 * <p>
 * Key summary points:
 * <p>
 * 1. Postorder Traversal Fundamentals:
 * - Traversal order: left subtree → right subtree → current node
 * - Unique advantage: access to both top-down parameters and bottom-up return values
 * - Best used when node operations depend on results from subtrees
 * <p>
 * 2. Maximum Sum BST Problem (LeetCode 1373):
 * - Find the maximum sum of any valid BST subtree within a binary tree
 * - Shows perfect use case for postorder traversal
 * - Demonstrates how to avoid redundant calculations through efficient algorithm design
 * <p>
 * 3. Common Optimization Patterns:
 * - Replacing multiple recursive traversals with a single efficient traversal
 * - Using arrays or objects to return multiple values
 * - Propagating information up the tree through return values
 * - Reducing time complexity from O(n²) to O(n)
 * <p>
 * 4. Practical Applications:
 * - Tree validation (BST, balanced, symmetric)
 * - Subtree optimization problems
 * - Tree transformation operations
 * - Path-related calculations
 * <p>
 * 5. Implementation Technique:
 * - Using return values to carry information upward
 * - Avoiding "recursion inside recursion" pattern
 * - Making decisions at each node based on subtree information
 */

public class _419_f_PostorderCompleteSummary {

    // Find maximum path sum
    private int maxPathSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        _419_f_PostorderCompleteSummary summary = new _419_f_PostorderCompleteSummary();
        summary.demonstrateAndVerify();

        // Key takeaways
        System.out.println("\nKey takeaways from BST Postorder Traversal:");
        System.out.println("1. Postorder traversal uniquely allows accessing both top-down and bottom-up information");
        System.out.println("2. It's ideal for problems where node operations depend on subtree properties");
        System.out.println("3. The efficient approach avoids multiple recursive traversals, reducing complexity from O(n²) to O(n)");
        System.out.println("4. Multi-value returns enable propagating complex information up the tree");
        System.out.println("5. The key optimization is calculating and passing all needed information in a single traversal");
        System.out.println("6. When facing tree problems, consider whether postorder traversal would provide advantages");

        System.out.println("\nImplementation Pattern:");
        System.out.println("1. Process left subtree first");
        System.out.println("2. Process right subtree next");
        System.out.println("3. At postorder position, use information from subtrees to:");
        System.out.println("   - Make decisions about current node");
        System.out.println("   - Calculate current node's properties");
        System.out.println("   - Update global state if needed");
        System.out.println("   - Return information to parent nodes");
    }

    /**
     * Section 1: Core Postorder Traversal Framework
     */
    public void postorderTraversal(TreeNode root) {
        if (root == null) return;

        postorderTraversal(root.left);
        postorderTraversal(root.right);

        // Postorder position: process current node after both subtrees
        System.out.println("Processing node: " + root.val);
    }

    /**
     * Section 2: Maximum Sum BST Implementation
     * The flagship example showing postorder traversal optimization
     */
    public int maxSumBST(TreeNode root) {
        int[] maxSum = new int[1]; // To store result
        findMaxMinSum(root, maxSum);
        return maxSum[0];
    }

    // Returns [isBST, min, max, sum]
    private int[] findMaxMinSum(TreeNode root, int[] maxSum) {
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        // Step 1: Get information from left and right subtrees
        int[] left = findMaxMinSum(root.left, maxSum);
        int[] right = findMaxMinSum(root.right, maxSum);

        // Step 2: Process current node (postorder position)
        int[] res = new int[4];

        // Check if this subtree is a valid BST
        if (left[0] == 1 && right[0] == 1 &&
                root.val > left[2] && root.val < right[1]) {

            // It's a valid BST, calculate its properties
            res[0] = 1; // Valid BST
            res[1] = Math.min(left[1], root.val); // Min value
            res[2] = Math.max(right[2], root.val); // Max value
            res[3] = left[3] + right[3] + root.val; // Sum

            // Update the maximum sum found so far
            maxSum[0] = Math.max(maxSum[0], res[3]);
        } else {
            // Not a valid BST
            res[0] = 0;
        }

        return res;
    }

    /**
     * Section 3: Inefficient vs. Efficient Approaches
     * Demonstrating the performance difference
     */

    // Inefficient approach with multiple traversals
    public int maxSumBST_inefficient(TreeNode root) {
        if (root == null) return 0;

        int max = 0;

        // Check if current subtree is a valid BST
        if (isBST(root, null, null)) {
            max = Math.max(max, sumOfTree(root));
        }

        // Try left and right subtrees
        max = Math.max(max, maxSumBST_inefficient(root.left));
        max = Math.max(max, maxSumBST_inefficient(root.right));

        return max;
    }

    // Helper methods for inefficient approach
    private boolean isBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }

        return isBST(root.left, min, root.val) &&
                isBST(root.right, root.val, max);
    }

    private int sumOfTree(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumOfTree(root.left) + sumOfTree(root.right);
    }

    public int maxSumBST_withObject(TreeNode root) {
        return findMaxSumBST(root).maxBSTSum;
    }

    private TreeInfo findMaxSumBST(TreeNode root) {
        if (root == null) {
            return new TreeInfo(
                    true,               // Empty tree is a valid BST
                    Integer.MAX_VALUE,  // Default min value (for comparison)
                    Integer.MIN_VALUE,  // Default max value (for comparison)
                    0,                  // Sum is 0 for empty tree
                    0                   // Max BST sum found so far
            );
        }

        // Get information from subtrees
        TreeInfo left = findMaxSumBST(root.left);
        TreeInfo right = findMaxSumBST(root.right);

        // Check if current tree is a valid BST
        if (left.isBST && right.isBST &&
                root.val > left.max && root.val < right.min) {

            // Calculate properties of this BST
            int min = Math.min(root.val, left.min);
            int max = Math.max(root.val, right.max);
            int sum = root.val + left.sum + right.sum;

            // Update maximum BST sum
            int maxBSTSum = Math.max(sum,
                    Math.max(left.maxBSTSum, right.maxBSTSum));

            return new TreeInfo(true, min, max, sum, maxBSTSum);
        } else {
            // Not a valid BST, but still track the maximum BST sum from subtrees
            int maxBSTSum = Math.max(left.maxBSTSum, right.maxBSTSum);

            // Return invalid BST marker, but pass along max BST sum
            return new TreeInfo(false, 0, 0, 0, maxBSTSum);
        }
    }

    /**
     * Section 5: Additional Postorder Applications
     */

    // Calculate tree height
    public int height(TreeNode root) {
        if (root == null) return -1;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Postorder: use subtree heights to calculate current height
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Check if tree is balanced
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) return -1;

        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) return -1;

        // Postorder: check balance at current node
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxPathSum;
    }

    private int maxGain(TreeNode root) {
        if (root == null) return 0;

        // Calculate gains from subtrees (ignore negative gains)
        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);

        // Postorder: calculate path sum through current node
        int pathSum = root.val + leftGain + rightGain;
        maxPathSum = Math.max(maxPathSum, pathSum);

        // Return the maximum gain if continuing this path
        return root.val + Math.max(leftGain, rightGain);
    }

    /**
     * Section 6: Demonstration and Verification
     */
    public void demonstrateAndVerify() {
        System.out.println("Postorder Traversal Summary and Verification:\n");

        // Create test trees
        TreeNode validBST = createValidBST();
        TreeNode mixedTree = createMixedTree();

        // Demonstrate Maximum Sum BST problem
        System.out.println("1. Maximum Sum BST Problem:");
        int efficientResult = maxSumBST(mixedTree);
        int inefficientResult = maxSumBST_inefficient(mixedTree);
        int objectResult = maxSumBST_withObject(mixedTree);

        System.out.println("  Efficient approach result: " + efficientResult);
        System.out.println("  Inefficient approach result: " + inefficientResult);
        System.out.println("  Object-based approach result: " + objectResult);
        System.out.println("  All approaches give same result: " +
                (efficientResult == inefficientResult &&
                        inefficientResult == objectResult));

        // Demonstrate performance difference on larger tree
        System.out.println("\n2. Performance comparison on larger tree:");
        TreeNode largeTree = createLargeTree(15); // Height 15, ~32K nodes

        long startTime = System.nanoTime();
        int result1 = maxSumBST(largeTree);
        long endTime = System.nanoTime();
        System.out.println("  Efficient approach time: " +
                (endTime - startTime) / 1000000 + " ms");

        // Only test smaller tree with inefficient approach
        TreeNode mediumTree = createLargeTree(10); // Height 10, ~1K nodes
        startTime = System.nanoTime();
        int result2 = maxSumBST_inefficient(mediumTree);
        endTime = System.nanoTime();
        System.out.println("  Inefficient approach time (smaller tree): " +
                (endTime - startTime) / 1000000 + " ms");

        // Additional postorder applications
        System.out.println("\n3. Other postorder applications:");
        System.out.println("  Tree height: " + height(mixedTree));
        System.out.println("  Is balanced: " + isBalanced(mixedTree));
        System.out.println("  Maximum path sum: " + maxPathSum(mixedTree));
    }

    /**
     * Utility methods for creating test trees
     */
    private TreeNode createValidBST() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);
        return root;
    }

    private TreeNode createMixedTree() {
        // Create a tree with both valid and invalid BST subtrees
        TreeNode root = new TreeNode(5);

        // Left subtree (valid BST)
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // Right subtree (invalid BST)
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(8); // Violates BST property
        root.right.right = new TreeNode(9);

        return root;
    }

    private TreeNode createLargeTree(int height) {
        if (height <= 0) return null;

        // Create a random value that sometimes creates valid BSTs and sometimes not
        int val = (int) (Math.random() * 100);
        TreeNode root = new TreeNode(val);

        // Make subtrees with random values
        root.left = createLargeTree(height - 1);
        root.right = createLargeTree(height - 1);

        return root;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Section 4: Multi-value Return Pattern
     * Using custom objects for clarity
     */
    public static class TreeInfo {
        boolean isBST;
        int min;
        int max;
        int sum;
        int maxBSTSum;

        TreeInfo(boolean isBST, int min, int max, int sum, int maxBSTSum) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.maxBSTSum = maxBSTSum;
        }
    }
}
