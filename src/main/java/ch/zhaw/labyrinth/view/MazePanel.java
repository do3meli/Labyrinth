package ch.zhaw.labyrinth.view;

import ch.zhaw.labyrinth.model.utils.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Draws a given Builder or Solver Algorithm to the screen
 * @author d.schlegel, b.buetikofer
 */
public class MazePanel extends JPanel implements Observer {
   
	// serial version UID
	private static final long serialVersionUID = 623347107962887545L;
	
	// instance var
    private int dimension;
    private final int zoom;
    private int speed;
    private BufferedImage buffImg;
    private Color color;


    /**
     * Default constructor, called from the controller
     * @param zoom 
     * @param dim 
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
     * TODO: JavaDoc
     */
    public void buildPanel(){

        // create the JPanel
        setBorder(BorderFactory.createEmptyBorder(1000, 1000, 1000, 1000));
        setPreferredSize(new Dimension(getDimension() * zoom, getDimension() * zoom));
        setBackground(Color.black);

    }

    /**
     * TODO: JavaDoc
     * @return BufferedImage
     */
    public BufferedImage getBuffImg() {
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
     * Setter method for instance variable mode
     * @param mode either solve or create
     */
    public void setMode(String mode) {
    }

    /**
     * getter method for dimension
     * @return int dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * getter method for speed
     * @return int Speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * setter method for speed
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * getter method for zoom
     * @return int zoom
     */
    public int getZoom() {
		return zoom;
	}

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
