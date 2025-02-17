package org.qinlinj.linear.algo.sort;

import org.qinlinj.linear.algo.sort.compare.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class JavaSorter {
    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        Arrays.sort(data); // general sort
        System.out.println(Arrays.toString(data));

        Person p1 = new Person("jus", 40);
        Person p2 = new Person("chel", 30);
        Person p3 = new Person("guo", 32);
        Person p4 = new Person("song", 33);
        Person[] people = new Person[]{p1, p2, p3, p4};
        //Arrays.sort(people);
//        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        Comparator<Person> comparator = (new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        //Arrays.sort(people, comparator);


        // insertion sort in small size of dataset
        // merge sort in big size of dataset
        //System.out.println(Arrays.toString(people));

        ArrayList<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        // Arrays.sort

        // using comparator
        Collections.sort(list, comparator);

        // using compareTo
        Collections.sort(list);
        System.out.println(list);
    }
}
