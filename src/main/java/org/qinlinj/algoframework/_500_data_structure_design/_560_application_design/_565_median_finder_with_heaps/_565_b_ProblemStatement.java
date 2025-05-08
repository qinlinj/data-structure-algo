package org.qinlinj.algoframework._500_data_structure_design._560_application_design._565_median_finder_with_heaps;

/**
 * PROBLEM STATEMENT: MEDIAN OF DATA STREAM (LeetCode 295)
 * <p>
 * Problem Definition:
 * The median is the middle value in an ordered integer list. If the list size is even,
 * the median is the average of the two middle values.
 * <p>
 * We need to implement a MedianFinder class with the following methods:
 * 1. addNum(int num) - Add an integer number to the data stream
 * 2. findMedian() - Return the median of all elements added so far
 * <p>
 * Examples:
 * - If we add [1], the median is 1
 * - If we add [1,2], the median is (1+2)/2 = 1.5
 * - If we add [1,2,3], the median is 2
 * <p>
 * Constraints:
 * - -10^5 <= num <= 10^5
 * - There will be at least one element before calling findMedian()
 * - At most 5 * 10^4 calls will be made to addNum and findMedian
 * <p>
 * Analysis of Potential Solutions:
 * 1. Array-based solution: Keep an array of all elements in sorted order.
 * - addNum: Use binary search to find insertion point (O(log n)) but insertion requires shifting elements (O(n))
 * - findMedian: Direct access to middle element(s) (O(1))
 * - Overall: Not optimal due to O(n) insertion time
 * <p>
 * 2. Linked list-based solution:
 * - addNum: Finding insertion position requires linear search (O(n)), but insertion is fast (O(1))
 * - findMedian: Requires traversing to middle (O(n))
 * - Overall: Not optimal due to O(n) operations
 * <p>
 * 3. Balanced Binary Search Tree:
 * - Issues:
 * a. Doesn't handle duplicates well (if using TreeSet)
 * b. Doesn't provide direct rank-finding operations
 * - Overall: Would require custom implementation
 * <p>
 * 4. Priority Queue (Heap) solution:
 * - While a single heap cannot provide the median directly, a two-heap approach can work
 * - This will be our chosen solution
 */
public class _565_b_ProblemStatement {

    // The class demonstrates the problem rather than implementing the solution

    public static void main(String[] args) {
        // Demonstrate the expected behavior of the MedianFinder class

        System.out.println("Example of expected MedianFinder behavior:");
        System.out.println("1. Initialize MedianFinder");
        System.out.println("2. Add number 1: [1]");
        System.out.println("   Expected median: 1.0");
        System.out.println("3. Add number 2: [1,2]");
        System.out.println("   Expected median: 1.5");
        System.out.println("4. Add number 3: [1,2,3]");
        System.out.println("   Expected median: 2.0");
        System.out.println();

        System.out.println("Problems with simple approaches:");
        System.out.println("- Array: O(n) insertion time due to shifting elements");
        System.out.println("- Linked List: O(n) time to find insertion point and find median");
        System.out.println("- TreeSet: Cannot handle duplicates and lacks rank-finding API");
        System.out.println("- Single Heap: Cannot provide median directly");
        System.out.println();

        System.out.println("Solution: Two-heap approach (see _565_c_Solution class)");
    }
}