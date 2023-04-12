package battleship;

public class Grid {
    public final int EMPTY = 0;
    public final int MISS = 1;
    public final int HIT_SHIP = 2;
    public final int UNHIT_SHIP = 3;
    public final int SUNK_SHIP = 4;

    private int[][] board = new int[10][10];

    private int[][] carrier = new int[5][2];
    private int[][] battleship = new int[4][2];
    private int[][] cruiser = new int[3][2];
    private int[][] submarine = new int[3][2];
    private int[][] destroyer = new int[2][2];

    private boolean placePoint(int x, int y) {
        if (board[x][y] == EMPTY) {
            board[x][y] = UNHIT_SHIP;
            return true;
        }
        else {
            return false;
        }
    }
    
    //returns true if the game is able to place the ship and false if it cant
    //this assumes that the ship is placed horizontally or vertically
    private boolean placeShip(int x1, int y1, int x2, int y2) {
        if (x1 < x2) {
            for (int i = x1; i <= x2; i++) {
                if (!placePoint(i, y1)) return false;
            }
        }
        else if (x2 < x1) {
            for (int i = x2; i <= x1; i++) {
                if (!placePoint(i, y1)) return false;
            }
        }
        else if (y1 < y2) {
            for (int i = y1; i <= y2; i++) {
                if (!placePoint(x1, i)) return false;
            }
        }
        else {
            for (int i = y2; i <= y1; i++) {
                if (!placePoint(x1, i)) return false;
            }
        }
        //the ship was placed successfully
        return true;
    }

    public boolean placeCarrier(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2);
    }

    public boolean placeBattleship(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2);
    }

    public boolean placeCruiser(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2);
    }

    public boolean placeSubmarine(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2);
    }

    public boolean placeDestroyer(int x1, int y1, int x2, int y2) {
        return placeShip(x1, y1, x2, y2);
    }

    //returns true if hit and false if miss
    public boolean guessPositon(int x, int y) {
        if (board[x][y] == 3) {
            return true;
        }
        else {
            return false;
        }
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
}