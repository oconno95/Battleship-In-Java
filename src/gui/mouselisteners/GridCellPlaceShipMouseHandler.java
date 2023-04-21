package gui.mouselisteners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import battleship.Battleship;
import battleship.GuiHumanPlayer;
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

    }


    @Override
    public void mouseEntered(MouseEvent e) {
        if(Main.game.getState() != Battleship.SETUP) {
            return;
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(Main.game.getState() != Battleship.SETUP) {
            return;
        }


    }
    
}
