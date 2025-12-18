package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * Class containing information about an individual ticket such as its name, description, and whether it is active.
 * Ticket also contains an ISwapList of AbstractCategory objects.
 * @author Benjamin Uy
 */
public class Ticket {

	/** ISwapList containing AbstractCategory objects */
	private ISwapList<AbstractCategory> categories;
	/** Name of the Ticket */
	private String ticketName;
	/** Description of the Ticket */
	private String ticketDescription;
	/** Boolean for whether the Ticket is active or not */
	private boolean active;
	
	/**
	 * Method constructs a Ticket object with the given parameters
	 * @param ticketName name of the Ticket
	 * @param ticketDescription description of the Ticket
	 * @param active boolean for whether or not the Ticket is active
	 */
	public Ticket(String ticketName, String ticketDescription, boolean active) {
		setTicketName(ticketName);
		setTicketDescription(ticketDescription);
		setActive(active);
		categories = new SwapList<AbstractCategory>();
	}

	/**
	 * Method returns the ticket name
	 * @return the name of the Ticket
	 */
	public String getTicketName() {
		return ticketName;
	}

	/**
	 * Method sets the Ticket name to the given parameter 
	 * @param ticketName name of the Ticket
	 * @throws IllegalArgumentException if the given parameter is null or empty string
	 */
	public void setTicketName(String ticketName) {
		if (ticketName == null || "".equals(ticketName)) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketName = ticketName;
	}

	/**
	 * Method returns the description of the Ticket
	 * @return description of the Ticket
	 */
	public String getTicketDescription() {
		return ticketDescription;
	}

	/**
	 * Method sets ticketDescription to the given parameter
	 * @param ticketDescription description of the Ticket
	 * @throws IllegalArgumentException if the given parameter is null
	 */
	public void setTicketDescription(String ticketDescription) {
		if (ticketDescription == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketDescription = ticketDescription;
	}

	/**
	 * Method returns whether or not the Ticket is active
	 * @return true if Ticket is active, false if not
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Method sets the Ticket active field to the given boolean
	 * @param active boolean for whether or not Ticket is active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Method returns the name of the AbstractCategory at index 0
	 * @return name of the AbstractCategory at index 0 or an empty string if categories field is null or empty
	 */
	public String getCategoryName() {
		if (categories == null || categories.size() == 0) {
			return "";
		}
		return categories.get(0).getCategoryName();
	}
	
	/**
	 * Method adds an AbstractCategory to the ISwapList
	 * @param category AbstractCategory to add to the ISwapList
	 * @throws IllegalArgumentException if the given parameter is null
	 */
	public void addCategory(AbstractCategory category) {
		if (category == null) {
			throw new IllegalArgumentException ("Incomplete ticket information.");
		}
		// Check if the AbstractCategory is already registered with the Ticket's categories field
		boolean contains = false;
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i) == category) {
				contains = true;
			}
		}
		if (!contains) {
			categories.add(category);
		}
	}
	
	/**
	 * Method marks the Ticket as complete
	 */
	public void completeTicket() {
		for (int i = 0; i < categories.size(); i++) {
			categories.get(i).completeTicket(this);
		}
	}
	
	/**
	 * Method returns a String representation of the Ticket for printing to a file
	 * @return String representation of the Ticket for printing to a file
	 */
	@Override
	public String toString() {
		String print = "* " + ticketName;
		if (active) {
			print = print + ",active"; 
		}
		print = print + "\n" + ticketDescription;
		return print;
	}
}
