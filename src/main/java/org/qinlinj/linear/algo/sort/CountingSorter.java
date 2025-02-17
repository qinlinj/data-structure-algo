package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class CountingSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{-22, 2, 3, 6, 1, 34, 11, 53, -1, 6, 22, 12};
        CountingSorter sorter = new CountingSorter();
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        int max = data[0];
        int min = data[0];
        for (int i = 1; i < data.length; i++) {
            max = Math.max(max, data[i]);
            min = Math.min(min, data[i]);
        }
        int[] countArray = new int[max - min + 1];
        for (int datum : data) {
            countArray[datum - min]++;
        }
        // calculate the index of each element
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        int tmp[] = new int[data.length];
        // get sorted array

//        for (int i = data.length - 1; i >= 0; i--) { // O(n)
//            int j = data[i];
//            int k = countArray[j - min] - 1;
//            tmp[k] = data[i];
//            countArray[j - min]--;
//        }

        for (int j = data.length - 1; j >= 0; j--) {
            tmp[--countArray[data[j] - min]] = data[j];
        }

        // array copy
        System.arraycopy(tmp, 0, data, 0, data.length);
    }
}
