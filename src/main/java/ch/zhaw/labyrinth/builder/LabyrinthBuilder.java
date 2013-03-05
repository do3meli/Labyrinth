package ch.zhaw.labyrinth.builder;

import java.util.Random;


public abstract class LabyrinthBuilder {
  
	// constants
	private static final int PATH = 0;	   // constant for PATH
	private static final int WALL = 1;     // constant for WALL
	
	// variables
	private int dimension;                 // dimension of maze
	private int[][] maze;				   // byte array for maze
 
	
	public LabyrinthBuilder(int dim){
		// make dim odd and set dimension
		setDimension(makeIntOdd(dim));
		// create empty array
		this.maze = new int[getDimension()][getDimension()];
		
	}
	
	
	/**
	 * Getter Method for Maze Array
	 * @return the maze - 2 dimensional array
	 */
	public int[][] getMaze(){
		return this.maze;
	}
	
	/**
	 * 
	 * @param x int of maze Array
	 * @param y int of maze Array
	 * @param value int for maze[x][y]
	 */
	public void setMaze(int x, int y, int value){
		this.maze[x][y] = value;
	}
	
	/**
	 * Getter Method for dimension
	 * @return the dimension of the maze
	 */
	public int getDimension(){
		return this.dimension;
	}
	
	/**
	 * Setter Method for dimension
	 * @param int dimension of the matrix
	 */
	private void setDimension(int dim){
		this.dimension = dim - 1;
	}

	/**
	 * This makes the input parameter odd
	 * @param x int
	 * @return an odd int
	 */
	private int makeIntOdd(int x){
		 if ( x % 2 == 0 ){
	         x = x + 1;
		 }
		 return x; 
	}


	/**
	 * @return a random int within the dimension of the array
	 */
	public int getRandomInt(int dim){
		//Random r = new Random();
		//return r.nextInt(dim);

        return (int)(Math.random() * (dim - 1) + 1) ;
	}
	
	/**
     * TODO: ENUM
	 * @return int value of WALL constant
	 */
	public static int getWall() {
		return WALL;
	}

	/**
	 * @return in value of PATH constant
	 */
	public static int getPath() {
		return PATH;
	}
	
	
	
}

