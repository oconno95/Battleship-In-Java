package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridGUI extends JPanel {
    public static final int GRID_SIZE = 10;
    
    public GridGUI() {
        setPreferredSize(new Dimension(300,300));
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for(int r = 0; r < GRID_SIZE; r++) {
            for(int c = 0; c < GRID_SIZE; c++) {
                final JButton button = new JButton();
                button.addMouseListener(new MouseHandler());
                this.add(button);
            }
        }
    }
}
