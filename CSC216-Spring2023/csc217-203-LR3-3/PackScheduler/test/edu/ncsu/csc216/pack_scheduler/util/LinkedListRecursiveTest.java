package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListRecursiveTest {

	@Test
	void testAddIndex() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		assertThrows(IndexOutOfBoundsException.class, () -> llr.add(-1, "d"));
		assertThrows(NullPointerException.class, () -> llr.add(0, null));
		assertThrows(IndexOutOfBoundsException.class, () -> llr.add(5, "a"));

	}
	
	@Test
	void testRemoveIdx() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		llr.add("apple");
		llr.add("banana");
		llr.add(1, "pineapple");
		assertThrows(IndexOutOfBoundsException.class, () -> llr.remove(-1));
	}
	
	@Test
	void testAddIntE() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		llr.add("apple");
		llr.add("banana");
		llr.add(1, "pineapple");
		assertEquals(3, llr.size());
		assertTrue(llr.contains("apple"));
		assertTrue(llr.contains("pineapple"));
	}
	
	@Test
	void testGet() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		llr.add("apple");
		llr.add("banana");
		llr.add("pear");
		assertEquals("apple", llr.get(0));
		assertThrows(IndexOutOfBoundsException.class, () -> llr.get(-1));
		assertEquals("banana", llr.get(1));
		assertEquals("pear", llr.get(2));
	}
	
	@Test
	void testRemove1() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		llr.add("apple");
		llr.add("banana");
		llr.add("pear");
		assertEquals("apple", llr.remove(0));
		assertEquals("pear", llr.remove(1));
	}
	
	@Test
	void testRemove2() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		llr.add("apple");
		llr.add("banana");
		llr.add("pear");
		assertTrue(llr.remove("banana"));
		assertTrue(llr.remove("pear"));
		assertEquals(1, llr.size());
		assertEquals("apple", llr.get(0));
	}
	
	/**
	 * Test LinkedList remove(); based on TSLinkedListRecursiveTest.testRemoveInt
	 */
	@Test
	void testRemove3() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		llr.add("orange");
		llr.add("banana");
		llr.add("apple");
		llr.add("kiwi");
		assertEquals("banana", llr.remove(1));
		assertEquals("apple", llr.get(1));
	}
	
	@Test
	void testSet() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		llr.add("apple");
		llr.add("pear");
		assertThrows(IndexOutOfBoundsException.class, () -> llr.set(5, "banana"));
		assertEquals("apple", llr.set(0, "banana"));
		assertEquals("banana", llr.get(0));
		assertEquals("pear", llr.set(1, "orange"));
		assertEquals("orange", llr.get(1));
		assertThrows(IllegalArgumentException.class, () -> llr.set(0, "banana"));
		assertThrows(NullPointerException.class, () -> llr.set(0, null));
	}
	
	@Test
	void testAddIntE2() {
		LinkedListRecursive<String> llr = new LinkedListRecursive<String>();
		llr.add(0, "1");
		assertEquals("1", llr.get(0));
		llr.add(1, "2");
		assertEquals("1", llr.get(0));
		assertEquals("2", llr.get(1));
	}

}
