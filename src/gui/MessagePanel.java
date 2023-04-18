package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel {
    public MessagePanel() {
        setPreferredSize(new Dimension(300, 100));
        setLayout(new FlowLayout());

        this.add(new JLabel("This is where messages are displayed!"));
    }
}
