package org.qinlinj.algoframework._700_brute_force_search_algo._730_dfs_backtracking_problems._734_set_partition; /**
 * Partition to K Equal Sum Subsets - Comparison and Analysis
 * <p>
 * This class compares and analyzes the different approaches to solving the partition
 * to k equal sum subsets problem. It also explores additional optimizations and
 * provides insights into the complexity analysis.
 * <p>
 * The two main approaches discussed are:
 * 1. Elements perspective: deciding which bucket each element goes into
 * 2. Buckets perspective: deciding which elements to include in each bucket
 * <p>
 * This class also presents visualizations and detailed explanations to help
 * understand the backtracking process and why certain optimizations work.
 */

import java.util.*;

public class _734_d_ComparisonAndAnalysis {

    /**
     * Main method to run the analysis
     */
    public static void main(String[] args) {
        _734_d_ComparisonAndAnalysis analysis = new _734_d_ComparisonAndAnalysis();

        System.out.println("Partition to K Equal Sum Subsets - Comparative Analysis");
        System.out.println("=======================================================");

        analysis.compareImplementations();
        analysis.visualizeDecisionTree();
        analysis.analyzeComplexity();
        analysis.summarizeKeyTakeaways();

        System.out.println("\nConclusion:");
        System.out.println("The partition to k equal sum subsets problem demonstrates how different");
        System.out.println("backtracking perspectives can lead to vastly different performance characteristics.");
        System.out.println("While both approaches shown here use backtracking, the 'buckets perspective'");
        System.out.println("with memoization proves significantly more efficient in practice, especially");
        System.out.println("for larger inputs. This highlights the importance of choosing the right");
        System.out.println("perspective when approaching combinatorial problems.");
    }

    /**
     * Method to compare the performance of all implementations
     */
    public void compareImplementations() {
        // Define test cases
        int[][] testCases = {
                {4, 3, 2, 3, 5, 2, 1},       // Example 1: Can be partitioned, k=4
                {1, 2, 3, 4},                // Example 2: Cannot be partitioned, k=3
                {2, 2, 2, 2, 3, 4, 5},       // Example 3: More challenging
                {9, 4, 10, 9, 3, 7, 3, 3, 7} // Example 4: Larger case
        };

        int[] kValues = {4, 3, 4, 3};

        System.out.println("Performance Comparison of Different Implementations:\n");
        System.out.println("------------------------------------------------");
        System.out.println("| Test Case | Elements Perspective | Buckets Perspective | Optimized Buckets |");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < testCases.length; i++) {
            int[] nums = testCases[i];
            int k = kValues[i];

            System.out.print("| Case " + (i + 1) + "    | ");

            // Elements perspective (with sort optimization)
            long startTime = System.nanoTime();
            boolean result1 = elementsApproach(nums.clone(), k);
            long endTime = System.nanoTime();
            long duration1 = (endTime - startTime) / 1_000_000;  // ms
            System.out.print(duration1 + "ms (" + result1 + ") | ");

            // Buckets perspective (basic)
            startTime = System.nanoTime();
            boolean result2 = bucketsApproach(nums.clone(), k);
            endTime = System.nanoTime();
            long duration2 = (endTime - startTime) / 1_000_000;  // ms
            System.out.print(duration2 + "ms (" + result2 + ") | ");

            // Buckets perspective (optimized with memoization)
            startTime = System.nanoTime();
            boolean result3 = bucketsApproachOptimized(nums.clone(), k);
            endTime = System.nanoTime();
            long duration3 = (endTime - startTime) / 1_000_000;  // ms
            System.out.println(duration3 + "ms (" + result3 + ") |");
        }
        System.out.println("------------------------------------------------");
    }

    /**
     * Elements perspective implementation (deciding which bucket each element goes into)
     */
    private boolean elementsApproach(int[] nums, int k) {
        // Basic validation
        if (k > nums.length) return false;

        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int target = sum / k;
        int[] buckets = new int[k];

        // Optimization: Sort in descending order
        Arrays.sort(nums);
        reverseArray(nums);

        return backtrackElements(nums, 0, buckets, target);
    }

    private boolean backtrackElements(int[] nums, int index, int[] buckets, int target) {
        if (index == nums.length) {
            // Check if all buckets have the target sum
            for (int bucket : buckets) {
                if (bucket != target) return false;
            }
            return true;
        }

        // Try to place the current element in each bucket
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + nums[index] > target) continue;

            buckets[i] += nums[index];
            if (backtrackElements(nums, index + 1, buckets, target)) {
                return true;
            }
            buckets[i] -= nums[index];

            // Optimization: If this bucket is empty (after backtracking),
            // then all other empty buckets will also fail
            if (buckets[i] == 0) break;
        }

        return false;
    }

    /**
     * Buckets perspective implementation (deciding which elements to include in each bucket)
     */
    private boolean bucketsApproach(int[] nums, int k) {
        // Basic validation
        if (k > nums.length) return false;

        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int target = sum / k;
        boolean[] used = new boolean[nums.length];

        return backtrackBuckets(k, 0, nums, 0, used, target);
    }

    private boolean backtrackBuckets(int k, int currentSum, int[] nums, int start,
                                     boolean[] used, int target) {
        // Base case: all buckets are filled
        if (k == 0) return true;

        // Current bucket is filled, move to the next bucket
        if (currentSum == target) {
            return backtrackBuckets(k - 1, 0, nums, 0, used, target);
        }

        // Try to add nums[i] to the current bucket
        for (int i = start; i < nums.length; i++) {
            if (used[i] || currentSum + nums[i] > target) continue;

            used[i] = true;
            if (backtrackBuckets(k, currentSum + nums[i], nums, i + 1, used, target)) {
                return true;
            }
            used[i] = false;
        }

        return false;
    }

    /**
     * Optimized buckets perspective with memoization and bit manipulation
     */
    private boolean bucketsApproachOptimized(int[] nums, int k) {
        // Basic validation
        if (k > nums.length) return false;

        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int target = sum / k;

        // Using bit manipulation for the used state
        return backtrackOptimized(k, 0, nums, 0, 0, target, new HashMap<>());
    }

    private boolean backtrackOptimized(int k, int currentSum, int[] nums, int start,
                                       int used, int target, HashMap<Integer, Boolean> memo) {
        // Base case: all buckets are filled
        if (k == 0) return true;

        // Current bucket is filled, move to the next bucket
        if (currentSum == target) {
            return backtrackOptimized(k - 1, 0, nums, 0, used, target, memo);
        }

        // Check memoization
        if (memo.containsKey(used)) {
            return memo.get(used);
        }

        // Try to add nums[i] to the current bucket
        for (int i = start; i < nums.length; i++) {
            // Skip if element is already used or would overflow bucket
            if (((used >> i) & 1) == 1 || currentSum + nums[i] > target) {
                continue;
            }

            // Mark element as used (set bit to 1)
            used |= (1 << i);

            if (backtrackOptimized(k, currentSum + nums[i], nums, i + 1, used, target, memo)) {
                return true;
            }

            // Backtrack: unmark element (set bit back to 0)
            used ^= (1 << i);
        }

        // Cache result for this used state
        memo.put(used, false);
        return false;
    }

    /**
     * Helper method to reverse an array
     */
    private void reverseArray(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Visualize the decision tree for a simple example
     */
    public void visualizeDecisionTree() {
        int[] nums = {1, 2, 3};
        int k = 2;
        int target = 3;  // Sum is 6, target for each bucket is 3

        System.out.println("\nVisualizing Decision Trees for nums = [1, 2, 3], k = 2, target = 3\n");

        System.out.println("Elements Perspective Decision Tree (partial):");
        System.out.println("Root");
        System.out.println("├── Element 1 (value=1)");
        System.out.println("│   ├── Place in Bucket 1: [1, 0]");
        System.out.println("│   │   ├── Element 2 (value=2)");
        System.out.println("│   │   │   ├── Place in Bucket 1: [3, 0] ✓");
        System.out.println("│   │   │   │   └── Element 3 (value=3)");
        System.out.println("│   │   │   │       └── Place in Bucket 2: [3, 3] ✓ SOLUTION FOUND!");
        System.out.println("│   │   │   └── Place in Bucket 2: [1, 2]");
        System.out.println("│   │   │       └── ...");
        System.out.println("│   │   └── ...");
        System.out.println("│   └── Place in Bucket 2: [0, 1]");
        System.out.println("│       └── ...");
        System.out.println("└── ...");

        System.out.println("\nBuckets Perspective Decision Tree (partial):");
        System.out.println("Root (Filling Bucket 1)");
        System.out.println("├── Include Element 1: [1], sum=1");
        System.out.println("│   ├── Include Element 2: [1, 2], sum=3 ✓");
        System.out.println("│   │   └── Bucket 1 full, move to Bucket 2");
        System.out.println("│   │       └── Include Element 3: [3], sum=3 ✓ SOLUTION FOUND!");
        System.out.println("│   └── Include Element 3: [1, 3], sum=4 ✗ (exceeds target)");
        System.out.println("├── Skip Element 1");
        System.out.println("│   ├── Include Element 2: [2], sum=2");
        System.out.println("│   │   └── Include Element 3: [2, 3], sum=5 ✗ (exceeds target)");
        System.out.println("│   └── Skip Element 2");
        System.out.println("│       └── Include Element 3: [3], sum=3 ✓");
        System.out.println("│           └── Bucket 1 full, move to Bucket 2");
        System.out.println("│               └── Empty elements remain ✗ (cannot form valid partition)");
        System.out.println("└── ...");
    }

    /**
     * Analyze the time and space complexity of different approaches
     */
    public void analyzeComplexity() {
        System.out.println("\nTime and Space Complexity Analysis:\n");

        System.out.println("1. Elements Perspective Approach:");
        System.out.println("   Time Complexity: O(k^n)");
        System.out.println("   - For each of the n elements, we have k choices (buckets)");
        System.out.println("   - This leads to k^n possible combinations in the worst case");
        System.out.println("   Space Complexity: O(n + k)");
        System.out.println("   - O(n) for the recursion stack");
        System.out.println("   - O(k) for the buckets array\n");

        System.out.println("2. Buckets Perspective Approach:");
        System.out.println("   Time Complexity: O(k * 2^n)");
        System.out.println("   - For each of the k buckets, we make 2^n decisions (include/exclude each element)");
        System.out.println("   Space Complexity: O(n)");
        System.out.println("   - O(n) for the recursion stack and the used array\n");

        System.out.println("3. Optimized Buckets Approach:");
        System.out.println("   Time Complexity: O(k * 2^n)");
        System.out.println("   - Still O(k * 2^n) in the worst case");
        System.out.println("   - But significantly faster in practice due to memoization");
        System.out.println("   Space Complexity: O(n + 2^n)");
        System.out.println("   - O(n) for the recursion stack");
        System.out.println("   - O(2^n) for the memoization table (at most 2^n different used states)\n");

        System.out.println("Why is the Buckets Perspective usually faster?");
        System.out.println("For most values of k and n where k > 2, k^n > k * 2^n");
        System.out.println("Example: With n=10 and k=4");
        System.out.println("- Elements perspective: 4^10 ≈ 1 million combinations");
        System.out.println("- Buckets perspective: 4 * 2^10 ≈ 4 thousand combinations");
        System.out.println("This illustrates the principle of 'fewer choices many times' vs 'many choices fewer times'\n");
    }

    /**
     * Summarize the key takeaways from the different approaches
     */
    public void summarizeKeyTakeaways() {
        System.out.println("\nKey Takeaways from Partition to K Equal Sum Subsets Problem:\n");

        System.out.println("1. Problem Analysis:");
        System.out.println("   - This is a classic NP-hard problem (subset sum / bin packing variant)");
        System.out.println("   - No polynomial time solution is known for the general case");
        System.out.println("   - Backtracking with pruning is the best general approach\n");

        System.out.println("2. Approach Selection:");
        System.out.println("   - The perspective you choose significantly impacts performance");
        System.out.println("   - 'Buckets perspective' is generally more efficient than 'elements perspective'");
        System.out.println("   - This demonstrates the principle: prefer 'few choices many times' over");
        System.out.println("     'many choices few times' in combinatorial problems\n");

        System.out.println("3. Optimization Techniques:");
        System.out.println("   - Sorting (for elements perspective)");
        System.out.println("   - Memoization (for buckets perspective)");
        System.out.println("   - Bit manipulation for state representation");
        System.out.println("   - Early pruning conditions to avoid unnecessary exploration\n");

        System.out.println("4. Backtracking Framework Mastery:");
        System.out.println("   - Make choices before recursion");
        System.out.println("   - Undo choices after recursion");
        System.out.println("   - Add pruning conditions to eliminate invalid paths early");
        System.out.println("   - Use memoization to avoid redundant calculations\n");
    }
}