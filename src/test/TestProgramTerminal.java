package test;

import java.util.Scanner;

import battleship.Battleship;

public class TestProgramTerminal {

    public static void main(String[] args) {
       Battleship game = new Battleship();
       char playAgain = 'n';
       do {
            game.startGame();
            System.out.println("Want to play again? (y,n): ");
            Scanner in = new Scanner(System.in);
            playAgain = in.nextLine().charAt(0);
            in.close();
       } while(playAgain == 'y');
    }
    
}
