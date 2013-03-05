package ch.zhaw.labyrinth.Main;

import ch.zhaw.labyrinth.builder.DeepFirstSearch;
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
       
    	int dim = 20;
    	
    	Labyrinth labyrinth = new Labyrinth();
        LabyrinthBuilder lab = new DeepFirstSearch(dim);
 
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
