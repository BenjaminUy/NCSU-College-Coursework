package edu.ncsu.csc216.wolf_tickets.model.util;

/**
 * Class that implements the ISortedList interface. ISortedList is a linked list that does not allow for duplicate elements.
 * It has functionality for adding/removing elements, checking for duplicates, getting elements, and checking the list size.
 * @author Benjamin Uy
 * @param <E> generic element type stored in SortedList
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	
	/** Size of the SortedList */
	private int size;
	/** First ListNode in the SortedList */
	private ListNode front;
	
	/**
	 * Constructor for SortedList; sets front to null and size to 0
	 */
	public SortedList() {
		front = null;
		size = 0;
	}
	
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is a duplicate as determined by contains()
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		} else if (contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		
		// Snippet of code from CSC 216 lecture slides on LinkedLists
		if (front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
			
	}

	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		ListNode current = front;
		E removed = null;
		if (idx == 0) {
			removed = current.data;
			front = front.next;
		} else {
			for (int i = 0; i < idx - 1; i++) {
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
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found
	 */
	@Override
	public boolean contains(E element) {
		ListNode current = front;
		if (size == 0) {
			return false;
		}
		if (element.compareTo(current.data) == 0)
			return true;
		while (current.next != null) {
			current = current.next;
			if (element.compareTo(current.data) == 0)
				return true;
		}
		return false;
	}

	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than or equal to size
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Inner class of SortedList representing a list node 
	 * @author Benjamin Uy
	 */
	private class ListNode {
		/** Data in the node of generic type E */
		private E data;
		/** The next node in the list */
		private ListNode next;
		
		/**
		 * Cconstructor of ListNode that takes two parameters, data and the next ListNode.
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
