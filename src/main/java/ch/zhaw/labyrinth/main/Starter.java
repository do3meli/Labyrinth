package ch.zhaw.labyrinth.main;

import ch.zhaw.labyrinth.controller.MazeController;
import ch.zhaw.labyrinth.model.utils.MazeModel;
import ch.zhaw.labyrinth.view.MazeView;

/**
 * Main Class to start the labyrinth solver. Everything starts with initializing the GUI
 *
 * @author b.buetikofer
 *
 */
class Starter {
    public static void main(String[] args) { 
    	// Initialize MVC
        MazeModel model = new MazeModel();
        MazeView view = new MazeView(model);
        MazeController controller = new MazeController(model, view);

        //view.setVisible(true);

    }
}
