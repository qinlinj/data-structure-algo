package org.qinlinj.nonlinear.highlevel.heap;

public class DataStreamTest {
    public static void main(String[] args) {
        DataStreamMaxHeapImplementation dataStream = new DataStreamMaxHeapImplementation();
        dataStream.add(3);
        System.out.println(dataStream.removeMax()); // 3
        dataStream.add(6);
        dataStream.add(5);
        System.out.println(dataStream.removeMax()); // 6
        dataStream.add(2);
        dataStream.add(1);
        System.out.println(dataStream.removeMax()); // 5
    }
}