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
        labyrinth.addLine(20,20,20,40);
        labyrinth.addLine(25,25,25,35);
        labyrinth.addLine(20,20,60,20);
        labyrinth.addLine(25,25,55,25);
        labyrinth.addLine(25,35,55,35);
        labyrinth.addLine(20,40,60,40);

        // Initialize GUI
        InitGui gui = new InitGui();
        gui.initGui(labyrinth);

    }
}
