package ch.zhaw.labyrinth.Main;

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

        labyrinth.addLine(1,2,3,4);
        labyrinth.addLine(10,20,30,40);
        labyrinth.addLine(25,20,15,10);

        // Initialize GUI
        InitGui gui = new InitGui();
        gui.initGui(labyrinth);

    }
}
