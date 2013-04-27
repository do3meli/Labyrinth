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
	private boolean visited;
	
	// constructor
	public Cell(){
		
	}
	
	// getter for path
	public boolean isPath() {
		return path;
	}
	
	// setter for path
	public void setPath(boolean path) {
		this.path = path;
	}
	
	// getter for visited
	public boolean isVisited() {
		return visited;
	}
	
	// setter for visited
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
