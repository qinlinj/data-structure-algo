package org.qinlinj.linear.algo.sort;

import java.util.Random;

/**
 * SortCompare - A utility class for comparing the performance of different sorting algorithms
 * <p>
 * This class provides methods to:
 * 1. Generate random test data
 * 2. Time the execution of various sorting algorithms
 * 3. Run multiple trials to get more reliable performance metrics
 * 4. Compare the relative performance of different sorting algorithms
 * <p>
 * The comparison is based on actual execution time rather than theoretical complexity,
 * which provides practical insights into algorithm performance on specific hardware.
 */
public class SortCompare {
    private static Random random = new Random();

    /**
     * Generates an array of random integers for testing sorting algorithms
     *
     * @param n the size of the array to generate
     * @return an array of n random integers
     */
    private static int[] genData(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            // Generate random integers between 0 and 999,999
            data[i] = random.nextInt(1000000);

            // Alternative (commented out): Generate integers in range [-10000, 90000)
            // data[i] = random.nextInt(100000) - 10000;
        }
        return data;
    }

    /**
     * Times the execution of a specified sorting algorithm on the provided data
     *
     * @param sortType the name of the sorting algorithm to use
     * @param data     the array of integers to sort
     * @return the time taken in milliseconds
     */
    private static long time(String sortType, int[] data) {
        long start = System.currentTimeMillis();

        // Execute the appropriate sorting algorithm based on the sortType parameter
        if (sortType.equals("bubble")) new BubbleSorter().sort(data);
            // else if (sortType.equals("selection")) new SelectionSorter().sort(data);
        else if (sortType.equals("insertion")) new InsertionSorter().sort(data);
        else if (sortType.equals("insertion_ql")) new InsertionSorter().sort_ql(data);
        else if (sortType.equals("insertion_swap")) new InsertionSorter().sort_swap(data);
        else if (sortType.equals("shell")) new ShellSorter().sort(data);
        else if (sortType.equals("shell_swap")) new ShellSorter().sort_swap(data);
        else if (sortType.equals("merge")) new MergeSorter().sort(data);
        else if (sortType.equals("merge_ql")) new MergeSorter().sort_ql(data);
        else if (sortType.equals("quick")) new QuickSorter().sort(data);
        else if (sortType.equals("quick_dm")) new QuickSorter().sort_dm(data);
            // else if (sortType.equals("shell")) new ShellSorter().sort(data);
        else if (sortType.equals("counting")) new CountingSorter().sort(data);
        else if (sortType.equals("radix")) new RadixSorter().sort(data);

        return System.currentTimeMillis() - start;
    }

    /**
     * Runs multiple sorting trials to get a more reliable performance measurement
     * <p>
     * For example, manyTimesSort("quick", 10000, 5) will:
     * 1. Generate 5 different random arrays, each with 10,000 elements
     * 2. Sort each array using QuickSort
     * 3. Return the total time taken for all 5 sorts
     *
     * @param sortType the name of the sorting algorithm to use
     * @param n        the size of each test array
     * @param k        the number of trials to run
     * @return the total time taken in milliseconds for all trials
     */
    private static long manyTimesSort(String sortType, int n, int k) {
        long totalTime = 0;
        for (int i = 0; i < k; i++) {
            totalTime += time(sortType, genData(n));
        }
        return totalTime;
    }

    /**
     * Main method to run the sorting performance tests
     * <p>
     * The commented-out code shows different configurations for testing
     * various sorting algorithms with different input sizes and trial counts.
     * <p>
     * The ratio of execution times (e.g., t1/t2) shows how many times
     * slower one algorithm is compared to another.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Various performance test configurations (most are commented out)
        // double t1 = manyTimesSort("bubble", 20000, 100);
        // double t2 = manyTimesSort("selection", 1000, 100);
        // double t3 = manyTimesSort("insertion_swap", 20000, 100);
        // double t4 = manyTimesSort("insertion", 20000, 100);
        // double t5 = manyTimesSort("insertion_ql", 20000, 100);
        // double t6 = manyTimesSort("shell", 20000, 100);
        // double t7 = manyTimesSort("shell_swap", 20000, 100);
        // double t8 = manyTimesSort("merge", 100000, 100);
        // double t9 = manyTimesSort("merge_ql", 20000, 100);
        // double t10 = manyTimesSort("quick", 100000, 100);

        // Currently active test: running RadixSort 10 times on arrays of size 10
        double t11 = manyTimesSort("radix", 10, 10);

        // Example performance comparisons (commented out)
        // double t5 = manyTimesSort("shell", 1000, 100);
        // System.out.println(t1 / t2); // t1 > t2 (Bubble sort is slower than Selection sort)
        // System.out.println(t2 / t3); // t2 > t3 (Selection sort is slower than Insertion sort with swaps)
        // System.out.println(t8); // Print MergeSort time
        // System.out.println(t10); // Print QuickSort time

        // Print the current test result
        System.out.println(t11);

        // System.out.println(t3 / t5); // t3 > t5 (Insertion sort is slower than Shell sort)
    }
}