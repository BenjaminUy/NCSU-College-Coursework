package edu.ncsu.csc316.cleaning.ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.cleaning.manager.ReportManager;

/**
 * This class allows the user to interact with the CleaningManager program by specifying the input
 * files for the room records and cleaning events log. Afterward, the program offers the required
 * functionality like getting a report of the most frequently cleaned rooms, the cleaning events of
 * each room, and a report on the estimated remaining bag life.
 * @author Benjamin Uy
 */
public class CleaningManagerUI {
	
	/**
	 * Starts the CleaningManager program
	 * @param args from command line
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to CleaningManager!");
		
		ReportManager r = null;
		boolean validFiles = false;
		
		do {
			System.out.println("\nPlease input the pathway to the file containing info about each room.");
			System.out.println("If you wish to quit CleaningManager, enter \"1\"");
			
			String roomFile = in.nextLine();
			if (roomFile.equals("1")) {
				in.close();
				System.exit(1);
			}
			
			System.out.println("\nNext, please input the file pathway to the input file containing logs of cleaning events.");
			System.out.println("If you wish to quit CleaningManager, enter \"1\"");
			
			String cleanFile = in.nextLine();
			if (cleanFile.equals("1")) {
				in.close();
				System.exit(1);
			}
			
			try {
				r = new ReportManager(roomFile, cleanFile);
				validFiles = true;
			} catch (FileNotFoundException e) {
				System.out.println("\nAt least one of the files you specified does not exist.");
			}
		} while (!validFiles);
		
		String userResponse = "";
		do {
			System.out.println("\nPlease input a number between 1-4, inclusive");
			System.out.println("1 - Quit CleaningManager");
			System.out.println("2 - View most frequently cleaned rooms");
			System.out.println("3 - View a report of cleanings by room");
			System.out.println("4 - View estimated remaining bag life");
			
			userResponse = in.nextLine();
			
			if (userResponse.equals("2")) {
				System.out.println("\nEnter the number of rooms you wish to include in the report");
				int a = Integer.parseInt(in.nextLine());
				System.out.println("\n" + r.getFrequencyReport(a));
			} else if (userResponse.equals("3")) {
				System.out.println("\n" + r.getRoomReport());
			} else if (userResponse.equals("4")) {
				System.out.println("\nEnter the date and time the vacuum bag was last replaced in the format \"MM/DD/YYYY HH:MM:SS\"");
				String b = in.nextLine();
				System.out.println("\n" + r.getVacuumBagReport(b));
			}
			
		} while (!userResponse.equals("1"));
		
		in.close();
		System.exit(1);
	}
}
