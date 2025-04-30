package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._414_binary_tree_post_order;

/**
 * FINDING DUPLICATE SUBTREES - LEVERAGING POST-ORDER TRAVERSAL
 * ===========================================================
 * <p>
 * This class implements the solution for LeetCode 652: Find Duplicate Subtrees
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return all duplicate subtrees. For each duplicate
 * subtree, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure and same node values.
 * <p>
 * Key Insights:
 * <p>
 * 1. The Power of Post-Order Position:
 * - In post-order traversal, we have access to complete information about both subtrees
 * - This makes it perfect for problems where we need to process entire subtrees
 * - As the textbook states: "Post-order position code can access data passed from
 * parent nodes via function parameters AND data returned from child nodes"
 * <p>
 * 2. How to Identify Duplicate Subtrees:
 * - We need a way to uniquely represent each subtree (serialization)
 * - Post-order traversal result can serve as a unique identifier for each subtree
 * - We use a HashMap to track the frequency of each subtree's serialized form
 * <p>
 * 3. Algorithm Steps:
 * - Perform post-order traversal on the binary tree
 * - For each node, create a serialized representation of the subtree rooted at that node
 * - Use a HashMap to track how many times we've seen each serialized representation
 * - If we encounter a serialized subtree for the second time, add it to our result list
 * - Ensure we only add each duplicate subtree once (by only adding when count == 1)
 * <p>
 * Time Complexity: O(n²) where n is the number of nodes. The serialization of each
 * subtree could take O(n) time, and we do this for each node.
 * <p>
 * Space Complexity: O(n²) for storing the serialized strings.
 */

import java.util.*;

public class _414_a_FindDuplicateSubtrees {
    /**
     * Helper method to print a tree for visualization (level-order traversal)
     */
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        System.out.println("Tree structure (level order):");

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            StringBuilder levelNodes = new StringBuilder();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node == null) {
                    levelNodes.append("null ");
                    continue;
                }

                levelNodes.append(node.val).append(" ");

                queue.offer(node.left);
                queue.offer(node.right);
            }

            // Check if this level has any non-null nodes
            if (levelNodes.toString().trim().replace("null", "").length() > 0) {
                System.out.println(levelNodes);
            } else {
                break; // All nulls in this level, no need to print more
            }
        }
    }

    /**
     * Helper method to print a list of subtrees (for displaying results)
     */
    private static void printSubtrees(List<TreeNode> subtrees) {
        System.out.println("\nDuplicate Subtrees:");

        if (subtrees.isEmpty()) {
            System.out.println("No duplicate subtrees found.");
            return;
        }

        for (int i = 0; i < subtrees.size(); i++) {
            System.out.println("\nDuplicate Subtree " + (i + 1) + ":");
            printTree(subtrees.get(i));
        }
    }

    /**
     * Visual explanation of how the algorithm works
     */
    private static void explainAlgorithm() {
        System.out.println("How the algorithm works:");
        System.out.println("------------------------");
        System.out.println("1. We perform a post-order traversal of the tree");
        System.out.println("2. For each node, we create a serialized string representation of its subtree");
        System.out.println("3. We use a HashMap to track how many times we've seen each serialized subtree");
        System.out.println("4. When we encounter a serialized subtree for the second time, we add it to our result");
        System.out.println("\nFor example, with the tree [1,2,3,4,null,2,4,null,null,4]:");
        System.out.println("- Multiple subtrees with root value 4 have serialization '#,#,4'");
        System.out.println("- Multiple subtrees with root value 2 have serialization '#,#,4,#,2'");
        System.out.println("- We add these subtrees to our result when we encounter them the second time");
    }

    public static void main(String[] args) {
        _414_a_FindDuplicateSubtrees solution = new _414_a_FindDuplicateSubtrees();

        // Example 1: Create the tree [1,2,3,4,null,2,4,null,null,4]
        //      1
        //    /   \
        //   2     3
        //  /     / \
        // 4     2   4
        //      /
        //     4
        TreeNode leaf4_1 = new TreeNode(4);
        TreeNode leaf4_2 = new TreeNode(4);
        TreeNode leaf4_3 = new TreeNode(4);
        TreeNode node2_1 = new TreeNode(2, leaf4_1, null);
        TreeNode node2_2 = new TreeNode(2, leaf4_3, null);
        TreeNode node3 = new TreeNode(3, node2_2, leaf4_2);
        TreeNode root1 = new TreeNode(1, node2_1, node3);

        System.out.println("Example 1:");
        printTree(root1);

        List<TreeNode> duplicates1 = solution.findDuplicateSubtrees(root1);
        printSubtrees(duplicates1);

        // Example 2: Create the tree [2,1,1]
        //      2
        //    /   \
        //   1     1
        TreeNode root2 = new TreeNode(2, new TreeNode(1), new TreeNode(1));

        System.out.println("\n\nExample 2:");
        printTree(root2);

        List<TreeNode> duplicates2 = solution.findDuplicateSubtrees(root2);
        printSubtrees(duplicates2);

        // Example 3: Create the tree [2,2,2,3,null,3,null]
        //      2
        //    /   \
        //   2     2
        //  /     /
        // 3     3
        TreeNode leaf3_1 = new TreeNode(3);
        TreeNode leaf3_2 = new TreeNode(3);
        TreeNode node2_3 = new TreeNode(2, leaf3_1, null);
        TreeNode node2_4 = new TreeNode(2, leaf3_2, null);
        TreeNode root3 = new TreeNode(2, node2_3, node2_4);

        System.out.println("\n\nExample 3:");
        printTree(root3);

        List<TreeNode> duplicates3 = solution.findDuplicateSubtrees(root3);
        printSubtrees(duplicates3);

        System.out.println("\n");
        explainAlgorithm();
    }

    /**
     * Main method to find duplicate subtrees
     *
     * @param root The root of the binary tree
     * @return List of root nodes of duplicate subtrees
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // Map to track subtree serializations and their frequencies
        HashMap<String, Integer> subtreeMap = new HashMap<>();
        // List to store the result nodes
        LinkedList<TreeNode> result = new LinkedList<>();

        // Start the post-order traversal and serialization
        serialize(root, subtreeMap, result);
        return result;
    }

    /**
     * Helper method that performs post-order traversal and serialization
     *
     * @param root       The current node being processed
     * @param subtreeMap Map to track subtree serializations and their frequencies
     * @param result     List to store the result nodes
     * @return Serialized representation of the subtree rooted at this node
     */
    private String serialize(TreeNode root, HashMap<String, Integer> subtreeMap, LinkedList<TreeNode> result) {
        // Base case: null node represented as "#"
        if (root == null) {
            return "#";
        }

        // Post-order traversal: first process left and right children
        String leftSerialized = serialize(root.left, subtreeMap, result);
        String rightSerialized = serialize(root.right, subtreeMap, result);

        // Construct the serialized representation of the current subtree
        // Format: left serialization + "," + right serialization + "," + current value
        String currentSerialized = leftSerialized + "," + rightSerialized + "," + root.val;

        // Update the frequency in our map
        int frequency = subtreeMap.getOrDefault(currentSerialized, 0);

        // If we've seen this subtree exactly once before, add it to our result
        // This ensures we only add each duplicate subtree once
        if (frequency == 1) {
            result.add(root);
        }

        // Increment the frequency count
        subtreeMap.put(currentSerialized, frequency + 1);

        // Return the serialized representation for use by the parent node
        return currentSerialized;
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        // Constructor for building test trees
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
