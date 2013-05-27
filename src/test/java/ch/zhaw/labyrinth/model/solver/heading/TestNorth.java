package ch.zhaw.labyrinth.model.solver.heading;

import static org.junit.Assert.assertEquals;

import java.io.File;

import ch.zhaw.labyrinth.model.MazeModel;
import org.junit.Before;
import org.junit.Test;

import ch.zhaw.labyrinth.model.builder.Import;

public class TestNorth {

	// instance variables
	private static MazeModel lab;
	private static North north;
	
	// constancts
	private static final int x = 3;
	private static final int y = 5;
	
	// NEW methods with the annotation Before are executed
    // before every test
    @Before
    public void setUp() {
    	lab = new Import(new MazeModel(),new File("src/test/resources/Maze1.txt")).build();
    	north = new North(x,y,lab,new MazeModel());
    }
    
    @Test
	public void testIsRight() {
		assertEquals(true,north.isRight());
	}
	
	@Test
	public void testIsLeft() {
		assertEquals(true,north.isLeft());
	}
	
	@Test
	public void testIsStraight() {
		assertEquals(false,north.isStraight());
	}
	
	@Test
	public void testIsBack() {
		assertEquals(true,north.isBack());
	}
	
	@Test
	public void testGoRight() {
		assertEquals(0,lab.isCellVisited(x, y));
		north.goRight();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(x+1,north.getX());
		assertEquals(true,lab.getCellValueAt(x+1, y));
	}
	
	@Test
	public void testGoLeft() {
		assertEquals(0,lab.isCellVisited(x, y));
		north.goLeft();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(x-1,north.getX());
		assertEquals(true,lab.getCellValueAt(x-1, y));
	}
	
	@Test
	public void testGoStraight() {
		assertEquals(0,lab.isCellVisited(x, y));
		north.goStraight();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(y-1,north.getY());
		assertEquals(false,lab.getCellValueAt(x, y-1));
	}
	
	@Test
	public void testGoBack() {
		assertEquals(0,lab.isCellVisited(x, y));
		north.goBack();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(y+1,north.getY());
		assertEquals(true,lab.getCellValueAt(x, y+1));
	}
    
}
