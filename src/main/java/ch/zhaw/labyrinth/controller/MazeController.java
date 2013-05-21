package ch.zhaw.labyrinth.controller;

import ch.zhaw.labyrinth.model.builder.Builder;
import ch.zhaw.labyrinth.model.builder.DepthFirstSearch;
import ch.zhaw.labyrinth.model.solver.AStar;
import ch.zhaw.labyrinth.model.solver.RightHand;
import ch.zhaw.labyrinth.model.utils.MazeModel;
import ch.zhaw.labyrinth.view.MazeView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private Builder mazeBuilder;

    /**
     *
     * @param model
     * @param view
     */
    public MazeController(MazeModel model, MazeView view) {
        this.model = model;
        this.view = view;

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
            int size = view.getSize();

            // Get Build Type
            String createAlgorithm = view.getCreateAlgorithm();

            // Build selected LabyrinthDrawer
            if (createAlgorithm.equals("Depth-First")) {
                mazeBuilder = new DepthFirstSearch(size);
            } else if (createAlgorithm.equals("Import")) {
                view.showFileChooser();
            } else {
                mazeBuilder = null;
            }

            // create the labyrinth drawer and active button
            view.createLabyrinth(); // TODO: Move this into model

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
//            // Build selected LabyrinthDrawer
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
