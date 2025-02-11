package org.qinlinj.linear.algo.recusion;

public class _509_FibonacciNumber {
    class Solution {
        public int fib(int n) {
            if (n <= 1) {
                return n;
            }
            return fib(n - 1) + fib(n - 2);
        }
    }
}
