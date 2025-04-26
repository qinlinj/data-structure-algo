package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._333_rabin_karp_algorithm;

import java.util.*;

/**
 * Rabin-Karp String Matching Algorithm
 * <p>
 * This class demonstrates the classic Rabin-Karp string matching algorithm,
 * which uses a rolling hash function to efficiently find pattern matches in text.
 * <p>
 * Key concepts:
 * 1. Convert both pattern and text windows to numeric hash values
 * 2. Use a sliding window approach to efficiently recalculate hash values
 * 3. Only perform character-by-character comparison when hash values match
 * <p>
 * Time complexity:
 * - Average case: O(n + m) where n is text length and m is pattern length
 * - Worst case: O(n*m) when many hash collisions occur
 * <p>
 * Space complexity: O(1) as we only store hash values and constants
 */

public class _333_a_IntroRabinKarpAlgorithm {

    // Base value for hash calculation (usually a prime number)
    private static final int R = 256; // ASCII character set size

    // Large prime number to prevent overflow
    private static final long Q = 1000000007;

    /**
     * Finds all occurrences of pattern in text using Rabin-Karp algorithm
     *
     * @param text    The text to search in
     * @param pattern The pattern to search for
     * @return List of starting indices of matches
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();

        // Edge cases
        if (m > n || m == 0) {
            return matches;
        }

        // Calculate hash for pattern and initial window of text
        long patternHash = 0;
        long windowHash = 0;

        // Calculate the value of R^(m-1) which we'll need to remove the leftmost character
        long RM = 1;
        for (int i = 0; i < m - 1; i++) {
            RM = (RM * R) % Q;
        }

        // Calculate initial hash values for pattern and first window in text
        for (int i = 0; i < m; i++) {
            patternHash = (R * patternHash + pattern.charAt(i)) % Q;
            windowHash = (R * windowHash + text.charAt(i)) % Q;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= n - m; i++) {
            // If hashes match, verify character by character
            if (patternHash == windowHash) {
                boolean isMatch = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    matches.add(i);
                }
            }

            // Calculate hash for next window: Remove leftmost character and add next character
            if (i < n - m) {
                // Remove leading digit
                windowHash = (windowHash - RM * text.charAt(i)) % Q;
                if (windowHash < 0) windowHash += Q; // Handle negative hash

                // Add trailing digit
                windowHash = (windowHash * R + text.charAt(i + m)) % Q;
            }
        }

        return matches;
    }

    /**
     * Simplified version with more verbose debug output for learning purposes
     */
    public static List<Integer> searchWithDebug(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        System.out.println("------- Rabin-Karp Algorithm Step by Step -------");
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Base (R): " + R);
        System.out.println("Modulus (Q): " + Q);

        // Calculate the value of R^(m-1)
        long RM = 1;
        for (int i = 0; i < m - 1; i++) {
            RM = (RM * R) % Q;
        }
        System.out.println("R^(m-1): " + RM);

        // Calculate hash for pattern
        long patternHash = 0;
        for (int i = 0; i < m; i++) {
            patternHash = (R * patternHash + pattern.charAt(i)) % Q;
        }
        System.out.println("Pattern hash: " + patternHash);

        // Calculate hash for initial window
        long windowHash = 0;
        for (int i = 0; i < m; i++) {
            windowHash = (R * windowHash + text.charAt(i)) % Q;
        }

        System.out.println("\nSliding Window Process:");
        // Slide the pattern over text one by one
        for (int i = 0; i <= n - m; i++) {
            System.out.println("\nWindow at position " + i + ": " + text.substring(i, i + m));
            System.out.println("Window hash: " + windowHash);

            // If hashes match, verify character by character
            if (patternHash == windowHash) {
                System.out.println("Hash match! Verifying characters...");
                boolean isMatch = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        isMatch = false;
                        System.out.println("Character mismatch at position " + j);
                        break;
                    }
                }
                if (isMatch) {
                    System.out.println("Match found at index " + i);
                    matches.add(i);
                }
            } else {
                System.out.println("Hashes don't match, skipping character verification");
            }

            // Calculate hash for next window
            if (i < n - m) {
                // Remove leftmost digit
                windowHash = (windowHash - RM * text.charAt(i)) % Q;
                if (windowHash < 0) windowHash += Q; // Handle negative hash

                // Add trailing digit
                windowHash = (windowHash * R + text.charAt(i + m)) % Q;

                System.out.println("New window hash after update: " + windowHash);
            }
        }

        return matches;
    }

    /**
     * Demonstration of adding and removing digits from a number
     */
    public static void demonstrateNumericOperations() {
        System.out.println("-------- Adding and Removing Digits --------");

        // Adding digits to the lowest position
        String s = "8264";
        int number = 0;

        System.out.println("Demonstrating adding digits to a number:");
        for (int i = 0; i < s.length(); i++) {
            // Add a digit to the lowest position
            number = 10 * number + (s.charAt(i) - '0');
            System.out.println("After adding digit " + s.charAt(i) + ": " + number);
        }

        // Removing the highest digit
        System.out.println("\nDemonstrating removing the highest digit:");
        int originalNumber = 8264;
        System.out.println("Original number: " + originalNumber);

        // Calculate the highest digit and its place value
        int highestDigit = originalNumber / 1000; // 8264 / 1000 = 8
        int placeValue = (int) Math.pow(10, 3); // 10^3 = 1000

        // Remove the highest digit
        int newNumber = originalNumber - highestDigit * placeValue;
        System.out.println("After removing highest digit: " + newNumber);

        // Sliding window hash in base 10
        System.out.println("\nDemonstrating sliding window hash with base 10:");
        String num = "12345";
        int windowSize = 3;
        int windowHash = 0;

        // Calculate initial window hash (123)
        for (int i = 0; i < windowSize; i++) {
            windowHash = 10 * windowHash + (num.charAt(i) - '0');
        }
        System.out.println("Initial window (123): " + windowHash);

        // Slide the window
        for (int i = 0; i <= num.length() - windowSize; i++) {
            System.out.println("Window at position " + i + ": " + num.substring(i, i + windowSize) + ", hash: " + windowHash);

            // Move window forward
            if (i < num.length() - windowSize) {
                // Remove leftmost digit
                int leftDigit = num.charAt(i) - '0';
                windowHash = windowHash - leftDigit * (int) Math.pow(10, windowSize - 1);

                // Add next digit
                int rightDigit = num.charAt(i + windowSize) - '0';
                windowHash = windowHash * 10 + rightDigit;
            }
        }
    }

    public static void main(String[] args) {
        // Demonstrate basic numeric operations related to Rabin-Karp
        demonstrateNumericOperations();

        System.out.println("\n-------- Rabin-Karp Algorithm --------");
        // Test case 1: Simple pattern matching
        String text1 = "ABABCABABABA";
        String pattern1 = "ABABA";
        List<Integer> matches1 = search(text1, pattern1);
        System.out.println("Pattern '" + pattern1 + "' found at positions: " + matches1);

        // Test case 2: Pattern at the beginning
        String text2 = "ABABABABABA";
        String pattern2 = "ABA";
        List<Integer> matches2 = search(text2, pattern2);
        System.out.println("Pattern '" + pattern2 + "' found at positions: " + matches2);

        // Test case 3: No matches
        String text3 = "ABCDEFG";
        String pattern3 = "XYZ";
        List<Integer> matches3 = search(text3, pattern3);
        System.out.println("Pattern '" + pattern3 + "' found at positions: " + matches3);

        // Test case 4: Longer example with detailed debug output
        String text4 = "AAAABAAAAAB";
        String pattern4 = "AAAB";
        System.out.println("\nDetailed example with debug output:");
        List<Integer> matches4 = searchWithDebug(text4, pattern4);
        System.out.println("\nSummary: Pattern '" + pattern4 + "' found at positions: " + matches4);
    }
}
