package edu.ncsu.csc216.wolf_hire.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Class tests PositionReader.java
 * @author Benjamin Uy
 */
public class PositionReaderTest {
	
	/** String for filename used in testing readPositionFile() */
	private final String position2 = "test-files/positions2.txt";
	/** String for filename used in testing readPositionsFile. Missing position name */
	private final String position3 = "test-files/positions3.txt";
	/** String for filename used in testing readPositionsFile. Missing hours per week */
	private final String position4 = "test-files/positions4.txt";
	/** String for filename used in testing readPositionsFile. Missing pay rate */
	private final String position5 = "test-files/positions5.txt";
	/** String for filename used in testing readPositionsFile. Has valid Position info but no valid Application */
	private final String position6 = "test-files/positions6.txt";	
	/** String for filename used in testing readPositionsFile. Also has valid Position info but no valid Application */
	private final String position7 = "test-files/positions7.txt";	
	/** String for filename used in testing readPositionsFile. Has a position line with extra tokens */
 	private final String extraTokens = "test-files/extra_tokens.txt";
	/** String for filename used in testing readPositionsFile. Has an application line with extra tokens */
 	private final String extraTokens2 = "test-files/extra_tokens2.txt";
 	/** String for filename used in testing readPositionsFile. Does not start with a '#' character */
 	private final String missingHash = "test-files/missing_hash.txt";
	
	/**
	 * Tests readPositionsFromFile with a valid records file
	 */
	@Test
	public void readPositionsFromFileTest() {
		try {
			ArrayList<Position> a = PositionReader.readPositionFile(position2);
			assertEquals(4, a.size());
			assertEquals("CSC 116 Grader", a.get(0).getPositionName());
			assertEquals(7, a.get(0).getApplications().size());			
			assertEquals("CSC 226 Grader", a.get(2).getPositionName());
			assertEquals("Research Assistant", a.get(3).getPositionName());
		} catch (IllegalArgumentException e) {
			fail("Unxpected error reading " + position2);
		}
	}
	
	/**
	 * Tests readPositionFromFile with a file that does not exist
	 */
	@Test
	public void testReadPositionsFromFileDoesNotExist() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> PositionReader.readPositionFile("."));
		assertEquals("Unable to load file .", e.getMessage());
	}
	
	/**
	 * Tests readPositionFromFile with a file containing an invalid position line
	 */
	@Test
	public void readPositionsFromFileTestInvalidPosition() {
		try {
			// Test with a file missing position name
			ArrayList<Position> a = PositionReader.readPositionFile(position3);
			assertEquals(0, a.size());	
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + position3);
		}
		
		try {
			// Test with a file missing work hours
			ArrayList<Position> b = PositionReader.readPositionFile(position4);
			assertEquals(0, b.size());
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + position4);
		}
		
		try {
			// Test with a file missing pay rate
			ArrayList<Position> c = PositionReader.readPositionFile(position5);
			assertEquals(0, c.size());
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + position5);
		}
		
		try {
			// Test with a file with valid Position info but no valid Applications
			ArrayList<Position> c = PositionReader.readPositionFile(position6);
			assertEquals(0, c.size());
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + position6);
		}
		
		try {
			// Additional test with a file with valid Position info but no valid Applications
			ArrayList<Position> c = PositionReader.readPositionFile(position7);
			assertEquals(0, c.size());
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + position7);
		}
		
		try {
			// Test with valid application info but position line has extra tokens
			ArrayList<Position> c = PositionReader.readPositionFile(extraTokens);
			assertEquals(0, c.size());
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + extraTokens);
		}
		
		try {
			// Test with valid position line but application line has extra tokens
			ArrayList<Position> c = PositionReader.readPositionFile(extraTokens2);
			assertEquals(0, c.size());
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + extraTokens2);
		}
		
		try {
			// Test with text file that does not have '#' character at the start
			ArrayList<Position> c = PositionReader.readPositionFile(missingHash);
			assertEquals(0, c.size());
		} catch (IllegalArgumentException e) {
			fail("Unexpected error reading " + extraTokens2);
		}
	}

}
