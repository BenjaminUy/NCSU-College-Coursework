package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Method handles reading files and creating Group objects from valid formatted files
 * @author Benjamin Uy
 */
public class GroupReader {
	
	/**
	 * Method receives a File with the file name to read from. 
	 * @param groupFile File to read from 
	 * @return Group object based on information in the given File
	 * @throws IllegalArgumentException if the file does not exist
	 * @throws IllegalArgumentException if the first character of the given File is not '!'
	 */
	public static Group readGroupFile(File groupFile) {
		try {
			Scanner fileReader = new Scanner(groupFile);						// Create new Scanner to read file
			String fullText = "";
			String groupName = "";
			boolean firstLineIn = false;
			while (fileReader.hasNextLine()) {									// Read the full file and store as a single String
				String line = fileReader.nextLine();
				if (!firstLineIn) {
					if (line.charAt(0) != '!') {								// Check the first char of the file is '!'		
						fileReader.close();
						throw new IllegalArgumentException("Unable to load file.");
					}
					groupName = line.substring(2);
					firstLineIn = true;
				}
				fullText = fullText + line + "\n";
			}
			fileReader.close();
			
			Group g = new Group(groupName);										// Construct a new Group object
			
			Scanner fullTextReader = new Scanner(fullText);
			fullTextReader.useDelimiter("\\r?\\n?[#]");
			while (fullTextReader.hasNext() ) {
				String categoryToken = fullTextReader.next().trim();
				Category c = processCategory(categoryToken);
				if (c != null) {
					g.addCategory(c);
				}
			}
			fullTextReader.close();
			
			g.setCurrentCategory("Active Tickets");
			g.setChanged(false);
			
			return g;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}
	
	/**
	 * Method assists readGroupFile by receiving a String and returning a Category object
	 * @param categoryText String line containing information about a Category
	 * @return Category to add to the Group
	 */
	private static Category processCategory(String categoryText) {			// Not sure if the return is supposed to be Type or Category
		Scanner scan = new Scanner(categoryText);
		
		String categoryLine = scan.nextLine();								// Create scanner to read the first line of categoryText
		Scanner categoryLineReader = new Scanner(categoryLine);
		categoryLineReader.useDelimiter(",");
		
		String categoryName = null;
		int completedNumber = -1;		// Initialize to an invalid number, should completedNumber not be found
		String placeholder = null;
		
		// Read the line containing information about the category
		int tokens = 0;
		while (tokens < 2) {
			if (categoryLineReader.hasNext()) {
				switch (tokens) {
				case 0:
					categoryName = categoryLineReader.next();
					if ("".equals(categoryName)) {
						categoryName = null;
					}
					tokens++;
					break;
				case 1:
					placeholder = categoryLineReader.next();
					if ("".equals(placeholder)) {
						completedNumber = -1;
						break;
					}
					completedNumber = Integer.parseInt(placeholder);
					tokens++;
					break;
				default:
					scan.close();
					return null;
				}
			} else {
				categoryLineReader.close();
				scan.close();
				return null;
			}
		}
		// If category has more tokens, the line is invalid
		if (categoryLineReader.hasNext()) {
			categoryLineReader.close();
			scan.close();
			return null;
		}
		
		Category c = null;
		categoryLineReader.close();
		
		try {
			c = new Category(categoryName, completedNumber);			// Attempt to construct a new Category object with given parameters
		} catch (IllegalArgumentException e) {
			scan.close();
			return null;
		}
		
		if (c != null) {
			scan.useDelimiter("\\r?\\n?[*]");
			while (scan.hasNext()) {
				String ticketLine = scan.next().trim();
				Ticket t = processTicket(c, ticketLine);
				if (null != t) {
					c.addTicket(t);
				}
			}
		}
		scan.close();
		
		return c;
		
	}
	
	/**
	 * Method assists processCategory by receiving an AbstractCategory and a string; returns
	 * a Ticket object
	 * @param category object that can contain multiple Ticket objects
	 * @param ticketText String line containing information about a Ticket
	 * @return Ticket object that is processed from the given String
	 */
	private static Ticket processTicket(AbstractCategory category, String ticketText) {
		Scanner ticketScan = new Scanner(ticketText);
		
		int tokens = 0;
		
		String nameAndActive = null;
		String description = "";
		boolean active = false;
		
		while (ticketScan.hasNext()) {
			switch (tokens) {
			case 0:
				nameAndActive = ticketScan.nextLine();
				tokens++;
				ticketScan.useDelimiter("\n");		// Scanner will stop before the newline character
				if (nameAndActive.substring(nameAndActive.length() - 7).equals(",active")) {
					tokens++;
					active = true;
					nameAndActive = nameAndActive.substring(0, nameAndActive.length() - 7);
				}
				break;
			default:
				description = description + ticketScan.nextLine();
				tokens++;
				break;
			}
		}
		ticketScan.close();
		
		Ticket t = null;
		try {
			t = new Ticket(nameAndActive, description, active);		// Attempt to create a new Ticket object
		} catch (IllegalArgumentException e) {
			return null;
		}
		return t;
	}

}
