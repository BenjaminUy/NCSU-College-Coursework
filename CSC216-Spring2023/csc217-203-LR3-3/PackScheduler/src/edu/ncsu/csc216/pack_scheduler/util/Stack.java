package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Stack interface which models a stack data structure. 
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 * @param <E> a generic type for holding any object in our stack
 */
public interface Stack<E> {
	
	/**
	 * Adds the element to the top of the stack.
	 * @param element the element added
	 * @throws IllegalArgumentException if there is no room
	 */
	void push(E element);
	
	/**
	 * Removes and returns the element at the top of the stack
	 * @return the element removed
	 * @throws EmptyStackException if the stack is empty
	 */
	E pop();
	
	/**
	 * Returns true if the stack is empty
	 * @return true if the stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * The number of elements in the stack
	 * @return the number of elements in the stack
	 */
	int size();
	
	/**
	 * Sets the capacity of our stack.
	 * @param capacity the capacity to set
	 * @throws IllegalArgumentException if the parameter is negative or less than the number of elements currently in the stack
	 */
	void setCapacity(int capacity);

}
