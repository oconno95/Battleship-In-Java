package battleship;

public class ComputerPlayer extends Player {


    public ComputerPlayer() {
        name = "Computer";
    }
    @Override
    public int[] getLocationToFireAt(Grid enemyGrid) {
        int x, y;
        do {
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
        } while(!enemyGrid.canFireAt(x, y));
        
        return new int[] {x, y};
    }

    @Override
    public void placeShips() {
        int[] coordinates;
        do {
            coordinates = getPositionsOfNShip(2);
        } while(!this.getGrid().placeDestroyer(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);

        do {
            coordinates = getPositionsOfNShip(3);
        } while(!this.getGrid().placeCruiser(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);

        do {
            coordinates = getPositionsOfNShip(3);
        } while(!this.getGrid().placeSubmarine(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);

        do {
            coordinates = getPositionsOfNShip(4);
        } while(!this.getGrid().placeBattleship(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);

        do {
            coordinates = getPositionsOfNShip(5);
        } while(!this.getGrid().placeCarrier(coordinates[0], coordinates[1], coordinates[2], coordinates[3]));
        System.out.println(coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + " " + coordinates[3]);

    }

    private int[] getPositionsOfNShip(int length) {
        int x0, y0, x1, y1;
        x0 = (int) (Math.random() * 10);
        y0 = (int) (Math.random() * 10);

        // 0 is right, 1 is up, 2 is left, 3 is down
        int direction = (int) (Math.random() * 4);

        switch(direction) {
            //right
            case 0:
                x1 = x0 + length - 1;
                y1 = y0;
                break;
            //up
            case 1:
                x1 = x0;
                y1 = y0 - length + 1;
                break;
            //left
            case 2:
                x1 = x0 - length + 1;
                y1 = y0;
                break;
            //down
            default:
                x1 = x0;
                y1 = y0 + length - 1;
                break;
        }
        return new int[] {x0, y0, x1, y1};
    }

  
    
}
