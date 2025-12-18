package edu.ncsu.csc216.wolf_tickets.model.group;

import java.io.File;

import edu.ncsu.csc216.wolf_tickets.model.io.GroupWriter;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * Class representing a Group, which contains the main functionality for the WolfTicketsGUI.
 * Group has a ActiveTicketList, AbstractCategory, and ISortedList of Category objects.
 * @author Benjamin Uy
 */
public class Group {
	/** ISortedList of Category objects */
	private ISortedList<Category> categories;
	/** ActiveTicketList for the list of active Ticket objects */
	private ActiveTicketList activeTicketList;
	/** AbstractCategory representing the current category */
	private AbstractCategory currentCategory;
	/** Name of the Group*/
	private String groupName;
	/** Boolean for whether the Group has been edited */
	private boolean isChanged;
	
	/**
	 * Method constructs a Group with the given parameter. The categories field is constructed as a SortedList
	 * and the activeTicketList is constructed as an empty ActiveTicketList. The currentCategory is set to the
	 * activeTicketList; isChanged is initialized to true. 
	 * @param groupName name of the Group
	 * @throws IllegalArgumentException if the given parameter is null, empty, or matches "Active Tickets"
	 */
	public Group(String groupName) {
		if (groupName == null || "".equals(groupName) || "Active Tickets".equals(groupName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		setGroupName(groupName);
		categories = new SortedList<Category>();
		activeTicketList = new ActiveTicketList();
		currentCategory = activeTicketList;
		isChanged = true;
	}
	
	/**
	 * Saves the current Group to the given file
	 * @param groupFile File to save the Group to
	 */
	public void saveGroup(File groupFile) {
		GroupWriter.writeGroupFile(groupFile, groupName, categories);
		isChanged = false;
	}
	
	/**
	 * Returns the Group name
	 * @return the name of the Group
	 */
	public String getGroupName() {
		return groupName;
	}
	
	/**
	 * Method sets the groupName to the given parameter
	 * @param groupName name of the Group
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * Method returns the Group's isChanged boolean
	 * @return boolean for whether the Group has changed
	 */
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	 * Method sets isChanged to the given parameter
	 * @param changed whether the Group has changed
	 */
	public void setChanged(boolean changed) {
		this.isChanged = changed;
	}
	
	/**
	 * Method adds the given Category to the list of categories
	 * @param category Category to add to list
	 * @throws IllegalArgumentException if the given category's name is equal to "Active Tickets"
	 * @throws IllegalArgumentException if the given category's name is a duplicate of an existing Category
	 */
	public void addCategory(Category category) {
		if (ActiveTicketList.ACTIVE_TASKS_NAME.equalsIgnoreCase(category.getCategoryName())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		categories.add(category);
		currentCategory = category;
		setChanged(true);
	}
	
	/**
	 * Method returns a list of Category names with "Active Tickets" being listed first
	 * @return list of Category names
	 */
	public String[] getCategoriesNames() {
		String[] names = new String[categories.size() + 1];		//  "Active Tickets" is always listed first in the array
		names[0] = ActiveTicketList.ACTIVE_TASKS_NAME;
		for (int i = 0; i < categories.size(); i++) {
			names[i + 1] = categories.get(i).getCategoryName();
		}
		return names;
	}
	
	/**
	 * Helper method that is used when working with the ActiveTicketList
	 */
	private void getActiveTicketList() {
		activeTicketList.clearTickets();	// First clear the ActiveTicketList
		for (int i = 0; i < categories.size(); i++) {
			for (int k = 0; k < categories.get(i).getTickets().size(); k++) {
				if (categories.get(i).getTicket(k).isActive()) {
					Ticket actTick = categories.get(i).getTicket(k);
					activeTicketList.addTicket(actTick);
				}
			}
		}
	}
	
	/**
	 * Method sets the currentCategory to the AbstractCategory with the given name
	 * @param categoryName name of the Category
	 */
	public void setCurrentCategory(String categoryName) {
		getActiveTicketList();
		currentCategory = activeTicketList;
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equalsIgnoreCase(categoryName)) {
				currentCategory = categories.get(i);
			}
		}
	}
	
	/**
	 * Method returns the current Category
	 * @return the current Category of Group
	 */
	public AbstractCategory getCurrentCategory() {
		return currentCategory;
	}
	
	/**
	 * Method edits the currentCategory to have the given name
	 * @param categoryName name of the Category
	 * @throws IllegalArgumentException if the category name is "Active Tickets"
	 * @throws IllegalArgumentException if the currentCategory is the activeTicketList
	 * @throws IllegalArgumentException if the given name is a duplicate of another Category
	 */
	public void editCategory(String categoryName) {
		if (ActiveTicketList.ACTIVE_TASKS_NAME.equalsIgnoreCase(categoryName))
			throw new IllegalArgumentException("Invalid name.");
		if (currentCategory == activeTicketList)
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equalsIgnoreCase(categoryName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		Category edit = null;
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).equals(currentCategory)) {
				edit = categories.remove(i);
			}
		}
		edit.setCategoryName(categoryName);
		categories.add(edit);
		isChanged = true;
	}
	
	/**
	 * Method removes the currentCategory and is then set to the activeTicketList
	 * @throws IllegalArgumentException if currentCategory is the activeTicketList
	 */
	public void removeCategory() {
		if (currentCategory == activeTicketList) {
			throw new IllegalArgumentException("The Active Tickets list may not be deleted.");
		}
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).equals(currentCategory)) {
				categories.remove(i);
			}
		}
		currentCategory = activeTicketList;
		isChanged = true;
	}
	
	/**
	 * Method adds a Ticket to the currentCategory
	 * @param t Ticket to add the currentCategory
	 */
	public void addTicket(Ticket t) {
		if (currentCategory.getClass() == Category.class) {
			currentCategory.addTicket(t);
			if (t.isActive()) {						// If given ticket is active, also add to the active ticket list
				getActiveTicketList();
				activeTicketList.addTicket(t);
			}
		}
		isChanged = true;
	}
	
	/**
	 * Method edits the Ticket in the currentCategory to have the given name, description, and boolean for active
	 * @param idx index of the Ticket in the list
	 * @param ticketName name of the Ticket
	 * @param ticketDescription description of the Ticket
	 * @param active whether the Ticket is active
	 */
	public void editTicket(int idx, String ticketName, String ticketDescription, boolean active) {
		if (currentCategory.getClass() == Category.class) {
			Ticket edit = currentCategory.getTickets().get(idx);
			edit.setTicketName(ticketName);
			edit.setTicketDescription(ticketDescription);
			edit.setActive(active);
			if (edit.isActive()) {
				getActiveTicketList();
				activeTicketList.addTicket(edit);
			}
		}
		isChanged = true;
	}
}
