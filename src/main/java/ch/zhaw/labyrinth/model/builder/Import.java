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
	private final File file;
	private final MazeModel lab;

	
	/**
	 * This is the default Constructor for Importing a maze
	 * @param model
     *          the maze model
	 * @param f
     *          File with the predefined maze as array in it
	 */
	public Import(MazeModel model, File f) {
		this.file = f;
		this.lab = model;
		build();
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
            		
            		// create new cell and new coordinate
                    Cell c = new Cell();
                    Coordinate cor = new Coordinate(k,j);
                    
                    // set path based on 0 or 1
                    if(str[k].equals("0")){
                    	c.setPath(true);
                    	 setChanged();
                         notifyObservers(cor);
                    }else{
                    	c.setPath(false);
                    }
                    
                    // set entry if there is 0 in first row or first column
                    if( (j == 0 || k == 0) && str[k].equals("0")){
                    	lab.setEntry(cor);
                    }
                    
                    // set exit if there is 0 in the last row or last column
                    if( (!scanner.hasNext() || k == str.length -1) && str[k].equals("0")){
                    	lab.setExit(cor);
                    }
                    
                    // put it into the maze and paint GUI
                    lab.getMaze().put(cor,c);
          
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
