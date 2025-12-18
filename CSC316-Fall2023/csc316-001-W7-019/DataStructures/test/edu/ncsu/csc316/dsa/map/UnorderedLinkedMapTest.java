package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class UnorderedLinkedMapTest {

    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertEquals("UnorderedLinkedMap[4, 3]", map.toString());
        
        assertNull(map.put(1, "string1"));
        assertEquals(3, map.size());
        assertEquals("UnorderedLinkedMap[1, 4, 3]", map.toString());
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 1, 4, 2, 5]", map.toString());
        
        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 3, 1, 2, 5]", map.toString());
        
        assertNull(map.get(20));
        assertEquals("UnorderedLinkedMap[4, 3, 1, 2, 5]", map.toString());
        
        assertNull(map.get(-1));
        assertEquals("UnorderedLinkedMap[4, 3, 1, 2, 5]", map.toString());
        
        assertEquals("string5", map.get(5));
        assertEquals("UnorderedLinkedMap[5, 4, 3, 1, 2]", map.toString());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        assertEquals(5, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals(4, map.size());
        assertEquals("UnorderedLinkedMap[4, 2, 5, 3]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals(3, map.size());
        assertEquals("UnorderedLinkedMap[4, 2, 5]", map.toString());
        
        assertEquals("string2", map.remove(2));
        assertEquals(2, map.size());
        assertEquals("UnorderedLinkedMap[4, 5]", map.toString());
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Integer> k = map.iterator();
        assertTrue(k.hasNext());
        assertEquals((Integer) 1, k.next());
        assertTrue(k.hasNext());
        assertEquals((Integer) 4, k.next());
        assertTrue(k.hasNext());
        assertEquals((Integer) 2, k.next());
        assertTrue(k.hasNext());
        assertEquals((Integer) 5, k.next());
        assertTrue(k.hasNext());
        assertEquals((Integer) 3, k.next());
        assertFalse(k.hasNext());
        try {
        	k.next();
        } catch (Exception e) {
        	assertTrue(e instanceof NoSuchElementException);
        }
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<Map.Entry<Integer, String>> it = map.entrySet();
        
        assertTrue(it.iterator().hasNext());
        assertEquals("string1", it.iterator().next().getValue());
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<String> v = map.values();
        Iterator<String> it = v.iterator();
        
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        assertFalse(it.hasNext());
        try {
        	it.next();
        } catch (Exception e) {
        	assertTrue(e instanceof NoSuchElementException);
        }
    }
}