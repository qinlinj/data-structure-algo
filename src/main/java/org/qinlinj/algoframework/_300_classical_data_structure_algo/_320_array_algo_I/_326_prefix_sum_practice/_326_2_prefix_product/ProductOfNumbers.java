package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._326_prefix_sum_practice._326_2_prefix_product;

/**
 * Product of the Last K Numbers (LeetCode 1352)
 * <p>
 * Key Points:
 * 1. Uses prefix product technique to efficiently calculate product of last k numbers
 * 2. Maintains a running prefix product list to avoid recalculation
 * 3. Special handling for zeros in the input stream
 * 4. Time complexity: O(1) for both add and getProduct operations
 * 5. Space complexity: O(n) where n is the number of elements added
 * 6. Demonstrates how prefix products can be used for efficient range product queries
 * 7. Similar to prefix sum but uses multiplication operation instead of addition
 */
public class ProductOfNumbers {
    // Prefix product list
    // preProduct.get(i) represents the product of all elements from 0 to i
    private java.util.ArrayList<Integer> preProduct;

    /**
     * Initialize the data structure
     */
    public ProductOfNumbers() {
        preProduct = new java.util.ArrayList<>();
        // Start with 1 to simplify calculation of products
        preProduct.add(1);
    }

    /**
     * Adds a number to the data structure
     *
     * @param num The number to add
     */
    public void add(int num) {
        if (num == 0) {
            // If we add 0, all previous products become useless for future calculations
            // because any product involving 0 will be 0
            preProduct.clear();
            preProduct.add(1);
            return;
        }

        int n = preProduct.size();
        // Add new prefix product (previous product * new number)
        preProduct.add(preProduct.get(n - 1) * num);
    }

    /**
     * Returns the product of the last k numbers
     *
     * @param k Number of elements to consider
     * @return Product of the last k numbers
     */
    public int getProduct(int k) {
        int n = preProduct.size();

        // If k is greater than the number of elements we have (not counting the initial 1),
        // it means there was a 0 in the sequence that cleared our prefix products
        if (k > n - 1) {
            return 0;
        }

        // Calculate the product of last k numbers using prefix products
        // preProduct[n-1] / preProduct[n-k-1] gives the product of elements [n-k, n-1]
        return preProduct.get(n - 1) / preProduct.get(n - k - 1);
    }
}
