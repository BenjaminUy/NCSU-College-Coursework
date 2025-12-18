package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.tickets.Category;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * Class tests GroupWriter.java
 * @author Benjamin Uy
 */
class GroupWriterTest {

	/**
	 * Tests GroupWriter writeGroupFile()
	 */
	@Test
	void testWriteGroupFile() {
		File f = new File("test-files/group_writer_actual.txt");
		
		SortedList<Category> s = new SortedList<Category>();
		
		// Create two Category objects to add to the Sorted List
		Category one = new Category("First", 0);
		Category two = new Category("Second", 2);
		
		two.addTicket(new Ticket("name1", "description1", false));
		two.addTicket(new Ticket("name2", "description2", true));
		
		s.add(one);
		s.add(two);
		assertEquals(2, s.size());
		
		GroupWriter.writeGroupFile(f, "Name", s);
		
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new FileInputStream("test-files/group_writer_actual.txt"));
			assertEquals("! Name", fileReader.nextLine());
			assertEquals("# First,0", fileReader.nextLine());
			assertEquals("# Second,2", fileReader.nextLine());
			assertEquals("* name1", fileReader.nextLine());
			assertEquals("description1", fileReader.nextLine());
			assertEquals("* name2,active", fileReader.nextLine());
			assertEquals("description2", fileReader.nextLine());
		} catch (Exception e) {
			fail();
		} finally {
			fileReader.close();
		}
	}

}
