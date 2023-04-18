package battleship;
public class Battleship {
    private Player p1;
    private Player p2;
    private Player winner;

    public static int SETUP = 0;
    public static int GAME = 1;
    public static int END = 2;

    public int state; 

    public Battleship(Player p1, Player p2) {
        this.reset(p1, p2);
    }

    public int getState() {return state;}

    public void reset(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.winner = null;
    }

    public void startGame() {
        this.performSetup();
        this.playGame();
        this.endGame();
    }

    private void performSetup() {
        this.state = SETUP;
        this.p1.placeShips();
        this.p2.placeShips();
    }

    private void printGrids() {
        System.out.println("Computer Grid");
        System.out.println(this.p2.getGrid());
        System.out.println();
        System.out.println("Human Grid");
        System.out.println(this.p1.getGrid());
    }
    private void playGame() {
        this.state = GAME;
        while(true) {
            printGrids();
            performTurn(this.p1, this.p2);
            if(winner != null) {
                break;
            }

            printGrids();
            performTurn(this.p2, this.p1);
            if(winner != null) {
                break;
            }
        }
    }

    private void performTurn(Player current, Player enemy) {
        int result = current.fireAt(enemy);
        if(result == Grid.SUNK_SHIP) {
            if(enemy.lostAllShips()) {
                winner = current;
            } else {
                current.printSunkMessage();
            }
        } else if(result == Grid.HIT_SHIP) {
            current.printHitMessage();
        } else if(result == Grid.MISS) {
            current.printMissMessage();
        }
    }

    private void endGame() {
        this.state = END;
        winner.printWinMessage();
    }
}
