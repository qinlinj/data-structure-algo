package org.qinlinj.javautil._200_java_object_oriented;

public class Employee {

    String name;
    int age;

    public Employee() {
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.setName("jus");
        e1.setAge(30);
        System.out.println(e1);
        
        Employee e2 = new Employee("chel", 40);
        System.out.println(e2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}