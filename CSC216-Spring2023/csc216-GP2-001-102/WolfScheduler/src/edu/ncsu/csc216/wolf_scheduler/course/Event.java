/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event is the second child class of Activity. In addition to items provided by Activity,
 * Event has one field called eventDetails--a string description of an Event. In addition to an Event constructor
 * and get/set methods for eventDetails, this class provides functionality for returning a short and long display array
 * containing information on an Event.
 * 
 * @author Benjamin Uy
 */
public class Event extends Activity {

	/**
	 * String containing a description about an Event
	 */
	private String eventDetails;
	
	/**
	 * Method constructs a Method object using title, meetingDays, startTime, endTime, and eventDetails.
	 * The first four parameters are provided by the super or parent class. Method throws an exception if
	 * eventDetails is invalid.
	 * @param title title of an Event
	 * @param meetingDays meeting days of an Event
	 * @param startTime starting time of an Event
	 * @param endTime ending time of an Event
	 * @param eventDetails description about an Event
	 * @throws IllegalArgumentException if eventDetails is null
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		setEventDetails(eventDetails);
	}

	/**
	 * Method returns a String array of length 4 containing two empty string elements and information 
	 * on title and meeting string of an Event. 
	 * Method overrides because an Event does not have a name and section like a Course.
	 * @return string array containing two empty strings and an Event's title and meeting string.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		shortDisplay[0] = "";
		shortDisplay[1] = "";
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		return shortDisplay;
	}

	/**
	 * Method returns a String array of length 7 containing information on the title, meeting string, and
	 * event details of an Event.
	 * Method overrides because an Event does not have a name, section, credits, and instructor id like Course.
	 * @return string array containing 4 empty strings and an Event's title, meeting string, and details
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = "";
		longDisplay[1] = "";
		longDisplay[2] = getTitle();
		longDisplay[3] = "";
		longDisplay[4] = "";
		longDisplay[5] = getMeetingString();
		longDisplay[6] = eventDetails;
		return longDisplay;
		
	}

	/**
	 * Method returns an Event's event details
	 * @return eventDetails description about an Event
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Method sets the eventDetails for an Event object
	 * @param eventDetails description about an Event
	 */
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	/**
	 * Method returns a string representation of an Event in the order:
	 * title, meeting days, start time, end time, event details
	 * Method overrides because an Event does not have a name, section, credits, and instructor id like Course.
	 * @return string representation of an Event
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + eventDetails;
	}
	
	/**
	 * Method sets the MeetingDaysAndTime for an Event using meetingDays, startTime, and endTime; throws
	 * an exception if the given parameters are invalid.
	 * @param meetingDays days that the Event occurs
	 * @param startTime time that the Event starts
	 * @param endTime time that the Event ends
	 * @throws IllegalArgumentException if meetingDays is null or an empty string
	 * @throws IllegalArgumentException if any of the characters in the meetingDays string are not
	 * 		M, T, W, H, F, S, or U
	 * @throws IllegalArgumentException if there are duplicate characters in the meetingDays string. 
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		int mondayCount = 0;
		int tuesdayCount = 0;
		int wednesdayCount = 0;
		int thursdayCount = 0;
		int fridayCount = 0;
		int saturdayCount = 0;
		int sundayCount = 0;
		
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}  
		
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
				case 'S':
					saturdayCount++;
					break;
				case 'U':
					sundayCount++;
					break;
				default: 
					throw new IllegalArgumentException("Invalid meeting days and times.");	
			}		
		}
		
		// Check for duplicates in the count
		if (mondayCount > 1 || tuesdayCount > 1 || wednesdayCount > 1 ||
				thursdayCount > 1 || fridayCount > 1 || saturdayCount > 1 || sundayCount > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * Method determines if the parameter is an instance of Event and, if so, compares it
	 * to see if it is a duplicate of another Event object.
	 * @param a an Activity that is being compared to an Event
	 * @return true if the two Events have the same title
	 */
	public boolean isDuplicate(Activity a) {
		if (a instanceof Event) {
			String event1 = this.getTitle();
			Event e = (Event) a;
			String event2 = e.getTitle();
			if (event1.equals(event2)) {
				return true;
			}
		}
		return false;
	}
	

}
