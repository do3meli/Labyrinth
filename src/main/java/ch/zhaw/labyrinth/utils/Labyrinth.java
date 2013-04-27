package ch.zhaw.labyrinth.utils;

import java.util.HashMap;
import java.util.Map;

public class Labyrinth {
	
	private Map<Coordinate, Cell> map;
	private int dimension;
	
	public Labyrinth(int dim){
		this.map = new HashMap<Coordinate, Cell>();
		this.dimension = dim;
		createEmptyMap();
	}
	
	
	private void createEmptyMap() {
		
		for (int x = 0; x < getDimension(); x++) {
			for (int y = 0; y < getDimension(); y++) {
				this.map.put(new Coordinate(x,y),new Cell());
			}
			
		}
		
	}


	public int getDimension() {
		return dimension;
	}
	
}
