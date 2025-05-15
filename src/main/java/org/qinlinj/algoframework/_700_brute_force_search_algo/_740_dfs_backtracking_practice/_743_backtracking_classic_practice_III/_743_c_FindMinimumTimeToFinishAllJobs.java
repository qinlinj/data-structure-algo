package org.qinlinj.algoframework._700_brute_force_search_algo._740_dfs_backtracking_practice._743_backtracking_classic_practice_III;

import java.util.*;

/**
 * 1723. Find Minimum Time to Finish All Jobs
 * <p>
 * Problem Summary:
 * You need to assign n jobs to k workers, where jobs[i] represents the time needed to complete job i.
 * Each job must be assigned to exactly one worker, and each worker can receive any number of jobs.
 * Find the minimum possible maximum working time of any worker.
 * <p>
 * Key Insights:
 * - This is a job assignment optimization problem that can be solved with backtracking
 * - We can approach this from the jobs' perspective (jobs selecting workers)
 * - Two critical optimizations:
 * 1. If assigning a job would make a worker's time exceed our current best solution, skip it
 * 2. If multiple workers have the same workload, we only need to try one of them
 * (since from the job's perspective, workers with equal workload are identical)
 * - The small input size (≤12 jobs) makes backtracking with optimizations feasible
 * <p>
 * Time Complexity: O(k^n) worst case without optimizations, where n is the number of jobs
 * Space Complexity: O(k) for tracking worker workloads plus recursion stack
 */
class _743_c_FindMinimumTimeToFinishAllJobs {

    // Track minimum maximum working time across all valid assignments
    int res = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        // Array to track each worker's current workload
        int[] workloads = new int[k];

        // Start backtracking from the first job
        backtrack(jobs, 0, workloads);

        return res;
    }

    void backtrack(int[] jobs, int jobIdx, int[] workloads) {
        // Base case: all jobs assigned
        if (jobIdx == jobs.length) {
            // Calculate maximum workload in current assignment
            int maxWorkload = 0;
            for (int workload : workloads) {
                maxWorkload = Math.max(maxWorkload, workload);
            }

            // Update minimum of the maximum workloads
            res = Math.min(res, maxWorkload);
            return;
        }

        // Optimization: track which workload values we've already tried
        // This is a critical insight - workers with same workload are interchangeable
        // from the job's perspective
        HashSet<Integer> tried = new HashSet<>();

        // Try assigning current job to each worker
        for (int workerIdx = 0; workerIdx < workloads.length; workerIdx++) {
            // Optimization 1: Skip if this assignment would exceed our current best solution
            if (workloads[workerIdx] + jobs[jobIdx] >= res) {
                continue;
            }

            // Optimization 2: Skip if we've already tried a worker with this workload
            // This avoids redundant exploration of equivalent states
            if (tried.contains(workloads[workerIdx])) {
                continue;
            }

            // Mark this workload as tried
            tried.add(workloads[workerIdx]);

            // Make choice: assign job to worker
            workloads[workerIdx] += jobs[jobIdx];

            // Explore next job assignment
            backtrack(jobs, jobIdx + 1, workloads);

            // Undo choice for backtracking
            workloads[workerIdx] -= jobs[jobIdx];
        }
    }

    /**
     * Alternative approach: Binary search with feasibility check
     * While not implemented here, we could also solve this with binary search:
     * 1. Binary search for the minimum possible maximum working time
     * 2. For each potential maximum time, check if it's possible to assign jobs
     *    without any worker exceeding that time limit
     * This would change time complexity to O(log(sum(jobs)) * k^n) in worst case,
     * potentially better with proper pruning.
     */
}