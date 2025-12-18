package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Custom implementation of a linked list that doesn't allow for null or
 * duplicate elements as defined by equals() method. Class inherits from
 * AbstractSequentialList which provides functionality of standard list methods
 * by implementing them in terms of an Iterator.
 * 
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 * @param <E> generic type E for any object in our list
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** The front of our list */
	private ListNode front;

	/** The back of our list */
	private ListNode back;

	/** The size of our LinkedList */
	private int size;

	/**
	 * Constructs a LinkedList. Sets front and back's data to null, and has back's
	 * previous point to front and front's next point to back.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}

	/**
	 * Method constructs a LinkedListIterator that is positioned at the given index
	 * such that the element at given index is returned in a call to
	 * ListIterator.next()
	 * 
	 * @param index index in the list to position the LinkedListIterator at
	 * @return a ListIterator positioned at the given index
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		ListIterator<E> i = new LinkedListIterator(index);
		return i;
	}

	/**
	 * Adds a new element to the list.
	 * 
	 * @param index   index in the list that element should be added
	 * @param element the element to add
	 * @throws IllegalArgumentException if the element is a duplicate in the list
	 */
	@Override
	public void add(int index, E element) {
		if (contains(element))
			throw new IllegalArgumentException("Duplicate element.");
		ListIterator<E> i = listIterator(index);
		i.add(element);
	}

	/**
	 * Sets an element to a new value.
	 * 
	 * @param index   the index of the node to be set
	 * @param element the new element of the node
	 * @return E the old element
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		if (contains(element))
			throw new IllegalArgumentException("Duplicate element.");
//		ListNode current = front;
//		for (int i = -1; i < index; i++) {
//			current = current.next;
//		}
//		E removed = current.data;
//		current.data = element;
		E removed = super.set(index, element);
		return removed;
	}

	/**
	 * Method returns the size of the LinkedList
	 * 
	 * @return size of the LinkedList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Inner class that implements the ListIterator interface and provides a
	 * concrete implementation of the AbstractSequentialList.
	 * 
	 * @author Hank Lenham
	 * @author Noah Anderson
	 * @author Benjamin Uy
	 */
	private class LinkedListIterator implements ListIterator<E> {

		/** Previous node in list */
		public ListNode prev;

		/** Next node in list */
		public ListNode next;

		/** Integer index of our previous node */
		public int previousIndex;

		/** Integer index of our next node */
		public int nextIndex;

		/** The last node retrieved in our list */
		private ListNode lastRetrieved;

		/**
		 * Constructs the LinkedListIterator positioned at the given index
		 * 
		 * @param index index in the list to start
		 * @throws IndexOutOfBoundsException if index is negative or greater than size
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException("Index out of range.");
			}
			ListNode current = front; // If index is within bounds, iterate through the list so that
			for (int i = -1; i < index; i++) { // the previous ListNode points at the ListNode at index - 1
				if (i == index - 1) {
					prev = current;
				}
				current = current.next;
			}
			next = current; // next ListNode points to the ListNode at index

			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}

		/**
		 * Method determines if the next ListNode has non-null data
		 * 
		 * @return true if the next ListNode has non-null data; false if otherwise
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Method returns the element of the next ListNode and moves the iterator
		 * forward
		 * 
		 * @return the element of the nextListNode
		 * @throws NoSuchElementException if next.data is null
		 */
		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			E temp = next.data;

			// move forward in the list
			next = next.next;
			prev = prev.next;
			
			nextIndex = nextIndex + 1;
			previousIndex = previousIndex + 1;

			return temp;
		}

		/**
		 * Method determines if the previous ListNode has non-null data
		 * 
		 * @return true if the previous ListNode has non-null data; false if otherwise
		 */
		@Override
		public boolean hasPrevious() {
			return prev.data != null;
		}

		/**
		 * Method returns the element of the previous ListNode and moves the iterator
		 * backward
		 * 
		 * @return the element of the prev ListNode
		 * @throws NoSuchElementException if prev.data is null
		 */
		@Override
		public E previous() {
			if (prev.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = prev;
			E temp = prev.data;

			// move backward in the list
			next = next.prev;
			prev = prev.prev;
			
			nextIndex = nextIndex - 1;
			previousIndex = previousIndex - 1;

			return temp;
		}

		/**
		 * Method returns the index of the next ListNode
		 * 
		 * @return index of the next ListNode
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Method returns the index of the previous ListNode
		 * 
		 * @return index of the previous ListNode
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the last retrieved element from the list. Can only be used once in between calls to next() to previous().
		 * @throws IllegalStateException if the last retrieved element doesn't exist
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null)
				throw new IllegalStateException();
			lastRetrieved.prev.next = lastRetrieved.next;
			lastRetrieved = null;
			size--;
		}

		/**
		 * Sets the last retrieved node in the list to an element of the user's choice.
		 * 
		 * @throws IllegalStateException if the last retrieved element doesn't exist
		 * @throws NullPointerException  if the passed element is null
		 */
		@Override
		public void set(E e) {
			if (lastRetrieved == null)
				throw new IllegalStateException();
			if (e == null)
				throw new NullPointerException();
			lastRetrieved.data = e;
		}

		/**
		 * Adds an element to the linked list before the element that would be returned
		 * by next(). Sets lastRetrieved to null and increments the previous index.
		 * 
		 * @param e the element to add
		 * @throws NullPointerException if the passed element is null
		 */
		@Override
		public void add(E e) {
			if (e == null)
				throw new NullPointerException("Null element.");
			
			// Create the new node and have it point to the next and previous nodes
			ListNode add = new ListNode(e, prev, next);
			// Update next.prev and prev.next to point to new node
			prev.next = add;
			next.prev = add;

			// Update previous to reference the added ListNode
			prev = add;

			lastRetrieved = null;
			
			size++;
			previousIndex++;
			nextIndex++;
		}
	}

	/**
	 * Inner class of LinkedList that represents a ListNode
	 * 
	 * @author Hank Lenham
	 * @author Noah Anderson
	 * @author Benjamin Uy
	 */
	private class ListNode {

		/** Data contained in node */
		public E data;

		/** Next node in list */
		public ListNode next;

		/** Previous node in list */
		public ListNode prev;

		/**
		 * Constructor of ListNode that takes in the parameter for ListNode data
		 * 
		 * @param data element contained in node
		 */
		public ListNode(E data) {
			this.data = data;
		}

		/**
		 * Constructor of ListNode that takes in three parameters for a ListNode's next
		 * and previous ListNodes along with the data contained
		 * 
		 * @param data element contained in node
		 * @param prev previous node in list
		 * @param next next node in list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
}