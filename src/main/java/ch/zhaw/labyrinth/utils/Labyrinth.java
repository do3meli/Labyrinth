package ch.zhaw.labyrinth.utils;

import java.util.HashMap;
import java.util.Map;

public class Labyrinth {
	
	// instance variables
	private Map<Coordinate, Cell> map;
	private int dimension;
	
	// constructor
	public Labyrinth(int dim){
		this.map = new HashMap<Coordinate, Cell>();
		this.dimension = dim;
		createEmptyMap();
	}
	
	// create empty map with cell's and coordinate's
	private void createEmptyMap() {
		
		for (int x = 0; x < getDimension(); x++) {
			for (int y = 0; y < getDimension(); y++) {
				this.map.put(new Coordinate(x,y),new Cell());
			}
		}
	}

    // getter for dimension
	public int getDimension() {
		return dimension;
	}
	
	// getter for Cell with coordinate attribute
	public Cell getCellAt(Coordinate c){
		return this.map.get(c);
	}
	
	// getter for Cell with x and y attributes
	public Cell getCellAt(int x, int y){
		return this.map.get(new Coordinate(x,y));
	}
}
