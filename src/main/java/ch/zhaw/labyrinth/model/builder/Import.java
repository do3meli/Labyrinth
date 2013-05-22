package ch.zhaw.labyrinth.model.builder;

import ch.zhaw.labyrinth.model.MazeModel;
import ch.zhaw.labyrinth.model.utils.Cell;
import ch.zhaw.labyrinth.model.utils.Coordinate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * This will import a File into the maze data structre we have
 * The File needs to have a specific format containing a 2 dimensional Array
 * @author Dominic Schlegel
 */

public class Import extends Observable implements Builder {
	
	// instance vars
	private File file;
	private MazeModel lab;
	
	/**
	 * This is the default Constructor for Importing a maze
	 * @param f File with the predefined maze as array in it
	 */
	public Import(File f) {
		this.file = f;
		this.lab = new MazeModel();
	}

	/**
	 * This builds up a maze data structure from a given file
	 * @return MazeModel
     */
	@Override
	public MazeModel build() {
		
		// local variable
		Scanner scanner = null;
		
		// try to load the file into the scanner
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
       
        // j var for line
        int j=0;
        
        // now iterate
        while(scanner.hasNext()){
            
        	String line=scanner.nextLine();
            
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
                    
                    // set entry if there is 0 in first row or first column
                    if( (j == 0 || k == 0) && str[k].equals("0")){
                    	lab.setEntry(new Coordinate(k,j));
                    }
                    
                    // set exit if there is 0 in the last row or last column
                    if( (scanner.hasNext() == false || k == str.length -1) && str[k].equals("0")){
                    	lab.setExit(new Coordinate(k,j));
                    }
                    
                    // put it into the maze
                    lab.getMaze().put(new Coordinate(k,j),c);
				}
            	
            	// increase j
                j++;
            }
		}
        
        // only set maze dimension when j greather than 0
        // otherwise paintComponent not working
        if( j > 0 ){
        	lab.setDimension(j);
        }
    
    // build() return
    return lab;
	}

    @Override
    public void registerObserver(Observer observer) {
        this.addObserver(observer);
    }
}
