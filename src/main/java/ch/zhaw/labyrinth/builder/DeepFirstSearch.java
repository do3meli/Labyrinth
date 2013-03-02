package ch.zhaw.labyrinth.builder;

import java.util.Arrays;

/**
 * This implements a DeepFirstSearch algorithm like descripted on
 * at the following website: http://www.migapro.com/depth-first-search/
 * @author Dominic Schlegel
 */
public class DeepFirstSearch extends LabyrinthBuilder {
  
	
	private int x;						   // keep track of current row x
	private int y;						   // keep track of current col y


	// Destructor
	public DeepFirstSearch(int n){
		
		// call super constructor
		super(n);
		// fill whole array with WALL
		Arrays.fill(getMaze(), getWall());
		// set start point in array
		this.x = getRandomInt(getDimension());
		this.y = getRandomInt(getDimension());
		setMaze(x,y,getPath());
		// create whole maze array
		createMaze();
		
	}
	
	
	

	private void createMaze() {
		
		// get a Random int for direction
		int dir = getRandomInt(4);
		
		// dir = 0 --> move up
		if (dir == 0){
			moveUp();
		}
		// dir = 1 --> move right
		if (dir == 1){
			moveRight();
		}
		// dir = 2 --> move down
		if (dir == 2){
			moveDown();
		}
		// dir = 3 --> move left
		if (dir == 3){
			moveLeft();
		}
		
	}

	
	
	private void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	private void moveDown() {
		// TODO Auto-generated method stub
		
	}

	private void moveRight() {
		// TODO Auto-generated method stub
		
	}

	private void moveUp(){
		 // TODO
	}
}


