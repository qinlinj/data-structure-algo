package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

public class RadixSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{2, 3, 6, 1, 34, 11, 53, 6, 22, 12};
        RadixSorter sorter = new RadixSorter();
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }

    public void sort(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        // get sort times
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            max = Math.max(max, data[i]);
        }
        int times = 0;
        while (max >= 1) {
            max = max / 10;
            times++;
        }

        for (int i = 0; i < times; i++) {
            int[] countArray = new int[10];
            for (int m = 0; m < data.length; m++) {
                int pos = (int) (data[m] / (Math.pow(10, i)) % 10);
                countArray[pos]++;
            }
            // calculate the index of each element
            for (int j = 1; j < countArray.length; j++) {
                countArray[j] += countArray[j - 1];
            }
            int tmp[] = new int[data.length];
            // get sorted array
            for (int k = data.length - 1; k >= 0; k--) {
                tmp[--countArray[(int) (data[k] / (Math.pow(10, i)) % 10)]] = data[k];
            }
            // array copy
            System.arraycopy(tmp, 0, data, 0, data.length);
        }
    }

    // --------------------------------------
    // support negative number
    public class NegativeRadixSorter {
        public void sort(int[] data) {
            if (data == null || data.length <= 1) return;

            int min = data[0];
            int max = data[0];
            for (int i = 1; i < data.length; i++) {
                min = Math.min(min, data[i]);
                max = Math.max(max, data[i]);
            }

            int shift = min < 0 ? -min : 0;
            if (shift > 0) {
                for (int i = 0; i < data.length; i++) {
                    data[i] += shift;
                }
                max += shift;
            }

            for (int exp = 1; max / exp > 0; exp *= 10) {
                countSort(data, exp);
            }

            if (shift > 0) {
                for (int i = 0; i < data.length; i++) {
                    data[i] -= shift;
                }
            }
        }

        private void countSort(int[] data, int exp) {
            int[] count = new int[10];
            for (int i = 0; i < data.length; i++) {
                int digit = (data[i] / exp) % 10;
                count[digit]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            int[] output = new int[data.length];
            for (int i = data.length - 1; i >= 0; i--) {
                int digit = (data[i] / exp) % 10;
                int k = count[digit] - 1;
                output[k] = data[i];
                count[digit]--;
            }

            for (int i = 0; i < data.length; i++) {
                data[i] = output[i];
            }
        }
    }
}
