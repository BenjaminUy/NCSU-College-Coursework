package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

class LinkedStackTest<E> {

	@Test
	void testLinkedStack() {
		LinkedStack<E> ls = new LinkedStack<E>(10);
		assertEquals(0, ls.size());
	}
	
	@Test
	void testPush() {
		LinkedStack<Integer> ls = new LinkedStack<Integer>(10);
		ls.push(1);
		assertEquals(1, ls.size());
		assertEquals(1, ls.pop());
		assertEquals(0, ls.size());
	}

	@Test
	void testPop() {
		LinkedStack<Integer> ls = new LinkedStack<Integer>(10);
		ls.push(1);
		assertEquals(1, ls.size());
		assertEquals(1, ls.pop());
		assertEquals(0, ls.size());
		assertThrows(EmptyStackException.class,
				() -> ls.pop());
	}

	@Test
	void testIsEmpty() {
		LinkedStack<Integer> ls = new LinkedStack<Integer>(10);
		ls.push(1);
		assertFalse(ls.isEmpty());
		assertEquals(1, ls.pop());
		assertTrue(ls.isEmpty());
	}

	@Test
	void testSize() {
		LinkedStack<Integer> ls = new LinkedStack<Integer>(10);
		assertEquals(0, ls.size());
		ls.push(1);
		assertEquals(1, ls.size());
		ls.push(2);
		assertEquals(2, ls.size());
		ls.push(3);
		assertEquals(3, ls.size());
	}

	@Test
	void testSetCapacity() {
		LinkedStack<Integer> ls = new LinkedStack<Integer>(1);
		ls.push(1);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> ls.push(2));
		assertEquals("Full capacity", e1.getMessage());
		ls.setCapacity(2);
		ls.push(2);
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> ls.setCapacity(1));
		assertEquals("Invalid capacity", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> ls.setCapacity(-1));
		assertEquals("Invalid capacity", e3.getMessage());
	}

}
