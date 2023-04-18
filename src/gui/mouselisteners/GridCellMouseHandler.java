package gui.mouselisteners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GridCellMouseHandler implements MouseListener {

    private int row, col;

    public GridCellMouseHandler(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
      //  throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
      System.out.println("Row = " + row + ", Col = " + col);
      JPanel source = (JPanel) e.getSource();
      source.setBackground(Color.GRAY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      JPanel source = (JPanel) e.getSource();
      source.setBackground(Color.WHITE);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
      JPanel source = (JPanel) e.getSource();
      source.setBackground(Color.WHITE);
    }
    
}
