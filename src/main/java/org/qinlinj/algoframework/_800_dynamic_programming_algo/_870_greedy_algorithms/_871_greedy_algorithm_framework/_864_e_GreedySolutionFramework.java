package org.qinlinj.algoframework._800_dynamic_programming_algo._870_greedy_algorithms._871_greedy_algorithm_framework;

/**
 * GREEDY ALGORITHM SOLUTION FRAMEWORK
 * <p>
 * General Approach to Solving Problems with Greedy Algorithms:
 * <p>
 * 1. ALGORITHM ESSENCE: All algorithms are fundamentally exhaustive search
 * 2. STANDARD PROCESS:
 * a) Start with brute force exhaustive approach
 * b) If timeout occurs, look for optimizations
 * c) Check for greedy choice property
 * d) Implement greedy solution if applicable
 * <p>
 * 3. IDENTIFICATION STRATEGY:
 * - Don't try to identify greedy problems upfront
 * - Start with brute force thinking
 * - Look for patterns where local optimal choices lead to global optimum
 * - Greedy is an optimization technique, not a starting point
 * <p>
 * 4. WHEN TO USE GREEDY:
 * - Brute force solution times out
 * - Can eliminate subproblems without full exploration
 * - Local optimal choices contribute to global optimum
 * - Can make irrevocable decisions at each step
 * <p>
 * 5. PROBLEM-SOLVING METHODOLOGY:
 * Step 1: Understand the problem completely
 * Step 2: Design brute force solution (exhaustive search)
 * Step 3: Identify bottlenecks and redundancies
 * Step 4: Look for greedy choice property
 * Step 5: Implement and verify greedy solution
 * <p>
 * 6. VERIFICATION TECHNIQUES:
 * - Prove that local optimal choices lead to global optimum
 * - Use induction or contradiction to verify correctness
 * - Test against brute force results for small inputs
 */

import java.util.*;

public class _864_e_GreedySolutionFramework {

    /**
     * Common Greedy Problem Patterns
     */
    public static void demonstrateCommonPatterns() {
        System.out.println("\n=== COMMON GREEDY PROBLEM PATTERNS ===");
        System.out.println();

        System.out.println("1. SCHEDULING/INTERVAL PROBLEMS:");
        System.out.println("   - Activity selection, meeting rooms, interval scheduling");
        System.out.println("   - Greedy choice: earliest finish time, least conflicts");
        System.out.println("   - Key insight: local optimal choice doesn't block future optimal choices");
        System.out.println();

        System.out.println("2. OPTIMIZATION WITH CONSTRAINTS:");
        System.out.println("   - Knapsack (fractional), coin change (specific systems)");
        System.out.println("   - Greedy choice: best ratio, largest denomination");
        System.out.println("   - Key insight: locally optimal ratio/choice leads to global optimum");
        System.out.println();

        System.out.println("3. GRAPH PROBLEMS:");
        System.out.println("   - Minimum spanning tree (Kruskal's, Prim's), shortest path (Dijkstra's)");
        System.out.println("   - Greedy choice: minimum weight edge, closest unvisited vertex");
        System.out.println("   - Key insight: locally optimal edge/vertex choice preserves global optimality");
        System.out.println();

        System.out.println("4. ORDERING/SEQUENCING PROBLEMS:");
        System.out.println("   - Job sequencing, Huffman coding, task scheduling");
        System.out.println("   - Greedy choice: optimal ordering criterion (deadline, frequency, priority)");
        System.out.println("   - Key insight: specific ordering maximizes/minimizes objective function");
        System.out.println();

        System.out.println("5. PATH/MOVEMENT PROBLEMS:");
        System.out.println("   - Jump game, gas station, minimum platforms");
        System.out.println("   - Greedy choice: maximize reach, minimize cost, optimal resource usage");
        System.out.println("   - Key insight: locally optimal movement leads to globally optimal path");
    }

    /**
     * When NOT to use Greedy Algorithms
     */
    public static void whenNotToUseGreedy() {
        System.out.println("\n=== WHEN NOT TO USE GREEDY ALGORITHMS ===");
        System.out.println();

        System.out.println("PROBLEMS WITHOUT GREEDY CHOICE PROPERTY:");
        System.out.println();

        System.out.println("1. 0/1 KNAPSACK PROBLEM:");
        System.out.println("   - Cannot use fractional items");
        System.out.println("   - Locally optimal choice (best ratio) may not lead to global optimum");
        System.out.println("   - Example: items [(value=10, weight=5), (value=10, weight=4), (value=7, weight=3)]");
        System.out.println("             capacity=7, greedy chooses item 2 (ratio=2.5), optimal chooses item 3");
        System.out.println();

        System.out.println("2. COIN CHANGE (GENERAL SYSTEMS):");
        System.out.println("   - Not all coin systems have greedy choice property");
        System.out.println("   - Example: coins=[1, 3, 4], amount=6");
        System.out.println("             greedy: 4+1+1=3 coins, optimal: 3+3=2 coins");
        System.out.println();

        System.out.println("3. TRAVELING SALESMAN PROBLEM:");
        System.out.println("   - Locally optimal edge choices don't guarantee global optimum");
        System.out.println("   - Nearest neighbor heuristic is greedy but not optimal");
        System.out.println("   - Requires dynamic programming or other approaches");
        System.out.println();

        System.out.println("4. LONGEST COMMON SUBSEQUENCE:");
        System.out.println("   - No clear greedy choice criterion");
        System.out.println("   - Character-by-character greedy choice may miss optimal alignment");
        System.out.println("   - Requires dynamic programming to explore all possibilities");
        System.out.println();

        System.out.println("SIGNS THAT GREEDY WON'T WORK:");
        System.out.println("- Local optimal choices conflict with global optimum");
        System.out.println("- Need to 'look ahead' to make optimal decisions");
        System.out.println("- Optimal solution requires backtracking or reconsidering choices");
        System.out.println("- Problem has overlapping subproblems with different optimal solutions");
    }

    /**
     * Debugging and Testing Greedy Algorithms
     */
    public static void debuggingAndTesting() {
        System.out.println("\n=== DEBUGGING AND TESTING GREEDY ALGORITHMS ===");
        System.out.println();

        System.out.println("COMMON DEBUGGING TECHNIQUES:");
        System.out.println();

        System.out.println("1. SMALL EXAMPLE VERIFICATION:");
        System.out.println("   - Test greedy solution against brute force for small inputs");
        System.out.println("   - Manually trace through algorithm steps");
        System.out.println("   - Verify that greedy choices lead to expected results");
        System.out.println();

        System.out.println("2. COUNTEREXAMPLE SEARCH:");
        System.out.println("   - Try to construct cases where greedy fails");
        System.out.println("   - If counterexample found, greedy approach is invalid");
        System.out.println("   - If no counterexample after thorough testing, likely correct");
        System.out.println();

        System.out.println("3. INVARIANT CHECKING:");
        System.out.println("   - Define invariants that should hold throughout algorithm");
        System.out.println("   - Verify invariants at each step");
        System.out.println("   - Example: 'current solution is always optimal for processed elements'");
        System.out.println();

        System.out.println("4. COMPLEXITY ANALYSIS:");
        System.out.println("   - Verify that greedy solution improves time/space complexity");
        System.out.println("   - Compare against brute force and dynamic programming approaches");
        System.out.println("   - Ensure greedy choice doesn't require expensive operations");
        System.out.println();

        System.out.println("TESTING STRATEGIES:");
        System.out.println("- Edge cases: empty input, single element, all same elements");
        System.out.println("- Boundary conditions: minimum/maximum values, exact limits");
        System.out.println("- Random test cases: generate large random inputs");
        System.out.println("- Known difficult cases: cases that commonly break greedy algorithms");
    }

    /**
     * Summary and Best Practices
     */
    public static void summaryAndBestPractices() {
        System.out.println("\n=== SUMMARY AND BEST PRACTICES ===");
        System.out.println();

        System.out.println("PROBLEM-SOLVING METHODOLOGY:");
        System.out.println("1. Start with understanding: What is the problem asking?");
        System.out.println("2. Design brute force: How would you solve it exhaustively?");
        System.out.println("3. Analyze complexity: Is brute force feasible?");
        System.out.println("4. Look for patterns: Can we eliminate choices early?");
        System.out.println("5. Identify greedy property: Do local choices lead to global optimum?");
        System.out.println("6. Implement and verify: Test thoroughly and prove correctness");
        System.out.println();

        System.out.println("BEST PRACTICES:");
        System.out.println("- Never assume a problem is greedy without verification");
        System.out.println("- Always implement brute force first for small test cases");
        System.out.println("- Clearly define the greedy choice criterion");
        System.out.println("- Prove or strongly justify why greedy works");
        System.out.println("- Test edge cases and boundary conditions");
        System.out.println("- Consider time/space complexity improvements");
        System.out.println();

        System.out.println("COMMON MISTAKES TO AVOID:");
        System.out.println("- Jumping to greedy solution without analysis");
        System.out.println("- Ignoring counterexamples or edge cases");
        System.out.println("- Implementing complex greedy choices that negate efficiency gains");
        System.out.println("- Forgetting to sort input when order matters");
        System.out.println("- Not verifying that greedy choice preserves optimal substructure");
        System.out.println();

        System.out.println("REMEMBER:");
        System.out.println("'Algorithm essence is exhaustive search. Greedy is an optimization");
        System.out.println("technique that works when local optimal choices aggregate to global");
        System.out.println("optimum. When in doubt, start with brute force and optimize.'");
    }

    public static void main(String[] args) {
        System.out.println("=== COMPREHENSIVE GREEDY ALGORITHM FRAMEWORK ===");
        System.out.println();

        // Demonstrate the complete framework
        GreedyProblemSolver.step1_BruteForceThinking();
        GreedyProblemSolver.step2_ImplementBruteForce();
        GreedyProblemSolver.step3_AnalyzeGreedyProperty();
        GreedyProblemSolver.step4_DesignGreedyAlgorithm();
        GreedyProblemSolver.step5_VerifyCorrectness();

        // Show practical example
        ActivitySelectionExample.demonstrateFramework();

        // Cover additional topics
        demonstrateCommonPatterns();
        whenNotToUseGreedy();
        debuggingAndTesting();
        summaryAndBestPractices();

        System.out.println("\n=== FINAL THOUGHTS ===");
        System.out.println("Greedy algorithms are powerful when applicable, but they require");
        System.out.println("careful analysis to ensure correctness. The key insight is that");
        System.out.println("not all optimization problems have the greedy choice property.");
        System.out.println("Always start with exhaustive thinking, then optimize wisely.");
        System.out.println();
        System.out.println("Master the framework:");
        System.out.println("1. Understand → 2. Brute Force → 3. Analyze → 4. Optimize → 5. Verify");
    }

    /**
     * Generic Problem-Solving Template
     * This demonstrates the systematic approach to identifying and solving greedy problems
     */
    public static class GreedyProblemSolver {

        /**
         * Step 1: Always start with brute force approach
         * This helps understand the problem structure and solution space
         */
        public static void step1_BruteForceThinking() {
            System.out.println("=== STEP 1: BRUTE FORCE THINKING ===");
            System.out.println();
            System.out.println("Always ask these questions first:");
            System.out.println("1. What are all possible choices at each step?");
            System.out.println("2. How many total combinations exist?");
            System.out.println("3. What does the decision tree look like?");
            System.out.println("4. What's the time complexity of exhaustive search?");
            System.out.println();
            System.out.println("Example Questions:");
            System.out.println("- Coin change: Try all combinations of coins");
            System.out.println("- Activity selection: Try all possible activity combinations");
            System.out.println("- Job scheduling: Try all possible job orderings");
            System.out.println();
        }

        /**
         * Step 2: Implement brute force solution
         * This provides correctness baseline and complexity understanding
         */
        public static void step2_ImplementBruteForce() {
            System.out.println("=== STEP 2: IMPLEMENT BRUTE FORCE ===");
            System.out.println();
            System.out.println("Implementation guidelines:");
            System.out.println("1. Write recursive solution first (easier to understand)");
            System.out.println("2. Add memoization if there are overlapping subproblems");
            System.out.println("3. Measure time complexity and identify bottlenecks");
            System.out.println("4. Test with small inputs to verify correctness");
            System.out.println();
            System.out.println("Common patterns:");
            System.out.println("- Recursive tree exploration");
            System.out.println("- Dynamic programming with state transitions");
            System.out.println("- Backtracking with pruning");
            System.out.println();
        }

        /**
         * Step 3: Analyze for greedy choice property
         * Look for patterns where local decisions don't need global information
         */
        public static void step3_AnalyzeGreedyProperty() {
            System.out.println("=== STEP 3: ANALYZE FOR GREEDY CHOICE PROPERTY ===");
            System.out.println();
            System.out.println("Key questions to ask:");
            System.out.println("1. Can we make a choice now without knowing future decisions?");
            System.out.println("2. Does the locally optimal choice contribute to global optimum?");
            System.out.println("3. Can we eliminate branches of the decision tree early?");
            System.out.println("4. Is there a clear ordering or priority for choices?");
            System.out.println();
            System.out.println("Signs of greedy choice property:");
            System.out.println("- Obvious 'best' choice at each step");
            System.out.println("- Choices don't depend on future unknown information");
            System.out.println("- Local optimization leads to global optimization");
            System.out.println("- Can sort elements by some criteria for optimal ordering");
            System.out.println();
        }

        /**
         * Step 4: Design greedy algorithm
         * Transform brute force approach into efficient greedy solution
         */
        public static void step4_DesignGreedyAlgorithm() {
            System.out.println("=== STEP 4: DESIGN GREEDY ALGORITHM ===");
            System.out.println();
            System.out.println("Design principles:");
            System.out.println("1. Define the greedy choice criterion clearly");
            System.out.println("2. Prove that greedy choice preserves optimal solution");
            System.out.println("3. Show that problem reduces to smaller subproblem");
            System.out.println("4. Implement efficient greedy selection");
            System.out.println();
            System.out.println("Common greedy strategies:");
            System.out.println("- Earliest deadline first (scheduling)");
            System.out.println("- Largest/smallest first (optimization)");
            System.out.println("- Closest/farthest first (geometric problems)");
            System.out.println("- Most/least frequent first (encoding problems)");
            System.out.println();
        }

        /**
         * Step 5: Verify correctness
         * Ensure greedy solution produces optimal results
         */
        public static void step5_VerifyCorrectness() {
            System.out.println("=== STEP 5: VERIFY CORRECTNESS ===");
            System.out.println();
            System.out.println("Verification methods:");
            System.out.println("1. PROOF BY INDUCTION:");
            System.out.println("   - Base case: greedy choice optimal for size 1");
            System.out.println("   - Inductive step: if optimal for size k, then optimal for k+1");
            System.out.println();
            System.out.println("2. PROOF BY CONTRADICTION:");
            System.out.println("   - Assume optimal solution differs from greedy solution");
            System.out.println("   - Show this leads to contradiction");
            System.out.println();
            System.out.println("3. EXCHANGE ARGUMENT:");
            System.out.println("   - Show greedy choice can be exchanged into any optimal solution");
            System.out.println("   - Prove exchange doesn't worsen the solution");
            System.out.println();
            System.out.println("4. EMPIRICAL TESTING:");
            System.out.println("   - Compare with brute force on small inputs");
            System.out.println("   - Test edge cases and boundary conditions");
            System.out.println();
        }
    }

    /**
     * Example: Activity Selection Problem
     * Demonstrates the complete framework application
     */
    public static class ActivitySelectionExample {

        /**
         * Step 1 & 2: Brute force solution
         * Try all possible combinations of non-overlapping activities
         */
        public static List<Activity> selectActivitiesBruteForce(List<Activity> activities) {
            return selectActivitiesHelper(activities, 0, new ArrayList<>(), 0);
        }

        private static List<Activity> selectActivitiesHelper(List<Activity> activities,
                                                             int index,
                                                             List<Activity> current,
                                                             int lastFinishTime) {
            if (index >= activities.size()) {
                return new ArrayList<>(current);
            }

            // Don't include current activity
            List<Activity> withoutCurrent = selectActivitiesHelper(activities, index + 1, current, lastFinishTime);

            // Include current activity if possible
            List<Activity> withCurrent = new ArrayList<>();
            if (activities.get(index).start >= lastFinishTime) {
                current.add(activities.get(index));
                withCurrent = selectActivitiesHelper(activities, index + 1, current, activities.get(index).finish);
                current.remove(current.size() - 1);
            }

            // Return the solution with more activities
            return withCurrent.size() > withoutCurrent.size() ? withCurrent : withoutCurrent;
        }

        /**
         * Step 4: Greedy solution
         * Always select activity with earliest finish time
         */
        public static List<Activity> selectActivitiesGreedy(List<Activity> activities) {
            if (activities.isEmpty()) return new ArrayList<>();

            // Sort by finish time (greedy choice criterion)
            activities.sort((a, b) -> Integer.compare(a.finish, b.finish));

            List<Activity> result = new ArrayList<>();
            result.add(activities.get(0));
            int lastFinishTime = activities.get(0).finish;

            // Greedily select non-conflicting activities
            for (int i = 1; i < activities.size(); i++) {
                if (activities.get(i).start >= lastFinishTime) {
                    result.add(activities.get(i));
                    lastFinishTime = activities.get(i).finish;
                }
            }

            return result;
        }

        /**
         * Demonstrate the complete problem-solving process
         */
        public static void demonstrateFramework() {
            System.out.println("=== ACTIVITY SELECTION PROBLEM DEMONSTRATION ===");
            System.out.println();

            // Create test data
            List<Activity> activities = Arrays.asList(
                    new Activity("A1", 1, 4),
                    new Activity("A2", 3, 5),
                    new Activity("A3", 0, 6),
                    new Activity("A4", 5, 7),
                    new Activity("A5", 3, 9),
                    new Activity("A6", 5, 9),
                    new Activity("A7", 6, 10),
                    new Activity("A8", 8, 11),
                    new Activity("A9", 8, 12),
                    new Activity("A10", 2, 14),
                    new Activity("A11", 12, 16)
            );

            System.out.println("Available activities: " + activities);
            System.out.println();

            // Apply framework
            GreedyProblemSolver.step1_BruteForceThinking();

            System.out.println("For Activity Selection:");
            System.out.println("- Each step: choose whether to include current activity");
            System.out.println("- Total combinations: 2^n = " + (1 << activities.size()));
            System.out.println("- Decision tree: binary tree of height n");
            System.out.println("- Brute force complexity: O(2^n)");
            System.out.println();

            GreedyProblemSolver.step2_ImplementBruteForce();

            // Test both approaches (limit brute force to small inputs)
            List<Activity> smallTest = activities.subList(0, Math.min(8, activities.size()));

            long startTime = System.nanoTime();
            List<Activity> bruteForceResult = selectActivitiesBruteForce(new ArrayList<>(smallTest));
            long bruteForceTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            List<Activity> greedyResult = selectActivitiesGreedy(new ArrayList<>(activities));
            long greedyTime = System.nanoTime() - startTime;

            System.out.println("Results:");
            System.out.println("Brute force (first 8 activities): " + bruteForceResult +
                    " (Time: " + bruteForceTime / 1000.0 + " μs)");
            System.out.println("Greedy (all activities): " + greedyResult +
                    " (Time: " + greedyTime / 1000.0 + " μs)");
            System.out.println();

            GreedyProblemSolver.step3_AnalyzeGreedyProperty();

            System.out.println("Analysis for Activity Selection:");
            System.out.println("✓ Can choose activity with earliest finish time immediately");
            System.out.println("✓ This choice doesn't preclude optimal solution");
            System.out.println("✓ Remaining problem has same structure");
            System.out.println("✓ Clear ordering criterion: sort by finish time");
            System.out.println();

            GreedyProblemSolver.step4_DesignGreedyAlgorithm();

            System.out.println("Greedy Algorithm Design:");
            System.out.println("1. Sort activities by finish time");
            System.out.println("2. Select first activity");
            System.out.println("3. For each remaining activity:");
            System.out.println("   - If start time >= last finish time, select it");
            System.out.println("4. Time complexity: O(n log n) for sorting + O(n) for selection = O(n log n)");
            System.out.println();

            GreedyProblemSolver.step5_VerifyCorrectness();

            System.out.println("Correctness Proof (Sketch):");
            System.out.println("- Greedy choice: activity with earliest finish time");
            System.out.println("- Proof: Any optimal solution can be modified to include this choice");
            System.out.println("- Exchange argument: replace first activity in optimal with greedy choice");
            System.out.println("- Result: same or better solution (earlier finish = more opportunities)");
        }

        static class Activity {
            int start, finish;
            String name;

            Activity(String name, int start, int finish) {
                this.name = name;
                this.start = start;
                this.finish = finish;
            }

            @Override
            public String toString() {
                return name + "(" + start + "-" + finish + ")";
            }
        }
    }
}