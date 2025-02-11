package org.qinlinj.linear.algo.recusion;

public class _70_ClimbingStairs {
    class Solution1 {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    class Solution2 {
        public int climbStairs(int n) {
            if (n <= 2) return n;
            int prev1 = 1; // f(n-2)
            int prev2 = 2; // f(n-1)
            int current = 0;

            for (int i = 3; i <= n; i++) {
                current = prev1 + prev2;
                prev1 = prev2;
                prev2 = current;
            }
            return current;
        }
    }
}
