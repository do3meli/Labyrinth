package ch.zhaw.labyrinth.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ch.zhaw.labyrinth.utils.Cell;
import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

/**
 * This will import a File into the maze data structre we have
 * The File needs to have a specific format containing a 2 dimensional Array
 * @author Dominic Schlegel
 */

public class Import extends Labyrinth {
	
	// instance vars
	private File file;
	private Scanner scanner;
	
	// constructor
	public Import(File f) {
		super();
		this.file = f;
		readFileIntoArray();
		printAsArray();
	}

	
	private void readFileIntoArray() {
		
		// try to load the file into the scanner
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
       
        // init vars for iteration
        int j=0;
        
        // now iterate
        while(scanner.hasNext()){
            
        	String line=scanner.nextLine();
            j=0;
            
            if(line.contains(" ")){
                
            	// split the line
            	String str[]=line.split(" ");
                
            	// iterate over each element
            	for (int k = 0; k < str.length; k++) {
            		
            		// create new cell
                    Cell c = new Cell();
                    
                    // set path based on 0 or 1
                    if(str[k].equals("0")){
                    	c.setPath(true);
                    }else{
                    	c.setPath(false);
                    }
                    
                    // put it into the maze
                    getMaze().put(new Coordinate(k,j),c);
				}
            	
            	// increase j
                j++;
            	
            }
		}
	}
	
	
}
