package org.qinlinj.practical.string;

/**
 * Optimized Rabin-Karp String Matching Algorithm with Rolling Hash
 * <p>
 * Advantages of Rabin-Karp with Rolling Hash:
 * 1. Optimal Efficiency: Achieves O(m+n) time complexity where m is main string length and n is pattern length
 * 2. Constant-time Hash Updates: Uses rolling hash technique to calculate subsequent hash values in O(1) time
 * 3. Reduced False Positives: Includes character-by-character verification to handle hash collisions
 * 4. Memory Efficient: Still requires O(m) space but with more efficient computations
 * 5. Practical for Large Texts: Especially effective for longer strings and patterns
 * <p>
 * Concept and Principle:
 * The Rabin-Karp algorithm with rolling hash optimizes string matching by:
 * 1. Computing an initial hash for the pattern and the first substring of the text
 * 2. Using a rolling hash function to efficiently compute subsequent hash values
 * 3. Only performing character-by-character comparison when hash values match
 * <p>
 * Rolling hash is the key optimization - it allows calculating the next hash value
 * in constant time by using the previous hash value, removing the oldest character's
 * contribution and adding the newest character's contribution.
 * <p>
 * Visual example:
 * Text:    "t h i s   i s   y o u r   c o d e"
 * Pattern: "y o u r"
 * <p>
 * Step 1: Compute initial hash values
 * hash("this") = hash value of first substring
 * hash("your") = hash value of pattern
 * <p>
 * Step 2: Use rolling hash to compute subsequent hashes
 * hash(" is ") = hash("this") - hash("t") + hash(" ")
 * hash("is y") = hash(" is ") - hash(" ") + hash("y")
 * ... and so on
 * <p>
 * Step 3: When hash("your") == hash(text substring), verify with character comparison
 * If the characters all match, we've found our pattern.
 */
public class RK2 {

    public static void main(String[] args) {
        RK2 b = new RK2();
        String mainStr = "this is your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of pattern in mainStr using the optimized Rabin-Karp algorithm
     * <p>
     * Visual example for our specific case:
     * mainStr:  "this is your code"
     * pattern:  "your"
     * <p>
     * Initial hash calculation:
     * - hash("this") = ('s'-'a') + ('i'-'a') + ('h'-'a') + ('t'-'a')
     * - hash("your") = ('r'-'a') + ('u'-'a') + ('o'-'a') + ('y'-'a')
     * <p>
     * Rolling hash calculations:
     * - hash(" is ") = hash("this") - ('t'-'a') + (' '-'a')
     * - hash("is y") = hash(" is ") - (' '-'a') + ('y'-'a')
     * ...and so on
     * <p>
     * When hash values match, we perform character comparison to verify the match.
     * <p>
     * Time Complexity: O(m+n) in average case
     * - Initial hash calculations: O(n)
     * - Rolling hash calculations: O(m)
     * - Character verification: O(n) in worst case (only performed on potential matches)
     * <p>
     * Space Complexity: O(m) for storing hash codes
     *
     * @param mainStr the main string to search in
     * @param pattern the pattern string to search for
     * @return the starting index of the first occurrence of pattern in mainStr, or -1 if not found
     */
    private int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        int[] hashCodes = new int[m - n + 1];
        hashCodes[0] = calFirstSubStrHashCode(mainStr.substring(0, n));

        for (int i = 1; i < m - n + 1; i++) {
            hashCodes[i] = calHashCode(mainStr, i, hashCodes, n);
        }

        int hashCode = calFirstSubStrHashCode(pattern);

        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                int k = i;
                for (int j = 0; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) != pattern.charAt(j)) {
                        break;
                    }
                    if (j == n - 1) return i;
                }
            }
        }
        return -1;
    }

    /**
     * Calculates the hash code for a string by summing the difference between
     * each character and 'a', reading the string from right to left.
     * <p>
     * This is a simple hash function that works well for demonstration purposes.
     * In a production environment, a more sophisticated hash function would be used.
     * <p>
     * Example calculation for "your":
     * hash = ('r'-'a') + ('u'-'a') + ('o'-'a') + ('y'-'a')
     * = (114-97) + (117-97) + (111-97) + (121-97)
     * = 17 + 20 + 14 + 24 = 75
     * <p>
     * Time Complexity: O(n) where n is the length of the string
     *
     * @param str the input string
     * @return the hash code for the string
     */
    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();

        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            hashCode += (str.charAt(n - i - 1) - 'a');
        }

        return hashCode;
    }

    /**
     * Calculates the hash code for a substring using the rolling hash technique.
     * <p>
     * The key advantage of rolling hash is that we can compute the hash of the current
     * substring in O(1) time using the hash of the previous substring.
     * <p>
     * Rolling hash formula:
     * hash(text[i...i+n-1]) = hash(text[i-1...i+n-2]) - hash(text[i-1]) + hash(text[i+n-1])
     * <p>
     * Visual example for rolling from "this" to " is ":
     * hash(" is ") = hash("this") - ('t'-'a') + (' '-'a')
     * <p>
     * Time Complexity: O(1) - constant time
     *
     * @param mainStr   the main string
     * @param i         the current index
     * @param hashCodes array of previously computed hash codes
     * @param n         the length of the pattern
     * @return the hash code for the current substring
     */
    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) {
        return hashCodes[i - 1] - (mainStr.charAt(i - 1) - 'a')
                + (mainStr.charAt(i + n - 1) - 'a');
    }
}
