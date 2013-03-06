package ch.zhaw.labyrinth.builder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

//NEW static import for assert methods
import static org.junit.Assert.*;

/**
* @author Dominic Schlegel
*/

public class TestLabyrinthBuilder {

	private static int dimension;
	private static LabyrinthBuilder lab;
	
    // NEW methods with the annotation Before
    // are called before each @Test method (like
    // setUp() in older junit versions
    @Before
    public void init() {
        
    }

    // NEW methods with the annotation After 
    // are called before each @Test method (like
    // tearDown() in older junit versions
    @After
    public void clear() {
       
    }

    // NEW methods with the annotation BeforeClass are executed
    // before executing all tests
    // Note: this method must be static
    @BeforeClass
    public static void beforeEverything() {
    	
    	// set dimension
    	dimension = 20;
    	
    	// create new labyrinth builder object
        lab = new DepthFirstSearch(dimension);
    	
    }

    // NEW methods with the annotation AfterClass are executed
    // after executing all tests
    // Note: this method must be static
    @AfterClass
    public static void cleanUp() {
    }

    // NEW test methods are found with the new annotation Test
    // instead of the signature void testMethod
    @Ignore
    public void testConstructorDimensionSet() {
    	
    	// as we initialize DepthFirstSearch with dimension = 20 
    	// it should get checked - and we will only get a odd number (dimension +1)
    	
    	assertEquals(21,lab.getDimension());
    	assertNotSame(dimension,lab.getDimension());
    }
      
    @Test
    public void testMakeIntOdd(){
    	int odd = 11;
    	int even = 20;
    	
    	assertEquals(odd,lab.makeIntOdd(odd));
    	assertNotSame(even,lab.makeIntOdd(even));
    	assertEquals(21,lab.makeIntOdd(even));
    }

    
    @Test
    public void testRandomInt(){
    	
    	int checkInt = lab.getRandomInt(dimension);
    	
    	assertTrue( checkInt <= dimension);
    	assertTrue( checkInt > 0);
    	
    	
    }
}

