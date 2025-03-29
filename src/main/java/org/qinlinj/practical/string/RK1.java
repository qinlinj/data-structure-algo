package org.qinlinj.practical.string;

/**
 * Rabin-Karp String Matching Algorithm Implementation
 * <p>
 * Advantages of Rabin-Karp:
 * 1. Efficiency: Average case time complexity of O(m+n) where m is the main string length and n is pattern length
 * 2. Hash-based approach: Uses hashing to quickly filter non-matching substrings
 * 3. Multiple pattern search: Can be extended to search for multiple patterns simultaneously
 * 4. Works well with binary data: Not limited to character strings
 * <p>
 * Concept and Principle:
 * The Rabin-Karp algorithm uses hashing to find patterns in strings. Instead of checking
 * character by character, it computes hash values for the pattern and each substring of
 * the text with the same length as the pattern. If the hash values match, it performs
 * a full comparison to confirm the match (to handle hash collisions).
 * <p>
 * The key insight is that computing hash values can be faster than character-by-character
 * comparison, especially when most substrings won't match the pattern.
 * <p>
 * Visual example:
 * Text:    "t h i s   i s   y o u r   c o d e"
 * Pattern: "y o u r"
 * <p>
 * Step 1: Compute hash value for "your" (pattern)
 * hash("your") = some integer value
 * <p>
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
 * <p>
 * Step 3: Compare hash values
 * If hash("your") == hash(any substring), check character by character to confirm match
 * In this case, hash("your") == value6, and "your" == "your", so match found at index 8
 */
public class RK1 {

    public static void main(String[] args) {
        RK1 b = new RK1();
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
        for (int i = 0; i < m - n + 1; i++) {
            hashCodes[i] = calHashCode(mainStr.substring(i, i + n));
        }

        int hashCode = calHashCode(pattern);

        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                return i;
            }
        }

        return -1;
    }

    private int calHashCode(String str) {
        return str.hashCode(); // O(n)
    }
}
