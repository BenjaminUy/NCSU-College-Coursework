package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;

/**
 * Class tests GroupReader.java
 * @author Benjamin Uy
 */
class GroupReaderTest { 
	
	/**
	 * Tests GroupReader readGroupFile with reading wolf_tickets_test_file.txt
	 */
	@Test
	void testReadGroupFileWolfTickets() {
		File f = new File("test-files/wolf_tickets_test_file.txt");
		Group g = GroupReader.readGroupFile(f);	
		
		assertEquals(5, g.getCurrentCategory().getTickets().size());
	}

	/**
	 * Tests GroupReader readGroupFile() with invalid files and smallest valid file
	 */
	@Test
	void testReadGroupFile0() {
		File f = new File("test-files/group0.txt");
		Group g = GroupReader.readGroupFile(f);			// Read smallest valid test file with only Group name
		
		assertEquals("CSC IT", g.getGroupName());
		assertEquals(1, g.getCategoriesNames().length);
		
		// Test inputs that would cause errors
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> GroupReader.readGroupFile(new File(".")));
		assertEquals("Unable to load file.", e.getMessage());
		
		e = assertThrows(IllegalArgumentException.class,
				() -> GroupReader.readGroupFile(new File("test-files/invalid_first_line.txt")));
		assertEquals("Unable to load file.", e.getMessage());
	}
	
	/**
	 * Tests GroupReader readGroupFile() with reading group1 file
	 */
	@Test
	void testReadGroupFile1() {
		File f = new File("test-files/group1.txt");
		Group g = GroupReader.readGroupFile(f);
		
		assertEquals("CSC IT", g.getGroupName());
		assertEquals(4, g.getCategoriesNames().length);
		
		g.setCurrentCategory("Active Tickets");
		assertEquals(5, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Web");
		assertEquals(5, g.getCurrentCategory().getCompletedCount());
		assertEquals(1, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Classroom Tech");
		assertEquals(10, g.getCurrentCategory().getCompletedCount());
		assertEquals(4, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Desktop");
		assertEquals(17, g.getCurrentCategory().getCompletedCount());
		assertEquals(2, g.getCurrentCategory().getTickets().size());
	}
	
	/**
	 * Tests GroupReader readGroupFile() with reading group2 file
	 */
	@Test
	void testReadGroupFile2() {
		File f = new File("test-files/group2.txt");
		Group g = GroupReader.readGroupFile(f);
		
		assertEquals("CSC IT", g.getGroupName());
		assertEquals(4, g.getCategoriesNames().length);
		
		// Check that contents of the Web category are correct
		g.setCurrentCategory("Active Tickets");
		assertEquals(0, g.getCurrentCategory().getCompletedCount());
		assertEquals(4, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Web");
		assertEquals(5, g.getCurrentCategory().getCompletedCount());
		assertEquals(0, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Classroom Tech");
		assertEquals(10, g.getCurrentCategory().getCompletedCount());
		assertEquals(4, g.getCurrentCategory().getTickets().size());
		
		g.setCurrentCategory("Desktop");
		assertEquals(17, g.getCurrentCategory().getCompletedCount());
		assertEquals(2, g.getCurrentCategory().getTickets().size());
	}

	/**
	 * Tests readGroupFile with reading group3, an invalid records file not starting with '!'
	 */
	@Test
	void testReadGroupFile3() {
		File f = new File("test-files/group3.txt");
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> GroupReader.readGroupFile(f));
		assertEquals("Unable to load file.", e.getMessage());
	}
	
	/**
	 * Test readGroupFile with group4, a records file with missing completed count
	 */
	@Test
	void testReadGroupFile4() {
		File f = new File("test-files/group4.txt");
		Group g = GroupReader.readGroupFile(f);
		assertEquals(1, g.getCategoriesNames().length);
		assertEquals("Active Tickets", g.getCurrentCategory().getCategoryName());
		assertEquals(0, g.getCurrentCategory().getTickets().size());
	}
	
	/**
	 * Test readGroupFile with group5, a records file without a category name
	 */
	@Test
	void testReadGroupFile5() {
		File f = new File("test-files/group5.txt");
		Group g = GroupReader.readGroupFile(f);
		assertEquals(1, g.getCategoriesNames().length);
		assertEquals("Active Tickets", g.getCurrentCategory().getCategoryName());
		assertEquals(0, g.getCurrentCategory().getTickets().size());
	}
	
	/**
	 * Test readGroupFile with group6, a records file with negative completed count
	 */
	@Test
	void testReadGroupFile6() {
		File f = new File("test-files/group6.txt");
		Group g = GroupReader.readGroupFile(f);
		assertEquals(1, g.getCategoriesNames().length);
		assertEquals("Active Tickets", g.getCurrentCategory().getCategoryName());
		assertEquals(0, g.getCurrentCategory().getTickets().size());
	}
	
	/**
	 * Test readGroupFile with group7, a records file with an invalid Ticket and a Ticket
	 * without a description
	 */
	@Test
	void testReadGroupFile7() {
		File f = new File("test-files/group7.txt");
		Group g = GroupReader.readGroupFile(f);
		assertEquals(2, g.getCategoriesNames().length);
		assertEquals(1, g.getCurrentCategory().getTickets().size());
		assertEquals("Camtasia", g.getCurrentCategory().getTickets().get(0).getTicketName());
	}

}
