package org.qinlinj.algoframework._400_binary_tree_algo._430_binary_tree_algo_practice_II._434_level_order_traversal_practice_I; /**
 * Summary of Binary Tree Level Order Traversal Techniques and Applications
 * <p>
 * This class provides a comprehensive overview of level order traversal (BFS) techniques
 * for binary trees and their applications in solving various tree-related problems.
 * <p>
 * Key Patterns and Insights:
 * <p>
 * 1. Core Level Order Traversal Pattern:
 * - Use a queue to track nodes at each level
 * - Process levels from top to bottom using an outer while loop
 * - Process nodes within each level from left to right using an inner for loop
 * - The size of the queue at the beginning of each outer loop iteration represents the level size
 * <p>
 * 2. Common Variations:
 * - Standard (top-down): Process and return levels in original order
 * - Bottom-up: Reverse the order of levels using LinkedList.addFirst()
 * - Zigzag: Alternate between left-to-right and right-to-left traversal within levels
 * - Targeted level processing: Calculate properties like max, sum, or average for each level
 * <p>
 * 3. Augmentations to Basic Pattern:
 * - Level numbering: Track level numbers for problems requiring specific level identification
 * - Node indexing: Assign position indices to nodes for width-related problems
 * - Connection building: Establish next pointers between nodes at the same level
 * - Property validation: Check level-specific properties (e.g., monotonicity, parity)
 * <p>
 * 4. Trade-offs vs. DFS:
 * - BFS excels at level-related problems but typically requires more space
 * - DFS can sometimes solve the same problems with a depth-tracking parameter
 * - BFS is more intuitive for problems specifically mentioning levels or width
 * <p>
 * Time Complexity: O(n) for all problems, where n is the number of nodes
 * Space Complexity: O(w) where w is the maximum width of the tree (number of nodes at the widest level)
 */

import java.util.*;

class _434_l_LevelOrderSummary {
    // Main method with a summary of the covered problems
    public static void main(String[] args) {
        System.out.println("Binary Tree Level Order Traversal Techniques Summary");
        System.out.println("-----------------------------------------------------");
        System.out.println("Problems covered in this module:");

        System.out.println("\n1. Basic Level Order Traversal (Problem 102)");
        System.out.println("   - Standard BFS traversal returning levels from top to bottom");

        System.out.println("\n2. Bottom-up Level Order Traversal (Problem 107)");
        System.out.println("   - Modification of standard BFS to return levels from bottom to top");
        System.out.println("   - Uses LinkedList.addFirst() to reverse the order of levels");

        System.out.println("\n3. Zigzag Level Order Traversal (Problem 103)");
        System.out.println("   - Alternative left-to-right and right-to-left traversal within levels");
        System.out.println("   - Uses a boolean flag to control direction and LinkedList for efficient insertion");

        System.out.println("\n4. Connect Right Pointers (Problem 117)");
        System.out.println("   - Uses BFS to connect nodes at the same level via next pointers");
        System.out.println("   - Maintains a reference to previous node to establish connections");

        System.out.println("\n5. Maximum Width of Binary Tree (Problem 662)");
        System.out.println("   - Tracks position indices for nodes to calculate width");
        System.out.println("   - Uses the formula: left child = 2*parent, right child = 2*parent + 1");

        System.out.println("\n6. Find Largest Value in Each Row (Problem 515)");
        System.out.println("   - Calculates maximum value at each level during BFS");
        System.out.println("   - Can be solved with either BFS or DFS approaches");

        System.out.println("\n7. Average of Levels in Binary Tree (Problem 637)");
        System.out.println("   - Calculates average of all node values at each level");
        System.out.println("   - Uses double for precision in average calculation");

        System.out.println("\n8. Check Completeness of a Binary Tree (Problem 958)");
        System.out.println("   - Uses BFS with null nodes to verify completeness");
        System.out.println("   - Key insight: After first null, all subsequent nodes must be null");

        System.out.println("\n9. Maximum Level Sum of a Binary Tree (Problem 1161)");
        System.out.println("   - Tracks level with maximum sum and level number");
        System.out.println("   - Returns smallest level number in case of ties");

        System.out.println("\n10. Deepest Leaves Sum (Problem 1302)");
        System.out.println("   - Efficiently calculates sum of deepest level");
        System.out.println("   - Leverages fact that the last level processed is the deepest");

        System.out.println("\n11. Even-Odd Tree (Problem 1609)");
        System.out.println("   - Validates level-specific properties during traversal");
        System.out.println("   - Checks both value parity and monotonicity constraints");

        System.out.println("\n-----------------------------------------------------");
        System.out.println("Key implementation techniques:");
        System.out.println("1. Use LinkedList for efficient operations at both ends");
        System.out.println("2. Track level number for level-specific processing");
        System.out.println("3. Maintain previous node references when needed for comparisons or connections");
        System.out.println("4. Consider both BFS and DFS approaches for level-processing problems");
        System.out.println("5. For specialized problems, adapt the standard template by adding appropriate state variables");
    }

    /**
     * Standard Level Order Traversal Template (BFS)
     * This template can be adapted for all level order traversal problems
     */
    public List<List<Integer>> standardLevelOrderTemplate(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Process levels from top to bottom
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process nodes in current level from left to right
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // Process current node
                currentLevel.add(node.val);

                // Add children to queue for next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // Add current level to result
            result.add(currentLevel);
        }

        return result;
    }

    /**
     * Demonstrates how to modify the standard template for various problems
     */
    public void levelOrderVariations() {
        // Sample tree just for illustration
        TreeNode root = new TreeNode(1);

        // 1. Bottom-up level order
        List<List<Integer>> bottomUp = new LinkedList<>();
        // ... standard BFS code ...
        // bottomUp.addFirst(currentLevel);  // Add to front instead of back

        // 2. Zigzag level order
        boolean leftToRight = true;
        // ... inside the loop ...
        // if (!leftToRight) Collections.reverse(currentLevel);
        // leftToRight = !leftToRight;

        // 3. Find maximum value in each level
        List<Integer> maxValues = new ArrayList<>();
        // ... inside the level loop ...
        // int max = Integer.MIN_VALUE;
        // for (each node) max = Math.max(max, node.val);
        // maxValues.add(max);

        // 4. Calculate level averages
        List<Double> averages = new ArrayList<>();
        // ... inside the level loop ...
        // double sum = 0;
        // for (each node) sum += node.val;
        // averages.add(sum / levelSize);

        // 5. Check tree completeness
        boolean endReached = false;
        // ... inside the node loop ...
        // if (node == null) endReached = true;
        // else if (endReached) return false;
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
}