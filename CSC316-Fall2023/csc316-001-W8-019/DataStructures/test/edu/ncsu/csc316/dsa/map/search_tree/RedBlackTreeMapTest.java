package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class RedBlackTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(5, "five");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(1, tree.size());
        
        tree.put(10, "ten");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(2, tree.size());
        
        tree.put(2, "two");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, tree.size());
        
        tree.put(3, "three");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, tree.size());
        
        tree.put(4, "four");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(5, tree.size());
        
        tree.put(1, "one");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(6, tree.size());
        
        tree.put(7, "seven");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(7, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, tree.size());
        
        tree.put(6, "six");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(8, tree.size()); 
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertNull(tree.get(1));
        
        tree.put(5, "five");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(1, tree.size());
        
        tree.put(10, "ten");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(2, tree.size());
        
        tree.put(2, "two");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, tree.size());
        
        assertEquals("five", tree.get(5));
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, tree.size());
        
        assertEquals("two", tree.get(2));
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, tree.size());
        
        assertNull(tree.get(11));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(5, "five");
        tree.put(10, "ten");
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(4, "four");        
        tree.put(1, "one");        
        tree.put(7, "seven");        
        tree.put(6, "six");      
        
        assertNull(tree.remove(20));
        
        assertEquals("ten", tree.remove(10));
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(7, tree.size()); 
        
        assertEquals("four", tree.remove(4));
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(6, tree.size()); 
        
        assertEquals("two", tree.remove(2));
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(5, tree.size()); 
        
        assertEquals("five", tree.remove(5));
        assertEquals(6, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(4, tree.size()); 
        
        assertEquals("three", tree.remove(3));
        assertEquals(6, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, tree.size()); 
        
        assertEquals("seven", tree.remove(7));
        assertEquals(6, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(2, tree.size()); 
        
        assertEquals("six", tree.remove(6));
        assertEquals(1, (int) tree.root().getElement().getKey());
        assertEquals(1, tree.size()); 
    }
}