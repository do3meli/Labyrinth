package ch.zhaw.labyrinth.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
		
		// fill whole array with WALL
		for(int x=0;x<getMaze().length;x++){
		    Arrays.fill( getMaze()[x], getWall() );
		}
			
		// set start point in array
		int x = getRandomInt(getDimension());
		int y = getRandomInt(getDimension());
		setMaze(x,y,getPath());
		
		// create whole maze array
		createMaze(x,y);
		
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
		// check if new value is not outside the matrix
		if (y - 2 > 0){
			// check if 2 cells ahead is a wall
			if(getMaze()[x][y - 2] == getWall() ){
													
				setMaze(x,y - 1,getPath());
				setMaze(x,y - 2,getPath());
													
				createMaze(x,y-2);
			}
		}		
	}

	private void moveDown(int x, int y) {
		
		// check if new value is not outside the matrix
		if (x + 2 < getDimension()){
			
			// check if 2 cells ahead is a wall
			if(getMaze()[x + 2][y] == getWall() ){
				
				setMaze(x + 1,y,getPath());
				setMaze(x + 2,y,getPath());
				
				createMaze(x + 2,y);
			}
			
		}
		
	}

	private void moveRight(int x, int y) {
       

		// check if new value is not outside the matrix
		if (y + 2 < getDimension()){
			// check if 2 cells ahead is a wall
			if(getMaze()[x][y + 2] == getWall() ){
										
				setMaze(x,y + 1,getPath());
				setMaze(x,y + 2,getPath());
										
				createMaze(x,y+2);
			}
		}
		
	}

	private void moveUp(int x, int y){
		// check if new value is not outside the matrix
		if (x - 2 > 0){
			
			// check if 2 cells ahead is a wall
			if(getMaze()[x - 2][y] == getWall() ){
							
				setMaze(x - 1,y,getPath());
				setMaze(x - 2,y,getPath());
							
				createMaze(x-2,y);
			}
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
}


