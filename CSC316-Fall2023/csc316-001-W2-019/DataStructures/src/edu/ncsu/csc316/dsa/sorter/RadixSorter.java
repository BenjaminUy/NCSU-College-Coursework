package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Method sorts the given generic element array using the radix sort algorithm
	 * @param data array of generic elements to be sorted
	 */
	@Override
	public void sort(E[] data) {
		// Find the largest value in the input data
		int max = 0;
		for (int i = 0; i <= data.length - 1; i++) {
			max = Math.max(max, data[i].getId());
		}
		// Determine how many digits are in the largest value
		int digitsInLargest = (int) Math.ceil( Math.log10(max + 1) / Math.log10(10));
		
		int numPlace = 1; // tracks "place" of the number
		
		for (int j = 1; j <= digitsInLargest; j++) {
			int[] countArray = new int[10];
			for (int i = 0; i <= data.length - 1; i++) {
				countArray[(data[i].getId() / numPlace) % 10] = countArray[(data[i].getId() / numPlace % 10)] + 1;
			}
			for (int i = 1; i <= 9; i++) {
				countArray[i] = countArray[i - 1] + countArray[i];
			}
			// Build final output array
			@SuppressWarnings("unchecked")
			E[] finalArray = (E[])(new Identifiable[data.length]);
			for (int i = data.length - 1; i >= 0; i--) {
				finalArray[countArray[(data[i].getId() / numPlace) % 10] - 1] = data[i];
				countArray[(data[i].getId() / numPlace % 10)] = countArray[(data[i].getId() / numPlace) % 10] - 1;
			}
			
			for (int i = 0; i <= data.length - 1; i++) {
				data[i] = finalArray[i];
			}
			
			numPlace = numPlace * 10;
		}
	}
}
