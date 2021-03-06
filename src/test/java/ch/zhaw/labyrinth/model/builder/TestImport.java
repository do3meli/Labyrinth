package ch.zhaw.labyrinth.model.builder;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ch.zhaw.labyrinth.model.MazeModel;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.zhaw.labyrinth.model.utils.Coordinate;

public class TestImport {
	
	// instance variables
	private static File f;
	private static MazeModel imp;
	
	// NEW methods with the annotation BeforeClass are executed
    // before executing all tests
    // Note: this method must be static
    @BeforeClass
    public static void beforeEverything() {
    	f = new File("src/test/resources/Maze1.txt");
    	imp = new Import(new MazeModel(),f).build();
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
		Coordinate cExpected = new Coordinate(19,0);
		assertTrue(cExpected.equals(cActual));	
		
	}
	
	@Test
	public void testImportExit(){
		Coordinate cActual = imp.getExit();
		Coordinate cExpected = new Coordinate(7,22);
		assertTrue(cExpected.equals(cActual));	
	}
	
	@Test
	public void testSomeWalls(){
		assertFalse(imp.getCellValueAt(4, 2));
		assertFalse(imp.getCellValueAt(8, 7));
		assertFalse(imp.getCellValueAt(10, 12));
		assertFalse(imp.getCellValueAt(4, 6));
	}
	
	@Test
	public void testSomePaths(){
		assertTrue(imp.getCellValueAt(2, 9));
		assertTrue(imp.getCellValueAt(13, 13));
		assertTrue(imp.getCellValueAt(4, 11));
		assertTrue(imp.getCellValueAt(11, 2));
	}
	
	@Test
	public void testWrongFileFormat() throws IOException{
		
		// setup tmp path and temp file name
		final String tmpPath = System.getProperty("java.io.tmpdir");
		final String tmpFile = "/WrongMazeFile.txt";
				
		// create the file object
		File wrongFile = new File(tmpPath + tmpFile);
		
		// if file already exists drop it
		if(wrongFile.exists()){
			wrongFile.delete();
		}
		
		// create the file stream
		BufferedWriter writer = new BufferedWriter(new FileWriter(wrongFile));
	    
		// now write something without spaces to the file and close the stream again
		writer.write("ThisFileContainsNoSpaces");
	    writer.newLine();
	    writer.write("ThereforTheImportWillHopefullyNotWork");
	    writer.flush();
        writer.close();
        
        // now try lets import that file
        MazeModel imp2 = new Import(new MazeModel(),wrongFile).build();
        
        // now dimension should be = 0
        assertEquals(0,imp2.getDimension());
	    
        // trash the file again
        wrongFile.delete();
        
	}

}
