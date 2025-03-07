package org.qinlinj.linear.algo.sort;

import org.qinlinj.linear.algo.sort.compare.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Java Built-in Sorting Algorithms Principle:
 * <p>
 * Java provides several built-in sorting methods through Arrays.sort() and Collections.sort().
 * These methods use different sorting algorithms depending on the data type and size:
 * <p>
 * 1. For primitive types (int, float, etc.):
 * - Uses Dual-Pivot Quicksort (since Java 7)
 * - Time complexity: O(n log n) on average, O(n²) worst case
 * <p>
 * 2. For object types (Objects that implement Comparable):
 * - Uses TimSort, a hybrid of Merge Sort and Insertion Sort
 * - TimSort is adaptive: uses Insertion Sort for small arrays or pre-sorted portions
 * - Uses Merge Sort for larger arrays
 * - Time complexity: O(n log n) worst case, can be O(n) for nearly sorted arrays
 * <p>
 * 3. Stability:
 * - Primitive array sorting is NOT stable
 * - Object array and Collections sorting IS stable
 * <p>
 * Two ways to define order for objects:
 * a) Implement Comparable interface (natural ordering)
 * b) Provide a Comparator (custom ordering)
 */
public class JavaSorter {
    public static void main(String[] args) {
        // Example 1: Sorting primitive int array
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        Arrays.sort(data); // Uses Dual-Pivot Quicksort
        System.out.println(Arrays.toString(data));

        // Example state changes:
        // Initial: [34, 33, 12, 78, 21, 1, 98, 100]
        // After sort: [1, 12, 21, 33, 34, 78, 98, 100]

        // Example 2: Create Person objects for sorting
        Person p1 = new Person("jus", 40);
        Person p2 = new Person("chel", 30);
        Person p3 = new Person("guo", 32);
        Person p4 = new Person("song", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};

        // The following line is commented out because it would throw an exception
        // if Person doesn't implement Comparable interface
        //Arrays.sort(people);

        // Example 3: Define a Comparator using anonymous class
        // This comparator compares Person objects based on their age
        Comparator<Person> comparator = (new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // Example 4: Alternative lambda syntax for Comparator (commented out)
        // Shorter, more modern syntax for creating the same comparator
        // Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());

        // The following line is commented out but would sort the people array by age
        // using the custom comparator
        //Arrays.sort(people, comparator);

        // Notes on Arrays.sort implementation:
        // - For small arrays (< ~7 elements): Uses Insertion Sort
        // - For larger arrays: Uses TimSort (a hybrid of Merge Sort and Insertion Sort)
        // System.out.println(Arrays.toString(people));

        // Example 5: Create an ArrayList of Person objects
        ArrayList<Person> list = new ArrayList<>();
        list.add(p1); // jus, 40
        list.add(p2); // chel, 30
        list.add(p3); // guo, 32
        list.add(p4); // song, 33

        // Initial list state: [Person{name=jus, age=40}, Person{name=chel, age=30},
        //                      Person{name=guo, age=32}, Person{name=song, age=33}]

        // Example 6: Sort ArrayList using custom comparator
        Collections.sort(list, comparator);
        // After sort by age:  [Person{name=chel, age=30}, Person{name=guo, age=32},
        //                      Person{name=song, age=33}, Person{name=jus, age=40}]

        // Example 7: Sort ArrayList using the natural ordering defined by compareTo
        // This requires Person class to implement Comparable<Person>
        Collections.sort(list);
        // Assuming Person implements Comparable to sort by name:
        // After sort by name: [Person{name=chel, age=30}, Person{name=guo, age=32},
        //                      Person{name=jus, age=40}, Person{name=song, age=33}]

        System.out.println(list);
    }
}