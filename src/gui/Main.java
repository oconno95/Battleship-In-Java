package gui;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import battleship.Battleship;
import battleship.ComputerPlayer;
import battleship.GuiHumanPlayer;
import battleship.Player;
import gui.mouselisteners.GridCellMouseHandler;
import gui.mouselisteners.GridCellPlaceShipMouseHandler;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Main {
    private static final Player COM_PLAYER = new ComputerPlayer();
    private static final GuiHumanPlayer HUMAN_PLAYER = new GuiHumanPlayer();

    public static final Battleship game = new Battleship(HUMAN_PLAYER, COM_PLAYER);
    public static final GridGUI playerGrid = new GridGUI(HUMAN_PLAYER);
    public static final GridGUI enemyGrid = new GridGUI(COM_PLAYER);

    public static void main(String[] args) {
        JFrame f = new JFrame();
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setSize(800, 800);

        //create player grid and add mouse listeners so that player can place ships
        for(Component c : playerGrid.getComponents()) {
            c.addMouseListener(new GridCellPlaceShipMouseHandler(HUMAN_PLAYER));
        }
        

        //create enemys grid and add mouse listeners so that player can fire at enemy
        for(Component c : enemyGrid.getComponents()) {
            c.addMouseListener(new GridCellMouseHandler(HUMAN_PLAYER));
        }

        //add padding to right of playerGrid and left of enemyGrid
        playerGrid.setBorder(new EmptyBorder(0,0,10,10));
        enemyGrid.setBorder(new EmptyBorder(0,10,10,0));

        f.getContentPane().setLayout(new GridBagLayout());

        //set up grids
        f.getContentPane().add(
            playerGrid, 
            new GridBagConstraints(0, 0 , 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,2,2,2), 0, 0)
        );
        f.getContentPane().add(
            enemyGrid, 
            new GridBagConstraints(1, 0 , 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,2,2,2), 0, 0)
        );


        //set message panel
        f.getContentPane().add(
            new MessagePanel(),
            new GridBagConstraints(0, 1, 3, 1, 1.0, 0.4, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,2,2,2), 0, 0)
        );

        //set button panel
        f.getContentPane().add(
            new ButtonPanel(),
            new GridBagConstraints(0, 2, 3, 1, 1.0, 0.4, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,2,2,2), 0, 0)
        );

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setUndecorated(false);
        f.setResizable(true);
        f.setVisible(true);
    }



}
