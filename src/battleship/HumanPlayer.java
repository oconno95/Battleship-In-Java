package battleship;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public static final Scanner in = new Scanner(System.in);

    public HumanPlayer() {
        name = "You";
    }

    @Override
    public void placeShips() {
        int[] coordinates;
        do {
            coordinates = getInput("Destroyer", 2);
        } while(!this.getGrid().placeDestroyer(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        do {
            coordinates = getInput("Cruiser", 3);
        } while(!this.getGrid().placeCruiser(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        do {
            coordinates = getInput("Submarine", 3);
        } while(!this.getGrid().placeSubmarine(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        do {
            coordinates = getInput("Battleship", 4);
        } while(!this.getGrid().placeBattleship(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        do {
            coordinates = getInput("Carrier", 5);
        } while(!this.getGrid().placeCarrier(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
    }


    private int[] getInput(String shipName, int sizeLimit) {
        System.out.print("Type in where the ends of the ship " + shipName + " of length " + sizeLimit + " will go (x0, y0, x1, y1): ");
        int x0 = in.nextInt();
        int y0 = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();

        return new int[] {x0, y0, x1, y1};
    }

    @Override
    public int[] getLocationToFireAt(Grid enemyGrid) {
        int x, y;
        do {
            System.out.print("Pick a location to fire (x, y): ");
            x = in.nextInt();
            y = in.nextInt();
        } while(!enemyGrid.canFireAt(x, y));

        return new int[] {x, y};
    }

    
}
