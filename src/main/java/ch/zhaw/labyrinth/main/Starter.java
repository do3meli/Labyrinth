package ch.zhaw.labyrinth.main;

import ch.zhaw.labyrinth.builder.DepthFirstSearch;
import ch.zhaw.labyrinth.builder.LabyrinthBuilder;
import ch.zhaw.labyrinth.gui.InitGui;
import ch.zhaw.labyrinth.gui.Labyrinth;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 03.03.13
 * Time: 10:56
 */
public class Starter {
    public static void main(String[] args) {
       
    	int dim = 21;
    	
    	Labyrinth labyrinth = new Labyrinth();
        LabyrinthBuilder lab = new DepthFirstSearch(dim);
      
        int[][] a= lab.getMaze();
        for(int i=0; i<dim; i++) {
            for (int j=0; j<dim; j++) {
                System.out.print(a[i][j]);
                labyrinth.addPoint(i,j,a[i][j]);
            }
            System.out.println();
        }
        // Initialize GUI
        InitGui gui = new InitGui();
        gui.initGui(labyrinth);

    }
}
