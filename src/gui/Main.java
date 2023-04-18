package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setSize(600, 400);

        GridGUI playerGrid = new GridGUI();
        GridGUI enemyGrid = new GridGUI();
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
