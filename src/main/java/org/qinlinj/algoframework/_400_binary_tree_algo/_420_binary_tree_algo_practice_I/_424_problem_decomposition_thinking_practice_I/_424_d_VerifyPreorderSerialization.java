package org.qinlinj.algoframework._400_binary_tree_algo._420_binary_tree_algo_practice_I._424_problem_decomposition_thinking_practice_I;

import java.util.*;

/**
 * Problem 331: Verify Preorder Serialization of a Binary Tree
 * <p>
 * Description:
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter
 * a non-null node, we record the node's value. If it is a null node, we record using
 * a sentinel value such as #.
 * <p>
 * For example, the binary tree [1,2,3,null,null,4,5] can be serialized
 * as "1,2,#,#,3,4,#,#,5,#,#".
 * <p>
 * Given a string of comma-separated values preorder, return true if it is a correct
 * preorder traversal serialization of a binary tree.
 * <p>
 * Key Concepts:
 * - Uses tree structure properties rather than reconstruction
 * - Leverages the relationship between nodes and edges in a binary tree:
 * - Each non-null node adds 2 outgoing edges and consumes 1 incoming edge
 * - Each null node consumes 1 incoming edge and adds 0 outgoing edges
 * - At the end, all edges should be consumed
 * <p>
 * Time Complexity: O(n), where n is the length of the preorder string
 * Space Complexity: O(1) for the edge counting approach
 */
public class _424_d_VerifyPreorderSerialization {

    /**
     * Verifies if a string is a valid preorder serialization of a binary tree
     * using edge counting.
     *
     * @param preorder A comma-separated string representing preorder traversal
     * @return True if the string is a valid preorder serialization, false otherwise
     */
    public boolean isValidSerialization(String preorder) {
        // Start with one available edge (the one pointing to the root)
        int availableEdges = 1;

        // Split the preorder string by commas
        String[] nodes = preorder.split(",");

        // Process each node
        for (String node : nodes) {
            // Before processing a node, we need an available edge
            availableEdges--;

            // If at any point we have negative available edges, it's invalid
            if (availableEdges < 0) {
                return false;
            }

            // If it's a non-null node, it provides two more edges
            if (!node.equals("#")) {
                availableEdges += 2;
            }
            // If it's a null node (#), it doesn't provide any new edges
        }

        // At the end, all edges should be used up
        return availableEdges == 0;
    }

    /**
     * Alternative implementation using a recursive deserializer to validate
     * the preorder traversal.
     */
    public boolean isValidSerializationRecursive(String preorder) {
        // Convert the string to a list for easier manipulation
        LinkedList<String> nodes = new LinkedList<>();

        // Split and add each node to the list
        for (String node : preorder.split(",")) {
            nodes.addLast(node);
        }

        // Try to deserialize the tree
        return deserialize(nodes) && nodes.isEmpty();
    }

    /**
     * Recursive helper function to deserialize and validate a binary tree
     *
     * @param nodes Linked list of nodes to process
     * @return True if the current subtree is valid, false otherwise
     */
    private boolean deserialize(LinkedList<String> nodes) {
        // If no more nodes to process, the serialization is incomplete
        if (nodes.isEmpty()) {
            return false;
        }

        // Get the next node
        String node = nodes.removeFirst();

        // If it's a null node, the subtree is valid
        if (node.equals("#")) {
            return true;
        }

        // For a non-null node, both left and right subtrees must be valid
        return deserialize(nodes) && deserialize(nodes);
    }
}
