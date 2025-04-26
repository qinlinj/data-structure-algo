package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._333_rabin_karp_algorithm;

/**
 * Rabin-Karp String Matching Algorithm
 * <p>
 * This class demonstrates the Rabin-Karp string matching algorithm, a pattern matching
 * algorithm that uses hashing to find patterns in text. It combines the sliding window
 * technique with rolling hash calculations to achieve efficient string matching.
 * <p>
 * Key features:
 * 1. Uses a rolling hash function to efficiently calculate hash values
 * 2. Avoids integer overflow with modular arithmetic
 * 3. Handles hash collisions with character-by-character verification
 * 4. Achieves O(N+L) average time complexity where N is text length and L is pattern length
 */
public class RabinKarpStringMatching {

    /**
     * Finds the first occurrence of the pattern string in the text
     *
     * @param txt The text to search in
     * @param pat The pattern to search for
     * @return The index of the first match, or -1 if no match is found
     */
    public static int search(String txt, String pat) {
        // Pattern length
        int L = pat.length();
        // Base (considering ASCII character set)
        int R = 256;
        // Large prime modulus to prevent overflow
        long Q = 1658598167; // A large prime number

        // Calculate R^(L-1) % Q for efficient rolling hash
        long RL = 1;
        for (int i = 1; i <= L - 1; i++) {
            RL = (RL * R) % Q;
        }

        // Calculate hash for pattern
        long patHash = 0;
        for (int i = 0; i < L; i++) {
            patHash = (R * patHash + pat.charAt(i)) % Q;
        }

        // Sliding window hash value
        long windowHash = 0;

        // Sliding window implementation
        int left = 0, right = 0;
        while (right < txt.length()) {
            // Expand window, add character to hash
            windowHash = ((R * windowHash) % Q + txt.charAt(right)) % Q;
            right++;

            // Check when window reaches pattern length
            if (right - left == L) {
                // Compare hashes
                if (windowHash == patHash) {
                    // Verify to avoid hash collisions
                    if (pat.equals(txt.substring(left, right))) {
                        return left; // Match found
                    }
                }

                // Slide window: remove leftmost character
                windowHash = (windowHash - (txt.charAt(left) * RL) % Q + Q) % Q;
                // Add Q and take modulus to handle negative values
                left++;
            }
        }

        return -1; // No match found
    }

    /**
     * Finds all occurrences of the pattern in the text
     *
     * @param txt The text to search in
     * @param pat The pattern to search for
     * @return An array of indices where matches are found
     */
    public static int[] searchAll(String txt, String pat) {
        // Pattern length
        int L = pat.length();
        if (L == 0 || L > txt.length()) {
            return new int[0];
        }

        // Base (considering ASCII character set)
        int R = 256;
        // Large prime modulus to prevent overflow
        long Q = 1658598167; // A large prime number

        // Calculate R^(L-1) % Q for efficient rolling hash
        long RL = 1;
        for (int i = 1; i <= L - 1; i++) {
            RL = (RL * R) % Q;
        }

        // Calculate hash for pattern
        long patHash = 0;
        for (int i = 0; i < L; i++) {
            patHash = (R * patHash + pat.charAt(i)) % Q;
        }

        // Sliding window hash value
        long windowHash = 0;

        // Store matches
        int[] tempResults = new int[txt.length()]; // Maximum possible matches
        int matchCount = 0;

        // Sliding window implementation
        int left = 0, right = 0;
        while (right < txt.length()) {
            // Expand window, add character to hash
            windowHash = ((R * windowHash) % Q + txt.charAt(right)) % Q;
            right++;

            // Check when window reaches pattern length
            if (right - left == L) {
                // Compare hashes
                if (windowHash == patHash) {
                    // Verify to avoid hash collisions
                    if (pat.equals(txt.substring(left, right))) {
                        tempResults[matchCount++] = left; // Store match
                    }
                }

                // Slide window: remove leftmost character
                windowHash = (windowHash - (txt.charAt(left) * RL) % Q + Q) % Q;
                left++;
            }
        }

        // Create result array of exact size
        int[] result = new int[matchCount];
        System.arraycopy(tempResults, 0, result, 0, matchCount);
        return result;
    }

    /**
     * Demonstrates the rolling hash calculation step by step
     *
     * @param txt Text to process
     * @param pat Pattern to search for
     */
    public static void demonstrateRollingHash(String txt, String pat) {
        int L = pat.length();
        int R = 256;
        long Q = 1658598167;

        System.out.println("Demonstrating Rabin-Karp Rolling Hash");
        System.out.println("Text: \"" + txt + "\"");
        System.out.println("Pattern: \"" + pat + "\"");
        System.out.println("Base (R): " + R);
        System.out.println("Modulus (Q): " + Q);

        // Calculate R^(L-1) % Q
        long RL = 1;
        for (int i = 1; i <= L - 1; i++) {
            RL = (RL * R) % Q;
        }
        System.out.println("R^(L-1) % Q: " + RL);

        // Calculate pattern hash
        long patHash = 0;
        System.out.println("\nCalculating pattern hash:");
        for (int i = 0; i < L; i++) {
            long oldHash = patHash;
            patHash = (R * patHash + pat.charAt(i)) % Q;
            System.out.printf("Add '%c': (%d * %d + %d) %% %d = %d\n",
                    pat.charAt(i), R, oldHash, (int) pat.charAt(i), Q, patHash);
        }
        System.out.println("Pattern hash: " + patHash);

        // Initial window hash
        long windowHash = 0;
        System.out.println("\nInitial window hash calculation:");
        for (int i = 0; i < L && i < txt.length(); i++) {
            long oldHash = windowHash;
            windowHash = (R * windowHash + txt.charAt(i)) % Q;
            System.out.printf("Add '%c': (%d * %d + %d) %% %d = %d\n",
                    txt.charAt(i), R, oldHash, (int) txt.charAt(i), Q, windowHash);
        }

        // Sliding window demonstration
        System.out.println("\nSliding window process:");
        int left = 0;
        for (int right = L; right < txt.length(); right++) {
            System.out.println("\nCurrent window: \"" + txt.substring(left, right) + "\"");
            System.out.println("Window hash: " + windowHash);
            System.out.println("Pattern hash: " + patHash);

            if (windowHash == patHash) {
                if (pat.equals(txt.substring(left, right))) {
                    System.out.println("Match found at index " + left);
                } else {
                    System.out.println("Hash collision! Strings are different.");
                }
            } else {
                System.out.println("Hashes don't match.");
            }

            // Remove leftmost character
            long oldHash = windowHash;
            long leftTermMod = (txt.charAt(left) * RL) % Q;
            windowHash = (windowHash - leftTermMod + Q) % Q;
            System.out.printf("Remove '%c': (%d - %d + %d) %% %d = %d\n",
                    txt.charAt(left), oldHash, leftTermMod, Q, Q, windowHash);

            // Add next character
            oldHash = windowHash;
            windowHash = (R * windowHash + txt.charAt(right)) % Q;
            System.out.printf("Add '%c': (%d * %d + %d) %% %d = %d\n",
                    txt.charAt(right), R, oldHash, (int) txt.charAt(right), Q, windowHash);

            left++;
        }

        // Check final window
        System.out.println("\nFinal window: \"" + txt.substring(left) + "\"");
        System.out.println("Window hash: " + windowHash);
        System.out.println("Pattern hash: " + patHash);

        if (windowHash == patHash) {
            if (pat.equals(txt.substring(left))) {
                System.out.println("Match found at index " + left);
            } else {
                System.out.println("Hash collision! Strings are different.");
            }
        } else {
            System.out.println("Hashes don't match.");
        }
    }

    /**
     * Shows the number operations for adding and removing digits
     */
    public static void demonstrateNumberOperations() {
        System.out.println("\n==== Demonstrating Basic Number Operations ====\n");

        // Adding digits to the lowest position (right side)
        String s = "8264";
        int number = 0;

        System.out.println("Converting string \"" + s + "\" to number:");
        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            int oldNumber = number;
            number = 10 * number + digit;
            System.out.printf("Add %d: 10 * %d + %d = %d\n", digit, oldNumber, digit, number);
        }
        System.out.println("Final number: " + number);

        // Removing digits from the highest position (left side)
        System.out.println("\nRemoving digits from the highest position:");
        int originalNumber = 8264;
        int highestDigit = 8;
        int placeValue = 1000; // 10^3

        System.out.printf("Original number: %d\n", originalNumber);
        System.out.printf("Remove highest digit %d: %d - %d * %d = %d\n",
                highestDigit, originalNumber, highestDigit, placeValue, originalNumber - highestDigit * placeValue);
    }

    public static void main(String[] args) {
        demonstrateNumberOperations();

        System.out.println("\n==== Rabin-Karp String Matching Algorithm ====\n");

        // Basic test cases
        String txt1 = "ABABCABABABA";
        String pat1 = "ABABA";
        int result1 = search(txt1, pat1);
        System.out.println("Finding \"" + pat1 + "\" in \"" + txt1 + "\"");
        System.out.println("Result: " + (result1 != -1 ? "Found at index " + result1 : "Not found"));

        String txt2 = "AABAACAADAABAABA";
        String pat2 = "AABA";
        int result2 = search(txt2, pat2);
        System.out.println("\nFinding \"" + pat2 + "\" in \"" + txt2 + "\"");
        System.out.println("Result: " + (result2 != -1 ? "Found at index " + result2 : "Not found"));

        // Finding all occurrences
        int[] allMatches = searchAll(txt2, pat2);
        System.out.println("\nFinding all occurrences of \"" + pat2 + "\" in \"" + txt2 + "\"");
        System.out.print("Results: ");
        for (int i = 0; i < allMatches.length; i++) {
            System.out.print(allMatches[i]);
            if (i < allMatches.length - 1) System.out.print(", ");
        }
        System.out.println();

        // Detailed demonstration with step-by-step hash calculations
        System.out.println("\n==== Detailed Rolling Hash Demonstration ====\n");
        demonstrateRollingHash("ABRACADABRA", "ABRA");
    }
}
