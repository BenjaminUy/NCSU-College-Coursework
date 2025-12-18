package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /** Delegate to our existing singly linked list class **/
    private SinglyLinkedList<E> list;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }
    
    /**
     * Adds the element to the top of the stack
     * @param value element to add to the top of the stack
     */
    public void push(E value) {
    	list.addFirst(value);
    }
    
    /**
     * Removes and returns the element from the top of the stack
     * @return the element from the top of the stack
     * @throws EmptyStackException if the list is empty
     */
    public E pop() {
    	if (isEmpty()) {
    		throw new EmptyStackException();
    	}
    	return list.removeFirst();
    }
    
    /**
     * Returns, but does not remove the element from the top of the stack
     * @return the element from the top of the stack
     * @throws EmptyStackException if the list is empty
     */
    public E top() {
    	if (isEmpty()) {
    		throw new EmptyStackException();
    	}
    	return list.first();
    	
    }
    
    /**
     * Returns the number of elements in the stack
     * @return the number of elements in the stack 
     */
    public int size() {
    	return list.size();
    }
}