package gui;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setSize(600, 400);
        f.add(new GridGUI());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setUndecorated(false);
        f.setResizable(true);
        f.setVisible(true);
    }
}
