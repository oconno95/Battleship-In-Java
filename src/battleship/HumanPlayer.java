package battleship;

import java.util.Scanner;

public class HumanPlayer extends Player {

    @Override
    public void placeShips() {
        Scanner k = new Scanner(System.in);
        placeNShip(2, k);
        placeNShip(3, k);
        placeNShip(3, k);
        placeNShip(4, k);
        placeNShip(5, k);
        k.close();
    }

    private void placeNShip(int n, Scanner k) {
        int x0, y0, x1, y1;
        do {
            System.out.print("Type in where the ends of the ship of length 2 will go (x0, y0, x1, y1): ");
            x0 = k.nextInt();
            y0 = k.nextInt();
            x1 = k.nextInt();
            y1 = k.nextInt();
        } while(!this.getGrid().placeShip(x0, y0, x1, y1));
    }
    @Override
    public int[] getLocationToFireAt(Grid enemyGrid) {
        Scanner k = new Scanner(System.in);
        int x, y;
        do {
            System.out.print("Pick a location to fire (x, y): ");
            x = k.nextInt();
            y = k.nextInt();
        } while(!enemyGrid.isEmpty());

        return new int[] {x, y};
    }

    
}
