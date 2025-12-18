package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Class responsible for writing Position s and their Application s to a provided file.
 * Contains one public method that accomplishes the above using a given String for file
 * name and a given List of Positions.
 * @author Benjamin Uy
 */
public class PositionWriter {

	/**
	 * Method writes List of Positions to file with the given fileName. 
	 * @param fileName name of the file
	 * @param positions List of Positions
	 * @throws IllegalArgumentException if method is unable to write to the file, specifically
	 * 		in the case that a FileNotFoundException is caught
	 */
	public static void writePositionsToFile(String fileName, List<Position> positions) {
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));
			for (Position p : positions) {
				// Check if the Position has any applications. If there are none, don't print to file
				if (p.getApplications().size() != 0) {
					fileWriter.println(p.toString());		// FileWriter first prints out the position as a string
					List<Application> positionApplications = p.getApplications();		// Get list of applications from the position
					for (int i = 0; i < positionApplications.size(); i++) {
						fileWriter.println(positionApplications.get(i).toString());		// FileWriter prints out the applications as strings
					}
				}
			}
			fileWriter.close();     
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}   
	}
	
}
