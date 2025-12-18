package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * Class that implements the ISwapList interface. SwapList is a custom array list that allows for duplicate elements
 * and has similar functionality to that of a normal List such as add, remove, and size. It also has unique functionality
 * for moving an element up, down, to the front of the list, and to the back of the list.
 * @author Benjamin Uy
 * @param <E> generic element type stored in SwapList
 */
public class SwapList<E> implements ISwapList<E> {
	
	/** Constant for the initial capacity of SwapList */
	private static final int INITIAL_CAPACITY = 10;
	/** Array of generic elements */
	private E[] list;
	/** Size of the SwapList */
	private int size;
	
	/**
	 * Constructor of SwapList; creates an array for generic elements with a capacity of 10 and size is set to 0
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		// Check if capacity is full and a new array is needed before adding
		checkCapacity(size);
		
		list[size()] = element;	// element is added at the index equal to current array's size
		size++;
		
	}
	
	/**
	 * Creates new array with double capacity, copies old array to new array
	 * @param size current size of the list
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int size) {
		if (list.length == size) {
			E[] newList = (E[]) new Object[list.length * 2];
			for (int i = 0; i < size; i++) {
				newList[i] = list[i];
			}
			list = newList;
		}
	}

	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E removed = get(idx);
		for (int i = idx; i < size() - 1; i++) {	// Shift elements right of remove element to the left
			list[i] = get(i + 1);
		}
		list[size - 1] = null;						// After shift, the last filled spot in array is set to null
		size--;										// Decrement size
		return removed;
	}
	
	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveUp(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E moved = get(idx);
		if (idx != 0) {					// Ensure that if given index is 0, the list won't change
			E temp = list[idx - 1]; 	// Create placeholder used for swapping elements
			list[idx - 1] = moved;
			list[idx] = temp;
		}
	}

	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if  idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveDown(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E moved = get(idx);
		if (idx != size() - 1) {		// Ensure that if given index is size - 1, the list won't change
			E temp = list[idx + 1];		// Create placeholder used for swapping elements
			list[idx + 1] = moved;
			list[idx] = temp;
		}
	}

	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToFront(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E moved = get(idx);
		if (idx != 0) {					// Ensure that if given index is 0, the list won't change
			for (int i = idx; i > 0; i--) {
				list[i] = list[i - 1];
			}
			list[0] = moved;
		}
	}

	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToBack(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E moved = get(idx);
		if (idx != size() - 1) {		// Ensure that if given index is size - 1, the list won't change
			for (int i = idx; i < size(); i++) {
				list[i] = list[i + 1];
			}
			list[size() - 1] = moved;
		}
	}

	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return list[idx];
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}
