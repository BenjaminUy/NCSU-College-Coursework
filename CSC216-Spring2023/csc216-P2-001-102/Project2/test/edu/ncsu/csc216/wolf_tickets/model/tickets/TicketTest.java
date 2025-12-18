package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class tests Ticket.java
 * @author Benjamin Uy
 */
class TicketTest {

	/**
	 * Tests Ticket addCategory()
	 */
	@Test
	void testAddCategory() {
		Ticket t = new Ticket("First", "Descript", false);

		Category c = new Category("Computer", 0);
		
		t.addCategory(c);
		assertEquals("Computer", t.getCategoryName());
	}

	/**
	 * Tests Ticket toString for an inactive and active Ticket
	 */
	@Test
	void testToString() {
		// Create inactive Ticket
		Ticket t = new Ticket("First", "Descript", false);
		assertEquals("First", t.getTicketName());
		assertEquals("Descript", t.getTicketDescription());
		assertFalse(t.isActive());
		assertEquals("* First\nDescript", t.toString());
		
		// Create active Ticket
		Ticket a = new Ticket("Second", "Active", true);
		assertEquals("Second", a.getTicketName());
		assertEquals("Active", a.getTicketDescription());
		assertTrue(a.isActive());
		assertEquals("* Second,active\nActive", a.toString());
	}

}
