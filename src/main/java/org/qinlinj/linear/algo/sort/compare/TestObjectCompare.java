package org.qinlinj.linear.algo.sort.compare;

import java.util.Comparator;

public class TestObjectCompare {
    public static void main(String[] args) {
        Person p1 = new Person("jus", 40);
        Person p2 = new Person("chel", 30);
        Comparator<Person> comparator = ((o1, o2) -> o1.getAge() - o2.getAge());
        int res = comparator.compare(p1, p2);
        System.out.println(res);
    }
}
