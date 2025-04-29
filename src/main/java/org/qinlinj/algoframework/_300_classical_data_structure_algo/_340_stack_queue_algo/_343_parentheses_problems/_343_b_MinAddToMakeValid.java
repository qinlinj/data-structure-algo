package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._343_parentheses_problems;

/**
 * Problem 2: Minimum Add to Make Parentheses Valid (LeetCode 921)
 * <p>
 * Given a string of parentheses, find the minimum number of parentheses to add
 * to make the string valid.
 * <p>
 * Examples:
 * "())" -> 1  (need to add one left bracket)
 * "(((" -> 3  (need to add three right brackets)
 */
class _343_b_MinAddToMakeValid {

    public int minAddToMakeValid(String s) {
        // Track number of insertions needed
        int insertions = 0;
        // Track demand for right brackets
        int need = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Each left bracket creates demand for one right bracket
                need++;
            } else if (s.charAt(i) == ')') {
                need--;

                // If need becomes negative, we have an unmatched right bracket
                // We need to insert a left bracket
                if (need < 0) {
                    insertions++;
                    need = 0; // Reset the need counter
                }
            }
        }

        // Any remaining positive need means we need to add right brackets
        return insertions + need;
    }
}
