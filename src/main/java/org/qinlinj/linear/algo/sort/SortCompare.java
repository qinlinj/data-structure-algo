package org.qinlinj.linear.algo.sort;

import java.util.Random;

public class SortCompare {
    private static Random random = new Random();

    private static int[] genData(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt();
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

//        else if (sortType.equals("shell")) new ShellSorter().sort(data);

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
        double t2 = manyTimesSort("insertion", 20000, 100);
//        double t5 = manyTimesSort("insertion_ql", 20000, 100);
        double t3 = manyTimesSort("shell", 20000, 100);
        double t5 = manyTimesSort("shell_swap", 20000, 100);

//        double t5 = manyTimesSort("shell", 1000, 100);
//        System.out.println(t1 / t2); // t1 > t2
//        System.out.println(t2 / t3); // t2 > t3
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t5);
//        System.out.println(t3 / t5); // t3 > t5
    }
}
