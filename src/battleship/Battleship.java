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

    private void playGame() {
        while(true) {
            performTurn(this.humanPlayer, this.compPlayer);
            if(winner != null) {
                break;
            }

            performTurn(this.compPlayer, this.humanPlayer);
            if(winner != null) {
                break;
            }
        }
    }

    private void performTurn(Player current, Player enemy) {
        int result = current.fireAt(enemy);
        if(enemy.lostShip()) {
            if(enemy.lostAllShips()) {
                winner = current;
            }
        }
        
    }

    private void endGame() {
        if(winner == this.humanPlayer) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }
}
