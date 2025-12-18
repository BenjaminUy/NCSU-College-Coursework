package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure 
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class SeparateChainingHashMapTest {

    private Map<Integer, String> map;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        map = new SeparateChainingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        assertEquals("string3", map.put(3, "stringfree"));
        assertNull(map.put(10, "string10"));
        assertNull(map.put(11, "string11"));
        assertEquals(4, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(10, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        assertEquals(11, (int)it.next().getKey()); // should be in a map in index 5
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.get(1));
        assertNull(map.put(1, "string1"));
        assertEquals("string1", map.get(1));
        
        assertEquals("string1", map.put(1, "uno"));
        assertEquals("uno", map.get(1));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.remove(1));
        assertNull(map.put(1, "string1"));
        assertEquals(1, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */   
    @Test
    public void testIterator() {        
        Iterator<Integer> it = map.iterator();
        try {
        	it.next();
        } catch (Exception e) {
        	assertTrue(e instanceof NoSuchElementException);
        }
        
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(8, "string8"));
        
        it = map.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, (int) it.next()); // Should be at index 2
        assertTrue(it.hasNext());
        assertEquals(8, (int) it.next()); // Should be at index 2
        assertTrue(it.hasNext());
        assertEquals(2, (int) it.next()); // Should be at index 3
        assertTrue(it.hasNext());
        assertEquals(3, (int) it.next()); // Should be at index 4
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the entrySet() behavior
     */   
    @Test
    public void testEntrySet() {
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
        try {
        	it.next();
        } catch (Exception e) {
        	assertTrue(e instanceof NoSuchElementException);
        }
        
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(8, "string8"));
        
        it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        assertEquals(1, (int) it.next().getKey()); // Should be at index 2
        assertTrue(it.hasNext());
        assertEquals(8, (int) it.next().getKey()); // Should be at index 2
        assertTrue(it.hasNext());
        assertEquals(2, (int) it.next().getKey()); // Should be at index 3
        assertTrue(it.hasNext());
        assertEquals(3, (int) it.next().getKey()); // Should be at index 4
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the values() behavior
     */   
    @Test
    public void testValues() {
        Iterator<String> it = map.values().iterator();
        try {
        	it.next();
        } catch (Exception e) {
        	assertTrue(e instanceof NoSuchElementException);
        }
        
        assertNull(map.put(1, "string1"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(3, "string3"));
        assertNull(map.put(8, "string8"));
        
        it = map.values().iterator();
        assertTrue(it.hasNext());
        assertEquals("string1", it.next()); // Should be at index 2
        assertTrue(it.hasNext());
        assertEquals("string8", it.next()); // Should be at index 2
        assertTrue(it.hasNext());
        assertEquals("string2", it.next()); // Should be at index 3
        assertTrue(it.hasNext());
        assertEquals("string3", it.next()); // Should be at index 4
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the various LinearProbingHashMap constructor methods
     */
    @Test
    public void testSeparateChainingHashMap() {
    	// Test the default constructor
    	map = new SeparateChainingHashMap<Integer, String>();
    	assertEquals(0, map.size());
    	assertTrue(map.isEmpty());
    	map.put(1, "string1");
    	map.put(2, "string2");
    	map.put(3, "string3");
    	assertEquals(3, map.size());
    	assertFalse(map.isEmpty());
    	map.remove(2);
    	assertEquals(2, map.size());
    	assertFalse(map.isEmpty());
    	
    	// Test constructor with boolean isTesting parameter
    	map = new SeparateChainingHashMap<Integer, String>(true);
    	assertEquals(0, map.size());
    	assertTrue(map.isEmpty());
    	map.put(1, "string1");
    	map.put(2, "string2");
    	map.put(3, "string3");
    	assertEquals(3, map.size());
    	assertFalse(map.isEmpty());
    	map.remove(2);
    	assertEquals(2, map.size());
    	assertFalse(map.isEmpty());
    	
    	// Test constructor with integer capacity parameter
    	map = new SeparateChainingHashMap<Integer, String>(2);
    	assertEquals(0, map.size());
    	assertTrue(map.isEmpty());
    	map.put(1, "string1");
    	map.put(2, "string2");
    	map.put(3, "string3");
    	assertEquals(3, map.size());
    	assertFalse(map.isEmpty());
    	map.remove(2);
    	assertEquals(2, map.size());
    	assertFalse(map.isEmpty());
    }
}