/**
 * Binary Search Algorithm Framework
 * <p>
 * This class provides a basic framework for binary search algorithms.
 * <p>
 * Key points:
 * 1. Binary search is an efficient O(log n) algorithm for finding elements in sorted arrays
 * 2. To avoid overflow when calculating mid index: use left + (right - left) / 2 instead of (left + right) / 2
 * 3. Using else-if structure instead of else makes the code logic clearer
 * 4. Understanding search space boundaries (closed vs open intervals) is crucial
 * 5. Search interval representation:
 * - [left, right] (closed on both sides)
 * - [left, right) (closed on left, open on right)
 * <p>
 * This framework class provides the structure for three main types of binary search:
 * - Standard binary search (find any occurrence)
 * - Left boundary search (find leftmost occurrence)
 * - Right boundary search (find rightmost occurrence)
 */
public class _334_a_BinarySearchFramework {

    /**
     * Main method to test the binary search implementation
     */
    public static void main(String[] args) {
        _334_a_BinarySearchFramework bs = new _334_a_BinarySearchFramework();

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 6;

        int result = bs.binarySearch(nums, target);

        System.out.println("Target " + target + " found at index: " + result);
    }

    /**
     * Binary search framework method (template)
     *
     * @param nums   sorted array to search in
     * @param target element to find
     * @return index of target if found, -1 otherwise
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;  // Note: This defines a closed interval [left, right]

        while (left <= right) {  // Note: <= for closed interval
            int mid = left + (right - left) / 2;  // Avoid overflow

            if (nums[mid] == target) {
                // Target found, specific action depends on search type
                return mid;  // For standard search, return immediately
            } else if (nums[mid] < target) {
                // Target is on the right side
                left = mid + 1;
            } else if (nums[mid] > target) {
                // Target is on the left side
                right = mid - 1;
            }
        }

        // Target not found
        return -1;
    }
}