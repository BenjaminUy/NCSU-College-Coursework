package edu.ncsu.csc216.wolf_hire.model.command;

/**
 * Class encapsulates the information about a user command that would lead
 * to a transition in the application FSM. Contains one inner enumeration, CommandValue
 * @author Benjamin Uy
 */
public class Command {
	
	/** CommandValue that represents possible actions that can cause transitions in the finite state machine. */
	private CommandValue command;
	/** String representing information about a specific command value */
	private String commandInformation;

	/**
	 * Enumeration for possible command values that can cause transitions in the finite state machine.
	 * These commands include: ASSIGN, REJECT, RESUBMIT, RETURN, SCHEDULE, PROCESS, HIRE, and TERMINATE.
	 * @author Benjamin Uy
	 */
	public enum CommandValue { 
		/** Command value for assigning an application to a reviewer */
		ASSIGN, 
		/** Command value for rejecting an application from position */
		REJECT, 
		/** Command value for resubmitting a rejected application */
		RESUBMIT, 
		/** Command value for returning an application to the submitted state */
		RETURN, 
		/** Command value for interviewing an applicant */
		SCHEDULE, 
		/** Command value for processing an application after interviews */
		PROCESS, 
		/** Command value for hiring an applicant to a position */
		HIRE, 
		/** Command value for removing an applicant from a position */
		TERMINATE }
	
	/**
	 * Constructor for Command. Throws exceptions if the given parameters are invalid.
	 * Also sets the fields for Command after error checking.
	 * @param command CommandValue for an action that can cause an application to transition between states
	 * @param commandInformation String containing information about the command value
	 * @throws IllegalArgumentException if the CommandValue parameter is null
	 * @throws IllegalArgumentException if the Command has a CommandValue of ASSIGN, REJECT, or TERMINATE
	 * 		with a null or empty string commandInformation
	 * @throws IllegalArgumentException if the Command has a CommandValue of RESUBMIT, RETURN, SCHEDULE,
	 * 		PROCESS, or HIRE and a non-null commandInformation
	 */
	public Command(CommandValue command, String commandInformation) {
		if (command == null) {
			throw new IllegalArgumentException("Invalid information.");
		} else if ((command == Command.CommandValue.ASSIGN || command == Command.CommandValue.REJECT || 
					command == Command.CommandValue.TERMINATE) && ("".equals(commandInformation) || commandInformation == null)) {
			throw new IllegalArgumentException("Invalid information.");
		} else if ((command == Command.CommandValue.RESUBMIT || command == Command.CommandValue.RETURN ||
					command == Command.CommandValue.SCHEDULE || command == Command.CommandValue.PROCESS ||
					command == Command.CommandValue.HIRE) && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		this.command = command;
		this.commandInformation = commandInformation;
 	}
	
	/**
	 * Gets the Command CommandValue
	 * @return CommandValue for a transition in the FSM
	 */
	public CommandValue getCommand() {
		return command;
	}
	
	/**
	 * Gets the Command information string
	 * @return String for Command information
	 */
	public String getCommandInformation() {
		return commandInformation;
	}
}
