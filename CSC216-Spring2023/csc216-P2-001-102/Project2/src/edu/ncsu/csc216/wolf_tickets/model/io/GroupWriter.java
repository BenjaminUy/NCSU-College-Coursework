package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;

/**
 * Method handles file output in the case of writing Group objects to text files in proper formatting
 * @author Benjamin Uy
 */
public class GroupWriter {

	/**
	 * Method writes to the given file with the given group name and categories list in proper formatting.
	 * @param groupFile file to write the Group to
	 * @param groupName name of the Group
	 * @param categories ISortedList of Category objects
	 * @throws IllegalArgumentException if there are any errors or exceptions caught while printing to the file
	 */
	public static void writeGroupFile(File groupFile, String groupName, ISortedList<Category> categories) {
		try {
			PrintStream fileWriter = new PrintStream(groupFile);
			fileWriter.println("! " + groupName);						// FileWriter first prints the name of the Group
			for (int i = 0; i < categories.size(); i++) {
				Category c = categories.get(i);
				fileWriter.println("# " + c.getCategoryName() + "," + c.getCompletedCount());	// FileWriter then prints out the Category
				ISwapList<Ticket> categoryTickets = c.getTickets();									// Get list of Tickets from the Category
					for (int k = 0; k < categoryTickets.size(); k++) {
						fileWriter.println(categoryTickets.get(k).toString());						// FileWriter prints out the Tickets as strings
					}
			}
			fileWriter.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
