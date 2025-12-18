package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }

	@Override
	public void add(int index, E element) {
		ensureCapacity(size + 1);
		
		// Shift the elements to the right to allow for insertion
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = element;
		size++;
	}

    /**
     * Returns the element at the specified index of the list
     * 
     * @param index the index at which to retrieve the element
     * @return the element at the specified index of the list
     * @throws IndexOutOfBoundsException if the specified index is not a valid index
     *                                   based on the current state of the list
     */
	@Override
	public E get(int index) {
		if (index >= data.length) {
			throw new IndexOutOfBoundsException("Out of range");
		}
		return data[index];
	}

	/**
	 * Returns and removes the element at the given index
	 * @return the element at the given index of the list
	 */
	@Override
	public E remove(int index) {
		checkIndex(index);
		
		E removed = get(index);
		
		// Shift the elements to the left during removal
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		
		if (index == size - 1) {
			data[index] = null;
		}
		
		size--;
		return removed;
	}

	/**
	 * Updates the location of the list at the given index to the given element
     * @param index   the index at which an existing element should be updated
     * @param element the new element to store are the provided index
     * @return the original element that was replaced by the updated element
	 * @throws IndexOutOfBoundsException if the given index is out of range
	 */
	@Override
	public E set(int index, E element) {
		if (index >= data.length) {
			throw new IndexOutOfBoundsException("Out of range");
		}
		E original = data[index];
		data[index] = element;
		return original;
	}

	/**
	 * Returns how many elements are in the array-based list
	 * @return number of elements that are in the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    /**
     * Returns an ElementIterator which is an Iterator that interacts with generic elements
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    /**
     * Inner class that represents an iterator which interacts with the generic E elements of the ArrayBasedList
     * @author Benjamin Uy
     */
	private class ElementIterator implements Iterator<E> {
		/** Integer which represents the position or index of the array */
	    private int position;
	    /** Boolean that determines if a remove operation is allowed */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	        this.position = 0;
	        this.removeOK = false;
	    }
	    
	    /**
	     * Determines if the ElementIterator has more positions after this one
	     * @return true if position is less than the array size, false if not
	     */
	    @Override
	    public boolean hasNext() {
	    	return position < size();
	    }

	    /**
	     * Method returns the element of the original position before position is incremented
	     * @return the element of the original position
	     * @throws NoSuchElementException if the call to hasNext() is false
	     */
	    @Override
	    public E next() {
	        if (!hasNext()) {
	        	throw new NoSuchElementException();
	        }
	        E placeholder = get(position);
	        position++;
	        removeOK = true;
	        return placeholder;
	    }
	        
	    /**
	     * Removes the position that was called from next() and decrements position
	     * @throws IllegalStateException() if removeOK is false
	     */
	    @Override
	    public void remove() {
	        if (!removeOK) {
	        	throw new IllegalStateException();
	        }
	        ArrayBasedList.this.remove(position - 1);
	        position--;
	        removeOK = false;
	    }
	}
}