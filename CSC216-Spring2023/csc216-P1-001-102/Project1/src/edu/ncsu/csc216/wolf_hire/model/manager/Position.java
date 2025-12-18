package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Position class represents a job position that can be in WolfHire, so it has information on
 * the position name, hours per week, and pay rate. In addition, Position maintains a List of Applications 
 * @author Benjamin Uy
 */
public class Position {

	/** List that can contain multiple Application objects */
	private List<Application> applications;
	/** Name of the Position */
	private String positionName;
	/** Number of hours per week for the Position */
	private int hoursPerWeek;
	/** Pay rate of the Position */
	private int payRate;
	
	/**
	 * Method creates a new Position and an empty List with the given parameters
	 * @param positionName name of the Position
	 * @param hoursPerWeek hours of per week for the Position
	 * @param payRate pay rate of the Position
	 */
	public Position(String positionName, int hoursPerWeek, int payRate) {
		setPositionName(positionName);
		setHoursPerWeek(hoursPerWeek);
		setPayRate(payRate);
		this.applications = new ArrayList<Application>();
	}

	/**
	 * Method gets the name of the Position
	 * @return name of the Position
	 */
	public String getPositionName() {
		return positionName;
	}
	
	/**
	 * Method sets the counter for the Application instances to the value of the maximum
	 * id in the list of Applications for the position by 1
	 */
	public void setApplicationId() {
		int maxId = 0;
		for (int i = 0; i < applications.size(); i++) {
			if (i == 0) {
				maxId = applications.get(i).getId();	// By default, the first id in the list is the maxId
			} else {
				maxId = Math.max(maxId, applications.get(i).getId());	// Current maxId is compared to next application's id
			}
		} 
		Application.setCounter(maxId + 1);
	}

	/**
	 * Method sets the name of the Position with the given parameter
	 * @param positionName name of the Position
	 * @throws IllegalArgumentException if the given parameter is null or an empty string
	 */
	private void setPositionName(String positionName) {
		if (positionName == null || "".equals(positionName)) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.positionName = positionName;
	}

	/**
	 * Method gets the hours per week of a Position
	 * @return hours per week of a Position
	 */
	public int getHoursPerWeek() {
		return hoursPerWeek;
	}

	/**
	 * Method sets the hoursPerWeek of a Position
	 * @param hoursPerWeek hours per week of a Position
	 * @throws IllegalArgumentException if the given parameter is less than 5 or more than 20
	 */
	private void setHoursPerWeek(int hoursPerWeek) {
		if (hoursPerWeek < 5 || hoursPerWeek > 20) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.hoursPerWeek = hoursPerWeek;
	}

	/**
	 * Method gets the pay rate of a Position
	 * @return payRate pay rate of a Position
	 */
	public int getPayRate() {
		return payRate;
	}

	/**
	 * Method sets the payRate of a Position
	 * @param payRate pay rate of a Position
	 * @throws IllegalArgumentException if the given parameter is less than 7 or more than 35
	 */
	private void setPayRate(int payRate) {
		if (payRate < 7 || payRate > 35) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.payRate = payRate;
	}
	
	/**
	 * Method creates a new Application in the submitted state, adds it to the list in sorted order, and returns the id
	 * @param firstName first name of the applicant
	 * @param surname surname of the applicant
	 * @param unityId unity id of the applicant
	 * @return integer for the application id
	 */
	public int addApplication(String firstName, String surname, String unityId) {
		Application a = new Application(firstName, surname, unityId);
		int id = addApplication(a); // Calls method that will add the application to the list in sorted order
		return id;
	}
	
	/**
	 * Method adds the application to the list in sorted order by id.
	 * @param application Application that will be added to the list of Applications
	 * @return integer for the application id
	 * @throws IllegalArgumentException if there is an application that exists with the given id
	 */
	public int addApplication(Application application) {
		for (int i = 0; i < applications.size(); i++) {		// For loop checking applications list for application with given id
			if (application.getId() == applications.get(i).getId()) {
				throw new IllegalArgumentException("Application cannot be created.");
			}
		}
		if (applications.size() == 0) {			// If the list is empty, add the given application
			applications.add(application);
			return application.getId();
		} else if (applications.size() == 1 && application.getId() > applications.get(0).getId()) {
			applications.add(application);		// Adds given application to end of list if greater than the list's only application's id
			return application.getId();
		} else if (applications.size() == 1 && application.getId() < applications.get(0).getId()) {
			applications.add(0, application);	// Adds given application to start of list if less than the list's only application's id
			return application.getId();
		}
		for (int i = 0; i < applications.size() - 1; i++) {		// For loop compares given application id to lists with at least two elements
			if (application.getId() < applications.get(i).getId() && application.getId() < applications.get(i + 1).getId()){
				applications.add(i, application);		// given application id is smaller than two sequential applications in list
				return application.getId();
			} else if (applications.get(i).getId() < application.getId() && application.getId() < applications.get(i + 1).getId()) {
				applications.add(i + 1, application);	// given application id is in between two applications in list
				return application.getId();
			} else if (i == applications.size() - 2 && applications.get(i).getId() < application.getId() &&
						applications.get(i + 1).getId() < application.getId()) {
				applications.add(application);			// given application id is more than the last two applications in list
			}
		}
		setApplicationId();
		return application.getId();
	}
	
	/**
	 * Method gets a List of Application objects
	 * @return List that can contain multiple Application objects
	 */
	public List<Application> getApplications() {
		return this.applications;
		}
	
	/**
	 * Method returns the Application in the List with the given id. Returns null if there is
	 * no Application with the given id.
	 * @param id unique number id of an Application
	 * @return Application that has the given id, null if not found
	 */
	public Application getApplicationById(int id) {
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == id) {
				return applications.get(i);
			}
		}
		return null; 	// If no application in the list is found with the given id, return null
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
		Application a = getApplicationById(id);
		if (a != null) {
			applications.remove(a);
		}
	}
	
	/**
	 * Method returns a string representation of the Position that is printed during file saving
	 * @return String representation of the Position that is printed during file saving
	 */
	public String toString() {
		return "# " + positionName + "," + hoursPerWeek + "," + payRate;
	}	
}