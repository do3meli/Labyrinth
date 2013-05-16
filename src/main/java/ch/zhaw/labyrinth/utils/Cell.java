package ch.zhaw.labyrinth.utils;


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
	
	/**
	 * Default constructor for Cell object.
	 * This only sets visits = 0
	 */
	public Cell(){
        visits = 0;
		
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
	
}
