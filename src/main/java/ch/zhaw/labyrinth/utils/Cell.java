package ch.zhaw.labyrinth.utils;


/**
 * A maze consists out of n-Cells. This class describes a Cell
 *
 * IF path = true 
 * 		=> you can walk throght the cell
 * IF path = false
 * 		=> you can not walk throught the cell
 */
public class Cell {
	
	// variables
	private boolean path;
	private int visits;
	
	// constructor
	public Cell(){
        visits = 0;
		
	}
	
	// getter for path
	public boolean isPath() {
		return path;
	}
	
	// setter for path
	public void setPath(boolean path) {
		this.path = path;
	}
	
	// getter for visits
	public int getVisits() {
		return visits;
	}
	
	// setter for visits
	public void incVisits() {
		this.visits++;
	}
	
}
