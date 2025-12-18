package edu.ncsu.csc316.cleaning.manager;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.cleaning.dsa.DataStructure;

/**
 * Class tests the functionality of ReportManager
 * @author Benjamin Uy
 */
public class ReportManagerTest {

	private String cleanEvents = "input/cleaningEvents.txt";
	private String cleanEmpty = "input/cleaningEmpty.txt";
	private String cleanSameDate = "input/cleaningSameDate.txt";
	private String roomEmpty = "input/roomEmpty.txt";
	private String roomInfo = "input/roomInfo.txt";
	private ReportManager r;
	
	/**
	 * Tests the behavior of the ReportManager constructors using invalid input
	 */
	@Test
	public void testReportManagerInvalid() {
		try {
			new ReportManager("doesNotExist", "doesNotExist");
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new ReportManager(roomInfo, "doesNotExist.txt");
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new ReportManager("doesNotExist", cleanEvents);
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new ReportManager("doesNotExist", "doesNotExist", DataStructure.UNORDEREDLINKEDMAP);
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new ReportManager(roomInfo, "doesNotExist.txt", DataStructure.UNORDEREDLINKEDMAP);
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
		
		try {
			new ReportManager("doesNotExist", cleanEvents, DataStructure.UNORDEREDLINKEDMAP);
			fail("Exception should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
		}
	}
	
	/**
	 * Tests the behavior of the ReportManager constructors using valid input
	 */
	@Test
	public void testReportManagerValid() {
		try {
			r = new ReportManager(roomInfo, cleanEvents);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		assertNotNull(r);
		
		try {
			r = new ReportManager(roomInfo, cleanEvents, DataStructure.UNORDEREDLINKEDMAP);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		assertNotNull(r);
	}
	
	/**
	 * Tests the functionality of the getVacuumBagReport method
	 */
	@Test
	public void testGetVacuumBagReport() {
		try {
			r = new ReportManager(roomInfo, cleanEvents);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		
		String message = r.getVacuumBagReport("01/01/1999 00:00:00");
		assertEquals("Vacuum Bag Report (last replaced 01/01/1999 00:00:00) [\n" + "   Bag is overdue for replacement!\n]", message);
		
		message = r.getVacuumBagReport("whoops");											// Input random String
		assertEquals("Date & time must be in the format: MM/DD/YYYY HH:MM:SS", message);
		
		message = r.getVacuumBagReport("0/120/2001 01:09:00");								// Misplaced numbers
		assertEquals("Date & time must be in the format: MM/DD/YYYY HH:MM:SS", message);
		
		message = r.getVacuumBagReport("01:09:00 01/01/1999");								// Switched order of time and date
		assertEquals("Date & time must be in the format: MM/DD/YYYY HH:MM:SS", message);
		
		message = r.getVacuumBagReport("06/01/2021 13:39:01");
		assertEquals("Vacuum Bag Report (last replaced 06/01/2021 13:39:01) [\n" + "   Bag is due for replacement in 5280 SQ FT\n]", message);
		
		message = r.getVacuumBagReport("06/01/2021 13:39:00");
		assertEquals("Vacuum Bag Report (last replaced 06/01/2021 13:39:00) [\n" + "   Bag is due for replacement in 5149 SQ FT\n]", message);
		
		message = r.getVacuumBagReport("05/21/2021 09:16:32");
		assertEquals("Vacuum Bag Report (last replaced 05/21/2021 09:16:32) [\n" + "   Bag is due for replacement in 3140 SQ FT\n]", message);
	}
	
	/**
	 * Tests the functionality of getFrequencyReport
	 */
	@Test
	public void testGetFrequencyReport() {
		try {
			r = new ReportManager(roomEmpty, cleanEmpty);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		assertNotNull(r);
		
		// Non-positive input
		assertEquals("Number of rooms must be greater than 0.", r.getFrequencyReport(0));
		assertEquals("Number of rooms must be greater than 0.", r.getFrequencyReport(-1));
		
		// Case where the room and cleaning log are both empty
		assertEquals("No rooms have been cleaned.", r.getFrequencyReport(1));
		
		try {
			r = new ReportManager(roomInfo, cleanEmpty);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		// Case where the cleaning log is empty
		assertEquals("No rooms have been cleaned.", r.getFrequencyReport(1));
		
		try {
			r = new ReportManager(roomEmpty, cleanEvents);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		// Case where the room log is empty
		assertEquals("No rooms have been cleaned.", r.getFrequencyReport(1));
		
		try {
			r = new ReportManager(roomInfo, cleanEvents);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		String message = r.getFrequencyReport(1);
		assertEquals("Frequency of Cleanings [\n   Living Room has been cleaned 6 times\n]", message);
	}
	
	/**
	 * Tests the functionality of getRoomReport
	 */
	@Test
	public void testGetRoomReport() {
		try {
			r = new ReportManager(roomInfo, cleanEvents);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		
		assertEquals("Room Report [\n   Dining Room was cleaned on [\n      05/31/2021 09:27:45\n" + 
				"      05/23/2021 18:22:11\n      05/21/2021 09:16:33\n   ]\n   Foyer was cleaned on [\n" +
				"      05/01/2021 10:03:11\n   ]\n   Guest Bathroom was cleaned on [\n      05/17/2021 04:37:31\n" +
				"      05/08/2021 07:01:51\n   ]\n   Guest Bedroom was cleaned on [\n      05/23/2021 11:51:19\n" +
				"      05/13/2021 22:20:34\n   ]\n   Kitchen was cleaned on [\n      (never cleaned)\n   ]\n" +
				"   Living Room was cleaned on [\n      05/30/2021 10:14:41\n      05/28/2021 17:22:52\n" +
				"      05/12/2021 18:59:12\n      05/11/2021 19:00:12\n      05/09/2021 18:44:23\n      05/03/2021 17:22:52\n" +
				"   ]\n   Office was cleaned on [\n      06/01/2021 13:39:01\n   ]\n]", r.getRoomReport());
		
		try {
			r = new ReportManager(roomEmpty, cleanEmpty);
		} catch (Exception e) {
			assertTrue(e instanceof FileNotFoundException);
			fail("Exception should not be thrown");
		}
		assertEquals("No rooms have been cleaned.", r.getRoomReport());
		
		try {
			r = new ReportManager(roomInfo, cleanSameDate);
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
		
		assertEquals("Room Report [\n   Dining Room was cleaned on [\n      06/01/2021 13:39:01\n   ]\n" + 
				"   Foyer was cleaned on [\n      (never cleaned)\n   ]\n   Guest Bathroom was cleaned on [\n      (never cleaned)\n" +
				"   ]\n   Guest Bedroom was cleaned on [\n      (never cleaned)\n   ]\n   Kitchen was cleaned on [\n      (never cleaned)\n   ]\n" +
				"   Living Room was cleaned on [\n      06/01/2021 13:39:01\n      06/01/2021 13:39:01\n   ]\n   Office was cleaned on " + 
				"[\n      06/01/2021 13:39:01\n   ]\n]", r.getRoomReport());
		
	}
}
