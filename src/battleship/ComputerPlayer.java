package battleship;

public class ComputerPlayer extends Player {

    @Override
    public int[] getLocationToFireAt(Grid grid) {
        int x, y;
        do {
            x = (int) (Math.random() * this.getGrid().size);
            y = (int) (Math.random() * this.getGrid().size);
        } while(!this.getGrid().isEmpty(x, y))
        
        return new int[] {x, y};
    }

    @Override
    public void placeShip() {
        this.placeNShip(2);
        this.placeNShip(3);
        this.placeNShip(3);
        this.placeNShip(4);
        this.placeNShip(5);

    }

    private void placeNShip(int n) {
        int x0, y0, x1, y1;
        do {
            x0 = (int) (Math.random() * this.getGrid().size);
            y0 = (int) (Math.random() * this.getGrid().size);

            // 0 is right, 1 is up, 2 is left, 3 is down
            int direction = (int) (Math.random() * 4);

            switch(direction) {
                //right
                case 0:
                    x1 = x0 + n;
                    y1 = y0;
                    break;
                //up
                case 1:
                    x1 = x0;
                    y1 = y0 - n;
                    break;
                //left
                case 2:
                    x1 = x0 - n;
                    y1 = y0;
                    break;
                //down
                default:
                    x1 = x0;
                    y1 = y0 + n;
                    break;
            }
        } while(!this.getGrid().placeShip(x0, y0, x1, y1));
    }

  
    
}
