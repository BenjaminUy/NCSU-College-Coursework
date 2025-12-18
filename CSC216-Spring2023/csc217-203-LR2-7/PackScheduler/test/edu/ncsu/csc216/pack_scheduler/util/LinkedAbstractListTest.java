/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class tests LinkedAbstractList.java
 * @author ajwdr
 * @author bsuy
 * @author hctynch
 */
public class LinkedAbstractListTest {
	
	/**
	 * Test method for LinkedAbstractList.add().
	 */
	@Test
	void testAdd() {
		LinkedAbstractList<String> temp = new LinkedAbstractList<String>(5);
		
		temp.add(0, "hi");
		assertEquals("hi", temp.get(0));
		
		temp.add(0, "2");
		assertEquals("2", temp.get(0));
		assertEquals(2, temp.size());
		assertEquals("hi", temp.get(1));
		
		temp.add(0, "3");
		assertEquals("3", temp.get(0));
		assertEquals(3, temp.size());
		assertEquals("2", temp.get(1));
		
		temp.add(1, "0");
		assertEquals("3", temp.get(0));
		assertEquals(4, temp.size());
		assertEquals("0", temp.get(1));
		
		temp.add(2, "5");
		assertEquals("5", temp.get(2));
	}
	
	/**
	 * Test method for LinkedAbstractList Constructor.
	 */
	@Test
	void testLinkedAbstractListConstructor() {
		LinkedAbstractList<String> link = new LinkedAbstractList<String>(1);
		assertEquals(0, link.size());
		
		// Check the LinkedAbstractList capacity by adding elements
		link.add(0, "hi");
		assertEquals("hi", link.get(0));
		assertEquals(1, link.size());
		
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> link.add(0, "hi again"));
		assertEquals("Full capacity", e.getMessage());
	}

	/**
	 * Test method for LinkedAbstractList.set().
	 */
	@Test
	void testSet() {
		LinkedAbstractList<String> l = new LinkedAbstractList<String>(5);
		Exception e2 = assertThrows(IndexOutOfBoundsException.class, () -> l.set(0, "hi"));
		assertEquals("Invalid index", e2.getMessage());
		l.add(0, "hi");
		assertEquals("hi", l.set(0, "bye"));
		assertEquals("bye", l.get(0));
		assertEquals(1, l.size());
		l.add(1, "hi");
		l.add(2, "hello");
		assertEquals("hi", l.set(1, "goodbye"));
		assertEquals("bye", l.get(0));
		assertEquals("goodbye", l.get(1));
		assertEquals("hello", l.get(2));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> l.set(5, "hi"));
		assertEquals("Invalid index", e1.getMessage());
	}

	/**
	 * Test method for LinkedAbstractList.remove()
	 */
	@Test
	void testRemoveInt() {
		LinkedAbstractList<String> l = new LinkedAbstractList<String>(5);
		l.add("hi");
		l.add("bye");
		l.add("hello");
		l.add("goodbye");
		
		assertEquals(4, l.size());
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, () -> l.remove(5));
		assertEquals("Invalid index", e1.getMessage());
		assertEquals("hello", l.remove(2));
		assertEquals(3, l.size());
		assertEquals("hi", l.get(0));
		assertEquals("bye", l.get(1));
		assertEquals("goodbye", l.get(2));
		
		assertEquals("hi", l.remove(0));
		assertEquals(2, l.size());
		assertEquals("bye", l.get(0));
		assertEquals("goodbye", l.get(1));
		
		assertEquals("goodbye", l.remove(1));
		assertEquals(1, l.size());
		assertEquals("bye", l.get(0));
		
		assertEquals("bye", l.remove(0));
		assertEquals(0, l.size());
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> l.remove(0));
		assertEquals("Invalid index", e.getMessage());
	}


}
