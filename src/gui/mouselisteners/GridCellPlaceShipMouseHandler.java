package gui.mouselisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import battleship.Battleship;
import battleship.GuiHumanPlayer;
import battleship.Player;
import gui.CellPanel;
import gui.GridGUI;
import gui.Main;

public class GridCellPlaceShipMouseHandler implements MouseListener {

    private GuiHumanPlayer player;
    public GridCellPlaceShipMouseHandler(GuiHumanPlayer p) {
        this.player = p;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}


    @Override
    public void mousePressed(MouseEvent e) {
        if(Main.game.getState() != Battleship.SETUP) {
            return;
        }

        CellPanel cell = (CellPanel) e.getSource();
        GridGUI grid = (GridGUI) cell.getParent();
        //right click rotates ship
        if(e.getButton() == MouseEvent.BUTTON3) {
            player.rotateShip();
            grid.selectAllCells(cell.getRow(), cell.getCol(), player.getNumberCellsOnShip(), player.placeShipDirection);
        } 
        //left click places a ship and updates the players grid gui
        else if(e.getButton() == MouseEvent.BUTTON1) {
            player.placeShip(cell.getRow(), cell.getCol());
            grid.updateGUI();

            //if the player has finished placing ships, have the computer set up their ships and
            // start the game
            if(player.getState() == Player.PLAYING_GAME) {
                Main.game.getEnemyPlayer().placeDestroyer();
                Main.game.getEnemyPlayer().placeCruiser();
                Main.game.getEnemyPlayer().placeSubmarine();
                Main.game.getEnemyPlayer().placeBattleship();
                Main.game.getEnemyPlayer().placeCarrier();
                Main.enemyGrid.updateGUI();

                Main.game.startGame();
            }
        }

    }


    //when user hovers over Grid.
    @Override
    public void mouseEntered(MouseEvent e) {
        if(Main.game.getState() != Battleship.SETUP) {
            return;
        }

        CellPanel cell = (CellPanel) e.getSource();
        ((GridGUI)cell.getParent()).selectAllCells(cell.getRow(), cell.getCol(), player.getNumberCellsOnShip(), player.placeShipDirection);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(Main.game.getState() != Battleship.SETUP) {
            return;
        }

    }
    
}
