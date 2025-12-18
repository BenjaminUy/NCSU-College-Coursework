/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		CourseRoll cr = new CourseRoll(150);
		assertEquals(150, cr.getEnrollmentCap());
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(251));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(9));
	}

	/**
	 * Test method for CourseRoll.getOpenSeats(), Enroll(), and canEnroll().
	 */
	@Test
	void testGetOpenSeatsEnrollandCanEnroll() {
		CourseRoll cr = new CourseRoll(150);
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validTestFile);
		Student zking = sd.getStudentById("zking");
		Student gstone = sd.getStudentById("gstone");
		assertTrue(cr.canEnroll(zking));
		assertTrue(cr.canEnroll(gstone));
		cr.enroll(zking);
		assertFalse(cr.canEnroll(zking));
		assertEquals(149, cr.getOpenSeats());
		cr.enroll(gstone);
		assertEquals(148, cr.getOpenSeats());
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
		assertThrows(IllegalArgumentException.class, () -> cr.enroll(gstone));
		assertThrows(IllegalArgumentException.class, () -> cr.enroll(null));
		assertThrows(IllegalArgumentException.class, () -> cr.enroll(stu));
		
	}

	/**
	 * Test method for CourseRoll.getEnrollmentCap().
	 */
	@Test
	void testGetEnrollmentCap() {
		CourseRoll cr = new CourseRoll(150);
		assertEquals(150, cr.getEnrollmentCap());
	}

	/**
	 * Test method for CourseRoll.setEnrollmentCap().
	 */
	@Test
	void testSetEnrollmentCap() {
		CourseRoll cr = new CourseRoll(25);
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
		CourseRoll cr = new CourseRoll(25);
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
		
		cr.drop(stu2);
		assertEquals(14, cr.getOpenSeats());
		assertThrows(IllegalArgumentException.class, () -> cr.drop(null));
	}

}