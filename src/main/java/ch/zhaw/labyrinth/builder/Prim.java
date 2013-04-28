package ch.zhaw.labyrinth.builder;

import ch.zhaw.labyrinth.utils.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import ch.zhaw.labyrinth.utils.Labyrinth;

public class Prim extends Labyrinth {
	
	// variables
	private ArrayList<Cell> frontiers;
	private ArrayList<Cell> neighbours;
	
	// constants
	private static final boolean WALL = false;
	private static final boolean PATH = true;

	// public Constructor
	public Prim(int dim) {
		
		// call super constructor
		super(dim);
		
		// init the array lists
		frontiers = new ArrayList<Cell>();
		neighbours = new ArrayList<Cell>();
       
		// Create "walled" Labyrinth
        fillAllCellValues(WALL);

		// now create the maze
		createMaze();
		
	}

    /**
     * Start by picking a cell, making it "in", and setting all its neighbors to "frontier".
     * Proceed by picking a "frontier" cell at random, and carving into it from one of its neighbor cells that are "in".
     * Change that "frontier" cell to "in", and update any of its neighbors that are "out" to "frontier".
     * The Maze is done when there are no more "frontier" cells left
     * (which means there are no more "out" cells left either, so they're all "in").
     */
	
	private void createMaze() {
		
		// create a Random
		Random r = new Random();
		
		// choose random cell and set a way
		int x = r.nextInt(getDimension());
        int y = r.nextInt(getDimension());
		
        // mark it and look for his neighbours the frontier cells
        mark(x,y);
        
        // iterate as long as there are any FRONTIER cells
        while(isAnyFrontier()){
        	
        	// get random cell
        	Cell randCell = getRandomFrontierCell();
        	
        	// now find the neighbours of that random cell
        	// TODO: findNeighbors(randCell.getX(),randCell.getY());
        	
        	// get random neighbour cell
        	int rand = r.nextInt(neighbours.size());
        	Cell n = neighbours.get(rand);
        	 
        	// TODO: mark(randCell.getX(), randCell.getY());
        	
        	
        }
        
     
	}
	
	
	


	private void mark(int x, int y){
		setCellValue(x,y,WALL);
		addFrontier(x-1,y);
		addFrontier(x+1,y);
		addFrontier(x,y-1);
		addFrontier(x,y+1);
	}

	private void addFrontier(int x, int y) {
		
		if( x > 0 && y > 0 && y < getDimension() && x < getDimension() && getCellValueAt(x, y) == WALL ){
            // TODO: frontiers.add(new Cell(x,y));
		}
	}
	
	private boolean isAnyFrontier(){
		
		if(frontiers.isEmpty()){
			return false;
		}else{
			return true;
		}
		
	}
	
	public Cell getRandomFrontierCell(){
		
		// shuffle my arraylist
		Collections.shuffle(frontiers);
		
		// create random
		Random r = new Random();
		int rand = r.nextInt(frontiers.size());
		
		// save random cell
		Cell cell = frontiers.get(rand);
		
		// remove from arraylist
		frontiers.remove(rand);
		
		// return random cell
		return cell;
		
	}
	
	
	public void findNeighbors(int x, int y){
		
		// first clear everything
		neighbours.clear();
		
		// find neighbours and add it to array list
		
		if ( x > 0 && y < getDimension() && x - 1 < getDimension() && getCellValueAt(x-1,y) == WALL){
            // TODO: neighbours.add(new Cell(x-1,y));
		}
		
		if ( x + 1 < getDimension() && y < getDimension() && getCellValueAt(x+1,y) == WALL){
            // TODO: neighbours.add(new Cell(x+1,y));
		}
		
		if ( y >= 1 && getCellValueAt(x,y-1) == WALL ){
            // TODO: neighbours.add(new Cell(x,y-1));
		}
		
		if ( y+1 < getDimension() && getCellValueAt(x,y+1) == WALL ){
			// TODO: neighbours.add(new Cell(x,y+1));
		}
		
	}
	
	
}
