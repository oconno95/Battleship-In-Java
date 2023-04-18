package gui;

import battleship.GuiHumanPlayer;
import battleship.Player;
import gui.mouselisteners.GridCellMouseHandler;

public class GridGUIHuman extends GridGUI {

    public GridGUIHuman(Player p, GuiHumanPlayer enemy) {
        super(p);
        for(int r = 0, i = 0; r < GRID_SIZE; r++) {
            for(int c = 0; c < GRID_SIZE; c++, i++) {
                getComponent(i).addMouseListener(new GridCellMouseHandler(r, c, enemy));
            }
        }

    }
    
}
