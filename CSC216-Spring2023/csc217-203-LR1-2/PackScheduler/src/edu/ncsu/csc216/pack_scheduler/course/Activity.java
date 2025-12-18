package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The Activity class represents an activity on a schedule and associated fields.
 * It contains fields for the title of the activity, the meeting days, and start/end time of
 * the activity. It contains getters and setters for each of these fields, methods that return 
 * short/long arrays with activity information, a method to check for schedule conflicts,
 * and a method for finding if two activities are duplicates. It also has overridden hashCode() and
 * equals() methods.
 * @author Noah Anderson
 * @author Benjamin Uy
 * @author Hank Lenham
 * **/ 
public abstract class Activity implements Conflict {

	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Maximum hour for Activity's starting/ending time of 24 */
	private static final int UPPER_HOUR = 24;
	/** Maximum minute for Activity's starting/ending time of 60 */
	private static final int UPPER_MINUTE = 60;

	/**
	 * Constructs an activity object with a title, meeting days, starting time, and
	 * ending time.
	 * 
	 * @param title       the title of the activity
	 * @param meetingDays the meeting days of the activity
	 * @param startTime   the starting time of the activity
	 * @param endTime     the ending time of the activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the title of the Activity
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the meeting days of the Activity
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the starting time of the Activity
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the ending time of the Activity
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Returns a string containing the meeting days, starting time and ending time
	 * (in standard time) for a Activity.
	 * 
	 * @return the meetingString
	 */
	public String getMeetingString() {
		// If meeting days are arranged, return appropriate meeting string
		if ("A".equals(getMeetingDays()))
			return "Arranged";

		String meetingString = "";
		int startHour, startMin, endHour, endMin;
		startHour = getStartTime() / 100;
		startMin = getStartTime() % 100;
		endHour = getEndTime() / 100;
		endMin = getEndTime() % 100;
		String startTimeOfDay = ""; // Keeps track of whether a starting time is AM or PM for a Activity
		String endTimeOfDay = ""; // Keeps track of whether ending time is AM or PM for a Activity

		// convert from military to standard time
		if (startHour > 12) {
			startHour -= 12;
			startTimeOfDay = "PM";
		} else if (startHour == 0) {
			startHour += 12;
			startTimeOfDay = "AM";
		} else if (startHour == 12) {
			startTimeOfDay = "PM";
		} else
			startTimeOfDay = "AM";
		if (endHour > 12) {
			endHour -= 12;
			endTimeOfDay = "PM";
		} else if (endHour == 0) {
			endHour += 12;
			endTimeOfDay = "AM";
		} else if (endHour == 12) {
			endTimeOfDay = "PM";
		} else
			endTimeOfDay = "AM";

		// assembling meeting string
		meetingString += getMeetingDays() + " "; // Concatenate meeting days first
		meetingString += startHour + ":"; // concatenate starting hours
		if (startMin < 10) // concatenates extra 0 if minutes are less than 0
			meetingString += "0";
		meetingString += startMin + startTimeOfDay + "-"; // Concatenate starting minutes and AM/PM
		meetingString += endHour + ":"; // Concatenate ending hours
		if (endMin < 10) // Concatenates extra 0 if minutes are less than 0
			meetingString += "0";
		meetingString += endMin + endTimeOfDay; // Concatenate starting minutes and AM/PM

		return meetingString;
	}

	/**
	 * Sets the title of the Activity. If the title is null or empty, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * Sets the meeting days, starting time, and ending time of the Activity. If the
	 * meeting days String is null or empty, an IllegalArgumentException is thrown.
	 * If the starting time is after the ending time, or there are given times on an
	 * arranged schedule, an IllegalArgumentException is thrown. If there is more
	 * than one of the same day, an IllegalArgumentException is thrown. If the
	 * starting/ending minutes are less than 0 or more than 59, or the hours are
	 * less than 0 or more than 24, an IllegalArgumentException is thrown. If the
	 * Activitys are arranged, only the meeting days are set, with the starting time
	 * and ending time being set to 0. If the Activitys aren't arranged, the meeting
	 * days, starting time, and ending time are set.
	 * 
	 * @param meetingDays the days that the Activity meets
	 * @param startTime   the starting time of the Activity
	 * @param endTime     the ending time of the Activity
	 * @throws IllegalArgumentException if the meetingDays, startTime, or endTime
	 *                                  parameter is invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays))
			throw new IllegalArgumentException("Invalid meeting days and times.");
		if (startTime > endTime)
			throw new IllegalArgumentException("Invalid meeting days and times.");

		int mCount, tCount, wCount, hCount, fCount;
		mCount = 0;
		tCount = 0;
		wCount = 0;
		hCount = 0;
		fCount = 0;
		for (int i = 0; i < meetingDays.length(); i++) {
			char day = meetingDays.charAt(i);
			switch (day) {
			case 'M':
				mCount++;
				break;
			case 'T':
				tCount++;
				break;
			case 'W':
				wCount++;
				break;
			case 'H':
				hCount++;
				break;
			case 'F':
				fCount++;
				break;
			case 'A':
				break;
			default:
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) 	// checks
																					// for
																					// duplicates
			throw new IllegalArgumentException("Invalid meeting days and times.");

		// Create variables to separate the starting and ending times into hours and
		// minutes
		int startHour, startMin, endHour, endMin = 0;
		startHour = startTime / 100;
		startMin = startTime % 100;
		endHour = endTime / 100;
		endMin = endTime % 100;

		if (startHour < 0 || startHour >= UPPER_HOUR) // not between 0 and 23, inclusive
			throw new IllegalArgumentException("Invalid meeting days and times.");

		if (startMin < 0 || startMin >= UPPER_MINUTE) // not between 0 and 59, inclusive
			throw new IllegalArgumentException("Invalid meeting days and times.");

		if (endHour < 0 || endHour >= UPPER_HOUR) // not between 0 and 23, inclusive
			throw new IllegalArgumentException("Invalid meeting days and times.");

		if (endMin < 0 || endMin >= UPPER_MINUTE) // not between 0 and 59, inclusive
			throw new IllegalArgumentException("Invalid meeting days and times.");

		// everything is valid and works together!
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns a short String array of fields related to an activity
	 * 
	 * @return the ShortDisplayArray
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Returns a long String array of fields related to an activity
	 * 
	 * @return the longDisplayArray
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks for time conflicts when adding an activity to the schedule.
	 * Throws a ConflictException if this activity overlaps in time by at least one minute,
	 * on at least one day with possibleConflictingActivity.
	 * @param possibleConflictingActivity The activity that is being compared to
	 * @throws ConflictException If this activity conflicts with the possibleConflictingActivity
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		// Check to see if any days overlap. If they do, the times will be compared.
		boolean dayOverlap = false; // Is set to true if the activities have a common meeting day
		for (int i = 0; i < getMeetingDays().length(); i++) {
			char day = getMeetingDays().charAt(i);
			// Iterates through each day, checking to see if it is in the other activity's meeting days.
			if (possibleConflictingActivity.getMeetingDays().indexOf(day) > -1) 
				dayOverlap = true;
		}
		// If either activity is arranged, their days do not count as overlapping or conflicting.
		if (this.getMeetingDays().equals("A") || possibleConflictingActivity.getMeetingDays().equals("A"))
			dayOverlap = false;
		// If days overlap, check meeting times.
		if (dayOverlap) {
			// using fields to store start/end times to make code simpler to read
			int startA = this.getStartTime();
			int startB = possibleConflictingActivity.getStartTime();
			int endA = this.getEndTime();
			int endB = possibleConflictingActivity.getEndTime();
			if (startB <= startA && startA <= endB) // if A's start time is during B, they conflict
				throw new ConflictException();
			if (startA <= startB && startB <= endA) // if B's start time is during A, they conflict
				throw new ConflictException();
		}
	}

	/**
	 * Finds if two activities are duplicates, meaning they have the same
	 * name/title.
	 * 
	 * @param activity the activity to compare to
	 * @return boolean true if the activities are duplicates
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Provides a unique hash code for the activity
	 * 
	 * @return result the hash code result
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
	 * Finds if one activity is equivalent to another activity in all fields
	 * 
	 * @param obj object to compare to
	 * @return boolean true if objects are equal
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

}