package battleship;

public class Grid {
    public final int EMPTY = 0;
    public final int MISS = 1;
    public final int HIT_SHIP = 2;
    public final int UNHIT_SHIP = 3;
    public final int SUNK_SHIP = 4;

    private int[][] board = new int[10][10];

    private int[][] carrier     = new int[5][2];
    private int[][] battleship  = new int[4][2];
    private int[][] cruiser     = new int[3][2];
    private int[][] submarine   = new int[3][2];
    private int[][] destroyer   = new int[2][2];
    

    private boolean placePoint(int x, int y) {
        if (board[x][y] == EMPTY) {
            board[x][y] = UNHIT_SHIP;
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
    private boolean placeShip(int x1, int y1, int x2, int y2, int[][] ship) {
        if (x1 < x2) {
            for (int i = x1; i <= x2; i++) {
                if (!placePoint(i, y1)) return false;
                else addShipPoint(ship, i-x1, i, y1);
            }
        }
        else if (x2 < x1) {
            for (int i = x2; i <= x1; i++) {
                if (!placePoint(i, y1)) return false;
                else addShipPoint(ship, i-x2, i, y1);
            }
        }
        else if (y1 < y2) {
            for (int i = y1; i <= y2; i++) {
                if (!placePoint(x1, i)) return false;
                else addShipPoint(ship, i-y1, x1, i);
            }
        }
        else {
            for (int i = y2; i <= y1; i++) {
                if (!placePoint(x1, i)) return false;
                else addShipPoint(ship, i-y2, x1, i);
            }
        }

        //updating board with positions of newly placed ship
        for(int[] pos : ship) {
            this.board[pos[0]][pos[1]] = UNHIT_SHIP;
        }

        //the ship was placed successfully
        return true;
    }

    public boolean placeCarrier(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, carrier);
    }

    public boolean placeBattleship(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, battleship);
    }

    public boolean placeCruiser(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, cruiser);
    }

    public boolean placeSubmarine(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, submarine);
    }

    public boolean placeDestroyer(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2, destroyer);
    }

    //Checks if the provided position is empty and returns true if it is
    public boolean positionIsEmpty(int x, int y) {
        if (board[x][y] == 0) {
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

    //returns true if hit and false if miss
    public boolean guessPositon(int x, int y) {
        if (board[x][y] == UNHIT_SHIP) {
            board[x][y] = HIT_SHIP;
            isShipDestroyed(findWhichShip(x, y));
            return true;
        }
        else {
            return false;
        }
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
            int positionState = board[coordinate[0]][coordinate[1]];
            if (positionState == UNHIT_SHIP) {
                return false;
            }
        }
        for(int[] coordinate : ship) {
            board[coordinate[0]][coordinate[1]] = SUNK_SHIP;
        }
        return true;
    }

    //retun true if all the ships are sunk
    public boolean isAllShipsDestroyed() {
        if (board[carrier[0][0]][carrier[0][1]] == SUNK_SHIP &&
            board[battleship[0][0]][battleship[0][1]] == SUNK_SHIP &&
            board[cruiser[0][0]][cruiser[0][1]] == SUNK_SHIP &&
            board[submarine[0][0]][submarine[0][1]] == SUNK_SHIP &&
            board[destroyer[0][0]][destroyer[0][1]] == SUNK_SHIP) {
            
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