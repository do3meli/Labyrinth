package ch.zhaw.labyrinth.view;

import ch.zhaw.labyrinth.model.utils.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * This class represents a JPanel in which a given Builder or Solver Algorithm
 * prints the maze
 *
 * @author d.schlegel, b.buetikofer
 */
public class MazePanel extends JPanel implements Observer {
   
	// serial version UID
	private static final long serialVersionUID = 623347107962887545L;
	
	// instance var
    private final int dimension;
    private final int zoom;
    private int speed;
    private final BufferedImage buffImg;
    private Color color;


    /**
     * Default constructor, called from the controller
     * @param zoom
     *          The zoom factor
     * @param dim
     *          The dimension of the maze
     */
    public MazePanel(int dim, int zoom) {
      this.dimension = dim;
      this.zoom = zoom; 
      this.buffImg = new BufferedImage(getDimension() * getZoom(), getDimension() * getZoom(), BufferedImage.TYPE_INT_RGB);
      prepareBuffImg();
    }


    /**
     * this prepares the buffered image with all black
     */
    private void prepareBuffImg(){
    	  BufferedImage bi = getBuffImg();
          Graphics2D g = bi.createGraphics();
          g.setPaint(Color.black);
          g.fillRect(0, 0, getBuffImg().getWidth(), getBuffImg().getHeight());
    }

    /**
     * This builds the boderer and sets the correct size of the frame
     */
    public void buildPanel(){

        // create the JPanel
        setBorder(BorderFactory.createEmptyBorder(1000, 1000, 1000, 1000));
        setPreferredSize(new Dimension(getDimension() * zoom, getDimension() * zoom));
        setBackground(Color.black);

    }

    /**
     * getter method for BufferedImages which is used to store the graphical maze representation
     * @return BufferedImage
     */
    BufferedImage getBuffImg() {
        return buffImg;
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(getBuffImg(), 0, 0, this);
    }


    /**
     * Sets the preferred window size to the size of the maze * zoom factor
     * @return Dimension
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getDimension()*zoom, getDimension()*zoom);
    }


    /**
     * Update GUI via observer
     */
    @Override
    public void update(Observable observable, Object o) {

        Coordinate curCoordinate = (Coordinate) o;

        BufferedImage bi = getBuffImg();
        Graphics2D g = bi.createGraphics();

        g.setPaint(getColor());

        g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, zoom, zoom );
        g.drawImage(getBuffImg(), 0, 0, this);
        repaint();
        
        try {
            Thread.sleep(getSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter method for dimension
     * @return int dimension
     */
    int getDimension() {
        return dimension;
    }

    /**
     * getter method for speed
     * @return int Speed
     */
    int getSpeed() {
        return speed;
    }

    /**
     * setter method for speed
     * @param speed
     *          The pause time in ms
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * getter method for zoom
     * @return int zoom
     */
    int getZoom() {
		return zoom;
	}
    
    /**
     * sets the color to the given parameter
     * @param color
     *      The color in which to print
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * gets the color
     * @return Color 
     */
    Color getColor() {
        return color;
    }
}
