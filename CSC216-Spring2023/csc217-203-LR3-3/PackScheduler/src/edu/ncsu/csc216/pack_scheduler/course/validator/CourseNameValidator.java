package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class that uses the state pattern to determine if a given course name is valid. This class has
 * an inner abstract class called State that also has inner classes representing the four states in the FSM.
 * CourseNameValidator has one method called isValid() which utilizes the state pattern.
 * @author hctynch
 * @author bsuy
 * @author ajwarre5
 */
public class CourseNameValidator {
	
	/** Boolean used for determining if CourseNameValidator is in an appropriate terminal state */ 
	private boolean validEndState = false;
	/** Variable that updates to the number of letters in course name */
	private int letterCount = 0;
	/** Variable that updates to the number of digits in course name */
	private int digitCount = 0;
	/** State representing the current state of the  */
	private State currentState;
	/** Final instance of the LetterState inner class, representing CourseNameValidator in the letter state */
	private final LetterState stateLetter = new LetterState();
	/** Final instance of the SuffixState inner class, representing CourseNameValidator in the suffix state */
	private final SuffixState stateSuffix = new SuffixState();
	/** Final instance of the NumberState inner class, representing CourseNameValidator in the number state */
	private final NumberState stateNumber = new NumberState();
	/** Final instance of the InitialState inner class, representing CourseNameValidator in the initial state */
	private final InitialState stateInitial = new InitialState();
	
	/**
	 * Method determines if the given courseName string is valid
	 * @param courseName name of course
	 * @return true if the given string is considered valid and false if not
	 * @throws InvalidTransitionException when the FSM attempts an invalid transition
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		// Set the state field to be the initial FSM state
		currentState = stateInitial;
		validEndState = false;
		digitCount = 0;
		letterCount = 0;
				// Create a variable to track the current character index
				int charIndex = 0;
				
				// Variable to keep track of the current input character being examined
				char c;
				
				// Iterate through the ID, examining one character at a time
				while(charIndex < courseName.length()) {
					// Set the current character being examined
					c = courseName.charAt(charIndex);
					if (Character.isLetter(c)) {
						currentState.onLetter();
					} else if (Character.isDigit(c)) {
						currentState.onDigit();
					} else {
						currentState.onOther();
					}
					charIndex++;
				}
				digitCount = 0;
				letterCount = 0;
				return validEndState;
	}
	
	/**
	 * Abstract class and inner class of CourseNameValidator. The State class also has four concrete methods: LetterState, SuffixState,
	 * InitialState, and NumberState. These concrete states represent different states in the FSM. State has three methods: onLetter, onDigit,
	 * and onOther which are used by the state classes.
	 * @author hctynch
	 * @author bsuy
	 * @author ajwarre5
	 */
	public abstract class State {
		/** 
		 * Handles letter input 
		 * @throws InvalidTransitionException if the *state class being called determines an invalid transition to the letterState
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		/** 
		 * Handles digit input
		 * @throws InvalidTransitionException if the *state class being called determines an invalid transition to the numberState
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		/**
		 * Method for throwing InvalidTransitionException if input is not letter or digit.
		 * @throws InvalidTransitionException if onOther() is called
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * Inner class of State representing the initial state
	 * @author Benjamin Uy
	 */
	public class InitialState extends State {
		
		/** Private constructor of InitialState */
		private InitialState() { }
		
		/**
		 * Method handles letter input for InitialState
		 */
		@Override
		public void onLetter() {
			validEndState = false;
			letterCount++;
			currentState = stateLetter;
		}

		/**
		 * Method handles digit input for InitialState
		 * @throws InvalidTransitionException if the method is called
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			validEndState = false;
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	/**
	 * Inner class of State representing the letter state
	 * @author Benjamin Uy
	 */
	public class LetterState extends State {
		/** Constant for the max number of prefix letters in a valid course name */
		private static final int MAX_PREFIX_LETTERS = 4;
		
		/** Private constructor of LetterState */
		private LetterState() { }
		
		/**
		 * Method handles letter input for LetterState
		 * @throws InvalidTransitionException if method is called if letterCount is not less than 4
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				letterCount++;
				currentState = stateLetter;
				validEndState = false;
			} else {
				validEndState = false;
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * Method handles digit input for LetterState
		 */
		@Override
		public void onDigit() {
			validEndState = false;
			digitCount++;
			currentState = stateNumber;
		}
	}
	
	/**
	 * Inner class of State representing the number or digit state
	 * @author Benjamin Uy
	 */
	public class NumberState extends State {
		/** Constant representing the max number of digits in a valid course name */
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		/** Private constructor of NumberState */
		private NumberState() { }
		
		/**
		 * Method handles letter input for NumberState
		 * @throws InvalidTransitionException if digitCount is not equal to 3
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = stateSuffix;
				validEndState = true;
			} else {
				validEndState = false;
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 * Method handles digit input for NumberState
		 * @throws InvalidTransitionException if digitCount is not less than 3
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				digitCount++;
				currentState = stateNumber;
				validEndState = false;
				if (digitCount == COURSE_NUMBER_LENGTH) {		// A valid terminal state in the FSM is when course name
					validEndState = true;					// ends with three digits
				}
			} else {
				validEndState = false;
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}
	
	/**
	 * Inner class of State representing the suffix state
	 * @author Benjamin Uy
	 */
	public class SuffixState extends State {
		
		/** Private constructor of SuffixState */
		private SuffixState() { }
		
		/** 
		 * Method handles letter input for SuffixState
		 * @throws InvalidTransitionException if method is called
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			validEndState = false;
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/** 
		 * Method handles digit input for SuffixState
		 * @throws InvalidTransitionException if method is called
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			validEndState = false;
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}
