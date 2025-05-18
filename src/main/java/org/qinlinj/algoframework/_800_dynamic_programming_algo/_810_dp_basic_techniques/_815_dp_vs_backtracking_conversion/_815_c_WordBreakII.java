package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._815_dp_vs_backtracking_conversion;

import java.util.*;

/**
 * Word Break II - LeetCode 140
 * <p>
 * Summary:
 * - Problem: Given a string s and a dictionary wordDict, find all possible ways s can be segmented
 * into a space-separated sequence of dictionary words.
 * <p>
 * - This extends the Word Break I problem by requiring all possible segmentations, not just determining
 * if segmentation is possible.
 * <p>
 * - Two approaches:
 * 1. Backtracking (Traversal): Build all possible segmentations by exploring the recursion tree
 * 2. Dynamic Programming (Problem Decomposition): Decompose into subproblems with memoization
 * <p>
 * - Key insights:
 * - Backtracking is more natural for collecting all possible solutions
 * - Dynamic programming requires more complex return values (lists of string combinations)
 * - Both approaches handle overlapping subproblems differently:
 * * Backtracking: Uses memoization to skip impossible branches
 * * DP: Uses memoization to store all possible segmentations for each suffix
 * <p>
 * - Complexity analysis:
 * - Time complexity for both approaches is exponential O(2^n) in worst case
 * - DP approach eliminates some redundancy but must store all possible combinations
 * - For problems requiring all possible combinations, backtracking is often more straightforward
 */
public class _815_c_WordBreakII {

    public static void main(String[] args) {
        // Example 1
        String s1 = "catsanddog";
        List<String> wordDict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");

        // Example 2
        String s2 = "pineapplepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");

        // Example 3
        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");

        // Test backtracking solution
        BacktrackingSolution backSol = new BacktrackingSolution();
        System.out.println("Backtracking solution:");
        System.out.println("Example 1: " + backSol.wordBreak(s1, wordDict1));
        System.out.println("Example 2: " + backSol.wordBreak(s2, wordDict2));
        System.out.println("Example 3: " + backSol.wordBreak(s3, wordDict3));

        // Test DP solution
        DPSolution dpSol = new DPSolution();
        System.out.println("\nDP solution:");
        System.out.println("Example 1: " + dpSol.wordBreak(s1, wordDict1));
        System.out.println("Example 2: " + dpSol.wordBreak(s2, wordDict2));
        System.out.println("Example 3: " + dpSol.wordBreak(s3, wordDict3));

        // Compare performance on a more complex example
        System.out.println("\nComparison on more complex examples would show that:");
        System.out.println("- Backtracking is more intuitive for collecting all solutions");
        System.out.println("- DP with memoization eliminates some redundancy but has overhead");
        System.out.println("- Both approaches have exponential complexity in worst case");
        System.out.println("- For problems requiring all combinations, traversal is often simpler");
    }

    /**
     * Backtracking solution for Word Break II
     * Builds all possible word segmentations using traversal approach
     */
    public static class BacktrackingSolution {
        private List<String> wordDict;
        private List<String> result = new ArrayList<>();
        private LinkedList<String> track = new LinkedList<>();

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            backtrack(s, 0);
            return result;
        }

        /**
         * Backtracking function to explore all possible word breaks
         *
         * @param s     the input string
         * @param start current position in the string
         */
        private void backtrack(String s, int start) {
            // Base case: reached end of string
            if (start == s.length()) {
                // Convert current path to a space-separated string
                result.add(String.join(" ", track));
                return;
            }

            // Try each word from dictionary
            for (String word : wordDict) {
                int len = word.length();
                // Check if current word matches substring starting at current position
                if (start + len <= s.length() &&
                        s.substring(start, start + len).equals(word)) {

                    // Add word to current path
                    track.addLast(word);
                    // Continue exploring
                    backtrack(s, start + len);
                    // Remove word (backtrack)
                    track.removeLast();
                }
            }
        }
    }

    /**
     * Optimized backtracking with memoization for impossible suffixes
     * This approach avoids exploring branches that cannot lead to a solution
     */
    public static class OptimizedBacktrackingSolution {
        private List<String> wordDict;
        private List<String> result = new ArrayList<>();
        private LinkedList<String> track = new LinkedList<>();
        // Set to store suffixes that cannot be segmented
        private Set<Integer> impossibleStarts = new HashSet<>();

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = wordDict;
            backtrack(s, 0);
            return result;
        }

        /**
         * Backtracking with memoization for impossible suffixes
         */
        private boolean backtrack(String s, int start) {
            // Base case: reached end of string
            if (start == s.length()) {
                result.add(String.join(" ", track));
                return true;
            }

            // Skip if we already know this suffix can't be segmented
            if (impossibleStarts.contains(start)) {
                return false;
            }

            boolean segmentedAnyway = false;

            // Try each word from dictionary
            for (String word : wordDict) {
                int len = word.length();
                if (start + len <= s.length() &&
                        s.substring(start, start + len).equals(word)) {

                    track.addLast(word);
                    boolean segmented = backtrack(s, start + len);
                    track.removeLast();

                    segmentedAnyway = segmentedAnyway || segmented;
                }
            }

            // If no valid segmentation found starting from this position
            if (!segmentedAnyway) {
                impossibleStarts.add(start);
            }

            return segmentedAnyway;
        }
    }

    /**
     * Dynamic Programming solution for Word Break II
     * Uses memoization to store all possible segmentations for each position
     */
    public static class DPSolution {
        private Set<String> wordDict;
        // Memoization: Map position to all possible segmentations from that position
        private Map<Integer, List<String>> memo = new HashMap<>();

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = new HashSet<>(wordDict);
            return dp(s, 0);
        }

        /**
         * DP function: returns all possible segmentations of s[start..]
         *
         * @param s     the input string
         * @param start current position
         * @return list of all possible segmentations
         */
        private List<String> dp(String s, int start) {
            // Check memoization first
            if (memo.containsKey(start)) {
                return memo.get(start);
            }

            List<String> result = new ArrayList<>();

            // Base case: reached end of string
            if (start == s.length()) {
                result.add("");
                return result;
            }

            // Try all possible prefixes
            for (int end = start + 1; end <= s.length(); end++) {
                String prefix = s.substring(start, end);
                if (wordDict.contains(prefix)) {
                    // Get all segmentations for the remaining suffix
                    List<String> subSegmentations = dp(s, end);

                    // Combine current prefix with each sub-segmentation
                    for (String subSegmentation : subSegmentations) {
                        if (subSegmentation.isEmpty()) {
                            result.add(prefix);
                        } else {
                            result.add(prefix + " " + subSegmentation);
                        }
                    }
                }
            }

            // Store in memo
            memo.put(start, result);
            return result;
        }
    }

    /**
     * Improved Dynamic Programming solution with better organization
     */
    public static class ImprovedDPSolution {
        private Set<String> wordDict;
        private List<String>[] memo;

        public List<String> wordBreak(String s, List<String> wordDict) {
            this.wordDict = new HashSet<>(wordDict);
            // Initialize memoization array
            memo = new List[s.length()];
            return dp(s, 0);
        }

        /**
         * DP function: returns all possible segmentations of s[start..]
         */
        private List<String> dp(String s, int i) {
            List<String> result = new ArrayList<>();

            // Base case: reached end of string
            if (i == s.length()) {
                result.add("");
                return result;
            }

            // Check memoization
            if (memo[i] != null) {
                return memo[i];
            }

            // Try all possible prefixes
            for (int len = 1; i + len <= s.length(); len++) {
                String prefix = s.substring(i, i + len);
                if (wordDict.contains(prefix)) {
                    // Get all segmentations for the remaining suffix
                    List<String> subSegmentations = dp(s, i + len);

                    // Combine current prefix with each sub-segmentation
                    for (String sub : subSegmentations) {
                        if (sub.isEmpty()) {
                            result.add(prefix);
                        } else {
                            result.add(prefix + " " + sub);
                        }
                    }
                }
            }

            // Store in memo
            memo[i] = result;
            return result;
        }
    }
}
