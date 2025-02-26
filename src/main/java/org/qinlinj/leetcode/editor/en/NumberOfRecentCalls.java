package org.qinlinj.leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

// [933] Number of Recent Calls
public class NumberOfRecentCalls {
//    public static void main(String[] args) {
//        Solution solution = new NumberOfRecentCalls().new Solution();
//    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            this.queue = new LinkedList<>();
        }

        public int ping(int t) {
            // Add the current timestamp
            queue.offer(t);

            // Remove all timestamps that are outside the 3000ms window
            while (!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }

            // Return the size of the queue, which represents the count of
            // requests in the last 3000ms
            return queue.size();
        }
    }

    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */
//leetcode submit region end(Prohibit modification and deletion)
    class solution0 {
        class RecentCounter {
            Queue<Integer> queue;
            int t;

            public RecentCounter() {
                this.queue = new LinkedList<>();
                this.t = 0;
            }

            public int ping(int t) {
                if (queue.isEmpty()) {
                    queue.offer(t);
                    this.t++;
                    return this.t;
                }
                while (!queue.isEmpty() && (t - queue.peek()) > 3000) {
                    queue.poll();
                    this.t--;
                }
                queue.offer(t);
                this.t++;
                return this.t;
            }
        }
    }
}


//You have a RecentCounter class which counts the number of recent requests
//within a certain time frame. 
//
// Implement the RecentCounter class: 
//
// 
// RecentCounter() Initializes the counter with zero recent requests. 
// int ping(int t) Adds a new request at time t, where t represents some time 
//in milliseconds, and returns the number of requests that has happened in the past 
//3000 milliseconds (including the new request). Specifically, return the number 
//of requests that have happened in the inclusive range [t - 3000, t]. 
// 
//
// It is guaranteed that every call to ping uses a strictly larger value of t 
//than the previous call. 
//
// 
// Example 1: 
//
// 
//Input
//["RecentCounter", "ping", "ping", "ping", "ping"]
//[[], [1], [100], [3001], [3002]]
//Output
//[null, 1, 2, 3, 3]
//
//Explanation
//RecentCounter recentCounter = new RecentCounter();
//recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
//recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], 
//return 2
//recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], 
//return 3
//recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,300
//2], return 3
// 
//
// 
// Constraints: 
//
// 
// 1 <= t <= 10⁹ 
// Each test case will call ping with strictly increasing values of t. 
// At most 10⁴ calls will be made to ping. 
// 
//
// Related TopicsDesign | Queue | Data Stream 
//
// 👍 656, 👎 1018bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
