package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class ArrayBasedListTest {

	/** List<String> instance used during testing */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.addLast("Last");
        assertEquals(1, list.size());
        assertEquals("Last", list.get(0));
        assertFalse(list.isEmpty());
        
        list.addLast("Second");
        assertEquals(2, list.size());
        assertEquals("Last", list.get(0));
        assertEquals("Second", list.get(1));
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        try {
        	list.last();
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.addLast("Last");
        assertEquals(1, list.size());
        assertEquals("Last", list.last());
        
        list.add(0, "First");
        assertEquals(2, list.size());
        assertEquals("Last", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.addFirst("First");
        assertEquals(1, list.size());
        assertEquals("First", list.get(0));
        assertFalse(list.isEmpty());
        
        list.addFirst("Second");
        assertEquals(2, list.size());
        assertEquals("Second", list.get(0));
        assertEquals("First", list.get(1));
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        try {
        	list.first();
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.addFirst("First");
        assertEquals(1, list.size());
        assertEquals("First", list.first());
        
        list.add(1, "Second");
        assertEquals(2, list.size());
        assertEquals("First", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        list.addLast("First");
        list.addLast("Second");
        
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        assertEquals("First", list.get(1));
        assertEquals("Second", list.get(2));
        
        assertEquals("First", it.next());
        
        it.remove();
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("Second", list.get(1));
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        try {
        	list.remove(0);
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        try {
        	list.remove(-1);
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.addFirst("First");
        list.addLast("Second");
        
        assertEquals("First", list.remove(0));
        assertEquals(1, list.size());
        
        list.addLast("Third");
        
        assertEquals("Third", list.remove(1));
        assertEquals(1, list.size());
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        try {
        	list.removeFirst();
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.addFirst("First");
        list.addLast("Second");
        
        assertEquals("First", list.removeFirst());
        assertEquals(1, list.size());
        assertEquals("Second", list.get(0));
        
        assertEquals("Second", list.removeFirst());
        assertEquals(0, list.size());
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        try {
        	list.removeLast();
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.addFirst("First");
        list.addLast("Second");
        
        assertEquals("Second", list.removeLast());
        assertEquals(1, list.size());
        assertEquals("First", list.get(0));
        
        assertEquals("First", list.removeLast());
        assertEquals(0, list.size());
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        try {
        	list.set(0, "this");
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.addFirst("First");
        assertEquals("First", list.get(0));
        
        list.set(0, "new");
        assertEquals(1, list.size());
        assertEquals("new", list.get(0));
    }
}
