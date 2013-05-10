package ch.zhaw.labyrinth.builder;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.zhaw.labyrinth.utils.Coordinate;

public class TestImport {
	
	// instance variables
	private static File f;
	private static Import imp;
	
	// NEW methods with the annotation BeforeClass are executed
    // before executing all tests
    // Note: this method must be static
    @BeforeClass
    public static void beforeEverything() {
    	f = new File("src/test/resources/Maze1.txt");
    	imp = new Import(f);
    }
	
	@Test
	public void testFileIsReadable() {
		// check if file exists, if it is a file and readable
		assertTrue(f.exists());
		assertTrue(f.isFile());
		assertTrue(f.canRead());
	}
	
	
	@Test
	public void testImportEntry(){
	
		Coordinate cActual = imp.getEntry();
		Coordinate cExpected = new Coordinate(0,1);
		assertTrue(cExpected.equals(cActual));	
		
	}
	
	@Test
	public void testImportExit(){
		Coordinate cActual = imp.getExit();
		Coordinate cExpected = new Coordinate(14,4);
		assertTrue(cExpected.equals(cActual));	
	}

}
