package gui;

import java.awt.Color;

import javax.swing.JPanel;

public class CellPanel extends JPanel {
    public static final Color MISS_COLOR = Color.LIGHT_GRAY;
    public static final Color HIT_COLOR = Color.RED;
    public static final Color EMPTY_COLOR = Color.WHITE;
    public static final Color SHIP_COLOR = Color.BLACK;

    private Color defaultColor = EMPTY_COLOR;    
    private Color currentColor = defaultColor;

    private int row, col;

    public CellPanel(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {return row;}
    public int getCol() {return col;}


    public void markHit() {
        this.update(HIT_COLOR);
    }

    public void markMiss() {
        this.update(MISS_COLOR);
    }

    public void markShip() {
        this.update(SHIP_COLOR);
    }

    public void changeColorOnClick() {
        this.update(this.currentColor.darker());
    }

    public void changeColorOnMouseRelease() {
        this.update(this.defaultColor);
    }
    public void update(Color newColor) {
        this.currentColor = newColor;
        this.setBackground(this.currentColor);
    }

}
