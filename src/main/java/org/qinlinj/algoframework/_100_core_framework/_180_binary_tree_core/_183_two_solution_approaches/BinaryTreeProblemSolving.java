package org.qinlinj.algoframework._100_core_framework._180_binary_tree_core._183_two_solution_approaches;


/**
 * TWO APPROACHES TO SOLVING BINARY TREE PROBLEMS
 * ==============================================
 * <p>
 * As mentioned in "My Algorithm Learning Experience," binary tree recursion solutions can be divided into two approaches:
 * <p>
 * 1. TRAVERSAL APPROACH:
 * - Traverse the entire tree once to calculate the answer
 * - Similar to the backtracking algorithm framework
 * - Function signature typically uses `void traverse(...)` with no return value
 * - Updates external variables to accumulate results
 * <p>
 * 2. PROBLEM DECOMPOSITION APPROACH:
 * - Calculate the answer by breaking down the problem into subproblems (subtrees)
 * - Similar to the dynamic programming framework
 * - Function is named according to its specific purpose and generally has a return value
 * - Return value represents the calculation result of the subproblem
 * <p>
 * The function naming convention highlights the solution's thought pattern:
 * - Functions without return values (void) indicate a traversal approach
 * - Functions with return values indicate a problem decomposition approach
 * <p>
 * For any binary tree problem, consider:
 * 1. Can you solve it by traversing the tree once? If yes, use a traverse function with external variables.
 * 2. Can you define a recursive function that solves the original problem using solutions to subproblems?
 * If yes, clearly define this function and use its return values effectively.
 * 3. Regardless of which approach you use, understand what each node needs to do and when (pre/in/post order).
 */

public class BinaryTreeProblemSolving {
}
