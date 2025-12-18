package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/**
	 * Constructor of SelectionSorter that calls the AbstractComparisonSorter's
	 * constructor with the given Comparator parameter
	 * @param comparator Comparator of generic elements
	 */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructor of SelectionSorter that calls the AbstractComparisonSorter's
     * constructor with a null parameter
     */
    public SelectionSorter() {
    	super(null);
    }
    
    /**
     * Method sorts the given array of generic elements using the selection sort algorithm
     * @param data array of generic elements to sort
     */
    public void sort(E[] data) {
        for (int i = 0; i <= data.length - 1; i++) {
        	int min = i;
        	for (int j = i + 1; j <= data.length - 1; j++) {
        		if (this.compare(data[j], data[min]) < 0) {
        			min = j;
        		}
        	}
        	if (i != min) {						// Swap
        		E placeholder = data[i];
        		data[i] = data[min];
        		data[min] = placeholder;
        	}
        	
        }
    }
}
