package ch.zhaw.labyrinth.gui.panels;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 12.03.13
 * Time: 09:23
 */
public class ConfigPanel extends JPanel {
    private static final String[] createAlgorithms = { "Depth-First", "Prim", "Kruskal"};
    private static final String[] solveAlgorithms = { "Wall-Follower", "Right-Hand", "Trémaux", "Backtrack", "Shortest Path"};
    private JComboBox solveList;
    private JComboBox createList;
    private JLabel createLabel;
    private JLabel solveLabel;
    private JTextField tfSize;
    private JTextField tfZoom;

    public ConfigPanel() {
        createLabel = new JLabel("Create Algorithm");
        createLabel.setBounds(6, 6, 107, 16);
        createList = new JComboBox(createAlgorithms);
        createList.setBounds(0, 23, 184, 27);
        solveLabel = new JLabel("Solve Algorithm");
        solveLabel.setBounds(6, 221, 100, 16);
        solveList = new JComboBox(solveAlgorithms);
        solveList.setBounds(0, 242, 184, 27);

        setLayout(null);
        setBounds(0, 0, 196, 478);

        // Size and Zoom
        JLabel lblSize = new JLabel("Size: ");
        lblSize.setBounds(6, 55, 61, 16);
        tfSize = new JTextField("41");
        tfSize.setBounds(50, 49, 134, 28);
        tfSize.setColumns(10);
        JLabel lblZoom = new JLabel("Zoom: ");
        lblZoom.setBounds(6, 83, 61, 16);
        tfZoom = new JTextField("4");
        tfZoom.setBounds(50, 83, 134, 28);
        tfZoom.setColumns(10);

        // Horizontal Separator
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 193, 196, 16);

        // Add everything together
        add(createLabel);
        add(createList);
        add(tfSize);
        add(tfZoom);
        add(lblSize);
        add(lblZoom);

        add(separator);

        add(solveLabel);
        add(solveList);

        // Add Button Panel
        ButtonPanel bPanel = new ButtonPanel();
        bPanel.setBounds(0, 338, 196, 140);
        add(bPanel);
    }
}
