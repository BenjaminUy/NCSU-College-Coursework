package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.FirstElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.LastElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.MiddleElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.RandomElementSelector;

/**
 * This class tests the QuickSorter class
 * @author Benjamin Uy
 */
public class QuickSorterTest {
	
	private Student sOne;
	private Student sTwo;
	private Student sThree;
	private Student sFour;
	private Student sFive;
	
	private QuickSorter<Student> sorter;

	/**
	 * This constructs the Student and QuickSorter objects used in testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}
	
	/**
	 * Method tests sort using the natural ordering of students and the MiddleElementSelector
	 */
	@Test
	public void testSortMiddleSelector() {
		sorter = new QuickSorter<Student>(new MiddleElementSelector());
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sThree, original[3]);
		assertEquals(sTwo, original[4]);
	}
	
	/**
	 * Method tests sort using the natural ordering of students and the MiddleElementSelector
	 * for a reversely-sorted array
	 */
	@Test
	public void testSortMiddleSelectorReverse() {
		sorter = new QuickSorter<Student>(new MiddleElementSelector());
		Student[] original = { sTwo, sThree, sOne, sFour, sFive };
		sorter.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sThree, original[3]);
		assertEquals(sTwo, original[4]);
	}
	
	/**
	 * Method tests sort using the natural ordering of students and the FirstElementSelector
	 */
	@Test
	public void testSortFirstSelector() {
		sorter = new QuickSorter<Student>(new FirstElementSelector());
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sThree, original[3]);
		assertEquals(sTwo, original[4]);
	}

	/**
	 * Method tests sort using the natural ordering of students and the LastElementSelector
	 */
	@Test
	public void testSortLastSelector() {
		sorter = new QuickSorter<Student>(new LastElementSelector());
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sThree, original[3]);
		assertEquals(sTwo, original[4]);
	}

	/**
	 * Method tests sort using the natural ordering of students and the RandomElementSelector
	 */
	@Test
	public void testSortRandomSelector() {
		sorter = new QuickSorter<Student>(new RandomElementSelector());
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sFive, original[0]);
		assertEquals(sFour, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sThree, original[3]);
		assertEquals(sTwo, original[4]);
		
		sorter = new QuickSorter<Student>();
		Student[] original2 = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original2);
		assertEquals(sFive, original2[0]);
		assertEquals(sFour, original2[1]);
		assertEquals(sOne, original2[2]);
		assertEquals(sThree, original2[3]);
		assertEquals(sTwo, original2[4]);
	}
	
	/**
	 * Method tests sort using the Student ID Comparator
	 */
	@Test
	public void testSortIDComparator() {
		sorter = new QuickSorter<Student>(new StudentIDComparator());
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

}
