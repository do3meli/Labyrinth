package ch.zhaw.labyrinth.builder;

import ch.zhaw.labyrinth.utils.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Prim extends LabyrinthBuilder {
	
	// variables
	private ArrayList<Cell> frontiers;
	private ArrayList<Cell> neighbours;


	// public Constructor
	public Prim(int dim) {
		
		// call super constructor
		super(dim);
		
		// init the array lists
		frontiers = new ArrayList<Cell>();
		neighbours = new ArrayList<Cell>();
       
		// Create "walled" LabyrinthDrawer
        fillArray();

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
        	
        	printArray();
        	
        }
        
     
	}
	
	
	


	private void mark(int x, int y){
		setMaze(x,y,getPath());
		addFrontier(x-1,y);
		addFrontier(x+1,y);
		addFrontier(x,y-1);
		addFrontier(x,y+1);
	}

	private void addFrontier(int x, int y) {
		
		if( x > 0 && y > 0 && y < getDimension() && x < getDimension() && getMaze()[x][y] == getWall() ){
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
	
	public void fillArray(){
		for(int x=0;x<getMaze().length;x++){
		    Arrays.fill( getMaze()[x], getWall() );
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
		
		if ( x > 0 && y < getDimension() && x - 1 < getDimension() && getMaze()[x-1][y] == getWall()){
			// TODO: neighbours.add(new Cell(x-1,y));
		}
		
		if ( x + 1 < getDimension() && y < getDimension() && getMaze()[x+1][y] == getWall()){
            // TODO:neighbours.add(new Cell(x+1,y));
		}
		
		if ( y >= 1 && getMaze()[x][y-1] == getWall() ){
            // TODO:neighbours.add(new Cell(x,y-1));
		}
		
		if ( y+1 < getDimension() && getMaze()[x][y+1] == getWall() ){
            // TODO:neighbours.add(new Cell(x,y+1));
		}
		
	}
	
	
}
