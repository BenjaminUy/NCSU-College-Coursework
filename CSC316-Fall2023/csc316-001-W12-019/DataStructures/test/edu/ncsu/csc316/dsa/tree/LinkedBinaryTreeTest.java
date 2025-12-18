package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class LinkedBinaryTreeTest {

    private LinkedBinaryTree<String> tree;
    private Position<String> one;
    private Position<String> two;
    private Position<String> three;
    private Position<String> four;
    private Position<String> five;
    private Position<String> six;
    private Position<String> seven;
    private Position<String> eight;
    private Position<String> nine;
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        assertEquals(10, tree.size());
        tree.set(one, "eleven");
        assertEquals(10, tree.size());
        assertEquals("eleven", tree.root().getElement());
        
        tree.set(nine, "ninety");
        assertEquals(10, tree.size());
        assertEquals("ninety", tree.right(four).getElement());
        
        try {
        	tree.set(null, "one");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        createTree();
        
        assertEquals(10, tree.size());
        tree.set(one, "eleven");
        assertEquals(10, tree.size());
        
        tree.addRight(six, "thirty");
        assertEquals(11, tree.size());
        
        tree.remove(eight);
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three));
        assertEquals(0, tree.numChildren(six));
        assertEquals(2, tree.numChildren(ten));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
    }   
    
    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertEquals(four, tree.parent(eight));
        assertEquals(four, tree.parent(nine));
        assertEquals(ten, tree.parent(seven));
        assertEquals(ten, tree.parent(five));
        assertEquals(two, tree.parent(six));
        assertEquals(two, tree.parent(ten));
        assertEquals(three, tree.parent(four));
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        assertEquals(null, tree.parent(one));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertNull(tree.sibling(one));
        assertEquals(three, tree.sibling(two));
        assertEquals(two, tree.sibling(three));
        assertEquals(six, tree.sibling(ten));
        assertEquals(ten, tree.sibling(six));
        assertEquals(seven, tree.sibling(five));
        assertEquals(five, tree.sibling(seven));
        assertNull(tree.sibling(four));
        assertEquals(eight, tree.sibling(nine));
        assertEquals(nine, tree.sibling(eight));
    }
    
    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertFalse(tree.isInternal(six));
        assertTrue(tree.isInternal(ten));
        assertTrue(tree.isInternal(four));
        assertFalse(tree.isInternal(seven));
        assertFalse(tree.isInternal(five));
        assertFalse(tree.isInternal(eight));
        assertFalse(tree.isInternal(nine));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertFalse(tree.isLeaf(three));
        assertTrue(tree.isLeaf(six));
        assertFalse(tree.isLeaf(ten));
        assertFalse(tree.isLeaf(four));
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        assertFalse(tree.isRoot(three));
        assertFalse(tree.isRoot(six));
        assertFalse(tree.isRoot(ten));
        assertFalse(tree.isRoot(four));
        assertFalse(tree.isRoot(seven));
        assertFalse(tree.isRoot(five));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(nine));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();     
        assertEquals(one, it.next());
        try {
        	it.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
        assertEquals(two, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(three, it.next());
        assertEquals(four, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        assertEquals(six, it.next());
        try {
        	it.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(ten, it.next());
        assertEquals(two, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertEquals(four, it.next());
        assertEquals(three, it.next());
        assertEquals(one, it.next());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertEquals(six, it.next());
        try {
        	it.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
        assertEquals(two, it.next());
        assertEquals(seven, it.next());
        assertEquals(ten, it.next());
        assertEquals(five, it.next());
        assertEquals(one, it.next());
        assertEquals(eight, it.next());
        assertEquals(four, it.next());
        assertEquals(nine, it.next());
        assertEquals(three, it.next());
        assertFalse(it.hasNext());
    }
    
    
    /**
     * Test the output of the toString()
     */
    @Test
    public void testToString() {
    	assertEquals("LinkedBinaryTree[\n]", tree.toString());
        createTree();
        assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   "
        		+ "five\n three\n  four\n   eight\n   nine\n]", tree.toString());
        
        tree.remove(nine);
        tree.remove(eight);
        tree.remove(three);
        
        assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   "
        		+ "five\n four\n]", tree.toString());
        
        
        // Concern here!!
        tree.remove(four);
        assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   "
        		+ "five\n]", tree.toString());
    }
    
    /**
     * Test the output of the levelOrder traversal behavior
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals(one, it.next());  
        try {
        	it.remove();
        } catch (Exception e) {
        	assertTrue(e instanceof UnsupportedOperationException);
        }
        assertEquals(two, it.next());
        assertEquals(three, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(four, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
        createTree();
        try {
        	tree.addLeft(null, "fin"); 
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
        try {
        	tree.addLeft(one, "fin"); 
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
        tree.addLeft(six, "fourteen");
        assertEquals("fourteen", tree.left(six).getElement());
        assertEquals(11, tree.size());
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
        createTree();
        try {
        	tree.addRight(null, "fin"); 
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
        try {
        	tree.addRight(one, "fin"); 
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
        tree.addRight(six, "fourteen");
        assertEquals("fourteen", tree.right(six).getElement());
        assertEquals(11, tree.size());
    }   
    
    /** Or, visually:
    *                    one
    *                /        \
    *             two          three
    *            /   \            /
    *         six   ten          four
    *              /   \        /     \
    *            seven  five  eight nine    
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        try {
        	tree.remove(null); 
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
        try {
        	tree.remove(one); 
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }
        assertEquals("nine", tree.remove(nine));
        assertEquals(9, tree.size());
        assertNull(tree.right(four));
        
        assertEquals("four", tree.remove(four));
        assertEquals(8, tree.size());
        assertEquals(eight, tree.left(three));
        assertNull(tree.right(three));
    }
    
    /**
     * Secondary test for the output of the remove(p) behavior
     */       
    @Test
    public void testRemove2() {           
    	one = tree.addRoot("one");
    	two = tree.addLeft(tree.root(), "two");
    	assertEquals(2, tree.size());
    	assertEquals(one, tree.root());
    	assertEquals(two, tree.left(tree.root()));
    	assertNull(tree.right(tree.root()));
    	assertEquals("LinkedBinaryTree[\none\n two\n]", tree.toString());
    	//                 one
        //               /        
    	//				two 
    	
    	tree.remove(one);
    	assertEquals(1, tree.size());
    	assertEquals(two, tree.root());
    	assertNull(tree.left(tree.root()));
    	assertNull(tree.right(tree.root()));
    	assertEquals("LinkedBinaryTree[\ntwo\n]", tree.toString());
    	// 				two
    	
    	three = tree.addRight(tree.root(), "three");
    	four = tree.addLeft(three, "four");
    	five = tree.addRight(three, "five");
    	assertEquals(4, tree.size());
    	assertEquals(two, tree.root());
    	assertNull(tree.left(two));
    	assertEquals(three, tree.right(two));
    	assertEquals(four, tree.left(three));
    	assertEquals(five, tree.right(three));
    	assertEquals("LinkedBinaryTree[\ntwo\n three\n  four\n  five\n]", tree.toString());
    	// 				two
    	//                \
    	//                three
    	//                /    \
    	//              four   five
    	
    	tree.remove(tree.root());
    	assertEquals(3, tree.size());
    	assertEquals(three, tree.root());
    	assertEquals(four, tree.left(three));
    	assertEquals(five, tree.right(three));
    	assertNull(tree.left(four));
    	assertNull(tree.right(four));
    	assertNull(tree.left(five));
    	assertNull(tree.right(five));
    	assertEquals("LinkedBinaryTree[\nthree\n four\n five\n]", tree.toString());
    	//                three
    	//                /    \
    	//              four   five
    }
}