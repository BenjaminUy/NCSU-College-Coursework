package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.io.PositionReader;
import edu.ncsu.csc216.wolf_hire.model.io.PositionWriter;

/**
 * The WolfHire class controls the creation and modification of several Positions. Each of these Positions
 * represents a single position and its list of Applications.
 * Implements the Singleton design pattern where only instance of WolfHire can be created.
 * @author Benjamin Uy
 */
public class WolfHire {

	/** List that can contain multiple Position objects */
	private ArrayList<Position> positions;
	/** Position considered to be active, so that any new applications will apply to that position */
	private Position activePosition;
	/** Instance of WolfHire; by Singleton design, only one instance of WolfHire can be created */
	private static WolfHire singleton;
	
	/**
	 * Method constructs a new instance of WolfHire, should getInstance() find that singleton is null
	 */
	private WolfHire() {
		this.positions = new ArrayList<Position>();
		this.activePosition = null;
	}
	
	/**
	 * Method gets the instance of a WolfHire or calls WolfHire() to construct an instance if singleton is null
	 * @return instance of WolfHire
	 */
	public static WolfHire getInstance() {
		if (singleton == null) {
			singleton = new WolfHire();
		}
		return singleton;
	}
	
	/**
	 * Method uses PositionReader to read the given fileName and returns a list of Positions which are
	 * added to the positions field.
	 * @param fileName name of the file
	 */
	public void loadPositionsFromFile(String fileName) {
		ArrayList<Position> loadedPositions = new ArrayList<Position>();
		loadedPositions = PositionReader.readPositionFile(fileName);
		activePosition = loadedPositions.get(0);		// First position in the returned list is made the activePosition
		for (int i = 0; i < loadedPositions.size(); i++) {
			positions.add(loadedPositions.get(i));
		}
	}
	
	/**
	 * Method writes the list of Positions to the file with the given fileName by using the PositionWriter class
	 * @param fileName name of the file
	 * @throws IllegalArgumentException if activePosition is null
	 */
	public void savePositionsToFile(String fileName) {
		if (activePosition == null) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		PositionWriter.writePositionsToFile(fileName, positions);		// Use PostitionWriter class to write positions to given file
	}
	
	/**
	 * Method creates a new Position with the given parameters and adds it to the end of the positions list.
	 * The position is then loaded as the activePosition by calling loadPosition()
	 * @param positionName name of the Position
	 * @param hoursPerWeek hours per week for the Position
	 * @param payRate pay rate of the Position
	 * @throws IllegalArgumentException if positionName is null, empty string, or a duplicate of an existing position name
	 */
	public void addNewPosition(String positionName, int hoursPerWeek, int payRate) {
		if (positionName == null || "".equals(positionName)) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		for (int i = 0; i < positions.size(); i++) {		// For loop checks for duplicate positions (indicated by matching names)
			if (positionName.equalsIgnoreCase(positions.get(i).getPositionName())) {
				throw new IllegalArgumentException("Position cannot be created.");
			}
		}
		Position addition = new Position(positionName, hoursPerWeek, payRate);
		positions.add(addition);	// Add the newly created Position to the end of the positions list
		loadPosition(positionName);	// Call method that will load position as active position
	}
	
	/**
	 * Method finds the Position with the given name in the list and makes it the activePosition.
	 * Method also sets the application id for that position, to ensure new Applications added to the
	 * position are created with the next correct id.
	 * @param positionName name of the Position
	 * @throws IllegalArgumentException if there is no position with the given name
	 */
	public void loadPosition(String positionName) {
		boolean positionFound = false;
		for (int i = 0; i < positions.size(); i++) {
			if (positionName.equals(positions.get(i).getPositionName())) {
				activePosition = positions.get(i);
				activePosition.setApplicationId();
				positionFound = true;
			}
		}
		if (!positionFound) {
			throw new IllegalArgumentException("Position not available.");
		}
	}
	
	/**
	 * Method gets the current activePosition
	 * @return position that is considered active or null if there is no activePosition
	 */
	public Position getActivePosition() {
		if (activePosition == null) {
			return null;
		}
		return activePosition;
	}
	
	/**
	 * Method gets the name of the current activePosition
	 * @return name of the current activePosition
	 */
	public String getActivePositionName() {
		if (activePosition == null) {
			return null;
		}
		return activePosition.getPositionName();
	}
	
	/**
	 * Method returns a String array of position names in the order they are listed
	 * in the positions list. 
	 * @return String array of position names or an empty list if there are no positions
	 */
	public String[] getPositionList() {
		String[] s = new String[0];
		if (positions.size() == 0) {
			return s;						// Return an empty array if there are no positions
		}
		s = new String[positions.size()];	// Create a new array that can hold the number of positions
		for (int i = 0; i < positions.size(); i++) {
			s[i] = positions.get(i).getPositionName();
		}
		return s;
	}
	
	/**
	 * Method adds an Application with the given parameters to the active position's list of Applications
	 * @param firstName first name of the applicant
	 * @param surname surname of the applicant
	 * @param unityId unity id of the applicant
	 */
	public void addApplicationToPosition(String firstName, String surname, String unityId) {
		Application a = new Application(firstName, surname, unityId);
		if (activePosition != null) {
			activePosition.addApplication(a);
		}
	}
	
	/**
	 * Method will find the Application with the given id and update it by passing in the given Command.
	 * If no Application has the given id, the List doesn't change 
	 * @param id of the Application
	 * @param c Command representing actions that could change an Application's state
	 */
	public void executeCommand(int id, Command c) {
		Application a = getApplicationById(id);
		if (a != null) {
			a.update(c);
		}
	}
	
	/**
	 * Method removes the Application with the given id from the List.
	 * If there is no Application to remove, the list doesn't change.
	 * @param id of the Application
	 */
	public void deleteApplicationById(int id) {
		Application found = getApplicationById(id);		// Find application in activePosition with given id
		if (found != null) {							// If method return is non-null, an application was found
			activePosition.getApplications().remove(found);		// Remove the found application from active positions
		}
	}
	
	/**
	 * Method returns a String array of Application objects with the state specified by the given parameter
	 * @param stateName representation of the Application state
	 * @return String array of Application objects filtered by the given parameter
	 */
	public String[][] getApplicationsAsArray(String stateName) {
		if (activePosition == null) {
			return null;
		}
		
		int similarState = 0;
		
		List<Application> matchedState = activePosition.getApplications();
		
		if ("All".equals(stateName)) {
			similarState = matchedState.size(); 
			
		} else {
			for (int i = 0; i < matchedState.size(); i++) {
				if (!stateName.equals(matchedState.get(i).getState())) {
					matchedState.remove(matchedState.get(i));
					i--;
				} else {
					similarState++;
				}
			}
		}
		
		String[][] s = new String[similarState][4];
		// List<Application> a = activePosition.getApplications();
		for (int i = 0; i < similarState; i++) {
			for (int k = 0; k < 4; k++) {
				switch (k) {
					case 0:
						s[i][k] = "" + (matchedState.get(i)).getId();
						break;
					case 1:
						s[i][k] = matchedState.get(i).getState();
						break;
					case 2:
						s[i][k] = matchedState.get(i).getUnityId();
						break;
					case 3: 
						s[i][k] = matchedState.get(i).getReviewer();
						break;
					default:
						return null;
				}				
			}
		}
		return s;
	}
	
	/**
	 * Method returns the Application in the List of the activePosition with the given id. Returns null if there is
	 * no Application with the given id.
	 * @param id unique number id of an Application
	 * @return Application in the activePosition that has the given id, null if not found
	 */
	public Application getApplicationById(int id) {
		if (activePosition == null) {
			return null;
		}
		for (int i = 0; i < activePosition.getApplications().size(); i++) {
			if (activePosition.getApplications().get(i).getId() == id) {
				Application found = activePosition.getApplications().get(i);
				return found;
			}
		}
		return null;	// If no application with the given id is found, return null
	}
	
	/**
	 * Protected method intentionally restricted to use for testing. Sets the singleton to null.
	 */
	protected void resetManager() {
		singleton = null;
	}
}