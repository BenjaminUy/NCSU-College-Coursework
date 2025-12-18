package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This class models a linked list using recursive functions.
 * 
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 * @param <E> the generic object contained in the linked list
 */
public class LinkedListRecursive<E> {
	/** The number of elements in the linked list. */
	private int size;
	/** The first ListNode in the linked list. */
	private ListNode front;

	/**
	 * Constructs the recursive linked list with an empty list and a size of 0.
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * Returns the size of the list.
	 * 
	 * @return the size
	 */
	public int size() {
		return size;
	}

	/**
	 * Finds if the list is empty.
	 * 
	 * @return true if the size is 0, false if not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Recursively searches for an element in the list.
	 * 
	 * @param element the element to search for
	 * @return true if the list contains the element, false if not found
	 */
	public boolean contains(E element) {
		if (isEmpty())
			return false;
		else {
			return front.contains(element);
		}
	}

	/**
	 * Recursively adds an element to the end of the linked list.
	 * 
	 * @param element the element to add
	 * @return true if the element is added
	 * @throws IllegalArgumentException if the element is a duplicate
	 */
	public boolean add(E element) {
		if (isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return true;
		} else if (contains(element))
			throw new IllegalArgumentException();
		else
			return front.add(element);
	}

	/**
	 * Adds a new ListNode with the specified element at the specified index using
	 * recursion.
	 * 
	 * @param idx     the index to insert the element at
	 * @param element the element to add to the list
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if the list contains the element
	 */
	public void add(int idx, E element) {
		if (idx < 0 || idx > size)
			throw new IndexOutOfBoundsException();
		else if (element == null)
			throw new NullPointerException();
		else if (contains(element))
			throw new IllegalArgumentException();
		else if (idx == 0) {
			front = new ListNode(element, front);
			size++;
		} else {
			front.add(idx - 1, element);
			size++;
		}
	}

	/**
	 * Gets an element at a particular index
	 * 
	 * @param idx the index of the element
	 * @return the element at that index
	 */
	public E get(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(idx);
	}
	
	/**
	 * Removes and returns the element at a particular index.
	 * @param idx the index of the element to remove
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is negative or greater than or equal to size
	 */
	public E remove(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		} else if (front == null) {
			return null;
		} else if (idx == 0) {
			E temp = front.data;
			if (front.next != null)
				front = front.next;
			size--;
			return temp;
		} else {
			return front.remove(idx - 1);
		}
	}
	
	/**
	 * Removes an element in our recursive list.
	 * @param element the element we will remove
	 * @return true if we can remove the element, false if not
	 */
	public boolean remove(E element) {
		if (element == null)
			return false;					// Not sure if we throw an exception
		if (isEmpty())
			return false;
		if (front.data == element) {
			front = front.next;
			size--;
			return true;
		}
		return front.remove(element);
	}
	
	/**
	 * Sets an element at a certain spot in the list to be whatever we want.
	 * @param idx the index of the place to set
	 * @param element the element to set
	 * @return the element replaced
	 * @throws IndexOutOfBoundsException if the index is negative or greater than or equal to size
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if the list already contains element
	 */
	public E set(int idx, E element) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		} else if (element == null) {
			throw new NullPointerException();
		} else if (contains(element)) {
			throw new IllegalArgumentException();
		}
		return front.set(idx, element);
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

		/**
		 * Constructor of ListNode that takes in the parameter for ListNode data
		 * 
		 * @param data element contained in node
		 * @param next the location of the next listNode
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * Adds an element to the next node if it is null. If not, recursively passes to
		 * the next node.
		 * 
		 * @param element the element to add
		 * @return true if the element is added
		 */
		public boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			}
			return next.add(element);
		}

		/**
		 * Adds an element to the list at the specified index using recursion.
		 * 
		 * @param idx     the index to insert the element at
		 * @param element the element to add
		 */
		public void add(int idx, E element) {
			if (idx == 0) {
				next = new ListNode(element, next);
			}
			else if (next != null)
				next.add(idx - 1, element);
			// Note that we increment size after this method is finished calling itself recursively

		}

		public E get(int idx) {
			if (idx == 0) {
				return data;
			} else {
				
				return next.get(idx - 1);
			}
		}

		public E remove(int idx) {
//			if (idx == 1) {
//				E temp = next.data;
//				next = front.next;
//				size--;
//				return temp;
//			}
			if (idx == 0) {
				E temp = next.data;
				next = next.next;
				size--;
				return temp;
			}
			else {
				return next.remove(idx - 1);
			}
		}

		public boolean remove(E element) {
			if (next == null) {
				return false;
			}
			if (next.data == element) {
				next = next.next;
				size--;
				return true;
			}
			return next.remove(element);
			
		}

		public E set(int idx, E element) {
			if (idx == 0) {
				E temp = data;
				data = element;
				return temp;
			}
			return next.set(idx - 1, element);
		}

		/**
		 * The private helper method that recursively searches for an element in each
		 * node.
		 * 
		 * @param element the element to search for
		 * @return true if the element is found in the node, false if not found
		 */
		public boolean contains(E element) {
			if (data == element)
				return true;
			else if (next != null)
				return next.contains(element);
			else
				return false;
		}
	}
}