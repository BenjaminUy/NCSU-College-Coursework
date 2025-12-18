package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests SortedList class
 * 
 * @author Benjamin
 * @author Hank
 * @author Noah
 */
public class SortedListTest {

	/**
	 * Tests SortedList()
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		assertTrue(list.isEmpty());
		
		list.add("snowpea");
		list.add("greenbean");
		list.add("nectarine");
		list.add("potato");
		list.add("sweetpotato");
		list.add("yam");
		list.add("broccoli");
		list.add("tomato");
		list.add("plum");
		list.add("pepper");
		list.add("chili");
		list.add("jalapeno");
		
		assertEquals(12, list.size());
		// Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		
	}

	/**
	 * Tests Add()
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
		
		list.add("azalea");
		assertEquals("azalea", list.get(1));
		assertEquals(3, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(2));
		
		list.add("carrot");
		assertEquals("carrot", list.get(3));
		assertEquals(4, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("azalea", list.get(1));
		assertEquals("banana", list.get(2));

		
		// Test adding a null element
		assertThrows(NullPointerException.class,
				() -> list.add(null));
		
		// Test adding a duplicate element
		assertThrows(IllegalArgumentException.class,
				() -> list.add("banana"));
	}
	
	/**
	 * Test Get()
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		// Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		// Add some elements to the list
		list.add("aardvark");
		list.add("anteater");
		list.add("antelope");
		// Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
		// Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(list.size()));
	}
	
	/**
	 * Tests Remove()
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		// Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		// Add some elements to the list - at least 4
		list.add("baseball");
		list.add("basketball");
		list.add("football");
		list.add("soccer");
		list.add("swimming");

		// Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(-1));
		// Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(list.size()));
		// Test removing a middle element
		assertEquals("football", list.remove(list.size() / 2));
		assertEquals(4, list.size());
		assertEquals("baseball", list.get(0));
		assertEquals("basketball", list.get(1));
		assertEquals("soccer", list.get(2));
		assertEquals("swimming", list.get(3));
		// Test removing the last element
		assertEquals("swimming", list.remove(list.size() - 1));
		assertEquals(3, list.size());
		assertEquals("baseball", list.get(0));
		assertEquals("basketball", list.get(1));
		assertEquals("soccer", list.get(2));
		// Test removing the first element
		assertEquals("baseball", list.remove(0));
		assertEquals(2, list.size());
		assertEquals("basketball", list.get(0));
		assertEquals("soccer", list.get(1));
		// Test removing the last element
		assertEquals("soccer", list.remove(list.size() - 1));
		assertEquals(1, list.size());
	}
	
	/**
	 * Tests IndexOf()
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("Something"));
		
		// Add some elements
		list.add("dog");
		list.add("hamster");
		list.add("parrot");
		
		// Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("dog"));
		assertEquals(1, list.indexOf("hamster"));
		assertEquals(2, list.indexOf("parrot"));
		
		assertEquals(-1, list.indexOf("Dog"));
		assertEquals(-1, list.indexOf(""));
		assertEquals(-1, list.indexOf("cat"));
		
		// Test checking the index of null
		assertThrows(NullPointerException.class,
				() -> list.indexOf(null));
	}
	
	/**
	 * Tests Clear()
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("square");
		list.add("triangle");
		list.add("circle");
		assertEquals(3, list.size());
		
		// Clear the list
		list.clear();
		
		// Test that the list is empty
		assertEquals(0, list.size());
		assertEquals(-1, list.indexOf("square"));
		assertEquals(-1, list.indexOf("triangle"));
		assertEquals(-1, list.indexOf("circle"));
	}

	/**
	 * Tests IsEmpty()
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		// Test that the list starts empty
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		
		// Add at least one element
		list.add("ranch");
		
		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
	}

	/**
	 * Tests Contains()
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		// Test the empty list case
		assertFalse(list.contains("hello"));
		// Add some elements
		list.add("grizzlies");
		list.add("knicks");
		list.add("bucks");
		assertTrue(list.contains("grizzlies"));
		assertTrue(list.contains("knicks"));
		assertTrue(list.contains("bucks"));
		assertFalse(list.contains("nets"));
	}
	
	/**
	 * Test Equals()
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("packers");
		list2.add("packers");
		list3.add("bears");
		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list1));
		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list2));
		
	}
	
	/**
	 * Test HashCode()
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("bruins");
		list2.add("bruins");
		list3.add("hurricanes");
		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list1.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());
	}

}
 