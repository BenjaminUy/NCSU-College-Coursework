package edu.ncsu.csc216.wolf_hire.model.application;

import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Concrete class representing the State Pattern context by managing application
 * information including the current state. The state is updated when a Command
 * encapsulating a transition is given to the Application class. Application also
 * encapsulates the ApplicationState interface and seven concrete *State classes.
 * @author Benjamin Uy
 */
public class Application {

	/** ApplicationState representing the current state of the Application */
	private ApplicationState currentState;
	/** Final instance of the SubmittedState inner class, and represents an application in the submitted phase */
	private final ApplicationState submittedState = new ApplicationState.SubmittedState();
	/** Final instance of the RejectedState inner class, and represents an application in the rejected phase */
	private final ApplicationState rejectedState = new ApplicationState.RejectedState();
	/** Final instance of the ReviewingState inner class, and represents an application in the review phase */
	private final ApplicationState reviewingState = new ApplicationState.ReviewingState();
	/** Final instance of the InterviewingState inner class, and represents an application in the interview phase */
	private final ApplicationState interviewingState = new ApplicationState.InterviewingState();
	/** Final instance of the ProcessingState inner class, and represents an application in the processing phase */
	private final ApplicationState processingState = new ApplicationState.ProcessingState();
	/** Final instance of the HiredState inner class, and represents an application in the hired phase */
	private final ApplicationState hiredState = new ApplicationState.HiredState();
	/** Final instance of the InactiveState inner class, and represents an application in the inactive phase */
	private final ApplicationState inactiveState = new ApplicationState.InactiveState();
	
	/** Integer for unique id for an application */
	private int applicationId;
	/** String representing the first name of the applicant */
	private String firstName;
	/** String representing the surname of the applicant */
	private String surname;
	/** String representing the Unity id of the applicant */	
	private String unityId;
	/** String representing the reviewer assigned to review the application */
	private String reviewer;
	/** String containing the rejection reason or termination reason for the application, if appropriate */
	private String note;
	
	/** Static integer used to create the applicationId. Used in two static methods: incrementCounter() and setCounter() */
	private static int counter;
	
	/** Constant string for the submitted state's name*/
	public static final String SUBMITTED_NAME = "Submitted";
	/** Constant string for the rejected state's name*/
	public static final String REJECTED_NAME = "Rejected";
	/** Constant string for the reviewing state's name*/
	public static final String REVIEWING_NAME = "Reviewing";
	/** Constant string for the interviewing state's name*/
	public static final String INTERVIEWING_NAME = "Interviewing";
	/** Constant string for the processing state's name*/
	public static final String PROCESSING_NAME = "Processing";
	/** Constant string for the hired state's name*/
	public static final String HIRED_NAME = "Hired";
	/** Constant string for the inactive state's name*/
	public static final String INACTIVE_NAME = "Inactive";
	/** Constant string for rejection reason of "Qualifications" */
	public static final String QUALIFICATIONS_REJECTION = "Qualifications";
	/** Constant string for rejection reason of "Incomplete" */
	public static final String INCOMPLETE_REJECTION = "Incomplete";
	/** Constant string for rejection reason of "Positions" */
	public static final String POSITIONS_REJECTION = "Positions";
	/** Constant string for rejection reason of "Duplicate" */
	public static final String DUPLICATE_REJECTION = "Duplicate";
	/** Constant string for the priority of "Completed" */
	public static final String COMPLETED_TERMINATION = "Completed";
	/** Constant string for the priority of "Resigned" */
	public static final String RESIGNED_TERMINATION = "Resigned";
	/** Constant string for the priority of "Fired" */
	public static final String FIRED_TERMINATION = "Fired";
	
	/**
	 * Interface for states in the Application State Pattern.  All 
	 * concrete Application states must implement the ApplicationState interface.
	 * The ApplicationState interface should be a private interface of the 
	 * Application class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface ApplicationState {
		
		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
		
		/**
		 * Inner class of the interface ApplicationState representing the submitted state of an application.
		 * Contains two public methods: updateState which updates the Application state and getStateName which
		 * returns a string representation of the class name.
		 * @author Benjamin Uy
		 */
		class SubmittedState implements ApplicationState {
			/**
			 * Method updates the Application from the given command.
			 * @param command Command describing the action that will update the Application's state.
			 * @throws UnsupportedOperationException if the given command is not ASSIGN or REJECT
			 * @throws UnsupportedOperationException if given command's command value is REJECT and does not have a rejection reason for its command information.
			 */
			public void updateState(Command command) {
				if (command.getCommand() != Command.CommandValue.ASSIGN && command.getCommand() != Command.CommandValue.REJECT) {
					throw new UnsupportedOperationException("Invalid command."); 
				} else if (command.getCommand() == Command.CommandValue.REJECT && command.getCommandInformation() != QUALIFICATIONS_REJECTION && 
						command.getCommandInformation() != INCOMPLETE_REJECTION && command.getCommandInformation() != POSITIONS_REJECTION && 
						command.getCommandInformation() != DUPLICATE_REJECTION) {
					throw new UnsupportedOperationException("Invalid command.");
				}
			}
			
			/**
			 * Returns the name of the SubmittedState as a String
			 * @return name of the SubmittedState as a String
			 */
			public String getStateName() {
				return SUBMITTED_NAME;
			}
		}
		
		/**
		 * Inner class of the interface ApplicationState representing the rejected state of an application.
		 * Contains two public methods: updateState which updates the Application state and getStateName which
		 * returns a string representation of the class name.
		 * @author Benjamin Uy
		 */
		class RejectedState implements ApplicationState  {
			/**
			 * Method updates the Application from the given command.
			 * @param command Command describing the action that will update the Application's state.
			 * @throws UnsupportedOperationException if the given command is not RESUBMIT
			 */
			public void updateState(Command command) {
				if (command.getCommand() != Command.CommandValue.RESUBMIT) {
					throw new UnsupportedOperationException("Invalid command.");
				}
			}
			
			/**
			 * Returns the name of the RejectedState as a String
			 * @return name of the RejectedState as a String
			 */
			public String getStateName() {
				return REJECTED_NAME;
			}
		}
		
		/**
		 * Inner class of the interface ApplicationState representing the reviewing state of an application.
		 * Contains two public methods: updateState which updates the Application state and getStateName which
		 * returns a string representation of the class name.
		 * @author Benjamin Uy
		 */
		class ReviewingState implements ApplicationState  {
			/**
			 * Method updates the Application from the given command.
			 * @param command Command describing the action that will update the Application's state.
			 * @throws UnsupportedOperationException if the given command is not SCHEDULE, RETURN, or REJECT
			 * @throws UnsupportedOperationException if given command's command value is REJECT and does not have a rejection reason for its command information.
			 */
			public void updateState(Command command) {
				if (command.getCommand() != Command.CommandValue.SCHEDULE && command.getCommand() != Command.CommandValue.RETURN &&
						command.getCommand() != Command.CommandValue.REJECT) {
					throw new UnsupportedOperationException("Invalid command.");
				} else if (command.getCommand() == Command.CommandValue.REJECT && command.getCommandInformation() != QUALIFICATIONS_REJECTION && 
						command.getCommandInformation() != INCOMPLETE_REJECTION && command.getCommandInformation() != POSITIONS_REJECTION && 
						command.getCommandInformation() != DUPLICATE_REJECTION) {
					throw new UnsupportedOperationException("Invalid command.");
				}
			}
			
			/**
			 * Returns the name of the ReviewingState as a String
			 * @return name of the ReviewingState as a String
			 */
			public String getStateName() {
				return REVIEWING_NAME;
			}
		}
		
		/**
		 * Inner class of the interface ApplicationState representing the interviewing state of an application.
		 * Contains two public methods: updateState which updates the Application state and getStateName which
		 * returns a string representation of the class name.
		 * @author Benjamin Uy
		 */
		class InterviewingState implements ApplicationState  {
			/**
			 * Method updates the Application from the given command.
			 * @param command Command describing the action that will update the Application's state.
			 * @throws UnsupportedOperationException if given command's command value is not PROCESS, ASSIGN, SCHEDULE, or REJECT
			 * @throws UnsupportedOperationException if given command's command value is REJECT and does not have a rejection reason for its command information.
			 */
			public void updateState(Command command) {
				if (command.getCommand() != Command.CommandValue.PROCESS && command.getCommand() != Command.CommandValue.ASSIGN &&
						command.getCommand() != Command.CommandValue.SCHEDULE && command.getCommand() != Command.CommandValue.REJECT) {
					throw new UnsupportedOperationException("Invalid command.");
				} else if (command.getCommand() == Command.CommandValue.REJECT && command.getCommandInformation() != QUALIFICATIONS_REJECTION && 
						command.getCommandInformation() != INCOMPLETE_REJECTION && command.getCommandInformation() != POSITIONS_REJECTION && 
						command.getCommandInformation() != DUPLICATE_REJECTION) {
					throw new UnsupportedOperationException("Invalid command.");
				}
			}
			
			/**
			 * Returns the name of the InterviewingState as a String
			 * @return name of the InterviewingState as a String
			 */
			public String getStateName() {
				return INTERVIEWING_NAME;
			}
		}
		
		/**
		 * Inner class of the interface ApplicationState representing the processing state of an application.
		 * Contains two public methods: updateState which updates the Application state and getStateName which
		 * returns a string representation of the class name.
		 * @author Benjamin Uy
		 */
		class ProcessingState implements ApplicationState  {
			/**
			 * Method updates the Application from the given command.
			 * @param command Command describing the action that will update the Application's state.
			 * @throws UnsupportedOperationException if given command's command value is not HIRE or REJECT.
			 * @throws UnsupportedOperationException if given command's command value is REJECT and does not have a rejection reason for its command information.
			 */
			public void updateState(Command command) {
				if (command.getCommand() != Command.CommandValue.HIRE && command.getCommand() != Command.CommandValue.REJECT) {
					throw new UnsupportedOperationException("Invalid command.");
				} else if (command.getCommand() == Command.CommandValue.REJECT && command.getCommandInformation() != QUALIFICATIONS_REJECTION && 
						command.getCommandInformation() != INCOMPLETE_REJECTION && command.getCommandInformation() != POSITIONS_REJECTION && 
						command.getCommandInformation() != DUPLICATE_REJECTION) {
					throw new UnsupportedOperationException("Invalid command.");
				}
			}
			
			/**
			 * Returns the name of the ProcessingState as a String
			 * @return name of the ProcessingState as a String
			 */
			public String getStateName() {
				return PROCESSING_NAME;
			}
		}
		
		/**
		 * Inner class of the interface ApplicationState representing the hired state of an application.
		 * Contains two public methods: updateState which updates the Application state and getStateName which
		 * returns a string representation of the class name.
		 * @author Benjamin Uy
		 */
		class HiredState implements ApplicationState  {
			/**
			 * Method updates the Application from the given command.
			 * @param command Command describing the action that will update the Application's state.
			 * @throws UnsupportedOperationException if given command's command value is not TERMINATE or the command information is not a termination reason
			 */
			public void updateState(Command command) {
				if (command.getCommand() != Command.CommandValue.TERMINATE || command.getCommandInformation() != COMPLETED_TERMINATION &&
						command.getCommandInformation() != RESIGNED_TERMINATION && command.getCommandInformation() != FIRED_TERMINATION) {
					throw new UnsupportedOperationException("Invalid command.");
				}
			}
			
			/**
			 * Returns the name of the HiredState as a String
			 * @return name of the HiredState as a String
			 */
			public String getStateName() {
				return HIRED_NAME;
			}
		}
		
		/**
		 * Inner class of the interface ApplicationState representing the inactive state of an application.
		 * Contains two public methods: updateState which updates the Application state and getStateName which
		 * returns a string representation of the class name.
		 * @author Benjamin Uy
		 */
		class InactiveState implements ApplicationState {
			/**
			 * Method updates the Application from the given command.
			 * @param command Command describing the action that will update the Application's state.
			 * @throws UnsupportedOperationException if this method is called with a given command. 
			 * 			There are no transitions from the inactive state.
			 */
			public void updateState(Command command) {
				throw new UnsupportedOperationException("Invalid command.");
			}
			
			/**
			 * Returns the name of the InactiveState as a String
			 * @return name of the InactiveState as a String
			 */
			public String getStateName() {
				return INACTIVE_NAME;
			}
		}
	}
	
	/**
	 * Method constructs a Application from the provided firstName, surname, and unityId. The applicationId is 
	 * set to the value stored in Application.counter, reviewer and note are set to null, and the current state
	 * is set to the submitted state. If any of the parameters are null or empty strings, then an IllegalArgumentException is thrown. 
	 * @param firstName the first name of the applicant
	 * @param surname the surname of the applicant
	 * @param unityId the unity id of the applicant
	 * @throws IllegalArgumentException if firstName, surname, or unityId are null or empty strings
	 */
	public Application(String firstName, String surname, String unityId) {
		if (firstName == null || "".equals(firstName) || surname == null || 
				"".equals(surname) ||  unityId == null || "".equals(unityId)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		setFirstName(firstName);
		setSurname(surname);
		setUnityId(unityId);
		if (counter == 0) {
			setId(1);
			incrementCounter();
			incrementCounter();
		} else {
			setId(counter);				// applicationId field set to the value stored in Application.counter
			incrementCounter();			// counter is then incremented
		}
		setReviewer(null);
		setNote(null);
		this.currentState = submittedState;	// a new Application starts in the submitted state
	}
	
	/**
	 * Constructor method for Application that takes for its parameters: id, state, firstName, unityId, reviewer, and note.
	 * After error checking, the fields of Application are set to the parameter values.
	 * This constructor is used in the PositionReader class.
	 * @param id id number of an Application
	 * @param state String representing the application state
	 * @param firstName the first name of the applicant
	 * @param surname the surname of the applicant
	 * @param unityId the unity id of the applicant
	 * @param reviewer reviewer assigned to review the application
	 * @param note String containing rejection reason or termination reason for the application, if applicable
	 * @throws IllegalArgumentException if id is less than or equal to zero or if firstName, surname, unityId, or state are null or empty strings
	 * @throws IllegalArgumentException if the state is SUBMITTED and the reviewer or note are non-null strings
	 * @throws IllegalArgumentException if the state is REJECTED and the reviewer is not null or the note does not contain a rejection reason
	 * @throws IllegalArgumentException if the state is REVIEWING, INTERVIEWING, PROCESSING, or HIRED and reviewer is null or the note
	 * 		contains a rejection/termination reason
	 * @throws IllegalArgumentException if the state is INACTIVE and the reviewer is null or the note contains a rejection reason or is null
	 */
	public Application(int id, String state, String firstName, String surname, String unityId, String reviewer, String note) {
		if (firstName == null || "".equals(firstName) || surname == null || 
				"".equals(surname) ||  unityId == null || "".equals(unityId) ||
				id <= 0 || state == null || "".equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		} else if (SUBMITTED_NAME.equals(state) && (reviewer != null || note != null)) {
			throw new IllegalArgumentException("Application cannot be created.");
		} else if (REJECTED_NAME.equals(state) && (reviewer != null || !QUALIFICATIONS_REJECTION.equals(note) && !INCOMPLETE_REJECTION.equals(note) &&
					!POSITIONS_REJECTION.equals(note) && !DUPLICATE_REJECTION.equals(note))) {
			throw new IllegalArgumentException("Application cannot be created.");
		} else if ((REVIEWING_NAME.equals(state) || INTERVIEWING_NAME.equals(state) || PROCESSING_NAME.equals(state)) && 
				(reviewer == null || "".equals(reviewer) || note != null)) {
			throw new IllegalArgumentException("Application cannot be created.");
		} else if (HIRED_NAME.equals(state) && (reviewer == null || "".equals(reviewer) || note != null)) {
			throw new IllegalArgumentException("Application cannot be created.");
		} else if (INACTIVE_NAME.equals(state) && (reviewer == null || note == null || !COMPLETED_TERMINATION.equals(note) && !RESIGNED_TERMINATION.equals(note) &&
				!FIRED_TERMINATION.equals(note))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		setFirstName(firstName);
		setSurname(surname);
		setUnityId(unityId);
		setReviewer(reviewer);
		setState(state);
		setNote(note);
		if (id > counter) {		// If id is greater than the current value in Application.counter
			setCounter(id);		// then Application.counter is updated to the id + 1 using setCounter(id)
		} else {
			setId(counter);
		}
		setId(id);
	}
	
	/**
	 * Sets the id of the Application with the given id
	 * @param id unique id for an Application
	 */
	private void setId(int id) {
		this.applicationId = id;
	}
	
	/**
	 * Sets the state field for the current state with the given String for state value
	 * @param stateValue String representing a state an Application can be in
	 */
	private void setState(String stateValue) {
		if (SUBMITTED_NAME.equals(stateValue)) {
			this.currentState = submittedState;
		} else if (REJECTED_NAME.equals(stateValue)) {
			this.currentState = rejectedState;
		} else if (REVIEWING_NAME.equals(stateValue)) {
			this.currentState = reviewingState;
		} else if (INTERVIEWING_NAME.equals(stateValue)) {
			this.currentState = interviewingState; 
		} else if (PROCESSING_NAME.equals(stateValue)) {
			this.currentState = processingState;
		} else if (HIRED_NAME.equals(stateValue)) {
			this.currentState = hiredState;
		} else if (INACTIVE_NAME.equals(stateValue)) {
			this.currentState = inactiveState;
		}
	}
	
	/**
	 * Method gets the id unique to the Application
	 * @return integer for the Application id 
	 */
	public int getId() {
		return applicationId;
	}
	
	/**
	 * Method gets a string representation of the Application's state
	 * @return String representation of the Application's current state
	 */
	public String getState() {
		return currentState.getStateName();
	}
	
	/**
	 * Method gets the first name of the applicant
	 * @return first name of the applicant
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Method sets the first name of the applicant
	 * @param firstName first name of the applicant
	 */
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Method gets the surname of the applicant
	 * @return surname of the applicant
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Method sets the surname of the applicant
	 * @param surname surname of the applicant
	 */
	private void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * Method gets the unity id of the applicant
	 * @return unity id of the applicant
	 */
	public String getUnityId() {
		return unityId;
	}
	
	/**
	 * Method sets the unity id of the applicant
	 * @param unityId unity id of the applicant
	 */
	private void setUnityId(String unityId) {
		this.unityId = unityId;
	}
	
	/**
	 * Method gets the reviewer of the Application
	 * @return reviewer of the Application
	 */
	public String getReviewer() {
		if (this.reviewer == null) {
			return "";
		}
		return reviewer;
	}
	
	/**
	 * Method sets the reviewer of the Application
	 * @param reviewer reviewer of the Application
	 */
	private void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	
	/**
	 * Method gets the note of the Application
	 * @return note which contains a rejection reason or termination reason for the Application, if applicable
	 */
	public String getNote() {
		if (this.note == null) {
			return "";
		} 
		return note;
	}
	
	/**
	 * Method sets the note of the Application
	 * @param note String containing a rejection reason or termination reason for the Application, if applicable
	 */
	private void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * Method increments the value of the counter field by 1
	 */
	public static void incrementCounter() {
		counter++;
	}
	
	/**
	 * Method sets the id for the next application to the given value
	 * @param newCount integer representing the id of the application
	 */
	public static void setCounter(int newCount) {
		counter = newCount;
	}
	
	/**
	 * Method returns the string representation of the Application that is printed during file save operations.
	 * Should the fields of reviewer and/or note are null, they will be replaced with empty strings. 
	 * @return string representation of the Application including id, state, first name, surname, unity id, reviewer, and note
	 */
	public String toString() {
		return "* " + getId() + "," + getState() + "," + getFirstName() + "," + getSurname() + "," + getUnityId() + "," + getReviewer() + "," + getNote();
	}
	
	/**
	 * Method drives the finite state machine by delegating to the currentState's updateState(Command) method.
	 * @param c Command describing the action that will update the Application's state.
	 * @throws UnsupportedOperationException if the current state determines that the transition is not
	 * 		appropriate to the FSM. The *State.updateState(Command) method will actually throw the exception
	 */
	public void update(Command c) {	
		currentState.updateState(c);	// Call the updateState() methods of the applicationState concrete classes
										// They will throw an UnsupportedOperationException if a given command is not appropriate for the current state
		if (currentState == submittedState) {
			if (c.getCommand() == Command.CommandValue.ASSIGN) {
				currentState = reviewingState;
				reviewer = c.getCommandInformation();	
			} else if (c.getCommand() == Command.CommandValue.REJECT) {
				currentState = rejectedState;
				reviewer = "";
				note = c.getCommandInformation();
			}
		} else if (currentState == rejectedState) {
			if (c.getCommand() == Command.CommandValue.RESUBMIT) {
				currentState = submittedState;
				note = "";
			}
		} else if (currentState == reviewingState) {
			if (c.getCommand() == Command.CommandValue.SCHEDULE) {
				currentState = interviewingState;
			} else if (c.getCommand() == Command.CommandValue.RETURN) {
				currentState = submittedState;
				reviewer = "";
			} else if (c.getCommand() == Command.CommandValue.REJECT) {
				currentState = rejectedState;
				reviewer = "";
				note = c.getCommandInformation();
			}
		} else if (currentState == interviewingState) {
			if (c.getCommand() == Command.CommandValue.PROCESS) {
				currentState =  processingState;
			} else if (c.getCommand() == Command.CommandValue.ASSIGN) {
				currentState = reviewingState;
				reviewer = c.getCommandInformation();
			} else if (c.getCommand() == Command.CommandValue.SCHEDULE) {
				currentState = interviewingState;
			} else if (c.getCommand() == Command.CommandValue.REJECT) {
				currentState = rejectedState;
				reviewer = "";
				note = c.getCommandInformation();
			}
		} else if (currentState == processingState) {
			if (c.getCommand() == Command.CommandValue.HIRE) {
				currentState = hiredState;
			} else if (c.getCommand() == Command.CommandValue.REJECT) {
				currentState = rejectedState;
				reviewer = "";
				note = c.getCommandInformation();
			}
		} else if (currentState == hiredState && c.getCommand() == Command.CommandValue.TERMINATE) {
			currentState = inactiveState;
			note = c.getCommandInformation();
		}	// Note, there are no transitions from the inactive state
	}
}
