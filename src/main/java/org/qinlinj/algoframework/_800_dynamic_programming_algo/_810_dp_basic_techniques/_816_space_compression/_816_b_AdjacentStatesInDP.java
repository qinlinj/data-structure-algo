package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._816_space_compression;

/**
 * Understanding Adjacent States in 2D Dynamic Programming
 * <p>
 * Summary:
 * - Space optimization is applicable to 2D DP problems where the calculation of dp[i][j]
 * only depends on adjacent states like dp[i-1][j], dp[i][j-1], dp[i-1][j-1], etc.
 * <p>
 * - "Adjacent states" means that to calculate dp[i][j], we only need values from:
 * 1. The current row (i)
 * 2. The previous row (i-1)
 * 3. Potentially specific positions like (i-1, j-1)
 * <p>
 * - When this pattern exists, we don't need to store the entire 2D array
 * <p>
 * - The Longest Palindromic Subsequence (LPS) problem is used as an example to demonstrate this concept
 * <p>
 * - In LPS, the state transition equation involves dp[i+1][j-1], dp[i+1][j], and dp[i][j-1],
 * all of which are adjacent to dp[i][j]
 * <p>
 * - When calculating states row by row (or column by column), we can reuse the same space
 * for different rows, reducing the space complexity
 */
public class _816_b_AdjacentStatesInDP {

    /**
     * Example: Longest Palindromic Subsequence with full 2D DP array
     * Time Complexity: O(n²)
     * Space Complexity: O(n²)
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        // Create 2D DP array
        int[][] dp = new int[n][n];

        // Base case: single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the DP table
        // We need to traverse in a specific order to ensure dependencies are met
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // State transition equation
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // The result is the LPS length for the entire string
        return dp[0][n - 1];
    }

    /**
     * Visualizes the dependencies between states in the DP array
     * Shows which states are needed to calculate dp[i][j]
     */
    public static void visualizeDependencies() {
        System.out.println("Visualizing Dependencies for dp[i][j] in LPS Problem:");
        System.out.println("=======================================================");

        // Create a visual representation of the dependencies
        String[][] visual = new String[5][5];

        // Initialize the visual grid
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                visual[i][j] = "   ";
            }
        }

        // Mark the cell we're currently calculating
        int targetI = 2;
        int targetJ = 3;
        visual[targetI][targetJ] = "[X]";

        // Mark the cells it depends on
        visual[targetI + 1][targetJ] = "[1]";     // dp[i+1][j]
        visual[targetI][targetJ - 1] = "[2]";     // dp[i][j-1]
        visual[targetI + 1][targetJ - 1] = "[3]"; // dp[i+1][j-1]

        // Print the visual representation
        System.out.println("Legend:");
        System.out.println("[X] = Current state dp[i][j] being calculated");
        System.out.println("[1] = dp[i+1][j] - Needed for max when chars don't match");
        System.out.println("[2] = dp[i][j-1] - Needed for max when chars don't match");
        System.out.println("[3] = dp[i+1][j-1] - Needed when chars match");
        System.out.println();

        // Print the grid
        System.out.println("    j→  0   1   2   3   4  ");
        System.out.println("  i↓  ┌───┬───┬───┬───┬───┐");
        for (int i = 0; i < 5; i++) {
            System.out.print("   " + i + " │");
            for (int j = 0; j < 5; j++) {
                System.out.print(visual[i][j] + "│");
            }
            System.out.println();
            if (i < 4) {
                System.out.println("     ├───┼───┼───┼───┼───┤");
            }
        }
        System.out.println("     └───┴───┴───┴───┴───┘");

        System.out.println("\nObservation: To calculate dp[i][j], we only need:");
        System.out.println("1. Values from the current row (dp[i][j-1])");
        System.out.println("2. Values from the row below (dp[i+1][j] and dp[i+1][j-1])");
        System.out.println("\nThis pattern makes the problem suitable for space optimization!");
    }

    /**
     * Shows how the DP table is filled for a simple example
     */
    public static void demonstrateLPSExample() {
        String s = "babad";
        int n = s.length();
        int[][] dp = new int[n][n];

        // Initialize base cases
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        System.out.println("\nStep-by-Step Filling of DP Table for s = \"" + s + "\":");
        System.out.println("=======================================================");

        // Initial state after base case
        System.out.println("After initializing base cases (single character palindromes):");
        printDPTable(dp, s);

        // Fill the DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }

                // Print the table after significant updates
                if ((i <= 2 && j >= 2) || (i == 0 && j == n - 1)) {
                    System.out.println("\nAfter calculating dp[" + i + "][" + j + "]:");
                    printDPTable(dp, s);
                }
            }
        }

        System.out.println("\nFinal result: The longest palindromic subsequence has length " + dp[0][n - 1]);
    }

    /**
     * Helper method to print the DP table in a readable format
     */
    private static void printDPTable(int[][] dp, String s) {
        int n = dp.length;

        // Print the string as column headers
        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.print(" " + s.charAt(j) + "  ");
        }
        System.out.println();

        // Print the top border
        System.out.print("   ┌");
        for (int j = 0; j < n; j++) {
            System.out.print("───");
            if (j < n - 1) System.out.print("┬");
        }
        System.out.println("┐");

        // Print the table contents
        for (int i = 0; i < n; i++) {
            System.out.print(" " + s.charAt(i) + " │");
            for (int j = 0; j < n; j++) {
                System.out.printf(" %d ", dp[i][j]);
                if (j < n - 1) System.out.print("│");
            }
            System.out.println("│");

            // Print row separator, except after the last row
            if (i < n - 1) {
                System.out.print("   ├");
                for (int j = 0; j < n; j++) {
                    System.out.print("───");
                    if (j < n - 1) System.out.print("┼");
                }
                System.out.println("┤");
            }
        }

        // Print the bottom border
        System.out.print("   └");
        for (int j = 0; j < n; j++) {
            System.out.print("───");
            if (j < n - 1) System.out.print("┴");
        }
        System.out.println("┘");
    }

    public static void main(String[] args) {
        System.out.println("Understanding Adjacent States in 2D Dynamic Programming");
        System.out.println("====================================================");

        // Visualize the dependencies
        visualizeDependencies();

        // Demonstrate the LPS solution step by step
        demonstrateLPSExample();

        // Test the LPS function
        String[] testCases = {"bbbab", "cbbd", "racecar", "aaa"};

        System.out.println("\nTesting Longest Palindromic Subsequence:");
        for (String s : testCases) {
            int length = longestPalindromeSubseq(s);
            System.out.println("LPS of \"" + s + "\" has length " + length);
        }
    }
}
