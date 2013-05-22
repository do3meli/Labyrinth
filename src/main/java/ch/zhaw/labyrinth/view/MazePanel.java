package ch.zhaw.labyrinth.view;

import ch.zhaw.labyrinth.model.utils.Cell;
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
    private Cell curCell;
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
        
        // add the canvas to the frame and make it visible
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        frame.setResizable(false);
        
    }
    
    /**
     * This is responsible for painting the stuff to the screen
     * @param g Graphics
     */
    @Override
	protected void paintComponent(Graphics g){
    	super.paintComponent(g);

//        if(mode.equals("create")) {
//            if (!curCell.isPath()){
//                g.setColor(Color.black);
//                g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, 1*zoom, 1*zoom );
//            }
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
      
    }
    
    /**
     * Sets the preferred window size to the size of the maze * zoom factor
     * @return Dimension
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getDimension()*zoom, getDimension()*zoom);
    }
    
//    @Override
//    public void run() {
//        HashMap<Coordinate, Cell> maze = this.maze.getMaze();
//
//        if(mode.equals("solve")) {
//            curCell = this.maze.getCellAt(curCoordinate);
//            paintComponent(canvas.getGraphics());
//        } else {
//
//            for(int i=0; i< this.maze.getDimension(); i++) {
//                for (int j=0; j< this.maze.getDimension(); j++) {
//
//                    curCoordinate = new Coordinate(i,j);
//                    curCell = maze.get(curCoordinate);
//
//                    if(!(curCell == null)) {
//                        paintComponent(canvas.getGraphics());
//                    }
//                }
//            }
//        }
//    }


    
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
    
//    /**
//     * Getter method for MazeModel instance var
//     * @return MazeModel
//     */
//    public MazeModel getMaze() {
//        return maze;
//    }
//
//    /**
//     * Setter method for MazeModel instance var
//     * @param maze
//     */
//    public void setMaze(MazeModel maze) {
//        this.maze = maze;
//    }
//
//    /**
//     * Setter method for MazeModel instance var.
//     * This also sets the curCoordinate compared to setMaze(MazeModel maze)
//     * @param mazeModel
//     * @param coordinate
//     */
//    public void setLabyrinth(MazeModel mazeModel, Coordinate coordinate) {
//        this.maze = mazeModel;
//        this.curCoordinate = coordinate;
//    }

    /**
     * Update GUI via observer
     */
    @Override
    public void update(Observable observable, Object o) {
        if(mode.equals("AStar")) {
            curCoordinate = (Coordinate)o;
            paintComponent(canvas.getGraphics());
        } else {
            paintComponent(canvas.getGraphics());
        }

        try {
            Thread.sleep(getSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
