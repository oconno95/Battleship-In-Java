package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import battleship.Grid;
import battleship.GuiHumanPlayer;
import battleship.Player;

/**
 * GridGUI represents the Grid object in the battleship package in GUI form.
 * It holds a list of CellPanels that it uses for each square in its grid.
 * MouseListeners can be added to this object to make it interactable.
 */
public class GridGUI extends JPanel {
    public static final int GRID_SIZE = 10;
    private ArrayList<CellPanel> selectedCells = new ArrayList<CellPanel>();
    private Player player;
    private boolean showShips = true;
    
    public GridGUI(Player p) {
        this.player = p;
        setPreferredSize(new Dimension(300,300));
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        for(int r = 0; r < GRID_SIZE; r++) {
            for(int c = 0; c < GRID_SIZE; c++) {
                final CellPanel cell = new CellPanel(r,c);
                cell.setEnabled(true);
                cell.setBackground(Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setPreferredSize(new Dimension(100, 100));
                this.add(cell);
            }
        }
    }

    public GridGUI(Player p, boolean showShips) {
        this(p);
        this.showShips = showShips;
    }

    public final ArrayList<CellPanel> getSelectedCells() {return this.selectedCells;}


    //This function makes it easier to select a group of cells that represent the space where
    //a ship can be. This allows the cells to be highlighted green. If the selected cells overflow
    //to a separate row or column such that a ship cannot be properly placed there, the selected
    //cells will be highlighted red.
    public void selectAllCells(int row, int col, int length, int direction) {
        for(CellPanel cell : this.selectedCells) {
            cell.markDefault();
        }
        this.selectedCells.clear();
        int rowIter = 0;
        int colIter = 0;

        switch(direction) {
            case GuiHumanPlayer.LEFT:
                colIter = -1;
                break;
            case GuiHumanPlayer.RIGHT:
                colIter = 1;
                break;
            case GuiHumanPlayer.UP:
                rowIter = -1;
                break;
            default: 
                rowIter = 1;
                break;
        }

        int r = row;
        int c = col;

        for(int i = 0; i < length; i++) {
            CellPanel cell = this.getCell(r,c);
            if(cell == null || cell.getDefaultColor() == CellPanel.SHIP_COLOR) {
                for(CellPanel p : this.selectedCells) {
                    p.markInvalidSelected();
                }
                return;
            }

            cell.markSelected();
            this.selectedCells.add(cell);
            r += rowIter;
            c += colIter;
            
        }
    }

    public void updateGUI() {
        for(int r = 0; r < 10; r++) {
            for(int c = 0; c < 10; c++) {
                int cell = player.getGrid().getCell(c, r);
                switch(cell) {
                    case Grid.HIT_SHIP:
                        getCell(r,c).markHit();
                        break;
                    case Grid.SUNK_SHIP:
                        getCell(r,c).markSink();
                        break;
                    case Grid.MISS:
                        getCell(r,c).markMiss();
                        break;
                    case Grid.UNHIT_SHIP:
                        //dont display ships to player if showShips is false
                        if(showShips) {
                            getCell(r,c).markShip();
                        } else {
                            getCell(r,c).markEmpty();
                        }
                        break;
                    default:
                        getCell(r,c).markEmpty();
                }
            }
        }
    }

    public CellPanel getCell(int row, int col) {
        if(row < 0 || row >= 10 || col < 0 || col >= 10) {
            return null;
        }
        int i = (row*GRID_SIZE) + col;
        if(i < 0 || i >= this.getComponentCount()) {
            return null;
        }
        return (CellPanel) this.getComponent(i);
    }
    
}