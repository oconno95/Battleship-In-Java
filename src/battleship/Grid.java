package battleship;

public class Grid {
    public static final int EMPTY = 0;
    public static final int MISS = 1;
    public static final int HIT_SHIP = 2;
    public static final int UNHIT_SHIP = 3;
    public static final int SUNK_SHIP = 4;
    public static final int INVALID_SHOT = 5;

    private int[][] board = new int[10][10];

    private int[][] carrier     = new int[5][2];
    private int[][] battleship  = new int[4][2];
    private int[][] cruiser     = new int[3][2];
    private int[][] submarine   = new int[3][2];
    private int[][] destroyer   = new int[2][2];
    

    public void reset() {
        board       = new int[10][10];
        carrier     = new int[5][2];
        battleship  = new int[4][2];
        cruiser     = new int[3][2];
        submarine   = new int[3][2];
        destroyer   = new int[2][2];
    }

    private boolean placePoint(int x, int y) {
        if (board[y][x] == EMPTY) {
            board[y][x] = UNHIT_SHIP;
            return true;
        }
        else {
            return false;
        }
    }

    private void addShipPoint(int[][] ship, int position, int x, int y) {
        ship[position][0] = x;
        ship[position][1] = y;
    }
    
    //returns true if the game is able to place the ship and false if it cant
    //this assumes that the ship is placed horizontally or vertically
    private boolean placeShip(int x1, int y1, int x2, int y2, int length, int[][] ship) {
        if(!pointIsOnGrid(x1, y1) || !pointIsOnGrid(x2, y2)) {
            return false;
        }

        if (x1 < x2) {
            for (int x = x1, i = 0; x <= x2; i++, x++) {
                if(i >= length) return false;
                if (!positionIsEmpty(x, y1)) return false;
                else addShipPoint(ship, i, x, y1);
            }
        }
        else if (x2 < x1) {
            for (int x = x2, i = 0; x <= x1; i++, x++) {
                if(i >= length) return false;
                if (!positionIsEmpty(x, y1)) return false;
                else addShipPoint(ship, i, x, y1);
            }
        }
        else if (y1 < y2) {
            for (int y = y1, i = 0; i <= y2; i++, y++) {
                if(i >= length) return false;
                if (!positionIsEmpty(x1, y)) return false;
                else addShipPoint(ship, i, x1, y);
            }
        }
        else {
            for (int y = y2, i = 0; i <= y1; i++, y++) {
                if(i >= length) return false;
                if (!positionIsEmpty(x1, y)) return false;
                else addShipPoint(ship, i, x1, y);
            }
        }

        //updating board with positions of newly placed ship
        for(int[] pos : ship) {
            this.board[pos[1]][pos[0]] = UNHIT_SHIP;
        }

        //the ship was placed successfully
        return true;
    }

    public boolean placeCarrier(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 5, carrier);
    }

    public boolean placeBattleship(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 4, battleship);
    }

    public boolean placeCruiser(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 3, cruiser);
    }

    public boolean placeSubmarine(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 3, submarine);
    }

    public boolean placeDestroyer(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, 2, destroyer);
    }

    //Checks if the provided position is empty and returns true if it is
    public boolean positionIsEmpty(int x, int y) {
        if (board[y][x] == EMPTY) {
            return true;
        }
        else {
            return false;
        }
    }

    //Checks that the ship is fully inside the grid and returns true if it is
    public boolean shipIsInGrid(int x1, int y1, int x2, int y2) {
        if (x1 >= 0 && x1 <= 9 &&
            x2 >= 0 && x2 <= 9 &&
            y1 >= 0 && y1 <= 9 &&
            y2 >= 0 && y2 <= 9) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean canFireAt(int x, int y) {
        return board[y][x] == EMPTY || board[y][x] == UNHIT_SHIP;
    }
    public boolean pointIsOnGrid(int x, int y) {
        return x >= 0 && x <= 9 && y >= 0 && y <= 9;
    }

    //returns true if hit and false if miss
    public int guessPositon(int x, int y) {
        //y is row, x is column
        if (board[y][x] == UNHIT_SHIP) {
            board[y][x] = HIT_SHIP;
            if(isShipDestroyed(findWhichShip(x, y))) {
                return SUNK_SHIP;
            }
            return HIT_SHIP;
        } else if(board[y][x] == EMPTY) {
            board[y][x] = MISS;
            return MISS;
        }
        return INVALID_SHOT;
    }

    //run through the 5 ships and return which ship it hit
    private int[][] findWhichShip(int x, int y) {
        for(int[] coordinate : carrier) {
            if(x == coordinate[0] && y == coordinate[1]) {
                return carrier;
            }
        }
        for(int[] coordinate : battleship) {
            if(x == coordinate[0] && y == coordinate[1]) {
                return battleship;
            }
        }
        for(int[] coordinate : cruiser) {
            if(x == coordinate[0] && y == coordinate[1]) {
                return cruiser;
            }
        }
        for(int[] coordinate : submarine) {
            if(x == coordinate[0] && y == coordinate[1]) {
                return submarine;
            }
        }
        for(int[] coordinate : destroyer) {
            if(x == coordinate[0] && y == coordinate[1]) {
                return destroyer;
            }
        }

        return null;
    }

    //check if a given ship is destroyed and return true if it is
    public boolean isShipDestroyed(int[][] ship) {
        for(int[] coordinate : ship) {
            int positionState = board[coordinate[1]][coordinate[0]];
            if (positionState == UNHIT_SHIP) {
                return false;
            }
        }
        for(int[] coordinate : ship) {
            board[coordinate[1]][coordinate[0]] = SUNK_SHIP;
        }
        return true;
    }

    //retun true if all the ships are sunk
    public boolean isAllShipsDestroyed() {
        //reusing isShipDestroyed method for checking if all ships are destroyed
        if (isShipDestroyed(battleship) &&
            isShipDestroyed(carrier) &&
            isShipDestroyed(cruiser) &&
            isShipDestroyed(destroyer) &&
            isShipDestroyed(submarine)) {
            return true;
        }
        return false;
    }

    public String toString() {
        String grid = "";

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                grid = grid + board[i][j] + " ";
            }
            grid += "\n";
        }
        return grid;
    }
}