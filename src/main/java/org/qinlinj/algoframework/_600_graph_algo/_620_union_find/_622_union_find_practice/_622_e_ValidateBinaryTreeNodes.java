package org.qinlinj.algoframework._600_graph_algo._620_union_find._622_union_find_practice;

import java.util.*;

/**
 * LeetCode 1361: Validate Binary Tree Nodes
 * <p>
 * Problem Description:
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children
 * leftChild[i] and rightChild[i]. If node i has no left child, then leftChild[i] is -1,
 * and similarly for the right child.
 * <p>
 * Return true if and only if all the given nodes form exactly one valid binary tree.
 * <p>
 * This problem demonstrates how Union-Find can be used to validate tree structures:
 * 1. A valid binary tree has exactly n-1 edges for n nodes
 * 2. Each node (except the root) must have exactly one parent
 * 3. There must be no cycles
 * 4. All nodes must be connected (one component)
 * <p>
 * Key Insights:
 * - Union-Find helps detect cycles in the graph
 * - We need to track node indegrees to ensure the proper tree structure
 * - A valid tree will have exactly one root with no parent
 * - All other nodes must have exactly one parent
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(n)
 */
public class _622_e_ValidateBinaryTreeNodes {

    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        _622_e_ValidateBinaryTreeNodes solution = new _622_e_ValidateBinaryTreeNodes();

        // Example 1: Valid binary tree
        int n1 = 4;
        int[] leftChild1 = {1, -1, 3, -1};
        int[] rightChild1 = {2, -1, -1, -1};
        System.out.println("Example 1:");
        printTree(n1, leftChild1, rightChild1);
        System.out.println("Is valid binary tree? " + solution.validateBinaryTreeNodes(n1, leftChild1, rightChild1));
        // Expected: true

        // Example 2: Invalid - has a cycle
        int n2 = 4;
        int[] leftChild2 = {1, -1, 3, -1};
        int[] rightChild2 = {2, 3, -1, -1};
        System.out.println("\nExample 2:");
        printTree(n2, leftChild2, rightChild2);
        System.out.println("Is valid binary tree? " + solution.validateBinaryTreeNodes(n2, leftChild2, rightChild2));
        // Expected: false (nodes 2 and 3 form a cycle)

        // Example 3: Invalid - has a cycle through root
        int n3 = 2;
        int[] leftChild3 = {1, 0};
        int[] rightChild3 = {-1, -1};
        System.out.println("\nExample 3:");
        printTree(n3, leftChild3, rightChild3);
        System.out.println("Is valid binary tree? " + solution.validateBinaryTreeNodes(n3, leftChild3, rightChild3));
        // Expected: false (nodes 0 and 1 form a cycle)
    }

    /**
     * Helper method to print the tree structure
     */
    private static void printTree(int n, int[] leftChild, int[] rightChild) {
        System.out.println("Number of nodes: " + n);
        System.out.println("Left children: " + Arrays.toString(leftChild));
        System.out.println("Right children: " + Arrays.toString(rightChild));
    }

    /**
     * Validates if the given child arrays form exactly one valid binary tree
     */
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // Step 1: Calculate the indegree (number of parents) for each node
        int[] indegree = new int[n];

        // Count indegrees from left children
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                indegree[leftChild[i]]++;
                // A valid binary tree node can only have one parent
                if (indegree[leftChild[i]] > 1) {
                    return false;
                }
            }
        }

        // Count indegrees from right children
        for (int i = 0; i < n; i++) {
            if (rightChild[i] != -1) {
                indegree[rightChild[i]]++;
                // A valid binary tree node can only have one parent
                if (indegree[rightChild[i]] > 1) {
                    return false;
                }
            }
        }

        // Step 2: Find the root (the only node with no parent)
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                // If we already found a root, this would be a second root, which is invalid
                if (root != -1) {
                    return false;
                }
                root = i;
            }
        }

        // If there's no root, it's invalid (must have a cycle)
        if (root == -1) {
            return false;
        }

        // Step 3: Use Union-Find to check if the structure forms a single connected component without cycles
        UnionFind uf = new UnionFind(n);

        // Process all edges
        for (int i = 0; i < n; i++) {
            // Check left child
            if (leftChild[i] != -1) {
                // If already connected, adding this edge would create a cycle
                if (uf.connected(i, leftChild[i])) {
                    return false;
                }
                uf.union(i, leftChild[i]);
            }

            // Check right child
            if (rightChild[i] != -1) {
                // If already connected, adding this edge would create a cycle
                if (uf.connected(i, rightChild[i])) {
                    return false;
                }
                uf.union(i, rightChild[i]);
            }
        }

        // Step 4: Ensure all nodes form a single connected component
        return uf.count() == 1;
    }

    /**
     * Alternative solution using DFS to detect cycles and check connectivity
     */
    public boolean validateBinaryTreeNodesDFS(int n, int[] leftChild, int[] rightChild) {
        // Calculate indegrees as before
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                indegree[leftChild[i]]++;
            }
            if (rightChild[i] != -1) {
                indegree[rightChild[i]]++;
            }
        }

        // Find the root
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                if (root != -1) {
                    return false; // Multiple roots
                }
                root = i;
            } else if (indegree[i] > 1) {
                return false; // Multiple parents
            }
        }

        if (root == -1) {
            return false; // No root
        }

        // Use DFS to check connectivity and cycles
        boolean[] visited = new boolean[n];
        if (!dfs(root, leftChild, rightChild, visited)) {
            return false; // Cycle detected
        }

        // Check if all nodes are visited
        for (boolean v : visited) {
            if (!v) {
                return false; // Not all nodes connected
            }
        }

        return true;
    }

    private boolean dfs(int node, int[] leftChild, int[] rightChild, boolean[] visited) {
        // If already visited, we have a cycle
        if (visited[node]) {
            return false;
        }

        visited[node] = true;

        // Check left child
        if (leftChild[node] != -1) {
            if (!dfs(leftChild[node], leftChild, rightChild, visited)) {
                return false;
            }
        }

        // Check right child
        if (rightChild[node] != -1) {
            if (!dfs(rightChild[node], leftChild, rightChild, visited)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Union-Find implementation
     */
    class UnionFind {
        private int count;
        private int[] parent;

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) return;

            parent[rootQ] = rootP;
            count--;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int count() {
            return count;
        }
    }
}