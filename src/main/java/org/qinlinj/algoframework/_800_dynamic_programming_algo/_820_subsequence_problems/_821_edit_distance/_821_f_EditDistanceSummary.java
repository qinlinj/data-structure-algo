package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._821_edit_distance;

/**
 * EDIT DISTANCE - COMPREHENSIVE SUMMARY AND OPTIMIZATIONS
 * <p>
 * Key Points:
 * <p>
 * 1. The Edit Distance problem variations:
 * - Basic: Find minimum number of operations
 * - Extended: Identify specific operations (skip, insert, delete, replace)
 * - Advanced: Optimize for space complexity
 * <p>
 * 2. Solution approaches comparison:
 * - Recursive: Intuitive but exponential time complexity O(3^(m+n))
 * - Memoization: Top-down DP, O(m×n) time and space complexity
 * - Tabulation: Bottom-up DP, O(m×n) time and space complexity
 * - Space-optimized: O(min(m,n)) space complexity
 * <p>
 * 3. Space optimization technique:
 * - Since each cell in the DP table only depends on the current row and previous row,
 * we can reduce space from O(m×n) to O(min(m,n))
 * - Use two rows (current and previous) instead of the entire matrix
 * - Further optimization: use a single row with clever indexing
 * <p>
 * 4. Variations of the problem:
 * - Different operation costs (weighted edit distance)
 * - Restricted operations (e.g., no replacements allowed)
 * - Multi-string alignment (generalized edit distance)
 * <p>
 * 5. Key applications:
 * - Computational biology (DNA sequence alignment)
 * - Natural language processing (spell checking)
 * - Information retrieval (fuzzy string matching)
 * - Text processing (diff tools)
 */
public class _821_f_EditDistanceSummary {

    /**
     * Space-optimized solution using only two rows
     * Reduces space complexity from O(m×n) to O(min(m,n))
     */
    public static int minDistanceSpaceOptimized(String s1, String s2) {
        // Ensure s1 is the shorter string to minimize space
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        int m = s1.length();
        int n = s2.length();

        // Use only two rows instead of the full matrix
        int[] prevRow = new int[m + 1];
        int[] currRow = new int[m + 1];

        // Initialize the first row (base case)
        for (int i = 0; i <= m; i++) {
            prevRow[i] = i;
        }

        // Fill the DP table row by row
        for (int j = 1; j <= n; j++) {
            currRow[0] = j;  // Base case for this row

            for (int i = 1; i <= m; i++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    currRow[i] = prevRow[i - 1];  // No operation
                } else {
                    currRow[i] = 1 + Math.min(
                            Math.min(prevRow[i],     // Delete
                                    currRow[i - 1]), // Insert
                            prevRow[i - 1]           // Replace
                    );
                }
            }

            // Swap rows for next iteration
            int[] temp = prevRow;
            prevRow = currRow;
            currRow = temp;
        }

        // The answer is in the last cell of the previous row
        return prevRow[m];
    }

    /**
     * Further-optimized version using only a single row
     * This is the most space-efficient implementation
     */
    public static int minDistanceSingleRow(String s1, String s2) {
        // Ensure s1 is the shorter string
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        int m = s1.length();
        int n = s2.length();

        // Use only one row
        int[] dp = new int[m + 1];

        // Initialize (base case)
        for (int i = 0; i <= m; i++) {
            dp[i] = i;
        }

        // Fill the DP table
        for (int j = 1; j <= n; j++) {
            int prev = dp[0];  // Store dp[i-1][j-1]
            dp[0] = j;         // Base case for this row

            for (int i = 1; i <= m; i++) {
                int temp = dp[i];  // Store the current value before overwriting

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i] = prev;
                } else {
                    dp[i] = 1 + Math.min(Math.min(dp[i], dp[i - 1]), prev);
                }

                prev = temp;  // Update prev for the next iteration
            }
        }

        return dp[m];
    }

    /**
     * Weighted edit distance - different costs for different operations
     */
    public static int weightedEditDistance(String s1, String s2,
                                           int insertCost, int deleteCost, int replaceCost) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i * deleteCost;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j * insertCost;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];  // No cost for matching characters
                } else {
                    dp[i][j] = Math.min(
                            Math.min(
                                    dp[i - 1][j] + deleteCost,      // Delete
                                    dp[i][j - 1] + insertCost),     // Insert
                            dp[i - 1][j - 1] + replaceCost      // Replace
                    );
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Compares all implemented approaches
     */
    public static void compareApproaches() {
        String s1 = "intention";
        String s2 = "execution";

        System.out.println("Comparing different approaches for Edit Distance:");
        System.out.println("Strings: \"" + s1 + "\" and \"" + s2 + "\"");

        // Basic DP solution
        long startTime = System.nanoTime();
        int basicResult = _821_d_EditDistanceTabulation.minDistance(s1, s2);
        long basicTime = System.nanoTime() - startTime;

        // Space-optimized solution (two rows)
        startTime = System.nanoTime();
        int optimizedResult = minDistanceSpaceOptimized(s1, s2);
        long optimizedTime = System.nanoTime() - startTime;

        // Single-row solution
        startTime = System.nanoTime();
        int singleRowResult = minDistanceSingleRow(s1, s2);
        long singleRowTime = System.nanoTime() - startTime;

        // Operation tracking solution
        startTime = System.nanoTime();
        int trackingResult = _821_e_EditDistanceOperationTracking.minDistance(s1, s2);
        long trackingTime = System.nanoTime() - startTime;

        // Weighted edit distance
        startTime = System.nanoTime();
        int weightedResult = weightedEditDistance(s1, s2, 1, 1, 1);
        long weightedTime = System.nanoTime() - startTime;

        // Print results
        System.out.println("\nResults:");
        System.out.println("Basic DP solution: " + basicResult +
                " (Time: " + basicTime / 1_000_000.0 + " ms)");
        System.out.println("Space-optimized (two rows): " + optimizedResult +
                " (Time: " + optimizedTime / 1_000_000.0 + " ms)");
        System.out.println("Space-optimized (single row): " + singleRowResult +
                " (Time: " + singleRowTime / 1_000_000.0 + " ms)");
        System.out.println("Operation tracking: " + trackingResult +
                " (Time: " + trackingTime / 1_000_000.0 + " ms)");
        System.out.println("Weighted edit distance: " + weightedResult +
                " (Time: " + weightedTime / 1_000_000.0 + " ms)");

        // Space complexity comparison
        System.out.println("\nSpace Complexity Comparison:");
        System.out.println("Basic DP solution: O(m×n) = O( + m * n + )");
        System.out.println("Space-optimized (two rows): O(2×min(m,n)) = O( + 2 * Math.min(m, n) + )");
        System.out.println("Space-optimized (single row): O(min(m,n)) = O( + Math.min(m, n) + )");
        System.out.println("Operation tracking: O(m×n) = O( + m * n + )");
    }

    /**
     * Example of practical applications
     */
    public static void demonstratePracticalApplications() {
        System.out.println("\nPractical Applications of Edit Distance:");

        // 1. Spell checking
        String misspelled = "recieve";
        String[] dictionary = {"receive", "relieve", "retrieve", "recipe"};

        System.out.println("\n1. Spell Checking Example:");
        System.out.println("Misspelled word: \"" + misspelled + "\"");
        System.out.println("Suggestions:");

        int minDist = Integer.MAX_VALUE;
        String bestMatch = "";

        for (String word : dictionary) {
            int distance = minDistanceSingleRow(misspelled, word);
            System.out.println("  - \"" + word + "\" (Distance: " + distance + ")");

            if (distance < minDist) {
                minDist = distance;
                bestMatch = word;
            }
        }

        System.out.println("Best suggestion: \"" + bestMatch + "\"");

        // 2. DNA sequence alignment
        String dna1 = "ACGTACGT";
        String dna2 = "ACGTAGCGT";

        System.out.println("\n2. DNA Sequence Alignment:");
        System.out.println("Sequence 1: " + dna1);
        System.out.println("Sequence 2: " + dna2);
        System.out.println("Edit distance: " + minDistanceSingleRow(dna1, dna2));
        System.out.println("Operations:");
        java.util.List<String> operations = _821_e_EditDistanceOperationTracking.getOperationsList(dna1, dna2);
        for (String op : operations) {
            System.out.println("  - " + op);
        }

        // 3. Fuzzy search example
        System.out.println("\n3. Fuzzy Search Example:");
        String query = "dynamic programing";
        String[] searchItems = {
                "dynamic programming",
                "dynamic program",
                "programs that are dynamic",
                "dynamically programmed",
                "something completely different"
        };

        System.out.println("Search query: \"" + query + "\"");
        System.out.println("Results (ordered by relevance):");

        java.util.Map<String, Integer> distances = new java.util.HashMap<>();
        for (String item : searchItems) {
            distances.put(item, minDistanceSingleRow(query, item));
        }

        // Sort results by distance
        searchItems = java.util.Arrays.stream(searchItems)
                .sorted((a, b) -> distances.get(a) - distances.get(b))
                .toArray(String[]::new);

        for (int i = 0; i < searchItems.length; i++) {
            System.out.println((i + 1) + ". \"" + searchItems[i] +
                    "\" (Distance: " + distances.get(searchItems[i]) + ")");
        }
    }

    /**
     * Main method to demonstrate various implementations and use cases
     */
    public static void main(String[] args) {
        System.out.println("EDIT DISTANCE - COMPREHENSIVE SUMMARY\n");

        compareApproaches();
        demonstratePracticalApplications();

        // Final summary
        System.out.println("\n---- FINAL SUMMARY ----");
        System.out.println("1. Edit Distance is a foundational dynamic programming problem.");
        System.out.println("2. It can be solved with various approaches (recursive, memoization, tabulation).");
        System.out.println("3. Space optimization can reduce memory usage from O(m×n) to O(min(m,n)).");
        System.out.println("4. Adding operation tracking adds practical value with minimal overhead.");
        System.out.println("5. The algorithm has numerous real-world applications in text processing,");
        System.out.println("   computational biology, and information retrieval.");

        int m = 10000, n = 10000;
        System.out.println("\nFor very large strings (m=" + m + ", n=" + n + "):");
        System.out.println("- Standard DP solution would use O(m×n) = " + (m * n * 4 / 1024 / 1024) + " MB memory");
        System.out.println("- Space-optimized solution would use only O(min(m,n)) = " +
                (Math.min(m, n) * 4 / 1024) + " KB memory");
    }
}
