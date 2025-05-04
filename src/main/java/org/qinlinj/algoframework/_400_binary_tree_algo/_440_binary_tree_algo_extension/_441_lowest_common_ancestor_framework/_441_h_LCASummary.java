package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._441_lowest_common_ancestor_framework;

/**
 * Lowest Common Ancestor (LCA) Algorithms Summary
 * ---------------------------------------------------------
 * This class provides a comprehensive summary of all LCA problem variants
 * and their respective solutions.
 * <p>
 * Key takeaways:
 * 1. LCA algorithms are fundamental in computer science with applications in:
 * - Version control systems (Git)
 * - Network routing
 * - Phylogenetic trees in biology
 * - Database indexing structures
 * - UI component hierarchies
 * <p>
 * 2. Main LCA problem variants:
 * - Standard LCA (#236): Finding LCA in a binary tree
 * - Multi-node LCA (#1676): Finding LCA of multiple nodes
 * - LCA with missing nodes (#1644): Handling nodes that might not exist
 * - BST LCA (#235): Optimized algorithm for binary search trees
 * - LCA with parent pointers (#1650): When nodes have references to parents
 * <p>
 * 3. Solution patterns:
 * - Tree recursion using pre-order and post-order operations
 * - Utilizing specific tree properties (BST)
 * - Reducing to other problems (linked list intersection)
 * - Complete vs. partial tree traversal depending on constraints
 * <p>
 * 4. Efficiency considerations:
 * - Standard binary tree: O(n) time, O(h) space
 * - BST: O(h) time, O(h) space, where h is tree height
 * - Parent pointers: O(h) time, O(1) space or O(h) with hash set
 */
public class _441_h_LCASummary {

    /**
     * Comparison of the different LCA algorithms
     */
    public static void main(String[] args) {
        System.out.println("=== Lowest Common Ancestor (LCA) Algorithms Summary ===");

        System.out.println("\n1. Standard LCA (LeetCode #236)");
        System.out.println("   - Problem: Find LCA of two nodes in a binary tree");
        System.out.println("   - Approach: Recursive traversal with pre-order and post-order checks");
        System.out.println("   - Time Complexity: O(n)");
        System.out.println("   - Space Complexity: O(h) where h is the height of the tree");
        System.out.println("   - Key insight: If p and q are in different subtrees, their LCA is the first common ancestor");

        System.out.println("\n2. Multi-node LCA (LeetCode #1676)");
        System.out.println("   - Problem: Find LCA of multiple nodes in a binary tree");
        System.out.println("   - Approach: Similar to standard LCA but with a HashSet for target values");
        System.out.println("   - Time Complexity: O(n)");
        System.out.println("   - Space Complexity: O(h + k) where k is the number of target nodes");
        System.out.println("   - Key insight: Extension of standard LCA for more than two nodes");

        System.out.println("\n3. LCA with Missing Nodes (LeetCode #1644)");
        System.out.println("   - Problem: Find LCA when nodes might not exist in the tree");
        System.out.println("   - Approach: Complete tree traversal with flags to check existence");
        System.out.println("   - Time Complexity: O(n)");
        System.out.println("   - Space Complexity: O(h)");
        System.out.println("   - Key insight: Move target check to post-order position to ensure complete traversal");

        System.out.println("\n4. BST LCA (LeetCode #235)");
        System.out.println("   - Problem: Find LCA in a Binary Search Tree");
        System.out.println("   - Approach: Use BST property to navigate directly to LCA");
        System.out.println("   - Time Complexity: O(h)");
        System.out.println("   - Space Complexity: O(h) recursive, O(1) iterative");
        System.out.println("   - Key insight: BST property allows skipping unnecessary subtrees");

        System.out.println("\n5. LCA with Parent Pointers (LeetCode #1650)");
        System.out.println("   - Problem: Find LCA when nodes have parent pointers");
        System.out.println("   - Approach: Treat as linked list intersection problem");
        System.out.println("   - Time Complexity: O(h)");
        System.out.println("   - Space Complexity: O(1) with two-pointer, O(h) with HashSet");
        System.out.println("   - Key insight: Parent pointers transform the problem to finding where two paths intersect");

        System.out.println("\n=== Common Patterns Across LCA Algorithms ===");
        System.out.println("1. Tree traversal recursion is fundamental to most solutions");
        System.out.println("2. Position of operations (pre-order vs post-order) matters greatly");
        System.out.println("3. Specific data structure properties can be leveraged (BST, parent pointers)");
        System.out.println("4. Whether full tree traversal is needed depends on problem constraints");
        System.out.println("5. Search optimization is important for efficiency");

        System.out.println("\n=== Applications of LCA ===");
        System.out.println("1. Git and version control systems for branch management");
        System.out.println("2. Hierarchical routing in computer networks");
        System.out.println("3. Phylogenetic trees in computational biology");
        System.out.println("4. UI component hierarchies in front-end frameworks");
        System.out.println("5. Database indexing structures and query optimization");
    }

    /**
     * Standard LCA implementation (for reference)
     */
    private TreeNode standardLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode left = standardLCA(root.left, p, q);
        TreeNode right = standardLCA(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    /**
     * BST LCA implementation (for reference)
     */
    private TreeNode bstLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val > p.val && root.val > q.val) {
            return bstLCA(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return bstLCA(root.right, p, q);
        }

        return root;
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}