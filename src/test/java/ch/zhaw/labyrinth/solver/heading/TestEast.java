package ch.zhaw.labyrinth.solver.heading;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.labyrinth.builder.Import;
import ch.zhaw.labyrinth.utils.Labyrinth;

public class TestEast {
	
	// instance variables
	private static Labyrinth lab;
	private static East east;
	
	// constancts
	private static final int x = 3;
	private static final int y = 5;
	
	// NEW methods with the annotation Before are executed
    // before every test
    @Before
    public void setUp() {
    	lab = new Import(new File("src/test/resources/Maze1.txt")).build();
    	east = new East(x,y,lab,new Labyrinth());
    }
    
	@Test
	public void testIsRight() {
		assertEquals(true,east.isRight());
	}
	
	@Test
	public void testIsLeftt() {
		assertEquals(false,east.isLeft());
	}
	
	@Test
	public void testIsStraight() {
		assertEquals(true,east.isStraight());
	}
	
	@Test
	public void testIsBack() {
		assertEquals(true,east.isBack());
	}
	
	@Test
	public void testGoRight() {
		assertEquals(0,lab.isCellVisited(x, y));
		east.goRight();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(y+1,east.getY());
		assertEquals(true,lab.getCellValueAt(x, y+1));
	}
	
	@Test
	public void testGoLeft() {
		assertEquals(0,lab.isCellVisited(x, y));
		east.goLeft();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(y-1,east.getY());
		assertEquals(false,lab.getCellValueAt(x, y-1));
	}
	
	@Test
	public void testGoStraight() {
		assertEquals(0,lab.isCellVisited(x, y));
		east.goStraight();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(x+1,east.getX());
		assertEquals(true,lab.getCellValueAt(x+1, y));
	}
	
	@Test
	public void testGoBack() {
		assertEquals(0,lab.isCellVisited(x, y));
		east.goBack();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(x-1,east.getX());
		assertEquals(true,lab.getCellValueAt(x-1, y));
	}
}
