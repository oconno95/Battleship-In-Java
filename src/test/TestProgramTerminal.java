package test;

import battleship.Battleship;
import battleship.ComputerPlayer;
import battleship.HumanPlayer;
import battleship.Player;

public class TestProgramTerminal {

    public static void placeShips(Player p) {
        p.placeDestroyer();
        p.placeCruiser();
        p.placeSubmarine();
        p.placeBattleship();
        p.placeCarrier();

    }
    public static void main(String[] args) {
       Battleship game = new Battleship(new HumanPlayer(), new ComputerPlayer());
       char playAgain = 'n';
       do {
            game.reset();
            
            //have players place their ships
            placeShips(game.getCurrentPlayer());
            placeShips(game.getEnemyPlayer());
            game.startGame(); //set the game state to GAME

            System.out.println("Your Grid:");
            game.printCurrentPlayerGrid();

            System.out.println("Computer Grid:");
            game.printEnemyPlayerGrid();

            //have the players keep firing until the game ends
            while(game.getState() != Battleship.END) {
                game.fire();
                game.printCurrentPlayerGrid();
            }
            game.getWinner().printWinMessage();
            System.out.println("Want to play again? (y,n): ");
            playAgain = HumanPlayer.in.next().charAt(0);
       } while(playAgain == 'y');

       HumanPlayer.in.close();
    }    
}
