package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
    public ButtonPanel() {
        setPreferredSize(new Dimension(300, 100));
        setLayout(new FlowLayout());

        final JButton resetButton = new JButton("Restart Game");
        final JButton exitButton = new JButton("Exit Game");

        //add mouse listeners

        //this will reset the battleship game
        resetButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
        
            @Override
            public void mousePressed(MouseEvent e) {
                Main.reset();
            }
        
            @Override
            public void mouseReleased(MouseEvent e) {}
        
            @Override
            public void mouseEntered(MouseEvent e) {}
        
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        //this will close the program
        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
        
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        
            @Override
            public void mouseReleased(MouseEvent e) {}
        
            @Override
            public void mouseEntered(MouseEvent e) {}
        
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        this.add(resetButton);
        this.add(exitButton);
    }
}
