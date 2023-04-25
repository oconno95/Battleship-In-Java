package gui;

import battleship.*;
import gui.mouselisteners.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Main {
    private static final Player COM_PLAYER = new ComputerPlayer(2);
    private static final GuiHumanPlayer HUMAN_PLAYER = new GuiHumanPlayer();

    //PUBLIC STATIC FINAL variables that can be accessed throughout the entire program.
    //Because only one of these objects will ever exist, this should be OK.
    public static final Battleship game = new Battleship(HUMAN_PLAYER, COM_PLAYER);
    public static final GridGUI playerGrid = new GridGUI(HUMAN_PLAYER);
    public static final GridGUI enemyGrid = new GridGUI(COM_PLAYER);
    public static final MessagePanel MESSAGE_PANEL = new MessagePanel();

    public static void reset() {
        //first message should ask the user to place their first ship, the destroyer
        MESSAGE_PANEL.setMessage("Please place your destroyer.");

        //give user choice of difficulty
        String[] options = {"Easy", "Medium", "Hard"};

        int difficulty = 0;
        do {
            //difficulty is same as the index for the options array (0=easy, 1=medium, 2=hard)
            difficulty = JOptionPane.showOptionDialog(
                null, 
                "Select the difficulty:", 
                "Difficulty Selector", 
                JOptionPane.OK_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null,
                options,
                null
            );
        }
        while(difficulty == -1); //-1 is set when user closes JOptionPane without selecting an option

        System.out.println(difficulty);

        
        Main.game.reset();
        Main.enemyGrid.updateGUI();
        Main.playerGrid.updateGUI();
    }
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
            MESSAGE_PANEL,
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

        Main.reset();
    }

}