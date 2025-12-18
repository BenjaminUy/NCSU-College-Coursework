package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class LinkedQueueTest {

	@Test
	void testEnqueue() {
		LinkedQueue<Integer> lq = new LinkedQueue<Integer>(10);
		lq.enqueue(1);
		assertEquals(1, lq.size());
		lq.enqueue(2);
		assertEquals(2, lq.size());
		assertEquals(1, lq.dequeue());
		assertEquals(2, lq.dequeue());
		LinkedQueue<Integer> lq1 = new LinkedQueue<Integer>(1);
		lq1.enqueue(1);
		assertThrows(IllegalArgumentException.class, () -> lq1.enqueue(2));
	}

	@Test
	void testDequeue() {
		LinkedQueue<Integer> lq = new LinkedQueue<Integer>(1);
		assertThrows(NoSuchElementException.class, () -> lq.dequeue());
	}

	@Test
	void testIsEmpty() {
		LinkedQueue<Integer> lq = new LinkedQueue<Integer>(2);
		assertTrue(lq.isEmpty());
		lq.enqueue(1);
		assertFalse(lq.isEmpty());
	}

	@Test
	void testSize() {
		LinkedQueue<Integer> lq = new LinkedQueue<Integer>(5);
		assertEquals(0, lq.size());
		lq.enqueue(1);
		lq.enqueue(2);
		assertEquals(2, lq.size());
	} 

	@Test
	void testSetCapacity() {
		LinkedQueue<Integer> lq = new LinkedQueue<Integer>(5);
		assertThrows(IllegalArgumentException.class, () -> lq.setCapacity(-1));
		lq.enqueue(1);
		lq.enqueue(2);
		assertThrows(IllegalArgumentException.class, () -> lq.setCapacity(1));
	}

}
