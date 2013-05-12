package ch.zhaw.labyrinth.utils;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestCell {
	
	
	// instance variables
	private static Cell c;
	
	
	// NEW methods with the annotation BeforeClass are executed
    // before executing all tests
    // Note: this method must be static
    @BeforeClass
    public static void beforeEverything() {
    	c = new Cell();
    }
	
	
	@Test
	public void testSetPath() {
		
		c.setPath(true);
		assertEquals(true,c.isPath());
		c.setPath(false);
		assertEquals(false,c.isPath());
		
	}
	
	@Test
	public void testSetVisited() {
		
		assertEquals(0,c.getVisits());
		c.incVisits();
		assertEquals(1,c.getVisits());
		
	}

}
