package edu.ncsu.csc216.wolf_tickets.model.tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class tests Category.java
 * @author Benjamin Uy
 */
class CategoryTest {

	/**
	 * Method tests Category getTicketsAsArray()
	 */
	@Test
	void testGetTicketsAsArray() {
		Category c = new Category("First", 0);
		
		// Create Tickets to add to Category
		Ticket first = new Ticket("Init", "Duo", false);
		Ticket second = new Ticket("Dary", "Faunch", false);
		Ticket third = new Ticket("Reach", "Push", true);
		Ticket fourth = new Ticket("Slide", "Slip", true);
		
		c.addTicket(first);
		assertEquals(1, c.getTickets().size());
		c.addTicket(second);
		assertEquals(2, c.getTickets().size());
		
		String[][] tickets = c.getTicketsAsArray();
		assertEquals("0", tickets[0][0]);
		assertEquals("Init", tickets[0][1]);
		assertEquals("1", tickets[1][0]);
		assertEquals("Dary", tickets[1][1]);
		
		c.addTicket(third);
		assertEquals(3, c.getTickets().size());
		c.addTicket(fourth);
		assertEquals(4, c.getTickets().size());
		
		// Create new String array with additional Tickets
		tickets = c.getTicketsAsArray();
		assertEquals("0", tickets[0][0]);
		assertEquals("Init", tickets[0][1]);
		assertEquals("1", tickets[1][0]);
		assertEquals("Dary", tickets[1][1]);
		assertEquals("2", tickets[2][0]);
		assertEquals("Reach", tickets[2][1]);
		assertEquals("3", tickets[3][0]);
		assertEquals("Slide", tickets[3][1]);
	}

}
