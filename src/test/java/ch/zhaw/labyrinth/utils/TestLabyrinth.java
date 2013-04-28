package ch.zhaw.labyrinth.utils;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestLabyrinth {
	
	// instance variables
	private static Labyrinth lab;
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
		lab = new Labyrinth(dim);
		
	}
	
	@Test
	public void testGetDimension() {
		assertEquals(dim,lab.getDimension());
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
		
		lab.setCellValue(c, false);
		assertEquals(false,lab.getCellAt(c).isPath());
		
	}
	
	@Test
	public void testGetAndSetCellAt(){
		
		int x = 5;
		int y = 2;
		
		lab.setCellValue(x,y, true);
		assertEquals(true,lab.getCellAt(x,y).isPath());
		
		lab.setCellValue(x,y, false);
		assertEquals(false,lab.getCellAt(x,y).isPath());
		
	}
	
	@Test
	public void testRandomInt(){
	    	
		int checkInt = lab.getRandomIntOdd(dim);
	    
		// check random is between 0 and dim
		assertTrue( checkInt < dim);
	    assertTrue( checkInt > 0);
	    
	    // check it is a odd number
	    assertFalse((checkInt & 1) == 0);
	  
	}
	

}
