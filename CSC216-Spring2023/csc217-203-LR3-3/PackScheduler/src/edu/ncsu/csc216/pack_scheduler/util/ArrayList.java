/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom implementation of an array list that doesn't allow for null elements or
 * duplicate elements. 
 * 
 * @param <E> generic element that is stored in the custom array list
 * @author Hunt Tynch
 * @author Andrew Warren
 * @author Benjamin Uy
 */
public class ArrayList<E> extends AbstractList<E> {
	/** Constant value for initializing the list size */
	private static final int INIT_SIZE = 10;
	/** List of generic elements */
	private E[] list;
	/** Size of the list */
	private int size;
	
	/**
	 * Constructor of empty ArrayList with initial capacity of 10.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	
	/**
	 * Method to add new element to list
	 * @param index index of element to be added
	 * @param element element to added
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is already in list
	 */
	public void add(int index, E element) {
		if (element == null)
			throw new NullPointerException("Null element.");
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element))
				throw new IllegalArgumentException("Duplicate Element.");
		}
		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException("Index out of range.");
		if (size < INIT_SIZE) {
			for (int i = size; i > index; i--) {
				list[i] = list[i - 1];
			}
		} else {
			growArray();
			for (int i = size; i > index; i--) {
				list[i] = list[i - 1];
			}
		}
		list[index] = element;
		size++;
	}
	
	/**
	 * Creates new array with double capacity, copies old array to new array
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] newList = (E[]) new Object[list.length * 2];
		for (int i = 0; i < size; i++) {
			newList[i] = list[i];
		}
		list = newList;
	}
	
	/**
	 * Method to remove the element at the index parameter
	 * @param index index of element to be removed
	 * @return original element at index
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index out of range.");
		E element = list[index];
		for (int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return element;
	}
	
	/**
	 * Method is sets the content of a point in the ArrayList by receiving
	 * an index value and an element as a parameter 
	 * @param index index of element to be set
	 * @param element element to set
	 * @return element that was replaced by the given element at the index value
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is already in list
	 */
	public E set(int index, E element) {
		if (element == null)
			throw new NullPointerException("Null element.");
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element))
				throw new IllegalArgumentException("Duplicate Element.");
		}
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("Index out of range.");
		E oldElement = list[index];
		list[index] = element;
		return oldElement;
	}
	
	/**
	 * Method gets the element of the ArrayList at given index
	 * @param index index of the element to get
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index out of range.");
		return list[index];
	}

	/**
	 * Method returns the size of the ArrayList
	 * @return size size of the ArrayList
	 */
	@Override
	public int size() {
		return size;
	}	
}