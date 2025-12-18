package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Class tests ActiveTicketList.java
 * @author Benjamin Uy
 */
class ActiveTicketListTest {

	/**
	 * Tests ActiveTicketList addTicket() and clearTickets()
	 */
	@Test
	void testAddTicket() {
		Ticket inactive = new Ticket("I", "am", false);
		ActiveTicketList a = new ActiveTicketList();
		assertEquals(0, a.getCompletedCount());
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> a.addTicket(inactive));
		assertEquals("Cannot add ticket to Active Tickets.", e.getMessage());
		assertEquals(0, a.getTickets().size());
		
		Ticket active1 = new Ticket("Active", "One", true);
		a.addTicket(active1);
		assertEquals(1, a.getTickets().size());
		
		// Remove all tickets in ActiveTicketList
		a.clearTickets();
		assertEquals(0, a.getTickets().size());
		assertEquals(0, a.getTicketsAsArray().length);
	}

	/**
	 * Tests ActiveTicketList getTicketsAsArray
	 */
	@Test
	void testGetTicketsAsArray() {
		ActiveTicketList a = new ActiveTicketList();
		
		Ticket active1 = new Ticket("Active", "One", true);
		a.addTicket(active1);
		assertEquals(1, a.getTickets().size());
		
		Ticket active2 = new Ticket("Twelve", "Two", true);
		a.addTicket(active2);
		assertEquals(2, a.getTickets().size());
		
		String[][] tickets = a.getTicketsAsArray();
		assertEquals("Active Tickets", tickets[0][0]);
		assertEquals("Active", tickets[0][1]);
		assertEquals("Active Tickets", tickets[1][0]);
		assertEquals("Twelve", tickets[1][1]);
	}
	
	/**
	 * Tests ActiveTicketList clearTickets(); based on TS Jenkins test
	 */
	@Test
	void testClearTickets() {
		ActiveTicketList a = new ActiveTicketList();
		
		// Create Tickets to add to the ActiveTicketList
		Ticket t1 = new Ticket("Ticket 1", "Ticket 1 Description", true);
		Ticket t2 = new Ticket("Ticket 2", "Ticket 2 Description", true);
		Ticket t3 = new Ticket("Ticket 3", "Ticket 3 Description", true);
		
		a.addTicket(t1);
		a.addTicket(t2);
		a.addTicket(t3);
		assertEquals(3, a.getTickets().size());
		
		// Clear list and ensure empty
		a.clearTickets();
		assertEquals(0, a.getTickets().size());
		assertEquals(0, a.getTicketsAsArray().length);
	}

}
