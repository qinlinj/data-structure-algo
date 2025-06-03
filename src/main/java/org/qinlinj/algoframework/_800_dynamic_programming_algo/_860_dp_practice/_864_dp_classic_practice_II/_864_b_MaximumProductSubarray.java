package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._864_dp_classic_practice_II;

/**
 * LeetCode 152. Maximum Product Subarray - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given an integer array nums, find a contiguous non-empty subarray within
 * the array that has the largest product, and return the product.
 * <p>
 * KEY CONCEPTS:
 * 1. Different from Maximum Sum Subarray (LeetCode 53) due to negative numbers
 * 2. Negative × Negative = Positive, so we need to track both max AND min products
 * 3. State Definition:
 * - dp_max[i] = maximum product ending at nums[i]
 * - dp_min[i] = minimum product ending at nums[i]
 * 4. State Transition: Consider three possibilities:
 * - Start new subarray at nums[i]
 * - Extend max subarray: dp_max[i-1] × nums[i]
 * - Extend min subarray: dp_min[i-1] × nums[i] (could become max if nums[i] < 0)
 * 5. Key insight: When nums[i] is negative, max and min swap roles
 * <p>
 * TIME COMPLEXITY: O(n)
 * SPACE COMPLEXITY: O(n) for array version, O(1) for optimized version
 * <p>
 * EXAMPLE:
 * nums = [2,3,-2,4] → Output: 6 (subarray [2,3])
 * nums = [-2,0,-1] → Output: 0 (subarray [0])
 */

public class _864_b_MaximumProductSubarray {

    public static void main(String[] args) {
        _864_b_MaximumProductSubarray solution = new _864_b_MaximumProductSubarray();

        System.out.println("=== Maximum Product Subarray Tests ===");

        // Test case 1: Positive result
        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Test Case 1:");
        System.out.print("Input: ");
        printArray(nums1);

        int result1_dp = solution.maxProduct(nums1);
        int result1_opt = solution.maxProductOptimized(nums1);
        int result1_alt = solution.maxProductAlternative(nums1);

        System.out.printf("Result (DP Array): %d\n", result1_dp);
        System.out.printf("Result (Optimized): %d\n", result1_opt);
        System.out.printf("Result (Alternative): %d\n", result1_alt);
        System.out.println("Explanation: Subarray [2,3] has maximum product 6\n");

        // Test case 2: Zero in array
        int[] nums2 = {-2, 0, -1};
        System.out.println("Test Case 2:");
        System.out.print("Input: ");
        printArray(nums2);

        int result2 = solution.maxProduct(nums2);
        System.out.printf("Result: %d\n", result2);
        System.out.println("Explanation: Subarray [0] has maximum product 0\n");

        // Test case 3: All negative numbers with even count
        int[] nums3 = {-2, -3, -1};
        System.out.println("Test Case 3:");
        System.out.print("Input: ");
        printArray(nums3);

        int result3 = solution.maxProduct(nums3);
        System.out.printf("Result: %d\n", result3);
        System.out.println("Explanation: Subarray [-2,-3] has maximum product 6\n");

        // Test case 4: Single negative number
        int[] nums4 = {-4};
        System.out.println("Test Case 4:");
        System.out.print("Input: ");
        printArray(nums4);

        int result4 = solution.maxProduct(nums4);
        System.out.printf("Result: %d\n", result4);
        System.out.println("Explanation: Only one element, so maximum product is -4\n");

        // Demonstrate DP state transitions
        System.out.println("=== DP State Transition Visualization ===");
        System.out.println("For nums = [2, 3, -2, 4]:");
        System.out.println("i=0: max=2,  min=2  (base case)");
        System.out.println("i=1: max=6,  min=3  (max: max(3, 2*3, 2*3)=6)");
        System.out.println("i=2: max=3,  min=-12 (max: max(-2, 6*(-2), 3*(-2))=max(-2,-12,-6)=-2, but we keep 3 from previous)");
        System.out.println("i=3: max=4,  min=-48 (negative makes previous min become large positive)");
        System.out.println("Global maximum: 6");
        System.out.println();

        System.out.println("=== Key Insights ===");
        System.out.println("1. Unlike Maximum Sum Subarray, we can't ignore negative products");
        System.out.println("2. A negative number can turn a large negative product into large positive");
        System.out.println("3. We must track both maximum AND minimum at each position");
        System.out.println("4. Zero effectively 'resets' the product sequence");
        System.out.println("5. State transition considers: start fresh, extend max, extend min");
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Approach 1: Standard DP with arrays
    public int maxProduct(int[] nums) {
        int n = nums.length;

        // dp_min[i] = minimum product ending at nums[i]
        int[] dpMin = new int[n];
        // dp_max[i] = maximum product ending at nums[i]
        int[] dpMax = new int[n];

        // Base case
        dpMin[0] = nums[0];
        dpMax[0] = nums[0];

        // State transition
        for (int i = 1; i < n; i++) {
            // Three candidates: start new, extend max, extend min
            int candidate1 = nums[i];                    // Start new subarray
            int candidate2 = dpMax[i - 1] * nums[i];       // Extend max subarray
            int candidate3 = dpMin[i - 1] * nums[i];       // Extend min subarray

            dpMax[i] = Math.max(candidate1, Math.max(candidate2, candidate3));
            dpMin[i] = Math.min(candidate1, Math.min(candidate2, candidate3));
        }

        // Find maximum among all ending positions
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dpMax[i]);
        }

        return result;
    }

    // Approach 2: Space-optimized version
    public int maxProductOptimized(int[] nums) {
        int n = nums.length;

        // Only need to track previous max and min
        int prevMax = nums[0];
        int prevMin = nums[0];
        int result = nums[0];

        for (int i = 1; i < n; i++) {
            // When nums[i] is negative, max and min will swap
            if (nums[i] < 0) {
                int temp = prevMax;
                prevMax = prevMin;
                prevMin = temp;
            }

            // Update max and min
            prevMax = Math.max(nums[i], prevMax * nums[i]);
            prevMin = Math.min(nums[i], prevMin * nums[i]);

            // Update global maximum
            result = Math.max(result, prevMax);
        }

        return result;
    }

    // Approach 3: Alternative clear implementation
    public int maxProductAlternative(int[] nums) {
        int n = nums.length;
        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];

        for (int i = 1; i < n; i++) {
            int num = nums[i];

            // Store current max before updating (since we need it for min calculation)
            int tempMax = currentMax;

            // Calculate new max and min
            currentMax = Math.max(num, Math.max(tempMax * num, currentMin * num));
            currentMin = Math.min(num, Math.min(tempMax * num, currentMin * num));

            // Update global maximum
            maxProduct = Math.max(maxProduct, currentMax);
        }

        return maxProduct;
    }

    // Helper methods for cleaner code
    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}