package org.qinlinj.nonlinear.tree.segmenttree;

// @formatter:off
/**
 * Segment Tree Implementation with Lazy Propagation
 *
 * CONCEPT AND PRINCIPLES:
 * A Segment Tree is a tree data structure used for storing information about intervals or segments.
 * It allows querying which segments contain a given point and updating segments in logarithmic time.
 *
 * Key advantages of Segment Trees:
 * 1. Efficient range queries - O(log n) time complexity
 * 2. Efficient range updates - O(log n) with lazy propagation
 * 3. Versatile - can be adapted for various problems (sum, min, max, gcd, etc.)
 * 4. Space efficient - requires O(n) space for n elements
 *
 * Basic operations:
 * - Build: Construct the segment tree from an array - O(n)
 * - Query: Find result for a range [l, r] - O(log n)
 * - Update: Update a single element or a range - O(log n)
 *
 * Lazy Propagation:
 * This optimization delays the propagation of updates to children nodes until necessary.
 * Without lazy propagation, updating a range would require O(n log n) time.
 * With lazy propagation, range updates can be performed in O(log n) time.
 */
public class SegmentTree {
    // The array that stores the segment tree nodes
    private int[] tree;

    // The array that stores lazy updates
    private int[] lazy;

    // The original array
    private int[] array;

    // Size of the original array
    private int n;

    /**
     * Constructor to initialize the segment tree with given array
     * Time Complexity: O(n) where n is the size of the input array
     *
     * @param arr the input array
     */
    public SegmentTree(int[] arr) {
        this.array = arr;
        this.n = arr.length;

        // Height of segment tree
        int height = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        // Maximum size of segment tree
        int maxSize = 2 * (int) Math.pow(2, height) - 1;

        // Allocate memory for segment tree and lazy tree
        tree = new int[maxSize];
        lazy = new int[maxSize];

        // Build the segment tree
        build(0, 0, n - 1);
    }

    /**
     * A recursive function that builds the segment tree
     * Time Complexity: O(n) where n is the size of the input array
     *
     * @param node current node index in the segment tree
     * @param start start index of the segment represented by current node
     * @param end end index of the segment represented by current node
     *
     * Example: For array [1, 3, 5, 7, 9, 11]
     * Initial call: build(0, 0, 5)
     *
     * Tree construction visualization:
     *
     *                  [0-5]:36
     *                 /       \
     *         [0-2]:9         [3-5]:27
     *         /     \         /     \
     *    [0-1]:4   [2]:5  [3-4]:16  [5]:11
     *    /    \           /    \
     * [0]:1  [1]:3     [3]:7  [4]:9
     */
    private void build(int node, int start, int end) {
        // If leaf node, store the value from original array
        if (start == end) {
            tree[node] = array[start];
            return;
        }

        // Otherwise, recursively build left and right children
        int mid = (start + end) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);

        // Internal node stores the sum of its children
        tree[node] = tree[leftChild] + tree[rightChild];

        /*
         * Current state of tree for example array [1, 3, 5, 7, 9, 11]:
         * tree[0] = 36 (sum of entire array)
         * tree[1] = 9 (sum of [1, 3, 5])
         * tree[2] = 27 (sum of [7, 9, 11])
         * tree[3] = 4 (sum of [1, 3])
         * tree[4] = 5 (sum of [5])
         * tree[5] = 16 (sum of [7, 9])
         * tree[6] = 11 (sum of [11])
         * tree[7] = 1 (value of [1])
         * tree[8] = 3 (value of [3])
         * ... and so on
         */
    }

    /**
     * Pushes lazy updates from current node to its children
     * Time Complexity: O(1)
     *
     * @param node current node index in the segment tree
     * @param start start index of the segment represented by current node
     * @param end end index of the segment represented by current node
     *
     * Example: If we had a lazy update of +2 at node 1 (representing range [0-2])
     * Before propagation:
     *         [0-2]:9(+2)    <-- node with pending lazy update
     *         /     \
     *    [0-1]:4   [2]:5     <-- children don't have the update yet
     *
     * After propagation:
     *         [0-2]:15       <-- node updated (9 + 2*3 = 15)
     *         /     \
     *    [0-1]:4(+2) [2]:5(+2) <-- lazy update pushed to children
     */
    private void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            // Update current node with lazy value
            // Note: multiply by (end-start+1) as this is the number of elements in this range
            tree[node] += lazy[node] * (end - start + 1);

            // If not leaf node, propagate lazy update to children
            if (start != end) {
                int leftChild = 2 * node + 1;
                int rightChild = 2 * node + 2;

                lazy[leftChild] += lazy[node];
                lazy[rightChild] += lazy[node];
            }

            // Reset lazy value for current node
            lazy[node] = 0;
        }
    }

    /**
     * Updates a range of elements in the segment tree
     * Time Complexity: O(log n)
     *
     * @param l left bound of the range to update
     * @param r right bound of the range to update
     * @param val value to add to each element in the range
     */
    public void updateRange(int l, int r, int val) {
        updateRange(0, 0, n - 1, l, r, val);
    }

    /**
     * Helper method to update a range of elements in the segment tree
     * Time Complexity: O(log n)
     *
     * @param node current node index in the segment tree
     * @param start start index of the segment represented by current node
     * @param end end index of the segment represented by current node
     * @param l left bound of the update range
     * @param r right bound of the update range
     * @param val value to add to each element in the range
     *
     * Example: For array [1, 3, 5, 7, 9, 11], updating range [1-3] with +2
     * Initial call: updateRange(0, 0, 5, 1, 3, 2)
     *
     * Before update:
     *                  [0-5]:36
     *                 /       \
     *         [0-2]:9         [3-5]:27
     *         /     \         /     \
     *    [0-1]:4   [2]:5  [3-4]:16  [5]:11
     *    /    \           /    \
     * [0]:1  [1]:3     [3]:7  [4]:9
     *
     * After update:
     *                  [0-5]:42 (+6)
     *                 /       \
     *         [0-2]:13 (+4)    [3-5]:29 (+2)
     *         /     \          /     \
     *    [0-1]:6 (+2) [2]:7 (+2) [3-4]:18 (+2) [5]:11
     *    /    \               /    \
     * [0]:1  [1]:5 (+2)     [3]:9 (+2) [4]:9
     *
     * Note: The nodes that fully contain [1-3] are updated with lazy propagation
     */
    private void updateRange(int node, int start, int end, int l, int r, int val) {
        // First, push any pending lazy updates
        pushDown(node, start, end);

        // If current segment is outside the update range, return
        if (start > r || end < l) {
            return;
        }

        // If current segment is fully within the update range
        if (start >= l && end <= r) {
            // Update this node and mark children for lazy update
            tree[node] += val * (end - start + 1);

            // If not leaf node, set lazy values for children
            if (start != end) {
                lazy[2 * node + 1] += val;
                lazy[2 * node + 2] += val;
            }
            return;
        }

        // If current segment is partially overlapped, recurse to children
        int mid = (start + end) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        updateRange(leftChild, start, mid, l, r, val);
        updateRange(rightChild, mid + 1, end, l, r, val);

        // Re-compute value for this node after children updates
        tree[node] = tree[leftChild] + tree[rightChild];
    }

    /**
     * Queries the sum in a given range
     * Time Complexity: O(log n)
     *
     * @param l left bound of the query range
     * @param r right bound of the query range
     * @return sum of elements in range [l, r]
     */
    public int queryRange(int l, int r) {
        return queryRange(0, 0, n - 1, l, r);
    }

    /**
     * Helper method to query the sum in a given range
     * Time Complexity: O(log n)
     *
     * @param node current node index in the segment tree
     * @param start start index of the segment represented by current node
     * @param end end index of the segment represented by current node
     * @param l left bound of the query range
     * @param r right bound of the query range
     * @return sum of elements in range [l, r]
     *
     * Example: For array [1, 3, 5, 7, 9, 11], querying range [2-4]
     * Initial call: queryRange(0, 0, 5, 2, 4)
     *
     * Let's assume we've done the update from previous example, so tree is:
     *                  [0-5]:42
     *                 /       \
     *         [0-2]:13        [3-5]:29
     *         /     \         /     \
     *    [0-1]:6   [2]:7  [3-4]:18  [5]:11
     *    /    \           /    \
     * [0]:1  [1]:5     [3]:9  [4]:9
     *
     * Query process:
     * 1. Check node 0 [0-5]: Partial overlap, recurse to children
     * 2. Check node 1 [0-2]: Partial overlap, recurse to children
     * 3. Check node 3 [0-1]: No overlap, return 0
     * 4. Check node 4 [2]: Full overlap, return 7
     * 5. Check node 2 [3-5]: Partial overlap, recurse to children
     * 6. Check node 5 [3-4]: Full overlap, return 18
     * 7. Check node 6 [5]: No overlap, return 0
     * 8. Sum results: 0 + 7 + 18 + 0 = 25
     */
    private int queryRange(int node, int start, int end, int l, int r) {
        // First, push any pending lazy updates
        pushDown(node, start, end);

        // If current segment is outside the query range, return 0
        if (start > r || end < l) {
            return 0;
        }

        // If current segment is fully within the query range
        if (start >= l && end <= r) {
            return tree[node];
        }

        // If current segment is partially overlapped, recurse to children
        int mid = (start + end) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        int leftSum = queryRange(leftChild, start, mid, l, r);
        int rightSum = queryRange(rightChild, mid + 1, end, l, r);

        return leftSum + rightSum;
    }

    /**
     * Updates a single element in the array
     * Time Complexity: O(log n)
     *
     * @param index index of the element to update
     * @param val new value to set
     */
    public void update(int index, int val) {
        // Calculate the difference
        int diff = val - array[index];

        // Update the array
        array[index] = val;

        // Update the segment tree
        updateRange(index, index, diff);
    }

    /**
     * Utility method to print the segment tree
     * Useful for debugging
     */
    public void printTreeSimple() {
        System.out.println("Segment Tree Structure:");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != 0) {
                System.out.println("Node " + i + ": " + tree[i] + (lazy[i] != 0 ? " (Lazy: " + lazy[i] + ")" : ""));
            }
        }
    }

    /**
     * Utility method to print the segment tree in a visual tree-like structure
     * Makes it easier to understand the shape and values of the tree
     */
    public void printTree() {
        System.out.println("Segment Tree Visual Structure:");

        // Get the height of the tree
        int height = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int maxHeight = height + 1; // Include leaf level

        // Create a 2D character array to represent the tree
        int maxWidth = 80; // Fixed width for better alignment
        String[][] treeArray = new String[maxHeight * 2][maxWidth];

        // Initialize the array with spaces
        for (int i = 0; i < treeArray.length; i++) {
            treeArray[i] = new String[maxWidth];
            for (int j = 0; j < maxWidth; j++) {
                treeArray[i][j] = " ";
            }
        }

        // Fill the array with the tree structure
        fillTreeArray(0, 0, n - 1, 0, 0, maxWidth - 1, treeArray);

        // Print the array
        for (String[] row : treeArray) {
            boolean nonEmpty = false;
            for (String cell : row) {
                if (!cell.equals(" ")) {
                    nonEmpty = true;
                    break;
                }
            }

            if (nonEmpty) {
                StringBuilder rowStr = new StringBuilder();
                for (String cell : row) {
                    rowStr.append(cell);
                }
                System.out.println(rowStr.toString().replaceAll("\\s+$", ""));
            }
        }
    }

    /**
     * Helper method to fill the 2D array with the tree structure
     *
     * @param node current node index in the segment tree
     * @param start start index of the segment represented by current node
     * @param end end index of the segment represented by current node
     * @param level current level in the tree
     * @param left left boundary of the current node's position
     * @param right right boundary of the current node's position
     * @param treeArray the 2D array to fill
     */
    private void fillTreeArray(int node, int start, int end, int level, int left, int right, String[][] treeArray) {
        if (node >= tree.length || left > right) {
            return;
        }

        int mid = (left + right) / 2;

        // Create node string with range and value
        String nodeStr;
        if (lazy[node] != 0) {
            nodeStr = String.format("[%d-%d]:%d(+%d)", start, end, tree[node], lazy[node]);
        } else {
            nodeStr = String.format("[%d-%d]:%d", start, end, tree[node]);
        }

        // Calculate position to place the node string
        int pos = mid - nodeStr.length() / 2;
        for (int i = 0; i < nodeStr.length(); i++) {
            if (pos + i < treeArray[level].length) {
                treeArray[level][pos + i] = String.valueOf(nodeStr.charAt(i));
            }
        }

        // If not a leaf node, add connecting lines and process children
        if (start != end) {
            int segMid = (start + end) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;

            // Calculate branch positions with better spacing
            int leftBranchPos = (left + mid - nodeStr.length()/4) / 2;
            int rightBranchPos = (mid + nodeStr.length()/4 + right) / 2;

            // Add connecting characters
            treeArray[level + 1][leftBranchPos] = "/";
            treeArray[level + 1][rightBranchPos] = "\\";

            // Process children with adjusted spacing
            int childWidth = (right - left) / 2;
            fillTreeArray(leftChild, start, segMid, level + 2, left, mid - 1, treeArray);
            fillTreeArray(rightChild, segMid + 1, end, level + 2, mid + 1, right, treeArray);
        }
    }
}