package org.qinlinj.algoframework._400_binary_tree_algo._410_binary_tree_algo_guiding_principle._414_binary_tree_post_order;

/**
 * FINDING DUPLICATE SUBTREES - OPTIMIZED SOLUTION
 * ==============================================
 * <p>
 * This class provides an optimized solution for LeetCode 652: Find Duplicate Subtrees
 * <p>
 * Problem Description:
 * Given the root of a binary tree, return all duplicate subtrees. For each duplicate
 * subtree, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure and same node values.
 * <p>
 * Optimization Insights:
 * <p>
 * 1. The serialization approach in the basic solution works correctly but has some inefficiencies:
 * - String concatenation and storage can be memory-intensive
 * - Serialized strings can become quite long for large trees
 * <p>
 * 2. Alternative Approach - Triplet Identification:
 * - Instead of full serialization, we can use a triplet of (node.val, left_id, right_id)
 * - Each subtree gets a unique ID based on its structure
 * - This approach reduces both time and space complexity
 * <p>
 * 3. Still leverages post-order traversal:
 * - We still need information from both subtrees to identify duplicates
 * - Post-order position remains the critical point for implementation
 * <p>
 * Time Complexity: Improved to O(n) from O(n²) in the original solution
 * <p>
 * Space Complexity: Improved to O(n) from O(n²) in the original solution
 */

import java.util.*;

public class _414_c_FindDuplicateSubtreesOptimized {
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
     * Explanation of optimization techniques
     */
    private static void explainOptimization() {
        System.out.println("Optimization Techniques:");
        System.out.println("-----------------------");
        System.out.println("1. Triplet-based ID assignment:");
        System.out.println("   - Instead of serializing the entire subtree as a string");
        System.out.println("   - We use a triplet (node.val, leftID, rightID) to identify subtrees");
        System.out.println("   - This reduces memory usage and improves performance");
        System.out.println();
        System.out.println("2. Trie-based approach:");
        System.out.println("   - Further optimization to avoid string operations completely");
        System.out.println("   - Uses a 3-way trie to assign IDs based on (val, leftID, rightID)");
        System.out.println("   - Reduces both time and space complexity");
        System.out.println();
        System.out.println("Both optimizations still leverage the post-order traversal position,");
        System.out.println("as we need complete information about both subtrees to identify duplicates.");
    }

    /**
     * Comparison of time and space complexity between the original and optimized solutions
     */
    private static void compareComplexity() {
        System.out.println("Complexity Comparison:");
        System.out.println("----------------------");
        System.out.println("Original Solution (String Serialization):");
        System.out.println("- Time Complexity: O(n²)");
        System.out.println("  * Each serialization can take O(n) time");
        System.out.println("  * We perform this for each of the n nodes");
        System.out.println("- Space Complexity: O(n²)");
        System.out.println("  * Each serialized string can be O(n) in length");
        System.out.println("  * We store one string for each of the n nodes");
        System.out.println();
        System.out.println("Optimized Solution (Triplet ID):");
        System.out.println("- Time Complexity: O(n)");
        System.out.println("  * Each ID assignment is O(1)");
        System.out.println("  * We perform this for each of the n nodes");
        System.out.println("- Space Complexity: O(n)");
        System.out.println("  * We store a constant-size triplet for each node");
        System.out.println();
        System.out.println("The improvement is particularly significant for large trees");
        System.out.println("where serialized strings would become very long.");
    }

    /**
     * Helper method to print subtrees (for displaying results)
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

    public static void main(String[] args) {
        _414_c_FindDuplicateSubtreesOptimized solution = new _414_c_FindDuplicateSubtreesOptimized();

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

        System.out.println("\nUsing triplet-based optimization:");
        List<TreeNode> duplicates1 = solution.findDuplicateSubtrees(root1);
        printSubtrees(duplicates1);

        System.out.println("\nUsing trie-based optimization:");
        List<TreeNode> duplicates1Alt = solution.findDuplicateSubtreesWithoutStrings(root1);
        printSubtrees(duplicates1Alt);

        // Example 2: Create the tree [2,1,1]
        //      2
        //    /   \
        //   1     1
        TreeNode root2 = new TreeNode(2, new TreeNode(1), new TreeNode(1));

        System.out.println("\n\nExample 2:");
        printTree(root2);

        System.out.println("\nUsing triplet-based optimization:");
        List<TreeNode> duplicates2 = solution.findDuplicateSubtrees(root2);
        printSubtrees(duplicates2);

        System.out.println("\n\n---------------------------------");
        explainOptimization();

        System.out.println("\n---------------------------------");
        compareComplexity();
    }

    /**
     * Main method to find duplicate subtrees using the optimized approach
     *
     * @param root The root of the binary tree
     * @return List of root nodes of duplicate subtrees
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // Map to store the unique identifier for each subtree structure
        HashMap<String, Integer> tripletToID = new HashMap<>();
        // Map to track the frequency of each subtree ID
        HashMap<Integer, Integer> idToCount = new HashMap<>();
        // List to store the result nodes
        List<TreeNode> result = new LinkedList<>();

        // Start the post-order traversal with an initial ID counter of 1
        assignIDs(root, tripletToID, idToCount, result);
        return result;
    }

    /**
     * Helper method that performs post-order traversal and assigns unique IDs to subtrees
     *
     * @param node        The current node being processed
     * @param tripletToID Map from subtree triplet representation to ID
     * @param idToCount   Map to track the frequency of each subtree ID
     * @param result      List to store the result nodes
     * @return The ID assigned to the current subtree
     */
    private int assignIDs(TreeNode node,
                          HashMap<String, Integer> tripletToID,
                          HashMap<Integer, Integer> idToCount,
                          List<TreeNode> result) {
        // Base case: null node
        if (node == null) {
            return 0;  // ID 0 is reserved for null nodes
        }

        // Post-order traversal: first process children
        int leftID = assignIDs(node.left, tripletToID, idToCount, result);
        int rightID = assignIDs(node.right, tripletToID, idToCount, result);

        // Create a triplet representation of this subtree
        // Format: "node.val,leftID,rightID"
        String triplet = node.val + "," + leftID + "," + rightID;

        // Assign a unique ID to this triplet if we haven't seen it before
        int currentID = tripletToID.computeIfAbsent(triplet, x -> tripletToID.size() + 1);

        // Update the frequency in our map
        int count = idToCount.getOrDefault(currentID, 0);
        idToCount.put(currentID, count + 1);

        // If we've seen this subtree exactly once before, add it to our result
        if (count == 1) {
            result.add(node);
        }

        // Return the ID for this subtree to be used by parent nodes
        return currentID;
    }

    /**
     * Alternative implementation using more efficient ID assignment
     * This approach avoids string concatenation entirely
     */
    public List<TreeNode> findDuplicateSubtreesWithoutStrings(TreeNode root) {
        // Map to store mappings from (val, leftID, rightID) to a unique ID
        TrieNode trie = new TrieNode();
        // Track frequency of each subtree ID
        HashMap<Integer, Integer> count = new HashMap<>();
        // Result list
        List<TreeNode> result = new LinkedList<>();

        // Start the traversal
        getID(root, trie, count, result);
        return result;
    }

    /**
     * Assigns IDs using a trie structure to avoid string operations
     */
    private int getID(TreeNode node, TrieNode trie, HashMap<Integer, Integer> count, List<TreeNode> result) {
        if (node == null) {
            return 0;
        }

        // Post-order traversal
        int leftID = getID(node.left, trie, count, result);
        int rightID = getID(node.right, trie, count, result);

        // Navigate the trie based on the current node's value and children IDs
        TrieNode valNode = trie.children.computeIfAbsent(node.val, k -> new TrieNode());
        TrieNode leftNode = valNode.children.computeIfAbsent(leftID, k -> new TrieNode());
        TrieNode rightNode = leftNode.children.computeIfAbsent(rightID, k -> new TrieNode());

        // If this triplet hasn't been assigned an ID, assign the next available one
        if (rightNode.id == 0) {
            rightNode.id = trie.nextID++;
        }

        // Update the frequency
        int currentID = rightNode.id;
        count.put(currentID, count.getOrDefault(currentID, 0) + 1);

        // Add to result if this is the second occurrence
        if (count.get(currentID) == 2) {
            result.add(node);
        }

        return currentID;
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

    /**
     * Helper class for the trie-based ID assignment
     * This is essentially a 3-way trie for (val, leftID, rightID) triplets
     */
    private static class TrieNode {
        HashMap<Integer, TrieNode> children = new HashMap<>();
        int id = 0;
        int nextID = 1;
    }
}
