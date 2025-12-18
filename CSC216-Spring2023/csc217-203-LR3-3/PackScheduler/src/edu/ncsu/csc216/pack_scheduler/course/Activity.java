package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;
/**
 * Activity abstract class for use in the Course and Event subclasses.
 * @author Hunt Tynch
 * @author Andrew Warren
 * @author Benjamin Uy
 */
public abstract class Activity implements Conflict {
	/** Highest value for hour */
	private static final int UPPER_HOUR = 24;
	/** Highest value for minute */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	
	/**
	 * Checks if two Activity objects have at least one day with an overlapping time.
	 * @param possibleConflictingActivity The activity to check for conflict against
	 * @throws ConflictException if the two activities have an overlapping time on the same day.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String otherMeetingDays = possibleConflictingActivity.getMeetingDays();
		String thisMeetingDays = this.getMeetingDays();
		
		// Arranged activities have no start/end times and thus cannot overlap.
		if("A".equals(thisMeetingDays) || "A".equals(otherMeetingDays)) {
			return;
		}
		
		for(int i = 0; i < thisMeetingDays.length(); i++) {
			for(int j = 0; j < otherMeetingDays.length(); j++) {
				//if there is an overlap in days
				boolean matchingDay = thisMeetingDays.charAt(i) == otherMeetingDays.charAt(j);
				// overlap case 1: this end time >= other start time
				boolean case1 = this.getStartTime() <= possibleConflictingActivity.getEndTime() && this.getEndTime() >= possibleConflictingActivity.getEndTime();
				// overlap case 2: this start time <= other end time
				boolean case2 = this.getEndTime() >= possibleConflictingActivity.getStartTime() && this.getStartTime() <= possibleConflictingActivity.getStartTime();
				//overlap case 3&4: one event within another
				boolean case3 = this.getStartTime() >= possibleConflictingActivity.getStartTime() && this.getEndTime() <= possibleConflictingActivity.getEndTime();
				boolean case4 = this.getStartTime() <= possibleConflictingActivity.getStartTime() && this.getEndTime() >= possibleConflictingActivity.getEndTime();
				if(matchingDay && (case1 || case2 || case3 || case4)) {
					throw new ConflictException();
				}
			}
		}
	}
	
	/**
	 * Short array for use in displaying Activity information. 
	 * Abstract method.
	 * @return returns length 4 array for display
	 */
	public abstract String[] getShortDisplayArray();
	/** 
	 * Long array for use in displaying Activity information.
	 * Abstract method.
	 * @return returns length 7 array for display
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Checks if the given activity is a duplicate
	 * @param activity the activity to check against
	 * @return true or false is the activity a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Constructor for abstract Activity class
	 * @param title title of the activity
	 * @param meetingDays meeting days of the activity
	 * @param startTime start time of the activity
	 * @param endTime end time of the activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Generates a hash code using activity's fields
	 */
	@Override
	public int hashCode() {
		return Objects.hash(endTime, meetingDays, startTime, title);
	}
	
	/**
	 * Checks if two Activity objects are the same using their fields.
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
		return endTime == other.endTime && Objects.equals(meetingDays, other.meetingDays)
				&& startTime == other.startTime && Objects.equals(title, other.title);
	}

	/**
	 * Returns the activity title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the activity title
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null or length 0
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the activity meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the activity starting time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the activity end time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the meeting days and time for Activity
	 * @param meetingDays days the activity meets
	 * @param startTime   activity starting time
	 * @param endTime     activity ending time
	 * @throws IllegalArgumentException if the meeting days or times are invalid such as if
	 * 		meetingDays is null or an empty string,
	 * 		startHour/endHour is greater than or equal to 24 or is negative,
	 * 		startMin/endMin is greater than or equal to 60 or is negative,
	 * 		or startTime is greater than endTime 
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;
		if (startHour >= UPPER_HOUR || startHour < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endHour >= UPPER_HOUR || endHour < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (startMin >= UPPER_MINUTE || startMin < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endMin >= UPPER_MINUTE || endMin < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns formatted meeting information string Or "Arranged" if meeting info is arranged
	 * @return formatted meeting information string
	 */
	public String getMeetingString() {
		if ("A".equals(this.meetingDays)) {
			return "Arranged";
		}
	
		int startHour = this.startTime / 100;
		int startMin = this.startTime % 100;
		boolean startPM = false;
	
		int endHour = this.endTime / 100;
		int endMin = this.endTime % 100;
		boolean endPM = false;
	
		if (startHour > 12) { // hours over 12 is PM
			startPM = true;
			startHour -= 12;
		} else if (startHour == 0) { // 0000 is 12AM
			startHour = 12;
		} else if (startHour == 12) { // 1200 is 12PM(noon)
			startPM = true;
		}
	
		if (endHour > 12) { // same for end time
			endPM = true;
			endHour -= 12;
		} else if (endHour == 0) {
			endHour = 12;
		} else if (endHour == 12) {
			endPM = true;
		}
	
		String info = ""; // meeting info is in format ex: "MW 10:35AM-12:40PM"
		info += this.meetingDays;
		info += " ";
		info += startHour;
		info += ":";
		if (startMin < 10) {
			info = info + "0" + startMin;
		} else {
			info += startMin;
		}
		if (startPM) {
			info += "PM";
		} else {
			info += "AM";
		}
		info += "-";
		info += endHour;
		info += ":";
		if (endMin < 10) {
			info = info + "0" + endMin;
		} else {
			info += endMin;
		}
		if (endPM) {
			info += "PM";
		} else {
			info += "AM";
		}
		return info;
	}

}