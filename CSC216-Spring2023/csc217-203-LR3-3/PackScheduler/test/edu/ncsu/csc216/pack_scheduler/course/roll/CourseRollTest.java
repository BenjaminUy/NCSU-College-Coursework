/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Class tests CourseRoll.java
 * @author hctynch
 * @author bsuy
 * @author ajwdr
 */
class CourseRollTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	
	/**
	 * Test method for CourseRoll constructor.
	 */
	@Test
	void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(10, cr.getEnrollmentCap());
		assertThrows(IllegalArgumentException.class, () -> cr.setEnrollmentCap(251));
		assertThrows(IllegalArgumentException.class, () -> cr.setEnrollmentCap(9));
	}

	/**
	 * Test method for CourseRoll.getOpenSeats(), Enroll(), and canEnroll().
	 */
	@Test
	void testGetOpenSeatsEnrollandCanEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		cr.setEnrollmentCap(10);
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validTestFile);
		Student zking = sd.getStudentById("zking");
		Student gstone = sd.getStudentById("gstone");
		assertTrue(cr.canEnroll(zking));
		assertTrue(cr.canEnroll(gstone));
		cr.enroll(zking);
		assertFalse(cr.canEnroll(zking));
		assertEquals(9, cr.getOpenSeats());
		cr.enroll(gstone);
		assertEquals(8, cr.getOpenSeats());
		cr.setEnrollmentCap(10);
		cr.enroll(sd.getStudentById("cschwartz"));
		cr.enroll(sd.getStudentById("shansen"));
		cr.enroll(sd.getStudentById("daustin"));
		cr.enroll(sd.getStudentById("rbrennan"));
		cr.enroll(sd.getStudentById("efrost"));
		cr.enroll(sd.getStudentById("lberg"));
		cr.enroll(sd.getStudentById("ahicks"));
		cr.enroll(sd.getStudentById("dnolan"));
		Student stu = new Student("stu", "dent", "sdent", "sdent@ncsu.edu", "pw", 18);
		assertTrue(cr.canEnroll(stu));
		assertThrows(IllegalArgumentException.class, () -> cr.enroll(gstone));
		assertThrows(IllegalArgumentException.class, () -> cr.enroll(null));		
	}

	/**
	 * Test method for CourseRoll.getEnrollmentCap().
	 */
	@Test
	void testGetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(10, cr.getEnrollmentCap());
	}

	/**
	 * Test method for CourseRoll.setEnrollmentCap().
	 */
	@Test
	void testSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		cr.setEnrollmentCap(250);
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validTestFile);
		Student zking = sd.getStudentById("zking");
		Student gstone = sd.getStudentById("gstone");
		cr.enroll(zking);
		cr.enroll(gstone);
		cr.enroll(sd.getStudentById("cschwartz"));
		cr.enroll(sd.getStudentById("shansen"));
		cr.enroll(sd.getStudentById("daustin"));
		cr.enroll(sd.getStudentById("rbrennan"));
		cr.enroll(sd.getStudentById("efrost"));
		cr.enroll(sd.getStudentById("lberg"));
		cr.enroll(sd.getStudentById("ahicks"));
		cr.enroll(sd.getStudentById("dnolan"));
		Student stu = new Student("stu", "dent", "sdent", "sdent@ncsu.edu", "pw", 18);
		cr.enroll(stu);
		Student stu2 = new Student("stu2", "dent", "sdent", "sdent@ncsu.edu", "pw", 18);
		cr.enroll(stu2);
		assertThrows(IllegalArgumentException.class, () -> cr.setEnrollmentCap(11));
	}


	/**
	 * Test method for CourseRoll.drop().
	 */
	@Test
	void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		cr.setEnrollmentCap(10);
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validTestFile);
		cr.enroll(sd.getStudentById("zking"));
		cr.enroll(sd.getStudentById("gstone"));
		cr.enroll(sd.getStudentById("cschwartz"));
		cr.enroll(sd.getStudentById("shansen"));
		cr.enroll(sd.getStudentById("daustin"));
		cr.enroll(sd.getStudentById("rbrennan"));
		cr.enroll(sd.getStudentById("efrost"));
		cr.enroll(sd.getStudentById("lberg"));
		cr.enroll(sd.getStudentById("ahicks"));
		cr.enroll(sd.getStudentById("dnolan"));
		Student stu = new Student("stu", "dent", "sdent", "sdent@ncsu.edu", "pw", 18);
		cr.enroll(stu);
		Student stu2 = new Student("stu2", "dent", "sdent", "sdent@ncsu.edu", "pw", 18);
		cr.enroll(stu2);
		cr.drop(stu2);
		assertEquals(0, cr.getOpenSeats());
		assertThrows(IllegalArgumentException.class, () -> cr.drop(null));
	}
	
	/**
	 * Tests waitList() functionality in CourseRoll.
	 */
	@Test
	void testWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		cr.setEnrollmentCap(10);
		assertEquals(0, cr.getNumberOnWaitlist());
	}

}