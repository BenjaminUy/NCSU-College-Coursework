package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Super class of Course and Event that contains fields on a courses'/events' 
 * meeting days, title, start, and end times. Aside from set and get behaviors for
 * the fields, the Activity class also provides functionality for returning a short
 * and long version of a display array containing field information. 
 * 
 * @author Benjamin Uy
 */
public abstract class Activity implements Conflict {

	/** Constant representing the upper limit of hours of a day in military time */
	private static final int UPPER_HOUR = 24;
	/** Constant representing the upper limit of minutes of an hour in military time */
	private static final int UPPER_MINUTE = 60;
	/** Title of an Activity */
	private String title;
	/** Meeting days of an Activity */
	private String meetingDays;
	/** Starting time of an Activity */
	private int startTime;
	/** Ending time of an Activity */
	private int endTime;

	/**
	 * Method constructs an activity using the fields: title, meetingDays, startTime, and endTime
	 * @param title title an Activity
	 * @param meetingDays meeting days of an Activity
	 * @param startTime starting time of an Activity
	 * @param endTime ending time of an Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Gets the Activity title
	 * @return title title of an Activity
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity title; throws an exception if the title is invalid
	 * @param title title of the Activity
	 * @throws IllegalArgumentException if the title is null or an empty string
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Gets the Activity's meeting days
	 * @return meetingDays days that Activity meets
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Gets the Activity's start time
	 * @return startTime military time that the Activity starts
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the Activity's end time
	 * @return endTime military time that the Activity ends
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's meeting days and start/end times; throws an exception if meetingDays, startTime, or endTime is invalid
	 * @param meetingDays days of the week that the Activity occurs 
	 * @param startTime military time that the Activity starts
	 * @param endTime military time that the Activity ends
	 * @throws IllegalArgumentException if meetingDays is null or an empty string
	 * @throws IllegalArgumentException if there are non-zero start/end times
	 * @throws IllegalArgumentException if start or end time is an incorrect time, or if end time is less than start time
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}  
		
		// Variables for Activity start and end time
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
	 * Method returns a string containing information about an Activity's meeting times/dates.
	 * The returned string will be in the order of meeting days, start time, and end time
	 * @return string containing information about what time/days an Activity meets
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
	 * Generates a hashCode for Activity using all fields.
	 * Method overrides to ensure that this method and equals() work at the same time using the same fields
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to Activity for equality on all fields.
	 * Method overrides because an Activity only has a title, meetingDays, startTime, and endTime fields
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
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
	 * Abstract method that returns a string array containing shortened information about an
	 * Event or Course.
	 * @return short version of a string array that provides information to the GUI
	 * 		about an Event or Course
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Abstract method that returns a string array containing full information about an
	 * Event or Course
	 * @return full version of a string array provides information to the GUI
	 * 		about an Event or Course
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Abstract method that returns true if the Activity being compared is a duplicate
	 * @param a Activity object
	 * @return true if the Activity being compared is a duplicate
	 */
	public abstract boolean isDuplicate(Activity a);

	/**
	 * Method that compares this object with the parameter Activity to see if there is any conflict
	 * in their times. Throws an exception if there is a conflict. Two Activity objects are in conflict
	 * if there is at least one day with an overlapping time; time overlaps, even if the minute is the same.
	 * @param possibleConflictingActivity Activity object that could cause a ConflictException
	 * @throws ConflictException if the start time of the given Activity is (1) greater than or equal to this startTime 
	 * 		and (2) less than or equal to this endTime
	 * @throws ConflictException if the end time of the given Activity is (1) greater than or equal to this startTime
	 * 		and (2) less than or equal to this endTime
	 * @throws ConflictException if the start time of the given activity is (1) less than or equal to this startTime 
	 * 		and (2) the end time of the given Activity is greater than or equal to this endTime.	
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String meetingDaysOther = possibleConflictingActivity.getMeetingDays();
		int startTimeOther = possibleConflictingActivity.getStartTime();
		int endTimeOther = possibleConflictingActivity.getEndTime(); 
		
		// Check the meetingDays strings to see if they are arranged
		if (!("A".equals(meetingDaysOther)) && !("A".equals(meetingDays))) {
			// Loop through meetingDays string of this and compare its characters to the other Activity
			for (int i = 0; i < meetingDays.length(); i++) {
				char meetingCharThis = meetingDays.charAt(i);
				if (meetingDaysOther.indexOf(meetingCharThis) >= 0) {
					if (startTimeOther >= startTime && startTimeOther <= endTime) {
						throw new ConflictException("Schedule conflict.");
					} else if (endTimeOther >= startTime && endTimeOther <= endTime) {
						throw new ConflictException("Schedule conflict.");					
					} else if (startTimeOther <= startTime && endTimeOther >= endTime) {
						throw new ConflictException("Schedule conflict.");
					}
				}
			}
		}
		
	}

}