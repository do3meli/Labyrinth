package ch.zhaw.labyrinth.gui;

import ch.zhaw.labyrinth.utils.Cell;
import ch.zhaw.labyrinth.utils.Coordinate;
import ch.zhaw.labyrinth.utils.Labyrinth;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Draws a given Builder or Solver Algorithm to the screen
 * @author d.schlegel
 */
public class LabyrinthDrawer extends JPanel implements Runnable, Observer {
   
	// serial version UID
	private static final long serialVersionUID = 623347107962887545L;
	
	// instance var
	private JPanel canvas;
    private Labyrinth labyrinth;
    private int zoom;
    private Cell curCell;
    private Coordinate curCoordinate;
    private String mode;
    private Gui gui;

    /**
     * Default Construktor
     * @param labyrinth
     * @param mode either create or solve
     * @param gui 
     */
    public LabyrinthDrawer(Labyrinth labyrinth, String mode, Gui gui) {
        this.gui = gui;
        this.labyrinth = labyrinth;
        this.zoom = gui.getTfZoom();
        this.mode = mode;

        buildFrame();
    }
    
    
    private void buildFrame(){
    	
    	// create the frame
    	JFrame frame = new JFrame("Labyrinth Solver");   
    	
        // create the JPanel
        canvas = new JPanel();
        canvas.setBorder(BorderFactory.createEmptyBorder(1000, 1000, 1000, 1000));
        canvas.setPreferredSize(new Dimension(labyrinth.getDimension()*zoom, labyrinth.getDimension()*zoom));
        
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

        if(mode.equals("create")) {
            if (!curCell.isPath()){
                g.setColor(Color.black);
                g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, 1*zoom, 1*zoom );
            }
        } else if (mode.equals("solve")) {
            if(labyrinth.getCellAt(curCoordinate.getX(), curCoordinate.getY()).getVisits() > 1) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.green);
            }
            g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, 1*zoom, 1*zoom );
        } else if (mode.equals("AStar")) {
            g.setColor(Color.orange);
            g.fillRect(curCoordinate.getX()*zoom, curCoordinate.getY()*zoom, 1*zoom, 1*zoom );
        }
      
    }
    
    /**
     * Sets the preferred window size to the size of the maze * zoom factor
     * @return Dimension
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(labyrinth.getDimension()*zoom, labyrinth.getDimension()*zoom);
    }
    
    @Override
    public void run() {
        HashMap<Coordinate, Cell> maze = labyrinth.getMaze();

        if(mode.equals("solve")) {
            curCell = labyrinth.getCellAt(curCoordinate);
            paintComponent(canvas.getGraphics());
        } else {

            for(int i=0; i< labyrinth.getDimension(); i++) {
                for (int j=0; j< labyrinth.getDimension(); j++) {

                    curCoordinate = new Coordinate(i,j);
                    curCell = maze.get(curCoordinate);

                    if(!(curCell == null)) {
                        paintComponent(canvas.getGraphics());
                    }
                }
            }
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
    
    /**
     * Getter method for Labyrinth instance var
     * @return Labyrinth
     */
    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    /**
     * Setter method for Labyrinth instance var
     * @param labyrinth
     */
    public void setLabyrinth(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    /**
     * Setter method for Labyrinth instance var.
     * This also sets the curCoordinate compared to setLabyrinth(Labyrinth labyrinth)
     * @param labyrinth
     * @param coordinate
     */
    public void setLabyrinth(Labyrinth labyrinth, Coordinate coordinate) {
        this.labyrinth = labyrinth;
        this.curCoordinate = coordinate;
    }
    
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
            Thread.sleep(gui.getSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
