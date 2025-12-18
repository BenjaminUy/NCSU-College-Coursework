package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

class ArrayStackTest<E> {

	@Test
	void testArrayStack() {
		ArrayStack<E> ls = new ArrayStack<E>(10);
		assertEquals(0, ls.size());
	}
	
	@Test
	void testPush() {
		ArrayStack<Integer> ls = new ArrayStack<Integer>(10);
		ls.push(1);
		assertEquals(1, ls.size());
		assertEquals(1, ls.pop());
		assertEquals(0, ls.size());
	}

	@Test
	void testPop() {
		ArrayStack<Integer> ls = new ArrayStack<Integer>(10);
		ls.push(1);
		assertEquals(1, ls.size());
		assertEquals(1, ls.pop());
		assertEquals(0, ls.size());
		assertThrows(EmptyStackException.class,
				() -> ls.pop());
	}

	@Test
	void testIsEmpty() {
		ArrayStack<Integer> ls = new ArrayStack<Integer>(10);
		ls.push(1);
		assertFalse(ls.isEmpty());
		assertEquals(1, ls.pop());
		assertTrue(ls.isEmpty());
	}

	@Test
	void testSize() {
		ArrayStack<Integer> ls = new ArrayStack<Integer>(10);
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
		ArrayStack<Integer> as = new ArrayStack<Integer>(1);
		as.push(1);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> as.push(2));
		assertEquals("Full capacity", e1.getMessage());
		as.setCapacity(2);
		as.push(2);
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> as.setCapacity(1));
		assertEquals("Invalid capacity", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> as.setCapacity(-1));
		assertEquals("Invalid capacity", e3.getMessage());
	}
}
