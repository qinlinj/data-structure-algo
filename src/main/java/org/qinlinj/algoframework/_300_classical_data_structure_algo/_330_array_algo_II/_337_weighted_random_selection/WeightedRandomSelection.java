package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._337_weighted_random_selection;

/**
 * Weighted Random Selection Algorithm
 * <p>
 * Key Concepts:
 * 1. Using prefix sum array to represent cumulative weights
 * 2. Generating a random number within the total weight range
 * 3. Using binary search to find the corresponding index
 * <p>
 * This class implements LeetCode 528: Random Pick with Weight
 * Problem: Given an array of weights, implement pickIndex() to randomly select
 * an index with probability proportional to its weight.
 * <p>
 * Approach:
 * 1. Build a prefix sum array from the weights
 * 2. Generate a random number between 1 and the total weight (inclusive)
 * 3. Use binary search to find the smallest index in the prefix sum array that is
 * greater than or equal to the random number
 * 4. Return that index (minus 1 to account for prefix sum offset)
 */
public class WeightedRandomSelection {

    // Prefix sum array to store cumulative weights
    private int[] prefixSum;
    private java.util.Random random;

    /**
     * Constructor to initialize the prefix sum array
     * Time Complexity: O(n) where n is the length of weights array
     * Space Complexity: O(n) for storing the prefix sum array
     *
     * @param w Array of weights
     */
    public WeightedRandomSelection(int[] w) {
        if (w == null || w.length == 0) {
            throw new IllegalArgumentException("Weights array cannot be empty");
        }

        // Initialize random number generator
        random = new java.util.Random();

        // Create prefix sum array with an offset of 1 (prefixSum[0] = 0)
        int n = w.length;
        prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        // Build the prefix sum array
        // prefixSum[i] = sum of weights from 0 to i-1
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i - 1];
        }
    }


    public static void main(String[] args) {
        // Example 1: Single weight
        int[] weights1 = {1};
        WeightedRandomSelection solution1 = new WeightedRandomSelection(weights1);
        System.out.println("Example 1 (weights=[1]): " + solution1.pickIndex()); // Always returns 0

        // Example 2: Two weights with different probabilities
        int[] weights2 = {1, 3};
        WeightedRandomSelection solution2 = new WeightedRandomSelection(weights2);

        // Test the distribution by sampling
        int samples = 10000;
        int[] counts = new int[weights2.length];

        for (int i = 0; i < samples; i++) {
            counts[solution2.pickIndex()]++;
        }

        System.out.println("\nExample 2 (weights=[1, 3]) distribution after " + samples + " samples:");
        for (int i = 0; i < counts.length; i++) {
            double percentage = (double) counts[i] / samples * 100;
            System.out.printf("Index %d: %d times (%.2f%%)\n", i, counts[i], percentage);
        }

        // Example 3: More complex weight distribution
        int[] weights3 = {1, 3, 2, 1};
        WeightedRandomSelection solution3 = new WeightedRandomSelection(weights3);

        System.out.println("\nExample 3 (weights=[1, 3, 2, 1]) - Some random picks:");
        for (int i = 0; i < 10; i++) {
            System.out.print(solution3.pickIndex() + " ");
        }
        /**
         * Visual explanation:
         *
         * For example, with weights [1, 3, 2, 1]:
         * prefixSum = [0, 1, 4, 6, 7]
         *
         * We visualize this as a line segment:
         * |--1--|------3------|----2----|--1--|
         *  w[0]     w[1]         w[2]    w[3]
         *
         * Indices:
         *          0     1     2     3     4     5     6     7
         *          |     |     |     |     |     |     |     |
         *          +-----+-----+-----+-----+-----+-----+-----+
         *          |  0  |  1  |     2     |     3     |  4  |
         *          +-----+-----+-----+-----+-----+-----+-----+
         * prefixSum: [0,    1,          4,          6,    7]
         *
         * If we generate target = 5:
         * - Binary search finds index 3 in prefixSum
         * - We return 3-1 = 2 as the result (index of original array)
         */
    }

    /**
     * Picks an index randomly with probability proportional to its weight
     * Time Complexity: O(log n) due to binary search
     * Space Complexity: O(1)
     *
     * @return Randomly selected index
     */
    public int pickIndex() {
        // Get total weight
        int totalWeight = prefixSum[prefixSum.length - 1];

        // Generate random number in range [1, totalWeight]
        // Using nextInt(n) which generates in range [0, n-1] and adding 1
        int target = random.nextInt(totalWeight) + 1;

        // Binary search to find the leftmost position where prefixSum[pos] >= target
        int index = leftBound(prefixSum, target);

        // Subtract 1 to account for the prefix sum array offset
        return index - 1;
    }

    /**
     * Binary search to find the leftmost element greater than or equal to target
     *
     * @param nums   Sorted array
     * @param target Target value
     * @return Index of the leftmost element >= target
     */
    private int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                // Found target, but continue searching left to find leftmost occurrence
                right = mid;
            } else if (nums[mid] < target) {
                // Target is to the right
                left = mid + 1;
            } else {
                // Target is to the left
                right = mid;
            }
        }

        return left;
    }
}