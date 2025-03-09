package org.qinlinj.linear.algo.sort;

/**
 * A utility class that provides methods for swapping elements in arrays.
 */
public class Sorter {
    /**
     * Swaps two elements in a generic array.
     *
     * @param <T>  The type of elements in the array
     * @param data The array containing elements to swap
     * @param i    Index of the first element
     * @param j    Index of the second element
     */
    public <T> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * Swaps two elements in an Integer array.
     *
     * @param data The Integer array containing elements to swap
     * @param i    Index of the first element
     * @param j    Index of the second element
     */
    public void swap(Integer[] data, int i, int j) {
        Integer temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * Swaps two elements in a primitive int array.
     *
     * @param data The int array containing elements to swap
     * @param i    Index of the first element
     * @param j    Index of the second element
     */
    public void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
