package ch.zhaw.labyrinth.model.solver.heading;

import static org.junit.Assert.*;

import java.io.File;

import ch.zhaw.labyrinth.model.MazeModel;
import org.junit.Before;
import org.junit.Test;

import ch.zhaw.labyrinth.model.builder.Import;

public class TestWest {

	// instance variables
	private static MazeModel lab;
	private static West west;
	
	// constancts
	private static final int x = 3;
	private static final int y = 5;
	
	// NEW methods with the annotation Before are executed
    // before every test
    @Before
    public void setUp() {
    	lab = new Import(new MazeModel(),new File("src/test/resources/Maze1.txt")).build();
    	west = new West(x,y,lab,new MazeModel());
    }
    
    @Test
   	public void testIsRight() {
   		assertEquals(false,west.isRight());
   	}
   	
   	@Test
   	public void testIsLeft() {
   		assertEquals(true,west.isLeft());
   	}
   	
   	@Test
   	public void testIsStraight() {
   		assertEquals(true,west.isStraight());
   	}
   	
   	@Test
   	public void testIsBack() {
   		assertEquals(true,west.isBack());
   	}
   	
   	@Test
   	public void testGoRight() {
   		assertEquals(0,west.getVisits(x, y));
   		west.goRight();
   		assertEquals(1,west.getVisits(x, y));
   		assertEquals(y-1,west.getY());
   		assertEquals(false,lab.getCellValueAt(x, y-1));
   	}
   	
   	@Test
   	public void testGoLeft() {
   		assertEquals(0,west.getVisits(x, y));
   		west.goLeft();
   		assertEquals(1,west.getVisits(x, y));
   		assertEquals(y+1,west.getY());
   		assertEquals(true,lab.getCellValueAt(x, y+1));
   	}
   	
   	@Test
   	public void testGoStraight() {
   		assertEquals(0,west.getVisits(x, y));
   		west.goStraight();
   		assertEquals(1,west.getVisits(x, y));
   		assertEquals(x-1,west.getX());
   		assertEquals(true,lab.getCellValueAt(x-1, y));
   	}
   	
   	@Test
   	public void testGoBack() {
   		assertEquals(0,west.getVisits(x, y));
   		west.goBack();
   		assertEquals(1,west.getVisits(x, y));
   		assertEquals(x+1,west.getX());
   		assertEquals(true,lab.getCellValueAt(x+1,y));
   	}


}
