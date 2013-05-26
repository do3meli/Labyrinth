package ch.zhaw.labyrinth.view;

import ch.zhaw.labyrinth.model.utils.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

/**
 * Draws a given Builder or Solver Algorithm to the screen
 * @author d.schlegel
 */
public class MazePanel extends JPanel implements Observer {
   
	// serial version UID
	private static final long serialVersionUID = 623347107962887545L;
	
	// instance var
    private int dimension;
    private int zoom;
    private int speed;
    private Coordinate curCoordinate;
    private String mode;
    private static JPanel panel;
    private BufferedImage buffImg;


    /**
     * Default constructor, called from the controller
     */
    public MazePanel() {
        panel = this;
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

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public void setBuffImg(BufferedImage buffImg) {
        this.buffImg = buffImg;
    }

    @Override
    protected void paintComponent(Graphics g) {

    	super.paintComponent(g);

        g.drawImage(getBuffImg(), 0, 0, null);
//        if(mode.equals("create")) {
//                g.setColor(Color.white);
//        }


//        } else if (mode.equals("solve")) {
//            if(maze.getCellAt(curCoordinate.getX(), curCoordinate.getY()).getVisits() > 1) {
//                g.setColor(Color.blue);
//            } else {
//                g.setColor(Color.green);
//            }
//            g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, 1*zoom, 1*zoom );
//        } else if (mode.equals("AStar")) {
//            g.setColor(Color.orange);
//            g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, 1*zoom, 1*zoom );
//        }

//        g.fillRect(curCoordinate.getX() * zoom, curCoordinate.getY() * zoom, zoom, zoom);


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
       
    	curCoordinate = (Coordinate)o;

        BufferedImage bi = getBuffImg();
        Graphics g = bi.getGraphics();
        g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, zoom, zoom );
        g.drawImage(getBuffImg(), 0, 0, null);

        try {
            Thread.sleep(getSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter method for mode instance variable
     * @return String with Draw mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Setter method for instance variable mode
     * @param mode either solve or create
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getDimension() {
        return dimension;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
