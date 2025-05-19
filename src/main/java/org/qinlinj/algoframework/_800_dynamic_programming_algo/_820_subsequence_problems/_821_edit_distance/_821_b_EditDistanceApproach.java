package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._821_edit_distance;

/**
 * EDIT DISTANCE PROBLEM - APPROACH AND RECURSIVE SOLUTION
 * <p>
 * Key Points:
 * <p>
 * 1. General approach for string dynamic programming problems:
 * - Use two pointers i and j pointing to characters in both strings
 * - Define a state that represents subproblems (usually substrings)
 * - Progress by moving pointers to reduce the problem size
 * <p>
 * 2. For edit distance, we can define dp(i,j) as the minimum edit distance
 * between substrings s1[0...i] and s2[0...j].
 * <p>
 * 3. Four possible operations at each step:
 * - Skip (when characters match): no cost, move both pointers
 * - Insert: add 1 to cost, move j pointer
 * - Delete: add 1 to cost, move i pointer
 * - Replace: add 1 to cost, move both pointers
 * <p>
 * 4. Base cases:
 * - If i reaches end of s1, remaining operations = insert remaining characters from s2
 * - If j reaches end of s2, remaining operations = delete remaining characters from s1
 * <p>
 * 5. The recursive solution tries all possible operations at each step and selects
 * the one that leads to the minimum edit distance.
 */
public class _821_b_EditDistanceApproach {

    /**
     * Recursive solution for the edit distance problem.
     * This demonstrates the core approach without optimization.
     */
    public static int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // Start from the end of both strings
        return dp(s1, m - 1, s2, n - 1);
    }

    /**
     * Recursive function that calculates minimum edit distance between
     * s1[0...i] and s2[0...j] substrings.
     */
    private static int dp(String s1, int i, String s2, int j) {
        // Base cases
        if (i == -1) return j + 1;  // Need to insert j+1 characters from s2
        if (j == -1) return i + 1;  // Need to delete i+1 characters from s1

        if (s1.charAt(i) == s2.charAt(j)) {
            // Characters match, no operation needed
            return dp(s1, i - 1, s2, j - 1);
        }

        // Try all three operations and choose the minimum cost
        return min(
                // Insert - move j pointer
                dp(s1, i, s2, j - 1) + 1,
                // Delete - move i pointer
                dp(s1, i - 1, s2, j) + 1,
                // Replace - move both pointers
                dp(s1, i - 1, s2, j - 1) + 1
        );
    }

    /**
     * Helper function to find the minimum of three integers
     */
    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * Visualize how the algorithm would progress for a simple example.
     * This shows the step-by-step operations for transforming "rad" to "apple".
     */
    public static void visualizeSample() {
        String s1 = "rad";
        String s2 = "apple";

        System.out.println("Visualizing transformation from \"" + s1 + "\" to \"" + s2 + "\":");
        System.out.println("Starting with pointers at the end of both strings:");
        System.out.println("i points to 'd' in \"rad\"");
        System.out.println("j points to 'e' in \"apple\"");

        System.out.println("\nPossible operations:");
        System.out.println("1. Insert 'e' -> ra[e]d");
        System.out.println("2. Delete 'd' -> ra");
        System.out.println("3. Replace 'd' with 'e' -> rae");

        System.out.println("\nAfter choosing to delete 'd' (minimum cost path):");
        System.out.println("i now points to 'a' in \"ra\"");
        System.out.println("j still points to 'e' in \"apple\"");

        System.out.println("\nContinuing this process recursively until base case is reached...");
    }

    /**
     * Demonstrates the recursive solution with a simple example
     */
    public static void main(String[] args) {
        // Warning: this recursive solution has exponential complexity!
        // It will be very slow for longer strings due to overlapping subproblems

        String s1 = "horse";
        String s2 = "ros";

        System.out.println("Calculating edit distance between \"" + s1 + "\" and \"" + s2 + "\"...");
        int distance = minDistance(s1, s2);
        System.out.println("Edit distance: " + distance);

        // Visualize sample approach
        System.out.println("\n----------\n");
        visualizeSample();

        System.out.println("\n----------\n");
        System.out.println("Note: This recursive solution is inefficient due to overlapping subproblems.");
        System.out.println("For example, when transforming \"horse\" to \"ros\", the subproblem of");
        System.out.println("transforming \"hor\" to \"ro\" would be calculated multiple times.");
        System.out.println("This is a clear indication that dynamic programming optimization is needed.");
    }
}
