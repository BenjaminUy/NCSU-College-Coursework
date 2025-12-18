package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Class processes a file containing position and application information and
 * creates a List of Position s and their associated Application s.
 * @author Benjamin Uy
 */
public class PositionReader {

	/**
	 * Method processes a file with the given fileName and returns an ArrayList of Positions
	 * @param fileName name of the file
	 * @return ArrayList containing Positions
	 * @throws IllegalArgumentException if a file cannot be read or found
	 */
	public static ArrayList<Position> readPositionFile(String fileName) {
		try { 
			Scanner fileReader = new Scanner(new FileInputStream(fileName));	// Create new Scanner to read file
			String fullText = "";
			ArrayList<Position> list = new ArrayList<Position>();	// Create empty ArrayList of Positions
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				fullText = fullText + line + "\n";				// Add the read line with a new line character
			}
			fileReader.close();
		
			if (fullText.charAt(0) != '#') {					// Check the first char of the read text file
				return list;									// Return empty list if first char of the text file is not '#'				
			}
			
			Scanner fullTextReader = new Scanner(fullText);		// Create new Scanner to read String of the full text
			fullTextReader.useDelimiter("\\r?\\n?[#]");		// Break the full text into positions
			while (fullTextReader.hasNext()) {
				String positionToken = fullTextReader.next().trim();	
				Position p = processPosition(positionToken);	// Call method that will process the position text
				if (p != null) {
					list.add(p);									// and output a Position to be added to list
				}
			}
			fullTextReader.close();
			
			return list;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file " + fileName);
		}
	}
	
	/**
	 * Method is given the whole text file as a String object and returns a Position after processing.
	 * Also breaks the text file into lines and calls processPositionLine() and processApplication() 
	 * to assist in processing
	 * @param positionText String containing the contents of a text file
	 * @return Position representing a job position in WolfHire
	 */
	private static Position processPosition(String positionText) {
		Scanner scan = new Scanner(positionText);
		String positionLine = scan.nextLine();
		Position p = processPositionLine(positionLine);
		
		if (p == null) {	// Check if Position returned from method is null
			scan.close();
			return null;	// If so, the Position line was invalid, so the Application list should not be created
		}

		scan.useDelimiter("\\r?\\n?[*]");
		while (scan.hasNext()) {
			String applicationLine = scan.next().trim();
			Application a = processApplication(applicationLine);
			if (null != a && null == p.getApplicationById(a.getId())) {			// Check to ensure there are no applications of a position with same id
				p.addApplication(a);
			}
		}
		// If there are no Applications in the Position, then the Position is invalid
		if (p.getApplications().size() == 0) {
			scan.close();
			return null;
		}
		scan.close();
		return p;
	}
	
	/**
	 * Method receives a line from processPosition() and returns a Position
	 * @param positionLine String that contains information about a Position
	 * @return Position representing a job position in WolfHire
	 */
	private static Position processPositionLine(String positionLine) {
		String positionInfoLine = positionLine;	// Remove the # symbol so that scanner properly reads tokens for position info
		Scanner scan = new Scanner(positionInfoLine);
		scan.useDelimiter(",");
		
		String positionName = null;
		int hoursPerWeek = 0;
		int payRate = 0;
		String placeholder = null;	// Placeholder variable used for containing read in Strings
		
		int tokens = 0;
		while (tokens < 3) {	// String should have three tokens
			if (scan.hasNext()) {
				switch (tokens) {
				case 0:
					positionName = scan.next();
					if ("".equals(positionName)) {
						positionName = null;
					}
					tokens++;
					break;
				case 1:
					placeholder = scan.next();
					if ("".equals(placeholder)) {
						hoursPerWeek = 0;
						break;
					}
					hoursPerWeek = Integer.parseInt(placeholder);
					tokens++;
					break;
				case 2:
					placeholder = scan.next();
					if ("".equals(placeholder)) {
						payRate = 0;
						break;
					}
					payRate = Integer.parseInt(placeholder);
					tokens++;
					break;
				default: 
					return null;
				}
			} else {
				scan.close();
				return null;
			}
		}
		if (scan.hasNext()) {
			scan.close();
			return null;
		}
		Position p = new Position(positionName, hoursPerWeek, payRate);
		scan.close();
		return p;
	}
	
	/**
	 * Method receives a line from processPosition() and returns an Application
	 * @param applicationLine String that contains information about an Application
	 * @return Application representing an applicant for a Position in WolfHire
	 */
	private static Application processApplication(String applicationLine) {
		Scanner scan = new Scanner(applicationLine);
		scan.useDelimiter(",");
		
		int tokens = 0;	// Counter for number of tokens in given string
		
		int id = 0;		
		String state = null;
		String firstName = null;
		String surname = null;
		String unityId = null;
		String reviewer = null;
		String note = null;
		
		// At a minimum, there should be five tokens for id, state, first name, surname, and unity id
		while (tokens < 5) {
			if (scan.hasNext()) {
				switch (tokens) {
				case 0:
					try {
						id = Integer.parseInt(scan.next());
					} catch (NumberFormatException e) {
						scan.close();
						return null;
					}
					tokens++;
					break;
				case 1:
					state = scan.next();
					tokens++;
					break;
				case 2:
					firstName = scan.next();
					tokens++;
					break;
				case 3:
					surname = scan.next();
					tokens++;
					break;
				case 4:
					unityId = scan.next();
					tokens++;
					break;
				default:
					return null;
				}
			} else {
				scan.close();
				return null;
			}
		}
		
		if (scan.hasNext()) {
			reviewer = scan.next();
			if ("".equals(reviewer)) {
				reviewer = null;
			}
		}
		if (scan.hasNext()) {
			note = scan.next();
		}
		if (scan.hasNext()) {
			scan.close();
			return null;
		}
	
		scan.close();
		try {
			Application a = new Application(id, state, firstName, surname, unityId, reviewer, note);
			return a;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}

