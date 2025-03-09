package org.qinlinj.nonlinear.tree.btree;

import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * B-Tree Implementation
 * =====================
 *
 * CONCEPT AND PRINCIPLES:
 * A B-tree is a self-balancing tree data structure that maintains sorted data and allows
 * searches, sequential access, insertions, and deletions in logarithmic time. Unlike binary trees,
 * a B-tree node can have more than two children. It is specifically designed to work well with
 * disk-based storage systems and databases where the data is too large to fit in memory.
 *
 * Key Properties:
 * 1. All leaf nodes are at the same depth (perfect balance)
 * 2. Every node has between t-1 and 2t-1 keys (except root, which may have fewer)
 * 3. A node with k keys has exactly k+1 children (if it's not a leaf)
 * 4. Keys within a node are sorted in ascending order
 * 5. Parameter t is called the "minimum degree" of the B-tree
 *
 * VISUALIZATION OF A B-TREE (t=3):
 *                   [10, 20]
 *                  /    |    \
 *         [3, 5, 7]  [15, 17]  [25, 30, 35]
 *
 * ADVANTAGES:
 * 1. Guarantees logarithmic-time operations regardless of data distribution
 * 2. Minimizes disk I/O operations by having high branching factor
 * 3. Maintains balance automatically during insertions and deletions
 * 4. Excellent for storing large datasets that don't fit in memory
 * 5. Optimized for systems with slow disk access (databases, file systems)
 * 6. Keeps related keys in the same node, improving locality of reference
 * 7. Very good space utilization compared to many other balanced trees
 *
 * APPLICATIONS:
 * - Database indexing (most DBMS implementations use B+ trees, a variant)
 * - File systems (NTFS, HFS+, ext4)
 * - Key-value stores
 * - In-memory dictionaries requiring guaranteed worst-case performance
 */
public class BTree<K extends Comparable<K>> {
    // Minimum degree - controls how many keys can be stored in a node
    // A node can have between t-1 and 2t-1 keys
    private int t;
    // Root node of the B-tree
    private Node root;

    /**
     * Constructs a B-tree with the specified minimum degree.
     *
     * Time Complexity: O(1)
     *
     * @param t the minimum degree of the B-tree (t ≥ 2)
     */
    public BTree(int t) {
        if (t < 2) {
            throw new IllegalArgumentException("Minimum degree must be at least 2");
        }
        this.t = t;
        this.root = new Node(true);
    }

    /**
     * Searches for a key in the B-tree.
     *
     * Time Complexity: O(log n) where n is the number of keys
     *
     * @param key the key to search for
     * @return true if the key is found, false otherwise
     */
    public boolean search(K key) {
        return search(root, key) != null;
    }

    /**
     * Recursive helper method to search for a key in a subtree.
     *
     * Time Complexity: O(log n)
     *
     * EXAMPLE:
     * Searching for key 17 in this B-tree (t=3):
     *                   [10, 20]
     *                  /    |    \
     *         [3, 5, 7]  [15, 17]  [25, 30, 35]
     *
     * Step 1: Start at root [10, 20]
     * Step 2: 17 is between 10 and 20, so follow middle child pointer
     * Step 3: Examine node [15, 17]
     * Step 4: Find 17 in this node, return true
     *
     * @param node the root of the subtree to search
     * @param key the key to search for
     * @return the node containing the key, or null if not found
     */
    private Node search(Node node, K key) {
        // Find the first key greater than or equal to k
        int i = 0;
        while (i < node.n && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }

        // If the found key is equal to k, return this node
        if (i < node.n && key.compareTo(node.keys.get(i)) == 0) {
            return node;
        }

        // If key is not found and this is a leaf node
        if (node.isLeaf) {
            return null;
        }

        // Recur to the appropriate child
        return search(node.children.get(i), key);
    }

    /**
     * Inserts a key into the B-tree.
     *
     * Time Complexity: O(log n) where n is the number of keys
     *
     * @param key the key to insert
     */
    public void insert(K key) {
        // If root is full, the tree grows in height
        if (root.n == 2 * t - 1) {
            // Create a new root
            Node newRoot = new Node(false);

            // Make old root as a child of new root
            newRoot.children.add(root);

            // Split the old root and move one key up
            splitChild(newRoot, 0);

            // New root has two children now. Decide which child gets the new key
            root = newRoot;
        }

        // Insert non-full case
        insertNonFull(root, key);
    }

    /**
     * Helper method to split a full child of a node.
     *
     * Time Complexity: O(t) where t is the minimum degree
     *
     * EXAMPLE:
     * Splitting a full child in this B-tree (t=2):
     * Before:
     *         [20]
     *        /    \
     *   [5, 10, 15]  [25, 30]
     *
     * Splitting the left child:
     * Step 1: Create new node for right half [15]
     * Step 2: Move median key 10 up to parent
     *
     * After:
     *         [10, 20]
     *        /    |    \
     *      [5]   [15]  [25, 30]
     *
     * @param parent the parent node
     * @param index the index of the full child in parent's children array
     */
    private void splitChild(Node parent, int index) {
        // Get the full child
        Node fullChild = parent.children.get(index);

        // Create a new node which will store t-1 keys of the full child
        Node newChild = new Node(fullChild.isLeaf);

        // Copy the last t-1 keys of full child to new child
        for (int j = 0; j < t - 1; j++) {
            if (j + t < fullChild.keys.size()) {
                newChild.keys.add(fullChild.keys.get(j + t));
            } else {
                // Ensure we don't go out of bounds
                break;
            }
        }

        // If full child is not a leaf, copy the last t children too
        if (!fullChild.isLeaf) {
            for (int j = 0; j < t; j++) {
                if (j + t < fullChild.children.size()) {
                    newChild.children.add(fullChild.children.get(j + t));
                } else {
                    // Ensure we don't go out of bounds
                    break;
                }
            }
        }

        // Update the key count
        newChild.n = newChild.keys.size();

        // Insert the new child into parent's children list
        if (index + 1 < parent.children.size()) {
            parent.children.add(index + 1, newChild);
        } else {
            parent.children.add(newChild);
        }

        // Move the middle key of fullChild up to the parent
        K middleKey = null;
        if (t - 1 < fullChild.keys.size()) {
            middleKey = fullChild.keys.get(t - 1);
        } else {
            // Handle out of bounds case
            middleKey = fullChild.keys.get(fullChild.keys.size() - 1);
        }

        if (index < parent.keys.size()) {
            parent.keys.add(index, middleKey);
        } else {
            parent.keys.add(middleKey);
        }

        // Increment the key count in parent
        parent.n++;

        // Remove keys and children that have been moved
        // First, remove the middle key that went up
        if (t - 1 < fullChild.keys.size()) {
            fullChild.keys.remove(t - 1);
        }

        // Remove keys that have been moved to newChild
        while (fullChild.keys.size() > t - 1) {
            fullChild.keys.remove(fullChild.keys.size() - 1);
        }

        // Remove children that have been moved
        if (!fullChild.isLeaf) {
            while (fullChild.children.size() > t) {
                fullChild.children.remove(fullChild.children.size() - 1);
            }
        }

        // Update key count
        fullChild.n = fullChild.keys.size();
    }

    /**
     * Inserts a key into a non-full node.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * EXAMPLE:
     * Inserting key 12 into a non-full node in this B-tree (t=2):
     * Before:
     *         [10, 20]
     *        /    |    \
     *      [5]   [15]  [25, 30]
     *
     * Step 1: Start at root [10, 20]
     * Step 2: 12 is between 10 and 20, so go to middle child [15]
     * Step 3: 12 < 15, and there's no child to go to (it's a leaf)
     * Step 4: Insert 12 into the beginning of the node
     *
     * After:
     *         [10, 20]
     *        /    |    \
     *      [5]  [12, 15]  [25, 30]
     *
     * @param node the non-full node to insert the key into
     * @param key the key to insert
     */
    private void insertNonFull(Node node, K key) {
        // Initialize index as rightmost element
        int i = node.n - 1;

        // If this is a leaf node
        if (node.isLeaf) {
            // Find position to insert and move all greater keys one step right
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }

            // Check if key already exists
            if (i >= 0 && key.compareTo(node.keys.get(i)) == 0) {
                return; // Key already exists, do nothing
            }

            // Insert the new key at the correct location
            node.keys.add(i + 1, key);
            node.n++;
        } else {
            // Find the child which is going to have the new key
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }

            // Check if key already exists
            if (i >= 0 && key.compareTo(node.keys.get(i)) == 0) {
                return; // Key already exists, do nothing
            }

            i++;

            // Check if the child is full
            if (node.children.get(i).n == 2 * t - 1) {
                // Split the child
                splitChild(node, i);

                // After split, the middle key of the child moves up
                // Decide which child will receive the new key
                if (key.compareTo(node.keys.get(i)) > 0) {
                    i++;
                }
            }

            // Recursively insert the key
            insertNonFull(node.children.get(i), key);
        }
    }

    /**
     * Removes a key from the B-tree.
     *
     * Time Complexity: O(log n) where n is the number of keys
     *
     * @param key the key to remove
     * @return true if the key was found and removed, false otherwise
     */
    public boolean remove(K key) {
        if (root.n == 0) {
            return false; // Empty tree
        }

        boolean result = remove(root, key);

        // If the root has no keys and has a child, make the child the new root
        if (root.n == 0 && !root.isLeaf) {
            root = root.children.get(0);
        }

        return result;
    }

    /**
     * Recursive helper method to remove a key from a subtree.
     *
     * Time Complexity: O(log n)
     *
     * EXAMPLE:
     * Removing key 15 from this B-tree (t=2):
     * Before:
     *         [10, 20]
     *        /    |    \
     *      [5]  [15]  [25, 30]
     *
     * Step 1: Find 15 in the middle child
     * Step 2: Since it's a leaf and has only one key, remove it directly
     *
     * After:
     *         [10, 20]
     *        /    |    \
     *      [5]   []   [25, 30]
     *
     * But this creates an empty node, so need to either redistribute or merge:
     * After rebalancing:
     *         [20]
     *        /    \
     *     [5, 10]  [25, 30]
     *
     * @param node the root of the subtree
     * @param key the key to remove
     * @return true if the key was found and removed, false otherwise
     */
    private boolean remove(Node node, K key) {
        int idx = findKeyIndex(node, key);

        // Case 1: Key is in this node
        if (idx < node.n && key.compareTo(node.keys.get(idx)) == 0) {
            return removeFromNode(node, key, idx);
        }

        // Case 2: Key is not in this node
        if (node.isLeaf) {
            return false; // Key not found in tree
        }

        // Determine whether to go to the last child
        boolean isLastChild = (idx == node.n);

        // If the child where the key should be has less than t keys, fill it
        if (node.children.get(idx).n < t) {
            fillChild(node, idx);
        }

        // If the last child has been merged, recur to the (idx-1)th child
        if (isLastChild && idx > node.n) {
            return remove(node.children.get(idx - 1), key);
        } else {
            return remove(node.children.get(idx), key);
        }
    }

    /**
     * Finds the index of a key in a node's key array.
     *
     * Time Complexity: O(t) where t is the minimum degree
     *
     * @param node the node to search
     * @param key the key to find
     * @return the index of the first key >= the given key, or node.n if no such key exists
     */
    private int findKeyIndex(Node node, K key) {
        int idx = 0;
        while (idx < node.n && key.compareTo(node.keys.get(idx)) > 0) {
            idx++;
        }
        return idx;
    }

    /**
     * Removes a key from a node at the given index.
     *
     * Time Complexity: O(h) where h is the height of the tree
     *
     * @param node the node containing the key
     * @param key the key to remove
     * @param idx the index of the key in the node's key array
     * @return true if the key was removed
     */
    private boolean removeFromNode(Node node, K key, int idx) {
        // Case 1: If the node is a leaf, simply remove the key
        if (node.isLeaf) {
            node.keys.remove(idx);
            node.n--;
            return true;
        }

        // Case 2: If the node is not a leaf

        // Case 2a: Child preceding the key has at least t keys
        // Find the predecessor and replace the key with it
        if (node.children.get(idx).n >= t) {
            K pred = getPredecessor(node, idx);
            node.keys.set(idx, pred);
            return remove(node.children.get(idx), pred);
        }

        // Case 2b: Child after the key has at least t keys
        // Find the successor and replace the key with it
        if (node.children.get(idx + 1).n >= t) {
            K succ = getSuccessor(node, idx);
            node.keys.set(idx, succ);
            return remove(node.children.get(idx + 1), succ);
        }

        // Case 2c: Both children have t-1 keys
        // Merge the key and the right child into the left child
        merge(node, idx);
        return remove(node.children.get(idx), key);
    }

    /**
     * Gets the predecessor of the key at the given index.
     * The predecessor is the rightmost key in the subtree rooted at the left child.
     *
     * Time Complexity: O(log n)
     *
     * @param node the node containing the key
     * @param idx the index of the key
     * @return the predecessor key
     */
    private K getPredecessor(Node node, int idx) {
        Node curr = node.children.get(idx);
        while (!curr.isLeaf) {
            curr = curr.children.get(curr.n);
        }
        return curr.keys.get(curr.n - 1);
    }

    /**
     * Gets the successor of the key at the given index.
     * The successor is the leftmost key in the subtree rooted at the right child.
     *
     * Time Complexity: O(log n)
     *
     * @param node the node containing the key
     * @param idx the index of the key
     * @return the successor key
     */
    private K getSuccessor(Node node, int idx) {
        Node curr = node.children.get(idx + 1);
        while (!curr.isLeaf) {
            curr = curr.children.get(0);
        }
        return curr.keys.get(0);
    }

    /**
     * Fills a child node that has fewer than t-1 keys.
     *
     * Time Complexity: O(t) where t is the minimum degree
     *
     * @param node the parent node
     * @param idx the index of the child to fill
     */
    private void fillChild(Node node, int idx) {
        // If previous child has more than t-1 keys, borrow from it
        if (idx > 0 && node.children.get(idx - 1).n >= t) {
            borrowFromPrev(node, idx);
        }
        // If next child has more than t-1 keys, borrow from it
        else if (idx < node.n && node.children.get(idx + 1).n >= t) {
            borrowFromNext(node, idx);
        }
        // Merge child with its sibling
        else {
            if (idx < node.n) {
                merge(node, idx);
            } else {
                merge(node, idx - 1);
            }
        }
    }

    /**
     * Borrows a key from the previous child and places it in the current child.
     *
     * Time Complexity: O(t) where t is the minimum degree
     *
     * EXAMPLE:
     * Borrowing from previous child in this B-tree (t=2):
     * Before:
     *         [10, 20]
     *        /    |    \
     *    [5, 7]  [15]  [25, 30]
     *
     * Borrowing for middle child:
     * Step 1: Move parent key 10 down to middle child
     * Step 2: Move previous child's last key 7 up to parent
     *
     * After:
     *         [7, 20]
     *        /    |    \
     *      [5]  [10, 15]  [25, 30]
     *
     * @param node the parent node
     * @param idx the index of the child that needs a key
     */
    private void borrowFromPrev(Node node, int idx) {
        Node child = node.children.get(idx);
        Node sibling = node.children.get(idx - 1);

        // Move all keys in child one step ahead
        child.keys.add(0, node.keys.get(idx - 1));

        // If it's not a leaf, move the last child of sibling to child
        if (!child.isLeaf && !sibling.children.isEmpty()) {
            child.children.add(0, sibling.children.get(sibling.n));
            sibling.children.remove(sibling.n);
        }

        // Move the last key from the sibling to the parent
        node.keys.set(idx - 1, sibling.keys.get(sibling.n - 1));

        // Remove the last key from the sibling
        sibling.keys.remove(sibling.n - 1);

        // Update counts
        child.n = child.keys.size();
        sibling.n = sibling.keys.size();
    }

    /**
     * Borrows a key from the next child and places it in the current child.
     *
     * Time Complexity: O(t) where t is the minimum degree
     *
     * EXAMPLE:
     * Borrowing from next child in this B-tree (t=2):
     * Before:
     *         [10, 20]
     *        /    |    \
     *      [5]  [15]  [25, 30]
     *
     * Borrowing for middle child:
     * Step 1: Move parent key 20 down to middle child
     * Step 2: Move next child's first key 25 up to parent
     *
     * After:
     *         [10, 25]
     *        /    |    \
     *      [5]  [15, 20]  [30]
     *
     * @param node the parent node
     * @param idx the index of the child that needs a key
     */
    private void borrowFromNext(Node node, int idx) {
        Node child = node.children.get(idx);
        Node sibling = node.children.get(idx + 1);

        // Add the parent's key to the end of the child's keys
        child.keys.add(node.keys.get(idx));

        // Move the first key from the sibling to the parent
        node.keys.set(idx, sibling.keys.get(0));
        sibling.keys.remove(0);

        // If sibling is not a leaf, move its first child to the child node
        if (!sibling.isLeaf && !sibling.children.isEmpty()) {
            child.children.add(sibling.children.get(0));
            sibling.children.remove(0);
        }

        // Update counts
        child.n = child.keys.size();
        sibling.n = sibling.keys.size();
    }

    /**
     * Merges the child at idx+1 into the child at idx.
     *
     * Time Complexity: O(t) where t is the minimum degree
     *
     * EXAMPLE:
     * Merging children in this B-tree (t=2):
     * Before:
     *         [10, 20, 30]
     *        /    |    \    \
     *      [5]   [15]  [25]  [35, 40]
     *
     * Merging middle two children:
     * Step 1: Move parent key 20 down to first child
     * Step 2: Move all keys from the second child to the first
     * Step 3: Remove the parent key and the second child pointer
     *
     * After:
     *         [10, 30]
     *        /    |    \
     *      [5]  [15, 20, 25]  [35, 40]
     *
     * @param node the parent node
     * @param idx the index of the first child
     */
    private void merge(Node node, int idx) {
        Node child = node.children.get(idx);
        Node sibling = node.children.get(idx + 1);

        // Pull down the key from the parent and insert at the end of child
        child.keys.add(node.keys.get(idx));

        // Copy all keys from sibling to child
        child.keys.addAll(sibling.keys);

        // Copy all children from sibling to child
        if (!child.isLeaf) {
            child.children.addAll(sibling.children);
        }

        // Remove the key from the parent and the sibling pointer
        node.keys.remove(idx);
        node.children.remove(idx + 1);

        // Update counts
        child.n = child.keys.size();
        node.n = node.keys.size();
    }

    /**
     * Returns the height of the B-tree.
     *
     * Time Complexity: O(log n) where n is the number of keys
     *
     * @return the height of the tree (a single node tree has height 1)
     */
    public int height() {
        return height(root);
    }

    /**
     * Recursive helper to calculate the height of a subtree.
     *
     * Time Complexity: O(log n)
     *
     * @param node the root of the subtree
     * @return the height of the subtree
     */
    private int height(Node node) {
        if (node.isLeaf) {
            return 1;
        }
        return 1 + height(node.children.get(0));
    }

    /**
     * Returns a string representation of the B-tree for debugging.
     *
     * Time Complexity: O(n) where n is the number of keys
     *
     * @return a string representation of the tree
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 0, sb);
        return sb.toString();
    }

    /**
     * Recursive helper to build a string representation of the B-tree.
     *
     * Time Complexity: O(n)
     *
     * @param node the current node
     * @param level the current level (for indentation)
     * @param sb the StringBuilder to append to
     */
    private void toString(Node node, int level, StringBuilder sb) {
        // Add line for current level
        sb.append(String.format("Level %d: ", level));

        // Print keys for this node
        sb.append("[");
        for (int i = 0; i < node.n; i++) {
            sb.append(node.keys.get(i));
            if (i < node.n - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        // Print an indicator if this is a leaf
        if (node.isLeaf) {
            sb.append(" (leaf)");
        }
        sb.append("\n");

        // Print connection lines to children
        if (!node.isLeaf && !node.children.isEmpty()) {
            // Show connection to children
            for (int i = 0; i < level; i++) {
                sb.append("  ");
            }
            sb.append("├── Children:\n");

            // Recursively print each child with proper indentation
            for (int i = 0; i < node.children.size(); i++) {
                // Indentation for child
                for (int j = 0; j < level; j++) {
                    sb.append("  ");
                }

                // Show which child number (leftmost is 0)
                sb.append(String.format("│   Child %d: ", i));

                // Recursively print the child's structure on a new line
                StringBuilder childSb = new StringBuilder();
                toString(node.children.get(i), level + 1, childSb);

                // Indent the child's output and add it to the main StringBuilder
                String childString = childSb.toString();
                // Remove the "Level X: " prefix that will be added by the child's call
                int prefixEnd = childString.indexOf(": ") + 2;
                sb.append(childString.substring(prefixEnd));

                // Add a spacer line between children except after the last child
                if (i < node.children.size() - 1) {
                    sb.append("\n");
                }
            }
        }
    }

    /**
     * Performs an in-order traversal of the B-tree.
     *
     * Time Complexity: O(n) where n is the number of keys
     *
     * @return a list containing all keys in sorted order
     */
    public List<K> inOrderTraversal() {
        List<K> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    /**
     * Recursive helper for in-order traversal.
     *
     * Time Complexity: O(n)
     *
     * @param node the current node
     * @param result the list to add keys to
     */
    private void inOrderTraversal(Node node, List<K> result) {
        if (node == null) {
            return;
        }

        int i;
        for (i = 0; i < node.n; i++) {
            // Traverse the left subtree
            if (!node.isLeaf && i < node.children.size()) {
                inOrderTraversal(node.children.get(i), result);
            }

            // Add the key
            result.add(node.keys.get(i));
        }

        // Traverse the rightmost child
        if (!node.isLeaf && i < node.children.size()) {
            inOrderTraversal(node.children.get(i), result);
        }
    }

    /**
     * Node class for the B-tree.
     * Each node contains an array of keys and possibly children pointers.
     */
    private class Node {
        // Number of keys currently stored in the node
        int n;
        // Array to store keys (actually used: 0 to n-1)
        ArrayList<K> keys;
        // Array to store child pointers (actually used: 0 to n)
        ArrayList<Node> children;
        // Flag to indicate if node is a leaf
        boolean isLeaf;

        /**
         * Constructs a new node.
         *
         * Time Complexity: O(1)
         *
         * @param isLeaf true if this node is a leaf, false otherwise
         */
        Node(boolean isLeaf) {
            this.n = 0;
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
            this.isLeaf = isLeaf;
        }
    }
}