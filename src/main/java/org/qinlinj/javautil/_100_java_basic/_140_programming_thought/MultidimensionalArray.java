package org.qinlinj.javautil._100_java_basic._140_programming_thought;

public class MultidimensionalArray {
    public static void main(String[] args) {
        int[][] a = {{1, 2}, {3, 4, 5}};

        for (int[] x : a) {

            for (int y : x) {
                System.out.println(y);
            }
        }

        //System.out.println(a[1][0]);

        int[][][] b = {{{1, 2}}, {{3}, {4}}};

        for (int[][] t : b) {
            for (int[] f : t) {
                for (int k : f) {
                    System.out.println(k);
                }
            }
        }

        //System.out.println(b[1][0][0]);
    }
}
