package ch.zhaw.labyrinth.utils;

import java.util.HashMap;
import java.util.Random;

/**
 * TODO: JavaDoc
 *
 * @author d.schlegel
 *
 */
public class Labyrinth {
	
	// instance variables
	private HashMap<Coordinate, Cell> maze;
	private int dimension;
    private Coordinate entry;
    private Coordinate exit;
	
	// constructor
	public Labyrinth(int dim){
		this.maze = new HashMap<Coordinate, Cell>();
		setDimension(dim);
		createEmptyMaze();
	}

    public Labyrinth() {
        this.maze = new HashMap<Coordinate, Cell>();
    }
	
	// create empty maze with cell's and coordinate's
	public void createEmptyMaze() {
		
		for (int x = 0; x < getDimension(); x++) {
			for (int y = 0; y < getDimension(); y++) {
				this.maze.put(new Coordinate(x, y), new Cell());
			}
		}
	}

    // getter for dimension
	public int getDimension() {
		return dimension;
	}

    public void setDimension(int dimension) {
        this.dimension = makeIntOdd(dimension);
    }

    // getter for Cell with coordinate attribute
	public Cell getCellAt(Coordinate c){
		return this.maze.get(c);
	}
	
	// getter for Cell with x and y attributes
	public Cell getCellAt(int x, int y){
		return this.maze.get(new Coordinate(x,y));
	}

    public HashMap<Coordinate, Cell> getMaze() {
        return maze;
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
	
	// setter method to set the cell value from a coordinate
	public void setCellValue(Coordinate c,boolean val){
		maze.get(c).setPath(val);
	}
	
	// setter method to set the cell value from an x and y parameter
    public void setCellValue(int x,int y,boolean val){
        Coordinate coordinate = new Coordinate(x,y);
        Cell cell = maze.get(coordinate);
        if(cell == null) {
            cell = new Cell();
            maze.put(coordinate, cell);
        }
        cell.setPath(val);
    }

    // setter method to set the cell value from an x and y parameter
    public void setCellVisited(int x, int y){
        Coordinate coordinate = new Coordinate(x,y);
        Cell cell = maze.get(coordinate);
        cell.incVisits();
    }

    public int isCellVisited(int x, int y){
        Coordinate coordinate = new Coordinate(x,y);
        Cell cell = maze.get(coordinate);
        return cell.getVisits();
    }
	
	// getter method for cell value at specific coordinate
	public boolean getCellValueAt(Coordinate c){
		return maze.get(c).isPath();
	}

    /**
     * Adds a reachable cell to the maze and saves it's predecessor
     *
     * @param coordinate
     * @param predecessor
     */
    public void addPath(Coordinate coordinate, Coordinate predecessor) {
        Cell cell = new Cell();
        cell.setPath(true);
        cell.setPredecessor(predecessor);
        maze.put(coordinate, cell);
    }

    /**
     * Adds a reachable cell to a maze
     *
     * @param coordinate
     */
    public void addPath(Coordinate coordinate) {
        Cell cell = new Cell();
        cell.setPath(true);
        maze.put(coordinate, cell);
    }

    /**
     * removes a cell from a maze
     *
     * @param currentCoordinate
     */
    public void removeCell(Coordinate currentCoordinate) {
        maze.remove(currentCoordinate);
    }
	

    /**
     * getter method for cell value at specific x,y point
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true when path, false when wall
     */
	public boolean getCellValueAt(int x, int y){
        Coordinate coordinate = new Coordinate(x,y);
        Cell cell = maze.get(coordinate);
		return cell.isPath();
	}

    public Coordinate getEntry() {
        return entry;
    }

    public void setEntry(Coordinate entry) {
        this.entry = entry;
    }

    public Coordinate getExit() {
        return exit;
    }
    
    /**
     * This makes an input integer odd by adding 1
     * @return if input is odd it just returns input. if input is even it adds 1 and returns that.
     * @param i an integer
     */
    public int makeIntOdd(int i){
    	if ( (i & 1) == 0 ) { 
    		return i+1; 
    	}else{ 
    		return i; 
    	}
    }

    public void setExit(Coordinate exit) {
        this.exit = exit;
    }
    
    /**
     * Will print the Cell datastructure as an array with zeros and ones.
     * This function is only use if debbuging is active set = true in GUI
     */
    public void printAsArray(){
    	for(int y=0; y < getDimension(); y++) {
			for (int x=0; x < getDimension(); x++) {
                if(getCellAt(x,y) == null) {
                    System.out.print("-");
                    continue;
                }
				if(getCellValueAt(x,y)){
					System.out.print(0);
				}else{
					System.out.print(1);
				}
	        }
	        System.out.println();
	    }
    	System.out.println();
    }



}
