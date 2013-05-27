package ch.zhaw.labyrinth.main;

import ch.zhaw.labyrinth.controller.MazeController;
import ch.zhaw.labyrinth.model.MazeModel;
import ch.zhaw.labyrinth.view.MazeView;

/**
 * Main Class to start the labyrinth solver.
 *
 * 1) create a new model instance
 * 2) create a new view
 * 3) create the controller and start the application
 *
 * @author b.buetikofer
 *
 */
public class Starter {
    public static void main(String[] args) { 
    	// Initialize MVC
        MazeModel model = new MazeModel();
        MazeView view = new MazeView();

        MazeController controller = new MazeController(model, view);

    }
}
