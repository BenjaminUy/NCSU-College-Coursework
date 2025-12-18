package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class ArrayQueueTest {

	@Test
	void testEnqueue() {
		ArrayQueue<Integer> aq = new ArrayQueue<Integer>(10);
		aq.enqueue(1);
		assertEquals(1, aq.size());
		aq.enqueue(2);
		assertEquals(2, aq.size());
		assertEquals(1, aq.dequeue());
		assertEquals(2, aq.dequeue());
		ArrayQueue<Integer> aq1 = new ArrayQueue<Integer>(1);
		aq1.enqueue(1);
		assertThrows(IllegalArgumentException.class, () -> aq1.enqueue(2));
	}

	@Test
	void testDequeue() {
		ArrayQueue<Integer> aq = new ArrayQueue<Integer>(1);
		assertThrows(NoSuchElementException.class, () -> aq.dequeue());
	}

	@Test
	void testIsEmpty() {
		ArrayQueue<Integer> aq = new ArrayQueue<Integer>(2);
		assertTrue(aq.isEmpty());
		aq.enqueue(1);
		assertFalse(aq.isEmpty());
	}

	@Test
	void testSize() {
		ArrayQueue<Integer> aq = new ArrayQueue<Integer>(5);
		assertEquals(0, aq.size());
		aq.enqueue(1);
		aq.enqueue(2);
		assertEquals(2, aq.size());
	} 

	@Test
	void testSetCapacity() {
		ArrayQueue<Integer> aq = new ArrayQueue<Integer>(5);
		assertThrows(IllegalArgumentException.class, () -> aq.setCapacity(-1));
		aq.enqueue(1);
		aq.enqueue(2);
		assertThrows(IllegalArgumentException.class, () -> aq.setCapacity(1));
	}

}
