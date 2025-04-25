package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_1_prefix_sum;

/**
 * Product of Array Except Self (LeetCode 238)
 * <p>
 * Key Points:
 * 1. Uses prefix product technique (analogous to prefix sum)
 * 2. Calculates products from left-to-right and right-to-left
 * 3. For each position, the answer is the product of all elements to the left times all elements to the right
 * 4. Time complexity: O(n) - requires two passes through the array
 * 5. Space complexity: O(n) for the result array (not counting output array)
 * 6. Does not use division operation as required by the problem
 * 7. Can be further optimized to use O(1) extra space by reusing the output array
 */
public class ProductExceptSelf {
    /**
     * Calculates an array where each element is the product of all elements
     * in the input array except the element at that position
     *
     * @param nums Input array
     * @return Array of products
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // From left to right prefix product
        // prefix[i] is the product of nums[0..i]
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }

        // From right to left prefix product
        // suffix[i] is the product of nums[i..n-1]
        int[] suffix = new int[n];
        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }

        // Result array
        int[] result = new int[n];

        // Handle edge cases (first and last elements)
        result[0] = suffix[1];
        result[n - 1] = prefix[n - 2];

        // For middle elements, multiply left product with right product
        for (int i = 1; i < n - 1; i++) {
            result[i] = prefix[i - 1] * suffix[i + 1];
        }

        return result;
    }
}
