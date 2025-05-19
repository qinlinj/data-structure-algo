package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._817_optimal_substructure_and_traversal_direction;

/**
 * OPTIMAL SUBSTRUCTURE IN DYNAMIC PROGRAMMING
 * <p>
 * Key Points:
 * <p>
 * 1. "Optimal Substructure" is a property where the optimal solution to a problem
 * can be constructed from optimal solutions of its subproblems.
 * <p>
 * 2. Many problems have optimal substructure, but only those with overlapping
 * subproblems are classified as dynamic programming problems.
 * <p>
 * 3. Examples of optimal substructure:
 * - Finding the highest score among multiple classes: the highest score in the
 * school can be found by comparing the highest scores from each class.
 * - Finding the maximum value in a binary tree: the max value at any node can be
 * determined by comparing the node's value with the max values of its subtrees.
 * <p>
 * 4. Counter-example: Finding the largest score difference across the school cannot be
 * derived from the largest score differences within each class - subproblems are not
 * independent in this case.
 * <p>
 * 5. When a problem doesn't naturally have optimal substructure, we should try to
 * transform it into an equivalent problem that does.
 * <p>
 * 6. Optimal substructure is necessary for dynamic programming, but not all problems
 * with optimal substructure require dynamic programming (only those with overlapping
 * subproblems).
 */
public class _817_a_OptimalSubstructure {

    /**
     * Example 1: Finding the maximum value in a binary tree
     * This demonstrates optimal substructure - the max value of a tree can be constructed
     * from the max values of its subtrees.
     */
    public static int maxVal(TreeNode root) {
        if (root == null)
            return -1;

        int left = maxVal(root.left);
        int right = maxVal(root.right);

        return Math.max(root.val, Math.max(left, right));
    }

    /**
     * Example 2: Finding the maximum score difference (bad approach)
     * This approach doesn't leverage optimal substructure and uses brute force.
     */
    public static int maxScoreDifferenceBrute(Student[] school) {
        int result = 0;
        for (Student a : school) {
            for (Student b : school) {
                if (a == b) continue;
                result = Math.max(result, Math.abs(a.score - b.score));
            }
        }
        return result;
    }

    /**
     * Example 2: Finding the maximum score difference (optimal approach)
     * We transform the problem to use optimal substructure: max difference = max score - min score
     */
    public static int maxScoreDifferenceOptimal(Student[] school) {
        if (school == null || school.length == 0) return 0;

        int maxScore = school[0].score;
        int minScore = school[0].score;

        for (Student student : school) {
            maxScore = Math.max(maxScore, student.score);
            minScore = Math.min(minScore, student.score);
        }

        return maxScore - minScore;
    }

    public static void main(String[] args) {
        // Example 1: Binary Tree
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);

        System.out.println("Maximum value in the tree: " + maxVal(root));

        // Example 2: Student scores
        Student[] school = new Student[5];
        school[0] = new Student(85);
        school[1] = new Student(92);
        school[2] = new Student(78);
        school[3] = new Student(95);
        school[4] = new Student(88);

        System.out.println("Maximum score difference (brute force): " + maxScoreDifferenceBrute(school));
        System.out.println("Maximum score difference (optimal): " + maxScoreDifferenceOptimal(school));
    }

    // Helper classes for the examples
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Student {
        int score;

        Student(int score) {
            this.score = score;
        }
    }
}