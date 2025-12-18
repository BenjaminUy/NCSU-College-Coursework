package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * This class Constructs and provides a way to interact with Course Objects.
 * Fields include name, title, section, credits, instructorId, meetingDays,
 * startTime, and endTime. Course objects can be constructed without a start and
 * end time. toString(), equals(), and hashCode() methods are modified.
 * 
 * @author Connor Hekking
 * @author Benjamin Uy
 * @author Hunt Tynch
 * @author Andrew Warren
 */
public class Course extends Activity implements Comparable<Course> {
	/** Minimum length for course name. */
	private static final int MIN_NAME_LENGTH = 4;
	/** Maximum length for course name. */
	private static final int MAX_NAME_LENGTH = 8;
	/** Length of section number */
	private static final int SECTION_LENGTH = 3;
	/** Maximum credit value for course */
	private static final int MAX_CREDITS = 5;
	/** Minimum credit value for course */
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Validator for course names. */
	private CourseNameValidator validator;
	/** Object keeping track of a Course's enrollment limits */
	private CourseRoll roll;
	
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap enrollment capacity for CourseRoll
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		validator = new CourseNameValidator();
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}
	
	/**
	 * Generates a hashCode using Course's fields
	 * @return integer representing a hashCode of Course's fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}
	
	/**
	 * Checks if the given activity is a duplicate (determined by if names are equal)
	 * @param activity The activity to be checked
	 * @return true if activity is a duplicate course, false if not
	 */
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course c = (Course) activity;
			if(this.getName().equals(c.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sets the meeting days and time for Course
	 * @param meetingDays days the course meets
	 * @param startTime   course starting time
	 * @param endTime     course ending time
	 * @throws IllegalArgumentException if the meeting days or time is invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays.contains("A") && meetingDays.length() > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		} else {
			int numM = 0;
			int numT = 0;
			int numW = 0;
			int numH = 0;
			int numF = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					numM += 1;
				} else if (meetingDays.charAt(i) == 'T') {
					numT += 1;
				} else if (meetingDays.charAt(i) == 'W') {
					numW += 1;
				} else if (meetingDays.charAt(i) == 'H') {
					numH += 1;
				} else if (meetingDays.charAt(i) == 'F') {
					numF += 1;
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			if (numM > 1 || numT > 1 || numW > 1 || numH > 1 || numF > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	
		}
		
	}

	/**
	 * Checks if two courses are equal using Course's fields
	 * @return true if the two Course objects are equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
	}

	/**
	 * Constructs a Course object with all fields but startTime and endTime
	 * @param name         name of course
	 * @param title        title of course
	 * @param section      section of course
	 * @param credits      credit hours of course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap enrollment capacity for the CourseRoll
	 * @param meetingDays  meeting days for course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the course's name
	 * @return the course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the course's name Checks: name is not null name is between the max and
	 * min length name has correct number of letters and digits name has a space
	 * between letters and digits
	 * @param name the name to set
	 * @throws IllegalArgumentException if the course name is invalid
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		try {
			if (!(validator.isValid(name)))
				throw new IllegalArgumentException("Invalid course name.");
		} catch (InvalidTransitionException ite) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		this.name = name;
	}

	/**
	 * Returns the course section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the course section
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section is invalid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < 3; i++) {
			if (!Character.isDigit(section.charAt(i))) { // NOTE the !inverse
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}

	/**
	 * Returns the course credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the course credits
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the credits are not in the correct range
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the course instructor's ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the course instructor's ID
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructorId is invalid
	 */
	public void setInstructorId(String instructorId) {
		if ("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}
	
	/**
	 * Returns a short version of an array 
	 * containing Course's name, section, title, meeting String, and open seats
	 * @return length 5 array for display which contains Course name, section, title, meeting string, and open seats
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortArr = new String[5];
		shortArr[0] = name;
		shortArr[1] = section;
		shortArr[2] = getTitle();
		shortArr[3] = getMeetingString();
		shortArr[4] = "" + roll.getOpenSeats();
		return shortArr;
		
	}
	
	/**
	 * Returns a long version of an array 
	 * containing Course's fields to display
	 * @return length 7 array for display which contains Course name, section, title, credits,
	 * 		instructor id, meeting string, and empty string (used for events) 
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longArr = new String[7];
		longArr[0] = name;
		longArr[1] = section;
		longArr[2] = getTitle();
		longArr[3] = Integer.toString(credits);
		longArr[4] = instructorId;
		longArr[5] = getMeetingString();
		longArr[6] = ""; // for event field
		return longArr;
	}
	
	/**
	 * Overrides Comparable.compareTo() to check a Course's name and section against another Course.
	 * 
	 * @param o A Course Object
	 * @return 0 if the objects are the same, 1 if this object is greater than the parameter, and -1 if this object is less than the parameter.
	 */
	@Override
	public int compareTo(Course o) {
		if (name.compareTo(o.getName()) != 0) {
			return name.compareTo(o.getName());
		} else if (section.compareTo(o.getSection()) != 0){
			return section.compareTo(o.getSection());
		}
		return 0;
	}
	
	/**
	 * Method returns the Course's roll field
	 * @return CourseRoll object of the Course 
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

} 