package ch.zhaw.labyrinth.model.builder;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import ch.zhaw.labyrinth.model.utils.MazeModel;

public class TestDFS {
	
	private static MazeModel dfs;
	
	// NEW methods with the annotation BeforeClass are executed
    // before executing all tests
    // Note: this method must be static
    @BeforeClass
    public static void beforeEverything() {
    	dfs = new DepthFirstSearch(23).build();
    }
    
    @Test
    public void testDimension(){
    	assertEquals(23,dfs.getDimension());
    }
}
