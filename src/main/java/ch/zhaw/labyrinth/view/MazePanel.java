package ch.zhaw.labyrinth.view;

import ch.zhaw.labyrinth.model.utils.Coordinate;

import javax.swing.*;
import java.awt.*;
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
//    private MazeModel maze;
    private int dimension;
    private int zoom;
    private int speed;

	private JPanel canvas;
    private Coordinate curCoordinate;
    private String mode;


    /**
     * Default constructor, called from the controller
     */
    public MazePanel() {
    	
    }

    /**
     * TODO: JavaDoc
     */
    public void buildFrame(){
    	
    	// create the frame
    	JFrame frame = new JFrame("Labyrinth Solver");   
    	
        // create the JPanel
        canvas = new JPanel();
        canvas.setBorder(BorderFactory.createEmptyBorder(1000, 1000, 1000, 1000));
        canvas.setPreferredSize(new Dimension(getDimension()*zoom, getDimension()*zoom));
    // canvas.setBackground(Color.black);
        
        // add the canvas to the frame and make it visible
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        frame.setResizable(false);
        
        // now make black cells to start with
      //  paintBlackWalls();
    }
    
    /**
     * This is responsible for painting the stuff to the screen
     * @param g Graphics
     */
    @Override
	protected void paintComponent(Graphics g){
    	

        if(mode.equals("create")) {
            
                g.setColor(Color.white);
           
        }
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

        g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, 1*zoom, 1*zoom );
        super.paintComponent(g);
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
        paintComponent(canvas.getGraphics());

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

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    private void paintBlackWalls(){
    	
    	for(int i=0; i< getDimension(); i++) {
          for (int j=0; j< getDimension(); j++) {

              curCoordinate = new Coordinate(i,j);
              paintComponent(canvas.getGraphics());
              
          }
      }
    }
    
}
