package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @param <E> generic element that the Sorter class interacts with
 */
public interface Sorter<E> {
	
	/**
	 * Method sorts the given array of elements
	 * @param data array of generic elements to sort
	 */
	void sort (E[] data);
}
