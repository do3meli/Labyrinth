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
	
}
