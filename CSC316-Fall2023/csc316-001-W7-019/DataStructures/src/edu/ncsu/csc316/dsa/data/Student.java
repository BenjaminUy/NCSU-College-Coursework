package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Benjamin Uy
 */
public class Student implements Comparable<Student>, Identifiable {

	/** The first name of the Student */
	private String first;
	/** The last name of the Student */
	private String last;
	/** The id number of the Student */
	private int id;
	/** The credit hours Student is taking */
	private int creditHours;
	/** The grade point average of the Student */
	private double gpa;
	/** The unityId of the Student */
	private String unityID;
	
	/**
	 * Method constructs the Student object from the given parameters
	 * @param first first name of the Student
	 * @param last last name of the Student
	 * @param id id number of the Student
	 * @param creditHours credit hours Student is taking
	 * @param gpa grade point average of the Student
	 * @param unityID unity Id of the Student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityID = unityID;
	}
	
	/**
	 * Method returns the first name of the Student
	 * @return the first name of the Student
	 */
	public String getFirst() {
		return first;
	}
	
	/**
	 * Method sets the first name of the Student to the given parameter
	 * @param first first name to set to
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	
	/**
	 * Method returns the last name of the Student
	 * @return the last name of the Student
	 */
	public String getLast() {
		return last;
	}
	
	/**
	 * Method sets the last name of the Student to the given parameter
	 * @param last the last name to set to
	 */
	public void setLast(String last) {
		this.last = last;
	}
	
	/**
	 * Method returns the id number of the Student
	 * @return the id number of the Student
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Method sets the id number of the Student
	 * @param id the id number to set to
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Method returns the credit hours of the Student
	 * @return the credit hours of the Student
	 */
	public int getCreditHours() {
		return creditHours;
	}
	
	/**
	 * Method sets the credit hours of the Student
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	/**
	 * Method returns the gpa of the Student
	 * @return the gpa of the Student
	 */
	public double getGpa() {
		return gpa;
	}
	
	/**
	 * Method sets the gpa of the Student
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	/**
	 * Method returns the unity id of the Student
	 * @return the unityID of the Student
	 */
	public String getUnityID() {
		return unityID;
	}
	
	/**
	 * Method sets the unity id of the Student
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}
	
	/**
	 * Method compares this Student object to the given Student object
	 * If the last name, first name, and id number are equal, this method returns 0
	 * @param o Student object that is being compared to this
	 * @return integer representation of this object's position relative to Student
	 */
	@Override
	public int compareTo(Student o) {
		if (last.compareTo(o.getLast()) < 0) {
			return -1;
		} else if (last.compareTo(o.getLast()) > 0) {
			return 1;
		} else if (first.compareTo(o.getFirst()) < 0) {
			return -1;
		} else if (first.compareTo(o.getFirst()) > 0) {
			return 1;
		} else if (id < o.getId()) {
			return -1;
		} else if (id > o.getId()) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * This method returns the hashCode representation of the Student object
	 * @return integer representing the hashCode of the Student object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}

	/**
	 * Method returns true if the called compareTo method returns 0 and false if not
	 * @param obj object that is being compared to this Student object for equality
	 * @return true if this Student object is equal to the Object in the parameter, false if not
	 */
	public boolean equals(Object obj) {
		Student other = (Student) obj;
		if (compareTo(other) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method returns a String representation of the Student object in the form:
	 * "firstName lastName, id, creditHours, gpa, unityId"
	 * @return String representation of the Student
	 */
	@Override
	public String toString() {
		return first + " " + last + ", " + id + ", " + creditHours + ", " + gpa + ", " + unityID;
	}
}