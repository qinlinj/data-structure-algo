package org.qinlinj.algoframework._800_dynamic_programming_algo._820_subsequence_problems._821_edit_distance;

/**
 * Class: Introduction to Edit Distance Problem
 * <p>
 * Key Points:
 * 1. The Edit Distance problem calculates the minimum number of operations required to transform one string into another.
 * 2. Three operations are allowed: insert a character, delete a character, replace a character.
 * 3. This algorithm has practical applications such as:
 * - Text correction (e.g., fixing errors in published content)
 * - DNA sequence comparison (measuring similarity between DNA sequences)
 * - Spell checking and autocorrect features
 * 4. The problem can be found as LeetCode #72 (Medium difficulty).
 */
public class _821_a_EditDistanceIntroduction {

    /**
     * Example implementation of the edit distance algorithm
     *
     * @param word1 The source string to transform
     * @param word2 The target string
     * @return The minimum number of operations required
     */
    public int minDistance(String word1, String word2) {
        // This is just a placeholder function that calls our actual implementation
        return calculateEditDistance(word1, word2);
    }

    /**
     * Sample usage of the edit distance algorithm with the example from LeetCode
     */
    public void demonstrateEditDistance() {
        String word1 = "horse";
        String word2 = "ros";

        int operations = minDistance(word1, word2);

        System.out.println("Transforming '" + word1 + "' to '" + word2 + "'");
        System.out.println("Minimum operations required: " + operations);
        System.out.println("Possible sequence of operations:");
        System.out.println("1. Replace 'h' with 'r': 'horse' -> 'rorse'");
        System.out.println("2. Delete 'r': 'rorse' -> 'rose'");
        System.out.println("3. Delete 'e': 'rose' -> 'ros'");
    }

    /**
     * This method would contain the actual implementation of the edit distance algorithm
     * For now it's a stub - the actual implementation will be in subsequent classes
     */
    private int calculateEditDistance(String s1, String s2) {
        // Placeholder - implementation will be in other classes
        return 0;
    }

    /**
     * Another example with longer strings
     */
    public void demonstrateLongerExample() {
        String word1 = "intention";
        String word2 = "execution";

        int operations = 5; // Hardcoded for this example

        System.out.println("Transforming '" + word1 + "' to '" + word2 + "'");
        System.out.println("Minimum operations required: " + operations);
        System.out.println("Possible sequence of operations:");
        System.out.println("1. Delete 't': 'intention' -> 'inention'");
        System.out.println("2. Replace 'i' with 'e': 'inention' -> 'enention'");
        System.out.println("3. Replace 'n' with 'x': 'enention' -> 'exention'");
        System.out.println("4. Replace 'n' with 'c': 'exention' -> 'exection'");
        System.out.println("5. Insert 'u': 'exection' -> 'execution'");
    }
}