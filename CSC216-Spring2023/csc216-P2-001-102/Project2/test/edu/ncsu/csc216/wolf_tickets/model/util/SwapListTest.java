package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Class tests SwapList.java
 * @author Benjamin Uy
 */
class SwapListTest {

	/**
	 * Tests SwapList constructor
	 */
	@Test
	void testSwapList() {
		SwapList<String> list = new SwapList<String>();
		assertEquals(0, list.size());
	}

	/**
	 * Tests SwapList add()
	 */
	@Test
	void testAdd() {
		SwapList<String> list = new SwapList<String>();
		list.add("1");
		assertEquals(1, list.size());
		list.add("2");
		assertEquals(2, list.size());
		Exception e = assertThrows(NullPointerException.class, 
				() -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());
		
		// Continue to add elements until full capacity is reached
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		assertEquals(10, list.size());

		list.add("11");		// Add additional element after surpassing initial capacity
	}

	/**
	 * Tests SwapList remove()
	 */
	@Test
	void testRemove() {
		SwapList<String> list = new SwapList<String>();
		Exception e = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class, 
					() -> list.remove(1));
			assertEquals("Invalid index.", e.getMessage());
		list.add("1");
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}

	/**
	 * Test SwapList moveUp()
	 */
	@Test
	void testMoveUp() {
		SwapList<String> list = new SwapList<String>();
		Exception e = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.moveUp(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class, 
					() -> list.moveUp(1));
			assertEquals("Invalid index.", e.getMessage());
		list.add("1");
		list.add("2");
		assertEquals(2, list.size());
		list.moveUp(1);
		assertEquals(2, list.size());
		assertEquals("2", list.get(0));
		assertEquals("1", list.get(1));
	}

	/**
	 * Test SwapList moveDown()
	 */
	@Test
	void testMoveDown() {
		SwapList<String> list = new SwapList<String>();
		Exception e = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.moveDown(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class, 
					() -> list.moveDown(1));
			assertEquals("Invalid index.", e.getMessage());
		list.add("1");
		list.add("2");
		assertEquals(2, list.size());
		list.moveDown(0);
		assertEquals(2, list.size());
		assertEquals("2", list.get(0));
		assertEquals("1", list.get(1));
	}

	/**
	 * Test SwapList moveToFront()
	 */
	@Test
	void testMoveToFront() {
		SwapList<String> list = new SwapList<String>();
		Exception e = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.moveToFront(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class, 
					() -> list.moveToFront(1));
			assertEquals("Invalid index.", e.getMessage());
		list.add("1");
		list.add("2");
		list.add("3");
		assertEquals(3, list.size());
		list.moveToFront(1);
		assertEquals(3, list.size());
		assertEquals("2", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("3", list.get(2));
		list.moveToFront(2);
		assertEquals(3, list.size());
		assertEquals("3", list.get(0));
		assertEquals("2", list.get(1));
		assertEquals("1", list.get(2));
	}

	/**
	 * Test SwapList moveToBack()
	 */
	@Test
	void testMoveToBack() {
		SwapList<String> list = new SwapList<String>();
		Exception e = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.moveToFront(-1));
		assertEquals("Invalid index.", e.getMessage());
		e = assertThrows(IndexOutOfBoundsException.class, 
					() -> list.moveToFront(1));
			assertEquals("Invalid index.", e.getMessage());
		list.add("1");
		list.add("2");
		list.add("3");
		assertEquals(3, list.size());
		list.moveToBack(1);
		assertEquals(3, list.size());
		assertEquals("1", list.get(0));
		assertEquals("3", list.get(1));
		assertEquals("2", list.get(2));
		list.moveToBack(0);
		assertEquals(3, list.size());
		assertEquals("3", list.get(0));
		assertEquals("2", list.get(1));
		assertEquals("1", list.get(2));
	}

}
