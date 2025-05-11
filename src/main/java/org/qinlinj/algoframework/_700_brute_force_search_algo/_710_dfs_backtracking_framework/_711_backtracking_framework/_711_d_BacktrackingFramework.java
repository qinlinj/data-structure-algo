package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._711_backtracking_framework;

import java.util.*;

/**
 * BACKTRACKING ALGORITHM FRAMEWORK
 * ================================
 * <p>
 * Core Framework:
 * Backtracking is essentially traversing a multi-way tree where:
 * 1. Actions are taken at pre-order position (before recursion) to make choices
 * 2. Actions are taken at post-order position (after recursion) to undo choices
 * <p>
 * Framework Template:
 * ```
 * result = []
 * def backtrack(path, choice_list):
 * if end_condition_met:
 * result.add(path.copy())
 * return
 * <p>
 * for choice in choice_list:
 * # Make choice
 * path.add(choice)
 * remove choice from choice_list
 * <p>
 * # Recurse
 * backtrack(path, choice_list)
 * <p>
 * # Undo choice
 * path.remove(choice)
 * add choice back to choice_list
 * ```
 * <p>
 * Key Pattern:
 * - Take action before recursion (pre-order traversal position)
 * - Backtrack after recursion (post-order traversal position)
 * <p>
 * Characteristics:
 * - Unlike dynamic programming, backtracking usually cannot be optimized by reusing subproblems
 * - Backtracking is pure brute-force enumeration, so time complexity is typically high
 * - Most backtracking problems follow this exact same pattern
 */
public class _711_d_BacktrackingFramework {

    /**
     * Example usage: Permutation problem using the template
     */
    public static void main(String[] args) {
        // Create a subclass implementing the specific logic for permutations
        BacktrackingTemplate<Integer> permutationSolver = new BacktrackingTemplate<Integer>() {
            @Override
            protected boolean isEndConditionMet(List<Integer> path, List<Integer> availableChoices) {
                // For permutation, we're done when path contains all elements
                return availableChoices.isEmpty();
            }
        };

        // Initial set of choices
        List<Integer> numbers = List.of(1, 2, 3);

        // Solve the permutation problem
        List<List<Integer>> permutations = permutationSolver.solve(numbers);

        // Display results
        System.out.println("All permutations of [1,2,3] using the backtracking framework:");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }
    }

    /**
     * A generic backtracking framework that can be adapted to various problems
     *
     * @param <T> Type of elements in the path/choice list
     */
    public static class BacktrackingTemplate<T> {
        private List<List<T>> results = new ArrayList<>();

        /**
         * Start the backtracking process
         *
         * @param choices Initial set of all possible choices
         * @return List of all valid solutions
         */
        public List<List<T>> solve(List<T> choices) {
            backtrack(new ArrayList<>(), new ArrayList<>(choices));
            return results;
        }

        /**
         * Main backtracking method
         *
         * @param path             Current path (choices made so far)
         * @param availableChoices Remaining available choices
         */
        private void backtrack(List<T> path, List<T> availableChoices) {
            // Check if end condition is met
            if (isEndConditionMet(path, availableChoices)) {
                // Add a copy of the current path to results
                results.add(new ArrayList<>(path));
                return;
            }

            // Create a copy to avoid concurrent modification
            List<T> choices = new ArrayList<>(availableChoices);

            // Try each choice
            for (T choice : choices) {
                // Skip invalid choices
                if (!isValidChoice(choice, path)) {
                    continue;
                }

                // 1. Make choice
                path.add(choice);
                availableChoices.remove(choice);

                // 2. Explore with this choice
                backtrack(path, availableChoices);

                // 3. Undo choice (backtrack)
                path.remove(path.size() - 1);
                availableChoices.add(choice);
            }
        }

        /**
         * Check if the end condition is met
         * Override this for specific problems
         */
        protected boolean isEndConditionMet(List<T> path, List<T> availableChoices) {
            // Default implementation: end when no more choices
            return availableChoices.isEmpty();
        }

        /**
         * Check if a choice is valid in the current context
         * Override this for specific problems
         */
        protected boolean isValidChoice(T choice, List<T> path) {
            // Default implementation: all choices are valid
            return true;
        }
    }
}