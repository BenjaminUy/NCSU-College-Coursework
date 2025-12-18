package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class ArrayBasedQueueTest {

    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
    }
    
    /**
     * Secondary test for the output of the enqueue(e) behavior, in tandem with the front() and dequeue() methods
     */     
    @Test
    public void testEnqueue2() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        assertEquals("one", queue.dequeue());
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("two", queue.front());
        
        queue.enqueue("four");
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("two", queue.front());
        
        assertEquals("two", queue.dequeue());
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("three", queue.front());
        
        assertEquals("three", queue.dequeue());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("four", queue.front());
        
        assertEquals("four", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        assertEquals("one", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("three");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        assertEquals("two", queue.dequeue());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
        assertEquals(0, queue.size());
        try {
            queue.front();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }  
        
        queue.enqueue("first");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("first", queue.front());
        
        queue.enqueue("second");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("first", queue.front());
        
        assertEquals("first", queue.dequeue());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("second", queue.front());
    }

}