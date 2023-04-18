package gui.mouselisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import battleship.GuiHumanPlayer;
import gui.CellPanel;

public class GridCellMouseHandler implements MouseListener {

    private int row, col;
    private GuiHumanPlayer human;

    public GridCellMouseHandler(int row, int col, GuiHumanPlayer human) {
      this.row = row;
      this.col = col;
      this.human = human;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
      System.out.println("Row = " + row + ", Col = " + col);
      CellPanel source = (CellPanel) e.getSource();
      source.changeColorOnClick();

      human.setLocation(row, col);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      CellPanel source = (CellPanel) e.getSource();
      source.changeColorOnMouseRelease();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {
      CellPanel source = (CellPanel) e.getSource();
      source.changeColorOnMouseRelease();
    }
    
}
