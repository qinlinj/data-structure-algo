package org.qinlinj.algoframework._400_binary_tree_algo._440_binary_tree_algo_extension._444_merge_sort_details_and_applications;

/**
 * Merge Sort Summary and Applications
 * ---------------------------------------------------------
 * <p>
 * This class provides a comprehensive summary of merge sort and its applications
 * to advanced algorithmic problems.
 * <p>
 * Key takeaways:
 * 1. Merge sort is conceptually a post-order traversal of a binary tree
 * 2. The algorithm follows a divide-and-conquer paradigm with three key steps:
 * - Divide the array into two halves
 * - Recursively sort each half
 * - Merge the sorted halves
 * 3. Time complexity is O(n log n) for all cases, making it more consistent than quicksort
 * 4. The merge step can be modified to solve various counting problems:
 * - Count smaller elements after self (LeetCode #315)
 * - Count reverse pairs (LeetCode #493)
 * - Count range sums (LeetCode #327)
 * 5. The ability to count inversions during merging makes merge sort uniquely
 * valuable for these types of problems
 * <p>
 * These applications demonstrate how understanding algorithms from a tree perspective
 * can lead to elegant solutions for complex problems.
 */
public class _444_f_MergeSortSummary {

    public static void main(String[] args) {
        System.out.println("=== Merge Sort: Conceptual Overview ===");
        System.out.println("Merge sort is fundamentally a post-order binary tree traversal:");
        System.out.println("1. Sort left subtree (left half of array)");
        System.out.println("2. Sort right subtree (right half of array)");
        System.out.println("3. Merge results in post-order position");
        System.out.println();

        System.out.println("=== Time and Space Complexity ===");
        System.out.println("Time: O(n log n) - Consistent across all cases");
        System.out.println("Space: O(n) - Requires temporary storage for merging");
        System.out.println("Tree Height: log n levels");
        System.out.println("Work per Level: O(n) comparisons and movements");
        System.out.println();

        System.out.println("=== Key Applications ===");
        summarizeApplications();

        System.out.println("\n=== Implementation Variations ===");
        summarizeImplementations();

        System.out.println("\n=== Comparison with Other Sorting Algorithms ===");
        compareWithOtherAlgorithms();
    }

    /**
     * Summarize the key applications of merge sort
     */
    private static void summarizeApplications() {
        System.out.println("1. Basic Sorting (LeetCode #912)");
        System.out.println("   - Direct application of merge sort to sort an array");
        System.out.println("   - Standard implementation with no modifications");

        System.out.println("\n2. Count Smaller Numbers After Self (LeetCode #315)");
        System.out.println("   - For each element, count smaller elements to its right");
        System.out.println("   - During merge: when taking element from left array, count elements from right array");
        System.out.println("   - Key insight: Tracking original indices during sorting");

        System.out.println("\n3. Reverse Pairs (LeetCode #493)");
        System.out.println("   - Count pairs (i,j) where i < j and nums[i] > 2*nums[j]");
        System.out.println("   - Before merge: count pairs using sliding window technique");
        System.out.println("   - Key insight: Both subarrays are already sorted, enabling efficient counting");

        System.out.println("\n4. Count of Range Sum (LeetCode #327)");
        System.out.println("   - Count subarrays with sum in range [lower, upper]");
        System.out.println("   - Use prefix sums and two pointers to find valid ranges");
        System.out.println("   - Key insight: For each prefix sum, find valid ranges in sorted right subarray");
    }

    /**
     * Summarize different implementation approaches for merge sort
     */
    private static void summarizeImplementations() {
        System.out.println("1. Recursive Implementation");
        System.out.println("   - Most intuitive approach following divide-and-conquer");
        System.out.println("   - Better illustrates the binary tree concept");
        System.out.println("   - May cause stack overflow for very large arrays");

        System.out.println("\n2. Iterative Implementation");
        System.out.println("   - Avoids recursion using a bottom-up approach");
        System.out.println("   - Starts with sorted subarrays of size 1 and doubles the size each iteration");
        System.out.println("   - More complex but eliminates recursion overhead");

        System.out.println("\n3. In-place Merge Sort");
        System.out.println("   - Attempts to reduce space complexity below O(n)");
        System.out.println("   - Much more complex and typically less efficient in practice");
        System.out.println("   - Rarely used except in memory-constrained environments");

        System.out.println("\n4. Parallel Merge Sort");
        System.out.println("   - Exploits divide-and-conquer for parallel processing");
        System.out.println("   - Each half can be sorted independently on different processors");
        System.out.println("   - Effective for very large datasets with multi-core systems");
    }

    /**
     * Compare merge sort with other sorting algorithms
     */
    private static void compareWithOtherAlgorithms() {
        System.out.println("1. Merge Sort vs Quick Sort");
        System.out.println("   - Merge sort: Consistent O(n log n), stable, requires O(n) extra space");
        System.out.println("   - Quick sort: Average O(n log n) but worst-case O(n²), in-place, typically faster");
        System.out.println("   - Merge sort better for linked lists; quick sort better for arrays");

        System.out.println("\n2. Merge Sort vs Heap Sort");
        System.out.println("   - Both guarantee O(n log n) in worst case");
        System.out.println("   - Heap sort is in-place but typically slower than merge sort in practice");
        System.out.println("   - Merge sort is stable; heap sort is not");

        System.out.println("\n3. Merge Sort vs Counting/Radix Sort");
        System.out.println("   - Counting/radix sorts can achieve O(n) for specific data types");
        System.out.println("   - Merge sort is more general-purpose and handles any comparable data");
        System.out.println("   - Counting/radix require assumptions about the data distribution");

        System.out.println("\n4. When to Choose Merge Sort");
        System.out.println("   - Need guaranteed O(n log n) performance");
        System.out.println("   - Stability is important (preserving order of equal elements)");
        System.out.println("   - Sorting linked lists");
        System.out.println("   - External sorting (when data doesn't fit in memory)");
        System.out.println("   - Problem requires counting inversions or similar patterns");
    }

    /**
     * Compare recursive frameworks between merge sort and binary tree algorithms
     */
    public static void comparativeAnalysis() {
        System.out.println("=== Comparative Analysis: Recursive Frameworks ===");

        System.out.println("\nMerge Sort Framework:");
        System.out.println("```java");
        System.out.println("void sort(int[] nums, int lo, int hi) {");
        System.out.println("    if (lo >= hi) return;");
        System.out.println("    int mid = lo + (hi - lo) / 2;");
        System.out.println("    sort(nums, lo, mid);        // Left half");
        System.out.println("    sort(nums, mid + 1, hi);    // Right half");
        System.out.println("    merge(nums, lo, mid, hi);   // Post-order: Combine results");
        System.out.println("}");
        System.out.println("```");

        System.out.println("\nBinary Tree Post-order Traversal:");
        System.out.println("```java");
        System.out.println("void postOrderTraversal(TreeNode root) {");
        System.out.println("    if (root == null) return;");
        System.out.println("    postOrderTraversal(root.left);  // Left subtree");
        System.out.println("    postOrderTraversal(root.right); // Right subtree");
        System.out.println("    process(root);                  // Post-order: Process node");
        System.out.println("}");
        System.out.println("```");

        System.out.println("\nBinary Tree MaxDepth Calculation:");
        System.out.println("```java");
        System.out.println("int maxDepth(TreeNode root) {");
        System.out.println("    if (root == null) return 0;");
        System.out.println("    int leftDepth = maxDepth(root.left);   // Left subtree");
        System.out.println("    int rightDepth = maxDepth(root.right); // Right subtree");
        System.out.println("    return Math.max(leftDepth, rightDepth) + 1; // Combine results");
        System.out.println("}");
        System.out.println("```");

        System.out.println("\nKey Insight: All three follow the same pattern of:");
        System.out.println("1. Process left subtree/subarray");
        System.out.println("2. Process right subtree/subarray");
        System.out.println("3. Combine results in post-order position");
    }
}