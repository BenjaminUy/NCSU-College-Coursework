package edu.ncsu.csc216.wolf_tickets.model.tickets;

import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;

/**
 * Class extends AbstractCategory and implements the Comparable interface for Category objects
 * @author Benjamin Uy
 */
public class Category extends AbstractCategory implements Comparable<Category> {

	/**
	 * Constructor for Category that calls the super class with the given parameters
	 * @param categoryName name of Category
	 * @param completedCount number of completed Tickets
	 */
	public Category(String categoryName, int completedCount) {
		super(categoryName, completedCount);
	}
	
	/**
	 * Method returns the Tickets in the Category as a 2D String array where the first column is the Ticket's
	 * priority (the index of the Ticket in the list) and the name of the Ticket
	 * @return 2D String array representation of Tickets
	 */
	public String[][] getTicketsAsArray() {
		ISwapList<Ticket> t = getTickets();
		String[][] s = new String[t.size()][2];
		
		for (int i = 0; i < s.length; i++) {
			s[i][0] = "" + i;
			s[i][1] = t.get(i).getTicketName();
		}	
		return s;
	}
	
	/**
	 * Method compares the names of the Category objects with a case-insensitive comparison
	 * @param otherCategory Category object to compare to this Category
	 * @return integer for how the otherCategory lexicographically positioned relative to this Category 
	 */
	public int compareTo(Category otherCategory) {
		return this.getCategoryName().compareToIgnoreCase(otherCategory.getCategoryName());
	}

}
