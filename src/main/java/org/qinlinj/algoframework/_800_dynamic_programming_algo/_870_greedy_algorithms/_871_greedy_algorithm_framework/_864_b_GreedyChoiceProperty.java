package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

public class _864_b_GreedyChoiceProperty {

    /**
     * Problem 1: Maximum value with limited number of bills
     * This problem HAS greedy choice property
     */
    public static class MaxValueProblem {

        /**
         * Greedy solution: Always choose higher denomination
         * Time Complexity: O(1)
         */
        public static int solve(int numBills) {
            // Local optimal choice: always pick 100-yuan bill
            // Global optimal solution: numBills * 100
            return numBills * 100;
        }

    }
}

