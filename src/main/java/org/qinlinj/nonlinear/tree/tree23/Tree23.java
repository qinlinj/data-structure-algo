package org.qinlinj.nonlinear.tree.tree23;

import java.util.ArrayList;
import java.util.List;
// @formatter:off
/**
 * 2-3 Tree Implementation
 *
 * CONCEPT AND PRINCIPLES:
 * A 2-3 tree is a self-balancing search tree where:
 * 1. Each internal node has either 2 or 3 children (hence the name "2-3 tree")
 * 2. A 2-node contains 1 key and has 2 children
 * 3. A 3-node contains 2 keys and has 3 children
 * 4. All leaf nodes are at the same level (perfect height balance)
 * 5. Keys within a node are ordered
 *
 * Visual representation of node types:
 *
 * 2-node:         3-node:
 *     (B)           (A,C)
 *    /   \         /  |  \
 *   A     C       *   B   D
 *
 * ADVANTAGES:
 * 1. Perfect Balance: All operations maintain perfect height balance automatically,
 *    ensuring O(log n) time complexity for basic operations.
 * 2. Efficient Search: The balanced nature ensures consistent search performance.
 * 3. Flexibility: Can adapt to varying data distribution patterns.
 * 4. No Worst Case: Unlike some trees (like AVL), the worst-case height is still logarithmic.
 * 5. Basis for B-trees: The concept extends to B-trees used in databases and
 *    file systems, making it important in system implementations.
 *
 * PRINCIPLES OF OPERATION:
 * - When inserting into a full 3-node, we split it into two 2-nodes and
 *   propagate the middle key up to the parent.
 * - When removing from a 2-node, we either borrow a key from a sibling or
 *   merge with a sibling, restructuring as needed.
 * - All operations maintain the perfect height balance property.
 */
// @formatter:off
public class Tree23<E extends Comparable<E>> {
    private Node root;
    private int size;

    /**
     * Constructor creates an empty 2-3 tree.
     * Time Complexity: O(1)
     */
    public Tree23() {
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
     * EXAMPLE: Searching for 5 in the following tree:
     *
     *        (7)
     *      /     \
     *    (3,5)   (9,11)
     *   /  |  \  /  |  \
     *  (1)(4)(6)(8)(10)(12)
     *
     * Steps:
     * 1. Start at root (7)
     * 2. 5 < 7, so go to left child (3,5)
     * 3. 3 < 5 < 5, so check middle value: 5 == 5
     * 4. Element found!
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

        // Check if the element is in this node
        if (node.keys.size() > 0 && element.compareTo(node.keys.get(0)) == 0) return true;
        if (node.keys.size() > 1 && element.compareTo(node.keys.get(1)) == 0) return true;

        // Determine which child to search
        if (node.keys.size() == 1) {
            // 2-node case
            if (element.compareTo(node.keys.get(0)) < 0) {
                return contains(node.children.get(0), element); // Left child
            } else {
                return contains(node.children.get(1), element); // Right child
            }
        } else {
            // 3-node case
            if (element.compareTo(node.keys.get(0)) < 0) {
                return contains(node.children.get(0), element); // Left child
            } else if (element.compareTo(node.keys.get(1)) < 0) {
                return contains(node.children.get(1), element); // Middle child
            } else {
                return contains(node.children.get(2), element); // Right child
            }
        }
    }

    /**
     * Inserts an element into the tree.
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Building a tree by inserting elements in order: 3, 5, 7, 9, 1, 2, 4
     *
     * Initial tree (empty): null
     *
     * After inserting 3:
     *    (3)
     *
     * After inserting 5:
     *    (3,5)
     *
     * After inserting 7:
     *      (5)
     *     /   \
     *   (3)   (7)
     *
     * After inserting 9:
     *      (5)
     *     /   \
     *   (3)   (7,9)
     *
     * After inserting 1:
     *      (5)
     *     /   \
     *   (1,3) (7,9)
     *
     * After inserting 2:
     *         (5)
     *       /     \
     *     (2)     (7,9)
     *    /   \
     *  (1)   (3)
     *
     * After inserting 4:
     *         (5)
     *       /     \
     *     (2)     (7,9)
     *    /   \
     *  (1)   (3,4)
     *
     * @param element the element to insert
     */
    public void insert(E element) {
        if (root == null) {
            // Empty tree case
            root = new Node();
            root.keys.add(element);
            size++;
            return;
        }

        // Use the insert helper that returns a NodeReturn object
        NodeReturn result = insert(root, element);

        // If the root was split, create a new root
        if (result.pKey != null) {
            Node newRoot = new Node();
            newRoot.keys.add(result.pKey);
            newRoot.children.add(result.leftChild);
            newRoot.children.add(result.rightChild);
            root = newRoot;
        }

        size++;
    }

    /**
     * Recursive helper for insertion that handles node splitting.
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Inserting 6 into the following tree:
     *      (5)
     *     /   \
     *   (3)   (7,9)
     *
     * Step 1: We determine 5 < 6 < 7, so we would insert into the right child (7,9)
     *
     * Step 2: The right child already has 2 keys, so inserting would make it overfull
     *      (5)
     *     /   \
     *   (3)   (6,7,9) // Temporarily overfull
     *
     * Step 3: Split the overfull node
     * - Left child: (6)
     * - Promoted key: 7
     * - Right child: (9)
     *
     * Step 4: Promote the middle key (7) to the parent, making it a 3-node
     *      (5,7)
     *     /  |  \
     *   (3) (6) (9)
     *
     * @param node the current node
     * @param element the element to insert
     * @return NodeReturn object containing split information if a split occurred
     */
    private NodeReturn insert(Node node, E element) {
        // Check if we're at a leaf node
        if (node.isLeaf()) {
            // Insert the element in order
            int i = 0;
            while (i < node.keys.size() && element.compareTo(node.keys.get(i)) > 0) {
                i++;
            }
            node.keys.add(i, element);

            // If node is now overfull, split it
            if (node.keys.size() > 2) {
                return splitNode(node);
            }

            return new NodeReturn(null, node, null);
        }

        // Internal node case - determine which child to insert into
        NodeReturn result;

        if (node.keys.size() == 1) {
            // 2-node case
            if (element.compareTo(node.keys.get(0)) < 0) {
                result = insert(node.children.get(0), element); // Left child
            } else if (element.compareTo(node.keys.get(0)) > 0) {
                result = insert(node.children.get(1), element); // Right child
            } else {
                // Element already exists
                size--; // Adjust size since we're not actually adding a new element
                return new NodeReturn(null, node, null);
            }
        } else {
            // 3-node case
            if (element.compareTo(node.keys.get(0)) < 0) {
                result = insert(node.children.get(0), element); // Left child
            } else if (element.compareTo(node.keys.get(1)) < 0) {
                result = insert(node.children.get(1), element); // Middle child
            } else if (element.compareTo(node.keys.get(1)) > 0) {
                result = insert(node.children.get(2), element); // Right child
            } else if (element.compareTo(node.keys.get(0)) == 0 ||
                    element.compareTo(node.keys.get(1)) == 0) {
                // Element already exists
                size--; // Adjust size since we're not actually adding a new element
                return new NodeReturn(null, node, null);
            } else {
                // Should never reach here
                result = new NodeReturn(null, node, null);
            }
        }

        // If child node was split, integrate its median key into this node
        if (result.pKey != null) {
            // EXAMPLE: After inserting 8 into a child of node (5,7), child (9) splits
            // and promotes 8 to this node
            //
            // Before:
            //      (5,7)
            //     /  |  \
            //   (3) (6) (9)
            //
            // After child split:
            //      (5,7)
            //     /  |  \
            //   (3) (6) (split: 8 promoted, with children null)
            //
            // After adding promoted key:
            //      (5,7,8)  // Temporarily overfull
            //     /  |  | \
            //   (3) (6)(L)(R)
            //
            // If this node becomes overfull, it will be split in the next step

            int i = 0;
            while (i < node.keys.size() && result.pKey.compareTo(node.keys.get(i)) > 0) {
                i++;
            }

            node.keys.add(i, result.pKey);

            // Remove the original child that was split
            node.children.remove(i < node.keys.size() ? i : node.children.size() - 1);

            // Add the two new children from the split
            node.children.add(i, result.rightChild);
            node.children.add(i, result.leftChild);

            // Check if this node is now overfull
            if (node.keys.size() > 2) {
                return splitNode(node);
            }
        }

        return new NodeReturn(null, node, null);
    }

    /**
     * Splits an overfull node (3+ keys) into two nodes.
     * Time Complexity: O(1) - constant time operations
     *
     * EXAMPLE: Splitting an overfull node with keys (3,5,7)
     *
     * Before (overfull node):
     *        (3,5,7)
     *       /  |  | \
     *      A   B  C  D
     *
     * After split:
     * - Left node: (3) with children A, B
     * - Promoted key: 5
     * - Right node: (7) with children C, D
     *
     * The parent of this node will then insert the promoted key 5
     * and update its children to include the new left and right nodes.
     *
     * @param node the overfull node to split
     * @return NodeReturn object with the promoted key and new nodes
     */
    private NodeReturn splitNode(Node node) {
        // Create new nodes
        Node leftNode = new Node();
        Node rightNode = new Node();

        // Middle key to be promoted
        E middleKey = node.keys.get(1);

        // Distribute keys
        leftNode.keys.add(node.keys.get(0));
        rightNode.keys.add(node.keys.get(2));

        // If node has children, distribute them correctly
        if (!node.isLeaf()) {
            // The first two children go to the left node
            leftNode.children.add(node.children.get(0));
            leftNode.children.add(node.children.get(1));

            // The last two children go to the right node
            rightNode.children.add(node.children.get(2));
            rightNode.children.add(node.children.get(3));
        }

        return new NodeReturn(middleKey, leftNode, rightNode);
    }

    /**
     * Removes an element from the tree if it exists.
     * Time Complexity: O(log n)
     *
     * EXAMPLE: Removing 5 from the following tree:
     *         (5)
     *       /     \
     *     (2)     (7,9)
     *    /   \   /  |  \
     *  (1)   (3)(6)(8)(10)
     *
     * Step 1: Find the node containing 5 (the root)
     *
     * Step 2: Since it's an internal node, replace it with its predecessor (3)
     *         (3)
     *       /     \
     *     (2)     (7,9)
     *    /       /  |  \
     *  (1)     (6)(8)(10)
     *
     * Step 3: Remove 3 from its original position
     * The resulting tree maintains its 2-3 tree properties
     *
     * @param element the element to remove
     * @return true if element was removed, false if not found
     */
    public boolean remove(E element) {
        if (isEmpty()) return false;

        boolean result = remove(root, element, null, -1);

        // If root has no keys, make its only child the new root
        if (result && root.keys.isEmpty() && !root.children.isEmpty()) {
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
     * @param parent parent of current node
     * @param childIndex index of current node in parent's children
     * @return true if element was found and removed
     */
    private boolean remove(Node node, E element, Node parent, int childIndex) {
        if (node == null) return false;

        int keyIndex = -1;

        // Check if the element is in this node
        for (int i = 0; i < node.keys.size(); i++) {
            if (element.compareTo(node.keys.get(i)) == 0) {
                keyIndex = i;
                break;
            }
        }

        if (keyIndex != -1) {
            // Element found in this node
            if (node.isLeaf()) {
                // EXAMPLE: Removing 3 from a leaf node (2,3,4)
                //
                // Before:      After:
                //   (2,3,4)      (2,4)

                // If leaf, simply remove it
                node.keys.remove(keyIndex);

                // If node is now underflowing, handle it
                if (node.keys.isEmpty() && parent != null) {
                    handleUnderflow(node, parent, childIndex);
                }

                return true;
            } else {
                // EXAMPLE: Removing 5 from an internal node
                //
                //     (5,8)                (3,8)
                //    /  |  \              /  |  \
                //  (3) (7) (9)          (2) (7) (9)
                //  / \                  /
                // (1)(2)               (1)
                //
                // First we replace 5 with its predecessor 3,
                // then we recursively remove 3 from the left subtree

                // Get the predecessor (rightmost element in left subtree)
                int predecessorChildIndex = Math.min(keyIndex, node.children.size() - 1);
                Node predecessorNode = node.children.get(predecessorChildIndex);
                while (!predecessorNode.children.isEmpty() && !predecessorNode.children.isEmpty()) {
                    predecessorNode = predecessorNode.children.get(predecessorNode.children.size() - 1);
                }

                // Make sure predecessor node has keys
                if (predecessorNode.keys.isEmpty()) {
                    return false;  // This should not happen in a valid 2-3 tree
                }

                // Replace the key with its predecessor
                E predecessor = predecessorNode.keys.get(predecessorNode.keys.size() - 1);
                node.keys.set(keyIndex, predecessor);

                // Now remove the predecessor
                return remove(node.children.get(predecessorChildIndex), predecessor, node, predecessorChildIndex);
            }
        } else {
            // Element not in this node, continue search in appropriate child

            // Determine which child to search
            int nextChild = 0;
            while (nextChild < node.keys.size() && element.compareTo(node.keys.get(nextChild)) > 0) {
                nextChild++;
            }

            // Check if we have a valid child index
            if (nextChild >= node.children.size()) {
                return false; // Element not in tree
            }

            // Recursively search in the appropriate child
            boolean result = remove(node.children.get(nextChild), element, node, nextChild);

            // Check if node needs rebalancing after removal
            if (result && node.children.size() > nextChild &&
                    node.children.get(nextChild) != null &&
                    node.children.get(nextChild).keys.isEmpty()) {
                handleUnderflow(node.children.get(nextChild), node, nextChild);
            }

            return result;
        }
    }

    /**
     * Handles the case when a node has underflowed (has no keys).
     * Time Complexity: O(1) - constant time operations
     *
     * EXAMPLE 1: Borrowing from left sibling
     *
     * Before:
     *        (7)
     *      /     \
     *    (3,5)   ()  // Empty node due to removal
     *   /  |  \     /
     *  (1)(4)(6)   (8)
     *
     * After (borrow from left):
     *        (5)
     *      /     \
     *    (3)     (7)
     *   /  \    /  \
     *  (1)(4)  (6) (8)
     *
     * EXAMPLE 2: Borrowing from right sibling
     *
     * Before:
     *        (5)
     *      /     \
     *    ()      (7,9)  // Left node empty due to removal
     *   /       /  |  \
     *  (3)     (6)(8)(10)
     *
     * After (borrow from right):
     *        (7)
     *      /     \
     *    (5)     (9)
     *   /  \    /  \
     *  (3) (6) (8)(10)
     *
     * EXAMPLE 3: Merging with sibling
     *
     * Before:
     *        (5)
     *      /     \
     *    (3)     ()  // Right node empty due to removal
     *   /  \    /
     *  (1) (4) (6)
     *
     * After (merge with right):
     *        (3)
     *      /     \
     *    (1)     (5,6)
     *            /  |  \
     *           (4)()  ()
     *
     * @param node the underflowing node
     * @param parent the parent of the underflowing node
     * @param childIndex the index of the underflowing node in parent's children
     */
    private void handleUnderflow(Node node, Node parent, int childIndex) {
        // Try to borrow from left sibling
        if (childIndex > 0) {
            Node leftSibling = parent.children.get(childIndex - 1);
            if (leftSibling.keys.size() > 1) {
                // Borrow from left sibling
                node.keys.add(0, parent.keys.get(childIndex - 1));
                parent.keys.set(childIndex - 1, leftSibling.keys.remove(leftSibling.keys.size() - 1));

                // If internal node, move the rightmost child of leftSibling to node
                if (!leftSibling.isLeaf()) {
                    node.children.add(0, leftSibling.children.remove(leftSibling.children.size() - 1));
                }
                return;
            }
        }

        // Try to borrow from right sibling
        if (childIndex < parent.children.size() - 1) {
            Node rightSibling = parent.children.get(childIndex + 1);
            if (rightSibling.keys.size() > 1) {
                // Borrow from right sibling
                node.keys.add(parent.keys.get(childIndex));
                parent.keys.set(childIndex, rightSibling.keys.remove(0));

                // If internal node, move the leftmost child of rightSibling to node
                if (!rightSibling.isLeaf()) {
                    node.children.add(rightSibling.children.remove(0));
                }
                return;
            }
        }

        // Merge with a sibling
        if (childIndex > 0) {
            // Merge with left sibling
            Node leftSibling = parent.children.get(childIndex - 1);

            // Add parent key to left sibling
            leftSibling.keys.add(parent.keys.remove(childIndex - 1));

            // Move any children of node to left sibling
            for (Node child : node.children) {
                leftSibling.children.add(child);
            }

            // Remove empty node
            parent.children.remove(childIndex);
        } else {
            // Merge with right sibling
            Node rightSibling = parent.children.get(childIndex + 1);

            // Add parent key to node
            node.keys.add(parent.keys.remove(childIndex));

            // Add all keys from right sibling
            node.keys.addAll(rightSibling.keys);

            // Add all children from right sibling
            node.children.addAll(rightSibling.children);

            // Remove right sibling
            parent.children.remove(childIndex + 1);
        }
    }

    /**
     * Returns a list of all elements in the tree in ascending order.
     * Time Complexity: O(n) - must visit all nodes
     *
     * EXAMPLE: In-order traversal of the following tree:
     *       (5)
     *     /     \
     *   (2)     (7,9)
     *  /  \    /  |  \
     * (1) (3) (6)(8)(10)
     *
     * Result: [1, 2, 3, 5, 6, 7, 8, 9, 10]
     *
     * @return list of all elements in ascending order
     */
    public List<E> inOrderTraversal() {
        List<E> result = new ArrayList<>();
        if (!isEmpty()) {
            inOrderTraversal(root, result);
        }
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

        if (node.isLeaf()) {
            result.addAll(node.keys);
            return;
        }

        // Process first part
        inOrderTraversal(node.children.get(0), result);

        // Add first key
        if (!node.keys.isEmpty()) {
            result.add(node.keys.get(0));
        }

        // Process middle (if 3-node)
        if (node.children.size() > 1) {
            inOrderTraversal(node.children.get(1), result);
        }

        // Add second key (if 3-node)
        if (node.keys.size() > 1) {
            result.add(node.keys.get(1));
        }

        // Process right part (if 3-node)
        if (node.children.size() > 2) {
            inOrderTraversal(node.children.get(2), result);
        }
    }

    /**
     * Returns the height of the tree.
     * Time Complexity: O(log n) - traverses one path from root to leaf
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
        if (node == null || node.isLeaf()) return 1;
        return 1 + height(node.children.get(0)); // All paths to leaves have same length
    }

    /**
     * Returns a string representation of the tree.
     *
     * @return a string representation of the tree
     */
    @Override
    public String toString() {
        if (isEmpty()) return "Empty Tree";

        StringBuilder sb = new StringBuilder();
        toString(root, sb, 0);
        return sb.toString();
    }

    /**
     * Recursive helper for toString method.
     *
     * @param node the current node
     * @param sb the StringBuilder to append to
     * @param level the current level in the tree (for indentation)
     */
    private void toString(Node node, StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }

        sb.append(node.keys);
        sb.append("\n");

        for (Node child : node.children) {
            toString(child, sb, level + 1);
        }
    }

    /**
     * Helper class for returning information about a node split.
     */
    private class NodeReturn {
        E pKey;            // The key to be promoted to the parent
        Node leftChild;    // The left child after split
        Node rightChild;   // The right child after split

        public NodeReturn(E pKey, Node leftChild, Node rightChild) {
            this.pKey = pKey;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    /**
     * Node class for the 2-3 Tree.
     * Each node can have 1 or 2 keys (for 2-node or 3-node).
     */
    private class Node {
        List<E> keys;                // Keys stored in the node (1 for 2-node, 2 for 3-node)
        List<Node> children;         // Children nodes (2 for 2-node, 3 for 3-node)

        /**
         * Constructs an empty node.
         */
        public Node() {
            keys = new ArrayList<>(3);       // Max 3 during temporary overflow
            children = new ArrayList<>(4);   // Max 4 during temporary overflow
        }

        /**
         * Checks if this node is a leaf (has no children).
         *
         * @return true if this is a leaf node, false otherwise
         */
        public boolean isLeaf() {
            return children.isEmpty();
        }
    }
}