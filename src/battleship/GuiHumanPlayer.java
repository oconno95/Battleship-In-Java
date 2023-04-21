package battleship;

public class GuiHumanPlayer extends Player {
    public static final int PLACING_DESTROYER = 0;
    public static final int PLACING_CRUISOR = 1;
    public static final int PLACING_SUBMARINE = 2;
    public static final int PLACING_BATTLESHIP = 3;
    public static final int PLACING_CARRIER = 4;
    public static final int PLAYING_GAME = 5;

    public static final int LEFT = 6;
    public static final int RIGHT = 7;
    public static final int UP = 8;
    public static final int DOWN = 9;



    private int row = -1, col = -1;
    public int placeShipDirection;
    private Player enemy;
    private int state;

    public GuiHumanPlayer(Player enemy) {
        super();
        this.enemy = enemy;
        this.state = PLACING_DESTROYER;
    }
    
    @Override
    public int[] getLocationToFireAt(Grid enemyGrid) {
        int r, c;
        while(true) {
            if(enemy.getGrid().canFireAt(col, row)) {

                break;
            }
        }
        r = row;
        c = col;
        row = -1;
        col = -1;
        return new int[] {r,c};
    }

    @Override
    public void placeShips() {
        
    }


    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    
}
