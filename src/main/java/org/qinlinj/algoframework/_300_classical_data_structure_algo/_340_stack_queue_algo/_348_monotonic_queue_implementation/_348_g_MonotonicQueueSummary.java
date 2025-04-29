package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._348_monotonic_queue_implementation;

/**
 * Monotonic Queue - Summary and Applications
 * <p>
 * This class provides a comprehensive overview of monotonic queues and their applications
 * in various algorithmic problems.
 * <p>
 * Key concepts covered:
 * 1. Definition and structure of a monotonic queue
 * 2. Advantages over other data structures
 * 3. Implementation patterns and variations
 * 4. Common application scenarios
 * 5. Sample problems and their solutions
 * <p>
 * A monotonic queue maintains elements in either monotonically increasing or decreasing order
 * while preserving the FIFO (First-In-First-Out) property of a queue. This allows for
 * efficient tracking of maximum/minimum values in a dynamic sequence, particularly in
 * sliding window scenarios.
 * <p>
 * Common applications include:
 * - Finding maximums/minimums in sliding windows
 * - Optimizing dynamic programming calculations
 * - Processing sequential data with constraints
 * - Handling circular arrays
 */

import java.util.*;

public class _348_g_MonotonicQueueSummary {

    /**
     * Main method to demonstrate common applications of monotonic queues
     */
    public static void main(String[] args) {
        displayIntroduction();
        displayImplementationPatterns();
        displayCommonApplications();
        displayExampleProblems();
        displayPerformanceAnalysis();
        displaySummaryAndConclusion();
    }

    /**
     * Display an introduction to monotonic queues
     */
    private static void displayIntroduction() {
        System.out.println("===== MONOTONIC QUEUE - INTRODUCTION =====");
        System.out.println("A monotonic queue is a data structure that maintains elements in either");
        System.out.println("monotonically increasing or decreasing order while preserving the queue's");
        System.out.println("First-In-First-Out (FIFO) property.");

        System.out.println("\nKey features:");
        System.out.println("1. O(1) access to maximum or minimum elements");
        System.out.println("2. Maintains time order of elements (unlike heaps)");
        System.out.println("3. Supports standard queue operations");
        System.out.println("4. Efficient for sliding window problems");

        System.out.println("\nAdvantages over other structures:");
        System.out.println("- More efficient than scanning a window: O(1) vs O(k) for window of size k");
        System.out.println("- Preserves order unlike priority queues");
        System.out.println("- More space-efficient than maintaining sorted sequences");
        System.out.println();
    }

    /**
     * Display implementation patterns for monotonic queues
     */
    private static void displayImplementationPatterns() {
        System.out.println("===== IMPLEMENTATION PATTERNS =====");
        System.out.println("1. Basic Monotonic Queue (single direction):");
        System.out.println("   - Maintains either maximum or minimum tracking");
        System.out.println("   - Uses a single deque for efficient operations");
        System.out.println("   - Requires special handling for element removal");

        System.out.println("\n2. Enhanced Generic Monotonic Queue:");
        System.out.println("   - Tracks both maximum and minimum values");
        System.out.println("   - Uses three internal queues (standard, max, min)");
        System.out.println("   - Provides a complete API with standard queue operations");
        System.out.println("   - Supports generic types with proper comparison");

        System.out.println("\n3. Index-based Monotonic Queue:");
        System.out.println("   - Stores indices instead of values");
        System.out.println("   - Useful when position information is needed");
        System.out.println("   - Facilitates sliding window implementations");

        System.out.println("\n4. Value-Span Pair Monotonic Queue:");
        System.out.println("   - Stores values along with their span information");
        System.out.println("   - Useful for problems like stock spans and histograms");
        System.out.println();
    }

    /**
     * Display common applications of monotonic queues
     */
    private static void displayCommonApplications() {
        System.out.println("===== COMMON APPLICATIONS =====");
        System.out.println("1. Sliding Window Problems:");
        System.out.println("   - Finding maximum/minimum in fixed-size windows");
        System.out.println("   - Tracking ranges with bounded differences");
        System.out.println("   - Processing sequential data with constraints");

        System.out.println("\n2. Dynamic Programming Optimization:");
        System.out.println("   - Eliminating nested loops in DP state transitions");
        System.out.println("   - Finding maximum/minimum values within a range of states");
        System.out.println("   - Improving time complexity from O(nk) to O(n)");

        System.out.println("\n3. Circular Array Processing:");
        System.out.println("   - Simulating circular arrays by expanding the window");
        System.out.println("   - Finding maximum/minimum values in circular contexts");
        System.out.println("   - Managing wrapping constraints in circular data structures");

        System.out.println("\n4. Range Query Problems:");
        System.out.println("   - Nearest smaller/greater element problems");
        System.out.println("   - Largest rectangle in histogram");
        System.out.println("   - Maximum area under skyline");
        System.out.println();
    }

    /**
     * Display example problems that use monotonic queues
     */
    private static void displayExampleProblems() {
        System.out.println("===== EXAMPLE PROBLEMS =====");
        System.out.println("1. Sliding Window Maximum (LeetCode 239):");
        System.out.println("   - Find maximum in each sliding window of fixed size");
        System.out.println("   - Monotonic queue tracks maximum in current window");
        System.out.println("   - O(n) time complexity with O(k) space");

        System.out.println("\n2. Longest Subarray with Limited Absolute Difference (LeetCode 1438):");
        System.out.println("   - Find longest subarray where max-min ≤ limit");
        System.out.println("   - Two monotonic queues track both max and min");
        System.out.println("   - Combined with sliding window technique");

        System.out.println("\n3. Shortest Subarray with Sum at Least K (LeetCode 862):");
        System.out.println("   - Find shortest subarray with sum ≥ K");
        System.out.println("   - Combines prefix sums with monotonic queue");
        System.out.println("   - Handles edge cases with negative numbers");

        System.out.println("\n4. Maximum Sum Circular Subarray (LeetCode 918):");
        System.out.println("   - Find maximum sum subarray in circular context");
        System.out.println("   - Uses monotonic queue with expanded array");
        System.out.println("   - Handles wraparound subarray constraints");

        System.out.println("\n5. Jump Game VI / Constrained Subset Sum (LeetCode 1696/1425):");
        System.out.println("   - Dynamic programming optimization problems");
        System.out.println("   - Use monotonic queue to eliminate inner loops");
        System.out.println("   - Improves time complexity from O(nk) to O(n)");
        System.out.println();
    }

    /**
     * Display performance analysis of monotonic queues
     */
    private static void displayPerformanceAnalysis() {
        System.out.println("===== PERFORMANCE ANALYSIS =====");
        System.out.println("Time Complexity:");
        System.out.println("- push() operation: O(1) amortized (worst-case O(n))");
        System.out.println("- pop() operation: O(1)");
        System.out.println("- max()/min() operations: O(1)");
        System.out.println("- Overall algorithm using monotonic queue: O(n) for processing n elements");

        System.out.println("\nSpace Complexity:");
        System.out.println("- Basic implementation: O(n) in worst case");
        System.out.println("- Enhanced implementation: O(n) for each internal queue");
        System.out.println("- Sliding window implementations: O(k) for window of size k");

        System.out.println("\nAmortized Analysis Explanation:");
        System.out.println("Although push() contains a while loop that appears to be O(n),");
        System.out.println("each element can only be removed once after being added.");
        System.out.println("This gives an amortized O(1) time complexity per operation,");
        System.out.println("leading to O(n) overall time complexity for processing n elements.");
        System.out.println();
    }

    /**
     * Display summary and conclusion
     */
    private static void displaySummaryAndConclusion() {
        System.out.println("===== SUMMARY AND CONCLUSION =====");
        System.out.println("Monotonic queues are powerful data structures that combine the benefits of:");
        System.out.println("- Stacks/queues: O(1) push/pop operations");
        System.out.println("- Priority queues: O(1) access to maximum/minimum");
        System.out.println("- Sliding windows: Efficient handling of dynamic ranges");

        System.out.println("\nKey takeaways:");
        System.out.println("1. Monotonic queues excel at problems requiring tracking extremes in sequences");
        System.out.println("2. They are particularly useful for sliding window problems");
        System.out.println("3. They can dramatically optimize certain dynamic programming approaches");
        System.out.println("4. They handle circular contexts effectively");
        System.out.println("5. Implementation is simple but powerful");

        System.out.println("\nWhen to consider using a monotonic queue:");
        System.out.println("- When you need O(1) access to maximum/minimum in a dynamic sequence");
        System.out.println("- When order of elements matters (unlike heaps)");
        System.out.println("- When sliding window problems involve finding maximums/minimums");
        System.out.println("- When dynamic programming has nested loops for finding max/min values");

        System.out.println("\nThe monotonic queue is an elegant data structure that simplifies");
        System.out.println("and optimizes solutions to a specific class of problems that would");
        System.out.println("otherwise require more complex or less efficient approaches.");
    }

    /**
     * Generic Monotonic Queue implementation (included for reference)
     */
    static class MonotonicQueue<E extends Comparable<E>> {
        // Standard queue to store all elements
        private LinkedList<E> q = new LinkedList<>();
        // Max queue (decreasing order)
        private LinkedList<E> maxq = new LinkedList<>();
        // Min queue (increasing order)
        private LinkedList<E> minq = new LinkedList<>();

        public void push(E elem) {
            q.addLast(elem);

            // Update max queue
            while (!maxq.isEmpty() && maxq.getLast().compareTo(elem) < 0) {
                maxq.pollLast();
            }
            maxq.addLast(elem);

            // Update min queue
            while (!minq.isEmpty() && minq.getLast().compareTo(elem) > 0) {
                minq.pollLast();
            }
            minq.addLast(elem);
        }

        public E max() {
            return maxq.getFirst();
        }

        public E min() {
            return minq.getFirst();
        }

        public E pop() {
            E deleteVal = q.pollFirst();

            if (deleteVal.equals(maxq.getFirst())) {
                maxq.pollFirst();
            }

            if (deleteVal.equals(minq.getFirst())) {
                minq.pollFirst();
            }

            return deleteVal;
        }

        public int size() {
            return q.size();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }
    }
}
