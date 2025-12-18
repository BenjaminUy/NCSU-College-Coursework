package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class SearchTableMapTest {

    private Map<Integer, String> map;
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SearchTableMap<Integer, String>();
        studentMap = new SearchTableMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals("string3", map.put(3, "three"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        
        assertNull(map.put(1, "string1"));
        assertEquals("SearchTableMap[1, 3]", map.toString());
        assertEquals(2, map.size());
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string5", map.get(5));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertNull(map.get(7));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string5", map.remove(5));
        assertEquals(4, map.size());
        assertEquals("SearchTableMap[1, 2, 3, 4]", map.toString());
        
        assertEquals("string1", map.remove(1));
        assertEquals(3, map.size());
        assertEquals("SearchTableMap[2, 3, 4]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals(2, map.size());
        assertEquals("SearchTableMap[2, 4]", map.toString());
        
        assertEquals("string2", map.remove(2));
        assertEquals(1, map.size());
        assertEquals("SearchTableMap[4]", map.toString());
        
        assertEquals("string4", map.remove(4));
        assertEquals(0, map.size());
        assertEquals("SearchTableMap[]", map.toString());
        assertTrue(map.isEmpty());
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
    	studentMap = new SearchTableMap<Student, Integer>(new StudentIDComparator());
    	
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertTrue(studentMap.isEmpty());
        assertNull(studentMap.put(s5, 5));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s1, 1));
       
        // Testing output for using the student id comparator
        assertFalse(studentMap.isEmpty());
        assertEquals("SearchTableMap[" + s1.toString() + ", " + s2.toString() + ", " + s3.toString() + ", " + 
        		s4.toString() + ", " + s5.toString() + "]", studentMap.toString());
        
        // Testing output for using natural ordering (last name)
        studentMap = new SearchTableMap<Student, Integer>();
        
        assertTrue(studentMap.isEmpty());
        assertNull(studentMap.put(s5, 5));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s1, 1));
        
        assertFalse(studentMap.isEmpty());
        assertEquals("SearchTableMap[" + s5.toString() + ", " + s3.toString() + ", " + s4.toString() + ", " + 
        		s1.toString() + ", " + s2.toString() + "]", studentMap.toString());
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
        assertEquals((Integer) 2, k.next());
        assertTrue(k.hasNext());
        assertEquals((Integer) 3, k.next());
        assertTrue(k.hasNext());
        assertEquals((Integer) 4, k.next());
        assertTrue(k.hasNext());
        assertEquals((Integer) 5, k.next());
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));
        
        assertFalse(it.hasNext());
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
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        assertFalse(it.hasNext());
        try {
        	it.next();
        } catch (Exception e) {
        	assertTrue(e instanceof NoSuchElementException);
        }
    }
}