package edu.ncsu.csc316.cleaning.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Class tests the behaviors of CleaningManager's methods
 * @author Benjamin Uy
 */
public class CleaningManagerTest {
	
	private String cleanEvents = "input/cleaningEvents.txt";
	private String cleanEvents2 = "input/cleaningEvents2.txt";
	private String cleanEventsSorted = "input/cleaningEventsSorted.txt";
	private String cleanSameDate = "input/cleaningSameDate.txt";
	private String roomInfo = "input/roomInfo.txt";
	private CleaningManager c;
	
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	
	/**
	 * Tests the behavior of the CleaningManager constructors using invalid input
	 */
	@Test
	public void testCleaningManagerInvalid() {
		try {
			new CleaningManager("doesNotExist", "doesNotExist");
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new CleaningManager(roomInfo, "doesNotExist.txt");
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new CleaningManager("doesNotExist", cleanEvents);
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new CleaningManager("doesNotExist", "doesNotExist", DataStructure.UNORDEREDLINKEDMAP);
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new CleaningManager(roomInfo, "doesNotExist.txt", DataStructure.UNORDEREDLINKEDMAP);
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new CleaningManager("doesNotExist", cleanEvents, DataStructure.UNORDEREDLINKEDMAP);
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
	}
	
	/**
	 * Tests the behavior of the CleaningManager constructors using valid input
	 */
	@Test
	public void testCleaningManagerValid() {
		try {
			c = new CleaningManager(roomInfo, cleanEvents);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		assertNotNull(c);
		
		try {
			c = new CleaningManager(roomInfo, cleanEvents, DataStructure.UNORDEREDLINKEDMAP);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		assertNotNull(c);
	}
	
	/**
	 * Tests the behavior of the getCoverageSince method
	 */
	@Test
	public void testGetCoverageSince() {
		try {
			c = new CleaningManager(roomInfo, cleanEvents2);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		
		assertEquals(0, c.getCoverageSince(null));
		
		LocalDateTime t = LocalDateTime.parse("01/01/1999 00:00:00", DATE_TIME_FORMAT);
		// Inputing a date that is before all previous cleaning events
		assertEquals(1714, c.getCoverageSince(t));
		
		// Inputting a date that is at the same time as the last cleaning event
		t = LocalDateTime.parse("06/01/2021 13:39:01", DATE_TIME_FORMAT);
		assertEquals(0, c.getCoverageSince(t));
		
		// Inputting a date that is right before the last cleaning event
		t = LocalDateTime.parse("06/01/2021 13:39:00", DATE_TIME_FORMAT);
		assertEquals(131, c.getCoverageSince(t));
		
		// Using larger input file with more clean events
		try {
			c = new CleaningManager(roomInfo, cleanEventsSorted, DataStructure.UNORDEREDLINKEDMAP);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		
		t = LocalDateTime.parse("01/01/1999 00:00:00", DATE_TIME_FORMAT);
		// Inputing a date that is before all previous cleaning events
		assertEquals(5727, c.getCoverageSince(t));
		
		t = LocalDateTime.parse("05/21/2021 09:16:32", DATE_TIME_FORMAT);
		// Inputting a date that is in between multiple cleaning dates
		assertEquals(2140, c.getCoverageSince(t));
	}
	
	/**
	 * Tests the output of the getEventsByRoom method
	 */
	@Test
	public void testGetEventsByRooms() {
		try {
			c = new CleaningManager(roomInfo, cleanEvents2);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
		
		Map<String, List<CleaningLogEntry>> map = c.getEventsByRoom();
		assertEquals(7, map.size());
		
		List<CleaningLogEntry> list = map.get("Foyer");
		assertEquals(0, list.size());
		
		list = map.get("Dining Room");
		assertEquals(0, list.size());
		
		list = map.get("Guest Bathroom");
		assertEquals(0, list.size());
		
		list = map.get("Guest Bedroom");
		assertEquals(0, list.size());
		
		list = map.get("Kitchen");
		assertEquals(0, list.size());
		
		list = map.get("Living Room");
		assertEquals(2, list.size());
		
		list = map.get("Office");
		assertEquals(1, list.size());
		
		try {
			c = new CleaningManager(roomInfo, cleanEventsSorted);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
		
		map = c.getEventsByRoom();
		assertEquals(7, map.size());
		
		list = map.get("Foyer");
		assertEquals(1, list.size());
		
		list = map.get("Dining Room");
		assertEquals(3, list.size());
		
		list = map.get("Guest Bathroom");
		assertEquals(2, list.size());
		
		list = map.get("Guest Bedroom");
		assertEquals(2, list.size());
		
		list = map.get("Kitchen");
		assertEquals(0, list.size());
		
		list = map.get("Living Room");
		assertEquals(6, list.size());
		
		list = map.get("Office");
		assertEquals(1, list.size());
		
		
		// Using file where all cleaning events have the same time stamp
		try {
			c = new CleaningManager(roomInfo, cleanSameDate);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
		
		map = c.getEventsByRoom();
		assertEquals(7, map.size());
		
		list = map.get("Foyer");
		assertEquals(0, list.size());
		
		list = map.get("Dining Room");
		assertEquals(1, list.size());
		
		list = map.get("Guest Bathroom");
		assertEquals(0, list.size());
		
		list = map.get("Guest Bedroom");
		assertEquals(0, list.size());
		
		list = map.get("Kitchen");
		assertEquals(0, list.size());
		
		list = map.get("Living Room");
		assertEquals(2, list.size());
		
		list = map.get("Office");
		assertEquals(1, list.size());
	}
}