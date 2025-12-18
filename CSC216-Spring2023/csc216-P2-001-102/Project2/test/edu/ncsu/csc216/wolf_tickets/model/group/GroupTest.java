package edu.ncsu.csc216.wolf_tickets.model.group;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;

/**
 * Class tests Group.java
 * @author Benjamin Uy
 */
class GroupTest {

	/**
	 * Tests Group constructor along with various setter/getter methods
	 */
	@Test
	void testGroup() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new Group(null));
		assertEquals("Invalid name.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class,
				() -> new Group(""));
		assertEquals("Invalid name.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class,
				() -> new Group("Active Tickets"));
		assertEquals("Invalid name.", e.getMessage());
		
		Group g = new Group("IT Squad");
		assertEquals("IT Squad", g.getGroupName());
		assertEquals("Active Tickets", g.getCurrentCategory().getCategoryName());
		assertEquals(0, g.getCurrentCategory().getTickets().size());
		assertTrue(g.isChanged());
		
		g.setGroupName("CSC IT");
		assertEquals("CSC IT", g.getGroupName());
	}

	/**
	 * Tests Group addCategory()
	 */
	@Test
	void testAddCategory() {
		Group g = new Group("IT Squad");
		
		Category active = new Category("Active Tickets", 0);			// Attempt to add Category by the name of "Active Tickets"
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> g.addCategory(active));
		assertEquals("Invalid name.", e.getMessage());
		
		Category first = new Category("First Category", 1);				// Create Category objects to add to the Group
		Category second = new Category("Second Category", 2);
		
		g.addCategory(first);
		assertEquals(first, g.getCurrentCategory());
		g.addCategory(second);
		assertEquals(second, g.getCurrentCategory());
		
		e = assertThrows(IllegalArgumentException.class,
				() -> g.addCategory(second));				// Add duplicate Category
		assertEquals("Invalid name.", e.getMessage());
	}

	/**
	 * Tests Group getCategoriesNames()
	 */
	@Test
	void testGetCategoriesNames() {
		Group g = new Group("IT Squad");
		
		Category first = new Category("First Category", 1);				// Create Category objects to add to the Group
		Category second = new Category("Second Category", 2);
		Category third = new Category("Abstine", 0);					
		Category fourth = new Category("Zeta", 0);
		
		g.addCategory(first);
		g.addCategory(second);
		g.addCategory(third);
		g.addCategory(fourth);
		
		String[] names = g.getCategoriesNames();
		assertEquals(5, names.length);
		assertEquals("Active Tickets", names[0]);
		// Check that Active Tickets is first in Categories even if not in alphabetical order
		assertEquals("Abstine", names[1]);
		assertEquals("First Category", names[2]);
		assertEquals("Second Category", names[3]);
		assertEquals("Zeta", names[4]);
	}

	/**
	 * Tests Group editCategory()
	 */
	@Test
	void testEditCategory() {
		Group g = new Group("IT Squad");
		
		// Attempt to edit the Active Tickets list name
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> g.editCategory("New name"));
		assertEquals("The Active Tickets list may not be edited.", e.getMessage());
		// Attempt to name the category "Active Tickets"
		e = assertThrows(IllegalArgumentException.class,
				() -> g.editCategory("Active Tickets"));
		assertEquals("Invalid name.", e.getMessage());
		
		Category first = new Category("First Category", 1);	
		
		g.addCategory(first);
		assertEquals(first, g.getCurrentCategory());
		
		g.editCategory("Third Category");
		assertEquals("Third Category", g.getCurrentCategory().getCategoryName());
		
		Category second = new Category("Second Category", 2);
		g.addCategory(second);
		
		// Attempt to edit the current category's name to match another existing category
		e = assertThrows(IllegalArgumentException.class,
				() -> g.editCategory("Third Category"));
		assertEquals("Invalid name.", e.getMessage());
	}
	
	/**
	 * Tests Group setCategory()
	 */
	void testSetCategory() {
		Group g = new Group("IT Squad");
		
		Category first = new Category("First Category", 1);	
		g.addCategory(first);
		
		// Intentionally set current category to the Active Tickets
		g.setCurrentCategory("Active Tickets");
		assertEquals("Active Tickets", g.getCurrentCategory().getCategoryName());
		
		// Intentionally set current category to the first added category
		g.setCurrentCategory("First Category");
		assertEquals(first, g.getCurrentCategory());
	}

	/**
	 * Method tests Group.removeCategory()
	 */
	@Test
	void testRemoveCategory() {
		Group g = new Group("IT Squad");
		
		Category first = new Category("First Category", 1);				// Create Category objects to add to the Group
		
		Exception e = assertThrows(IllegalArgumentException.class,		// Attempt to remove current category which is the Active Ticket list
				() -> g.removeCategory());
		assertEquals("The Active Tickets list may not be deleted.", e.getMessage());
		
		// Add first Category to Group
		g.addCategory(first);
		assertEquals(first, g.getCurrentCategory());
		g.removeCategory(); 		// Remove just-added Category
		
		e = assertThrows(IllegalArgumentException.class,		// Attempt to remove Active Ticket list which is the current category
				() -> g.removeCategory());
		assertEquals("The Active Tickets list may not be deleted.", e.getMessage());
	}

	/**
	 * Tests Group addTicket()
	 */
	@Test
	void testAddTicket() {
		Group g = new Group("IT Squad");
		
		Category first = new Category("First Category", 1);	
		
		// Add first Category to Group
		g.addCategory(first);
		assertEquals(first, g.getCurrentCategory());
		g.addTicket(new Ticket("name", "descript", false));
		
		assertEquals(1, g.getCurrentCategory().getTickets().size());
		assertEquals("name", g.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("descript", g.getCurrentCategory().getTicket(0).getTicketDescription());
		assertFalse(g.getCurrentCategory().getTicket(0).isActive());
	}

	/**
	 * Tests Group editTicket()
	 */
	@Test
	void testEditTicket() {
		Group g = new Group("IT Squad");
		
		Category first = new Category("First Category", 1);	
		
		// Add first Category to Group
		g.addCategory(first);
		assertEquals(first, g.getCurrentCategory());
		g.addTicket(new Ticket("name", "descript", false));
		
		g.editTicket(0, "System not working", "My code doesn't seem to work whenever I turn on my computer.", false);
		assertEquals("System not working", g.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("My code doesn't seem to work whenever I turn on my computer.", 
				g.getCurrentCategory().getTicket(0).getTicketDescription());
		assertFalse(g.getCurrentCategory().getTicket(0).isActive());
	}
	
	/**
	 * Tests Group saveGroup()
	 */
	@Test
	void testSaveGroup() {
		Group g = new Group("CSC IT");
		
		File f = new File("test-files/group0_actual.txt");
		g.saveGroup(f);
		
		checkFiles("test-files/group0.txt", "test-files/group0_actual.txt");
		
		assertFalse(g.isChanged());
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
