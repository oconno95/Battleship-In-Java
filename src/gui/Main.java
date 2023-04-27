package gui;

import battleship.*;
import gui.mouselisteners.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Main {
    private static final ComputerPlayer COM_PLAYER = new ComputerPlayer();
    private static final GuiHumanPlayer HUMAN_PLAYER = new GuiHumanPlayer();

    //PUBLIC STATIC FINAL variables that can be accessed throughout the entire program.
    //Because only one of these objects will ever exist, this should be OK.
    public static final Battleship game = new Battleship(HUMAN_PLAYER, COM_PLAYER);
    public static final GridGUI playerGrid = new GridGUI(HUMAN_PLAYER);
    public static final GridGUI enemyGrid = new GridGUI(COM_PLAYER, false);
    public static final MessagePanel MESSAGE_PANEL = new MessagePanel();

    public static void reset() {
        //first message should ask the user to place their first ship, the destroyer
        MESSAGE_PANEL.setMessagePlacement("destroyer");

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

        System.out.println("Difficulty: " + difficulty);

        Main.COM_PLAYER.setDifficulty(difficulty);
        
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

        JPanel playerGridWrapper = new JPanel();
        playerGridWrapper.setLayout(new BoxLayout(playerGridWrapper, BoxLayout.Y_AXIS));
        playerGridWrapper.add(playerGrid);
        playerGridWrapper.add(new JLabel("Your Grid"));


        JPanel enemyGridWrapper = new JPanel();
        enemyGridWrapper.setLayout(new BoxLayout(enemyGridWrapper, BoxLayout.Y_AXIS));
        enemyGridWrapper.add(enemyGrid);
        enemyGridWrapper.add(new JLabel("Enemy Grid"));

        //add padding to right of playerGridWrapper and left of enemyGridWrapper
        playerGridWrapper.setBorder(new EmptyBorder(0,0,10,10));
        enemyGridWrapper.setBorder(new EmptyBorder(0,10,10,0));


        //set up grids
        f.getContentPane().add(
            playerGridWrapper, 
            new GridBagConstraints(0, 0 , 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,2,2,2), 0, 0)
        );
        f.getContentPane().add(
            enemyGridWrapper, 
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