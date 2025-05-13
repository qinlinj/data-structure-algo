package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._734_set_partition;

/**
 * Partition to K Equal Sum Subsets - Buckets Perspective
 * <p>
 * This class implements the second approach to solving the partition to k equal
 * sum subsets problem: viewing the problem from the buckets' perspective.
 * <p>
 * In this approach:
 * - We focus on filling one bucket at a time to its target sum
 * - For each bucket, we decide which elements to include
 * - Once a bucket is filled, we move to the next bucket
 * <p>
 * Time Complexity: O(k * 2^n) where n is the number of elements
 * For each of the k buckets, we make a binary choice (include/exclude) for each element
 * <p>
 * This approach is generally more efficient than the elements perspective because:
 * - It's better to make "few choices many times" than "many choices few times"
 * - Making n "binary choices" k times: O(k * 2^n) is usually better than
 * making n "k-way choices" once: O(k^n)
 */

import java.util.*;

public class _734_c_BucketsPerspective {

    /**
     * Main method to run the solution
     */
    public static void main(String[] args) {
        _734_c_BucketsPerspective solution = new _734_c_BucketsPerspective();

        System.out.println("Partition to K Equal Sum Subsets - Buckets Perspective");
        System.out.println("=======================================================");
        System.out.println("This approach fills buckets one by one, deciding which elements to include in each bucket.\n");

        solution.runExamples();
        solution.explainOptimizations();

        System.out.println("Time Complexity Analysis:");
        System.out.println("- Basic implementation: O(k * 2^n)");
        System.out.println("  For each of k buckets, we make a binary choice for each of n elements");
        System.out.println("- Optimized implementation: Still O(k * 2^n) worst case, but with significant");
        System.out.println("  practical improvements due to memoization");
        System.out.println("\nWhy this approach is usually better than the elements perspective (O(k^n)):");
        System.out.println("- It's better to make 'few choices many times' than 'many choices few times'");
        System.out.println("- For most values of k and n, k * 2^n < k^n");
    }

    /**
     * Determines if the array can be partitioned into k subsets with equal sum
     * using the buckets' perspective approach
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // Basic validation
        if (k > nums.length) return false;

        // Calculate the sum of all elements
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If the sum cannot be divided evenly by k, return false
        if (sum % k != 0) return false;

        // Calculate the target sum for each subset
        int target = sum / k;

        // Create a boolean array to track which elements are used
        boolean[] used = new boolean[nums.length];

        // Start the backtracking process with the first bucket
        return backtrack(k, 0, nums, 0, used, target);
    }

    /**
     * Backtracking method to fill buckets one by one
     *
     * @param k Number of buckets remaining to be filled
     * @param currentSum Sum of elements in the current bucket so far
     * @param nums Array of numbers
     * @param start Index in nums to start considering elements from
     * @param used Boolean array to track which elements are already used
     * @param target Target sum for each bucket
     * @return true if a valid partition exists
     */
    private boolean backtrack(int k, int currentSum, int[] nums, int start, boolean[] used, int target) {
        // Base case: all buckets are filled
        if (k == 0) {
            return true;
        }

        // Current bucket is filled, move to the next bucket
        if (currentSum == target) {
            // Start from the beginning of nums for the next bucket
            return backtrack(k - 1, 0, nums, 0, used, target);
        }

        // Try to add nums[i] to the current bucket
        for (int i = start; i < nums.length; i++) {
            // Skip if the element is already used or would overflow the bucket
            if (used[i] || currentSum + nums[i] > target) {
                continue;
            }

            // Use the current element
            used[i] = true;
            currentSum += nums[i];

            // Recursively try to fill the rest of the current bucket
            if (backtrack(k, currentSum, nums, i + 1, used, target)) {
                return true;
            }

            // Backtrack: remove the element from the current bucket
            used[i] = false;
            currentSum -= nums[i];
        }

        // Cannot fill the current bucket with the available elements
        return false;
    }

    /**
     * Optimized version with memoization to avoid redundant calculations
     */
    public boolean canPartitionKSubsetsOptimized(int[] nums, int k) {
        // Basic validation
        if (k > nums.length) return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) return false;

        int target = sum / k;

        // Using bit manipulation to represent the 'used' state
        // Instead of boolean[] used, we use an integer where each bit represents
        // whether an element is used or not
        return backtrackWithMemo(k, 0, nums, 0, 0, target, new HashMap<>());
    }

    /**
     * Optimized backtracking with memoization and bit manipulation
     *
     * @param k Number of buckets remaining to be filled
     * @param currentSum Sum of elements in the current bucket so far
     * @param nums Array of numbers
     * @param start Index in nums to start considering elements from
     * @param used Bit representation of used elements
     * @param target Target sum for each bucket
     * @param memo Memoization map to avoid redundant calculations
     * @return true if a valid partition exists
     */
    private boolean backtrackWithMemo(int k, int currentSum, int[] nums, int start,
                                      int used, int target, HashMap<Integer, Boolean> memo) {
        // Base case: all buckets are filled
        if (k == 0) {
            return true;
        }

        // Current bucket is filled, move to the next bucket
        if (currentSum == target) {
            return backtrackWithMemo(k - 1, 0, nums, 0, used, target, memo);
        }

        // Check memoization
        if (memo.containsKey(used)) {
            return memo.get(used);
        }

        // Try to add nums[i] to the current bucket
        for (int i = start; i < nums.length; i++) {
            // Skip if the element is already used
            if (((used >> i) & 1) == 1) {
                continue;
            }

            // Skip if adding this element would overflow the bucket
            if (currentSum + nums[i] > target) {
                continue;
            }

            // Mark element as used using bit manipulation
            used |= (1 << i);
            currentSum += nums[i];

            // Recursively try to fill the rest of the current bucket
            if (backtrackWithMemo(k, currentSum, nums, i + 1, used, target, memo)) {
                return true;
            }

            // Backtrack: unmark the element
            used ^= (1 << i);  // XOR to flip the bit back to 0
            currentSum -= nums[i];
        }

        // Cache the result for this state
        memo.put(used, false);
        return false;
    }

    /**
     * Helper method to run and compare the different implementations
     */
    public void runExamples() {
        int[][] examples = {
                {4, 3, 2, 3, 5, 2, 1},  // Can be partitioned into 4 subsets
                {1, 2, 3, 4},           // Cannot be partitioned into 3 subsets
                {2, 2, 2, 2, 3, 4, 5},  // Can be partitioned into 4 subsets
                {1, 1, 1, 1, 2, 2, 2, 2} // Can be partitioned into 4 subsets
        };

        int[] kValues = {4, 3, 4, 4};

        System.out.println("Basic Implementation vs Optimized Implementation:\n");

        for (int i = 0; i < examples.length; i++) {
            int[] nums = examples[i];
            int k = kValues[i];

            System.out.println("Example " + (i + 1) + ": nums = " + Arrays.toString(nums) + ", k = " + k);

            // Time the basic implementation
            long startTime = System.nanoTime();
            boolean resultBasic = canPartitionKSubsets(nums, k);
            long endTime = System.nanoTime();
            long durationBasic = (endTime - startTime) / 1_000_000;  // Convert to milliseconds

            System.out.println("Basic implementation result: " + resultBasic);
            System.out.println("Basic implementation time: " + durationBasic + " ms");

            // Time the optimized implementation
            startTime = System.nanoTime();
            boolean resultOptimized = canPartitionKSubsetsOptimized(nums, k);
            endTime = System.nanoTime();
            long durationOptimized = (endTime - startTime) / 1_000_000;  // Convert to milliseconds

            System.out.println("Optimized implementation result: " + resultOptimized);
            System.out.println("Optimized implementation time: " + durationOptimized + " ms");

            System.out.println("Speed improvement: " +
                    String.format("%.2f", (double) durationBasic / durationOptimized) + "x\n");
        }
    }

    /**
     * Helper method to explain the optimization techniques
     */
    public void explainOptimizations() {
        System.out.println("Optimization Techniques:\n");

        System.out.println("1. Memoization");
        System.out.println("   - Used state (which elements are used) determines future possibilities");
        System.out.println("   - Cache results for each 'used' state to avoid redundant calculations");
        System.out.println("   - This significantly reduces time complexity for inputs with many duplicate values\n");

        System.out.println("2. Bit Manipulation");
        System.out.println("   - Use an integer to represent the 'used' state instead of a boolean array");
        System.out.println("   - Each bit in the integer represents whether an element is used (1) or not (0)");
        System.out.println("   - This makes the code more efficient and enables direct memoization");
        System.out.println("   - Limit: This works when nums.length <= 32 (size of int in Java)\n");

        System.out.println("3. Start Index Optimization");
        System.out.println("   - For each new bucket, start from the beginning of the array");
        System.out.println("   - For the same bucket, continue from where we left off");
        System.out.println("   - This prevents considering the same combinations multiple times\n");
    }
}