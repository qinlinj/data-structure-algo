package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._821_edit_distance;

 *
         * Key Points:
        *
        * 1. Beyond minimum edit distance, we often need to know the exact operations required
 *    for the transformation.
        *
        * 2. Operation tracking approach:
        *    - Extend the DP table to store both the distance value and the operation type
 *    - Operation types: Skip (0), Insert (1), Delete (2), Replace (3)
        *    - After filling the DP table, backtrack from the bottom-right to determine the sequence
 *      of operations
 *
         * 3. Backtracking process:
        *    - Start from dp[m][n] (bottom-right cell)
        *    - For each cell, check its operation type
 *    - Move to the previous cell based on the operation type
 *    - Continue until reaching dp[0][0]
        *
        * 4. Real-world applications:
        *    - Text diff tools showing exact changes between versions
 *    - Spell checkers suggesting specific corrections
 *    - DNA sequence alignment tools highlighting mutations
 *    - Text editing tools with "track changes" functionality
 *
         * 5. This enhanced version maintains the same O(m×n) time and space complexity
 *    as the basic DP solution, but provides much more practical information.
        */
public class _821_e_EditDistanceOperationTracking {

    /**
     * Node class to store both value and operation in the DP table
     */
    static class Node {
        int val;     // Edit distance value
        int choice;  // Operation code: 0=skip, 1=insert, 2=delete, 3=replace

        public Node(int val, int choice) {
            this.val = val;
            this.choice = choice;
        }
    }

    /**
     * Calculates minimum edit distance and tracks operations
     */
    public static int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        Node[][] dp = new Node[m + 1][n + 1];

        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            // Base case: s1 to empty string requires deletions
            dp[i][0] = new Node(i, 2);  // Delete operation
        }

        for (int j = 1; j <= n; j++) {
            // Base case: empty string to s2 requires insertions
            dp[0][j] = new Node(j, 1);  // Insert operation
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Characters match - skip operation
                    dp[i][j] = new Node(dp[i - 1][j - 1].val, 0);
                } else {
                    // Find minimum cost operation
                    dp[i][j] = minNode(
                            dp[i - 1][j],      // Delete
                            dp[i][j - 1],      // Insert
                            dp[i - 1][j - 1]   // Replace
                    );
                    // Increment the cost
                    dp[i][j].val++;
                }
            }
        }

        // Print the operations (optional)
        printOperations(dp, s1, s2);

        // Return the minimum edit distance
        return dp[m][n].val;
    }

    /**
     * Determines which operation has the minimum cost
     */
    private static Node minNode(Node delete, Node insert, Node replace) {
        Node result = new Node(delete.val, 2);  // Assume delete is minimum

        if (result.val > insert.val) {
            result.val = insert.val;
            result.choice = 1;  // Insert
        }

        if (result.val > replace.val) {
            result.val = replace.val;
            result.choice = 3;  // Replace
        }

        return result;
    }

    /**
     * Prints the sequence of operations by backtracking through the DP table
     */
    private static void printOperations(Node[][] dp, String s1, String s2) {
        int i = s1.length();
        int j = s2.length();

        System.out.println("Operations to transform \"" + s1 + "\" to \"" + s2 + "\":");

        // Start from the bottom-right cell and backtrack
        while (i > 0 && j > 0) {
            char c1 = s1.charAt(i - 1);
            char c2 = s2.charAt(j - 1);
            int choice = dp[i][j].choice;

            switch (choice) {
                case 0:  // Skip
                    System.out.println("Skip '" + c1 + "'");
                    i--;
                    j--;
                    break;
                case 1:  // Insert
                    System.out.println("Insert '" + c2 + "'");
                    j--;
                    break;
                case 2:  // Delete
                    System.out.println("Delete '" + c1 + "'");
                    i--;
                    break;
                case 3:  // Replace
                    System.out.println("Replace '" + c1 + "' with '" + c2 + "'");
                    i--;
                    j--;
                    break;
            }
        }

        // Handle remaining characters in s1 (all require deletion)
        while (i > 0) {
            System.out.println("Delete '" + s1.charAt(i - 1) + "'");
            i--;
        }

        // Handle remaining characters in s2 (all require insertion)
        while (j > 0) {
            System.out.println("Insert '" + s2.charAt(j - 1) + "'");
            j--;
        }
    }

    /**
     * Alternative method that returns the list of operations
     */
    public static java.util.List<String> getOperationsList(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        Node[][] dp = new Node[m + 1][n + 1];

        // Initialize base cases (same as above)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = new Node(i, 2);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = new Node(j, 1);
        }

        // Fill the DP table (same as above)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = new Node(dp[i - 1][j - 1].val, 0);
                } else {
                    dp[i][j] = minNode(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
                    dp[i][j].val++;
                }
            }
        }

        // Backtrack and collect operations
        java.util.List<String> operations = new java.util.ArrayList<>();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            char c1 = s1.charAt(i - 1);
            char c2 = s2.charAt(j - 1);
            int choice = dp[i][j].choice;

            switch (choice) {
                case 0:
                    operations.add("Skip '" + c1 + "'");
                    i--; j--;
                    break;
                case 1:
                    operations.add("Insert '" + c2 + "'");
                    j--;
                    break;
                case 2:
                    operations.add("Delete '" + c1 + "'");
                    i--;
                    break;
                case 3:
                    operations.add("Replace '" + c1 + "' with '" + c2 + "'");
                    i--; j--;
                    break;
            }
        }

        while (i > 0) {
            operations.add("Delete '" + s1.charAt(i - 1) + "'");
            i--;
        }

        while (j > 0) {
            operations.add("Insert '" + s2.charAt(j - 1) + "'");
            j--;
        }

        // Reverse the list to get operations in order
        java.util.Collections.reverse(operations);
        return operations;
    }

    /**
     * Demonstrates the Operation Tracking solution
     */
    public static void main(String[] args) {
        // Example 1 from problem statement
        String s1 = "horse";
        String s2 = "ros";

        System.out.println("Example 1:");
        int distance = minDistance(s1, s2);
        System.out.println("\nMinimum edit distance: " + distance);

        // Example 2 from problem statement
        System.out.println("\n-------------------\n");
        System.out.println("Example 2:");
        s1 = "intention";
        s2 = "execution";

        distance = minDistance(s1, s2);
        System.out.println("\nMinimum edit distance: " + distance);

        // Example of getting operations as a list
        System.out.println("\n-------------------\n");
        System.out.println("Operations as a list (Example 2):");
        java.util.List<String> operations = getOperationsList(s1, s2);
        for (int i = 0; i < operations.size(); i++) {
            System.out.println(i + 1 + ". " + operations.get(i));
        }

        // Real-world application example
        System.out.println("\n-------------------\n");
        System.out.println("Real-world Application Example:");
        System.out.println("This algorithm could be used in:");
        System.out.println("1. Text editors to implement a 'track changes' feature");
        System.out.println("2. Version control systems to visualize differences between files");
        System.out.println("3. DNA sequence alignment to identify mutations");
        System.out.println("4. Spell checkers to suggest corrections");
    }
}
