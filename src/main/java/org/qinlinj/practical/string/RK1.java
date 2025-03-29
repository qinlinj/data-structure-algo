package org.qinlinj.practical.string;

// @formatter:off
/**
 * Rabin-Karp String Matching Algorithm Implementation
 *
 * Advantages of Rabin-Karp:
 * 1. Efficiency: Average case time complexity of O(m+n) where m is the main string length and n is pattern length
 * 2. Hash-based approach: Uses hashing to quickly filter non-matching substrings
 * 3. Multiple pattern search: Can be extended to search for multiple patterns simultaneously
 * 4. Works well with binary data: Not limited to character strings
 *
 * Concept and Principle:
 * The Rabin-Karp algorithm uses hashing to find patterns in strings. Instead of checking
 * character by character, it computes hash values for the pattern and each substring of
 * the text with the same length as the pattern. If the hash values match, it performs
 * a full comparison to confirm the match (to handle hash collisions).
 *
 * The key insight is that computing hash values can be faster than character-by-character
 * comparison, especially when most substrings won't match the pattern.
 *
 * Visual example:
 * Text:    "t h i s   i s   y o u r   c o d e"
 * Pattern: "y o u r"
 *
 * Step 1: Compute hash value for "your" (pattern)
 * hash("your") = some integer value
 *
 * Step 2: Compute hash values for each 4-character substring of the text
 * hash("this") = value1
 * hash(" is ") = value2
 * hash("is y") = value3
 * hash("s yo") = value4
 * hash(" you") = value5
 * hash("your") = value6
 * hash("our ") = value7
 * hash("ur c") = value8
 * hash("r co") = value9
 * hash(" cod") = value10
 * hash("code") = value11
 *
 * Step 3: Compare hash values
 * If hash("your") == hash(any substring), check character by character to confirm match
 * In this case, hash("your") == value6, and "your" == "your", so match found at index 8
 */
public class RK1 {

    public static void main(String[] args) {
        RK1 b = new RK1();
        String mainStr = "this is your code";
        String patternStr = "your";

        // Print the index of the first occurrence of patternStr in mainStr
        System.out.println(b.indexOf(mainStr, patternStr));
    }

    /**
     * Finds the first occurrence of pattern in mainStr using the Rabin-Karp algorithm
     *
     * Visual example for our specific case:
     * mainStr:  "this is your code"
     * pattern:  "your"
     *
     * Step 1: Compute hash value for "your"
     *
     * Step 2: Compute hash values for all 4-character substrings:
     * "this", " is ", "is y", "s yo", " you", "your", "our ", "ur c", "r co", " cod", "code"
     *
     * Step 3: Compare the pattern hash with each substring hash
     * When we find a hash match at "your", we have our result: index 8
     *
     * Time Complexity: O(m*n) in worst case, but typically closer to O(m+n) in practice
     * - Computing hash for pattern: O(n)
     * - Computing hashes for all substrings: O(m*n) in this implementation (could be optimized to O(m))
     * - Comparing hashes: O(m)
     * - Character comparison on hash match: O(n) (only performed on potential matches)
     *
     * Space Complexity: O(m) for storing all hash codes
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

        // Compute hash codes for all possible n-length substrings of mainStr
        int[] hashCodes = new int[m - n + 1];
        for (int i = 0; i < m - n + 1; i++) {
            // Extract substring and calculate its hash
            hashCodes[i] = calHashCode(mainStr.substring(i, i + n));
        }

        // Compute hash code for the pattern
        int hashCode = calHashCode(pattern);

        // Compare the pattern's hash code with each substring's hash code
        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                // Hash match found - in a complete implementation, we should verify
                // by comparing the actual strings to avoid false positives due to hash collisions
                // For simplicity, this implementation assumes no hash collisions
                return i;
            }
        }

        // Pattern not found
        return -1;
    }

    /**
     * Calculates a hash code for the given string
     *
     * This implementation uses Java's built-in hashCode method, which has O(n) time complexity
     * where n is the length of the string.
     *
     * In a more optimized Rabin-Karp implementation, we would use a rolling hash function
     * that allows us to compute the next substring's hash in O(1) time using the previous hash.
     *
     * Example of a rolling hash calculation:
     * For hash("abcd") = a*31^3 + b*31^2 + c*31^1 + d*31^0
     * Then hash("bcde") = (hash("abcd") - a*31^3) * 31 + e*31^0
     *
     * Time Complexity: O(n) where n is the length of the string
     *
     * @param str the input string
     * @return the hash code for the string
     */
    private int calHashCode(String str) {
        return str.hashCode(); // O(n)
    }
}
