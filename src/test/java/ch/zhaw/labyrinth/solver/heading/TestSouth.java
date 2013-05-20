package ch.zhaw.labyrinth.solver.heading;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.labyrinth.builder.Import;
import ch.zhaw.labyrinth.utils.Labyrinth;

public class TestSouth {
	
	// instance variables
	private static Labyrinth lab;
	private static South south;
	
	// constancts
	private static final int x = 3;
	private static final int y = 5;
	
	// NEW methods with the annotation Before are executed
    // before every test
    @Before
    public void setUp() {
    	lab = new Import(new File("src/test/resources/Maze1.txt")).build();
    	south = new South(x,y,lab,new Labyrinth());
    }
	
    @Test
	public void testIsRight() {
		assertEquals(true,south.isRight());
	}
	
	@Test
	public void testIsLeft() {
		assertEquals(true,south.isLeft());
	}
	
	@Test
	public void testIsStraight() {
		assertEquals(true,south.isStraight());
	}
	
	@Test
	public void testIsBack() {
		assertEquals(false,south.isBack());
	}
	
	@Test
	public void testGoRight() {
		assertEquals(0,lab.isCellVisited(x, y));
		south.goRight();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(x-1,south.getX());
		assertEquals(true,lab.getCellValueAt(x-1, y));
	}
	
	@Test
	public void testGoLeft() {
		assertEquals(0,lab.isCellVisited(x, y));
		south.goLeft();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(x+1,south.getX());
		assertEquals(true,lab.getCellValueAt(x+1, y));
	}
	
	@Test
	public void testGoStraight() {
		assertEquals(0,lab.isCellVisited(x, y));
		south.goStraight();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(y+1,south.getY());
		assertEquals(true,lab.getCellValueAt(x, y+1));
	}
	
	@Test
	public void testGoBack() {
		assertEquals(0,lab.isCellVisited(x, y));
		south.goBack();
		assertEquals(1,lab.isCellVisited(x, y));
		assertEquals(y-1,south.getY());
		assertEquals(false,lab.getCellValueAt(x, y-1));
	}

}
