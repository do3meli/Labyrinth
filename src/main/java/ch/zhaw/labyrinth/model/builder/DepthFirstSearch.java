package ch.zhaw.labyrinth.model.builder;

import ch.zhaw.labyrinth.model.MazeModel;
import ch.zhaw.labyrinth.model.utils.Coordinate;

import java.util.*;

/**
 * This implements a DeepFirstSearch algorithm like descripted
 * on the following website: http://www.migapro.com/depth-first-search/
 * @author Dominic Schlegel
 */
public class DepthFirstSearch extends Observable implements Builder {
  
	// constant
	private static final boolean PATH = true;
	
	// instance var
	private MazeModel lab;

	/**
	 * Default Constructor DFS
	 * @param dim size of the maze
	 */
	public DepthFirstSearch(MazeModel lab, int dim){
		
		// create a maze object
		this.lab = lab;

        lab.setDimension(dim);
        lab.createEmptyMaze();
	}
	
	/**
	 * This builds the maze with the DepthFirstSearch Algorithm
	 * @return MazeModel
     */
	@Override
	public MazeModel build() {

		// set start point in array
		int x = lab.getRandomIntOdd(lab.getDimension());
		int y = lab.getRandomIntOdd(lab.getDimension());
		lab.setCellValue(x, y, PATH);
				
		// create whole maze array
		createMaze(x,y);
		
		// create input and ouput of the maze
		createInputOutput();
		
		// now return the created maze TODO: warum returnen wir da was?
		return lab;

	}

	/**
	 * Method which is recursively called by the maze creation process.
	 * @param x int current location
	 * @param y int current location
	 */
	private void createMaze(int x, int y) {
		
		// create directions 
		Integer[] dir = generateRandomDirections();
		
		for (int i = 0; i < 4; i++) {
			
			// dir = 0 --> move up
			if (dir[i] == 0){
				moveUp(x,y);
			}
			// dir = 1 --> move right
			if (dir[i] == 1){
				moveRight(x,y);
			}
			// dir = 2 --> move down
			if (dir[i] == 2){
				moveDown(x,y);
			}
			// dir = 3 --> move left
			if (dir[i] == 3){
				moveLeft(x,y);
			}

           
		}
	}

	/**
	 * This goes 2 cells up if that is still within the dimension
	 * @param x int current location
	 * @param y int current location
	 */
	private void moveUp(int x, int y) {
		
		// check if new value is not outside the matrix and 
		// check if 2 cells ahead is a wall
		if ( y - 2 > 0 && !lab.getCellValueAt(x, y - 2)){
			
			lab.setCellValue(x, y - 1, PATH);
			 setChanged();
	         notifyObservers(new Coordinate(x, y-1));
			
			lab.setCellValue(x, y - 2, PATH);
			 setChanged();
	         notifyObservers(new Coordinate(x, y-2));
			
			
			createMaze(x,y-2);
		}		
	}
	
	/**
	 * This goes 2 cells right if that is still within the dimension
	 * @param x int current location
	 * @param y int current location
	 */
	private void moveRight(int x, int y) {
		
		// check if new value is not outside the matrix and
		// check if 2 cells ahead is a wall
		if ( x + 2 < lab.getDimension() && !lab.getCellValueAt(x + 2, y)){
			
			lab.setCellValue(x + 1, y, PATH);
			 setChanged();
	         notifyObservers(new Coordinate(x+1, y));
			lab.setCellValue(x + 2, y, PATH);
			 setChanged();
	         notifyObservers(new Coordinate(x+2, y));
			createMaze(x + 2,y);
		}
	}
	
	/**
	 * This goes 2 cells down if that is still within the dimension
	 * @param x int current location
	 * @param y int current location
	 */
	private void moveDown(int x, int y) {
		// check if new value is not outside the matrix and
		// check if 2 cells ahead is a wall
		if ( y + 2 < lab.getDimension() && !lab.getCellValueAt(x, y + 2)){
						
			lab.setCellValue(x,y+1,PATH);
			 setChanged();
	         notifyObservers(new Coordinate(x, y+1));
			lab.setCellValue(x,y+2,PATH);
										
			 setChanged();
	         notifyObservers(new Coordinate(x, y+2));
			createMaze(x,y+2);
		}	
	}
	
	/**
	 * This goes 2 cells left if that is still within the dimension
	 * @param x int current location
	 * @param y int current location
	 */
	private void moveLeft(int x, int y){
		// check if new value is not outside the matrix and 
		// check if 2 cells ahead is a wall
		if ( x - 2 > 0 && !lab.getCellValueAt(x - 2, y)){
			
			lab.setCellValue(x-1,y,PATH);
			 setChanged();
	         notifyObservers(new Coordinate(x-1, y));
			lab.setCellValue(x-2,y,PATH);
			 setChanged();
	         notifyObservers(new Coordinate(x-2, y));
							
			createMaze(x-2,y);
		}
	}
	
	/**
	 * Creates a random shuffeled Integer Array which is used for the directions
	 * @return Integer[] Array with 4 random shuffeled directions
	 */
	private Integer[] generateRandomDirections() {
	      ArrayList<Integer> randoms = new ArrayList<Integer>();
	      for (int i = 0; i < 4; i++){
	           randoms.add(i);
	      }
	      Collections.shuffle(randoms);
	 
	     return randoms.toArray(new Integer[4]);
	 }
	
	/**
	 * After the maze is created this adds a random entry and exit to the maze.
	 * If the entry is at the top the exit is at the bottom.
	 * If the entry is left the exit is right.
	 */
	public void createInputOutput(){
		
		// create random
		Random rand = new Random();
		int r = rand.nextInt(2);
		
		int rEntry = rand.nextInt(lab.getDimension() - 2) + 1;
		int rOut = rand.nextInt(lab.getDimension() - 2) + 1;
			
		// r = 0 => entry is at the top of the maze
		if ( r == 0){
			
			// check if 1 cell ahead entry is free
			while(!lab.getCellValueAt(rEntry,1)){
				rEntry = rand.nextInt(lab.getDimension() -2) + 1;
			}
			
			// check if 1 cell ahead output is free
			while(!lab.getCellValueAt(rOut,lab.getDimension() - 2)){
				rOut = rand.nextInt(lab.getDimension() - 2) + 1;
			}
		
			// now make the field a path
			lab.setCellValue( rEntry,0, PATH);
			 setChanged();
	         notifyObservers(new Coordinate(rEntry, 0));
			lab.setCellValue(rOut,lab.getDimension() - 1, PATH);
			 setChanged();
	         notifyObservers(new Coordinate(rOut, lab.getDimension() -1));

            // Save entry & exit
			lab.setEntry(new Coordinate( rEntry,0));
            lab.setExit(new Coordinate(rOut, lab.getDimension() - 1));
		}
		
		// r = 1 => entry is on the left site
		if ( r == 1){
			
			// check if 1 cell ahead entry is free
			while(!lab.getCellValueAt(1,rEntry)){
				rEntry = rand.nextInt(lab.getDimension()-2) + 1;
			}
			
			// check if 1 cell ahead output is free
			while(!lab.getCellValueAt(lab.getDimension() - 2,rOut)){
				rOut = rand.nextInt(lab.getDimension()-2) + 1;
			}
		
			// now make the field a path
			lab.setCellValue(0, rEntry, PATH);
			 setChanged();
	         notifyObservers(new Coordinate(0, rEntry));
			lab.setCellValue(lab.getDimension() - 1, rOut, PATH);
			
			 setChanged();
	         notifyObservers(new Coordinate(lab.getDimension()-1, rOut));
            // Save entry & exit
            lab.setEntry(new Coordinate(0, rEntry));
            lab.setExit(new Coordinate(lab.getDimension() - 1, rOut));
		}
	}

    @Override
    public void registerObserver(Observer obs) {
        this.addObserver(obs);
    }
	
}