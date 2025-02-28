package org.qinlinj.nonlinear.highlevel.heap;

import java.util.Random;

public class MaxHeapTest {
    public static void main(String[] args) {
        int n = 10000;
        Random random = new Random();

        MaxHeap<Integer> heap = new MaxHeap<>();

        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt());
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = heap.removeMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new RuntimeException("Error");
            }
        }

        System.out.println("Test MaxHeap Success");
    }
}
