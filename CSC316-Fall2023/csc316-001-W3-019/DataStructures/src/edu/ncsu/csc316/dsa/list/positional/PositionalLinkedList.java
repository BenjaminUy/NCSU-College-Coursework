package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

    /**
     * Inner class representing a Position in the PositionalLinkedList
     * A PositionalNode<E> has fields for its element and its next/previous PositionalNodes 
     * @author Benjamin Uy
     * @param <E> generic element that is used by this class
     */
    private static class PositionalNode<E> implements Position<E> {

    	/** Generic element or data stored in this object */
        private E element;
        /** The next PositionalNode after this object */ 
        private PositionalNode<E> next;
        /** The previous PositionalNode after this object */
        private PositionalNode<E> previous;

        /**
         * Constructs a new PositionalNode with the given value and a null next
         * @param value element stored in the PositionalNode
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * Constructs a new PositionalNode with the given value and next PositionalNode
         * @param value element stored in the PositionalNode
         * @param next the next PositionalNode after this object
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Constructs a new PositionalNode with the given value, and next/previous PositionalNode
         * @param value element stored in the PositionalNode
         * @param next the next PositionalNode after this object
         * @param prev the previous PositionalNode after this object
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Sets the previous PositionalNode to the given PositionalNode
         * @param prev the PositionalNode to set the previous field to
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Returns the PositionalNode in the previous field
         * @return the PositionalNode of the previous field
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Sets the next PositionalNode to the given PositionalNode
         * @param next the PositionalNode to set the next field to
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Returns the PositionalNode in the next field
         * @return the PositionalNode of the next field
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        /**
         * Returns the element stored in the PostionalNode
         * @return the element stored in the PositionalNode
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the element stored in the PositionalNode to the given parameter
         * @param element the value to set the element field to
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * Adds a Position in between the given PositionalNodes and returns the newly created Position
     * @param element element of the PositionalNode
     * @param next the next PositionalNode
     * @param prev the previous PositionalNode
     * @return the newly created Position
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
        Position<E> addition = new PositionalNode<E>(element, next, prev);
        prev.next = validate(addition);
        next.previous = validate(addition);
        size++;
        return addition;
    }
    
    /**
     * Adds a Position after the given Position and returns the newly created Position
     * @param p the Position to add after
     * @param value value of the PositionalNode
     * @return the newly created Position
     */
    public Position<E> addAfter(Position<E> p, E value) {
    	PositionalNode<E> validatedPosition = validate(p);
    	return addBetween(value, validatedPosition.getNext(), validatedPosition);
    }
   
    /**
     * Adds a Position before the given Position and returns the newly created Position
     * @param p the Position to add before
     * @param value value of the PositionalNode
     * @return the newly created Position
     */
    public Position<E> addBefore(Position<E> p, E value) {
    	PositionalNode<E> validatedPosition = validate(p);
    	return addBetween(value, validatedPosition, validatedPosition.getPrevious());
    }
    
    /**
     * Adds a Position in the beginning of the PositionalLinkedList
     * @param value value of the PositionalNode
     * @return the newly created Position
     */
    public Position<E> addFirst(E value) {
    	return addBetween(value, front.getNext(), front);
    }
    
    /**
     * Adds a Position to the end of the PositionalLinkedList
     * @param value value of the PositionalNode
     * @return the newly created Position
     */
    public Position<E> addLast(E value) {
    	return addBetween(value, tail, tail.getPrevious());
    }
    
    /**
     * Returns the Position after the given Position. Returns null if there is no Position after
     * the given Position
     * @param p Position whose next Position will be checked
     * @return the Position after the given Position and null if the next Position's element is null
     * 		meaning that it is a sentinel/dummy node
     */
    public Position<E> after(Position<E> p) {
    	PositionalNode<E> current = validate(p);
    	if (current.getNext().getElement() == null) {
    		return null;
    	}
    	return current.getNext();
    }
    
    /**
     * Returns the Position before the given Position. Returns null if there is no Position before
     * the given Position
     * @param p Position whose previous Position will be checked
     * @return the Position before the given Position and null if the previous Position's element is null
     * 		meaning that it is a sentinel/dummy node
     */
    public Position<E> before(Position<E> p) {
    	PositionalNode<E> current = validate(p);
    	if (current.getPrevious().getElement() == null) {
    		return null;
    	}
    	return current.getPrevious();
    }
    
    /**
     * Returns the Position after the front sentinel node and null if the list is empty
     * @return the Position after the front sentinel node, if it exists
     */
    public Position<E> first() {
    	if (isEmpty()) {
    		return null;
    	}
    	return front.getNext();
    }
    
    /**
     * Returns the Position before the tail sentinel node and null if the list is empty
     * @return the Position before the tail sentinel node, if it exists
     */
    public Position<E> last() {
    	if (isEmpty()) {
    		return null;
    	}
    	return tail.getPrevious();
    }
    
    /**
     * Returns whether the PositionalLinkedList contains any non-sentinel Positions
     * @return true if the PositionalLinkedList contains no elements
     */
    public boolean isEmpty() {
    	if (size == 0) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Removes and returns the element at position p (eliminating the position)
     * @param p Position that will be removed from the PositionalLinkedList
     * @return the element of the removed position p
     */
    public E remove(Position<E> p) {
    	E placeholder = p.getElement();
    	
    	PositionalNode<E> removed = validate(p);
    	
    	// Update the previous and next references of this position's positional nodes to
    	// 'remove' it from the list
    	removed.getPrevious().next = removed.getNext();
    	removed.getNext().previous = removed.getPrevious();
    	
    	size--;
    	
    	return placeholder;
    }
    
    /**
     * Replaces the element at position p, and returns the original element at the position
     * @param p Position that will be updated
     * @param value generic element that the Position's element will be set to
     * @return the original element at position p
     */
    public E set(Position<E> p, E value) {
    	PositionalNode<E> update = validate(p);
    	// Get original element from the position
    	E placeholder = update.getElement();
    	// Update the element of the position
    	update.setElement(value);
    	return placeholder;
    }
    
    /**
     * Returns how many non-sentinel Positions are in the PositionalLinkedList
     * @return size of the PositionalLinkedList
     */
    public int size() {
    	return size;
    }
    
    /**
     * Inner class that allows the client to create an iterator to visit each 
     * Position in the list
     * @author Benjamin Uy
     */
    private class PositionIterator implements Iterator<Position<E>> {
    	/** Field for current Position node in the PositionalLinkedList */
        private Position<E> current;
        /** Boolean that determines whether a remove operation can be done */
        private boolean removeOK;

        /**
         * Constructs a new PositionIterator with default fields
         */
        public PositionIterator() {
            this.current = front;
            this.removeOK = false;
        }

        /**
         * Returns whether the current Position has a next Position
         * @return true if the call to the after() method is not null, false if otherwise
         */
        @Override
        public boolean hasNext() {
        	if (after(current) == null) {
        		return false;
        	}
        	return true;
        }

        /**
         * Returns the updated Current position after a call to the after() method
         * @return the updated Current position after the original Current position
         * @throws NoSuchElementException if hasNext() returns false
         */
        @Override
        public Position<E> next() {
            if (!hasNext()) {
            	throw new NoSuchElementException();
            }
            current = after(current);
            removeOK = true;
            return current;
        }

        /**
         * Removes the current Position node from the PositionalLinkedList
         * @throws IllegalStateException if removeOK is false
         */
        @Override
        public void remove() {
        	if (!removeOK) {
	        	throw new IllegalStateException();
	        }
        	PositionalLinkedList.this.remove(current);
        	removeOK = false;
        }
    }
    
    /**
     * Inner class which is the Iterator that allows clients to visit
     * each element in the list
     * @author Benjamin Uy
     */
    private class ElementIterator implements Iterator<E> {

    	/** Instance of an Iterator object */
        private Iterator<Position<E>> it;

        /**
         * Constructor for the ElementIterator
         */
        public ElementIterator() {
            it = new PositionIterator();
        }

        /**
         * Returns whether the iterator has more elements
         * @return true if the iterator has more elements, false if not
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
         * Returns the next element in the List
         * @return the next element in the List
         */
        @Override
        public E next() {
            return it.next().getElement();
        }

        /**
         * Removes the current element from the List
         */
        @Override
        public void remove() {
            it.remove();
        }
    }
    
    /**
     * Method returns an iterator over the elements in the list
     * @return an iterator that interacts with elements
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    /**
     * Method returns an iterator over the Positions in the list
     * @return an iterator that interacts with Positions
     */
    @Override
    public Iterable<Position<E>> positions() {
    	Iterable<Position<E>> p = new PositionIterable();
    	return p;
    }
    
    private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
  

}