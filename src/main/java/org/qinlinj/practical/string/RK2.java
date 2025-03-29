package org.qinlinj.practical.string;

// @formatter:off
/**
 * Optimized Rabin-Karp String Matching Algorithm with Rolling Hash
 *
 * Advantages of Rabin-Karp with Rolling Hash:
 * 1. Optimal Efficiency: Achieves O(m+n) time complexity where m is main string length and n is pattern length
 * 2. Constant-time Hash Updates: Uses rolling hash technique to calculate subsequent hash values in O(1) time
 * 3. Reduced False Positives: Includes character-by-character verification to handle hash collisions
 * 4. Memory Efficient: Still requires O(m) space but with more efficient computations
 * 5. Practical for Large Texts: Especially effective for longer strings and patterns
 *
 * Concept and Principle:
 * The Rabin-Karp algorithm with rolling hash optimizes string matching by:
 * 1. Computing an initial hash for the pattern and the first substring of the text
 * 2. Using a rolling hash function to efficiently compute subsequent hash values
 * 3. Only performing character-by-character comparison when hash values match
 *
 * Rolling hash is the key optimization - it allows calculating the next hash value
 * in constant time by using the previous hash value, removing the oldest character's
 * contribution and adding the newest character's contribution.
 *
 * Visual example:
 * Text:    "t h i s   i s   y o u r   c o d e"
 * Pattern: "y o u r"
 *
 * Step 1: Compute initial hash values
 * hash("this") = hash value of first substring
 * hash("your") = hash value of pattern
 *
 * Step 2: Use rolling hash to compute subsequent hashes
 * hash(" is ") = hash("this") - hash("t") + hash(" ")
 * hash("is y") = hash(" is ") - hash(" ") + hash("y")
 * ... and so on
 *
 * Step 3: When hash("your") == hash(text substring), verify with character comparison
 * If the characters all match, we've found our pattern.
 */
public class RK2 {

    public static void main(String[] args) {
        RK2 b = new RK2();
        String mainStr = "this is your code";
        String patternStr = "your";

        // Print the index where the pattern is found in the main string
        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of pattern in mainStr using the optimized Rabin-Karp algorithm
     *
     * Visual example for our specific case:
     * mainStr:  "this is your code"
     * pattern:  "your"
     *
     * Initial hash calculation:
     * - hash("this") = ('s'-'a') + ('i'-'a') + ('h'-'a') + ('t'-'a')
     * - hash("your") = ('r'-'a') + ('u'-'a') + ('o'-'a') + ('y'-'a')
     *
     * Rolling hash calculations:
     * - hash(" is ") = hash("this") - ('t'-'a') + (' '-'a')
     * - hash("is y") = hash(" is ") - (' '-'a') + ('y'-'a')
     * ...and so on
     *
     * When hash values match, we perform character comparison to verify the match.
     *
     * Time Complexity: O(m+n) in average case
     * - Initial hash calculations: O(n)
     * - Rolling hash calculations: O(m)
     * - Character verification: O(n) in worst case (only performed on potential matches)
     *
     * Space Complexity: O(m) for storing hash codes
     *
     * @param mainStr the main string to search in
     * @param pattern the pattern string to search for
     * @return the starting index of the first occurrence of pattern in mainStr, or -1 if not found
     */
    private int indexOf(String mainStr, String pattern) {
        // Handle null inputs
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        // If pattern is longer than main string, it cannot be found
        if (m < n) return -1;

        // Array to store hash codes for all possible n-length substrings
        int[] hashCodes = new int[m - n + 1];

        // Calculate hash code for the first substring
        hashCodes[0] = calFirstSubStrHashCode(mainStr.substring(0, n));

        // Calculate hash codes for remaining substrings using rolling hash technique
        for (int i = 1; i < m - n + 1; i++) {
            hashCodes[i] = calHashCode(mainStr, i, hashCodes, n);
        }

        // Calculate hash code for the pattern
        int hashCode = calFirstSubStrHashCode(pattern);

        // Compare hash codes and verify potential matches
        for (int i = 0; i < m - n + 1; i++) {
            // If hash codes match, verify character by character
            if (hashCode == hashCodes[i]) {
                int k = i;
                for (int j = 0; j < n && k < m; j++, k++) {
                    // If any character doesn't match, break
                    if (mainStr.charAt(k) != pattern.charAt(j)) {
                        break;
                    }
                    // If we've matched all characters in the pattern
                    if (j == n - 1) return i;
                }
            }
        }
        // Pattern not found
        return -1;
    }

    /**
     * Calculates the hash code for a string by summing the difference between
     * each character and 'a', reading the string from right to left.
     *
     * This is a simple hash function that works well for demonstration purposes.
     * In a production environment, a more sophisticated hash function would be used.
     *
     * Example calculation for "your":
     * hash = ('r'-'a') + ('u'-'a') + ('o'-'a') + ('y'-'a')
     *      = (114-97) + (117-97) + (111-97) + (121-97)
     *      = 17 + 20 + 14 + 24 = 75
     *
     * Time Complexity: O(n) where n is the length of the string
     *
     * @param str the input string
     * @return the hash code for the string
     */
    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();

        int hashCode = 0;
        // Calculate hash by summing (char - 'a') for each character from right to left
        for (int i = 0; i < n; i++) {
            hashCode += (str.charAt(n - i - 1) - 'a');
        }

        return hashCode;
    }

    /**
     * Calculates the hash code for a substring using the rolling hash technique.
     *
     * The key advantage of rolling hash is that we can compute the hash of the current
     * substring in O(1) time using the hash of the previous substring.
     *
     * Rolling hash formula:
     * hash(text[i...i+n-1]) = hash(text[i-1...i+n-2]) - hash(text[i-1]) + hash(text[i+n-1])
     *
     * Visual example for rolling from "this" to " is ":
     * hash(" is ") = hash("this") - ('t'-'a') + (' '-'a')
     *
     * Time Complexity: O(1) - constant time
     *
     * @param mainStr the main string
     * @param i the current index
     * @param hashCodes array of previously computed hash codes
     * @param n the length of the pattern
     * @return the hash code for the current substring
     */
    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) {
        // Remove contribution of the character moving out of the window
        // Add contribution of the character moving into the window
        return hashCodes[i - 1] - (mainStr.charAt(i - 1) - 'a')
                + (mainStr.charAt(i + n - 1) - 'a');
    }
}
