package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	private PivotSelector selector;
	
	 /**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
	
	/**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * LastElementSelector chooses the last index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Benjamin Uy
     */
    public static class LastElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * MiddleElementSelector chooses the middle index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Benjamin Uy
     */
    public static class MiddleElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return (high + low) / 2;
        }
    }
    
    /**
     * RandomElementSelector chooses a random index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Benjamin Uy
     */
    public static class RandomElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
        	Random rand = new Random();
        	
        	int randomIndex = rand.nextInt(high + 1);
        	while (randomIndex < low || randomIndex > high) {
        		randomIndex = rand.nextInt(high + 1);
        	}
        	return randomIndex;
        }
    }
    
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * Helper method that determines which pivot selector is used during the quick sort.
     * If the param is null, method assigns selector to be the RandomElementSelector
     * @param selector PivotSelector to be used during sorting
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }

    /**
     * Method starts the quick sorting process by initializing the values for the low and high indices
     * @param data array of generic elements to sort
     */
	@Override
	public void sort(E[] data) {
		int low = 0;
		int high = data.length - 1;
		quickSorter(data, low, high);
	}
	
	/**
	 * Private method that has recursive calls to itself as it applies the quick sort algorithm
	 * @param data array of generic elements to sort
	 * @param low lowest index to sort from
	 * @param high highest index to sort from
	 */
	private void quickSorter(E[] data, int low, int high) {
		if (low < high) {
			int pivotLocation = partition(data, low, high);
			quickSorter(data, low, pivotLocation - 1);
			quickSorter(data, pivotLocation + 1, high);
		}
	}
	
	/**
	 * Private helper method that implements the partition algorithm of quick sort
	 * @param data array of generic elements to sort
	 * @param low lowest index to sort from
	 * @param high highest index to sort to
	 * @return index of the pivot element
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		E placeholder = data[high];
		data[high] = data[pivotIndex];
		data[pivotIndex] = placeholder;
		return partitionHelper(data, low, high);
	}
	
	/**
	 * Private helper method used to 
	 * @param data array to sort
	 * @param low lowest index being sorted
	 * @param high highest index being sorted
	 * @return index of the pivot element
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int separator = low;
		for (int j = low; j <= high - 1; j++) {
			if (super.compare(data[j], pivot) <= 0) {
				E placeholder = data[separator];
				data[separator] = data[j];
				data[j] = placeholder;
				separator = separator + 1;
			}
		}
		E placeholder = data[high];
		data[high] = data[separator];
		data[separator] = placeholder;
		
		return separator;
	}
	
	
}