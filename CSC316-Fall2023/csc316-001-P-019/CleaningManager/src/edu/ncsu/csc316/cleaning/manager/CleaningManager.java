package edu.ncsu.csc316.cleaning.manager;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Comparator;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.data.RoomRecord;
import edu.ncsu.csc316.cleaning.dsa.Algorithm;
import edu.ncsu.csc316.cleaning.dsa.DSAFactory;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.cleaning.io.InputReader;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * CleaningManager deals with functionality of getting a collection of cleaning events by room
 * and calculating the area coverage of the vacuum after a specific time. An instance of this class is
 * used in ReportManager. For fields, CleaningManager has a List of RoomRecord objects, a List of CleaningLogEntry objects,
 * and a Map of entries storing Strings and RoomRecords.
 * @author Benjamin Uy
 */
public class CleaningManager {
	
	private List<RoomRecord> roomRecords;
	private List<CleaningLogEntry> cleaningLog;
	private Map<String, RoomRecord> mapOfRooms;
	
	/**
	 * Constructor method of CleaningManager that allows the user the specify the file pathways for the room and cleaning log
	 * along with the type of map that the software uses
	 * @param pathToRoomFile pathway to the file containing room information
	 * @param pathToLogFile pathway to the file containing cleaning log events
	 * @param mapType specific map that the software will use
	 * @throws FileNotFoundException if either readLogFile or readRoomFile cannot locate the file specified in the parameters
	 */
    public CleaningManager(String pathToRoomFile, String pathToLogFile, DataStructure mapType) throws FileNotFoundException {
        DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.MERGESORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.RADIX_SORT);
        DSAFactory.setMapType(mapType);
       
        cleaningLog = InputReader.readLogFile(pathToLogFile);
        roomRecords = InputReader.readRoomFile(pathToRoomFile);
        
        // Private method to sort the cleaning log in chronological order (oldest to newest)
        // By default, whenever a new instance of CleaningManager is created, the cleaningLog is automatically sorted
        cleaningLog = sortByDate(cleaningLog);
        
        // Private method to sort the room records in alphabetical order (A-Z)
        roomRecords = sortByAlphabet(roomRecords);
       
        // Create a Map that contains the room id and roomRecord objects
        mapOfRooms = DSAFactory.getMap(null);
        for (int i = 0; i < roomRecords.size(); i++) {
        	mapOfRooms.put(roomRecords.get(i).getRoomID(), roomRecords.get(i));
        }
    }

	/**
     * Alternative constructor method of CleaningManager where the user does not specify the type of map used in the program.
     * By default, the method sets the map type to a search table.
     * @param pathToRoomFile pathway to the file containing room information
     * @param pathToLogFile pathway to the file containing cleaning log events
     * @throws FileNotFoundException if either readLogFile or readRoomFile cannot locate the file
     */
    public CleaningManager(String pathToRoomFile, String pathToLogFile) throws FileNotFoundException {
        this(pathToRoomFile, pathToLogFile, DataStructure.SKIPLIST);
    }
    
    /**
     * Custom comparator used to help compare and sort CleaningLogEntry objects
     * @author Benjamin Uy
     */
    private class CleaningLogEntryComparator implements Comparator<CleaningLogEntry> {

    	/**
    	 * Method first sorts CleaningLogEntry objects by their time-stamp, then by their room ID
    	 * @return integer representation of o1's 'position' relative to o2
    	 */
		@Override
		public int compare(CleaningLogEntry o1, CleaningLogEntry o2) {
			if (!o1.getTimestamp().isEqual(o2.getTimestamp())) {
				return o1.getTimestamp().compareTo(o2.getTimestamp());
			} else if (!o1.getRoomID().equalsIgnoreCase(o2.getRoomID())) {
				return o1.getRoomID().compareTo(o2.getRoomID());
			}
			return 0;
		}	
    }
    
    /**
     * Private helper method that sorts the given list of CleaningLogEntry objects in ascending chronological time (least recent at the beginning
     * of the list and most recent at the end)
     * @param cleaningLog list of CleaningLogEntry objects to sort.
     * @return sorted list of CleaningLogEntrys in ascending chronological order
     */
	private List<CleaningLogEntry> sortByDate(List<CleaningLogEntry> cleaningLog) {
        Sorter<CleaningLogEntry> cleanArraySorter = DSAFactory.getComparisonSorter(new CleaningLogEntryComparator());
        CleaningLogEntry[] cleanArray = new CleaningLogEntry[cleaningLog.size()];
        
        // Iterate through cleaningLog and store CleaningLogEntry objects in the array
        for (int i = 0; i < cleaningLog.size(); i++) {
        	cleanArray[i] = cleaningLog.get(i);
        }
        // Use the custom comparator sorter to sort the array
        cleanArraySorter.sort(cleanArray);
        
        List<CleaningLogEntry> updatedLog = DSAFactory.getIndexedList(); 
        for (int k = 0; k < cleanArray.length; k++) {
        	updatedLog.addLast(cleanArray[k]);
        }  
        return updatedLog;
	}
	
	/**
	 * Private helper method that sorts the given list of RoomRecord objects in alphabetical order (A-Z)
	 * @param roomRecords list of RoomRecord objects to sort
	 * @return sorted list of RoomRecords in alphabetical order
	 */
	private List<RoomRecord> sortByAlphabet(List<RoomRecord> roomRecords) {
		Sorter<RoomRecord> roomSorter = DSAFactory.getComparisonSorter(null);
		RoomRecord[] roomArray = new RoomRecord[roomRecords.size()];
		
		// Iterate through roomRecords and store roomRecord objects in array
		for (int i = 0; i < roomRecords.size(); i++) {
			roomArray[i] = roomRecords.get(i);		
		}
		
		roomSorter.sort(roomArray);
		
        List<RoomRecord> updatedRooms = DSAFactory.getIndexedList(); 
        for (int k = 0; k < roomArray.length; k++) {
        	updatedRooms.addLast(roomArray[k]);
        }  
        return updatedRooms;
	}

    /**
     * Method returns a Map of entries containing the String id of the rooms and a list of CleaningLogEntry objects for that room.
     * @return Map of entries with the key being the room id and the value being a list of CleaningLogEntries for that room.
     */
    public Map<String, List<CleaningLogEntry>> getEventsByRoom() {    
    	
    	Map<String, List<CleaningLogEntry>> roomsAndCleaning = DSAFactory.getMap(null);
    	
    	for (int i = 0; i < roomRecords.size(); i++) {
    		roomsAndCleaning.put(roomRecords.get(i).getRoomID(), DSAFactory.getIndexedList());
    	}
    	
    	for (int k = cleaningLog.size() - 1; k >= 0; k--) {
			CleaningLogEntry c = cleaningLog.get(k);
			List<CleaningLogEntry> events = roomsAndCleaning.get(c.getRoomID());
			events.addLast(c);
			
			roomsAndCleaning.put(c.getRoomID(), events);
    	}
        
        return roomsAndCleaning;
    }

    /**
     * Method gets the total area (in square feet) that the vacuum has covered since its last
     * replacement indicated by the given time parameter. 
     * @param time parameter representing the last time that vacuum bag was replaced
     * @return integer representing the total area in square feet that the vacuum has cleaned
     */
    public int getCoverageSince(LocalDateTime time) {
    	if (time == null || roomRecords.size() == 0 || cleaningLog.size() == 0) {
    		return 0;
    	}
    	int coveredSquareFeet = 0;
    	
    	// Iterate through the cleaningLog and check each CleaningLogEntry to see if it
	    // happened after the given time. Since we know the cleaningLog is sorted, if we
    	// find an entry whose time stamp is before the date, then we can stop the search
	    for (int i = cleaningLog.size() - 1; i >= 0; i--) {
	    	CleaningLogEntry cleanEvent = cleaningLog.get(i);
	    	   
	    	// If the cleanEvent's time stamp is after time, then 
	    	if (cleanEvent.getTimestamp().compareTo(time) > 0) {
	    		RoomRecord room = mapOfRooms.get(cleanEvent.getRoomID());
	    		if (room != null) {
	    			coveredSquareFeet = coveredSquareFeet + (room.getLength() * room.getWidth() * 
	    					cleanEvent.getPercentCompleted()) / 100;
	    		}
	    	} else {
	    		i = -1;	// Stop the search if a time stamp is found to be before
	    	}
	    }
	    return coveredSquareFeet;
    }
}
