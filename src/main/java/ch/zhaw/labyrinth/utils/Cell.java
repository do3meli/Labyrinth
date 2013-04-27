package ch.zhaw.labyrinth.utils;

public class Cell {
	
	// constants
	private static final int PATH = 0;	
	private static final int WALL = 1;
	
	// variables
	private int value;
	private boolean visited;
	
	// constructor
	public Cell(){
		
	}
	
	private void setValue(int v){
		this.value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setWall(){
		setValue(WALL);
	}
	
	public void setPath(){
		setValue(PATH);
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
