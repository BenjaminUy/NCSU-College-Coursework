package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class tests AbstractCategory.java
 * @author Benjamin Uy
 */
class AbstractCategoryTest {

	/**
	 * Tests AbstractCategory constructor for invalid parameters
	 */
	@Test
	void testAbstractCategory() {
		// Empty string
		Exception e = assertThrows(IllegalArgumentException.class,
				() ->  new Category("", 10));
		assertEquals("Invalid name.", e.getMessage());
		// Null name
		e = assertThrows(IllegalArgumentException.class,
				() ->  new Category(null, 10));
		assertEquals("Invalid name.", e.getMessage());
		// Negative completed number of tickets
				e = assertThrows(IllegalArgumentException.class,
						() ->  new Category("Name", -1));
				assertEquals("Invalid completed count.", e.getMessage());
	}
	
	/**
	 * Tests AbstractCategory getTicket() and completeTicket()
	 */
	@Test
	void testGetTicketAndCompleteTicket() {
		AbstractCategory a = new Category("Name", 10);
		assertEquals(0, a.getTickets().size());
		
		// Create Ticket objects to add to the AbstractCategory
		Ticket t1 = new Ticket("One", "Descript", false);
		Ticket t2 = new Ticket("Two", "Descript", true);
		Ticket t3 = new Ticket("Three", "Descript", false);
		
		// Add Tickets to category
		a.addTicket(t1);
		assertEquals(1, a.getTickets().size());
		a.addTicket(t2);
		assertEquals(2, a.getTickets().size());
		a.addTicket(t3);
		assertEquals(3, a.getTickets().size());
		
		// Get Tickets in order
		assertEquals(t1, a.getTicket(0));
		assertEquals(t2, a.getTicket(1));
		assertEquals(t3, a.getTicket(2));
		
		// Complete Tickets in the list, thereby removing them
		a.completeTicket(t2);
		assertEquals(2, a.getTickets().size());
		assertEquals(t1, a.getTicket(0));
		assertEquals(t3, a.getTicket(1));
		a.completeTicket(t3);
		assertEquals(1, a.getTickets().size());
		assertEquals(t1, a.getTicket(0));
		a.completeTicket(t1);
		assertEquals(0, a.getTickets().size());
	}

}
