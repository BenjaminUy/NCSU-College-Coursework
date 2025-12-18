package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Benjamin Uy
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

    private PositionalList<Entry<K, V>> list;
    
    /**
     * Constructs a new UnorderedLinkedMap and sets the list field to a new PositionalLinkedList
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * Private helper method used to find an existing key in the map
     * @param key key to find in the map
     * @return position of the key-value pair in the map
     */
    private Position<Entry<K, V>> lookUp(K key) {
    	Position<Entry<K, V>> traverse = list.first();
    	// Iterate through the list to look for an entry with the given key
    	if (traverse != null) {
	        for (int i = 0; i < list.size(); i++) {
	        	if (traverse.getElement().getKey().equals(key)) {
	        		return traverse;
	        	}
	        	traverse = list.after(traverse);
	        }
    	}
    	// If none are found, return null
		return null;
    }
    
    /**
     * Helper method that represents the move-to-front heuristic; used to improve
     * the average-case cost of a lookUp in the map by moving the given position to the front of the list
     * @param position Position to move to the front of the list
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	list.remove(position);
    	list.addFirst(position.getElement());
    }

    /**
     * This method checks if the key already exists in the map. If a key is found,
     * the key is updated to have the given value; the replaced value will be returned.
     * If no key is found, a new entry is added at the front of list and null will be returned.
     * @param key key to search or add to the map
     * @param value value to update the key to
     * @return the value of a pre-existing key, should there be one; if not, return null
     */
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key); 
        V original = null;
        if (p != null) {
        	original = p.getElement().getValue();
        	
        	MapEntry<K, V> entry = (MapEntry<K, V>) p.getElement();
        	entry.setValue(value);
        	moveToFront(p);
        } else {
        	list.addFirst(new MapEntry<K, V>(key, value));
        
        }
        return original;
        
    }
    
	/**
	 * Return the key of the entry
	 * @param key target key to get the value from in the map
	 * @return the key of the entry, if the entry is not found, return null
	 */
    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if (p != null) {
        	moveToFront(p);
        	return p.getElement().getValue();
        } else {
        	return null;
        }
    }
    
    /**
     * Removes the entry with the given key from the map
     * 
     * @return the value of the removed entry or null if there isn't an entry with the given key
     */
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       
       if (p != null) {
           V removedValue = p.getElement().getValue();
           list.remove(p);
           return removedValue;
       } else {
    	   return null;
       }
    } 
    
    /**
     * Returns the size of the map
     * 
     * @return returns the size of the map
     */
    @Override
    public int size() {
        return list.size();
    }
    
    /**
     * Returns an Iterable object that interacts with entries or key-value pairs
     * @return an Iterable object that interacts with key-value pairs
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    /**
     * Returns a String representation of the map showing the order of entries within it in chronological order
     * @return String representation of the map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
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