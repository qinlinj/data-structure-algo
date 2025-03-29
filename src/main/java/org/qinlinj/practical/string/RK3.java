package org.qinlinj.practical.string;

// @formatter:off
/**
 * Rabin-Karp String Matching Algorithm with Polynomial Rolling Hash
 *
 * Advantages of Rabin-Karp with Polynomial Rolling Hash:
 * 1. Improved Collision Resistance: Using polynomial hash function significantly reduces hash collisions
 * 2. Linear Time Complexity: Achieves O(m+n) time complexity where m is main string length and n is pattern length
 * 3. Efficient Hash Updates: Uses polynomial rolling hash for O(1) time hash updates
 * 4. Suitable for Multiple Pattern Matching: Can be extended to search multiple patterns simultaneously
 * 5. Better Hash Distribution: Polynomial hash provides better distribution of hash values
 *
 * Concept and Principle:
 * This implementation uses a polynomial hash function where each character contributes
 * to the hash value based on its value and position in the string. Specifically, it uses
 * powers of 26 (since we're dealing with lowercase English letters) as position weights.
 *
 * The polynomial hash is calculated as:
 * hash(s) = s[0]*26^(n-1) + s[1]*26^(n-2) + ... + s[n-1]*26^0
 *
 * Where s[i] is the numeric value of the character (relative to 'a').
 *
 * The rolling hash update formula is:
 * hash(s[i+1...i+n]) = hash(s[i...i+n-1])*26 - s[i]*26^n + s[i+n]
 *
 * Visual example:
 * Text:    "t h i s   i s   y o u r   c o d e"
 * Pattern: "y o u r"
 *
 * Step 1: Compute polynomial hash for first substring and pattern
 * hash("this") = ('t'-'a')*26^3 + ('h'-'a')*26^2 + ('i'-'a')*26^1 + ('s'-'a')*26^0
 * hash("your") = ('y'-'a')*26^3 + ('o'-'a')*26^2 + ('u'-'a')*26^1 + ('r'-'a')*26^0
 *
 * Step 2: Use rolling hash to compute subsequent hashes
 * hash(" is ") = hash("this")*26 - ('t'-'a')*26^4 + (' '-'a')
 * hash("is y") = hash(" is ")*26 - (' '-'a')*26^4 + ('y'-'a')
 * ... and so on
 *
 * Step 3: When hash values match, we've found our pattern (assuming no hash collisions)
 */
public class RK3 {

    public static void main(String[] args) {
        RK3 b = new RK3();
        String mainStr = "this is your code";
        String patternStr = "your";

        // Print the index where the pattern is found in the main string
        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of pattern in mainStr using the Rabin-Karp algorithm
     * with polynomial rolling hash.
     *
     * Visual example for our specific case:
     * mainStr:  "this is your code"
     * pattern:  "your"
     *
     * Initial polynomial hash for "this":
     * ('t'-'a')*26^3 + ('h'-'a')*26^2 + ('i'-'a')*26^1 + ('s'-'a')*26^0
     * = (116-97)*17576 + (104-97)*676 + (105-97)*26 + (115-97)*1
     * = 19*17576 + 7*676 + 8*26 + 18*1
     * = 333944 + 4732 + 208 + 18 = 338902
     *
     * Initial polynomial hash for "your":
     * ('y'-'a')*26^3 + ('o'-'a')*26^2 + ('u'-'a')*26^1 + ('r'-'a')*26^0
     * = (121-97)*17576 + (111-97)*676 + (117-97)*26 + (114-97)*1
     * = 24*17576 + 14*676 + 20*26 + 17*1
     * = 421824 + 9464 + 520 + 17 = 431825
     *
     * Note: This implementation assumes no hash collisions to simplify the code.
     * In practice, character-by-character verification would be needed.
     *
     * Time Complexity: O(m+n) in average case
     * - Initial hash calculations: O(n)
     * - Rolling hash calculations: O(m)
     *
     * Space Complexity: O(m) for storing hash codes
     *
     * @param mainStr the main string to search in
     * @param pattern the pattern string to search for
     * @return the starting index of the first occurrence of pattern in mainStr, or -1 if not found
     */
    public int indexOf(String mainStr, String pattern) {
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

        // Compare hash codes to find matches
        // NOTE: In a complete implementation, we would verify character-by-character
        // to handle hash collisions, but this simplified version assumes no collisions
        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                return i;
            }
        }
        // Pattern not found
        return -1;
    }

    /**
     * Calculates the polynomial hash code for a string.
     *
     * This hash function uses a polynomial with base 26 (for the 26 lowercase letters).
     * Each character contributes to the hash based on its value and position, with
     * position weights calculated as powers of 26.
     *
     * Formula: hash = Σ (s[i] - 'a') * 26^(n-i-1) for i from 0 to n-1
     *
     * Example calculation for "your":
     * hash = ('y'-'a')*26^3 + ('o'-'a')*26^2 + ('u'-'a')*26^1 + ('r'-'a')*26^0
     *
     * The numeric values are:
     * 'y'-'a' = 121-97 = 24
     * 'o'-'a' = 111-97 = 14
     * 'u'-'a' = 117-97 = 20
     * 'r'-'a' = 114-97 = 17
     *
     * So hash = 24*26^3 + 14*26^2 + 20*26^1 + 17*26^0
     *         = 24*17576 + 14*676 + 20*26 + 17*1
     *         = 421824 + 9464 + 520 + 17
     *         = 431825
     *
     * Time Complexity: O(n) where n is the length of the string
     *
     * @param str the input string
     * @return the polynomial hash code for the string
     */
    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();

        int hashCode = 0;
        // Calculate hash using polynomial formula
        for (int i = 0; i < n; i++) {
            // For each character, add its contribution: (char - 'a') * 26^position
            hashCode += (int) Math.pow(26, i) * (str.charAt(n - i - 1) - 'a');
        }

        return hashCode;
    }

    /**
     * Calculates the hash code for a substring using the polynomial rolling hash technique.
     *
     * The polynomial rolling hash update formula is:
     * hash(s[i...i+n-1]) = hash(s[i-1...i+n-2]) * 26 - (s[i-1] - 'a') * 26^n + (s[i+n-1] - 'a')
     *
     * This allows us to compute the new hash in O(1) time instead of O(n).
     *
     * Visual example for rolling from "this" to " is ":
     * hash(" is ") = hash("this") * 26 - ('t'-'a') * 26^4 + (' '-'a')
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
        // Rolling hash formula:
        // Multiply previous hash by base (26)
        // Subtract the contribution of the character moving out of the window
        // Add the contribution of the character moving into the window
        return hashCodes[i - 1] * 26 - (mainStr.charAt(i - 1) - 'a') * (int) Math.pow(26, n)
                + (mainStr.charAt(i + n - 1) - 'a');
    }
}