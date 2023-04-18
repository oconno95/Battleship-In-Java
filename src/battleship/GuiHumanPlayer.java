package battleship;

public class GuiHumanPlayer extends Player {

    private int row = -1, col = -1;

    private boolean shipHoriz = true;
    private Player enemy;

    public GuiHumanPlayer(Player enemy) {
        super();
        this.enemy = enemy;
    }
    
    @Override
    public int[] getLocationToFireAt(Grid enemyGrid) {
        return null;
    }

    @Override
    public void placeShips() {
        
    }


    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    
}
