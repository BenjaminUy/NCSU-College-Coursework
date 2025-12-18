package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * Abstract class at the top of the hierarchy for categories. 
 * AbstractCategory has fields for its category name, an ISwapList of Tickets, and the number of completed Tickets
 * @author Benjamin Uy
 */
public abstract class AbstractCategory {
	/** Name of the AbstractCategory */
	private String categoryName;
	/** Number of completed Tickets */
	private int completedCount;
	/** ISwapList of Ticket objects */
	private ISwapList<Ticket> tickets;
	
	/**
	 * Constructor for AbstractCategory that sets the fields from the parameters and constructs a SwapList for Tickets
	 * @param categoryName name of the AbstractCategory
	 * @param completedCount number of completed tickets
	 * @throws IllegalArgumentException if categoryName is null or empty String
	 * @throws IllegalArgumentException if completedCount is negative
	 */
	public AbstractCategory(String categoryName, int completedCount) {
		if (categoryName == null || "".equals(categoryName)) {
			throw new IllegalArgumentException("Invalid name.");
		} else if (completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		setCategoryName(categoryName);
		this.completedCount = completedCount;
		tickets = new SwapList<Ticket>();
	}

	/**
	 * Method returns the category name of the AbstractCategory
	 * @return category name of the AbstractCategory
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Method sets the category name to the given parameter
	 * @param categoryName name of the category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * Method returns the ISwapList of Tickets
	 * @return ISwapList of Tickets
	 */
	public ISwapList<Ticket> getTickets() {
		return tickets;
	}
	
	/**
	 * Method returns the number of completed Tickets
	 * @return number of completed Tickets
	 */
	public int getCompletedCount() {
		return completedCount;
	}
	
	/**
	 * Method adds the given Ticket to the end of the list and adds the current instance of Category to the Ticket
	 * @param t Ticket object to add
	 */
	public void addTicket(Ticket t) {
		tickets.add(t);
		t.addCategory(this);
	}
	
	/**
	 * Method removes and returns the Ticket object at the given index
	 * @param idx index of the Ticket to remove
	 * @return Ticket object at the index
	 */
	public Ticket removeTicket(int idx) {
		return tickets.remove(idx);
	}
	
	/**
	 * Method returns the Ticket at the given index
	 * @param idx index of the Ticket to get
	 * @return Ticket object at given index
	 */
	public Ticket getTicket(int idx) {
		return tickets.get(idx);
	}
	
	/**
	 * Method finds the given Ticket in the list and removes it.
	 * Afterward, the completedCount is incremented.
	 * @param t Ticket object to set as complete
	 */
	public void completeTicket(Ticket t) {
		for (int i = 0; i < tickets.size(); i++) {
			if (t == tickets.get(i)) {
				removeTicket(i);
				completedCount++;
			}
		}
	}
	
	/**
	 * Method returns a 2D String array representation of Tickets
	 * @return 2D String array representing Tickets
	 */
	public abstract String[][] getTicketsAsArray();

}
