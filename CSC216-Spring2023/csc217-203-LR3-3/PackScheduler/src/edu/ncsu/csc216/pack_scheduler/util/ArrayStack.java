package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * ArrayStack is a stack that implements our stack interface.
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 * @param <E> generic type for any object
 */
public class ArrayStack<E> implements Stack<E> {

	/** An ArrayList to hold our elements */
	private ArrayList<E> list;
	
	/** The maximum number of elements that can be in the stack */
	private int capacity;
	
	/** The size of our stack */
	private int size;
	
	/**
	 * Constructing a new ArrayStack based on capacity
	 * @param capacity the capacity of our stack
	 */
	public ArrayStack(int capacity){
		list = new ArrayList<E>();
		setCapacity(capacity);
		size = 0;
	}
	
	/**
	 * Pushes a new element to the top of the stack.
	 * @param element the element to push to the stack
	 * @throws IllegalArgumentException if the stack is full
	 */
	@Override
	public void push(E element) {
		if (size == capacity)
			throw new IllegalArgumentException("Full capacity");
		list.add(0, element);
		size++;
	}

	/**
	 * Pops an element from the top of the stack.
	 * @return E the element that was popped
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (size == 0)
			throw new EmptyStackException();
		size--;
		return list.remove(0);
	}

	/**
	 * Determines if the stack is empty.
	 * @return boolean true if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list.
	 * @return int the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the capacity of the stack by delegating to the LinkedAbstractList
	 * @param capacity the maximum number of elements that can be in the stack
	 * @throws IllegalArgumentException if the capacity is negative or less than the current size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size())
			throw new IllegalArgumentException("Invalid capacity");
		this.capacity = capacity;
	}

}
