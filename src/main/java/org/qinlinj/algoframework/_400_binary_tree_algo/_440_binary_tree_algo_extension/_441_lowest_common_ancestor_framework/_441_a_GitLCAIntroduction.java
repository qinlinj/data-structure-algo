package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._441_lowest_common_ancestor_framework;

import java.util.*;

/**
 * Introduction to Lowest Common Ancestor (LCA) Algorithm
 * ---------------------------------------------------------
 * This class introduces the Lowest Common Ancestor problem through Git examples.
 * <p>
 * Key points:
 * 1. Git uses LCA to identify conflicts between branches
 * 2. When rebasing a branch, Git finds the LCA between branches and applies changes
 * 3. Git detects conflicts by comparing changes made on both branches since their LCA
 * 4. LCA is a fundamental algorithm in computer science with various applications
 * 5. In Git's context, LCA helps determine where branches diverged
 */
public class _441_a_GitLCAIntroduction {

    /**
     * This is a conceptual representation of how Git might identify
     * the LCA between two branches before a rebase operation.
     * <p>
     * Note: This is a simplified representation for educational purposes.
     * Git's actual implementation is more complex.
     */
    public static CommitNode findGitLCA(CommitNode branch1Head, CommitNode branch2Head) {
        // A set to track visited commits in the first branch
        Set<CommitNode> branch1Commits = new HashSet<>();

        // Traverse up the first branch and mark all commits
        CommitNode current = branch1Head;
        while (current != null) {
            branch1Commits.add(current);
            current = current.parent;
        }

        // Traverse up the second branch until we find a common commit
        current = branch2Head;
        while (current != null) {
            if (branch1Commits.contains(current)) {
                return current; // This is the LCA
            }
            current = current.parent;
        }

        return null; // No common ancestor found (should never happen in Git)
    }

    // Sample usage demonstrating the concept
    public static void main(String[] args) {
        // Create a simple commit history:
        //       A
        //      /
        // C -- B -- D -- master
        //            \
        //             E -- F -- dev

        CommitNode C = new CommitNode("C", null);
        CommitNode B = new CommitNode("B", C);
        CommitNode A = new CommitNode("A", B);
        CommitNode D = new CommitNode("D", B);
        CommitNode E = new CommitNode("E", D);
        CommitNode F = new CommitNode("F", E);

        // master branch head is D, dev branch head is F
        CommitNode master = D;
        CommitNode dev = F;

        // Find LCA
        CommitNode lca = findGitLCA(master, dev);
        System.out.println("The LCA of master and dev is: " + lca.commitId);
        // Should output: The LCA of master and dev is: B
    }

    // Simple representation of a Git commit node
    static class CommitNode {
        String commitId;
        CommitNode parent;

        public CommitNode(String commitId, CommitNode parent) {
            this.commitId = commitId;
            this.parent = parent;
        }
    }
}