package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._435_level_order_traversal_practice_II; /**
 * Summary of Advanced Binary Tree Traversal Techniques
 * <p>
 * This class provides a comprehensive overview of specialized traversal techniques
 * for binary trees, focusing on more complex applications beyond standard traversals.
 * <p>
 * Key Patterns and Insights:
 * <p>
 * 1. Converting Trees to Graphs:
 * - Parent Pointers: Track parent-child relationships to enable upward traversal
 * - Adjacency Lists: Represent trees as undirected graphs for more flexible traversal
 * - Applications: Finding nodes at distance K, path finding in trees
 * <p>
 * 2. Iterator-Based Tree Traversal:
 * - Stack-Based Simulation: Using stacks to simulate recursive traversal
 * - On-Demand Processing: Generate tree elements only when needed
 * - Applications: Comparing tree properties, efficient memory usage for large trees
 * <p>
 * 3. Dynamic Tree Modifications:
 * - Complete Binary Tree Properties: Leveraging level-order properties for insertions
 * - Queue-Based Node Tracking: Maintaining insertable nodes for efficient operations
 * - Applications: Tree builders, heap implementations, tree balancing
 * <p>
 * 4. Custom Tree Traversal Orders:
 * - Targeted Traversal: Finding specific node types (e.g., leaves)
 * - Multi-directional Traversal: Combining upward and downward movement
 * - Applications: Tree comparison, finding patterns within trees
 * <p>
 * Time Complexity Analysis:
 * - Most solutions maintain O(n) time complexity for traversal
 * - Advanced data structures can achieve O(1) for specific operations
 * - Space complexity ranges from O(h) to O(n) depending on approach
 */

import java.util.*;

class _435_d_AdvancedTreeTraversalSummary {
    // Main method with a summary of the covered topics
    public static void main(String[] args) {
        System.out.println("Advanced Binary Tree Traversal Techniques Summary");
        System.out.println("------------------------------------------------");
        System.out.println("Problems covered in this module:");

        System.out.println("\n1. Complete Binary Tree Inserter (Problem 919)");
        System.out.println("   - Maintains a queue of nodes that can accept children");
        System.out.println("   - Inserts new nodes in level order to preserve completeness");
        System.out.println("   - O(1) time complexity for most operations after initialization");

        System.out.println("\n2. Leaf-Similar Trees (Problem 872)");
        System.out.println("   - Uses iterator pattern to generate leaf sequences");
        System.out.println("   - Stack-based simulation of recursive traversal");
        System.out.println("   - Allows on-demand processing of tree elements");

        System.out.println("\n3. All Nodes Distance K in Binary Tree (Problem 863)");
        System.out.println("   - Converts binary tree to undirected graph");
        System.out.println("   - Uses BFS to find nodes at specific distance");
        System.out.println("   - Demonstrates tree-to-graph transformation technique");

        System.out.println("\n------------------------------------------------");
        System.out.println("Key implementation techniques:");
        System.out.println("1. Parent Mapping: Enable upward traversal in trees");
        System.out.println("2. Stack-Based Iterators: Efficient on-demand traversal");
        System.out.println("3. Tree-to-Graph Conversion: Flexible navigation in all directions");
        System.out.println("4. Queue-Based Node Tracking: Maintain state for dynamic tree operations");

        System.out.println("\nAdvanced applications of these techniques:");
        System.out.println("- Tree serialization and deserialization");
        System.out.println("- Path finding in trees (without backtracking)");
        System.out.println("- Dynamic tree modifications (insertions, deletions)");
        System.out.println("- Structure-based tree comparisons");
    }

    /**
     * Demonstrates converting a binary tree to a graph with parent pointers
     */
    public Map<Integer, TreeNode> buildParentMap(TreeNode root) {
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        buildParentMapHelper(root, null, parentMap);
        return parentMap;
    }

    private void buildParentMapHelper(TreeNode node, TreeNode parent, Map<Integer, TreeNode> parentMap) {
        if (node == null) {
            return;
        }

        // Store parent reference
        parentMap.put(node.val, parent);

        // Process children
        buildParentMapHelper(node.left, node, parentMap);
        buildParentMapHelper(node.right, node, parentMap);
    }

    /**
     * Demonstrates converting a binary tree to a full graph (adjacency list)
     */
    public Map<Integer, List<Integer>> buildTreeGraph(TreeNode root) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildTreeGraphHelper(root, null, graph);
        return graph;
    }

    private void buildTreeGraphHelper(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (node == null) {
            return;
        }

        // Ensure node has an entry in the graph
        if (!graph.containsKey(node.val)) {
            graph.put(node.val, new ArrayList<>());
        }

        // Add edge to parent (if exists)
        if (parent != null) {
            graph.get(node.val).add(parent.val);

            // Ensure parent has an entry and add edge to current node
            if (!graph.containsKey(parent.val)) {
                graph.put(parent.val, new ArrayList<>());
            }
            graph.get(parent.val).add(node.val);
        }

        // Process children
        buildTreeGraphHelper(node.left, node, graph);
        buildTreeGraphHelper(node.right, node, graph);
    }

    /**
     * Finds all nodes at distance k from a target node
     */
    public List<Integer> nodesAtDistanceK(TreeNode root, TreeNode target, int k) {
        // Step 1: Build parent references
        Map<Integer, TreeNode> parentMap = buildParentMap(root);

        // Step 2: Use BFS to find nodes at distance k
        Queue<TreeNode> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        queue.offer(target);
        visited.add(target.val);
        int distance = 0;

        while (!queue.isEmpty() && distance <= k) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                if (distance == k) {
                    result.add(current.val);
                }

                // Explore in all directions (left, right, parent)
                if (current.left != null && !visited.contains(current.left.val)) {
                    visited.add(current.left.val);
                    queue.offer(current.left);
                }

                if (current.right != null && !visited.contains(current.right.val)) {
                    visited.add(current.right.val);
                    queue.offer(current.right);
                }

                TreeNode parent = parentMap.get(current.val);
                if (parent != null && !visited.contains(parent.val)) {
                    visited.add(parent.val);
                    queue.offer(parent);
                }
            }

            distance++;
        }

        return result;
    }

    /**
     * Checks if two trees have the same leaf sequence
     */
    public boolean areLeafSimilar(TreeNode root1, TreeNode root2) {
        LeafIterator it1 = new LeafIterator(root1);
        LeafIterator it2 = new LeafIterator(root2);

        while (it1.hasNext() && it2.hasNext()) {
            TreeNode leaf1 = it1.next();
            TreeNode leaf2 = it2.next();

            if (leaf1.val != leaf2.val) {
                return false;
            }
        }

        // Both iterators should be exhausted for trees to be leaf-similar
        return !it1.hasNext() && !it2.hasNext();
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

    /**
     * Iterator pattern for leaf nodes
     */
    public class LeafIterator {
        private Stack<TreeNode> stack = new Stack<>();

        public LeafIterator(TreeNode root) {
            if (root != null) {
                stack.push(root);
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public TreeNode next() {
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();

                // If leaf node, return it
                if (node.left == null && node.right == null) {
                    return node;
                }

                // Push children in reverse order
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            return null;
        }
    }

    /**
     * Complete Binary Tree Inserter data structure
     */
    public class CBTInserter {
        private Queue<TreeNode> insertableNodes = new LinkedList<>();
        private TreeNode root;

        public CBTInserter(TreeNode root) {
            this.root = root;

            // Find nodes that can accept children
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                // Add child nodes to the traversal queue
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                // If node has room for children, add to insertableNodes
                if (node.left == null || node.right == null) {
                    insertableNodes.offer(node);
                }
            }
        }

        public int insert(int val) {
            TreeNode newNode = new TreeNode(val);
            TreeNode parent = insertableNodes.peek();

            if (parent.left == null) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
                insertableNodes.poll(); // Parent is full, remove it
            }

            // New node can accept children in the future
            insertableNodes.offer(newNode);

            return parent.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}