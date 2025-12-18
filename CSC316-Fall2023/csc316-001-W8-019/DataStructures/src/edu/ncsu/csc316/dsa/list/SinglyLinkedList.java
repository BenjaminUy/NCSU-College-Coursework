package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;    
    
    private static class LinkedListNode<E> {
        
        private E data;
        private LinkedListNode<E> next;
        
        /**
         * Constructs a LinkedListNode with a null next reference
         * @param data element stored in the LinkedListNode
         */
        public LinkedListNode(E data) {
        	this.data = data;
        	this.next = null;
        }
        
        /**
         * Constructs a LinkedListNode with the given parameters for data and next
         * @param data element stored in the LinkedListNode
         * @param next LinkedListNode that this LinkedListNode points to
         */
        public LinkedListNode(E data, LinkedListNode<E> next) {
        	this.data = data;
        	this.next = next;
        }
    }
    
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }
    
    /**
     * Adds a new LinkedListNode in the SinglyLinkedNode at the given index with the given value.
     * @param index index in the list to add the given value
     * @param value element to add to the list
     */
    public void add(int index, E value) {
    	checkIndexForAdd(index);
    	
    	// Adding at the beginning of the list
    	if (index == 0) {
    		LinkedListNode<E> placeholder = new LinkedListNode<E>(value, front.next);
    		front.next = placeholder;
    	} else {
    		// Other cases of adding, in-between and including to the back
        	LinkedListNode<E> addition = front;
        	for (int i = 0; i < index; i++) {
        		addition = addition.next;
        	}
        	addition.next = new LinkedListNode<E>(value, addition.next);
    	}
    	size++;
    	
    	// Update to find the tail
    	LinkedListNode<E> traversal = front;
    	for (int i = 0; i < size(); i++) {
    		traversal = traversal.next;
    	}
    	tail = traversal;
    }
    
    /**
     * Removes the linked list node at the given index and returns the data stored in that node
     * @param index position of the linked list node to be removed
     * @return data stored in the linked list node at the given index
     */
    public E remove(int index) {
    	checkIndex(index);
    	E placeholder = get(index);
    	LinkedListNode<E> traversal = front;
    	
    	if (index == 0) {						// Removing from the beginning
    		if (size() == 1) {
    			new SinglyLinkedList<E>();
    		} else {
    			front.next = front.next.next;	// At this point, we know that size is at least 2
    		}
    	} else if (index == size() - 1) {		// Removing from the end
    		for (int i = 0; i < index - 1; i++) {
    			traversal = traversal.next;
    		}
    		tail = new LinkedListNode<E>(traversal.data);
    	} else {
    		traversal = front.next;
    		for (int i = 0; i < index - 1; i++) {
    			traversal = traversal.next;
    		}
    		traversal.next = traversal.next.next;
    	}
    	
    	size--;

    	// Update to find the tail
    	LinkedListNode<E> updateTail = front;
    	for (int i = 0; i < size(); i++) {
    		updateTail = updateTail.next;
    	}
    	tail = updateTail;
    	return placeholder;
    }
    
    /**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.data;
    }

    /**
     * Creates and adds a new linked list node with the given element to the end of the SinglyLinkedList.
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     * @param element element to add to the end of the SinglyLinkedList
     */    
    @Override
    public void addLast(E element) {
    	if (size() == 0) {
    		LinkedListNode<E> placeholder = new LinkedListNode<E>(element, front.next);
    		front.next = placeholder;
    		tail = front.next;
    	} else {
	        LinkedListNode<E> newTail = new LinkedListNode<E>(element);
	        tail.next = newTail;
	        tail = newTail;
    	}
        size++;
    }
    
    /**
     * Returns the data stored in the LinkedListNode at the given index
     * @param index position of the LinkedListNode to retrieve data from
     * @return data of the LinkedListNode at the given index
     */
    public E get(int index) {
    	checkIndex(index);
    	
    	if (index == 0) {
    		return front.next.data;
    	} else if (index == size() - 1) {
    		return tail.data;
    	}
    	
    	LinkedListNode<E> traverse = front.next; 
    	for (int i = 1; i <= index; i++) {
    		traverse = traverse.next;
    	}
    	return traverse.data;
    }
    
    /**
     * Updates the data stored in the LinkedListNode at the given index
     * @param index position of the LinkedListNode to update data
     * @param value updated data of the LinkedListNode
     * @return original data of the LinkedListNode at the given index
     */
    public E set(int index, E value) {    	
    	checkIndex(index);
    	E placeholder = get(index);
    	
    	// Updating the beginning of the list
    	if (index == 0) {
    		if (size() == 0) {
    			front.next = new LinkedListNode<E>(value);
    			tail = front.next;	
    		} else {
    			front.next = new LinkedListNode<E>(value, front.next.next);
    		}
    	} else if (index == size() - 1) {		// Updating the end of the list and assuming size is at least 2
    		LinkedListNode<E> traverse = front.next;
    		for (int i = 0; i < size() - 2; i++) {
    			traverse = traverse.next;
    		}
    		traverse.next = new LinkedListNode<E>(value);
    		tail = traverse.next;													// May need to look into this
    	} else {								// Updating anywhere in the list and assuming size is at least 3
    		LinkedListNode<E> traverse = front.next;
    		for (int i = 0; i < index - 1; i++) {
    			traverse = traverse.next;
    		}
    		traverse.next = new LinkedListNode<E>(value, traverse.next.next);		// Including this
    	}    	
    	return placeholder;
    }
    
    /**
     * Returns the size of the SinglyLinkedList
     * @return the number of linked nodes in the SinglyLinkedList
     */
    public int size() {
    	return size;
    }
    
    /**
     * Inner class that represents an iterator which interacts with the generic E elements of the Singly Linked List
     * @author Benjamin Uy
     */
    private class ElementIterator implements Iterator<E> {
    	
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
        	this.current = front.next;
        }

        /**
         * Returns whether there is a next LinkedListNode associated with the current LinkedListNode
         * @return true if there is a next LinkedListNode after current, false otherwise
         */
        @Override
        public boolean hasNext() {
            return (current != null);
        }

        /**
         * Returns the data stored at the current LinkedListNode, then updates current to current.next
         * @return data stored at the current LinkedListNode
         * @throws NoSuchElementException if hasNext() returns false
         */
        @Override
        public E next() {
        	if (!hasNext()) {
        		throw new NoSuchElementException();
        	}
        	E placeholder = current.data;
            current = current.next;
            return placeholder;
        }
        
        /**
         * This method, when called, will through an UnsupportedOperationException because the implementation of SinglyLinkedList
         * does not support the removal of elements when using the iterator
         * @throws UnsupportedOperationException when this method is called
         */
        @Override    
        public void remove() {
    	    // DO NOT CHANGE THIS METHOD
            throw new UnsupportedOperationException(
                "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
        }
    }

    /**
     * Inherited method from the Iterable interface that returns an Iterator object
     * @return an ElementIterator which is of object type Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
}