package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This class represents the bubble sort algorithm
 * @author Benjamin Uy
 * @param <E> generic elements that this sorter class interacts with
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * Constructor of the BubbleSorter object that uses the given Comparator object
	 * @param comparator Comparator of generic elements
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * Constructor of the BubbleSorter object that calls the AbstractComparisonSorter will a null parameter
	 */
	public BubbleSorter() {
		super(null);
	}
	
	/**
	 * This method sorts the given array of generic elements using the bubble sort method
	 * @param data array of generic elements
	 */
	public void sort(E[] data) {
		boolean repeat = true;
		while (repeat) {
			repeat = false;
			for (int i = 1; i <= data.length - 1; i++) {
				if (this.compare(data[i], data[i - 1]) < 0) {
					E placeholder = data[i - 1];
					data[i - 1] = data[i];
					data[i] = placeholder;
					repeat = true;
				}
					
			}
		}
	}
}
