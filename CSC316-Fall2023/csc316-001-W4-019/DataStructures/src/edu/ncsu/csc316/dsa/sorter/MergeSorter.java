package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

    /**
     * Sorts the given array of generic elements using the merge sort algorithm
     * @param data array of generic elements to sort
     */
	@Override
	public void sort(E[] data) {
		if (data.length >= 2) {
			int mid = data.length / 2;
			
			E[] left = (E[]) Arrays.copyOfRange(data, 0, mid);

			E[] right = (E[]) Arrays.copyOfRange(data, mid, data.length);
					
			sort(left);
			sort(right);
			
			merge(left, right, data);
		}
	}
    
	/**
	 * Helper method that merges the right and left halves of the arrays from merge sort into a single sorted array
	 * @param s1 array containing the sorted left half
	 * @param s2 array containing the sorted right half
	 * @param s original array that this method will sort/merge the array data into
	 */
    private void merge(E[] s1, E[] s2, E[] s) {
    	int leftIndex = 0;
    	int rightIndex = 0;
    	while (leftIndex + rightIndex < s.length) {
    		if (rightIndex == s2.length || (leftIndex < s1.length && super.compare(s1[leftIndex], s2[rightIndex]) < 0)) {
    			s[leftIndex + rightIndex] = s1[leftIndex];
    			leftIndex++;
    		} else {
    			s[leftIndex + rightIndex] = s2[rightIndex];
    			rightIndex++;
    		}
    	}
    }

}
