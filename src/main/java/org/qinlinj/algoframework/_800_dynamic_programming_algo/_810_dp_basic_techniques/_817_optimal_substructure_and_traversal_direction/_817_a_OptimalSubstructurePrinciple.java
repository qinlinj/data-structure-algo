package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * Class: Optimal Substructure Principle
 * <p>
 * Key Points:
 * 1. Optimal Substructure: A property where the optimal solution to a problem can be constructed from
 * optimal solutions of its subproblems. Not exclusive to dynamic programming problems.
 * 2. Many problems have optimal substructure, but only those with overlapping subproblems
 * are classified as dynamic programming problems.
 * 3. For optimal substructure to work, subproblems must be independent of each other.
 * 4. When optimal substructure fails, we may need to reformulate the problem.
 * 5. Problems that involve finding maximum/minimum values often have optimal substructure.
 */
public class _817_a_OptimalSubstructurePrinciple {

    // Example 1: Finding maximum value in a binary tree (has optimal substructure)
    public int maxVal(TreeNode root) {
        if (root == null)
            return -1;
        int left = maxVal(root.left);
        int right = maxVal(root.right);
        return Math.max(root.val, Math.max(left, right));
    }

    // Example 2: School score problem (has optimal substructure)
    public int findMaxScoreInSchool(int[] classMaxScores) {
        int maxScore = Integer.MIN_VALUE;
        for (int score : classMaxScores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    // Example 3: Maximum score difference problem (doesn't have optimal substructure initially)
    // Brute force approach when optimal substructure is not present
    public int maxScoreDifferenceBruteForce(int[] scores) {
        int maxDiff = 0;
        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores.length; j++) {
                if (i == j) continue;
                maxDiff = Math.max(maxDiff, Math.abs(scores[i] - scores[j]));
            }
        }
        return maxDiff;
    }

    // Example 4: Reformulated maximum score difference problem (now has optimal substructure)
    public int maxScoreDifferenceOptimal(int[] scores) {
        if (scores.length == 0) return 0;

        int maxScore = scores[0];
        int minScore = scores[0];

        for (int score : scores) {
            maxScore = Math.max(maxScore, score);
            minScore = Math.min(minScore, score);
        }

        return maxScore - minScore;
    }

    // TreeNode class for the binary tree example
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}