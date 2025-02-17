package org.qinlinj.linear.algo.sort;

import java.util.Random;

public class SortCompare {
    private static Random random = new Random();

    private static int[] genData(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(1000000);
//            data[i] = random.nextInt(100000) - 10000;
        }
        return data;
    }

    private static long time(String sortType, int[] data) {
        long start = System.currentTimeMillis();

        if (sortType.equals("bubble")) new BubbleSorter().sort(data);
//        else if (sortType.equals("selection")) new SelectionSorter().sort(data);
        else if (sortType.equals("insertion")) new InsertionSorter().sort(data);
        else if (sortType.equals("insertion_ql")) new InsertionSorter().sort_ql(data);
        else if (sortType.equals("insertion_swap")) new InsertionSorter().sort_swap(data);
        else if (sortType.equals("shell")) new ShellSorter().sort(data);
        else if (sortType.equals("shell_swap")) new ShellSorter().sort_swap(data);
        else if (sortType.equals("merge")) new MergeSorter().sort(data);
        else if (sortType.equals("merge_ql")) new MergeSorter().sort_ql(data);
        else if (sortType.equals("quick")) new QuickSorter().sort(data);
        else if (sortType.equals("quick_dm")) new QuickSorter().sort_dm(data);
//        else if (sortType.equals("shell")) new ShellSorter().sort(data);
        else if (sortType.equals("counting")) new CountingSorter().sort(data);
        else if (sortType.equals("radix")) new RadixSorter().sort(data);

        return System.currentTimeMillis() - start;
    }

    private static long manyTimesSort(String sortType, int n, int k) {
        long totalTime = 0;
        for (int i = 0; i < k; i++) {
            totalTime += time(sortType, genData(n));
        }
        return totalTime;
    }

    public static void main(String[] args) {
//        double t1 = manyTimesSort("bubble", 20000, 100);
//        double t2 = manyTimesSort("selection", 1000, 100);
//        double t3 = manyTimesSort("insertion_swap", 20000, 100);
//        double t4 = manyTimesSort("insertion", 20000, 100);
//        double t5 = manyTimesSort("insertion_ql", 20000, 100);
//        double t6 = manyTimesSort("shell", 20000, 100);
//        double t7 = manyTimesSort("shell_swap", 20000, 100);
//        double t8 = manyTimesSort("merge", 100000, 100);
//        double t9 = manyTimesSort("merge_ql", 20000, 100);
//        double t10 = manyTimesSort("quick", 100000, 100);
        double t11 = manyTimesSort("radix", 10, 10);

//        double t5 = manyTimesSort("shell", 1000, 100);
//        System.out.println(t1 / t2); // t1 > t2
//        System.out.println(t2 / t3); // t2 > t3
//        System.out.println(t8);
//        System.out.println(t10);
        System.out.println(t11);
//        System.out.println(t3 / t5); // t3 > t5
    }
}
