package org.qinlinj.linear.algo.sort.compare;

import org.qinlinj.linear.algo.sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;

public class ThreeWayQuickSorter<E extends Comparable<E>> extends Sorter {
    static Comparator<Person> comparator = new Comparator<Person>() {
        public int compare(Person p1, Person p2) {
            return p1.getAge() - p2.getAge();
        }
    };

    public static void main(String[] args) {
        Person p1 = new Person("jus", 40);
        Person p2 = new Person("chel", 30);
        Person p3 = new Person("guo", 32);
        Person p4 = new Person("song", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};
//        Comparator<Person> comparator = ((o1, o2) -> o2.getAge() - o1.getAge());
        new ThreeWayQuickSorter().sort(people, comparator);
        System.out.println(Arrays.toString(people));
    }

    // compareTo
    public void sort(E[] data) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1);
    }

    // --------------------------------------------
    // comparator

    private void sort(E[] data, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        swap(data, mid, hi);
        E pivot = data[hi];

        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            if (data[i].compareTo(pivot) < 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i].compareTo(pivot) > 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(data, lo, less - 1);
        sort(data, great + 1, hi);
    }

    public void sort(E[] data, Comparator comparator) {
        if (data == null || data.length <= 1) return;
        sort(data, 0, data.length - 1);
    }

    private void sort(E[] data, int lo, int hi, Comparator comparator) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        swap(data, mid, hi);
        E pivot = data[hi];

        int less = lo;
        int great = hi;

        int i = lo;
        while (i <= great) {
            if (data[i].compareTo(pivot) < 0) {
                swap(data, i, less);
                less++;
                i++;
            } else if (data[i].compareTo(pivot) > 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }

        sort(data, lo, less - 1, comparator);
        sort(data, great + 1, hi, comparator);
    }

}
