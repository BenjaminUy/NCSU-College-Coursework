package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * This method sorts the given array of generic elements using
	 * the counting sorter algorithm.
	 * @param data array of generic elements to be sorted
	 */
	@Override
	public void sort(E[] data) {
		// Find the min and the max elements in the input data
		int min = data[0].getId();
		int max = data[0].getId();
		for (int i = 0; i <= data.length - 1; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		// Calculate the range of elements
		int range = max - min + 1;
			
		// Count frequency of values
		int[] countArray = new int[range];
		for (int i = 0; i <= data.length - 1; i++) {
			countArray[data[i].getId() - min] = countArray[data[i].getId() - min] + 1;
		}
		// Accumulate frequencies
		for (int i = 1; i <= range - 1; i++) {
			countArray[i] = countArray[i - 1] + countArray[i];
		}
		// Build final output array
		@SuppressWarnings("unchecked")
		E[] finalArray = (E[])(new Identifiable[data.length]);
		for (int i = data.length - 1; i >= 0; i--) {
			finalArray[countArray[data[i].getId() - min] - 1] = data[i];
			countArray[data[i].getId() - min] = countArray[data[i].getId() - min] - 1;
		}
		
		for (int i = 0; i < data.length; i++) {
			data[i] = finalArray[i];
		}
	}
		
}