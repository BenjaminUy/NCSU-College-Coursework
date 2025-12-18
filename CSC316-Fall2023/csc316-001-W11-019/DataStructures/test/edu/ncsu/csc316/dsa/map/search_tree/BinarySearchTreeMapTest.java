package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class BinarySearchTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int) tree.root().getElement().getKey());
        
        assertEquals("one", tree.put(1, "uno"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int) tree.root().getElement().getKey());
        
        assertNull(tree.put(2, "two"));
        assertEquals(2, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
        
        assertNull(tree.put(0, "zero"));
        assertEquals(3, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(0, (int) tree.left(tree.root()).getElement().getKey());
        
        Entry<Integer, String> one = tree.root().getElement();
        Entry<Integer, String> two = tree.right(tree.root()).getElement();
        Entry<Integer, String> zero = tree.left(tree.root()).getElement();
        
        assertEquals("BalanceableBinaryTree[\n" + one.toString() + "\n " + zero.toString() + "\n " + two.toString()
        	+ "\n]", tree.toString());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));
        
        assertNull(tree.get(2));
        tree.put(2,  "two");
        assertEquals(2, tree.size());
        assertEquals("two", tree.get(2));
        assertEquals("one", tree.get(1));
        
        assertEquals("one", tree.put(1, "uno"));
        tree.put(2,  "two");
        assertEquals(2, tree.size());
        assertEquals("two", tree.get(2));
        assertEquals("uno", tree.get(1));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(3, "three");
        tree.put(4, "four");
        tree.put(2, "two");
        assertEquals(3, tree.size());
        //   	 3
        //		/ \
        //	   2   4
        
       assertEquals("three", tree.remove(3));
       assertEquals("four", tree.root().getElement().getValue());
       assertEquals("two", tree.left(tree.root()).getElement().getValue());
       assertEquals(2, tree.size());
       //   	 4
       //		/
       //	   2 
       
       assertEquals("two", tree.remove(2));
       assertEquals("four", tree.root().getElement().getValue());
       assertEquals(1, tree.size());
       //   	 4
       
       tree.put(5, "five");
       assertEquals("four", tree.root().getElement().getValue());
       assertEquals("five", tree.right(tree.root()).getElement().getValue());
       assertEquals(2, tree.size());
       //       4
       //        \
       //         5
       
       assertEquals("four", tree.remove(4));
       assertEquals("five", tree.root().getElement().getValue());
       assertEquals(1, tree.size());
       //       5
    }
    
    /**
     * Secondary test for remove functionality along with behavior of toString()
     */
    @Test
    public void testRemove2() {
        tree.put(2,  "two");
        tree.put(3, "three");
        tree.put(1, "one");
        //      2
        //    /  \
        //   1    3
        
        Entry<Integer, String> one = tree.left(tree.root()).getElement();
        Entry<Integer, String> two = tree.root().getElement();
        Entry<Integer, String> three = tree.right(tree.root()).getElement();
        
        assertEquals("BalanceableBinaryTree[\n" + two.toString() + "\n " + one.toString() + "\n " + three.toString()
        	+ "\n]", tree.toString());
        
        assertEquals("two", tree.remove(2));
        assertEquals("three", tree.root().getElement().getValue());
        
        //     3
        //    /
        //   1
        
        assertEquals("BalanceableBinaryTree[\n" + three.toString() + "\n " + one.toString() + "\n]", tree.toString());
        
        assertEquals("three", tree.remove(3));
        assertEquals("one", tree.root().getElement().getValue());
        
        assertEquals("BalanceableBinaryTree[\n" + one.toString() + "\n]", tree.toString());
    }
    
    /**
     * Tests the output of the entrySet method
     */
    @Test
    public void testEntrySet() {
    	Iterator<Entry<Integer, String>> it = tree.entrySet().iterator();
    	assertFalse(it.hasNext());
    	try {
    		it.next();
    	} catch (Exception e) {
    		assertTrue(e instanceof NoSuchElementException);
    	}
    	
        tree.put(2,  "two");
        tree.put(3, "three");
        tree.put(1, "one");
        
        Entry<Integer, String> one = tree.left(tree.root()).getElement();
        Entry<Integer, String> three = tree.right(tree.root()).getElement();
        Entry<Integer, String> two = tree.root().getElement();
        
        it = tree.entrySet().iterator();
    	assertTrue(it.hasNext());
    	assertEquals(one, it.next());
    	assertTrue(it.hasNext());
    	assertEquals(two, it.next());
    	assertTrue(it.hasNext());
    	assertEquals(three, it.next());
    	assertFalse(it.hasNext());	
    }
}