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
		for(int x=0;x<getMaze().length;x++){
		    Arrays.fill( getMaze()[x], getWall() );
		}
	
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
		// check if new value is not outside the matrix
		if (this.y - 2 <= getDimension()){
			// check if 2 cells ahead is a wall
			if(getMaze()[this.x][this.y - 4] == getWall() ){
													
				setMaze(this.x,this.y - 1,getPath());
				setMaze(this.x,this.y - 2,getPath());
													
				this.y = y - 2;
				createMaze();
			}
		}		
	}

	private void moveDown() {
		
		// check if new value is not outside the matrix
		if (this.x + 2 <= getDimension()){
			
			// check if 2 cells ahead is a wall
			if(getMaze()[this.x + 4][this.y] == getWall() ){
				
				setMaze(this.x + 1,this.y,getPath());
				setMaze(this.x + 2,this.y,getPath());
				
				this.x = x + 2;
				createMaze();
			}
			
		}
		
	}

	private void moveRight() {
		// check if new value is not outside the matrix
		if (this.y + 2 <= getDimension()){
			// check if 2 cells ahead is a wall
			if(getMaze()[this.x][this.y + 4] == getWall() ){
										
				setMaze(this.x,this.y + 1,getPath());
				setMaze(this.x,this.y + 2,getPath());
										
				this.y = y + 2;
				createMaze();
			}
		}
		
	}

	private void moveUp(){
		// check if new value is not outside the matrix
		if (this.x - 2 <= getDimension()){
			
			// check if 2 cells ahead is a wall
			if(getMaze()[this.x - 4][this.y] == getWall() ){
							
				setMaze(this.x - 1,this.y,getPath());
				setMaze(this.x - 2,this.y,getPath());
							
				this.x = x - 2;
				createMaze();
			}
		}
	}
}


