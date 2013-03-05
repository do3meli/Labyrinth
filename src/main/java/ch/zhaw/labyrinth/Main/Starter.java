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
        Labyrinth labyrinth = new Labyrinth();
        LabyrinthBuilder lab = new DeepFirstSearch(20);
 
        int[][] a= lab.getMaze();
        for(int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
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
