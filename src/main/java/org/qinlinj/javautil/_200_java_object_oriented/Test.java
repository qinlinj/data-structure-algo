package org.qinlinj.javautil._200_java_object_oriented;

public class Test {
    public static void main(String[] args) {
        Employee b = new Employee("jus", 30);
        Employee c = b;
        c.setName("chel");
        Employee d = b;
        d.setAge(40);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }
}
