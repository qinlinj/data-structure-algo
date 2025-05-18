package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._815_dp_vs_backtracking_conversion;

import java.util.*;

/**
 * Main Class to Demonstrate All Concepts and Implementations
 * <p>
 * This class ties together all the concepts covered in the previous classes,
 * providing a unified entry point to understand the relationships between the
 * different approaches to recursive problem-solving.
 * <p>
 * The class demonstrates:
 * 1. How to approach the same problem using different recursive paradigms
 * 2. Performance comparisons between different approaches
 * 3. Guidelines for selecting the appropriate algorithm based on problem characteristics
 * <p>
 * Key Takeaways:
 * <p>
 * - Recursive problem-solving can be approached using either "traversal" (backtracking)
 * or "problem decomposition" (dynamic programming) techniques
 * <p>
 * - Backtracking follows a state-space tree traversal approach, exploring all possible
 * combinations by making choices at each step and undoing them when backtracking
 * <p>
 * - Dynamic Programming breaks down problems into smaller subproblems and uses memoization
 * to avoid redundant calculations of overlapping subproblems
 * <p>
 * - The Word Break problems illustrate how these approaches can be applied to the same problem:
 * - Word Break I: Determine if a string can be segmented into dictionary words
 * - Word Break II: Find all possible ways to segment a string
 * <p>
 * - For problems requiring all possible solutions, backtracking is often more intuitive,
 * while DP can be more efficient for problems with optimal substructure and overlapping subproblems
 * <p>
 * - Hybrid approaches that combine elements of both techniques can sometimes offer the best solution
 */
public class _815_f_BacktrackingConversion {
    public static void main(String[] args) {
        System.out.println("Recursive Problem-Solving Techniques Demonstration");
        System.out.println("=================================================\n");

        demonstrateWordBreakI();
        System.out.println("\n-------------------------------------------------\n");

        demonstrateWordBreakII();
        System.out.println("\n-------------------------------------------------\n");

        comparePerformance();
        System.out.println("\n-------------------------------------------------\n");

        provideSummaryAndGuidelines();
    }

    /**
     * Demonstrates the Word Break I problem using different approaches
     */
    private static void demonstrateWordBreakI() {
        System.out.println("Word Break I Problem Demonstration");
        System.out.println("--------------------------------");

        // Example input
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");

        System.out.println("Input: s = \"" + s + "\", wordDict = " + wordDict);

        // Backtracking solution
        _815_b_WordBreakI.BacktrackingSolution backSol = new _815_b_WordBreakI.BacktrackingSolution();
        long startTime = System.nanoTime();
        boolean backtrackingResult = backSol.wordBreak(s, wordDict);
        long backtrackingTime = System.nanoTime() - startTime;

        // DP solution
        _815_b_WordBreakI.DPSolution dpSol = new _815_b_WordBreakI.DPSolution();
        startTime = System.nanoTime();
        boolean dpResult = dpSol.wordBreak(s, wordDict);
        long dpTime = System.nanoTime() - startTime;

        System.out.println("\nResults:");
        System.out.println("Backtracking Solution: " + backtrackingResult);
        System.out.println("DP Solution: " + dpResult);

        System.out.println("\nPerformance (nanoseconds):");
        System.out.println("Backtracking Time: " + backtrackingTime);
        System.out.println("DP Time: " + dpTime);
        System.out.println("DP is " + (backtrackingTime / (double) dpTime) + "x faster on this example");

        // Harder example that shows more significant difference
        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        wordDict = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa");

        System.out.println("\nHarder Example: s = \"aaaa...aaab\" (length 100), wordDict = [\"a\", \"aa\", \"aaa\", \"aaaa\", \"aaaaa\"]");

        // Only run DP solution on harder example
        startTime = System.nanoTime();
        dpResult = dpSol.wordBreak(s, wordDict);
        dpTime = System.nanoTime() - startTime;

        System.out.println("DP Solution: " + dpResult + " (Time: " + dpTime + " ns)");
        System.out.println("Note: Backtracking solution would take too long on this example due to exponential complexity");
    }

    /**
     * Demonstrates the Word Break II problem using different approaches
     */
    private static void demonstrateWordBreakII() {
        System.out.println("Word Break II Problem Demonstration");
        System.out.println("---------------------------------");

        // Example input
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");

        System.out.println("Input: s = \"" + s + "\", wordDict = " + wordDict);

        // Backtracking solution
        _815_c_WordBreakII.BacktrackingSolution backSol = new _815_c_WordBreakII.BacktrackingSolution();
        long startTime = System.nanoTime();
        List<String> backtrackingResult = backSol.wordBreak(s, wordDict);
        long backtrackingTime = System.nanoTime() - startTime;

        // DP solution
        _815_c_WordBreakII.DPSolution dpSol = new _815_c_WordBreakII.DPSolution();
        startTime = System.nanoTime();
        List<String> dpResult = dpSol.wordBreak(s, wordDict);
        long dpTime = System.nanoTime() - startTime;

        System.out.println("\nResults:");
        System.out.println("Backtracking Solution: " + backtrackingResult);
        System.out.println("DP Solution: " + dpResult);

        System.out.println("\nPerformance (nanoseconds):");
        System.out.println("Backtracking Time: " + backtrackingTime);
        System.out.println("DP Time: " + dpTime);

        // Explain the performance difference
        System.out.println("\nPerformance Analysis:");
        System.out.println("For Word Break II, the performance difference between approaches is less dramatic");
        System.out.println("than for Word Break I because both approaches have exponential worst-case complexity");
        System.out.println("when finding all possible combinations.");

        System.out.println("\nHowever, the DP approach can still be more efficient for certain inputs where");
        System.out.println("there are many overlapping subproblems, while the backtracking approach may be");
        System.out.println("more intuitive and sometimes more efficient when there are few valid segmentations.");
    }

    /**
     * Provides a more detailed performance comparison with analysis
     */
    private static void comparePerformance() {
        System.out.println("Performance Comparison and Analysis");
        System.out.println("----------------------------------");

        System.out.println("Theoretical Time Complexity:");
        System.out.println("1. Word Break I");
        System.out.println("   - Backtracking without memoization: O(2^n), where n is the length of the string");
        System.out.println("     This is because there are potentially 2^n ways to segment the string");
        System.out.println("   - Backtracking with memoization: O(n^2), with n states and O(n) work per state");
        System.out.println("   - DP with memoization: O(n^3), with n states and O(n^2) work per state");
        System.out.println();

        System.out.println("2. Word Break II");
        System.out.println("   - Both approaches: O(2^n) worst-case when all segmentations are valid");
        System.out.println("   - DP approach may still be more efficient for certain inputs due to memoization");
        System.out.println();

        System.out.println("Space Complexity:");
        System.out.println("1. Backtracking: O(n) for recursion stack + O(n) for current path = O(n)");
        System.out.println("2. DP with memoization: O(n) for recursion + O(n*2^n) for memo in worst case = O(n*2^n)");
        System.out.println();

        System.out.println("Practical Considerations:");
        System.out.println("1. Small inputs: Both approaches perform similarly");
        System.out.println("2. Large inputs with few valid solutions: Backtracking may be more efficient");
        System.out.println("3. Large inputs with many valid solutions: DP with memoization may be more efficient");
        System.out.println("4. Extremely large inputs: Both approaches may be impractical due to exponential complexity");
        System.out.println();

        System.out.println("Key Performance Insights:");
        System.out.println("1. Overlapping Subproblems: The more overlapping subproblems a problem has,");
        System.out.println("   the more benefit memoization provides, favoring the DP approach");
        System.out.println();
        System.out.println("2. Early Pruning: If the problem allows for early detection of invalid paths,");
        System.out.println("   backtracking with pruning can be very efficient");
        System.out.println();
        System.out.println("3. Memory Trade-offs: DP solutions with memoization often trade memory for speed,");
        System.out.println("   which may be problematic for very large inputs or memory-constrained environments");
    }

    /**
     * Provides a summary of the concepts and guidelines for selecting an approach
     */
    private static void provideSummaryAndGuidelines() {
        System.out.println("Summary and Selection Guidelines");
        System.out.println("-------------------------------");

        System.out.println("Summary of Key Concepts:");
        System.out.println("1. Recursive problem-solving can be approached in two primary ways:");
        System.out.println("   - Traversal (Backtracking): Explores a conceptual tree of all possibilities");
        System.out.println("   - Problem Decomposition (DP): Breaks problems into smaller overlapping subproblems");
        System.out.println();

        System.out.println("2. Backtracking characteristics:");
        System.out.println("   - Explores the entire solution space systematically");
        System.out.println("   - Makes decisions at each step (choose/explore/unchoose)");
        System.out.println("   - Naturally collects all possible solutions");
        System.out.println("   - Can use pruning to eliminate branches that won't lead to valid solutions");
        System.out.println();

        System.out.println("3. Dynamic Programming characteristics:");
        System.out.println("   - Relies on optimal substructure (solutions to subproblems can build the main solution)");
        System.out.println("   - Uses memoization to avoid redundant calculations");
        System.out.println("   - Typically more efficient for problems with many overlapping subproblems");
        System.out.println("   - Can be implemented top-down (recursion with memoization) or bottom-up (tabulation)");
        System.out.println();

        System.out.println("Guidelines for Selecting an Approach:");
        System.out.println("1. If the problem requires finding ALL possible solutions:");
        System.out.println("   - Backtracking is typically more natural and intuitive");
        System.out.println("   - DP can still be used but may require more complex return values and logic");
        System.out.println();

        System.out.println("2. If the problem exhibits optimal substructure and overlapping subproblems:");
        System.out.println("   - Dynamic Programming will typically be more efficient");
        System.out.println("   - Examples: shortest paths, optimal value problems, counting problems");
        System.out.println();

        System.out.println("3. If the problem has constraints that allow early pruning:");
        System.out.println("   - Backtracking with pruning can be very efficient");
        System.out.println("   - The ability to eliminate large portions of the search space is valuable");
        System.out.println();

        System.out.println("4. Consider a hybrid approach when:");
        System.out.println("   - The problem requires all solutions but has significant overlapping subproblems");
        System.out.println("   - You can use memoization within a backtracking framework");
        System.out.println("   - You need to balance between memory usage and time efficiency");
        System.out.println();

        System.out.println("Practical Advice:");
        System.out.println("1. Start with the most intuitive approach for the problem");
        System.out.println("2. If performance is unsatisfactory, consider optimizations or switching approaches");
        System.out.println("3. For problems requiring all solutions, backtracking is often a good starting point");
        System.out.println("4. For optimization problems, dynamic programming is usually the go-to approach");
        System.out.println("5. Don't be afraid to combine techniques - the best solutions often blend multiple approaches");
    }

    /**
     * An example class that demonstrates a hybrid approach combining backtracking with memoization
     */
    public static class HybridApproach {
        private Set<String> wordDict;
        private List<String> result;
        private List<String> currentPath;
        // Memoization to avoid redundant calculations
        private Map<Integer, Boolean> canSegmentMemo;
        private Map<Integer, List<List<String>>> segmentationsMemo;

        /**
         * Main function to find all ways to segment a string
         */
        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = new HashSet<>(wordDict);
            this.result = new ArrayList<>();
            this.currentPath = new ArrayList<>();
            this.canSegmentMemo = new HashMap<>();
            this.segmentationsMemo = new HashMap<>();

            // First check if segmentation is possible (optimization)
            if (!canSegment(s, 0)) {
                return result;
            }

            // Then find all segmentations using backtracking
            backtrack(s, 0);
            return result;
        }

        /**
         * Check if s[start..] can be segmented (dynamic programming)
         */
        private boolean canSegment(String s, int start) {
            if (start == s.length()) {
                return true;
            }

            if (canSegmentMemo.containsKey(start)) {
                return canSegmentMemo.get(start);
            }

            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDict.contains(s.substring(start, end)) && canSegment(s, end)) {
                    canSegmentMemo.put(start, true);
                    return true;
                }
            }

            canSegmentMemo.put(start, false);
            return false;
        }

        /**
         * Find all ways to segment s[start..] (backtracking with memoization)
         */
        private void backtrack(String s, int start) {
            // Base case: reached end of string
            if (start == s.length()) {
                result.add(String.join(" ", currentPath));
                return;
            }

            // Try each possible word from dictionary
            for (int end = start + 1; end <= s.length(); end++) {
                String word = s.substring(start, end);
                if (wordDict.contains(word)) {
                    // Only explore paths that can lead to a valid segmentation
                    if (canSegment(s, end)) {
                        currentPath.add(word);
                        backtrack(s, end);
                        currentPath.remove(currentPath.size() - 1);
                    }
                }
            }
        }
    }
}
