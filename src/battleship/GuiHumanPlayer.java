package battleship;

public class GuiHumanPlayer extends Player {
    public static final int LEFT = 6;
    public static final int RIGHT = 7;
    public static final int UP = 8;
    public static final int DOWN = 9;


    private int row = -1, col = -1;
    public int placeShipDirection = RIGHT;

    public GuiHumanPlayer() {
        super();
        this.state = PLACING_DESTROYER;
    }

    @Override
    public void reset() {
        super.reset();
        this.state = PLACING_DESTROYER;
        row = -1;
        col = -1;
        placeShipDirection = RIGHT;
    }

    public int getNumberCellsOnShip() {
        switch(this.state) {
            case PLACING_DESTROYER:
                return 2;
            case PLACING_CRUISOR:
            case PLACING_SUBMARINE:
                return 3;
            case PLACING_BATTLESHIP:
                return 4;
            case PLACING_CARRIER:
                return 5;
            default:
                return 0;
        }
    }
    
    @Override
    public int[] getLocationToFireAt(Grid enemyGrid) {
        return new int[] {this.col, this.row};
    }

    public void rotateShip() {
        switch(placeShipDirection) {
            case LEFT:
                placeShipDirection = DOWN;
                break;
            case DOWN:
                placeShipDirection = RIGHT;
                break;
            case RIGHT:
                placeShipDirection = UP;
                break;
            default:
                placeShipDirection = LEFT;
        }
    }

    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void placeShip(int row, int col) {
        setLocation(row, col);
        switch(state) {
            case PLACING_DESTROYER:
                if(placeDestroyer()) {state = PLACING_CRUISOR;}
                break;
            case PLACING_CRUISOR:
                if(placeCruiser()) {state = PLACING_SUBMARINE;}
                break;
            case PLACING_SUBMARINE:
                if(placeSubmarine()) {state = PLACING_BATTLESHIP;}
                break;
            case PLACING_BATTLESHIP:
                if(placeBattleship()) {state = PLACING_CARRIER;}
                break;
            case PLACING_CARRIER:
                if(placeCarrier()) {state = PLAYING_GAME;}
                break;
            default:
                break;
        }
        System.out.println(this.state);
        System.out.println(this.getGrid().toString());
    }

    //when the player selects a point on the GUI, find the 2nd end of the ship being placed.
    private int[] getSecondEndPoint(int x, int y, int length) {
        switch(placeShipDirection) {
            case LEFT:
                return new int[] {x - length + 1, y};
            case RIGHT:
                return new int[] {x + length - 1, y};
            case DOWN:
                return new int[] {x, y + length - 1};
            default:
                return new int[] {x, y - length + 1};
        }
    }

    @Override
    public boolean placeDestroyer() {
        int[] coords = getSecondEndPoint(this.col, this.row, 2);
        System.out.println(coords[0] + " " + coords[1]);
        return this.getGrid().placeDestroyer(this.col, this.row, coords[0], coords[1]);
    }

    @Override
    public boolean placeCruiser() {
        int[] coords = getSecondEndPoint(this.col, this.row, 3);
        return this.getGrid().placeCruiser(this.col, this.row, coords[0], coords[1]);
    }

    @Override
    public boolean placeSubmarine() {
        int[] coords = getSecondEndPoint(this.col, this.row, 3);
        return this.getGrid().placeSubmarine(this.col, this.row, coords[0], coords[1]);
    }

    @Override
    public boolean placeBattleship() {
        int[] coords = getSecondEndPoint(this.col, this.row, 4);
        return this.getGrid().placeBattleship(this.col, this.row, coords[0], coords[1]);
    }

    @Override
    public boolean placeCarrier() {
        int[] coords = getSecondEndPoint(this.col, this.row, 5);
        return this.getGrid().placeCarrier(this.col, this.row, coords[0], coords[1]);
    }

    
}
