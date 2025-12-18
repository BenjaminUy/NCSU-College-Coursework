package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 * 
 * @author Dr. King
 * @author Benjamin Uy
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    private ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SearchTableMap() {
        this(null);
    }
    
    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on a
     * provided {@link Comparator}
     * 
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */ 
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }

    /**
     * Method searches the list for the given key value, then returns the index of that key in the list
     * @param key target key value to locate
     * @return the index of the entry in the list, as determined by binarySearchHelper
     */
    private int lookUp(K key) {
        return binarySearchHelper(0, list.size() - 1, key);
    }

    /**
     * Private helper method uses the binary search algorithm to locate the cell with the given key
     * @param min minimum index to consider
     * @param max max index to consider
     * @param key target key value to locate
     * @return the index of the entry in the list; returns a negative number that indicates where the element
     * 		should be in the list
     */
    private int binarySearchHelper(int min, int max, K key) {
        if (min > max) {
        	return -1 * (min + 1);
        }
        int mid = (max + min) / 2;   
        if (list.get(mid).getKey() == key) {
        	return mid;
        } else if (compare(list.get(mid).getKey(), key) > 0) {
        	return binarySearchHelper(min, mid - 1, key);
        } else {
        	return binarySearchHelper(mid + 1, max, key);
        }  
    }

    /**
     * Returns the number of elements in the SearchTableMap
     * @return the number of elements in the map
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Returns the value of the entry with the given target key in the map.
     * If no entry with the given key is found, this method will return null.
     * @param key target key to locate in the map
     * @return the value of the entry with the given key, returns null if no entry is found
     */
    @Override
    public V get(K key) {
        int index = lookUp(key);
        if (index < 0) {
        	return null;
        }
        return list.get(index).getValue();
    }

    /**
     * Returns an Iterable object that interacts with Entry objects; these entries contain a key and value
     * @return an Iterable object that interacts with Entry objects
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        for (Entry<K, V> entry : list) {
            set.add(entry);
        }
        return set;
    }

    /**
     * This method checks if the key already exists in the map. If a key is found,
     * the key is updated to have the given value; the replaced value will be returned.
     * If no key is found, a new entry is added in the list as ordered by binary search
     * and null will be returned.
     * @param key key to search or add to the map
     * @param value value to update the key to
     * @return the value of a pre-existing key, should there be one; if not, return null
     */
    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
        
        if (index >= 0) {
        	V original = this.get(key);
        	list.remove(index);
        	list.add(index, new MapEntry<K, V>(key, value));
        	return original;
        } else {
        	list.add((index + 1) * -1, new MapEntry<K, V>(key, value));
        	return null;
        }
    }

    /**
     * Removes the entry with the given key from the map
     * @param key target key to remove
     * @return the value of the removed entry or null if there isn't an entry with the given key
     */
    @Override
    public V remove(K key) {
        int index = lookUp(key);
        if (index >= 0) {
        	V original = this.get(key);
        	list.remove(index);
        	return original;
        } else {
        	return null;
        }
    }
    
    /**
     * Returns a String representation of the map showing the order of entries within it in chronological order
     * @return String representation of the map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchTableMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}