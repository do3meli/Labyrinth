package ch.zhaw.labyrinth.controller;

import ch.zhaw.labyrinth.model.builder.Builder;
import ch.zhaw.labyrinth.model.builder.DepthFirstSearch;
import ch.zhaw.labyrinth.model.MazeModel;
import ch.zhaw.labyrinth.view.MazePanel;
import ch.zhaw.labyrinth.view.MazeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Controller
 */
public class MazeController {
    private MazeModel model;
    private MazeView view;
    private MazePanel mazePanel;
    private Builder mazeBuilder;

    /**
     *
     * @param model
     * @param view
     */
    public MazeController(MazeModel model, MazeView view) {
        this.model = model;
        this.view = view;

        mazePanel = new MazePanel();

        // Create Gui
        view.createAndShowGUI();

        // Add Listeners
        view.addImportListener(new ImportItemListener());
        view.addCreateListener(new CreateActionListener());
        //view.addSolveListener(new SolveActionListener());
        //view.addChangeSpeedListener(new SpeedChangeAction());


    }


    /**
     * ActionListener for Create Algorithm Button
     */
    private class CreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Get Variables from GUI text boxes
            int dimension = view.getDimension();

            // Get Build Type
            String createAlgorithm = view.getCreateAlgorithm();

            // Build selected MazePanel
            if (createAlgorithm.equals("Depth-First")) {
                mazeBuilder = new DepthFirstSearch(model, dimension);
            } else if (createAlgorithm.equals("Import")) {
                view.showFileChooser();
            } else {
                mazeBuilder = null;
            }

            // Configure the MazePanel object
            mazePanel.setMode("Create");
            mazePanel.setZoom(view.getZoom());
            mazePanel.setDimension(view.getDimension());
            mazePanel.setSpeed(view.getSpeed());

            // Build panel
            mazePanel.buildFrame();

            // Register observer
            mazeBuilder.registerObserver(mazePanel);

            // Build Maze
            mazeBuilder.build();

        }
    }

//    /**
//     * ActionListener for Solve Button
//     */
//    private class SolveActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//            // Get Build Type
//            String type = view.getSolveAlgorithm();
//
//            // Build selected MazePanel
//            if (type.equals("Right-Hand")) {
//                setLbsolver(new RightHand(lbuilder));
//            } else if (type.equals("A* Search")) {
//                setLbsolver(new AStar(lbuilder));
//            } else {
//                setLbsolver(null);
//            }
//            setMode("solve");
//            startSolver();
//
//
//        }
//    }
//
//    private class SpeedChangeAction implements ChangeListener {
//        @Override
//        public void stateChanged(ChangeEvent e) {
//            setSpeed(slider.getValue());
//            String str = Integer.toString(getSpeed());
//            sliderLabel.setText(str);
//        }
//    }

    /**
     * This ItemListeners does fire when the item of the createList is getting changed.
     * if the selected item is Import it does show JFileChooser otherwise it does nothing.
     * @author d.schlegel
     */
    private class ImportItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED && e.getItem() == "Import") {

                view.showFileChooser();
            }
        }
    }
}
