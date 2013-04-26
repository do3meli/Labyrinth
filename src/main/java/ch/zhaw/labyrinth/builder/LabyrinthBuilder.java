package ch.zhaw.labyrinth.builder;

import java.util.Random;


public abstract class LabyrinthBuilder {
  
	// constants
	private static final int PATH = 0;	   // constant for PATH
	private static final int WALL = 1;     // constant for WALL
	
	// variables
	private int dimension;                 // dimension of maze
	private int[][] maze;				   // byte array for maze
    private int[] entry;
    private int[] exit;

 
	
	public LabyrinthBuilder(int dim){
		// make dim odd and set dimension
		setDimension(makeIntOdd(dim));
		// create empty array
		this.maze = new int[getDimension()][getDimension()];
        entry = new int[5];
        exit = new int[5];

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
		this.dimension = dim;
	}

	/**
	 * This makes the input parameter odd
	 * @param x int
	 * @return an odd int
	 */
	public int makeIntOdd(int x){
		 if ( x % 2 == 0 ){
	         x = x + 1;
		 }
		 return x; 
	}

    public int[] getEntry() {
        return entry;
    }

    public void setEntry(int x, int y) {
        entry[0] = x;
        entry[1] = y;
    }

    public int[] getExit() {
        return exit;
    }

    public void setExit(int x, int y) {
        exit[0] = x;
        exit[1] = y;
    }

    /**
	 * @return a random int within the dimension 
	 */
	public int getRandomIntOdd(int dim){
		Random rand = new Random();
		int r = rand.nextInt(dim);
		while (r % 2 == 0) {
			r = rand.nextInt(dim);
		}
		return r;
	}
	
	/**
     * TODO: ENUM
	 * @return int value of WALL constant
	 */
	public static int getWall() {
		return WALL;
	}

	/**
	 * TODO: ENUM
	 * @return in value of PATH constant
	 */
	public static int getPath() {
		return PATH;
	}
	
	/**
	 * This method nicely prints the array to the console.
	 * Only used for Debugging
	 */
	public void printArray(){
		for(int i=0; i < getDimension(); i++) {
			for (int j=0; j < getDimension(); j++) {
				System.out.print(getMaze()[i][j]);
	        }
	        System.out.println();
	    }
		System.out.println();
	}
	
	
}

