package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._723_backtracking_dfs_questions; /**
 * DIFFERENCE BETWEEN BACKTRACKING AND DFS ALGORITHMS
 * <p>
 * This class explains the key differences and similarities between backtracking and DFS algorithms.
 * <p>
 * Key insights:
 * 1. Both backtracking and DFS are essentially the same in that they use "traversal" thinking
 * to perform exhaustive enumeration.
 * 2. The only real difference is their focus:
 * - Backtracking focuses on "branches" (edges) of the tree
 * - DFS focuses on "nodes" of the tree
 * 3. The choice between them depends on the problem requirements
 * <p>
 * We'll demonstrate both approaches using a multi-way tree traversal example.
 */

import java.util.*;

public class _723_a_BacktrackingVsDFS {

    /**
     * Standard multi-way tree traversal
     */
    public static void traverse(Node root) {
        if (root == null) {
            return;
        }

        for (Node child : root.children) {
            traverse(child);
        }
    }

    /**
     * Backtracking framework applied to multi-way tree traversal
     * Focus is on the branches (edges) between nodes
     */
    public static void backtrack(Node root) {
        if (root == null) {
            return;
        }

        for (Node child : root.children) {
            // Make choice (focus on the branch between root and child)
            System.out.printf("I'm making a choice on the branch between %s and %s\n", root, child);

            backtrack(child);

            // Undo choice (focus on the branch between root and child)
            System.out.printf("I'm undoing a choice on the branch between %s and %s\n", root, child);
        }
    }

    /**
     * DFS framework applied to multi-way tree traversal
     * Focus is on the nodes themselves
     */
    public static void dfs(Node root) {
        if (root == null) {
            return;
        }

        // Make choice (focus on the current node)
        System.out.printf("I'm making a choice at node %s\n", root);

        for (Node child : root.children) {
            dfs(child);
        }

        // Undo choice (focus on the current node)
        System.out.printf("I'm undoing a choice at node %s\n", root);
    }

    /**
     * DFS example to print all node values in a multi-way tree
     */
    public static void dfsPrint(Node root) {
        if (root == null) {
            return;
        }

        // Process the current node
        System.out.println(root.val);

        // Recurse on children
        for (Node child : root.children) {
            dfsPrint(child);
        }
    }

    /**
     * Attempting the same with backtracking would miss the root node
     * unless handled separately
     */
    public static void backtrackPrint(Node root) {
        if (root == null) {
            return;
        }

        for (Node child : root.children) {
            // This would miss printing the root node!
            System.out.println(child.val);
            backtrackPrint(child);
        }
    }

    /**
     * This version fixes the issue by handling the root node separately
     */
    public static void backtrackPrintFixed(Node root) {
        if (root == null) {
            return;
        }

        // Handle root separately
        System.out.println(root.val);

        for (Node child : root.children) {
            backtrackPrintFixed(child);
        }
    }

    /**
     * Main method with example usage
     */
    public static void main(String[] args) {
        // Create a simple multi-way tree
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node grandchild1 = new Node(5);
        Node grandchild2 = new Node(6);

        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);
        child1.children.add(grandchild1);
        child2.children.add(grandchild2);

        System.out.println("BACKTRACKING VS DFS COMPARISON");
        System.out.println("==============================");

        System.out.println("\nBacktracking example (focus on branches):");
        backtrack(root);

        System.out.println("\nDFS example (focus on nodes):");
        dfs(root);

        System.out.println("\nDFS print all node values:");
        dfsPrint(root);

        System.out.println("\nBacktracking print would miss the root:");
        System.out.println("(Printing children only in the loop)");
        // Would miss the root node, so let's not run it
        // backtrackPrint(root);

        System.out.println("\nFixed backtracking print (handling root separately):");
        backtrackPrintFixed(root);

        System.out.println("\nConclusion:");
        System.out.println("- Both approaches traverse the entire tree");
        System.out.println("- Backtracking focuses on edges (choices between nodes)");
        System.out.println("- DFS focuses on nodes themselves");
        System.out.println("- Choose based on problem requirements");
        System.out.println("- For permutations/combinations/subsets, backtracking is more natural");
        System.out.println("  as we're making choices on the branches (adding/removing elements)");
    }

    /**
     * Node class for a multi-way tree
     */
    static class Node {
        int val;
        List<Node> children;

        Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node(" + val + ")";
        }
    }
}