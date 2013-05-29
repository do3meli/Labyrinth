package ch.zhaw.labyrinth.model.utils;


/**
 * A maze consists out of n-Cells. This class describes a Cell
 *
 * IF path = true 
 * 		=> you can walk throght the cell
 * IF path = false
 * 		=> you can not walk throught the cell
 *
 * 	@author b.buetikofer, d.schlegel
 *
 */
public class Cell {
	
	// instance variables
	private boolean path;
	private int visits;
    private Coordinate predecessor;
    private int f,g,h;
	
	/**
	 * Default constructor for Cell object.
	 * A newly create cell has no visits
	 */
	public Cell(){
        visits = 0;
        f = 0;
        g = 0;
        h = 0;
	}

	/**
	 * getter for path instance variable
	 * @return boolean
	 */
	public boolean isPath() {
		return path;
	}
	
	/**
	 * setter for path instance variable
	 * @param path boolean for the path
	 */
	public void setPath(boolean path) {
		this.path = path;
	}
	
	/**
	 * getter for visits instance variable
	 * @return int how many times this cell has been visited already
	 */
	public int getVisits() {
		return visits;
	}
	
	/**
	 * This increases the visits instance var by one
	 */
	public void incVisits() {
		this.visits++;
	}

    /**
     * Returns the coordinates of predecessor from this cell
     * @return Coordinate the coordinate to the predecessor
     */
    public Coordinate getPredecessor() {
        return predecessor;
    }

    /**
     * Sets the predecessor
     * @param predecessor
     */
    public void setPredecessor(Coordinate predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * Getter for F
     * @return int f
     */
    public int getF() {
        return f;
    }
    
    /**
     * setter method for F
     * @param f
     */
    public void setF(int f) {
        this.f = f;
    }
    
    /**
     * Getter method for g
     * @return int g
     */
    public int getG() {
        return g;
    }
    
    /**
     * setter method for G
     * @param g int
     */
    public void setG(int g) {
        this.g = g;
    }
    
    /**
     * getter Method for H
     * @return int h
     */
    public int getH() {
        return h;
    }
    
    /**
     * setter method for H
     * @param h int
     */
    public void setH(int h) {
        this.h = h;
    }
}
