/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests ArrayList class
 * 
 * @author hunt tynch
 * @author andrew warren
 * @author benjamin uy
 *
 */
class ArrayListTest {


	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#ArrayList()}.
	 */
	@Test
	void testArrayList() {
		ArrayList<String> a = new ArrayList<String>();
		
		assertEquals(0, a.size());	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	void testAddIntE() {
		ArrayList<String> a = new ArrayList<String>();
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> a.add(1, "error"));
		assertEquals("Index out of range.", e.getMessage());
		
		Exception e2 = assertThrows(NullPointerException.class, () -> a.add(0, null));
		assertEquals("Null element.", e2.getMessage());
		
		a.add(0, "first");
		assertEquals("first", a.get(0));
		assertEquals(1, a.size());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> a.add(1, "first"));
		assertEquals("Duplicate Element.", e3.getMessage());
		
		a.add(1, "last");
		assertEquals("first", a.get(0));
		assertEquals("last", a.get(1));
		assertEquals(2, a.size());
		
		a.add(1, "mid");
		assertEquals("first", a.get(0));
		assertEquals("last", a.get(2));
		assertEquals("mid", a.get(1));
		assertEquals(3, a.size());
		
		
		a.add(3, "3");
		a.add(4, "4");
		a.add(5, "5");
		a.add(6, "6");
		a.add(7, "7");
		a.add(8, "8");
		a.add(9, "9");
		assertEquals(10, a.size());
		
		a.add(0, "x");
		assertEquals("x", a.get(0));
		assertEquals("9", a.get(10));
		assertEquals(11, a.size());
		
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add(0, "orange");
		a1.add(1, "banana");
		a1.add(2, "apple");
		a1.add(3, "kiwi");
		assertEquals("banana", a1.remove(1));
		assertEquals("apple", a1.get(1));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#remove(int)}.
	 */
	@Test
	void testRemoveInt() {
		ArrayList<String> a = new ArrayList<String>();

		
		
		a.add(0, "first");
		assertEquals("first", a.get(0));
		assertEquals(1, a.size());
		
		a.add(1, "last");
		assertEquals("first", a.get(0));
		assertEquals("last", a.get(1));
		assertEquals(2, a.size());
		
		a.add(1, "mid");
		assertEquals("first", a.get(0));
		assertEquals("last", a.get(2));
		assertEquals("mid", a.get(1));
		assertEquals(3, a.size());
		
		assertEquals("mid", a.remove(1));
		assertEquals(2, a.size());
		assertEquals("first", a.get(0));
		assertEquals("last", a.get(1));
		
		assertEquals("last", a.remove(1));
		assertEquals(1, a.size());
		assertEquals("first", a.get(0));
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> a.remove(1));
				assertEquals("Index out of range.", e.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayList#set(int, java.lang.Object)}.
	 */
	@Test
	void testSetIntE() {
		ArrayList<String> a = new ArrayList<String>();
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> a.add(1, "error"));
		assertEquals("Index out of range.", e.getMessage());
		
		Exception e2 = assertThrows(NullPointerException.class, () -> a.add(0, null));
		assertEquals("Null element.", e2.getMessage());
		
		a.add(0, "first");
		assertEquals("first", a.get(0));
		assertEquals(1, a.size());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> a.add(1, "first"));
		assertEquals("Duplicate Element.", e3.getMessage());
		
		assertEquals("first", a.set(0, "second"));
		assertEquals("second", a.get(0));
		assertEquals(1, a.size());
	}

}
