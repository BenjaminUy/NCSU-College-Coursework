/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom implementation of an array list in that null or duplicate elements
 * are not allowed, and the array list has a set capacity
 * @author ajwdr
 * @author bsuy
 * @author hctynch
 * @param <E> generic type parameter that this list contains
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** First ListNode in the list of generic type E */
	private ListNode front;
	/** Size of the list */
	private int size;
	/** Capacity of the list */
	private int capacity;
	
	/**
	 * Constructor of LinkedAbstractList. Initializes the state so that front will be null,
	 * size will be 0, and capacity will be assigned to the parameter value.
	 * @param capacity capacity of the list
	 * @throws IllegalArgumentException if the given parameter is less than 0
	 * @throws IllegalArgumentException if the capacity is less than the current list's size
	 */
	public LinkedAbstractList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		front = null;
		size = 0;
		if (capacity < size) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity;
	}
	
	/**
	 * Method returns the size of the list (how many elements are there)
	 * @return integer representing the list size
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Method adds a new list node to the LinkedAbstractList at the given index with the given element
	 * @param index index in the list that the element should be added
	 * @param element data type that is stored in a ListNode
	 * @throws IllegalArgumentException if size is equal to capacity
	 * @throws NullPointerException if the given element to add is null
	 * @throws IllegalArgumentException if the method to add is a duplicate of
	 * 		another element in the list as determined by equals()
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
	 */
	@Override
	public void add(int index, E element) {
		if (size() == capacity) {
			throw new IllegalArgumentException("Full capacity");
		}
		if (element == null) {
			throw new NullPointerException("Invalid element");
		}
		for (int i = 0; i < size; i++) {
			if (element.equals(get(i))) {
				throw new IllegalArgumentException("Duplicate element");
			}
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		ListNode current = front;
		if (index == 0) {
			front = new ListNode(element, current);
		} else if (index < size){
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		} else if (index == size) {
			while (current.next != null) {
				current = current.next;
			}
			current.next = new ListNode(element);
		}
		size++;
	}
	
	/**
	 * Method will remove the elements at the given index. If there is only one element in
	 * the list, the front reference is updated; otherwise, all references are updated to 
	 * "go around" the removed element.
	 * @return removed elements at the given index of the list
	 * @throws IndexOutOfBoundsException if index is less than 0 or is greater than or equal to size
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		ListNode current = front;
		E removed = null;
		if (index == 0) {
			removed = current.data;
			front = front.next;
		} else {
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			// Once the index is reached, get the element to remove
			removed = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return removed;
	}
	
	/**
	 * Returns the element at the index.
	 * @param index the index of the element to get
	 * @return the element at the index
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		ListNode current = front;
		if (index == 0) {
			return current.data;
		}
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	/**
	 * Set the element at the index to the param element.
	 * @param index the index of the element to replace
	 * @param element the element to replace the indexed element with
	 * @return oldElement the element from the old list that is replaced
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than size
	 */
	@Override
	public E set(int index, E element) {
		if (element == null)
			throw new NullPointerException("Null element");
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Invalid index");
		ListNode check = front;
		for (int i = 0; i < size(); i++) {
			if (check.data.equals(element))
				throw new IllegalArgumentException("Duplicate element");
			check = check.next;
		}
		ListNode current = front;
		ListNode old = null;
		if (index == 0) {
			old = front;
			front = new ListNode(element, front.next);
		} else {
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			old = current.next;
			current.next = new ListNode(element, current.next.next);
		}
		return old.data;
	}
	
	/**
	 * Sets the capacity.
	 * @param capacity the capacity to set
	 * @throws IllegalArgumentException if the capacity to set is less than size
	 */
	public void setCapacity(int capacity) {
		if (capacity < size())
			throw new IllegalArgumentException("Invalid capacity");
		this.capacity = capacity;
	}

	/**
	 * Inner class of LinkedAbstract representing a list node 
	 * @author ajwdr
	 * @author bsuy
	 * @author hctynch
	 */
	private class ListNode {
		/** Data in the node of generic type E */
		private E data;
		/** The next node in the list */
		private ListNode next;
		
		/**
		 * First constructor of ListNode that takes one parameter for data.
		 * The created ListNode from this method is not linked to a following ListNode.
		 * @param data data in the node
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}
		
		/**
		 * Second constructor of ListNode that takes two parameters, data and the next ListNode.
		 * The created ListNode from this method is linked to a following ListNode.
		 * @param data data in the node
		 * @param next following node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
