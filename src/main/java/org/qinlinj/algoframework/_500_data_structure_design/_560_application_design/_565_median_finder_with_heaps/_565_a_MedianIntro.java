package org.qinlinj.algoframework._500_data_structure_design._560_application_design._565_median_finder_with_heaps;

/**
 * FINDING THE MEDIAN
 * <p>
 * This class introduces the concept of finding the median in different scenarios.
 * <p>
 * Key concepts:
 * 1. The median is the middle value in a sorted array of numbers.
 * 2. For arrays with odd length, the median is the single middle element.
 * 3. For arrays with even length, the median is the average of the two middle elements.
 * 4. For large datasets, probabilistic approaches can be used by sampling a portion of data.
 * 5. Finding the median in a data stream presents unique challenges that require specialized data structures.
 * <p>
 * The median finding algorithm discussed in this example is from LeetCode problem 295 - "Find Median from Data Stream".
 */
public class _565_a_MedianIntro {

    /**
     * Simple demonstration of finding the median in a static array
     */
    public static double findMedianInStaticArray(int[] nums) {
        // First sort the array (this example uses a simple bubble sort for clarity)
        sortArray(nums);

        int n = nums.length;

        // If the array has odd length, return the middle element
        if (n % 2 == 1) {
            return nums[n / 2];
        }
        // If the array has even length, return the average of the two middle elements
        else {
            return (nums[n / 2 - 1] + nums[n / 2]) / 2.0;
        }
    }

    /**
     * Simple bubble sort implementation for demonstration
     */
    private static void sortArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    // Swap elements
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Demonstration of finding approximate median using sampling (probabilistic approach)
     */
    public static double findApproximateMedian(int[] largeDataset, double sampleRatio) {
        // Determine sample size
        int sampleSize = (int) (largeDataset.length * sampleRatio);
        int[] sample = new int[sampleSize];

        // Randomly sample elements from the dataset
        for (int i = 0; i < sampleSize; i++) {
            int randomIndex = (int) (Math.random() * largeDataset.length);
            sample[i] = largeDataset[randomIndex];
        }

        // Find median in the sample
        return findMedianInStaticArray(sample);
    }

    public static void main(String[] args) {
        // Example of finding median in a static array
        int[] nums1 = {2, 3, 4};
        System.out.println("Median of [2,3,4]: " + findMedianInStaticArray(nums1)); // Should output 3.0

        int[] nums2 = {2, 3};
        System.out.println("Median of [2,3]: " + findMedianInStaticArray(nums2)); // Should output 2.5

        // For dynamic data streams, see the MedianFinder class implementation
        System.out.println("For finding median in a data stream, use the MedianFinder class.");
    }
}