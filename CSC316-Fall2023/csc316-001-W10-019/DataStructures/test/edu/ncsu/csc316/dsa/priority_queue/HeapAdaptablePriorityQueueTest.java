package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class HeapAdaptablePriorityQueueTest {

    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        assertEquals(3, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int) heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.replaceKey(e7, -6);
        assertEquals(-6, (int) heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.replaceKey(e7, 10);
        assertEquals(-5, (int) heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.replaceKey(e6, -100);
        assertEquals(-100, (int) heap.min().getKey());
        assertEquals("six", heap.min().getValue());
        assertEquals(3, heap.size());
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
//        Entry<Integer, String> e7 = heap.insert(7, "seven");
//        Entry<Integer, String> e6 = heap.insert(6, "six");
//        Entry<Integer, String> e5 = heap.insert(5, "five");
//        Entry<Integer, String> e4 = heap.insert(4, "four");
//        Entry<Integer, String> e3 = heap.insert(3, "three");
//        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(3, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int) heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(3, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        
        heap.replaceValue(e0, "nada");
        assertEquals(0, (int) heap.min().getKey());
        assertEquals("nada", heap.min().getValue());
        assertEquals(3, heap.size());
        assertEquals("nada",  e0.getValue());
        
        heap.replaceValue(e1, "uno");
        assertEquals(0, (int) heap.min().getKey());
        assertEquals("nada", heap.min().getValue());
        assertEquals(3, heap.size());
        assertEquals("uno",  e1.getValue());
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
//        Entry<Integer, String> e8 = heap.insert(8, "eight");
//        Entry<Integer, String> e7 = heap.insert(7, "seven");
//        Entry<Integer, String> e6 = heap.insert(6, "six");
//        Entry<Integer, String> e5 = heap.insert(5, "five");
//        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(4, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int) heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.remove(e3);
        assertEquals(1, (int) heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(2, heap.size());
        
        heap.remove(e1);
        assertEquals(2, (int) heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(1, heap.size());
        
        heap.remove(e2);
        assertNull(heap.min());
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        Entry<Student, String> five = sHeap.insert(s5, "five");
        assertEquals(1, sHeap.size());
        assertEquals("five", sHeap.min().getValue());
        assertEquals(s5, sHeap.min().getKey());
        
        Entry<Student, String> three = sHeap.insert(s3, "three");
        assertEquals(2, sHeap.size());
        assertEquals("three", sHeap.min().getValue());
        assertEquals(s3, sHeap.min().getKey());
        
        Entry<Student, String> four = sHeap.insert(s4, "four");
        assertEquals(3, sHeap.size());
        assertEquals("three", sHeap.min().getValue());
        assertEquals(s3, sHeap.min().getKey());
        
        assertEquals(three, sHeap.deleteMin());
        assertEquals(2, sHeap.size());
        assertEquals("four", sHeap.min().getValue());
        assertEquals(s4, sHeap.min().getKey());
        
        sHeap.replaceKey(five, s2);
        assertEquals(2, sHeap.size());
        assertEquals("five", sHeap.min().getValue());
        assertEquals(s2, sHeap.min().getKey());
        
        sHeap.replaceValue(four, "fourteen");
        assertEquals(2, sHeap.size());
        assertEquals("five", sHeap.min().getValue());
        assertEquals(s2, sHeap.min().getKey());
        
        sHeap.remove(five);
        assertEquals(1, sHeap.size());
        assertEquals("fourteen", sHeap.min().getValue());
        assertEquals(s4, sHeap.min().getKey());
        
        sHeap.insert(s1, "one");
        assertEquals(2, sHeap.size());
        assertEquals("one", sHeap.min().getValue());
        assertEquals(s1, sHeap.min().getKey());
    }
}