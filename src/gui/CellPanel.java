package gui;

import java.awt.Color;

import javax.swing.JPanel;

public class CellPanel extends JPanel {
    public static final Color MISS_COLOR = Color.LIGHT_GRAY;
    public static final Color HIT_COLOR = Color.RED;
    public static final Color EMPTY_COLOR = Color.WHITE;
    public static final Color SHIP_COLOR = Color.BLACK;
    public static final Color VALID_SELECT_COLOR = Color.GREEN.brighter();
    public static final Color INVALID_SELECT_COLOR = Color.RED;


    private Color defaultColor = EMPTY_COLOR;    
    private Color currentColor = defaultColor;

    private int row, col;

    public CellPanel(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Color getDefaultColor() {return this.defaultColor;}

    public int getRow() {return row;}
    public int getCol() {return col;}

    private void setDefaultColor(Color color) {
        this.defaultColor = color;
    }

    public void markHit() {
        this.setDefaultColor(HIT_COLOR);
        this.update(HIT_COLOR);
    }

    public void markMiss() {
        this.setDefaultColor(MISS_COLOR);
        this.update(MISS_COLOR);
    }

    public void markShip() {
        this.setDefaultColor(SHIP_COLOR);
        this.update(SHIP_COLOR);
    }

    public void markEmpty() {
        this.update(EMPTY_COLOR);
    }

    public void markSelected() {
        this.update(VALID_SELECT_COLOR);
    }

    public void markInvalidSelected() {
        this.update(INVALID_SELECT_COLOR);
    }

    public void markDefault() {
        this.update(this.defaultColor);
    }

    public void update(Color newColor) {
        this.currentColor = newColor;
        this.setBackground(this.currentColor);
    }

}
