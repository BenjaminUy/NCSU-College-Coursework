package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Allows us to queue students in our wait lists. Used by ArrayQueue and LinkedQueue.
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 * @param <E> generic type for any object
 */
public interface Queue<E> {
	
	/**
	 * Adds the element to the back of the queue
	 * @param element the element to add
	 * @throws IllegalArgumentException if the capacity is met
	 */
	void enqueue(E element);
	
	/**
	 * Removes and returns the element from the front of the queue
	 * @return the element removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	E dequeue();
	
	/**
	 * Returns true if the queue is empty
	 * @return true if the queue is empty, false if not
	 */
	boolean isEmpty();
	
	/**
	 * Number of elements in the queue
	 * @return the number of elements in the queue
	 */
	int size();
	
	/**
	 * Sets the queue's capacity
	 * @param capacity the capacity of the queue
	 */
	void setCapacity(int capacity);
}
