package ch.zhaw.labyrinth.gui.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 12.03.13
 * Time: 09:36
 */
public class ButtonPanel extends JPanel {
    public ButtonPanel() {

        // Buttons
        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton resetButton = new JButton("Reset");

        // ActionListeners for Buttons
        startButton.addActionListener(new StartActionListener());
        pauseButton.addActionListener(new PauseActionListener());
        resetButton.addActionListener(new ResetActionListener());

        add(startButton);
        add(pauseButton);
        add(resetButton);
    }



    /**
     * ActionListeners
     */
    private class StartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //LabyrinthBuilder lbuilder;

            // Get Variables
            //int size = Integer.valueOf(tfSize.getText());
            //int zoom = Integer.valueOf(tfZoom.getText());

            // Set zoom factor
            //labyrinthPanel.setZoom(zoom);

            // Get Build Type
            //String type = (String)createList.getSelectedItem();

            //if (type.equals("Depth-First")) {
            //    lbuilder = new DepthFirstSearch(size);
            //} else {
            //    lbuilder = null;
            //}

            // Build LabyrinthDrawer
            //int[][] a = lbuilder.getMaze();
            //for(int i=0; i<lbuilder.getDimension(); i++) {
            //    for (int j=0; j<lbuilder.getDimension(); j++) {
            //        labyrinthPanel.addPoint(i,j,a[i][j]);
            //    }
            //}

            // TODO: Print LabyrinthDrawer point by point in gui
            //labyrinthPanel.setBounds(195, 0, 605, 478);
            //labyrinthPanel.setVisible(true);
        }
    }

    private class PauseActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Pause LabyrinthDrawer Creation
        }
    }

    private class ResetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Reset LabyrinthDrawer Creation
        }
    }
}
