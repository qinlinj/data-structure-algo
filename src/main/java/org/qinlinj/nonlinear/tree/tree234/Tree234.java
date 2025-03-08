package org.qinlinj.nonlinear.tree.tree234;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// @formatter:off
/**
 * 2-3-4 Tree Implementation
 *
 * CONCEPT AND PRINCIPLES:
 * A 2-3-4 tree is a self-balancing search tree where:
 * 1. Each node contains 1, 2, or 3 keys (hence the name "2-3-4" tree)
 * 2. A 2-node contains 1 key and has 2 children
 * 3. A 3-node contains 2 keys and has 3 children
 * 4. A 4-node contains 3 keys and has 4 children
 * 5. All leaf nodes are at the same level (perfect height balance)
 * 6. Keys within a node are in ascending order
 *
 * Node structure examples:
 *
 * 2-node:           3-node:             4-node:
 *    (B)             (B,D)              (B,D,F)
 *   /   \           /  |  \            / | | \
 *  A     C         A   C   E          A  C E  G
 *
 * ADVANTAGES:
 * 1. Guaranteed O(log n) operations: All operations maintain height balance automatically.
 * 2. Efficient for both searches and updates: Balance ensures consistent performance.
 * 3. Better space utilization than binary trees: Nodes can contain more keys.
 * 4. Better locality for disk-based systems: Fewer nodes need to be accessed.
 * 5. Direct relation to B-trees: 2-3-4 trees are a specific case of B-trees (order 4).
 * 6. Simpler rebalancing than AVL or Red-Black trees: The splitting operation is straightforward.
 * 7. Maps directly to Red-Black trees: Every 2-3-4 tree can be represented as a Red-Black tree.
 *
 * PRINCIPLES OF OPERATION:
 * - When inserting into a full 4-node, we split it into two 2-nodes and
 *   push the middle key up to the parent.
 * - This pre-splitting approach (splitting on the way down) ensures we never
 *   need to split a node during the insertion's upward phase.
 * - All paths from root to leaves have the same length, maintaining perfect balance.
 * - When removing, we ensure we never encounter a 2-node by transforming them
 *   to 3-nodes or 4-nodes on the way down.
 */
public class Tree234<E extends Comparable<E>> {
    private Node root;
    private int size;

    /**
     * Constructor creates an empty 2-3-4 tree.
     * Time Complexity: O(1)
     */
    public Tree234() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the tree.
     * Time Complexity: O(1)
     *
     * @return the size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the tree is empty.
     * Time Complexity: O(1)
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Searches for a specific element in the tree.
     * Time Complexity: O(log n) - Height of the tree is logarithmic
     *
     * EXAMPLE: Searching for 40 in this tree:
     *
     *           (30,50,70)
     *          /    |    \    \
     *        (10,20) (40) (60) (80,90)
     *
     * Steps:
     * 1. Start at root (30,50,70)
     * 2. Compare: 30 < 40 < 50, so go to child at index 1 (40)
     * 3. Found 40 in leaf node!
     *
     * @param element the element to search for
     * @return true if the element is found, false otherwise
     */
    public boolean contains(E element) {
        if (isEmpty()) return false;
        return contains(root, element);
    }

    /**
     * Recursive helper for searching an element.
     * Time Complexity: O(log n)
     *
     * @param node the current node being checked
     * @param element the element to search for
     * @return true if the element is found, false otherwise
     */
    private boolean contains(Node node, E element) {
        if (node == null) return false;

        // Check if element is in this node's keys
        for (int i = 0; i < node.keyCount; i++) {
            if (element.compareTo(node.keys.get(i)) == 0) {
                return true;
            }
        }

        // If not found, determine which child to search
        int childIndex = findChildIndex(node, element);

        // If we're at a leaf node, element is not in the tree
        if (node.children.isEmpty() || childIndex >= node.children.size()) {
            return false;
        }

        // Recursively search in the appropriate child
        return contains(node.children.get(childIndex), element);
    }

    /**
     * Finds the index of the child to follow when searching for an element.
     * Time Complexity: O(1) - At most comparing with 3 keys
     *
     * @param node the node whose children we're examining
     * @param element the element we're looking for
     * @return the index of the child to follow
     */
    private int findChildIndex(Node node, E element) {
        int i = 0;
        while (i < node.keyCount && element.compareTo(node.keys.get(i)) > 0) {
            i++;
        }
        return i;
    }

    /**
     * Inserts an element into the tree.
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Building a 2-3-4 tree by inserting elements in order: 10, 20, 30, 40, 50, 60, 70, 80, 90
     *
     * Initial tree (empty): null
     *
     * After inserting 10:
     *    (10)
     *
     * After inserting 20:
     *    (10,20)
     *
     * After inserting 30:
     *    (10,20,30)
     *
     * After inserting 40 (split required):
     *      (20)
     *     /    \
     *   (10)   (30,40)
     *
     * After inserting 50:
     *       (20)
     *      /    \
     *    (10)   (30,40,50)
     *
     * After inserting 60 (split required):
     *        (20,40)
     *       /   |   \
     *    (10)  (30) (50,60)
     *
     * After inserting 70:
     *        (20,40)
     *       /   |   \
     *    (10)  (30) (50,60,70)
     *
     * After inserting 80 (split required):
     *          (20,40,60)
     *         /   |   |   \
     *       (10) (30) (50) (70,80)
     *
     * After inserting 90 (root split required):
     *              (40)
     *             /    \
     *         (20)      (60)
     *        /   \     /   \
     *     (10)  (30) (50) (70,80,90)
     *
     * @param element the element to insert
     */
    public void insert(E element) {
        if (root == null) {
            // Empty tree case
            root = new Node();
            root.keys.add(element);
            root.keyCount = 1;
            size++;
            return;
        }

        // Handle the case where we might need to split the root
        if (root.keyCount == 3) {
            // Split the root into two nodes
            Node newRoot = new Node();
            Node rightChild = new Node();

            // Move the right key to the new right child
            rightChild.keys.add(root.keys.get(2));
            rightChild.keyCount = 1;

            // Middle key goes up to new root
            newRoot.keys.add(root.keys.get(1));
            newRoot.keyCount = 1;

            // If root had children, distribute them appropriately
            if (!root.children.isEmpty()) {
                rightChild.children.add(root.children.get(2));
                rightChild.children.add(root.children.get(3));
            }

            // Update the old root (now left child)
            root.keys.remove(2);
            root.keys.remove(1);
            root.keyCount = 1;

            if (!root.children.isEmpty()) {
                while (root.children.size() > 2) {
                    root.children.remove(root.children.size() - 1);
                }
            }

            // Setup the new root's children
            newRoot.children.add(root);
            newRoot.children.add(rightChild);

            // Make the new node the root
            root = newRoot;
        }

        // Now insert normally
        insert(root, element);
        size++;
    }

    /**
     * Recursive helper for insertion that handles node splitting.
     * Time Complexity: O(log n)
     *
     * @param node the current node
     * @param element the element to insert
     */
    private void insert(Node node, E element) {
        // Determine which child the element should go to
        int childIndex = findChildIndex(node, element);

        // If we're at a leaf node, insert the element here
        if (node.children.isEmpty()) {
            insertIntoNode(node, element, childIndex);
            return;
        }

        // Check if the child node we're about to traverse is full (has 3 keys)
        Node childNode = node.children.get(childIndex);

        if (childNode.keyCount == 3) {
            // Split the child before going down

            // Extract middle key and promote to parent
            E middleKey = childNode.keys.get(1);
            insertIntoNode(node, middleKey, childIndex);

            // Create a new node for the right half
            Node rightChild = new Node();
            rightChild.keys.add(childNode.keys.get(2));
            rightChild.keyCount = 1;

            // If the child had children, distribute them appropriately
            if (!childNode.children.isEmpty()) {
                rightChild.children.add(childNode.children.get(2));
                rightChild.children.add(childNode.children.get(3));

                // Remove the moved children from the original child
                while (childNode.children.size() > 2) {
                    childNode.children.remove(childNode.children.size() - 1);
                }
            }

            // Update the original child node (now left child)
            // Remove keys from end to avoid index shifting
            childNode.keys.remove(2);
            childNode.keys.remove(1);
            childNode.keyCount = 1;

            // Add the new right child to the parent's children
            node.children.add(childIndex + 1, rightChild);

            // Determine which of the two children should get the new element
            if (element.compareTo(middleKey) < 0) {
                // Go to left child
                insert(childNode, element);
            } else {
                // Go to right child
                insert(rightChild, element);
            }
        } else {
            // Continue insertion recursively
            insert(childNode, element);
        }
    }

    /**
     * Inserts an element into a node at the specified position.
     * Time Complexity: O(1) - Only dealing with at most 4 keys
     *
     * @param node the node to insert into
     * @param element the element to insert
     * @param index the position to insert at
     */
    private void insertIntoNode(Node node, E element, int index) {
        // Ensure the index is valid
        if (index > node.keyCount) {
            index = node.keyCount;
        }

        node.keys.add(index, element);
        node.keyCount++;
    }

    /**
     * Removes an element from the tree if it exists.
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Removing 30 from this tree:
     *
     *        (20,40,60)
     *       /   |   |   \
     *    (10)  (30) (50) (70,80)
     *
     * Since 30 is in a leaf node with more than 1 key, we can simply remove it:
     *
     * After removal:
     *        (20,40,60)
     *       /   |   |   \
     *    (10)  ()   (50) (70,80)
     *
     * But this creates an empty node, so we need to redistribute or merge:
     * (In this case, since siblings have 1 key each, we merge)
     *
     * After rebalancing:
     *        (20,60)
     *       /   |   \
     *    (10)  (40,50) (70,80)
     *
     * @param element the element to remove
     * @return true if element was removed, false if not found
     */
    public boolean remove(E element) {
        if (isEmpty()) return false;

        boolean result = remove(root, element, null, -1);

        // If root has no keys after removal, make its only child the new root
        if (result && root.keyCount == 0 && !root.children.isEmpty()) {
            root = root.children.get(0);
        }

        if (result) size--;
        return result;
    }

    /**
     * Recursive helper for removing an element.
     * Time Complexity: O(log n)
     *
     * @param node current node
     * @param element element to remove
     * @param parent parent of current node (null for root)
     * @param nodeIndex index of current node in parent's children
     * @return true if element was found and removed
     */
    private boolean remove(Node node, E element, Node parent, int nodeIndex) {
        if (node == null) return false;

        // Check if element is in this node
        int keyIndex = -1;
        for (int i = 0; i < node.keyCount; i++) {
            if (element.compareTo(node.keys.get(i)) == 0) {
                keyIndex = i;
                break;
            }
        }

        if (keyIndex != -1) {
            // Element found in this node
            if (node.children.isEmpty()) {
                // If it's a leaf node, simply remove the key
                node.keys.remove(keyIndex);
                node.keyCount--;

                // Handle the case where a node becomes empty
                if (node.keyCount == 0 && parent != null) {
                    handleEmptyNode(parent, nodeIndex);
                }

                return true;
            } else {
                // If it's an internal node, replace with predecessor and delete predecessor
                // Find the rightmost element in the left subtree
                Node predecessorNode = node.children.get(keyIndex);
                while (!predecessorNode.children.isEmpty()) {
                    predecessorNode = predecessorNode.children.get(predecessorNode.keyCount);
                }

                // Replace with the rightmost key of the predecessor node
                E predecessor = predecessorNode.keys.get(predecessorNode.keyCount - 1);
                node.keys.set(keyIndex, predecessor);

                // Now remove the predecessor from its original position
                return remove(node.children.get(keyIndex), predecessor, node, keyIndex);
            }
        }

        // Element is not in this node, determine which child to search
        int childIndex = findChildIndex(node, element);
        if (childIndex >= node.children.size()) {
            return false; // Element not in tree
        }

        // Ensure the child node has at least 2 keys before going down
        if (node.children.get(childIndex).keyCount < 2) {
            ensureChildHasAtLeastTwoKeys(node, childIndex);
        }

        // Recursively remove from the appropriate child
        return remove(node.children.get(childIndex), element, node, childIndex);
    }

    /**
     * Handle the case where a node becomes empty after removal
     */
    private void handleEmptyNode(Node parent, int emptyNodeIndex) {
        // Remove the empty node
        parent.children.remove(emptyNodeIndex);

        // Adjust parent's keys if necessary
        if (emptyNodeIndex > 0 && emptyNodeIndex <= parent.keyCount) {
            parent.keys.remove(emptyNodeIndex - 1);
            parent.keyCount--;
        }
    }

    /**
     * Ensures that a child node has at least 2 keys before descending into it.
     * This might involve redistributing keys from siblings or merging with a sibling.
     * Time Complexity: O(1) - Operations on a fixed number of nodes
     *
     * @param parent the parent node
     * @param childIndex the index of the child that needs checking
     */
    private void ensureChildHasAtLeastTwoKeys(Node parent, int childIndex) {
        Node child = parent.children.get(childIndex);

        // If child already has at least 2 keys, nothing to do
        if (child.keyCount >= 2) return;

        // Try to borrow from left sibling
        if (childIndex > 0) {
            Node leftSibling = parent.children.get(childIndex - 1);
            if (leftSibling.keyCount > 1) {
                // Borrow rightmost key from left sibling
                E parentKey = parent.keys.get(childIndex - 1);
                E siblingKey = leftSibling.keys.get(leftSibling.keyCount - 1);

                // Move parent key down to child
                child.keys.add(0, parentKey);
                child.keyCount++;

                // Move sibling key up to parent
                parent.keys.set(childIndex - 1, siblingKey);

                // Remove the borrowed key from sibling
                leftSibling.keys.remove(leftSibling.keyCount - 1);
                leftSibling.keyCount--;

                // If not leaf nodes, move the rightmost child reference too
                if (!leftSibling.children.isEmpty()) {
                    child.children.add(0, leftSibling.children.get(leftSibling.children.size() - 1));
                    leftSibling.children.remove(leftSibling.children.size() - 1);
                }

                return;
            }
        }

        // Try to borrow from right sibling
        if (childIndex < parent.children.size() - 1) {
            Node rightSibling = parent.children.get(childIndex + 1);
            if (rightSibling.keyCount > 1) {
                // Borrow leftmost key from right sibling
                E parentKey = parent.keys.get(childIndex);
                E siblingKey = rightSibling.keys.get(0);

                // Move parent key down to child
                child.keys.add(parentKey);
                child.keyCount++;

                // Move sibling key up to parent
                parent.keys.set(childIndex, siblingKey);

                // Remove the borrowed key from sibling
                rightSibling.keys.remove(0);
                rightSibling.keyCount--;

                // If not leaf nodes, move the leftmost child reference too
                if (!rightSibling.children.isEmpty()) {
                    child.children.add(rightSibling.children.get(0));
                    rightSibling.children.remove(0);
                }

                return;
            }
        }

        // If we got here, we need to merge with a sibling

        // Merge with left sibling if possible
        if (childIndex > 0) {
            Node leftSibling = parent.children.get(childIndex - 1);

            // Add parent key to left sibling
            E parentKey = parent.keys.get(childIndex - 1);
            leftSibling.keys.add(parentKey);

            // Add all keys from child to left sibling
            leftSibling.keys.addAll(child.keys);
            leftSibling.keyCount = leftSibling.keys.size();

            // Add all children from child to left sibling
            if (!child.children.isEmpty()) {
                leftSibling.children.addAll(child.children);
            }

            // Remove the parent key and child pointer
            parent.keys.remove(childIndex - 1);
            parent.children.remove(childIndex);
            parent.keyCount--;

            return;
        }

        // Merge with right sibling
        if (childIndex < parent.children.size() - 1) {
            Node rightSibling = parent.children.get(childIndex + 1);

            // Add parent key to child
            E parentKey = parent.keys.get(childIndex);
            child.keys.add(parentKey);

            // Add all keys from right sibling to child
            child.keys.addAll(rightSibling.keys);
            child.keyCount = child.keys.size();

            // Add all children from right sibling to child
            if (!rightSibling.children.isEmpty()) {
                child.children.addAll(rightSibling.children);
            }

            // Remove the parent key and right sibling pointer
            parent.keys.remove(childIndex);
            parent.children.remove(childIndex + 1);
            parent.keyCount--;
        }
    }

    /**
     * Returns a list of all elements in the tree in level-order traversal.
     * Time Complexity: O(n) - must visit all nodes
     *
     * @return list of all elements in level-order
     */
    public List<List<E>> levelOrderTraversal() {
        List<List<E>> result = new ArrayList<>();
        if (isEmpty()) return result;

        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        queue.add(root);
        levelQueue.add(0);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int level = levelQueue.poll();

            // Ensure we have a list for this level
            while (result.size() <= level) {
                result.add(new ArrayList<>());
            }

            // Add all keys at this node to the result
            for (int i = 0; i < node.keyCount; i++) {
                result.get(level).add(node.keys.get(i));
            }

            // Enqueue all children
            for (int i = 0; i < node.children.size(); i++) {
                queue.add(node.children.get(i));
                levelQueue.add(level + 1);
            }
        }

        return result;
    }

    /**
     * Returns a list of all elements in the tree in in-order traversal.
     * Time Complexity: O(n) - must visit all nodes
     *
     * EXAMPLE: In-order traversal of this tree:
     *
     *          (20,40,60)
     *         /   |   |   \
     *      (10) (30) (50) (70,80)
     *
     * Result: [10, 20, 30, 40, 50, 60, 70, 80]
     *
     * @return list of all elements in in-order (sorted order)
     */
    public List<E> inOrderTraversal() {
        List<E> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    /**
     * Recursive helper for in-order traversal.
     * Time Complexity: O(n)
     *
     * @param node current node
     * @param result list to add elements to
     */
    private void inOrderTraversal(Node node, List<E> result) {
        if (node == null) return;

        if (node.children.isEmpty()) {
            // Leaf node, just add all keys
            for (int i = 0; i < node.keyCount; i++) {
                result.add(node.keys.get(i));
            }
            return;
        }

        // Internal node, interleave children and keys
        for (int i = 0; i < node.keyCount; i++) {
            inOrderTraversal(node.children.get(i), result);
            result.add(node.keys.get(i));
        }

        // Don't forget the last child
        if (node.children.size() > node.keyCount) {
            inOrderTraversal(node.children.get(node.keyCount), result);
        }
    }

    /**
     * Returns the height of the tree.
     * Time Complexity: O(log n) - tree is balanced
     *
     * @return the height of the tree (0 for empty tree)
     */
    public int height() {
        if (isEmpty()) return 0;
        return height(root);
    }

    /**
     * Recursive helper for calculating height.
     * Time Complexity: O(log n)
     *
     * @param node the current node
     * @return the height of the subtree from this node
     */
    private int height(Node node) {
        if (node == null) return 0;
        if (node.children.isEmpty()) return 1;
        return 1 + height(node.children.get(0)); // All paths have same length
    }

    /**
     * Returns a string representation of the tree.
     * Useful for debugging and testing.
     *
     * @return a string representation of the tree
     */
    @Override
    public String toString() {
        if (isEmpty()) return "Empty Tree";

        StringBuilder sb = new StringBuilder();
        List<List<E>> levels = levelOrderTraversal();

        for (int i = 0; i < levels.size(); i++) {
            sb.append("Level ").append(i).append(": ");
            sb.append(levels.get(i));
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Verifies that the tree structure is a valid 2-3-4 tree.
     * Time Complexity: O(n) - needs to check all nodes
     *
     * @return true if the tree is valid, false otherwise
     */
    public boolean isValid() {
        if (isEmpty()) return true;

        // Check if all the levels have the same depth
        int treeHeight = height();
        boolean[] allLeavesAtSameDepth = new boolean[1];
        allLeavesAtSameDepth[0] = true;
        checkLeafDepth(root, 1, treeHeight, allLeavesAtSameDepth);
        if (!allLeavesAtSameDepth[0]) return false;

        // Check if the tree is ordered properly
        List<E> inOrder = inOrderTraversal();
        for (int i = 1; i < inOrder.size(); i++) {
            if (inOrder.get(i-1).compareTo(inOrder.get(i)) >= 0) {
                return false;
            }
        }

        // Check node structure
        return isValidNodeStructure(root);
    }

    /**
     * Helper method to check if all leaves are at the same depth.
     * Time Complexity: O(n) - might need to check all nodes
     *
     * @param node current node
     * @param currentDepth depth of current node
     * @param expectedHeight expected height of all leaves
     * @param allLeavesAtSameDepth boolean array to store result
     */
    private void checkLeafDepth(Node node, int currentDepth, int expectedHeight, boolean[] allLeavesAtSameDepth) {
        if (!allLeavesAtSameDepth[0]) return;

        if (node.children.isEmpty()) {
            // This is a leaf node, check if it's at the expected depth
            if (currentDepth != expectedHeight) {
                allLeavesAtSameDepth[0] = false;
            }
            return;
        }

        // Recursively check all children
        for (Node child : node.children) {
            checkLeafDepth(child, currentDepth + 1, expectedHeight, allLeavesAtSameDepth);
        }
    }

    /**
     * Helper method to check if all nodes follow the 2-3-4 tree structure.
     * Time Complexity: O(n) - must check all nodes
     *
     * @param node current node
     * @return true if the subtree rooted at node is valid
     */
    private boolean isValidNodeStructure(Node node) {
        if (node == null) return true;

        // Check if this node has a valid number of keys (1-3)
        if (node.keyCount < 1 || node.keyCount > 3) {
            return false;
        }

        // Check if keys are in ascending order
        for (int i = 1; i < node.keyCount; i++) {
            if (node.keys.get(i-1).compareTo(node.keys.get(i)) >= 0) {
                return false;
            }
        }

        // If this is an internal node, it should have (keyCount + 1) children
        if (!node.children.isEmpty()) {
            if (node.children.size() != node.keyCount + 1) {
                return false;
            }

            // Recursively check all children
            for (Node child : node.children) {
                if (!isValidNodeStructure(child)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Finds the minimum value in the tree.
     * Time Complexity: O(log n) - tree height is logarithmic
     *
     * EXAMPLE: Finding minimum in this tree:
     *          (20,40,60)
     *         /   |   |   \
     *      (10) (30) (50) (70,80)
     *
     * We follow the leftmost path from the root until reaching a leaf:
     * root → leftmost child (10) → value 10 is the minimum
     *
     * @return the minimum element in the tree
     * @throws IllegalStateException if the tree is empty
     */
    public E findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot find minimum in an empty tree");
        }

        Node current = root;
        while (!current.children.isEmpty()) {
            current = current.children.get(0); // Always take the leftmost child
        }

        return current.keys.get(0); // Leftmost key in the leftmost leaf
    }

    /**
     * Finds the maximum value in the tree.
     * Time Complexity: O(log n) - tree height is logarithmic
     *
     * EXAMPLE: Finding maximum in this tree:
     *          (20,40,60)
     *         /   |   |   \
     *      (10) (30) (50) (70,80)
     *
     * We follow the rightmost path from the root until reaching a leaf:
     * root → rightmost child (70,80) → rightmost value 80 is the maximum
     *
     * @return the maximum element in the tree
     * @throws IllegalStateException if the tree is empty
     */
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot find maximum in an empty tree");
        }

        Node current = root;
        while (!current.children.isEmpty()) {
            current = current.children.get(current.children.size() - 1); // Take the rightmost child
        }

        return current.keys.get(current.keyCount - 1); // Rightmost key in the rightmost leaf
    }

    /**
     * Performs a range query, finding all elements between min and max (inclusive).
     * Time Complexity: O(log n + k) where k is the number of elements in the range
     *
     * EXAMPLE: Finding all elements between 25 and 55 in this tree:
     *          (20,40,60)
     *         /   |   |   \
     *      (10) (30) (50) (70,80)
     *
     * Result: [30, 40, 50]
     *
     * @param min the lower bound of the range (inclusive)
     * @param max the upper bound of the range (inclusive)
     * @return list of all elements in the range
     */
    public List<E> rangeQuery(E min, E max) {
        List<E> result = new ArrayList<>();
        rangeQuery(root, min, max, result);
        return result;
    }

    /**
     * Recursive helper for range query.
     * Time Complexity: O(log n + k)
     *
     * @param node current node
     * @param min lower bound
     * @param max upper bound
     * @param result list to add results to
     */
    private void rangeQuery(Node node, E min, E max, List<E> result) {
        if (node == null) return;

        // If this is a leaf node, check each key
        if (node.children.isEmpty()) {
            for (int i = 0; i < node.keyCount; i++) {
                E key = node.keys.get(i);
                if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0) {
                    result.add(key);
                }
            }
            return;
        }

        // For internal nodes, we need to determine which children to visit
        int i = 0;

        // Skip children that can't contain values in the range
        while (i < node.keyCount && node.keys.get(i).compareTo(min) < 0) {
            i++;
        }

        // Visit the first potential child
        if (i < node.children.size()) {
            rangeQuery(node.children.get(i), min, max, result);
        }

        // For each key that's in range, add it and visit the next child
        while (i < node.keyCount) {
            E key = node.keys.get(i);

            // If this key is within range, add it
            if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0) {
                result.add(key);
            }

            // If this key exceeds the max, we're done
            if (key.compareTo(max) > 0) {
                break;
            }

            // Visit the next child
            i++;
            if (i < node.children.size()) {
                rangeQuery(node.children.get(i), min, max, result);
            }
        }
    }

    /**
     * Clears the tree, removing all elements.
     * Time Complexity: O(1)
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Node class for the 2-3-4 Tree.
     * Each node can have 1, 2, or 3 keys.
     */
    private class Node {
        List<E> keys;            // Keys stored in the node
        List<Node> children;     // Children nodes
        int keyCount;            // Number of keys currently in the node

        /**
         * Constructs an empty node.
         */
        public Node() {
            keys = new ArrayList<>(3);      // At most 3 keys in a 2-3-4 tree node
            children = new ArrayList<>(4);  // At most 4 children
            keyCount = 0;
        }
    }
}