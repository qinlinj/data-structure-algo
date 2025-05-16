package org.qinlinj.algoframework._800_dynamic_programming_algo._810_dp_basic_techniques._812_longest_increasing_subsequence;

/**
 * Longest Increasing Subsequence - Binary Search Approach
 * ======================================================
 * <p>
 * This class demonstrates the O(n log n) solution to the Longest Increasing Subsequence (LIS)
 * problem using binary search. While less intuitive than the dynamic programming approach,
 * this method is significantly more efficient for large inputs.
 * <p>
 * Key Concepts:
 * <p>
 * 1. Patience Game Analogy:
 * - The algorithm can be visualized as playing a card game where cards are placed in piles
 * - Each card (number) can only be placed on a card with a higher value
 * - If no suitable pile exists, start a new pile
 * - If multiple piles are valid, choose the leftmost one
 * - The number of piles at the end equals the length of the LIS
 * <p>
 * 2. Binary Search Application:
 * - We maintain an array 'top' where top[i] = smallest element that can end an increasing subsequence of length i+1
 * - For each element, we use binary search to find the correct pile (position in 'top')
 * - This allows us to place each element optimally in O(log n) time
 * <p>
 * 3. Time Complexity: O(n log n)
 * - We process each of the n elements once
 * - For each element, binary search takes O(log n) time
 * <p>
 * 4. Space Complexity: O(n) for the 'top' array
 * <p>
 * This approach is particularly valuable for large datasets where the O(n²) dynamic programming
 * solution would be too slow.
 */
public class _812_b_LongestIncreasingSubsequenceBinarySearch {

    public static void main(String[] args) {
        _812_b_LongestIncreasingSubsequenceBinarySearch solution =
                new _812_b_LongestIncreasingSubsequenceBinarySearch();

        // Example 1 from LeetCode: [10,9,2,5,3,7,101,18]
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Example 1: " + solution.lengthOfLIS(nums1)); // Expected: 4

        // Example 2 from LeetCode: [0,1,0,3,2,3]
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Example 2: " + solution.lengthOfLIS(nums2)); // Expected: 4

        // Example 3 from LeetCode: [7,7,7,7,7,7,7]
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Example 3: " + solution.lengthOfLIS(nums3)); // Expected: 1

        // Demonstrative example with step-by-step explanation
        System.out.println("\n--- Step-by-Step Demonstration (Patience Sort) ---");
        int[] demonstrativeExample = {2, 6, 3, 4, 1, 2, 9, 5, 8};
        solution.demonstratePatienceSort(demonstrativeExample);
    }

    /**
     * Find the length of the longest increasing subsequence in the given array
     * using the binary search approach.
     *
     * @param nums array of integers
     * @return length of the longest increasing subsequence
     */
    public int lengthOfLIS(int[] nums) {
        // Edge case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 'top' array stores the smallest value that can end a subsequence of length i+1
        int[] top = new int[nums.length];
        // piles represents the number of piles (or the length of LIS found so far)
        int piles = 0;

        // Process each card (number)
        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];

            // Use binary search to find the pile to place the current card
            // We're searching for the leftmost pile that has a top card > current card
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (top[mid] >= poker) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // If no suitable pile found, create a new pile
            if (left == piles) {
                piles++;
            }

            // Place the card on the chosen pile
            top[left] = poker;
        }

        // The number of piles is equal to the length of the LIS
        return piles;
    }

    /**
     * Demonstrates the execution of the patience sort-based LIS algorithm
     * with step-by-step visualization of the piles.
     */
    public void demonstratePatienceSort(int[] nums) {
        System.out.println("Finding LIS using patience sort for array: " + java.util.Arrays.toString(nums));

        int[] top = new int[nums.length];
        int piles = 0;

        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];
            System.out.println("\nProcessing card: " + poker);

            // Binary search to find the correct pile
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (top[mid] >= poker) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // Print current piles before placing the card
            System.out.print("Current piles before placing: ");
            for (int j = 0; j < piles; j++) {
                System.out.print(top[j] + " ");
            }
            System.out.println();

            // Place the card
            String action;
            if (left == piles) {
                action = "Creating new pile " + piles;
                piles++;
            } else {
                action = "Placing on existing pile " + left + " (replacing " + top[left] + ")";
            }
            top[left] = poker;

            System.out.println(action);

            // Print current piles after placing the card
            System.out.print("Current piles after placing: ");
            for (int j = 0; j < piles; j++) {
                System.out.print(top[j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nFinal number of piles (LIS length): " + piles);
    }
}
