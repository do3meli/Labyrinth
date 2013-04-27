package ch.zhaw.labyrinth.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

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
	
	// fill empty Cell values with something
	public void fillAllCellValues(boolean value){
		
		for (Entry<Coordinate, Cell> coordinate : map.entrySet() ) { 
			coordinate.getValue().setPath(value); 
		} 
	}
	
	 /**
	  * @return a random int within the dimension 
	 */
	public int getRandomIntOdd(int dim){
		Random rand = new Random();
		int r = rand.nextInt(dim);
		while (r % 2 == 0) {
			r = rand.nextInt(dim);
		}
		return r;
	}
	
	// setter method to set the cell value from a coordinate
	public void setCellValue(Coordinate c,boolean val){
		map.get(c).setPath(val);
	}
	
	// setter method to set the cell value from an x and y parameter
	public void setCellValue(int x,int y,boolean val){
		map.get(new Coordinate(x,y)).setPath(val);
	}
	
	// getter method for cell value at specific coordinate
	public boolean getCellValueAt(Coordinate c){
		return map.get(c).isPath();
	}
	
	// getter method for cell value at specific x,y point
	public boolean getCellValueAt(int x, int y){
		return map.get(new Coordinate(x,y)).isPath();
	}
}
