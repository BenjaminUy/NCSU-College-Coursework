package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for SortedList class.
 * @author Connor Hekking, Hunt Tynch, Kartik Alle
 */

public class SortedListTest {

	/**
	 * Tests that SortedList is constructed correctly with the correct size and elements. 
	 * Test that the list size can increase.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		assertTrue(list.isEmpty());
		
		//Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		for(int x = 0; x < 10; x++) {
			list.add("apple" + x);
		}
		assertTrue(list.contains("apple1"));
		list.add("banana");
		assertEquals(11, list.size());
		assertTrue(list.contains("banana"));
		
		
	}


	/**
	 * Tests add method of SortedList. 
	 * Tests that element is added correctly and at the right spot. 
	 * Additionally tests null and duplicate elements.
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		// Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals("apple", list.get(0));
		assertEquals(2, list.size());
		assertEquals("banana", list.get(1));
		list.add("azul");
		assertEquals("apple", list.get(0));
		assertEquals(3, list.size());
		assertEquals("azul", list.get(1));
		assertEquals("banana", list.get(2));
		list.add("carrot");
		assertEquals("apple", list.get(0));
		assertEquals(4, list.size());
		assertEquals("azul", list.get(1));
		assertEquals("banana", list.get(2));
		assertEquals("carrot", list.get(3));
		
		// Test adding a null element
		assertThrows(NullPointerException.class, () -> list.add(null));
		// Test adding a duplicate element
		assertThrows(IllegalArgumentException.class, () -> list.add("apple"));
	}
	
	/**
	 * Tests get method of SortedList. 
	 * Test get on empty list, on a list with elements, at an invalid index, and at index size.
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		// Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		// Add some elements to the list
		list.add("apple");
		list.add("banana");
		assertEquals("apple", list.get(0));
		// Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		// Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
		
	}
	
	/**
	 * Tests remove method of SortedList.
	 * Tests removing from empty list, list with elements, invalid index, index size. 
	 * Tests removing middle, last, and first element.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		// Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		// Add some elements to the list - at least 4
		list.add("apple");
		list.add("azul");
		list.add("banana");
		list.add("carrot");
		// Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		// Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		// Test removing a middle element
		assertEquals("azul", list.remove(1));
		assertEquals("banana", list.get(1));
		assertFalse(list.contains("azul"));
		assertEquals("apple", list.get(0));
		assertEquals(3, list.size());
		// Test removing the last element
		assertEquals("carrot", list.remove(2));
		assertEquals(2, list.size());
		// Test removing the first element
		assertEquals("apple", list.remove(0));
		assertEquals(1, list.size());
		// Test removing the last element
		assertEquals("banana", list.remove(0));
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests indexOf method of SortedList. 
	 * Tests empty list, non-empty list, correct and invalid indexes.
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));
		// Add some elements
		list.add("apple");
		list.add("banana");
		// Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(-1, list.indexOf("carrot"));
		// Test checking the index of null
		assertThrows(NullPointerException.class, () -> list.indexOf(null));
	}
	
	/**
	 * Tests clear method of SortedList. 
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("apple");
		list.add("banana");
		// Clear the list
		list.clear();
		// Test that the list is empty
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests isEmpty method of SortedList. 
	 * Tests empty and non-empty list.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		//Add at least one element
		list.add("apple");
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests contains method of SortedList. 
	 * Tests empty list, true and false cases.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		// Test the empty list case
		assertFalse(list.contains("apple"));
		// Add some elements
		list.add("apple");
		list.add("banana");
		// Test some true and false cases
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("carrot"));
	}
	
	/**
	 * Tests equals method of SortedList. 
	 * Test for equality and non-equality.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("apple");
		list2.add("apple");
		list3.add("banana");
		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list1));
		assertFalse(list2.equals(list3));
	}
	
	/**
	 * Tests hashCode method of SortedList. 
	 * Tests for same and different hashcodes.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("apple");
		list2.add("apple");
		list3.add("banana");
		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
 