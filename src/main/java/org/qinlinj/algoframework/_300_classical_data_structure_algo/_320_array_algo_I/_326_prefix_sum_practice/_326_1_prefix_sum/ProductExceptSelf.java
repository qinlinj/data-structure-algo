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
     * Example usage
     */
    public static void main(String[] args) {
        ProductExceptSelf solution = new ProductExceptSelf();

        // Example 1: [1, 2, 3, 4]
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = solution.productExceptSelf(nums1);
        System.out.println("Example 1:");
        printArray(result1);  // Expected: [24, 12, 8, 6]

        int[] result1Optimized = solution.productExceptSelfOptimized(nums1);
        System.out.println("Example 1 (optimized):");
        printArray(result1Optimized);  // Expected: [24, 12, 8, 6]

        // Example 2: [-1, 1, 0, -3, 3]
        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = solution.productExceptSelf(nums2);
        System.out.println("Example 2:");
        printArray(result2);  // Expected: [0, 0, 9, 0, 0]

        int[] result2Optimized = solution.productExceptSelfOptimized(nums2);
        System.out.println("Example 2 (optimized):");
        printArray(result2Optimized);  // Expected: [0, 0, 9, 0, 0]
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

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

    /**
     * Optimized solution with O(1) extra space (not counting output array)
     * Uses the output array to store the left products, then computes right products on-the-fly
     *
     * @param nums Input array
     * @return Array of products
     */
    public int[] productExceptSelfOptimized(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Calculate left products and store in result array
        result[0] = 1; // There's no element to the left of first element
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Calculate right products on-the-fly and multiply with left products
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }
}
