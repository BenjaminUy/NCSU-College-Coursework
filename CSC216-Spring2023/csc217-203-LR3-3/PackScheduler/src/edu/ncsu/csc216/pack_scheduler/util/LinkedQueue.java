package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * LinkedQueue uses a linked list and implements our Queue
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 * @param <E> generic type for any object in our queue
 */
public class LinkedQueue<E> implements Queue<E> {

	/** Constructing an empty list for our queue to use */
	private LinkedAbstractList<E> list;
	
	/** The size of our list. */
	private int size;
	
	/** Our lists capacity. */
	private int capacity;
	
	/** 
	 * Constructs our LinkedQueue.
	 * @param capacity the capacity of our queue.
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
		size = 0;
	}
	
	/**
	 * Adds the element to the back of the queue
	 * @throws IllegalArgumentException if the queue is at capacity
	 */
	@Override
	public void enqueue(E element) {
		if(capacity == size) {
			throw new IllegalArgumentException("Full capacity.");
		}
		list.add(size, element);
		size++;
	}

	/**
	 * Remove the element at the front of the queue
	 * and return it
	 * @return the element removed.
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E dequeue() {
		if(list.size() == 0) {
			throw new NoSuchElementException();
		}
		size--;
		return list.remove(0);
	}

	/**
	 * Determines if the queue is empty
	 * @return true if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list.
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the capacity of our list
	 * @param capacity the maximum number of elements that can be in our queue
	 * @throws IllegalArgumentException if the capacity is invalid
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity;
	}
	
	/**
	 * Helper method to determine if our linked queue contains an object
	 * @param e the object we will be checking against
	 * @return true if the object is found in our list, false if not
	 */
	public boolean contains(E e) {
		for(int i = 0; i < size; i++) {
			if(list.get(i).equals(e)) {
				return true;
			}
		}
		return false;
	}

}
