package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import battleship.Grid;
import battleship.Player;

public class GridGUI extends JPanel {
    public static final int GRID_SIZE = 10;
    private Player player;
    
    public GridGUI(Player p) {
        this.player = p;
        setPreferredSize(new Dimension(300,300));
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for(int r = 0; r < GRID_SIZE; r++) {
            for(int c = 0; c < GRID_SIZE; c++) {
                final CellPanel cell = new CellPanel();
                cell.setEnabled(true);
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setPreferredSize(new Dimension(100, 100));
               // cell.addMouseListener(new GridCellMouseHandler(r, c, p));
                this.add(cell);
            }
        }
    }

    public void updateFire(int row, int col, int result) {
        CellPanel panel = (CellPanel) this.getComponent((row * GRID_SIZE) + col);
        if(result == Grid.MISS) {
            panel.markMiss();
        } else if(result == Grid.HIT_SHIP || result == Grid.SUNK_SHIP) {
            panel.markHit();
        }
    }
}
