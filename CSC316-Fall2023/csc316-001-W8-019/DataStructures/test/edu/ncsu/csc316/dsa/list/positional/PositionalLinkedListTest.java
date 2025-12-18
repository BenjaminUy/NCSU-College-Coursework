package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class PositionalLinkedListTest {

	/** PositionalList<String> instance used during testing */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first, list.first());
        assertEquals(second, list.after(first));
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(first, list.last());
        assertEquals(second, list.first());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        
        assertEquals(second, list.first());
        assertEquals(first, list.after(second));
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        
        assertEquals(second, list.last());
        assertEquals(first, list.first());
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertNull(list.before(first));
        
        Position<String> second = list.addLast("two");
        assertEquals(first, list.before(second));
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertNull(list.after(first));
        
        Position<String> second = list.addFirst("two");
        assertEquals(first, list.after(second));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(first, list.first());
        
        Position<String> second = list.addBefore(first, "two");
        assertEquals(second, list.first());
        assertEquals(first, list.last());
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(first, list.last());
        
        Position<String> second = list.addAfter(first, "two");
        assertEquals(first, list.first());
        assertEquals(second, list.last());
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
        Position<String> first = list.addFirst("one");
        assertEquals(first.getElement(), "one");  
        assertEquals("one", list.set(first, "second"));
        assertEquals(first.getElement(), "second");
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
       
        assertEquals("one", list.remove(first));
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        Position<String> second = list.addFirst("two");
        Position<String> third = list.addFirst("three");
        
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertEquals("three", list.remove(third));
        assertEquals("two", list.remove(second));
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertEquals(0, list.size());
    	assertTrue(list.isEmpty());
    	
    	Iterator<String> it = list.iterator();
    	
    	try {
    		it.remove();
    		fail("An IllegalStateException should have been thrown");           
          } catch(Exception e) {
              assertTrue(e instanceof IllegalStateException);
          }
    	assertFalse(it.hasNext());
    	
    	Position<String> a = list.addLast("one");
    	
    	assertEquals(1, list.size());
    	assertFalse(list.isEmpty());
    	assertEquals(a, list.first());
    	
    	it = list.iterator();
    	
    	assertTrue(it.hasNext());
    	assertEquals("one", it.next());
    	assertFalse(it.hasNext());
    	try {
    		it.next();
    		fail("A NoSuchElementException should have been thrown");
    	} catch(Exception e) {
    		assertTrue(e instanceof NoSuchElementException);
    	}
    	
    	Position<String> b = list.addLast("First");
    	Position<String> c = list.addLast("Second");
    	
    	assertEquals(3, list.size());
    	assertEquals(a, list.first());
    	assertEquals(b, list.after(a));
    	assertEquals(c, list.after(b));
    	
    	assertEquals("First", it.next());
    	it.remove();
    	
    	assertEquals(2, list.size());
    	assertEquals(a, list.first());
    	assertEquals(c, list.after(a));
    	
    	Position<String> d = list.addLast("two");
    	assertEquals(3, list.size());
    	assertEquals(a, list.first());
    	assertEquals(c, list.after(a));
    	assertEquals(d, list.after(c));
    
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());

    }

}