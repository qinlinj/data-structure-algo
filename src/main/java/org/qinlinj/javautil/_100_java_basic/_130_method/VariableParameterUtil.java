package org.qinlinj.javautil._100_java_basic._130_method;

public class VariableParameterUtil {
    // The essence of variable parameters is the array int[]
    static void m1(int... arr) {
        for (int a : arr) {
            System.out.println(a);
        }
    }

    // The essence of variable parameters is the array int[]
    static void m2(int a, char c, String... arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

        // Variable parameters: Data type... Parameter name
        // If a variable parameter is used together with other parameters, the variable parameter can only be the last parameter of the method
        // Method name (Parameter List)
        // Parameter Precautions: The number of parameters, parameter types, and parameter sequences must all be exactly the same.
        // Problem: The number of parameters in the requirement is not fixed and can be solved by using variable parameters

        m1(1, 2, 3, 4, 5, 6);
        m2(100, 'a', "1", "abc", "xyz");

    }
}
