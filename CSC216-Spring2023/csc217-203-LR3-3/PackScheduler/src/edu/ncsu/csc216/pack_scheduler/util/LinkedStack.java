package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * LinkedStack implements our stack interface.
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 * @param <E> generic type for any object in our stack
 */
public class LinkedStack<E> implements Stack<E>  {

	/** Empty LinkedAbstractList for our stack. */
	private LinkedAbstractList<E> list;
	
	/**
	 * Constructs our LinkedStack.
	 * @param capacity the capacity of our stack
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Pushes an element to the top of the stack.
	 * @param element the element to be pushed
	 */
	@Override
	public void push(E element) {
		list.add(0, element);
	}

	/**
	 * Pops an element from the top of the stack.
	 * @return E the element that was popped
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		try {
			return list.remove(0);
		} catch (IndexOutOfBoundsException e) {
			throw new EmptyStackException();
		}
	}

	/**
	 * Determines if the stack is empty.
	 * @return boolean true if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the size of the list.
	 * @return int the size of the list
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the stack by delegating to the LinkedAbstractList
	 * @param capacity the maximum number of elements that can be in the stack
	 */
	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
	}

}
