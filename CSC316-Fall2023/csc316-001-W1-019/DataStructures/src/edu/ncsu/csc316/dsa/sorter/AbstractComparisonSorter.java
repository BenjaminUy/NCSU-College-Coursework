package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstract class that houses the common functionalities between the InsertionSorter 
 * and the SelectionSorter classes. These similarities are in their compare method, 
 * NatureOrder inner classes, and setComparator methods.
 * @author Benjamin Uy
 * @param <E> generic elements that the sorter will interact with
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** Comparator that works with generic elements */ 
    private Comparator<E> comparator;
    
    /**
     * Constructor of the AbstractComparisonSorter and sets the Comparator to the given
     * custom comparator
     * @param comparator Comparator of generic objects to set the comparator to
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Method sets the Comparator of the AbstractComparisonSorter; should the parameter
     * be null, this comparator is set to the natural order. Otherwise, the comparator
     * is set to the given parameter.
     * @param comparator Comparator<E> object to the comparator to
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * This method compares the two given objects and returns an integer
     * representation of the first element's position relative to the second
     * @param first the first generic element to be compared 
     * @param second the second generic element to be compared
     * @return an integer representation of the first element's position relative
     * 		to the second element
     */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}
