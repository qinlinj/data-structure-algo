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

        System.out.println(b.indexOf(mainStr, patternStr));
    }

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
                return i;
            }
        }
        return -1;
    }


    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();

        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            hashCode += (int) Math.pow(26, i) * (str.charAt(n - i - 1) - 'a');
        }

        return hashCode;
    }

    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) {
        return hashCodes[i - 1] * 26 - (mainStr.charAt(i - 1) - 'a') * (int) Math.pow(26, n)
                + (mainStr.charAt(i + n - 1) - 'a');
    }
}
