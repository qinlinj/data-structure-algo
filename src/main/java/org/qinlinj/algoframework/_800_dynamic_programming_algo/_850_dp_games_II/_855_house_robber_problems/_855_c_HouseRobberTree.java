package org.qinlinj.algoframework._800_dynamic_programming_algo._850_dp_games_II._855_house_robber_problems;


// Definition for a binary tree node
class TreeNode {
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

public class _855_c_HouseRobberTree {
    // Approach 1: Top-down with memoization
    private Map<TreeNode, Integer> memo = new HashMap<>();

    public int robWithMemo(TreeNode root) {
        if (root == null) return 0;

        // Check memoization
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        // Option 1: Rob current node, skip children, rob grandchildren
        int robCurrent = root.val;
        if (root.left != null) {
            robCurrent += robWithMemo(root.left.left) + robWithMemo(root.left.right);
        }
        if (root.right != null) {
            robCurrent += robWithMemo(root.right.left) + robWithMemo(root.right.right);
        }

        // Option 2: Don't rob current node, rob children optimally
        int skipCurrent = robWithMemo(root.left) + robWithMemo(root.right);

        int result = Math.max(robCurrent, skipCurrent);
        memo.put(root, result);
        return result;
    }
}
