package gui.mouselisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import battleship.Battleship;
import battleship.GuiHumanPlayer;
import gui.CellPanel;
import gui.Main;

public class GridCellMouseHandler implements MouseListener {

    private GuiHumanPlayer human;

    public GridCellMouseHandler(GuiHumanPlayer human) {
      this.human = human;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
      if(Main.game.getState() != Battleship.GAME) {
        return;
      }

      CellPanel source = (CellPanel) e.getSource();
      int row = source.getRow();
      int col = source.getCol();

      System.out.println("Row = " + row + ", Col = " + col);
      

      source.changeColorOnClick();

      human.setLocation(row, col);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if(Main.game.getState() != Battleship.GAME) {
        return;
      }

      CellPanel source = (CellPanel) e.getSource();
      source.changeColorOnMouseRelease();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {
      if(Main.game.getState() != Battleship.GAME) {
        return;
      }
      
      CellPanel source = (CellPanel) e.getSource();
      source.changeColorOnMouseRelease();

    }
    
}
