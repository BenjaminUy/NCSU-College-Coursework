package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The Course class represents a course at NC State and associated fields.
 * It contains fields for the name, section, amount of credits, and instructor ID of the course.
 * It contains methods for getting and setting each of these fields for a course, a method to find
 * if two courses are duplicates, and methods for returning an array displaying course information.
 * It has overridden toString(), hashCode(), and equals() methods that handle courses.
 * @author Noah Anderson
 * @author Benjamin Uy
 * @author Hank Lenham
 */
public class Course extends Activity implements Comparable<Course> {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's minimum name length of 5 */
	private static final int MIN_NAME_LENGTH = 5;
	/** Course's maximum name length of 8 */
	private static final int MAX_NAME_LENGTH = 8;
	/** Course name's minimum letter count of 1 */
	private static final int MIN_LETTER_COUNT = 1;
	/** Course name's maximum letter count of 4 */
	private static final int MAX_LETTER_COUNT = 4;
	/** Course name's digit count of 3 */
	private static final int DIGIT_COUNT = 3;
	/** Course section's length of 3 */
	private static final int SECTION_LENGTH = 3;
	/** Course's maximum credits of 5 */
	private static final int MAX_CREDITS = 5;
	/** Course's minimum credits of 1 */
	private static final int MIN_CREDITS = 1;
	/**
	 * Creates a course with values for all fields
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours of Course
	 * @param instructorId instructor's Unity ID
	 * @param meetingDays  meeting days for class as a series of chars
	 * @param startTime    starting time of Course
	 * @param endTime      ending time of Course
	 */
    public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
            int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
    }

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours of Course
	 * @param instructorId instructor's Unity ID
	 * @param meetingDays  meeting days for class as a series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the name of the Course
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the section of the Course
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Returns the credit hours of the Course
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Returns the instructor's unity ID for the Course
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		// Throw exception if the name is null
		if (name == null)
			throw new IllegalArgumentException("Invalid course name.");

		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 character or greater than 8
		// characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
			throw new IllegalArgumentException("Invalid course name.");

		// Check for pattern of L[LLL] NNN
		int letterCounter = 0; // counts # of letters in name
		int digitCounter = 0; // counts # of digits in name
		boolean spaceLoc = false; // is true if a space has been found in the name
		for (int i = 0; i < name.length(); i++) {
			if (!spaceLoc) {
				if (Character.isLetter(name.charAt(i)))
					letterCounter++;
				else if (name.charAt(i) == ' ')
					spaceLoc = true;
				else
					throw new IllegalArgumentException("Invalid course name.");
			} else if (spaceLoc) {
				if (Character.isDigit(name.charAt(i)))
					digitCounter++;
				else
					throw new IllegalArgumentException("Invalid course name.");
			}
		}

		// Check that the number of letters is correct
		if (letterCounter < MIN_LETTER_COUNT || letterCounter > MAX_LETTER_COUNT)
			throw new IllegalArgumentException("Invalid course name.");

		// Check that the number of digits is correct
		if (digitCounter != DIGIT_COUNT)
			throw new IllegalArgumentException("Invalid course name.");

		this.name = name;
	}

	/**
	 * Sets the section of the Course. If the section is null, not 3 digits long, or
	 * contains a letter, an IllegalArgumentException is thrown.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		for (int i = 0; i < SECTION_LENGTH; i++) {
			if (Character.isLetter(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Sets the credit hours of the Course. If the credit hours aren't in the
	 * allowed range, from 1 to 5 inclusive, an IllegalArgumentException is thrown.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the credits parameter is invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Sets the instructor's unity ID for the Course. If the instructor ID is null
	 * or empty, an IllegalArgumentException is thrown.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the instructorId parameter is invalid
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Sets the course's meeting days and time.
	 * This overridden method checks to make sure that an arranged course has no
	 * start/end times or additional meeting days listed, and throws an IllegalArgumentException if 
	 * either of those occur.
	 * An IllegalArgumentException is thrown if a day besides Monday-Friday or Arranged is listed.
	 * @throws IllegalArgumentException if meetingDays, startTime, endTime are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		String allowedDays = "MTWHFA"; // The five weekdays and an arranged option
		for (int i = 0; i < meetingDays.length(); i++) {
			char day = meetingDays.charAt(i);
			if (allowedDays.indexOf(day) < 0) // If meeting day is not in week/A
				throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if ("A".equals(meetingDays)) { // arranged
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		super.setMeetingDaysAndTime(meetingDays, 0, 0);
		}
		// If A is not the entire meetingDays String, but is in the string
		// This means there is another day besides A, which shouldn't happen
		else if (meetingDays.indexOf("A") >= 0)
				throw new IllegalArgumentException("Invalid meeting days and times.");
		else
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * Arranged courses (A for the meeting days) do not have starting or ending times returned.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {

		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Returns a unique hash code for a course.
	 * @return result the result of the hash for the course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Finds if a course is equal to another passed course in all fields.
	 * @param obj course to compare to
	 * @return boolean true if courses are equal
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
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a short String array containing the name, section, title, and meeting days and time
	 * of the course.
	 * @return arr the String array containing relevant fields
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] arr = new String[4];
		arr[0] = getName(); 
		arr[1] = getSection();
		arr[2] = getTitle();
		arr[3] = getMeetingString();
		return arr;
	}

	/**
	 * Returns a long String array containing the name, section, title, credits, instructor ID,
	 * meeting days and time, and an additional empty string that is used by events but not courses.
	 * @return arr the String array containing relevant fields
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] arr = new String[7];
		arr[0] = getName();
		arr[1] = getSection();
		arr[2] = getTitle();
		arr[3] = String.valueOf(getCredits());
		arr[4] = getInstructorId();
		arr[5] = getMeetingString();
		arr[6] = "";
		return arr;
	}

	/**
	 * Finds if two courses are duplicates of each other. If the two objects are both courses 
	 * with the same name, they are considered duplicates.
	 * @param activity the activity to compare to
	 * @return boolean true if courses are duplicates
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course other = (Course) activity;
			return this.getName().equals(other.getName());
		}
		else
			return false;
	}

	@Override
	public int compareTo(Course o) {
		String otherName = o.getName();
		String otherSection = o.getSection();
		
		if (!this.name.equals(otherName)) {
			if (this.name.compareToIgnoreCase(otherName) > 0) {
				return 1;
			} else {
				return -1;
			}
		} else if (!this.section.equals(otherSection)) {
			if (this.section.compareToIgnoreCase(otherSection) > 0) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}
}
