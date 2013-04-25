package ch.zhaw.labyrinth.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * This implements a DeepFirstSearch algorithm like descripted on
 * at the following website: http://www.migapro.com/depth-first-search/
 * @author Dominic Schlegel
 */
public class DepthFirstSearch extends LabyrinthBuilder {
  
	
	private Integer[] dir;

	// Constructor
	public DepthFirstSearch(int n){
		
		// call super constructor
		super(n);
		
		// now setup everything
		setupMaze();
		
		
	}
	
	public void setupMaze(){
		// fill whole array with WALL
		fillArray();
					
		// set start point in array
		int x = getRandomIntOdd(getDimension());
		int y = getRandomIntOdd(getDimension());
		setMaze(x,y,getPath());
				
		// create whole maze array
		createMaze(x,y);
		
		// create input and ouput of the maze
		createInputOutput();

	}

	private void createMaze(int x, int y) {
		
		// create directions 
		this.dir = generateRandomDirections();
		
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

	// TODO: create one move method/class to avoid code duplication
	
	private void moveLeft(int x, int y) {
		// check if new value is not outside the matrix and 
		// check if 2 cells ahead is a wall
		if ( y - 2 > 0 && getMaze()[x][y - 2] == getWall() ){
													
			setMaze(x,y - 1,getPath());
			setMaze(x,y - 2,getPath());
													
			createMaze(x,y-2);
		}		
	}

	private void moveDown(int x, int y) {
		
		// check if new value is not outside the matrix and
		// check if 2 cells ahead is a wall
		if ( x + 2 < getDimension() && getMaze()[x + 2][y] == getWall() ){
			
			setMaze(x + 1,y,getPath());
			setMaze(x + 2,y,getPath());
				
			createMaze(x + 2,y);
		}
	}

	private void moveRight(int x, int y) {
		// check if new value is not outside the matrix and
		// check if 2 cells ahead is a wall
		if ( y + 2 < getDimension() && getMaze()[x][y + 2] == getWall() ){
										
			setMaze(x,y + 1,getPath());
			setMaze(x,y + 2,getPath());
										
			createMaze(x,y+2);
		}	
	}

	private void moveUp(int x, int y){
		// check if new value is not outside the matrix and 
		// check if 2 cells ahead is a wall
		if ( x - 2 > 0 && getMaze()[x - 2][y] == getWall() ){
				
			setMaze(x - 1,y,getPath());
			setMaze(x - 2,y,getPath());
							
			createMaze(x-2,y);
		}
	}
	
	public Integer[] generateRandomDirections() {
	      ArrayList<Integer> randoms = new ArrayList<Integer>();
	      for (int i = 0; i < 4; i++){
	           randoms.add(i);
	      }
	      Collections.shuffle(randoms);
	 
	     return randoms.toArray(new Integer[4]);
	 }
	
	public void fillArray(){
		for(int x=0;x<getMaze().length;x++){
		    Arrays.fill( getMaze()[x], getWall() );
		}
	}
	
	
	public void createInputOutput(){
		
		// create random
		Random rand = new Random();
		int r = rand.nextInt(2);
		
		
		// get a random for entry and one for output
		int r_entry = rand.nextInt(getDimension());
		int r_out = rand.nextInt(getDimension());
		
		
		
		// r = 0 => entry is at the top of the maze
		if ( r == 0){
			
			// check if 1 cell ahead entry is free
			while(getMaze()[1][r_entry] != getPath()){
				r_entry = rand.nextInt(getDimension());
			}
			
			// check if 1 cell ahead output is free
			while(getMaze()[getDimension()-2][r_out] != getPath()){
				r_out = rand.nextInt(getDimension());
			}
		
			// now make the field a path
			setMaze(0,r_entry,getPath());
			setMaze(getDimension()-1,r_out,getPath());
		
		}
		
		// r = 1 => entry is on the left site
		if ( r == 1){
			
			// check if 1 cell ahead entry is free
			while(getMaze()[r_entry][1] != getPath()){
				r_entry = rand.nextInt(getDimension());
			}
			
			// check if 1 cell ahead output is free
			while(getMaze()[r_out][getDimension()-2] != getPath()){
				r_out = rand.nextInt(getDimension());
			}
		
			// now make the field a path
			setMaze(r_entry,0,getPath());
			setMaze(r_out,getDimension()-1,getPath());
		}
		
		
		
	}
	
}


