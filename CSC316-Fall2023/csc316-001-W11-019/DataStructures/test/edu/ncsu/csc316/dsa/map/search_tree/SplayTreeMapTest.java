package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class SplayTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(1, "A");
        assertEquals(1, tree.size());
        assertEquals(1, (int) tree.root().getElement().getKey());
        //        1
        
        tree.put(2, "B");
        assertEquals(2, tree.size());
        assertEquals(2, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        //       2
        //      /
        //     1
        
        tree.put(3, "C");
        assertEquals(3, tree.size());
        assertEquals(3, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        //      3
        //     /
        //    2
        //   /
        //  1
        
        tree.put(0, "D");
        assertEquals(4, tree.size());
        assertEquals(0, (int) tree.root().getElement().getKey());
        assertEquals(3, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(1, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
        //    0
        //     \
        //      3
        //      /
        //     1
        //     \
        //      2     
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
    	assertEquals(0, tree.size());
        assertNull(tree.get(1));
        
        tree.put(1, "A");
        assertEquals(1, tree.size());
        assertEquals(1, (int) tree.root().getElement().getKey());
        //        1
        
        assertEquals("A", tree.get(1));
        assertEquals(1, tree.size());
        assertEquals(1, (int) tree.root().getElement().getKey());
        //        1
        
        tree.put(2, "B");
        assertEquals(2, tree.size());
        assertEquals(2, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.left(tree.root()).getElement().getKey());
        //       2
        //      /
        //     1
        
        assertEquals("A", tree.get(1));
        assertEquals(2, tree.size());
        assertEquals(1, (int) tree.root().getElement().getKey());
        assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
        //     1
        //      \
        //       2
        
        tree.put(0, "C");
        assertEquals(3, tree.size());
        assertEquals(0, (int) tree.root().getElement().getKey());
        assertEquals(1, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(2, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        //     0
        //      \
        //       1
        //        \
        //         2
        
        assertEquals("A", tree.get(1));
        assertEquals(3, tree.size());
        assertEquals(1, (int) tree.root().getElement().getKey());
        assertEquals(0, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(2, (int) tree.right(tree.root()).getElement().getKey());
        //     1
        //    / \
        //   0   2  
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	assertEquals(0, tree.size());
    	assertNull(tree.remove(1));
    	
    	tree.put(48, "A");
    	tree.put(50, "B");
    	tree.put(90, "C");
    	tree.put(60, "D");
    	tree.put(49, "E");
    	tree.put(52, "F");
    	assertEquals(52, (int) tree.root().getElement().getKey());
    	assertEquals(49, (int) tree.left(tree.root()).getElement().getKey());
    	assertEquals(60, (int) tree.right(tree.root()).getElement().getKey());
    	assertEquals(48, (int) tree.left(tree.left(tree.root())).getElement().getKey());
    	assertEquals(50, (int) tree.right(tree.left(tree.root())).getElement().getKey());
    	assertEquals(90, (int) tree.right(tree.right(tree.root())).getElement().getKey());
    	assertEquals(6, tree.size());
    	//      52
    	//     / \
    	//   49   60
    	//  / \    \
    	// 48  50   90
    	
    	assertEquals("F", tree.remove(52));
    	assertEquals(60, (int) tree.root().getElement().getKey());
    	assertEquals(49, (int) tree.left(tree.root()).getElement().getKey());
    	assertEquals(90, (int) tree.right(tree.root()).getElement().getKey());
    	assertEquals(48, (int) tree.left(tree.left(tree.root())).getElement().getKey());
    	assertEquals(50, (int) tree.right(tree.left(tree.root())).getElement().getKey());
    	assertEquals(5, tree.size());
    	//      60
    	//     / \
    	//   49   90
    	//  / \    
    	// 48  50  
    	
    	assertEquals("A", tree.remove(48));
    	assertEquals(49, (int) tree.root().getElement().getKey());
    	assertEquals(60, (int) tree.right(tree.root()).getElement().getKey());
    	assertEquals(50, (int) tree.left(tree.right(tree.root())).getElement().getKey());
    	assertEquals(90, (int) tree.right(tree.right(tree.root())).getElement().getKey());
    	assertEquals(4, tree.size());
    	//      49
    	//       \
    	//        60
    	//        / \
    	//       50  90
    	
    	assertEquals("C", tree.remove(90));
    	assertEquals(60, (int) tree.root().getElement().getKey());
    	assertEquals(49, (int) tree.left(tree.root()).getElement().getKey());
    	assertEquals(50, (int) tree.right(tree.left(tree.root())).getElement().getKey());
    	assertEquals(3, tree.size());
    	//        60
    	//        /
    	//       49 
    	//        \
    	//        50
    }
}