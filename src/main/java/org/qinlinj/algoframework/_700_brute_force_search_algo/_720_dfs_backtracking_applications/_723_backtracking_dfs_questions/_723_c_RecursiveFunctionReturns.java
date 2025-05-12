package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._723_backtracking_dfs_questions;

/**
 * RETURN VALUES IN BACKTRACKING/DFS/TRAVERSE FUNCTIONS
 * <p>
 * This class discusses whether backtrack/dfs/traverse functions should have
 * return values or not, with examples and best practices.
 * <p>
 * Key recommendations:
 * 1. Keep backtrack/dfs/traverse functions as void methods for clarity
 * 2. Use external variables to track results and control recursion termination
 * 3. If using a return value, make sure the function name and purpose are clear
 * 4. Follow consistent coding styles to clarify your thinking approach
 */
public class _723_c_RecursiveFunctionReturns {

    /**
     * Approach 1: Using traverse with return value (not recommended)
     * The return value is used to terminate recursion early
     */
    public static TreeNode findTargetApproach1(TreeNode root, int targetVal) {
        // External variable to store result
        ResultHolder result = new ResultHolder();
        traverseWithReturn(root, targetVal, result);
        return result.node;
    }

    // Not recommended: Using return value to control recursion
    private static boolean traverseWithReturn(TreeNode root, int targetVal, ResultHolder result) {
        if (root == null) {
            return false;
        }
        if (root.val == targetVal) {
            result.node = root;
            return true;
        }

        // Early termination with boolean return
        return traverseWithReturn(root.left, targetVal, result) ||
                traverseWithReturn(root.right, targetVal, result);
    }

    /**
     * Approach 2: Using traverse without return value (recommended)
     * External variable controls recursion termination
     */
    public static TreeNode findTargetApproach2(TreeNode root, int targetVal) {
        // External variable to store result
        ResultHolder result = new ResultHolder();
        traverse(root, targetVal, result);
        return result.node;
    }

    // Recommended: Standard tree traversal without return value
    private static void traverse(TreeNode root, int targetVal, ResultHolder result) {
        if (root == null) {
            return;
        }

        // Early termination using external variable
        if (result.node != null) {
            return;
        }

        if (root.val == targetVal) {
            result.node = root;
            return;
        }

        // Standard traversal
        traverse(root.left, targetVal, result);
        traverse(root.right, targetVal, result);
    }

    /**
     * Approach 3: Using properly named function with return value
     * "Divide and conquer" approach with meaningful function name
     */
    public static TreeNode findTargetApproach3(TreeNode root, int targetVal) {
        return canFind(root, targetVal);
    }

    // Clear function definition with appropriate name for its purpose
    private static TreeNode canFind(TreeNode root, int targetVal) {
        if (root == null) {
            return null;
        }

        if (root.val == targetVal) {
            return root;
        }

        // Try left subtree
        TreeNode leftResult = canFind(root.left, targetVal);
        if (leftResult != null) {
            return leftResult;
        }

        // Try right subtree
        TreeNode rightResult = canFind(root.right, targetVal);
        if (rightResult != null) {
            return rightResult;
        }

        // Not found
        return null;
    }

    /**
     * Approach 4: Direct "divide and conquer" approach
     * Most clean and straightforward
     */
    public static TreeNode findTarget(TreeNode root, int targetVal) {
        // Base case
        if (root == null) {
            return null;
        }
        if (root.val == targetVal) {
            return root;
        }

        // Try left subtree
        TreeNode leftResult = findTarget(root.left, targetVal);
        if (leftResult != null) {
            return leftResult;
        }

        // Try right subtree
        TreeNode rightResult = findTarget(root.right, targetVal);
        if (rightResult != null) {
            return rightResult;
        }

        return null;
    }

    /**
     * Main method with examples and explanations
     */
    public static void main(String[] args) {
        System.out.println("RETURN VALUES IN BACKTRACKING/DFS/TRAVERSE FUNCTIONS");
        System.out.println("====================================================");

        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        int targetVal = 5;

        // Test each approach
        TreeNode result1 = findTargetApproach1(root, targetVal);
        TreeNode result2 = findTargetApproach2(root, targetVal);
        TreeNode result3 = findTargetApproach3(root, targetVal);
        TreeNode result4 = findTarget(root, targetVal);

        System.out.println("\nResults for finding node with value " + targetVal + ":");
        System.out.println("Approach 1 (traverse with return): " + result1);
        System.out.println("Approach 2 (traverse without return): " + result2);
        System.out.println("Approach 3 (canFind with return): " + result3);
        System.out.println("Approach 4 (direct divide-and-conquer): " + result4);

        System.out.println("\nKey recommendations:");
        System.out.println("1. Keep traverse/dfs/backtrack functions as void methods");
        System.out.println("   - Makes the purpose clear (just traversal)");
        System.out.println("   - Algorithm logic can be studied at pre/in/post-order positions");

        System.out.println("\n2. Use external variables to control recursion termination");
        System.out.println("   - Clearer than using boolean return values");
        System.out.println("   - More consistent with the traversal mindset");

        System.out.println("\n3. If using return values, use proper function names");
        System.out.println("   - Define clearly what the function does");
        System.out.println("   - Make sure the name reflects the return value's meaning");

        System.out.println("\n4. Consider the two recursive thinking modes:");
        System.out.println("   - 'Traversal' thinking: Generally void functions");
        System.out.println("   - 'Divide and conquer' thinking: Generally has return values");

        System.out.println("\n5. Follow consistent coding styles to clarify your approach");
        System.out.println("   - Helps interviewers understand your thought process");
        System.out.println("   - Helps you maintain clarity in complex algorithms");
    }

    /**
     * Example problem: Find any node with value targetVal in a binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode(" + val + ")";
        }
    }

    // Helper class to hold result
    static class ResultHolder {
        TreeNode node = null;
    }
}