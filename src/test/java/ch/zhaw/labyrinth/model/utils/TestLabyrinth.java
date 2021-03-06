package ch.zhaw.labyrinth.model.utils;

import java.util.Random;

import ch.zhaw.labyrinth.model.MazeModel;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLabyrinth {
	
	// instance variables
	private static MazeModel lab;
	private static int dim;	
	
	// NEW methods with the annotation BeforeClass are executed
    // before executing all tests
    // Note: this method must be static
	@BeforeClass
	public static void beforeEverything() {
		
		// create a random dimension between 5 and 49
		// needs to be between that range because i use 5 below for some tests
		// INFO:
		// to pick a number from from 5 to 35 inclusively, 
		// the upper limit number will be 35-5+1=31 and 5 needs to be added to the result
		Random r = new Random();
		dim = r.nextInt(45) + 5;

		// create a lab
		lab = new MazeModel();
		lab.setDimension(dim);
		lab.createEmptyMaze();
	}
	
	@Test
	public void testGetDimension() {
		assertEquals(lab.makeIntOdd(dim),lab.getDimension());
	}
	
	@Test
	public void testCreateEmptyMaze(){
		
		int expectedPower = (int)Math.pow(lab.getDimension(),2);
		assertEquals(expectedPower, lab.getMaze().size());	
	}
	
	@Test
	public void testGetAndSetCellAtCoordinate(){
		
		Coordinate c = new Coordinate(5,2);
		
		lab.setCellValue(c, true);
		assertEquals(true,lab.getCellAt(c).isPath());
		assertEquals(true,lab.getCellValueAt(c));
		
		lab.setCellValue(c, false);
		assertEquals(false,lab.getCellAt(c).isPath());
		assertEquals(false,lab.getCellValueAt(c));
		
		c.equals(lab.getCellAt(c));
		
		assertEquals(false,lab.getCellValueAt(new Coordinate(99,88)));
	}
	
	@Test
	public void testGetAndSetCellAt(){
		
		int x = 5;
		int y = 2;
		
		lab.setCellValue(x,y, true);
		assertEquals(true,lab.getCellAt(x,y).isPath());
		assertEquals(true,lab.getCellValueAt(x, y));
		
		lab.setCellValue(x,y, false);
		assertEquals(false,lab.getCellAt(x,y).isPath());
		assertEquals(false,lab.getCellValueAt(x, y));
	}
	
	@Test
	public void testRandomInt(){
	    	
		int even = 12;
		int odd = 13;
		
		int checkInt1 = lab.getRandomIntOdd(dim);
	    int checkInt2 = lab.getRandomIntOdd(even);
		int checkInt3 = lab.getRandomIntOdd(odd);
	    
		// check random is between 0 and dim
		assertTrue( checkInt1 < dim);
	    assertTrue( checkInt1 > 0);
	    assertTrue( checkInt2 < even);
	    assertTrue( checkInt2 > 0);
	    assertTrue( checkInt3 < odd);
	    assertTrue( checkInt3 > 0);
	   
	    // check it is a odd number
	    assertFalse((checkInt1 & 1) == 0);
	  
	}
	
	@Test
	public void testIsVisited(){
		Coordinate c = new Coordinate(1,2);

		assertEquals(0,lab.getCellAt(c).getVisits());
		lab.getCellAt(c).incVisits();
		assertEquals(1,lab.getCellAt(c).getVisits());
	}
	
	@Test
	public void testGetAndSetEntry(){
		Coordinate c = new Coordinate(0,5);
		lab.setEntry(c);
		Coordinate r = lab.getEntry();
		
		assertTrue(c.equals(r));
	}
	
	@Test
	public void testGetAndSetExit(){
		Coordinate c = new Coordinate(2,5);
		lab.setExit(c);
		Coordinate r = lab.getExit();
		
		assertTrue(c.equals(r));
	}
	
}