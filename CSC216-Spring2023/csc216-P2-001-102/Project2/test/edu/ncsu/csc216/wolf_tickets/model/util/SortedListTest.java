package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class tests SortedList.java
 * @author Benjamin Uy
 */
class SortedListTest {

	/**
	 * Tests SortedList constructor
	 */
	@Test
	void testSortedList() {
		SortedList<String> s = new SortedList<String>();
		assertEquals(0, s.size());
	}

	/**
	 * Tests SortedList add()
	 */
	@Test
	void testAdd() {
		SortedList<String> s = new SortedList<String>();
		
		s.add("b");
		assertEquals(1, s.size());
		s.add("c");
		assertEquals(2, s.size());
		assertEquals("b", s.get(0));
		assertEquals("c", s.get(1));
		s.add("a");
		assertEquals(3, s.size());
		assertEquals("a", s.get(0));
		assertEquals("b", s.get(1));
		assertEquals("c", s.get(2));
		
		Exception e = assertThrows(NullPointerException.class,
				() -> s.add(null));	
		assertEquals("Cannot add null element.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class,
				() -> s.add("b"));
		assertEquals("Cannot add duplicate element.", e.getMessage());
		
		s.add("e");
		assertEquals(4, s.size());
		assertEquals("a", s.get(0));
		assertEquals("b", s.get(1));
		assertEquals("c", s.get(2));
		assertEquals("e", s.get(3));
		
		s.add("d");
		assertEquals(5, s.size());
		assertEquals("a", s.get(0));
		assertEquals("b", s.get(1));
		assertEquals("c", s.get(2));
		assertEquals("d", s.get(3));
		assertEquals("e", s.get(4));
	}

	/**
	 * Tests SortedList remove()
	 */
	@Test
	void testRemove() {
		SortedList<String> s = new SortedList<String>();
		
		Exception e = assertThrows(IndexOutOfBoundsException.class,
				() -> s.get(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class,
				() -> s.get(2));
		assertEquals("Invalid index.", e.getMessage());
		
		s.add("b");
		assertEquals(1, s.size());
		assertEquals("b", s.remove(0));
		assertEquals(0, s.size());
		
		s.add("c");
		assertEquals(1, s.size());
		s.add("a");
		assertEquals(2, s.size());
		assertEquals("a", s.get(0));
		assertEquals("c", s.get(1));
		
		assertEquals("c", s.remove(1));
		assertEquals(1, s.size());
		assertEquals("a", s.remove(0));
		assertEquals(0, s.size());	
	}
	
	/**
	 * Tests SortedList remove(); based on TS test
	 */
	@Test
	void testRemove2() {
		SortedList<String> s = new SortedList<String>();
		
		s.add("banana");
		s.add("apple");
		s.add("orange");
		s.add("eggplant");
		assertEquals(4, s.size());
		assertEquals("apple", s.get(0));
		assertEquals("banana", s.get(1));
		assertEquals("eggplant", s.get(2));
		assertEquals("orange", s.get(3));
		
		assertEquals("banana", s.remove(1));
		assertEquals(3, s.size());
		assertEquals("apple", s.get(0));
		assertEquals("eggplant", s.get(1));
		assertEquals("orange", s.get(2));
	}

	/**
	 * Method tests SortedList contains()
	 */
	@Test
	void testContains() {
		SortedList<String> s = new SortedList<String>();
		assertFalse(s.contains("s"));
		s.add("s");
		assertTrue(s.contains("s"));
	}

	/**
	 * Method tests SortedList get()
	 */
	@Test
	void testGet() {
		SortedList<String> s = new SortedList<String>();
		s.add("1");
		assertEquals("1", s.get(0));
		
		Exception e = assertThrows(IndexOutOfBoundsException.class,
				() -> s.get(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class,
				() -> s.get(1));
		assertEquals("Invalid index.", e.getMessage());
		
		s.add("2");
		assertEquals("2", s.get(1));
	}
}
