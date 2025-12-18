package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * This file tests the InsertionSorter class
 * @author Benjamin Uy
 * @param <E> the generic type of data to sort
 */
public class InsertionSorterTest<E extends Comparable<E>> {

	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	
	private Student student1 = new Student("first", "alex", 1, 15, 3.0, "flast");
	private Student student2 = new Student("second", "barley", 2, 16, 3.5, "sbarle");
	private Student student3 = new Student("third", "charles", 3, 17, 3.7, "tcharl");
	
	private Student[] studentAscending = { student1, student2, student3 };
	private Student[] studentDescending = { student3, student2, student1 };
	private Student[] studentRandom = {student1, student3, student2};
	
	private InsertionSorter<E> integerSorter;

	/**
	 * This constructs a new InsertionSorter for every test
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<E>();
	}
	
	/**
	 * Tests the sort method of InsertionSorter using arrays of integers
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSortIntegers() {
		integerSorter.sort((E[]) dataAscending);
		assertEquals(1, (int) dataAscending[0]);
		assertEquals(2, (int) dataAscending[1]);
		assertEquals(3, (int) dataAscending[2]);
		assertEquals(4, (int) dataAscending[3]);
		assertEquals(5, (int) dataAscending[4]);

		integerSorter.sort((E[]) dataDescending);
		assertEquals(1, (int) dataDescending[0]);
		assertEquals(2, (int)dataDescending[1]);
		assertEquals(3, (int) dataDescending[2]);
		assertEquals(4, (int) dataDescending[3]);
		assertEquals(5, (int) dataDescending[4]);

		integerSorter.sort((E[]) dataRandom);
		assertEquals(1, (int) dataRandom[0]);
		assertEquals(2, (int) dataRandom[1]);
		assertEquals(3, (int) dataRandom[2]);
		assertEquals(4, (int) dataRandom[3]);
		assertEquals(5, (int) dataRandom[4]);
	}

	/**
	 * Tests the sort method of InsertionSorter by using arrays of Students
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testSortStudent() {
		integerSorter.sort((E[]) studentAscending);
		assertEquals(student1, studentAscending[0]);
		assertEquals(student2, studentAscending[1]);
		assertEquals(student3, studentAscending[2]);
		
		integerSorter.sort((E[]) studentDescending);
		assertEquals(student1, studentAscending[0]);
		assertEquals(student2, studentAscending[1]);
		assertEquals(student3, studentAscending[2]);
		
		integerSorter.sort((E[]) studentRandom);
		assertEquals(student1, studentAscending[0]);
		assertEquals(student2, studentAscending[1]);
		assertEquals(student3, studentAscending[2]);
		
	}
}
