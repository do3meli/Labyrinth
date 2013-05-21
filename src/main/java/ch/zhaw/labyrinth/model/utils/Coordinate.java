package ch.zhaw.labyrinth.model.utils;

/**
 * This class represents the x and y-coordinate of a cell
 *
 * @author b.buetikofer
 */
public class Coordinate {
	
	// instance variables
	private int x;
	private int y;

    /**
     *
     * Default constructor. A coordinate has always an x- and y-value
     *
     * @param x x-Coordinate
     * @param y y-coordinate
     */
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter method for x
	 * @return int the x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter method for y
	 * @return int the y coordinate
	 */
	public int getY() {
		return y;
	}

    
    /**
     * A cell is equals whenever the x and y coordinate are the same
     * @return boolean which says if objects are equals or not
     */
	@Override
    public boolean equals(Object o) {
        
    	if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()){
        	return false;
        }

        Coordinate that = (Coordinate) o;

        if (x != that.x){
        	return false;
        }
        if (y != that.y){
        	return false;
        }

        return true;
    }
	
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
