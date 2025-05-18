package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._814_dp_enumeration_perspectives;

/**
 * Dynamic Programming Enumeration Perspectives
 * <p>
 * Summary:
 * - This class combines the concepts discussed in previous classes to demonstrate
 * how different enumeration perspectives affect algorithm design and efficiency
 * - Key insights:
 * 1. The same DP problem can often be approached from multiple perspectives
 * 2. Different perspectives lead to different state transition equations
 * 3. All valid perspectives produce correct results, but may have vastly different efficiency
 * 4. Problems that can be abstracted as the "ball-box model" typically allow multiple perspectives
 * - Identifying when multiple enumeration perspectives are possible is a valuable skill
 * - Most string matching, subsequence, and subset problems can be approached from either:
 * - Element-focused perspective (ball perspective)
 * - Pattern-focused perspective (box perspective)
 * - Choosing the right perspective can reduce time complexity from O(n²) to O(n)
 */
public class _814_d_DPEnumerationPerspectives {

    public static void main(String[] args) {
        // Test LCS
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("LCS using perspective 1: " + lcs.longestCommonSubsequence1(text1, text2));  // 3
        System.out.println("LCS using perspective 2: " + lcs.longestCommonSubsequence2(text1, text2));  // 3

        // Test Coin Change
        CoinChange cc = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Coin Change using perspective 1: " + cc.coinChange1(coins, amount));  // 3
        System.out.println("Coin Change using perspective 2: " + cc.coinChange2(coins, amount));  // 3

        System.out.println("\nNote: While both perspectives give the same results, the efficiency differs.");
        System.out.println("The best perspective is often the one that minimizes nested loops in the implementation.");
    }

    /**
     * Example: Longest Common Subsequence (LCS)
     * Demonstrates two perspectives for solving the same problem
     */
    public static class LongestCommonSubsequence {

        /**
         * Approach 1: Using text1 perspective (focus on decisions for each character in text1)
         * Definition: dp(i,j) = length of LCS between text1[i..] and text2[j..]
         */
        public int longestCommonSubsequence1(String text1, String text2) {
            Integer[][] memo = new Integer[text1.length()][text2.length()];
            return dp1(text1, 0, text2, 0, memo);
        }

        private int dp1(String text1, int i, String text2, int j, Integer[][] memo) {
            // Base cases
            if (i == text1.length() || j == text2.length()) {
                return 0;
            }

            // Check memo
            if (memo[i][j] != null) {
                return memo[i][j];
            }

            int result;

            // If characters match
            if (text1.charAt(i) == text2.charAt(j)) {
                // Include both characters in LCS
                result = 1 + dp1(text1, i + 1, text2, j + 1, memo);
            } else {
                // Two options:
                // 1. Skip character in text1
                // 2. Skip character in text2
                // Take maximum of these two options
                result = Math.max(
                        dp1(text1, i + 1, text2, j, memo),
                        dp1(text1, i, text2, j + 1, memo)
                );
            }

            // Save to memo
            memo[i][j] = result;
            return result;
        }

        /**
         * Approach 2: Using position matching perspective (finding positions for matches)
         * This approach is less efficient due to nested loops
         */
        public int longestCommonSubsequence2(String text1, String text2) {
            Integer[][] memo = new Integer[text1.length()][text2.length()];
            return dp2(text1, 0, text2, 0, memo);
        }

        private int dp2(String text1, int i, String text2, int j, Integer[][] memo) {
            // Base cases
            if (i == text1.length() || j == text2.length()) {
                return 0;
            }

            // Check memo
            if (memo[i][j] != null) {
                return memo[i][j];
            }

            int result = 0;

            // Find all matching positions for text1[i] in text2[j..]
            for (int k = j; k < text2.length(); k++) {
                if (text1.charAt(i) == text2.charAt(k)) {
                    // Found a match, try to include it
                    result = Math.max(result, 1 + dp2(text1, i + 1, text2, k + 1, memo));
                }
            }

            // Also consider skipping text1[i]
            result = Math.max(result, dp2(text1, i + 1, text2, j, memo));

            // Save to memo
            memo[i][j] = result;
            return result;
        }
    }

    /**
     * Example: Coin Change
     * Demonstrates two perspectives for solving the same problem
     */
    public static class CoinChange {

        /**
         * Approach 1: Coin-perspective (which coins to use)
         * More efficient for this problem
         */
        public int coinChange1(int[] coins, int amount) {
            Integer[] memo = new Integer[amount + 1];
            int result = dp1(coins, amount, memo);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private int dp1(int[] coins, int amount, Integer[] memo) {
            // Base case
            if (amount == 0) return 0;
            if (amount < 0) return Integer.MAX_VALUE;

            // Check memo
            if (memo[amount] != null) {
                return memo[amount];
            }

            int result = Integer.MAX_VALUE;

            // Try each coin
            for (int coin : coins) {
                int subproblem = dp1(coins, amount - coin, memo);
                if (subproblem != Integer.MAX_VALUE) {
                    result = Math.min(result, subproblem + 1);
                }
            }

            // Save to memo
            memo[amount] = result;
            return result;
        }

        /**
         * Approach 2: Amount-perspective (filling the amount sequentially)
         * Less efficient due to unnecessary calculations
         */
        public int coinChange2(int[] coins, int amount) {
            Integer[][] memo = new Integer[amount + 1][coins.length + 1];
            int result = dp2(coins, amount, 0, memo);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private int dp2(int[] coins, int amount, int coinIndex, Integer[][] memo) {
            // Base cases
            if (amount == 0) return 0;
            if (coinIndex == coins.length || amount < 0) return Integer.MAX_VALUE;

            // Check memo
            if (memo[amount][coinIndex] != null) {
                return memo[amount][coinIndex];
            }

            // Two options:
            // 1. Use the current coin
            int useCoin = dp2(coins, amount - coins[coinIndex], coinIndex, memo);
            if (useCoin != Integer.MAX_VALUE) {
                useCoin = useCoin + 1;
            }

            // 2. Skip the current coin
            int skipCoin = dp2(coins, amount, coinIndex + 1, memo);

            // Take the minimum
            int result = Math.min(useCoin, skipCoin);

            // Save to memo
            memo[amount][coinIndex] = result;
            return result;
        }
    }
}