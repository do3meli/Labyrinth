package ch.zhaw.labyrinth.builder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPrim {

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
        lab = new Prim(dimension);
    	
    }

    // NEW methods with the annotation AfterClass are executed
    // after executing all tests
    // Note: this method must be static
    @AfterClass
    public static void cleanUp() {
    }
    
    
    
    @Test
    public void testAddFrontiers() {
    
    
    }
    
}
