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
public class Course {

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
	
	/**
	 * Constant representing the upper limit of hours of a day in military time
	 */
	private static final int UPPER_HOUR = 24;
	
	/**
	 * Constant representing the upper limit of minutes of an hour in military time
	 */
	private static final int UPPER_MINUTE = 60;
	
	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	/**
	 * Constructs a Course object with values for all fields
	 * @param name name of the Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		setName(name);
		setTitle(title);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
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
	 * @throws IllegalArgumentException if name does not contain a space between letter characters and number characters
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
	 * Gets the Course title
	 * @return title title of the Course
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course title; throws an exception if the title is invalid
	 * @param title title of the Course
	 * @throws IllegalArgumentException if the title is null or an empty string
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
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
	 * Gets the Course's meeting days
	 * @return meetingDays days that Course has class
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Gets the Course's start time
	 * @return startTime military time that class starts
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the Course's end time
	 * @return endTime military time that class ends
	 */
	public int getEndTime() {
		return endTime;
	}
	
	/**
	 * Sets the Course's meeting days and start/end times; throws an exception if meetingDays, startTime, or endTime is invalid
	 * @param meetingDays days of the week that the Course meets 
	 * @param startTime military time that the Course starts
	 * @param endTime military time that the Course ends
	 * @throws IllegalArgumentException if meetingDays is null or an empty string
	 * @throws IllegalArgumentException if an arranged class has non-zero start/end times
	 * @throws IllegalArgumentException if meetingDays string contains duplicates or invalid characters
	 * @throws IllegalArgumentException if start or end time is an incorrect time, or if end time is less than start time
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}  
		
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;		
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
		
		// Variables for Course start and end time
		int startHour = 0;
		int startMinute = 0;
		int endHour = 0;
		int endMinute = 0;
		
		startHour = startTime / 100;
		startMinute = startTime % 100;
		endHour = endTime / 100;
		endMinute = endTime % 100;
		
		// Check for invalid start and end times
		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} else if (startMinute < 0 || startMinute >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} else if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} else if (endMinute < 0 || endMinute >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		} else if (startHour > endHour) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Method returns a string containing information about a Course's meeting times/dates.
	 * The returned string will usually be in the order of meeting days, start time, and end time
	 * @return string containing information about what time/days a Course meets
	 */
	public String getMeetingString() {
		int startTimeHourStandard = startTime / 100;
		int startTimeMinStandard = startTime % 100;
		int endTimeHourStandard = endTime / 100;
		int endTimeMinStandard = endTime % 100;
		
		boolean pastTwelve = false;
		
		// Check to see if the startTimeHour is at least 12 (PM time)
		if (startTimeHourStandard >= 12) {
			pastTwelve = true;
			if (startTimeHourStandard > 12) {
				startTimeHourStandard -= 12;
			}
		}
		
		String meetingInfo = meetingDays + " " + startTimeHourStandard + ":";
		
		if ("A".equals(meetingDays)) {
			return "Arranged";
		}
		
		// Determine digits of the start time minutes
		if (startTimeMinStandard >= 10) {
			meetingInfo += startTimeMinStandard;
		} else if (startTimeMinStandard < 10 && startTimeMinStandard > 0) {
			meetingInfo += "0" + startTimeMinStandard;
		} else {
			meetingInfo += "00";
		}
		
		if (!pastTwelve) {
			meetingInfo += "AM";
		} else {
			meetingInfo += "PM";
		}
		
		// Reset pastTwelve for endTime
		pastTwelve = false;
		
		if (endTimeHourStandard >= 12) {
			pastTwelve = true;
			if (endTimeHourStandard > 12) {
				endTimeHourStandard -= 12;
			}
		}
		
		meetingInfo += "-" + endTimeHourStandard + ":";
		
		// Determine digits of the end time minutes
		if (endTimeMinStandard >= 10) {
			meetingInfo += "" + endTimeMinStandard;
		} else if (endTimeMinStandard < 10 && endTimeMinStandard > 0) {
			meetingInfo += "0" + endTimeMinStandard;
		} else {
			meetingInfo += "00";
		}
		
		if (!pastTwelve) {
			meetingInfo += "AM";
		} else {
			meetingInfo += "PM";
		}
		
		return meetingInfo;
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the Object to compare
	 * @return true is the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
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
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(meetingDays)) {
	        return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
	    }
	    return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + "," + startTime + "," + endTime; 
	}
}
