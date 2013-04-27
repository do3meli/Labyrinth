package ch.zhaw.labyrinth.utils;

/**
 * This class represents the x and y-coordinate of a cell
 *
 * @param x
 *  x-Coordinate
 * @param y
 *  y-coordinate
 */
public class Coordinate {
	
	// instance variables
	private int x;
	private int y;
	
	// constructor
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// getter for x
	public int getX() {
		return x;
	}
	
	// getter for y
	public int getY() {
		return y;
	}
	
}
