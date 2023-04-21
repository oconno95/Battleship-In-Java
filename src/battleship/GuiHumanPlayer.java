package battleship;

public class GuiHumanPlayer extends Player {
    public static final int LEFT = 6;
    public static final int RIGHT = 7;
    public static final int UP = 8;
    public static final int DOWN = 9;


    private int row = -1, col = -1;
    public int placeShipDirection;
    private Player enemy;

    public GuiHumanPlayer(Player enemy) {
        super();
        this.enemy = enemy;
        this.state = PLACING_DESTROYER;
    }
    
    @Override
    public int[] getLocationToFireAt(Grid enemyGrid) {
        return new int[] {this.col, this.row};
    }


    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void placeDestroyer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeDestroyer'");
    }

    @Override
    public void placeCruiser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeCruiser'");
    }

    @Override
    public void placeSubmarine() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeSubmarine'");
    }

    @Override
    public void placeBattleship() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeBattleship'");
    }

    @Override
    public void placeCarrier() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeCarrier'");
    }

    
}
