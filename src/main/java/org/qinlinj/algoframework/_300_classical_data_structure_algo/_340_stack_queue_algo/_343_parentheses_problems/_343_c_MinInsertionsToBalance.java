package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._343_parentheses_problems;

/**
 * Problem 3: Minimum Insertions to Balance Parentheses (LeetCode 1541)
 * <p>
 * In this problem, the matching rule is special:
 * - Each left bracket '(' must match with TWO consecutive right brackets '))'
 * - Left brackets must appear before their matching right brackets
 * <p>
 * Find the minimum number of brackets to insert to make the string valid.
 * <p>
 * Examples:
 * "(()))" -> 1  (need one more right bracket to make "(()))")
 * "(((((((" -> 12  (need twelve right brackets)
 * "))))))))" -> 5  (need four left brackets at the beginning and one right at the end)
 */
class _343_c_MinInsertionsToBalance {

    public int minInsertions(String s) {
        // Track number of insertions needed
        int insertions = 0;
        // Track demand for right brackets (each left bracket needs TWO right brackets)
        int need = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Each left bracket creates demand for TWO right brackets
                need += 2;

                // Special case: if need is odd, we need to insert a right bracket
                // to make the previous left bracket have two right brackets
                if (need % 2 == 1) {
                    insertions++;
                    need--; // We inserted one right bracket
                }
            } else if (s.charAt(i) == ')') {
                need--;

                // If need becomes negative, we have an unmatched right bracket
                // We need to insert a left bracket (which will need two right brackets)
                if (need == -1) {
                    insertions++; // Insert a left bracket
                    need = 1;     // Now we need one more right bracket
                }
            }
        }

        // Any remaining need means we need to add right brackets
        return insertions + need;
    }
}