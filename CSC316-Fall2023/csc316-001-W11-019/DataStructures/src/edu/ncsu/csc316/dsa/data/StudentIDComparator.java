package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 * @author Benjamin Uy
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Method compares students based on id in ascending order; if id is equal
	 * this method returns 0
	 * @param one first Student object that is being compared
	 * @param two second Student object that is being compared
	 * @return integer representation of Student one's position relative to Student two
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getId() < two.getId()) {
			return -1;
		} else if (one.getId() > two.getId()) {
			return 1;
		}
		return 0;
	}

}
