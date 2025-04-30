package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._413_binary_tree_postorder;

/**
 * BINARY TREE CONSTRUCTION - COMPREHENSIVE SUMMARY
 * ===============================================
 * <p>
 * This class provides a comprehensive summary of the binary tree construction algorithms covered:
 * 1. Constructing a Maximum Binary Tree
 * 2. Constructing a Tree from Preorder and Inorder Traversals
 * 3. Constructing a Tree from Inorder and Postorder Traversals
 * 4. Constructing a Tree from Preorder and Postorder Traversals
 * <p>
 * Common Patterns Across All Algorithms:
 * <p>
 * 1. Problem Decomposition Approach:
 * - All binary tree construction algorithms follow the problem decomposition pattern
 * - The general formula is: constructTree(data) = root + constructTree(leftData) + constructTree(rightData)
 * <p>
 * 2. Key Steps in Every Algorithm:
 * a) Identify the root node from the given data
 * b) Determine how to divide the remaining data into left and right subtree components
 * c) Recursively construct the left and right subtrees
 * d) Connect the root with the subtrees
 * <p>
 * 3. Traversal Properties:
 * - Preorder traversal: Root comes first (root, left, right)
 * - Inorder traversal: Root comes in the middle (left, root, right)
 * - Postorder traversal: Root comes last (left, right, root)
 * <p>
 * 4. Unique Tree Identification:
 * - Preorder + Inorder: Uniquely identifies a tree
 * - Postorder + Inorder: Uniquely identifies a tree
 * - Preorder + Postorder: Does NOT always uniquely identify a tree
 * <p>
 * This summary serves as a reference for understanding and implementing
 * binary tree construction algorithms.
 */

public class _413_f_BinaryTreeConstructionSummary {
    /**
     * Summary of traversal properties and their relationships
     */
    public static void explainTraversalProperties() {
        System.out.println("Binary Tree Traversal Properties:");
        System.out.println("--------------------------------");
        System.out.println("1. Preorder: Root, Left, Right");
        System.out.println("   - First element is always the root");
        System.out.println();
        System.out.println("2. Inorder: Left, Root, Right");
        System.out.println("   - Root divides the array into left and right subtrees");
        System.out.println("   - Crucial for determining tree structure");
        System.out.println();
        System.out.println("3. Postorder: Left, Right, Root");
        System.out.println("   - Last element is always the root");
        System.out.println();
        System.out.println("Combinations for Tree Construction:");
        System.out.println("   Preorder + Inorder: Uniquely defines a tree");
        System.out.println("   Postorder + Inorder: Uniquely defines a tree");
        System.out.println("   Preorder + Postorder: May not uniquely define a tree");
    }

    /**
     * Summary of problem decomposition pattern in tree construction
     */
    public static void explainProblemDecomposition() {
        System.out.println("Problem Decomposition Pattern:");
        System.out.println("-----------------------------");
        System.out.println("1. Identify the root node");
        System.out.println("2. Divide the data into left and right subtree components");
        System.out.println("3. Recursively construct the left and right subtrees");
        System.out.println("4. Connect the root with the constructed subtrees");
        System.out.println();
        System.out.println("This pattern applies to all tree construction algorithms.");
        System.out.println("The key differences are in how we identify the root and");
        System.out.println("how we determine the boundaries for the subtrees.");
    }

    public static void main(String[] args) {
        System.out.println("Binary Tree Construction - Summary");
        System.out.println("=================================");
        System.out.println();

        explainTraversalProperties();
        System.out.println();

        explainProblemDecomposition();
        System.out.println();

        System.out.println("This class summarizes four binary tree construction algorithms:");
        System.out.println("1. Maximum Binary Tree (LeetCode 654)");
        System.out.println("2. Construct from Preorder and Inorder (LeetCode 105)");
        System.out.println("3. Construct from Inorder and Postorder (LeetCode 106)");
        System.out.println("4. Construct from Preorder and Postorder (LeetCode 889)");
        System.out.println();
        System.out.println("Each algorithm follows the problem decomposition pattern");
        System.out.println("but with different strategies for identifying the root and");
        System.out.println("determining the subtree boundaries.");
    }

    /**
     * ALGORITHM 1: MAXIMUM BINARY TREE CONSTRUCTION
     * <p>
     * Given an array of unique integers, construct a binary tree where:
     * - Root is the maximum value in the array
     * - Left subtree is constructed from elements before the maximum
     * - Right subtree is constructed from elements after the maximum
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildMaxTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildMaxTree(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        // Find max value and index
        int maxIndex = lo;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Create root with max value
        TreeNode root = new TreeNode(nums[maxIndex]);

        // Recursively build left and right subtrees
        root.left = buildMaxTree(nums, lo, maxIndex - 1);
        root.right = buildMaxTree(nums, maxIndex + 1, hi);

        return root;
    }

    /**
     * ALGORITHM 2: CONSTRUCT FROM PREORDER AND INORDER
     * <p>
     * Given preorder and inorder traversals, construct the binary tree.
     * - First element in preorder is the root
     * - Find root in inorder to determine left and right subtrees
     */
    public TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        // Create value-to-index map for inorder array
        java.util.HashMap<Integer, Integer> inMap = new java.util.HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildPreIn(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode buildPreIn(int[] preorder, int preStart, int preEnd,
                                int[] inorder, int inStart, int inEnd,
                                java.util.HashMap<Integer, Integer> inMap) {
        if (preStart > preEnd) {
            return null;
        }

        // Root is first element in preorder
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Find root in inorder
        int rootIndex = inMap.get(rootVal);

        // Calculate sizes
        int leftSize = rootIndex - inStart;

        // Recursively build subtrees
        root.left = buildPreIn(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, rootIndex - 1, inMap);
        root.right = buildPreIn(preorder, preStart + leftSize + 1, preEnd,
                inorder, rootIndex + 1, inEnd, inMap);

        return root;
    }

    /**
     * ALGORITHM 3: CONSTRUCT FROM INORDER AND POSTORDER
     * <p>
     * Given inorder and postorder traversals, construct the binary tree.
     * - Last element in postorder is the root
     * - Find root in inorder to determine left and right subtrees
     */
    public TreeNode buildTreeFromInPost(int[] inorder, int[] postorder) {
        // Create value-to-index map for inorder array
        java.util.HashMap<Integer, Integer> inMap = new java.util.HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildInPost(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1, inMap);
    }

    private TreeNode buildInPost(int[] inorder, int inStart, int inEnd,
                                 int[] postorder, int postStart, int postEnd,
                                 java.util.HashMap<Integer, Integer> inMap) {
        if (inStart > inEnd) {
            return null;
        }

        // Root is last element in postorder
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // Find root in inorder
        int rootIndex = inMap.get(rootVal);

        // Calculate sizes
        int leftSize = rootIndex - inStart;

        // Recursively build subtrees
        root.left = buildInPost(inorder, inStart, rootIndex - 1,
                postorder, postStart, postStart + leftSize - 1, inMap);
        root.right = buildInPost(inorder, rootIndex + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1, inMap);

        return root;
    }

    /**
     * ALGORITHM 4: CONSTRUCT FROM PREORDER AND POSTORDER
     * <p>
     * Given preorder and postorder traversals, construct a binary tree (may not be unique).
     * - First element in preorder is the root
     * - Last element in postorder is also the root
     * - Second element in preorder is the root of left subtree
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // Create value-to-index map for postorder array
        java.util.HashMap<Integer, Integer> postMap = new java.util.HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postMap.put(postorder[i], i);
        }

        return buildPrePost(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1, postMap);
    }

    private TreeNode buildPrePost(int[] preorder, int preStart, int preEnd,
                                  int[] postorder, int postStart, int postEnd,
                                  java.util.HashMap<Integer, Integer> postMap) {
        if (preStart > preEnd) {
            return null;
        }

        // Single node case
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        // Create root from first preorder element
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Use second element in preorder (left root) for division
        int leftRootVal = preorder[preStart + 1];
        int leftRootIndex = postMap.get(leftRootVal);

        // Calculate left subtree size
        int leftSize = leftRootIndex - postStart + 1;

        // Recursively build subtrees
        root.left = buildPrePost(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, leftRootIndex, postMap);
        root.right = buildPrePost(preorder, preStart + leftSize + 1, preEnd,
                postorder, leftRootIndex + 1, postEnd - 1, postMap);

        return root;
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
}