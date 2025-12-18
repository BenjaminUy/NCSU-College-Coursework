package edu.ncsu.csc316.dsa.list;

/**
 * A skeletal implementation of the List abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the list
 * abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public abstract class AbstractList<E> implements List<E> {

	/**
	 * Adds the given element to the start of the List.
	 * @param element generic element to be added to the start of the List
	 */
    @Override
    public void addFirst(E element) {
        add(0, element);
    }

	/**
	 * Adds the given element to the end of the List.
	 * @param element generic element to be added to the end of the List
	 */
    @Override
    public void addLast(E element) {
        add(size(), element);
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when accessing any specific
     * indexes within the list.
     * 
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when adding elements at specific
     * indexes within the list.
     * 
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndexForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

	/**
	 * Returns the first element of the List
	 * @return the element at index 0 of the List
	 */
    @Override
    public E first() {
        return get(0);
    }

    /**
     * Returns true if the List is empty and false if not
     * @return true if the size of the List is 0 and false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the last element of the List
     * @return the element at index (size() - 1) of the List
     */
    @Override
    public E last() {
        return get(size() - 1);
    }

    /**
     * Returns and removes the first element of the List
     * @return the first element of the List
     */
    @Override
    public E removeFirst() {
        return remove(0);
    }

    /**
     * Returns and removes the last element of the List
     * @return the last element of the List
     */
    @Override
    public E removeLast() {
        return remove(size() - 1);
    }
}