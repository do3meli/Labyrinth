package ch.zhaw.labyrinth.utils;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestCoordinate {
	
	
	// instance variables
	private static Coordinate c1;
	
	// NEW methods with the annotation BeforeClass are executed
    // before executing all tests
    // Note: this method must be static
    @BeforeClass
    public static void beforeEverything() {
    	c1 = new Coordinate(1,5);
    }
	
	@Test
	public void testConstructor() {
		assertEquals(1,c1.getX());
		assertEquals(5,c1.getY());
	}
	
	@Test
	public void testEquals(){
		
		Coordinate c2 = new Coordinate(2,3);
		Coordinate c3 = new Coordinate(1,5);
		Coordinate c4 = new Coordinate(1,3);
		
		assertFalse(c2.equals(c1));
		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(c4));
		assertTrue(c3.equals(c1));
		assertTrue(c1.equals(c3));
		assertTrue(c1.equals(c1));
		assertFalse(c1.equals(null));
		assertFalse(c1.equals(new Object()));
	}
	
	@Test
	public void testHashCode(){
		
		int expected = 31 * c1.getX() + c1.getY();
		assertEquals(expected,c1.hashCode());
	}

}
