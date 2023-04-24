package battleship;

/**
 * WARNING:
 * This was refactored so that this program can be used in event-driven programs.
 * Thus, it is important to call these functions in the correct order because there is 
 * currently very little error handling checking if the functions being called are 
 * supposed to be called. For example, calling fire() when the game state is SETUP may
 * break something.
 * 
 */
public class Battleship {
    private Player p1;
    private Player p2;

    private Player currentPlayer;
    private Player enemyPlayer;
    private Player winner;

    public static int SETUP = 0;
    public static int GAME = 1;
    public static int END = 2;


    private int state; 


    public Battleship(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.currentPlayer = this.p1;
        this.enemyPlayer = this.p2;
        this.state = SETUP;
        this.winner = null;
    }

    public int getState() {return state;}

    public Player getCurrentPlayer() {return currentPlayer;}
    public Player getEnemyPlayer() {return enemyPlayer;}


    public void reset() {
        this.currentPlayer = this.p1;
        this.enemyPlayer = this.p2;

        this.currentPlayer.reset();
        this.enemyPlayer.reset();
        this.winner = null;
        this.state = SETUP;
    }

    public Player getWinner() {return this.winner;}

    public void startGame() {
        this.state = GAME;
    }

    private void nextTurn() {
        Player temp = this.currentPlayer;
        this.currentPlayer = this.enemyPlayer;
        this.enemyPlayer = temp;
    }

    public boolean fire() {
        int result = this.currentPlayer.fireAt(this.enemyPlayer);
        if(result == Grid.HIT_SHIP) {
            this.currentPlayer.useHitMessage();
        } else if(result == Grid.SUNK_SHIP) {
            this.currentPlayer.useSunkMessage();
        } else if(result == Grid.MISS) {
            this.currentPlayer.useMissMessage();
        } else {
            this.currentPlayer.useInvalidShotMessage();
            return false;
        }
        gameHasEnded();
        nextTurn();
        return true;
    }

    public boolean gameHasEnded() {
        if(this.winner != null) {
            return true;
        }

        if(this.currentPlayer.lostAllShips()) {
            this.winner = this.enemyPlayer;
        } else if(this.enemyPlayer.lostAllShips()) {
            this.winner = this.currentPlayer;
        }

        if(this.winner != null) {
            this.winner.useWinMessage();
            this.state = END;
            return true;
        }

        return false;
    }

    public void printCurrentPlayerGrid() {
        System.out.println("CurrentPlayer Grid");
        System.out.println(this.currentPlayer.getGrid());
    }

    public void printEnemyPlayerGrid() {
        System.out.println("EnemyPlayer Grid");
        System.out.println(this.enemyPlayer.getGrid());
    }
}
