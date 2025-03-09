package org.qinlinj.nonlinear.highlevel.heap;

/**
 * Test class for demonstrating the DataStreamMaxHeapImplementation functionality.
 */
public class DataStreamTest {
    /**
     * Main method to run the demonstration.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new data stream with max heap implementation
        DataStreamMaxHeapImplementation dataStream = new DataStreamMaxHeapImplementation();

        // Add value 3 to the stream
        dataStream.add(3);

        // Remove and print the maximum value (should be 3)
        System.out.println(dataStream.removeMax()); // 3

        // Add values 6 and 5 to the stream
        dataStream.add(6);
        dataStream.add(5);

        // Remove and print the maximum value (should be 6)
        System.out.println(dataStream.removeMax()); // 6

        // Add values 2 and 1 to the stream
        dataStream.add(2);
        dataStream.add(1);

        // Remove and print the maximum value (should be 5)
        System.out.println(dataStream.removeMax()); // 5
    }
}