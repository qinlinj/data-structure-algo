package org.qinlinj.algoframework._800_dynamic_programming_algo._860_dp_practice._864_dp_classic_practice_II;

/**
 * LeetCode 1235. Maximum Profit in Job Scheduling - Dynamic Programming Solution
 * <p>
 * PROBLEM SUMMARY:
 * Given job start times, end times, and profits, find the maximum profit you can
 * achieve by scheduling non-overlapping jobs. If a job ends at time X, you can
 * immediately start another job at time X.
 * <p>
 * KEY CONCEPTS:
 * 1. Variant of 0-1 Knapsack problem with time constraints
 * 2. Interval scheduling optimization problem
 * 3. Sort by end times to enable DP state definition
 * 4. State Definition: dp[t] = maximum profit achievable in time interval [0, t]
 * 5. For each job: choose to take it OR skip it (classic DP choice)
 * 6. TreeMap for efficient range queries (find latest time ≤ job start time)
 * <p>
 * TIME COMPLEXITY: O(n log n) - sorting + n operations with TreeMap
 * SPACE COMPLEXITY: O(n) for TreeMap storage
 * <p>
 * KEY INSIGHT:
 * After sorting by end times, for each job we can make optimal choice:
 * - Take job: profit = job.profit + max_profit_before(job.startTime)
 * - Skip job: profit = current_max_profit
 * <p>
 * EXAMPLE:
 * Jobs: [(1,3,50), (2,4,10), (3,5,40), (3,6,70)]
 * After sorting by end time: same order
 * Optimal: Take jobs 1 and 4 → profit = 50 + 70 = 120
 */

import java.util.*;

public class _864_e_JobScheduling {

    public static void main(String[] args) {
        _864_e_JobScheduling solution = new _864_e_JobScheduling();

        System.out.println("=== Maximum Profit Job Scheduling Tests ===");

        // Test case 1: Example from problem
        int[] startTime1 = {1, 2, 3, 3};
        int[] endTime1 = {3, 4, 5, 6};
        int[] profit1 = {50, 10, 40, 70};

        System.out.println("Test Case 1:");
        printJobSchedule(startTime1, endTime1, profit1);

        int result1_tree = solution.jobScheduling(startTime1, endTime1, profit1);
        int result1_bin = solution.jobSchedulingBinarySearch(startTime1, endTime1, profit1);

        System.out.printf("Result (TreeMap): %d\n", result1_tree);
        System.out.printf("Result (Binary Search): %d\n", result1_bin);
        System.out.println("Explanation: Take jobs (1,3,50) and (3,6,70) → profit = 120\n");

        // Test case 2: More complex example
        int[] startTime2 = {1, 2, 3, 4, 6};
        int[] endTime2 = {3, 5, 10, 6, 9};
        int[] profit2 = {20, 20, 100, 70, 60};

        System.out.println("Test Case 2:");
        printJobSchedule(startTime2, endTime2, profit2);

        int result2 = solution.jobScheduling(startTime2, endTime2, profit2);
        System.out.printf("Result: %d\n", result2);
        System.out.println("Explanation: Take jobs (1,3,20), (4,6,70), (6,9,60) → profit = 150\n");

        // Test case 3: All overlapping
        int[] startTime3 = {1, 1, 1};
        int[] endTime3 = {2, 3, 4};
        int[] profit3 = {5, 6, 4};

        System.out.println("Test Case 3:");
        printJobSchedule(startTime3, endTime3, profit3);

        int result3 = solution.jobScheduling(startTime3, endTime3, profit3);
        System.out.printf("Result: %d\n", result3);
        System.out.println("Explanation: All jobs overlap, take highest profit job (1,3,6) → profit = 6\n");

        // Test case 4: No overlapping
        int[] startTime4 = {1, 3, 6, 8};
        int[] endTime4 = {2, 5, 7, 9};
        int[] profit4 = {5, 6, 4, 3};

        System.out.println("Test Case 4:");
        printJobSchedule(startTime4, endTime4, profit4);

        int result4 = solution.jobScheduling(startTime4, endTime4, profit4);
        System.out.printf("Result: %d\n", result4);
        System.out.println("Explanation: No overlaps, take all jobs → profit = 18\n");

        // Algorithm explanation
        System.out.println("=== Algorithm Analysis ===");
        System.out.println("Why sorting by END time is crucial:");
        System.out.println("1. Enables clear DP state definition: dp[t] = max profit by time t");
        System.out.println("2. When processing job ending at time T, we know all");
        System.out.println("   previous jobs end before or at T");
        System.out.println("3. Can make optimal choice: take job + best_before(start) vs skip");
        System.out.println();

        System.out.println("TreeMap advantages:");
        System.out.println("- floorEntry(start): O(log n) to find max profit before start time");
        System.out.println("- Automatically maintains sorted order");
        System.out.println("- lastEntry(): O(log n) to get current maximum");
        System.out.println();

        System.out.println("DP State Transition:");
        System.out.println("For each job j:");
        System.out.println("dp[j.end] = max(");
        System.out.println("    dp[previous_max],                    // skip job j");
        System.out.println("    j.profit + dp[max_time ≤ j.start]   // take job j");
        System.out.println(")");
    }

    private static void printJobSchedule(int[] start, int[] end, int[] profit) {
        System.out.println("Jobs (start, end, profit):");
        for (int i = 0; i < start.length; i++) {
            System.out.printf("  Job %d: (%d, %d, %d)\n", i + 1, start[i], end[i], profit[i]);
        }
    }

    // Main solution using TreeMap for efficient range queries
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;

        // Create job objects and sort by end time
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        // Sort by end time - crucial for DP state definition
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1]));

        // TreeMap: time -> maximum profit achievable by that time
        // Automatically maintains sorted order for range queries
        TreeMap<Integer, Integer> dp = new TreeMap<>();

        // Base case: at time 0, maximum profit is 0
        dp.put(0, 0);

        // Process each job in sorted order
        for (int[] job : jobs) {
            int start = job[0];
            int end = job[1];
            int jobProfit = job[2];

            // Option 1: Take this job
            // Find maximum profit achievable before or at start time
            int profitWithJob = dp.floorEntry(start).getValue() + jobProfit;

            // Option 2: Skip this job
            int profitWithoutJob = dp.lastEntry().getValue();

            // Take the maximum of both options
            int maxProfit = Math.max(profitWithJob, profitWithoutJob);

            // Update DP state for this end time
            dp.put(end, maxProfit);
        }

        return dp.lastEntry().getValue();
    }

    // Alternative implementation with binary search (when TreeMap not available)
    public int jobSchedulingBinarySearch(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;

        // Create and sort jobs by end time
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> Integer.compare(a.end, b.end));

        // dp[i] = maximum profit using jobs 0 to i
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;

        for (int i = 1; i < n; i++) {
            // Option 1: Take current job
            int profitWithCurrent = jobs[i].profit;

            // Find latest job that doesn't conflict
            int latestNonConflict = findLatestNonConflicting(jobs, i);
            if (latestNonConflict != -1) {
                profitWithCurrent += dp[latestNonConflict];
            }

            // Option 2: Skip current job
            int profitWithoutCurrent = dp[i - 1];

            // Take maximum
            dp[i] = Math.max(profitWithCurrent, profitWithoutCurrent);
        }

        return dp[n - 1];
    }

    // Binary search to find latest job that ends <= current job's start time
    private int findLatestNonConflicting(Job[] jobs, int currentIndex) {
        int left = 0, right = currentIndex - 1;
        int result = -1;
        int currentStart = jobs[currentIndex].start;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (jobs[mid].end <= currentStart) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // Helper class for binary search approach
    static class Job {
        int start, end, profit;

        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}