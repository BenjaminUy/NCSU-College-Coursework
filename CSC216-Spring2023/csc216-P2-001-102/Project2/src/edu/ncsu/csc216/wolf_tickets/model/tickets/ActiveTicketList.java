package edu.ncsu.csc216.wolf_tickets.model.tickets;

/**
 * Class represents a list of Ticket objects that are considered active. This class extends AbstractCategory.
 * @author Benjamin Uy
 */
public class ActiveTicketList extends AbstractCategory {

	/** Constant holding the name of "Active Tickets" */
	public static final String ACTIVE_TICKETS_NAME = "Active Tickets";
	/** Constant holding the name of "Active Tickets" */	
	public static final String ACTIVE_TASKS_NAME = "Active Tickets";
	
	/**
	 * Method constructs the ActiveTicketList with "Active Tickets" as the name and no completed tickets
	 */
	public ActiveTicketList() {
		super(ACTIVE_TICKETS_NAME, 0);
	}
	
	/**
	 * Method adds the given Ticket to the list
	 * @param t Ticket to add to the list
	 * @throws IllegalArgumentException if given ticket is not active
	 */
	@Override
	public void addTicket(Ticket t) {
		if (!t.isActive()) {
			throw new IllegalArgumentException("Cannot add ticket to Active Tickets.");
		}
		super.addTicket(t);
	}
	
	/**
	 * Method sets the category name for ActiveTicketList
	 * @param categoryName name of the Category
	 * @throws IllegalArgumentException if the given category name is "Active Tickets"
	 */
	@Override
	public void setCategoryName(String categoryName) {
		if (!categoryName.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		super.setCategoryName(categoryName);
	}
	
	/**
	 * Method returns a 2D String array where the first column is the name of the Category
	 * that the Ticket belongs to along with the name of the Ticket
	 * @return 2D String array representation of the Ticket list
	 */
	public String[][] getTicketsAsArray() {
		String[][] ticketArray = new String[super.getTickets().size()][2];
		for (int i = 0; i < ticketArray.length; i++) {
			ticketArray[i][0] = super.getTickets().get(i).getCategoryName();		// Have Category name in the first column
			ticketArray[i][1] = super.getTickets().get(i).getTicketName();			// Have Ticket name in second column
		}
		return ticketArray;
	}
	
	/**
	 * Clears the ActiveTicketList of all Tickets
	 */
	public void clearTickets() {
		for (int i = 0; i < super.getTickets().size(); i++) {
			super.removeTicket(i);
			i--;		// Decrement i after every removed Ticket
		}
	}

}
