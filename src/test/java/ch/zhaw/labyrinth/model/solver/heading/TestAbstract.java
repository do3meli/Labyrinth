package ch.zhaw.labyrinth.model.solver.heading;

import static org.junit.Assert.assertTrue;

import java.io.File;

import ch.zhaw.labyrinth.model.MazeModel;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.zhaw.labyrinth.model.builder.Import;
import ch.zhaw.labyrinth.model.utils.Coordinate;

public class TestAbstract {

	// instance variables
	private static MazeModel lab;
	private static East east;
	
	// constancts
	private static final int x = 3;
	private static final int y = 5;
	
	// NEW methods with the annotation Before are executed
    // before every test
    @BeforeClass
    public static void beforeEverything() {
    	lab = new Import(new File("src/test/resources/Maze1.txt")).build();
    	east = new East(x,y,lab,new MazeModel());
    }
    
    @Test
    public void testGetCoordinate(){
    	assertTrue(east.getCoordinate().equals(new Coordinate(x,y)));
    }
    
  

}
