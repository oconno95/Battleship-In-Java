package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import gui.mouselisteners.GridCellMouseHandler;

public class GridGUI extends JPanel {
    public static final int GRID_SIZE = 10;
    
    public GridGUI() {
        setPreferredSize(new Dimension(300,300));
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for(int r = 0; r < GRID_SIZE; r++) {
            for(int c = 0; c < GRID_SIZE; c++) {
                final JPanel cell = new JPanel();
                cell.setEnabled(true);
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setPreferredSize(new Dimension(100, 100));
                cell.addMouseListener(new GridCellMouseHandler(r, c));
                this.add(cell);
            }
        }
    }
}
