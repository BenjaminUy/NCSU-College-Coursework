package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 * @author Benjamin Uy
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Method compares students based on GPA in descending order; if gpa is equal,
	 * this method calls Student's compareTo method as secondary sorting criteria
	 * @param one first Student object that is being compared
	 * @param two second Student object that is being compared
	 * @return integer representation of Student one's position relative to Student two
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getGpa() < two.getGpa()) {
			return 1;
		} else if (one.getGpa() > two.getGpa()) {
			return -1;
		}
		// Call the Student compareTo method the two Student objects as secondary sorting criteria
		return one.compareTo(two);
	}

}
