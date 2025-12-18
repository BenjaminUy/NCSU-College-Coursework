package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * This class tests the StudentReader class
 * @author Benjamin Uy
 */
public class StudentReaderTest {
	
	/**
	 * Method tests StudentReader readInputAsArray with a file of student info in ascending order
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/**
	 * Method tests StudentReader readInputAsArray with a file of student info lines in random order
	 */
	@Test
	public void testReadFile2() {
		Student[] contents = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		assertEquals("Lacie", contents[0].getFirst());
		assertEquals("Tyree", contents[1].getFirst());
		assertEquals("Loise", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Shanti", contents[4].getFirst());
		assertEquals("Roxann", contents[5].getFirst());
		assertEquals("Evelin", contents[6].getFirst());
		assertEquals("Alicia", contents[7].getFirst());
		assertEquals("Charlene", contents[8].getFirst());
		assertEquals("Nichole", contents[9].getFirst());
		assertEquals("Ara", contents[10].getFirst());
		assertEquals("Dante", contents[11].getFirst());
		assertEquals("Tanner", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Amber", contents[14].getFirst());
		assertEquals("Lewis", contents[15].getFirst());
	}
}
