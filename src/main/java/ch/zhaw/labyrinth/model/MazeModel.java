package ch.zhaw.labyrinth.model;

import ch.zhaw.labyrinth.model.utils.Cell;
import ch.zhaw.labyrinth.model.utils.Coordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class represents a maze. 
 * A maze contains a HashMap with a Coordinate as Key
 * and a Cell as value.
 *
 * @author d.schlegel, b.buetikofer
 */
public class MazeModel {
    
	// constants
	private static final String[] CREATE_ALGORITHMS = { "Depth-First", "Import" };
    private static final String[] SOLVE_ALGORITHMS = { "Right-Hand", "A* Search" };

	// instance variables
	private final HashMap<Coordinate, Cell> maze;
	private int dimension;
    private Coordinate entry;
    private Coordinate exit;
	
	
	/**
	 * Default Constructor, initializes the maze
	 */
    public MazeModel() {
        this.maze = new HashMap<Coordinate, Cell>();
    }
	
	/**
	 *  create empty maze with cell's and coordinate's
	 */
	public void createEmptyMaze() {	
		for (int x = 0; x < getDimension(); x++) {
			for (int y = 0; y < getDimension(); y++) {
				this.maze.put(new Coordinate(x, y), new Cell());
			}
		}
	}

    /**
     * getter for dimension
     * @return int Dimension
     */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * set the Dimension. 
	 * makes sure dimension is odd by calling makeIntOdd
	 * @param dimension
     *          the dimension of the maze
	 */
    public void setDimension(int dimension) {
        this.dimension = makeIntOdd(dimension);
    }

    /**
     * getter for Cell with coordinate attribute
     * @param c Coordinate
     * @return Cell
     */
	public Cell getCellAt(Coordinate c){
		return this.maze.get(c);
	}
	
	/**
	 * getter for Cell with x and y attributes
	 * @param x x-axis int value
	 * @param y y-axis int value
	 * @return Cell
	 */
	public Cell getCellAt(int x, int y){
		return this.maze.get(new Coordinate(x,y));
	}
	
	/**
	 * Returns the maze as HashMap
	 * @return maze
     *      the HashMap containing the maze
	 */
    public HashMap<Coordinate, Cell> getMaze() {
        return maze;
    }

    /**
     * Gets a Random integer which is odd
     * @param dim Dimension of the maze as an int
     * * @return a random int within the dimension 
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
	 * setter method to set the cell value from a coordinate
	 * @param c Coordinate
	 * @param val boolean if cell is path = true otherwise false
	 */
	public void setCellValue(Coordinate c,boolean val){
		maze.get(c).setPath(val);
	}
	
	/**
	 * setter method to set the cell value from an x and y parameter
	 * @param x x-axis int
	 * @param y y-axis int
	 * @param val boolean if cell is path = true otherwise false
	 */
    public void setCellValue(int x,int y,boolean val){
        Coordinate coordinate = new Coordinate(x,y);
        Cell cell = maze.get(coordinate);
        if(cell == null) {
            cell = new Cell();
            maze.put(coordinate, cell);
        }
        cell.setPath(val);
    }

    /**
     * setter method to set the cell visited value from an x and y parameter
     * @param x x-axis value
     * @param y y-axis value
     */
    public void setCellVisited(int x, int y){
        Coordinate coordinate = new Coordinate(x,y);
        Cell cell = maze.get(coordinate);
        cell.incVisits();
    }
    
    /**
     * Getter method for visit count of a Cell
     * @param x x-axis int
     * @param y y-axis int
     * @return int with count of cell visited
     * @see Cell
     */
    public int isCellVisited(int x, int y){
        Coordinate coordinate = new Coordinate(x,y);
        Cell cell = maze.get(coordinate);
        return cell.getVisits();
    }
	
	/**
	 * getter method for cell value at specific coordinate
	 * @param c Coordinate
	 * @return val boolean if cell is path = true otherwise false
	 */
	public boolean getCellValueAt(Coordinate c){
        if(maze.get(c) == null) {
            return false;
        } else {
		    return maze.get(c).isPath();
        }
	}

    /**
     * Adds a reachable cell to the maze and saves it's predecessor
     * @param coordinate
     *          the coordinates of the current cell
     * @param predecessor
     *          the coordinates of the predecessor cell
     */
    public void addPath(Coordinate coordinate, Coordinate predecessor) {
        Cell cell = new Cell();
        cell.setPath(true);
        cell.setPredecessor(predecessor);
        maze.put(coordinate, cell);
    }

    /**
     * Adds a reachable cell to a maze
     * @param coordinate
     *      the coordinates to the cell to add
     */
    public void addPath(Coordinate coordinate, Cell cell) {
        maze.put(coordinate, cell);
    }

    /**
     * removes a cell from a maze
     * @param currentCoordinate
     *      the coordinates to the cell to remove
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
        if(cell == null) {
            return false;
        } else {
		    return cell.isPath();
        }
	}
	
	/**
	 * Getter method for Entry
	 * @return Coordinate holding the Entry
	 */
    public Coordinate getEntry() {
        return entry;
    }
    
    /**
     * Setter method for Entry
     * @param entry Coordinate holding the Entry
     */
    public void setEntry(Coordinate entry) {
        this.entry = entry;
    }
    
    /**
     * Getter Method for exit
     * @return Coordinate holding the exit
     */
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
    
    /**
     * Setter method for Exit
     * @param exit Coordinate holding the exit
     */
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

    /**
     * Gets the lowest F
     * @return the coordinates to the cell with the lowest F value
     */
    public Coordinate getLowestF() {
        // TODO: Very ulgy...
        int last = 999999999;  
        Coordinate coordinate = null;

        for (Map.Entry<Coordinate, Cell> entry : maze.entrySet()) {
            if(entry.getValue().getF() <= last) {
                last=entry.getValue().getF();
                coordinate = entry.getKey();
            }
        }

        return coordinate;
    }
    
    /**
     * Getter method for create algorithm constant
     * @return String Array with create algorithms
     */
    public String[] getCreateAlgorithms() {
        return CREATE_ALGORITHMS;
    }
    
    /**
     * Getter method for solve algorithm constant
     * @return String Array with solve algorithms
     */
    public String[] getSolveAlgorithms() {
        return SOLVE_ALGORITHMS;
    }
}
