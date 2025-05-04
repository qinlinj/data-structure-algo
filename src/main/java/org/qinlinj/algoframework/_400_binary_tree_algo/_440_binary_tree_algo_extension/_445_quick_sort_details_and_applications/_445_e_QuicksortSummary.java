package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._445_quick_sort_details_and_applications;

import java.util.*;

/**
 * Quicksort and Quick Select Summary
 * ---------------------------------------------------------
 * <p>
 * This class provides a comprehensive summary of quicksort and quick select algorithms,
 * highlighting their relationships, variations, and applications.
 * <p>
 * Key takeaways:
 * 1. The binary tree perspective:
 * - Quicksort can be viewed as a pre-order traversal of a binary tree
 * - Partitioning places one element in its correct position and recursively sorts subarrays
 * - The recursive structure effectively constructs a binary search tree
 * <p>
 * 2. Algorithm comparison:
 * - Merge sort: "Sort left half, sort right half, then merge" (post-order traversal)
 * - Quicksort: "Partition array, then sort left half and right half" (pre-order traversal)
 * - Quick select: "Partition array, then search in only one half" (partial pre-order traversal)
 * <p>
 * 3. Performance characteristics:
 * - Quicksort: O(n log n) average case, O(n²) worst case
 * - Quick select: O(n) average case, O(n²) worst case (or O(n) with median-of-medians)
 * - Both algorithms benefit significantly from randomization
 * <p>
 * 4. Practical considerations:
 * - Quicksort is not stable but generally has better cache performance than merge sort
 * - Quick select is more efficient than sorting for finding a single element
 * - Binary heap methods (priority queue) offer simpler but slower alternatives
 * <p>
 * These algorithms demonstrate how the same underlying concept (partitioning)
 * can be adapted to solve different problems efficiently.
 */
public class _445_e_QuicksortSummary {

    /**
     * Main method to present summary information and comparative analysis
     */
    public static void main(String[] args) {
        System.out.println("=== Quicksort and Quick Select: A Comprehensive Analysis ===\n");

        // Compare algorithm paradigms
        compareAlgorithmParadigms();

        // Analyze key techniques and optimizations
        analyzeKeyTechniques();

        // Present practical applications
        discussApplications();

        // Comparison with other algorithms
        compareWithOtherAlgorithms();

        // Analyze stability and its implications
        analyzeStability();

        // Draw connection to LeetCode problems
        connectionToLeetCode();
    }

    /**
     * Compare the paradigms of Quicksort, Merge Sort, and Quick Select
     */
    private static void compareAlgorithmParadigms() {
        System.out.println("COMPARISON OF ALGORITHM PARADIGMS");
        System.out.println("--------------------------------");

        System.out.println("\nRecursive Structure and Binary Tree View:");
        System.out.println("------------------------------------------");
        System.out.println("1. Merge Sort (Post-order):");
        System.out.println("   - First process left subtree");
        System.out.println("   - Then process right subtree");
        System.out.println("   - Finally, process current node (merge)");
        System.out.println("   - Builds results from bottom up");

        System.out.println("\n2. Quicksort (Pre-order):");
        System.out.println("   - First process current node (partition)");
        System.out.println("   - Then process left subtree");
        System.out.println("   - Finally, process right subtree");
        System.out.println("   - Places elements in final positions top-down");

        System.out.println("\n3. Quick Select (Partial pre-order):");
        System.out.println("   - First process current node (partition)");
        System.out.println("   - Then process EITHER left OR right subtree");
        System.out.println("   - Follows only one path in the recursive tree");

        System.out.println("\nOne-Line Descriptions:");
        System.out.println("---------------------");
        System.out.println("- Merge Sort: \"Sort left half, sort right half, then merge results\"");
        System.out.println("- Quicksort: \"Partition to place one element, then sort both halves\"");
        System.out.println("- Quick Select: \"Partition, then only follow the half containing the target\"");

        System.out.println("\nRecursive Tree Comparison:");
        System.out.println("-------------------------");
        System.out.println("For array [3, 2, 1, 5, 6, 4]:");

        System.out.println("\nQuicksort tree (pre-order, fully traversed):");
        System.out.println("                   [3* 2 1 5 6 4]                    ");
        System.out.println("                     /       \\                     ");
        System.out.println("           [2* 1 3]             [5* 6 4]             ");
        System.out.println("             /   \\               /   \\             ");
        System.out.println("          [1*]    [3*]         [4*]    [6*]          ");
        System.out.println("(* indicates the pivot in each partition)");

        System.out.println("\nQuick Select tree (finding 3rd largest, partial traversal):");
        System.out.println("                   [3* 2 1 5 6 4]                    ");
        System.out.println("                            \\                       ");
        System.out.println("                           [5* 6 4]                   ");
        System.out.println("                           /                         ");
        System.out.println("                         [4*]                       ");
        System.out.println("(Pivots: 3→5→4, found answer: 4)");
    }

    /**
     * Analyze key techniques and optimizations for quicksort
     */
    private static void analyzeKeyTechniques() {
        System.out.println("\nKEY TECHNIQUES AND OPTIMIZATIONS");
        System.out.println("-------------------------------");

        System.out.println("\n1. Partitioning Schemes:");
        System.out.println("   a. Lomuto partition - simpler but less efficient");
        System.out.println("   b. Hoare partition - more efficient, fewer swaps");
        System.out.println("   c. Three-way partition - efficient for many duplicates");

        System.out.println("\n2. Pivot Selection Strategies:");
        System.out.println("   a. First element - simple but vulnerable to worst case");
        System.out.println("   b. Random element - statistical protection against worst case");
        System.out.println("   c. Median-of-three - good compromise (first, middle, last)");
        System.out.println("   d. Median-of-medians - guarantees O(n) for Quick Select");

        System.out.println("\n3. Small Subarray Optimizations:");
        System.out.println("   a. Insertion sort for small subarrays (< 10-20 elements)");
        System.out.println("   b. Early termination when subarrays are already small");

        System.out.println("\n4. Tail Recursion Elimination:");
        System.out.println("   a. Process smaller partition recursively");
        System.out.println("   b. Use iteration for larger partition");
        System.out.println("   c. Reduces stack space from O(n) to O(log n) in worst case");

        System.out.println("\n5. Dual-Pivot Quicksort:");
        System.out.println("   a. Used in Java's Arrays.sort() for primitive types");
        System.out.println("   b. Partitions array into three parts with two pivots");
        System.out.println("   c. Better practical performance than single-pivot");
    }

    /**
     * Discuss practical applications of quicksort and quick select
     */
    private static void discussApplications() {
        System.out.println("\nPRACTICAL APPLICATIONS");
        System.out.println("---------------------");

        System.out.println("\nQuicksort Applications:");
        System.out.println("1. General-purpose sorting in many programming languages");
        System.out.println("   - Java's Arrays.sort() uses dual-pivot quicksort for primitives");
        System.out.println("   - C++'s std::sort() uses introsort (quicksort with heapsort fallback)");
        System.out.println("2. Database systems for indexing and query processing");
        System.out.println("3. Graphics rendering for sorting polygons by depth");

        System.out.println("\nQuick Select Applications:");
        System.out.println("1. Finding the kth smallest/largest element (LeetCode #215)");
        System.out.println("2. Computing median without sorting the entire array");
        System.out.println("3. Efficient image processing (median filtering)");
        System.out.println("4. Statistical analysis (percentiles, quartiles)");
        System.out.println("5. Machine learning (k-nearest neighbors, decision trees)");

        System.out.println("\nReal-world Examples:");
        System.out.println("1. Finding median house prices in a dataset");
        System.out.println("2. Selecting top k performing stocks");
        System.out.println("3. Trimmed mean calculations (removing outliers)");
        System.out.println("4. Computing the median-of-medians for robust statistics");
    }

    /**
     * Compare quicksort with other sorting algorithms and quick select with alternatives
     */
    private static void compareWithOtherAlgorithms() {
        System.out.println("\nCOMPARISON WITH OTHER ALGORITHMS");
        System.out.println("-------------------------------");

        System.out.println("\nSorting Algorithm Comparison:");
        System.out.println("----------------------------");
        System.out.println("Algorithm      | Average   | Worst     | Space    | Stable    | In-place");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Quicksort      | O(n log n)| O(n²)     | O(log n) | No        | Yes");
        System.out.println("Merge Sort     | O(n log n)| O(n log n)| O(n)     | Yes       | No");
        System.out.println("Heapsort       | O(n log n)| O(n log n)| O(1)     | No        | Yes");
        System.out.println("Insertion Sort | O(n²)     | O(n²)     | O(1)     | Yes       | Yes");
        System.out.println("Timsort        | O(n log n)| O(n log n)| O(n)     | Yes       | No");
        System.out.println("Dual-Pivot QS  | O(n log n)| O(n²)     | O(log n) | No        | Yes");

        System.out.println("\nSelection Algorithm Comparison:");
        System.out.println("------------------------------");
        System.out.println("Algorithm        | Average | Worst   | Space     | Application");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Quick Select     | O(n)    | O(n²)   | O(log n)  | General purpose");
        System.out.println("Determ. QS       | O(n)    | O(n)    | O(log n)  | When worst-case guarantees matter");
        System.out.println("Priority Queue   | O(n log k)| O(n log k)| O(k)  | Simple implementation, small k");
        System.out.println("Partial Sort     | O(n log k)| O(n log k)| O(1)  | When sorted order is also needed");
        System.out.println("Binary Search Tree| O(n)   | O(n²)   | O(n)     | When data is already in a BST");

        System.out.println("\nPractical Performance Factors:");
        System.out.println("----------------------------");
        System.out.println("1. Cache efficiency - Quicksort's locality of reference");
        System.out.println("2. Branch prediction - Regular patterns are faster");
        System.out.println("3. Memory allocation - In-place algorithms use less memory");
        System.out.println("4. Data distribution - Some algorithms perform better with specific patterns");
        System.out.println("5. Implementation details - Optimizations can drastically change performance");
    }

    /**
     * Analyze stability in sorting algorithms and its implications
     */
    private static void analyzeStability() {
        System.out.println("\nSTABILITY ANALYSIS AND IMPLICATIONS");
        System.out.println("---------------------------------");

        System.out.println("\nWhat is Stability?");
        System.out.println("- A sorting algorithm is stable if elements with equal keys maintain their relative order");
        System.out.println("- Example: If we have [(A,1), (B,2), (C,2)], a stable sort ensures (B,2) comes before (C,2)");

        System.out.println("\nWhy Quicksort is Not Stable:");
        System.out.println("- During partitioning, elements equal to the pivot may change relative positions");
        System.out.println("- Example with array [4, 2, 4*, 1] (where 4* is a second occurrence of 4):");
        System.out.println("  After partitioning: [1, 2, 4*, 4] or [1, 2, 4, 4*]");
        System.out.println("  The order of the two 4's cannot be guaranteed");

        System.out.println("\nMaking Quicksort Stable:");
        System.out.println("1. Add indices to elements before sorting");
        System.out.println("2. Use indices as secondary sort key");
        System.out.println("3. Costs extra O(n) space and adds overhead");

        System.out.println("\nWhen Stability Matters:");
        System.out.println("1. Multi-field sorting (sort by one field, then another)");
        System.out.println("2. Preserving order from previous sorts");
        System.out.println("3. Database operations (maintaining order in JOINs)");
        System.out.println("4. User interface consistency (displayed order)");

        System.out.println("\nReal-world Example:");
        System.out.println("- Sorting a list of orders first by date, then by customer name");
        System.out.println("- Stable sort ensures orders on the same date maintain customer name order");
        System.out.println("- Non-stable sort would require resorting by both fields simultaneously");

        System.out.println("\nJava's Approach:");
        System.out.println("- Arrays.sort() uses quicksort for primitives (non-stable)");
        System.out.println("- Arrays.sort() uses mergesort for objects (stable)");
        System.out.println("- When stability matters, use Collections.sort() or Arrays.sort() with objects");
    }

    /**
     * Connect to LeetCode problems that use quicksort and quick select
     */
    private static void connectionToLeetCode() {
        System.out.println("\nCONNECTION TO LEETCODE PROBLEMS");
        System.out.println("------------------------------");

        System.out.println("\nQuicksort Problems:");
        System.out.println("1. LeetCode #912 - Sort an Array");
        System.out.println("   - Direct application of quicksort");
        System.out.println("   - Tests understanding of basic implementation");

        System.out.println("\n2. LeetCode #75 - Sort Colors");
        System.out.println("   - Dutch national flag problem");
        System.out.println("   - Application of three-way partitioning");
        System.out.println("   - O(n) single-pass solution possible");

        System.out.println("\nQuick Select Problems:");
        System.out.println("1. LeetCode #215 - Kth Largest Element in an Array");
        System.out.println("   - Direct application of quick select");
        System.out.println("   - Find kth largest without sorting the entire array");

        System.out.println("\n2. LeetCode #973 - K Closest Points to Origin");
        System.out.println("   - Find k points closest to origin");
        System.out.println("   - Quick select on distances");

        System.out.println("\n3. LeetCode #347 - Top K Frequent Elements");
        System.out.println("   - Find k most frequent elements");
        System.out.println("   - Combination of hash map and quick select");

        System.out.println("\n4. LeetCode #295 - Find Median from Data Stream");
        System.out.println("   - Advanced application of selection algorithms");
        System.out.println("   - Requires maintaining running median");

        System.out.println("\nAdvanced Variants:");
        System.out.println("1. LeetCode #324 - Wiggle Sort II");
        System.out.println("   - Find median using quick select");
        System.out.println("   - Three-way partitioning around median");

        System.out.println("\n2. LeetCode #493 - Reverse Pairs");
        System.out.println("   - Combine merge sort or quicksort with counting");
        System.out.println("   - Count pairs during sorting process");

        System.out.println("\nKey Takeaway:");
        System.out.println("Understanding the principles behind quicksort and quick select");
        System.out.println("provides a powerful framework for solving a wide range of");
        System.out.println("algorithmic problems beyond simple sorting.");
    }

    /**
     * Inner class demonstrating quicksort code implementation
     */
    private static class QuickSort {
        // Standard quicksort implementation
        public static void sort(int[] nums) {
            shuffle(nums);
            sort(nums, 0, nums.length - 1);
        }

        private static void sort(int[] nums, int lo, int hi) {
            if (lo >= hi) return;

            int p = partition(nums, lo, hi);
            sort(nums, lo, p - 1);
            sort(nums, p + 1, hi);
        }

        private static int partition(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            int i = lo + 1, j = hi;

            while (i <= j) {
                while (i < hi && nums[i] <= pivot) i++;
                while (j > lo && nums[j] > pivot) j--;

                if (i >= j) break;
                swap(nums, i, j);
            }

            swap(nums, lo, j);
            return j;
        }

        private static void shuffle(int[] nums) {
            Random rand = new Random();
            for (int i = 0; i < nums.length; i++) {
                int r = i + rand.nextInt(nums.length - i);
                swap(nums, i, r);
            }
        }

        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * Inner class demonstrating quick select code implementation
     */
    private static class QuickSelect {
        // Standard quick select implementation
        public static int findKthLargest(int[] nums, int k) {
            shuffle(nums);
            int n = nums.length;
            k = n - k; // Convert to 0-based index of kth smallest

            int lo = 0, hi = n - 1;
            while (lo < hi) {
                int j = partition(nums, lo, hi);

                if (j < k) lo = j + 1;
                else if (j > k) hi = j - 1;
                else break;
            }

            return nums[k];
        }

        private static int partition(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            int i = lo + 1, j = hi;

            while (i <= j) {
                while (i < hi && nums[i] <= pivot) i++;
                while (j > lo && nums[j] > pivot) j--;

                if (i >= j) break;
                swap(nums, i, j);
            }

            swap(nums, lo, j);
            return j;
        }

        private static void shuffle(int[] nums) {
            Random rand = new Random();
            for (int i = 0; i < nums.length; i++) {
                int r = i + rand.nextInt(nums.length - i);
                swap(nums, i, r);
            }
        }

        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}