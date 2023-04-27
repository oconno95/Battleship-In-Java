package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel {
    private JLabel message = new JLabel("This is where messages are displayed!");

    public MessagePanel() {
        setPreferredSize(new Dimension(300, 100));
        setLayout(new FlowLayout());

        this.add(message);
    }

    public void setMessage(String text) {
        message.setText(text);
    }

    public void setMessagePlacement(String text) {
        String pleasePlace = new String("Please place your ");
        String placingInstructions = new String(".  ---  Left Click to place the ship and Right Click to rotate the ship");

        message.setText(pleasePlace + text + placingInstructions);
    }
}
