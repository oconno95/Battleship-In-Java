package gui.mouselisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import battleship.Battleship;
import battleship.GuiHumanPlayer;
import gui.CellPanel;
import gui.GridGUI;
import gui.Main;

/**
 * This class is for the cells in the GridGUI for the enemy computer player.
 * This is where the user can click on a cell to fire at an enemy ship.
 */
public class GridCellMouseHandler implements MouseListener {

    private GuiHumanPlayer human;

    public GridCellMouseHandler(GuiHumanPlayer human) {
      this.human = human;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
      if(Main.game.getState() != Battleship.GAME) {
        return;
      }

      CellPanel source = (CellPanel) e.getSource();
      int row = source.getRow();
      int col = source.getCol();

      System.out.println("Row = " + row + ", Col = " + col);
      
      human.setLocation(row, col); //required so that the GuiHumanPlayer can fire at the correct spot

      if (Main.game.getState() != Battleship.END) {
        //if the currentPlayer (GuiHumanPlayer) fires a valid shot, the computer will fire when main.game.fire is called again
        if (Main.game.fire()) {
          //try {Thread.sleep(1000);} catch (InterruptedException e1) {e1.printStackTrace();}
          Main.game.fire();
          Main.game.fire(); //computer player will always make a valid shot, so no need to check return value

          //if game has ended, display the win message.
          if(Main.game.gameHasEnded()) {
            Main.game.getWinner().useWinMessage();
            Main.MESSAGE_PANEL.setMessage(Main.game.getWinner().getCurrentMessage());
          } else {
            Main.MESSAGE_PANEL.setMessage(
              Main.game.getCurrentPlayer().getCurrentMessage() + " " + 
              Main.game.getEnemyPlayer().getCurrentMessage()
            );
          }
          
          ((GridGUI) source.getParent()).updateGUI();
          Main.playerGrid.updateGUI();
        }
      }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
      if(Main.game.getState() != Battleship.GAME) {
        return;
      }
      CellPanel source = (CellPanel) e.getSource();
      source.markSelected();
    }

    @Override
    public void mouseExited(MouseEvent e) {
      if(Main.game.getState() != Battleship.GAME) {
        return;
      }
      
      CellPanel source = (CellPanel) e.getSource();
      source.markDefault();

    }
    
}
