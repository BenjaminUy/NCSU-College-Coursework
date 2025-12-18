package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Queue for our wait list using an arraylist.
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 * @param <E> generic type of any object
 */
public class ArrayQueue<E> implements Queue<E> {

	/** An empty list to hold our queue's objects */
	private ArrayList<E> list;
	
	/** The capacity of our queue. */
	private int capacity;
	
	/**
	 * Constructs our array queue.
	 * @param capacity the capacity of our queue
	 */
	public ArrayQueue(int capacity){
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds the element to the back of the queue
	 * @throws IllegalArgumentException if the queue is at capacity
	 */
	@Override
	public void enqueue(E element) {
		if(capacity == list.size()) {
			throw new IllegalArgumentException("Full capacity");
		}
		list.add(size(), element);
	}

	/**
	 * Removes the element at the front of the queue
	 * and returns it.
	 * @return the element removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if(list.size() == 0) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	/**
	 * Determines if queue is empty
	 * @return true if the queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns size of our queue
	 * @return the size of the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets our list capacity
	 * @param capacity the capacity to set
	 * @throws IllegalArgumentException if the capacity is invalid
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity;
	}

}
