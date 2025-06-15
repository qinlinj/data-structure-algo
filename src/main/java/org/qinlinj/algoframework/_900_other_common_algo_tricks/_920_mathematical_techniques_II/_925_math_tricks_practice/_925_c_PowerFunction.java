package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._925_math_tricks_practice;

public class _925_c_PowerFunction {
    /**
     * Calculates x raised to the power of k using fast exponentiation
     *
     * @param a base number
     * @param k exponent
     * @return a^k as a double
     */
    public double myPow(double a, int k) {
        if (k == 0) return 1;

        if (k == Integer.MIN_VALUE) {
            // Handle Integer.MIN_VALUE separately to avoid overflow when negating
            // Integer.MIN_VALUE = -2^31, and -(-2^31) would overflow int range
            return myPow(1 / a, -(k + 1)) / a;
        }

        if (k < 0) {
            return myPow(1 / a, -k);
        }

        if (k % 2 == 1) {
            // k is odd: a^k = a * a^(k-1)
            return a * myPow(a, k - 1);
        } else {
            // k is even: a^k = (a^(k/2))^2
            double sub = myPow(a, k / 2);
            return sub * sub;
        }
    }

}
