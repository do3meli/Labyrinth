package ch.zhaw.labyrinth.builder;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import ch.zhaw.labyrinth.utils.Labyrinth;

public class TestDFS {
	
	private static Labyrinth dfs;
	
	// NEW methods with the annotation BeforeClass are executed
    // before executing all tests
    // Note: this method must be static
    @BeforeClass
    public static void beforeEverything() {
    	dfs = new Labyrinth(23);
    }
    
    @Test
    public void testDimension(){
    	assertEquals(23,dfs.getDimension());
    }
}
