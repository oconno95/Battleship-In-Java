package battleship;
public class Battleship {
    private HumanPlayer humanPlayer;
    private ComputerPlayer compPlayer;
    private Player winner;

    public Battleship() {
        this.reset();
    }

    public void reset() {
        this.humanPlayer = new HumanPlayer();
        this.compPlayer = new ComputerPlayer();
        this.winner = null;
    }

    public void startGame() {
        this.performSetup();
        this.playGame();
        this.endGame();
    }

    private void performSetup() {
        this.humanPlayer.placeShips();
        this.compPlayer.placeShips();
    }

    private void printGrids() {
        System.out.println("Computer Grid");
        System.out.println(this.compPlayer.getGrid());
        System.out.println();
        System.out.println("Human Grid");
        System.out.println(this.humanPlayer.getGrid());
    }
    private void playGame() {
        while(true) {
            printGrids();
            performTurn(this.humanPlayer, this.compPlayer);
            if(winner != null) {
                break;
            }

            printGrids();
            performTurn(this.compPlayer, this.humanPlayer);
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
        winner.printWinMessage();
    }
}
