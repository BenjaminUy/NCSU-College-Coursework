/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * A Course represents an object that is read in from a course records text file; it can be part of a course catalog or
 * a student's schedule.
 * Object class contains information about a Course such as name, title, section, credits, instructor id, meeting days,
 * start time, and end time.
 * Allows the user to update and get the Course name, title, section, credits, instructor id, meeting days,
 * start time, and end time.
 * 
 * @author Benjamin Uy
 */
public class Course extends Activity {

	/**
	 * Constant representing the minimum length of a valid course name
	 */
	private static final int MIN_NAME_LENGTH = 5;
	
	/**
	 * Constant representing the maximum length of a valid course name
	 */
	private static final int MAX_NAME_LENGTH = 8;
	
	/**
	 * Constant representing the minimum number of letters of a valid course name
	 */
	private static final int MIN_LETTER_COUNT = 1;
	
	/**
	 * Constant representing the maximum number of letters of a valid course name
	 */
	private static final int MAX_LETTER_COUNT = 4;
	
	/**
	 * Constant representing the required number of digits of a valid course name
	 */
	private static final int DIGIT_COUNT = 3;
	
	/**
	 * Constant representing the required length of a valid course's section number
	 */
	private static final int SECTION_LENGTH = 3;
	
	/**
	 * Constant representing the minimum number of credit hours a course can offer
	 */
	private static final int MIN_CREDITS = 1;
	
	/**
	 * Constant representing the maximum number of credit hours a course can offer
	 */
	private static final int MAX_CREDITS = 5;
	
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	
	/**
	 * Constructs a Course object with values for all fields
	 * @param name name of the Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId Course instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
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
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged. 
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId Course instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
	    this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}
	
	/**
	 * Returns the Course name
	 * @return name name of the Course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course name; throws an exception if the name is invalid
	 * @param name name of Course
	 * @throws IllegalArgumentException if name is null
	 * @throws IllegalArgumentException if name has a length less than 5 or more than 8
	 * @throws IllegalArgumentException if the characters before the space are not letters
	 * @throws IllegalArgumentException if the characters after the space are not digits
	 * @throws IllegalArgumentException if name has less than 1 or more than 4 letter characters
	 * @throws IllegalArgumentException if name does not have exactly three trailing digit characters
	 */
	private void setName(String name) {
		// Throw exception if the name is null
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		} 
		
		// Throw exception if the name is an empty string
		// Throw exception if the name contains less than 5 characters or greater than 8 characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");	
		}
		
		// Check for pattern of L[LLL] NNN
		int numberOfLetters = 0;
		int numberOfDigits = 0;
		boolean foundSpace = false;
		boolean foundLetter = false;
		
		// for loop going through String name
		for (int i = 0; i < name.length(); i++ ) {
			char letterAtIndex = name.charAt(i);
			if (!foundSpace) {
				if (Character.isLetter(letterAtIndex)) {
					numberOfLetters++;
					foundLetter = true;
				} else if (letterAtIndex == ' ' && foundLetter) {
					foundSpace = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (foundSpace) {
				if (Character.isDigit(letterAtIndex)) {
					numberOfDigits++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}
		
		// Check that the number of letters is correct
		if (numberOfLetters < MIN_LETTER_COUNT || numberOfLetters > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		// Check that the number of digits is correct
		if (numberOfDigits != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		this.name = name;
	}

	/**
	 * Gets the Course section
	 * @return section section of the Course
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course section; throws an exception if section is invalid
	 * @param section section of the Course
	 * @throws IllegalArgumentException if section is null or is not three characters long
	 * @throws IllegalArgumentException if section does not contain only digits
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section."); 
		}
		
		// Checking section string to see if all characters are digits
		for (int i = 0; i < section.length(); i++) {
			char letterAtIndex = section.charAt(i);
			if (!(Character.isDigit(letterAtIndex))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		
		this.section = section;
	}

	/**
	 * Gets the Course credit hours
	 * @return credits Course credit hours
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course credit hours; throws an exception if credits is invalid
	 * @param credits Course credit hours
	 * @throws IllegalArgumentException if credits is less than 1 or greater than 5
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Gets the Course's instructorId
	 * @return instructorId id of the instructor
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructorId; throws an exception if instructorId is invalid
	 * @param instructorId id of the instructor
	 * @throws IllegalArgumentException if instructorId is null or an empty string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * Method overrides because a Course's meeting days string can have an "A" for arranged, meaning that
	 * the Course does not have a start or end time.
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
	 * Method returns a integer representing the hashCode of a Course.
	 * Method overrides to ensure that this method and equals() work at the same time using the same fields.
	 * @return integer representing the hashCode of a Course
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
	 * Method compares a given object to this object for equality on all fields.
	 * Method overrides because for objects to be equal, they must have the same field values.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
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
	 * Method returns a shortened array (length 4) of information on a Course including:
	 * name, section, title, and meeting string.
	 * @return String array of information on a Course including name, section, title, and meeting string
	 */
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		shortDisplay[0] = getName();
		shortDisplay[1] = getSection();
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		return shortDisplay;
	}

	/**
	 * Method returns a long array (length 7) of information on a Course including:
	 * name, section, title, credits, instructor id, meeting string, and empty string.
	 * @return String array of information on a Course including name,
	 * section, title, credits, instructor id, meeting string, and empty string.
	 */
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = getName();
		longDisplay[1] = getSection();
		longDisplay[2] = getTitle();
		longDisplay[3] = "" + getCredits();
		longDisplay[4] = getInstructorId();
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		return longDisplay;
	}
	
	/**
	 * Method sets the meetingDaysAndTime string for Course using meetingDays, startTime, and endTime;
	 * throws an exception if the parameters are invalid
	 * @throws IllegalArgumentException if meetingDays is null or empty
	 * @throws IllegalArgumentException if startTime or endTime is not zero when meetingDays equals "A"
	 * @throws IllegalArgumentException if any of the characters in the meetingDays string are not
	 * 		M, T, W, H, F, or A
	 * @throws IllegalArgumentException if there are duplicate characters in the meetingDays string
	 * @throws IllegalArgumentException if 'A' is not the only character in the meetingDays string, should it be found.
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}  
		
		if ("A".equals(meetingDays) && (startTime != 0 || endTime != 0)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}	
		
		// Keep count of which days are in the meetingDays string
		int mondayCount = 0;
		int tuesdayCount = 0;
		int wednesdayCount = 0;
		int thursdayCount = 0;
		int fridayCount = 0;
		int asynchronousCount = 0;
		
		for (int i = 0; i < meetingDays.length(); i++) {
			char characterAtIndex = meetingDays.charAt(i);
			switch (characterAtIndex) {
				case 'M':
					mondayCount++;
					break;
				case 'T':
					tuesdayCount++;
					break;
				case 'W':
					wednesdayCount++;
					break;
				case 'H':
					thursdayCount++;
					break;
				case 'F':
					fridayCount++;
					break;
				case 'A':
					asynchronousCount++;
					break;
				default: 
					throw new IllegalArgumentException("Invalid meeting days and times.");	
			}		
		}
		
		// Check for duplicates in the count and see, if 'A' was found, it was the only char in the meetingDay string
		if (mondayCount > 1 || tuesdayCount > 1 || wednesdayCount > 1 ||
				thursdayCount > 1 || fridayCount > 1 || asynchronousCount > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} else if (asynchronousCount == 1 && (mondayCount > 0 || tuesdayCount > 0 || wednesdayCount > 0 ||
				thursdayCount > 0 || fridayCount > 0)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Method determines if the parameter is an instance of Course and, if so, compares it
	 * to see if it is a duplicate of another Course object.
	 * @param a an Activity that is being compared to a Course
	 * @return true if the two Courses have the same name
	 */
	public boolean isDuplicate(Activity a) {
		if (a instanceof Course) {
			String course1 = this.getName();
			Course c = (Course) a;
			String course2 = c.getName();
			if (course1.equals(course2)) {
				return true;
			}
		}
		return false;
	}
}
