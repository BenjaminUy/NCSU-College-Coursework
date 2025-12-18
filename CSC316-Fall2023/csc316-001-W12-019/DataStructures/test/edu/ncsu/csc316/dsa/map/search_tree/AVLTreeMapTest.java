package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class AVLTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(5, "L");
        tree.put(6, "T");
        tree.put(2, "C");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(3, tree.size());
        //		 5
        //		/ \
        //     2   6 
        
        tree.put(4, "H");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(4, tree.size());
        //		 5
        //		/ \
        //     2   6 
        //     \
        //      4
        
        tree.put(3, "F");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(5, tree.size());
        //		 5
        //		/ \
        //     3   6 
        //    /\
        //   2  4
        
        tree.put(7, "A");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(7, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(6, tree.size());
        //		 5
        //		/ \
        //     3   6 
        //    /\    \
        //   2  4    7
        
        tree.put(8, "B");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(7, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(8, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(6, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(3, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(7, tree.size());
        //		 5
        //		/ \
        //     3   7 
        //    /\   /\
        //   2  4 6  8
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
        assertNull(tree.get(1));
        
        tree.put(5, "L");
        tree.put(6, "T");
        tree.put(2, "C");
        assertEquals(5, (int) tree.root().getElement().getKey());
        assertEquals(6, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(2, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(3, tree.size());
        //		 5
        //		/ \
        //     2   6 
        
        assertEquals("L", tree.get(5));
        assertEquals("T", tree.get(6));
        assertEquals("C", tree.get(2));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        
        // Example based on AVL Tree lecture slides for remove method
        tree.put(44, "A");
        tree.put(17, "B");
        tree.put(62, "C");
        tree.put(32, "D");
        tree.put(50, "E");
        tree.put(78, "F");
        tree.put(48, "G");
        tree.put(54, "H");
        assertEquals(8, tree.size());
        assertEquals(44, (int) tree.root().getElement().getKey());
        assertEquals(17, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(62, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(32, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(50, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(78, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(48, (int) tree.left(tree.left(tree.right(tree.root()))).getElement().getKey());
        assertEquals(54, (int) tree.right(tree.left(tree.right(tree.root()))).getElement().getKey());
        //         44
        //        /  \
        //      17    62
        //       \    /  \
        //       32   50  78
        //           /  \
        //          48  54
        
        assertEquals("D", tree.remove(32));
        assertEquals(7, tree.size());
        assertEquals(50, (int) tree.root().getElement().getKey());
        assertEquals(44, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(62, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(48, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(17, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(54, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(78, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        //         50
        //        /  \
        //      44    62
        //     / \    / \
        //    17 48  54  78
        
        assertEquals("E", tree.remove(50));
        assertEquals(6, tree.size());
        assertEquals(54, (int) tree.root().getElement().getKey());
        assertEquals(44, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(62, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(48, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(17, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(78, (int) tree.right(tree.right(tree.root())).getElement().getKey());
        //         54
        //        /  \
        //      44    62
        //     / \      \
        //    17 48      78
        
        assertEquals("C", tree.remove(62));
        assertEquals(5, tree.size());
        assertEquals(54, (int) tree.root().getElement().getKey());
        assertEquals(44, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(78, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(48, (int) tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(17, (int) tree.left(tree.left(tree.root())).getElement().getKey());
        //         54
        //        /  \
        //      44    78
        //     / \      
        //    17 48      
        
        assertEquals("F", tree.remove(78));
        assertEquals(4, tree.size());
        assertEquals(44, (int) tree.root().getElement().getKey());
        assertEquals(17, (int) tree.left(tree.root()).getElement().getKey());
        assertEquals(54, (int) tree.right(tree.root()).getElement().getKey());
        assertEquals(48, (int) tree.left(tree.right(tree.root())).getElement().getKey());
        //         44
        //        /  \
        //      17    54  
        //           /   
        //          48      
    }
}