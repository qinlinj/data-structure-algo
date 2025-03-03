package org.qinlinj.nonlinear.highlevel.set;

public interface Set<E> {
    /**
     * Returns the number of elements in the set.
     *
     * @return the total count of unique elements in the set
     */
    int size();

    /**
     * Checks if the set contains no elements.
     *
     * @return true if the set is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Adds the specified element to the set if it is not already present.
     * If the set already contains the element, the set remains unchanged.
     *
     * @param e the element to be added to the set
     */
    void add(E e);

    /**
     * Removes the specified element from the set if it is present.
     * If the set does not contain the element, no action is taken.
     *
     * @param e the element to be removed from the set
     */
    void remove(E e);

    /**
     * Checks whether the specified element is contained in the set.
     *
     * @param e the element to check for presence in the set
     * @return true if the set contains the specified element, false otherwise
     */
    boolean contains(E e);
}

