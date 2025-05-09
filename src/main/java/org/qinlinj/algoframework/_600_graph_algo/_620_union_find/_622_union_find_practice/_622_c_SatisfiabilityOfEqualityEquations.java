package org.qinlinj.algoframework._600_graph_algo._620_union_find._622_union_find_practice;

import java.util.*;

/**
 * LeetCode 990: Satisfiability of Equality Equations
 * <p>
 * Problem Description:
 * Given an array of strings equations representing equations of the form "a==b" or "a!=b",
 * determine if it is possible to assign values to the variables to satisfy all the equations.
 * <p>
 * This problem demonstrates how Union-Find can be applied to equality relationships:
 * 1. Process all equality equations first, connecting variables that should be equal
 * 2. Then check inequality equations to ensure they don't contradict the equalities
 * <p>
 * Key Insights:
 * - Equality forms an equivalence relation (reflexive, symmetric, transitive)
 * - We can use Union-Find to group variables that must have the same value
 * - Inequality requires that variables belong to different groups
 * - The processing order matters: equalities first, then inequalities
 * <p>
 * Time Complexity: O(n) where n is the number of equations
 * Space Complexity: O(1) since there are at most 26 lowercase letters
 */
public class _622_c_SatisfiabilityOfEqualityEquations {

    /**
     * Test method to demonstrate the solution
     */
    public static void main(String[] args) {
        _622_c_SatisfiabilityOfEqualityEquations solution = new _622_c_SatisfiabilityOfEqualityEquations();

        // Example 1: Simple satisfiable case
        String[] equations1 = {"a==b", "b==c", "c==a"};
        System.out.println("Example 1 - Equations: " + Arrays.toString(equations1));
        System.out.println("Can be satisfied: " + solution.equationsPossible(equations1));
        // Expected: true (a=b=c is a valid assignment)

        // Example 2: Unsatisfiable due to contradiction
        String[] equations2 = {"a==b", "b!=a"};
        System.out.println("Example 2 - Equations: " + Arrays.toString(equations2));
        System.out.println("Can be satisfied: " + solution.equationsPossible(equations2));
        // Expected: false (contradiction: a==b and b!=a)

        // Example 3: Example from the problem
        String[] equations3 = {"a==b", "b!=c", "c==a"};
        System.out.println("Example 3 - Equations: " + Arrays.toString(equations3));
        System.out.println("Can be satisfied: " + solution.equationsPossible(equations3));
        // Expected: false (contradiction: a==b, c==a implies b==c, but b!=c)

        // Example 4: Another satisfiable case
        String[] equations4 = {"c==c", "b==d", "x!=z"};
        System.out.println("Example 4 - Equations: " + Arrays.toString(equations4));
        System.out.println("Can be satisfied: " + solution.equationsPossible(equations4));
        // Expected: true (c=c, b=d, x≠z is a valid assignment)

        // Example 5: More complex case
        String[] equations5 = {"a==b", "e==c", "b==c", "a!=e"};
        System.out.println("Example 5 - Equations: " + Arrays.toString(equations5));
        System.out.println("Can be satisfied: " + solution.equationsPossible(equations5));
        // Expected: false (contradiction: a==b, b==c, e==c implies a==e, but a!=e)
    }

    /**
     * Determines if the given equality and inequality equations can be satisfied
     */
    public boolean equationsPossible(String[] equations) {
        // Initialize Union-Find with 26 lowercase letters
        UnionFind uf = new UnionFind(26);

        // First pass: process all equality equations
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char var1 = equation.charAt(0);
                char var2 = equation.charAt(3);
                // Connect variables that should be equal
                uf.union(var1 - 'a', var2 - 'a');
            }
        }

        // Second pass: check inequality equations
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char var1 = equation.charAt(0);
                char var2 = equation.charAt(3);

                // If the variables are connected but should be unequal, contradiction found
                if (uf.connected(var1 - 'a', var2 - 'a')) {
                    return false;
                }
            }
        }

        // No contradictions found, equations can be satisfied
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

            // Initialize: each element is its own parent
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) return;

            // Merge components
            parent[rootP] = rootQ;
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