package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> generic element that the InsertionSorter class interacts with
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor of the InsertionSorter object that uses the given Comparator object
	 * @param comparator Comparator of generic elements to compare objects
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor of the InsertionSorter that sets the comparator its default of natural ordering
	 */
	public InsertionSorter() {
		super(null);
	}
	
	/**
	 * Method implements the sort method of the Sorter interface.
	 * This method takes the given element array and sorts the elements.
	 * @param data array of elements to be sorted
	 */
	public void sort(E[] data) {		
		for (int i = 1; i <= data.length - 1; i++) {
		E x = data[i];
		int j = i - 1;
		while (j >= 0 && this.compare(data[j], x) > 0) {
			data[j + 1] = data[j];
			j = j - 1;
		}
		data[j + 1] = x;
		}
	}
}
