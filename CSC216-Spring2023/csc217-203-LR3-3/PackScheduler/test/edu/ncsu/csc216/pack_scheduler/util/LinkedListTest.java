package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Tests LinkedList
 * @author Hank Lenham
 * @author Noah Anderson
 * @author Benjamin Uy
 */
class LinkedListTest {

	/**
	 * Tests construction of LinkedList.
	 */
	@Test
	void testLinkedList() {
		LinkedList<String> a = new LinkedList<String>();
		
		assertEquals(0, a.size());	
	}

	/**
	 * Tests add().
	 */
	@Test
	void testAddAll() {
		LinkedList<String> a = new LinkedList<String>();
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> a.add(1, "error"));
		assertEquals("Index out of range.", e.getMessage());
		
		Exception e2 = assertThrows(NullPointerException.class, () -> a.add(0, null));
		assertEquals("Null element.", e2.getMessage());
		  
		a.add(0, "first");
		assertEquals(1, a.size());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> a.add(1, "first"));
		assertEquals("Duplicate element.", e3.getMessage());
		
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
		
		LinkedList<String> a1 = new LinkedList<String>();
		a1.add(0, "orange");
		a1.add(1, "banana");
		a1.add(2, "apple");
		a1.add(3, "kiwi");
		assertEquals("banana", a1.remove(1));
		assertEquals("apple", a1.get(1));
	}

	/**
	 * Tests remove().
	 */
	@Test
	void testRemoveInt() {
		LinkedList<String> a = new LinkedList<String>();

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
	
	}

	/**
	 * Tests set().
	 */
	@Test
	void testSetIntE() {
		LinkedList<String> a = new LinkedList<String>();
		
		Exception e = assertThrows(IndexOutOfBoundsException.class, () -> a.add(1, "error"));
		assertEquals("Index out of range.", e.getMessage());
		
		Exception e2 = assertThrows(NullPointerException.class, () -> a.add(0, null));
		assertEquals("Null element.", e2.getMessage());
		
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> a.set(0, "new"));
		assertEquals("Invalid index.", e3.getMessage());
		
		a.add(0, "first");
		assertEquals("first", a.get(0));
		assertEquals(1, a.size());
		
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> a.add(1, "first"));
		assertEquals("Duplicate element.", e4.getMessage());
		
		assertEquals("first", a.set(0, "second"));
		assertEquals("second", a.get(0));
		assertEquals(1, a.size());
	}
	
	/**
	 * Tests LinkedListIterator.hasNext() and .hasPrevious()
	 * for an empty list (size of zero)
	 */
	@Test
	void testHasNextAndHasPrevious() {
		LinkedList<String> a = new LinkedList<String>();
		assertFalse(a.listIterator(0).hasPrevious());
		assertFalse(a.listIterator(0).hasNext());
		assertEquals(0, a.listIterator(0).nextIndex());
		assertEquals(-1, a.listIterator(0).previousIndex());	
	}
	
	/**
	 * Tests LinkedListIterator.next() and .previous()
	 * for an empty list (size of zero)
	 */
	@Test
	void testNextAndPrevious() {
		LinkedList<String> a = new LinkedList<String>();
		assertThrows(NoSuchElementException.class,
				() -> a.listIterator(0).next());
		assertThrows(NoSuchElementException.class,
				() -> a.listIterator(0).previous());
	}
	
	/**
	 * Tests LinkedListIterator.add()
	 */
	@Test 
	void testListIteratorAdd() {
		LinkedList<String> a = new LinkedList<String>();
		
		ListIterator<String> li = a.listIterator(0);
		
		Exception e = assertThrows(NullPointerException.class,
				() -> li.add(null));
		assertEquals("Null element.", e.getMessage());
		
		// Add first element
		li.add("First element");
		assertEquals(1, a.size());
		assertEquals(0, li.previousIndex());
		assertEquals(1, li.nextIndex());
		assertFalse(li.hasNext());
		assertTrue(li.hasPrevious());
		assertEquals("First element", li.previous());	
		
		// Add second element
		li.add("Second");						
		assertEquals(2, a.size());
		assertEquals(0, li.previousIndex());
		assertEquals(1, li.nextIndex());
		assertTrue(li.hasNext());
		assertTrue(li.hasPrevious());
		assertEquals("Second", li.previous());
	}
	
	@Test
	void testLastIndexOf() {
		LinkedList<String> a = new LinkedList<String>();
		a.add(0, "orange");
		a.add(1, "banana");
		a.add(2, "apple");
		a.add(3, "kiwi");
		assertEquals("orange", a.get(0));
		assertEquals(0, a.lastIndexOf("orange"));
		assertEquals("orange", a.get(0));
	}
}
